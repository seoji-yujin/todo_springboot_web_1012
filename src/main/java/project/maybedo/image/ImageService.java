//package project.maybedo.image;
//
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//import project.maybedo.image.imageDTO.ImageResponseDTO;
//import project.maybedo.image.imageDTO.ImageUploadDTO;
//import project.maybedo.member.Member;
//import project.maybedo.member.MemberRepository;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//@RequiredArgsConstructor
//public class ImageService {
//
//    private final MemberRepository memberRepository;
//    private final ImageRepository imageRepository;
//
//    @Value("${file.path}")
//    private String uploadFolder;
//
//    public void upload(ImageUploadDTO imageUploadDTO, String username) {
//        Member member = memberRepository.findByUsername(username);
//        MultipartFile file = imageUploadDTO.getFile();
//
//        UUID uuid = UUID.randomUUID();
//        String imageFileName = uuid + "_" + file.getOriginalFilename();
//
//        File destinationFile = new File(uploadFolder + imageFileName);
//
//        try {
//            file.transferTo(destinationFile);
//
//            Image image = imageRepository.findByMember(member);
//            if (image != null) {
//                // 이미지가 이미 존재하면 url 업데이트
//                image.updateUrl("/profileImages/" + imageFileName);
//            } else {
//                // 이미지가 없으면 객체 생성 후 저장
//                image = Image.builder()
//                        .member(member)
//                        .url("/profileImages/" + imageFileName)
//                        .build();
//            }
//            imageRepository.save(image);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public ImageResponseDTO findImage(String username) {
//        Member member = memberRepository.findByUsername(username);
//        Image image = imageRepository.findByMember(member);
//
//        String defaultImageUrl = "/profileImages/anonymous.png";
//
//        if (image == null) {
//            return ImageResponseDTO.builder()
//                    .url(defaultImageUrl)
//                    .build();
//        } else {
//            return ImageResponseDTO.builder()
//                    .url(image.getUrl())
//                    .build();
//        }
//    }
//}
