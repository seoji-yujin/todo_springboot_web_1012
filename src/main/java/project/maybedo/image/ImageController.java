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
    public ResponseDto<String> upload(ImageUploadDTO imageUploadDTO) {
        String fileName = imageService.upload(imageUploadDTO);

        return new ResponseDto<String>(HttpStatus.OK.value(), fileName);
    }
}
