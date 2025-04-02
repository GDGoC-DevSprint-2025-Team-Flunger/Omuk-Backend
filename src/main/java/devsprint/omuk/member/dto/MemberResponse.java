package devsprint.omuk.member.dto;

import devsprint.omuk.member.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberResponse {
    private Long id;
    private String name;
    private String email;

    public MemberResponse(MemberEntity member) {
        this.id = member.getId();
        this.name = member.getName();
        this.email = member.getEmail();
    }
}
