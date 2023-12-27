package project.maybedo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import project.maybedo.image.imageDTO.ImageResponseDTO;
import project.maybedo.image.imageDTO.ImageUploadDTO;
import project.maybedo.member.Member;
import project.maybedo.member.MemberRepository;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;

    public String upload(ImageUploadDTO imageUploadDTO, String username) {
        Member member = memberRepository.findByUsername(username);
        MultipartFile file = imageUploadDTO.getFile();

        UUID uuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();
        int idx = originFileName.lastIndexOf('.');
        String extension = originFileName.substring(idx);

        String projectPath = System.getProperty("user.dir") + "/src/main/images/"; // 파일이 저장될 폴더의 경로
        System.out.println("projectPath"+projectPath);
        String resultFileName = uuid + extension;
        File destinationFile = new File(projectPath + resultFileName);

        try {
            file.transferTo(destinationFile);

            Image image = imageRepository.findByMember(member);
            if (image != null) {
                // 사용자의 이전 프로필 이미지를 로컬 저장소에서 제거
//                String prevFilename = image.getUrl().substring("/images/".length());
//                System.out.println("prevFilename"+prevFilename);
//                File deleteFile = new File(projectPath + prevFilename);
//                deleteFile.delete();

                // 이미지가 이미 존재하면 url 업데이트
                image.updateUrl(resultFileName);
            } else {
                // 이미지가 없으면 객체 생성 후 저장
                image = Image.builder()
                        .member(member)
                        .url(resultFileName)
                        .build();
            }
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (resultFileName);
    }

    public ImageResponseDTO findImage(String username) {
        Member member = memberRepository.findByUsername(username);
        Image image = imageRepository.findByMember(member);

        String defaultImageUrl = "anonymous.png";

        if (image == null) {
            return ImageResponseDTO.builder()
                    .url(defaultImageUrl)
                    .build();
        } else {
            return ImageResponseDTO.builder()
                    .url(image.getUrl())
                    .build();
        }
    }
}
