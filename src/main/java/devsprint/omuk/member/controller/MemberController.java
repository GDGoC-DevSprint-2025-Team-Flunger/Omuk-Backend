package devsprint.omuk.member.controller;
import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberSaveRequest;
//import devsprint.omuk.member.repository.MemberRepository;
import devsprint.omuk.member.service.MemberService;
import jdk.jshell.Snippet;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController{

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping()
    public ResponseEntity<Void> addMember(@RequestBody MemberSaveRequest memberSaveRequest) {
        memberService.saveMember(memberSaveRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/preference")
    public ResponseEntity<Void> addPreference(MemberPreferenceRequest memberPreferenceRequest) {
        memberService.saveMemberPreference(memberPreferenceRequest);
        return ResponseEntity.ok().build();
    }
}
