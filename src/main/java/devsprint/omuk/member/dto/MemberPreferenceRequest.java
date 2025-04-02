package devsprint.omuk.member.dto;

import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.entity.MemberEntity;
import lombok.Data;

import java.util.List;

@Data
public class MemberPreferenceRequest {
    private Long memberId;
    private String taste;
    private String time;
    private String allergy;

    public MemberPreference toDomain(){
        return new MemberPreference(memberId, taste, time, allergy);
    }

}
