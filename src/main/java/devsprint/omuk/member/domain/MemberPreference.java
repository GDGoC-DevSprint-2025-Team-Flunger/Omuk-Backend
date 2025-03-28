package devsprint.omuk.member.domain;

import devsprint.omuk.member.dto.Allergy;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;

public class MemberPreference {
    private Taste taste;
    private Time time;
    private Allergy allergy;

    public MemberPreference(Taste taste, Time time, Allergy allergy) {
        this.taste = taste;
        this.time = time;
        this.allergy = allergy;
    }
}
