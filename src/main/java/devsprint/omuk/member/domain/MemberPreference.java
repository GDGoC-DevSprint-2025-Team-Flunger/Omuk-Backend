package devsprint.omuk.member.domain;

import devsprint.omuk.member.dto.Allergy;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberPreference {
    private Long memberId;
    private List<Taste> taste;
    private List<Time> time;
    private List<Allergy> allergy;

    public MemberPreference(Long memberId, List<Taste> taste, List<Time> time, List<Allergy> allergy) {
        this.memberId = memberId;
        this.taste = taste;
        this.time = time;
        this.allergy = allergy;
    }

}
