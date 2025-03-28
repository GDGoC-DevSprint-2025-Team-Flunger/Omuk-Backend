package devsprint.omuk.member.dto;

import lombok.Data;

@Data
public class MemberPreferenceRequest {

    private Taste taste;
    private Time time;
    private Allergy allergy;

}
