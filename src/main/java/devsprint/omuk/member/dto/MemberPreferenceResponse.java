package devsprint.omuk.member.dto;

import devsprint.omuk.member.entity.MemberPreferenceEntity;
import lombok.Data;

import java.util.List;

@Data
public class MemberPreferenceResponse {

    private Long memberId;
    private List<Taste> taste;
    private List<Allergy> allergy;
    private List<Time> time;

    // 생성자
    public MemberPreferenceResponse(MemberPreferenceEntity memberPreferenceEntity) {
        this.memberId = memberPreferenceEntity.getMemberId();
        this.taste = memberPreferenceEntity.getTaste();
        this.allergy = memberPreferenceEntity.getAllergy();
        this.time = memberPreferenceEntity.getTime();
    }
}
