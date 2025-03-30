package devsprint.omuk.member.dto;

import devsprint.omuk.member.domain.Member;
import devsprint.omuk.member.entity.MemberEntity;
import lombok.Data;

@Data
public class MemberSaveRequest {

    private String name;
    private String email;

    public Member toDomain(){
        return new Member(name, email);
    }

}
