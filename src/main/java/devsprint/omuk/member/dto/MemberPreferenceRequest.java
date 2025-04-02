package devsprint.omuk.member.dto;

import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.entity.MemberEntity;
import lombok.Data;

import java.util.List;

@Data
public class MemberPreferenceRequest {
    private Long memberId;
    private List<String> taste;
    private List<String> allergy;
    private List<String> time;

}
