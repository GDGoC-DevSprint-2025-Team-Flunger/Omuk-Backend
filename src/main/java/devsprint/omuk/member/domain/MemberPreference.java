package devsprint.omuk.member.domain;

import devsprint.omuk.member.dto.Allergy;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;
import lombok.Getter;

import java.util.List;

@Getter
public class MemberPreference {
    private Long memberId;
    private String taste;
    private String time;
    private String allergy;

    public MemberPreference(Long memberId, String taste, String time, String allergy) {
        this.memberId = memberId;
        this.taste = taste;
        this.time = time;
        this.allergy = allergy;
    }

}
