package devsprint.omuk.member.dto;

import devsprint.omuk.member.entity.MemberPreferenceEntity;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;
import devsprint.omuk.member.dto.Allergy;
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

    // Getter (필요에 따라 Setter도 추가 가능)
    public Long getMemberId() {
        return memberId;
    }

    public List<Taste> getTaste() {
        return taste;
    }

    public List<Allergy> getAllergy() {
        return allergy;
    }

    public List<Time> getTime() {
        return time;
    }
}
