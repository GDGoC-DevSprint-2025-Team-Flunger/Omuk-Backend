package devsprint.omuk.member.entity;

import devsprint.omuk.member.domain.Member;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "member")
@Data
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    public MemberEntity(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public MemberEntity() {

    }
}
