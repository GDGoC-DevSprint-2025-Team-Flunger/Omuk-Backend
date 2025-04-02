package devsprint.omuk.member.controller;
import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberPreferenceResponse;
import devsprint.omuk.member.dto.MemberResponse;
import devsprint.omuk.member.dto.MemberSaveRequest;
import devsprint.omuk.member.service.MemberPreferenceService;
import devsprint.omuk.member.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/member")
public class MemberController{

    private final MemberService memberService;
    private final MemberPreferenceService memberPreferenceService;

    public MemberController(MemberService memberService, MemberPreferenceService memberPreferenceService) {
        this.memberService = memberService;
        this.memberPreferenceService = memberPreferenceService;
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

    @PostMapping("/preference/{memberId}")
    public ResponseEntity<String> savePreference(
            @PathVariable Long memberId,
            @RequestBody MemberPreferenceRequest request) {

        // 🔹 memberId를 DTO에 설정
        request.setMemberId(memberId);

        memberPreferenceService.saveMemberPreference(request);
        return ResponseEntity.ok("회원 선호 정보가 저장되었습니다.");
    }

    @GetMapping("/preference/{memberId}")
    public ResponseEntity<MemberPreferenceResponse> getPreferenceByMemberId(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getPreferenceByMemberId(memberId));
    }


}
