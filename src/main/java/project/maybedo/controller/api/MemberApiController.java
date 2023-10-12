package project.maybedo.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.maybedo.controller.dto.ResponseDto;
import project.maybedo.domain.Member;
import project.maybedo.service.MemberService;

@RestController
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    @PostMapping("joinProc")
    public ResponseDto<Integer> save(@RequestBody Member member){
        System.out.println("MemberApiController: save 호출됨");
        memberService.join(member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

}
