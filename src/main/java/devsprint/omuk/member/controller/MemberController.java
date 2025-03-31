package devsprint.omuk.member.controller;
import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberPreferenceResponse;
import devsprint.omuk.member.dto.MemberResponse;
import devsprint.omuk.member.dto.MemberSaveRequest;
import devsprint.omuk.member.service.MemberService;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberData(@PathVariable Long id){
        MemberResponse memberResponse = memberService.getMemberData(id);
        return ResponseEntity.ok(memberResponse);
    }

    @PostMapping("/preference")
    public ResponseEntity<Void> addPreference(@RequestBody MemberPreferenceRequest memberPreferenceRequest) {
        memberService.saveMemberPreference(memberPreferenceRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/preference/{memberId}")
    public ResponseEntity<MemberPreferenceResponse> getMemberPreference(@PathVariable Long memberId) {
        MemberPreferenceResponse preferenceResponse = memberService.getMemberPreference(memberId);
        return ResponseEntity.ok(preferenceResponse);
    }

}
