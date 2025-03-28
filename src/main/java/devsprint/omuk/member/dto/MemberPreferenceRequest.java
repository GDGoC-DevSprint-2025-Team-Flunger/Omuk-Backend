package devsprint.omuk.member.dto;

import devsprint.omuk.member.domain.MemberPreference;
import lombok.Data;

@Data
public class MemberPreferenceRequest {

    private Taste taste;
    private Time time;
    private Allergy allergy;

    public MemberPreference toDomain(){
        return new MemberPreference(taste,time, allergy);
    }
}
