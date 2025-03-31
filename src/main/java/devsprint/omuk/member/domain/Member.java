package devsprint.omuk.member.domain;

import lombok.Getter;

@Getter
public class Member {
    private String name;
    private String email;
    // 비즈니스 로직

    public Member(String name, String email) {
        this.name = name;
        this.email = email;
    }

}
