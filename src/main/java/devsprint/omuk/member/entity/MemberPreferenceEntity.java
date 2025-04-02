package devsprint.omuk.member.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.dto.Allergy;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;
import jakarta.persistence.*;
import lombok.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
}
