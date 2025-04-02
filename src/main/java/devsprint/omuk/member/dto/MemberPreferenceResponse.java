package devsprint.omuk.member.dto;

import devsprint.omuk.member.entity.MemberPreferenceEntity;
import lombok.Data;

import java.util.List;

@Data
public class MemberPreferenceResponse {

    private Long memberId;
    private String taste;
    private String allergy;
    private String time;

    // Projection을 받아서 DTO로 변환하는 생성자
    public MemberPreferenceResponse(MemberPreferenceProjection projection) {
        this.memberId = projection.getMemberId();
        this.taste = projection.getTasteTypes();
        this.allergy = projection.getAllergyTypes();
        this.time = projection.getTimeTypes();
    }
}

