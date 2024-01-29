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
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String upload(MultipartFile file) {
        UUID uuid = UUID.randomUUID();
        String originFileName = file.getOriginalFilename();
        int idx = originFileName.lastIndexOf('.');
        String extension = originFileName.substring(idx);

        String projectPath = "/home/ec2-user/web/maybedo/backend/src/main/webapp/"; // 파일이 저장될 폴더의 경로
        String resultFileName = uuid + extension;
        File destinationFile = new File(projectPath + resultFileName);

        try {
            file.transferTo(destinationFile);

            Image image = new Image();
            image.setUrl(resultFileName);
            image.setOriginFileName(originFileName);
            imageRepository.save(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return (resultFileName);
    }
}
