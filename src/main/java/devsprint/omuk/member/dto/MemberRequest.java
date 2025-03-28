package devsprint.omuk.member.dto;

import devsprint.omuk.member.domain.Member;
import lombok.Data;

@Data
public class MemberRequest {

    private String name;
    private String email;

    public Member toDomain(){
        return new Member(name, email);
    }
}
