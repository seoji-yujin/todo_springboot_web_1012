package project.maybedo.image;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.dto.ResponseDto;
import project.maybedo.image.imageDTO.ImageUploadDTO;
import project.maybedo.member.Member;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    @PostMapping("/upload")
    public ResponseDto<Integer> upload(ImageUploadDTO imageUploadDTO, HttpSession session) {
        Member member = (Member)session.getAttribute("principal");
        imageService.upload(imageUploadDTO, member.getUsername());

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
