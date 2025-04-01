package devsprint.omuk.member.entity;

import com.fasterxml.jackson.databind.ObjectMapper;
import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.dto.Allergy;
import devsprint.omuk.member.dto.Taste;
import devsprint.omuk.member.dto.Time;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "preference")
@Data
public class MemberPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Setter
    @Column(name = "taste")
    private String tasteJson;

    @Setter
    @Column(name = "time")
    private String timeJson;

    @Setter
    @Column(name = "allergy")
    private String allergyJson;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    // List<Taste>로 변환하여 반환
    public List<Taste> getTaste() {
        return parseTasteJson(this.tasteJson);
    }

    // List<Allergy>로 변환하여 반환
    public List<Allergy> getAllergy() {
        return parseAllergyJson(this.allergyJson);
    }

    // List<Time>로 변환하여 반환
    public List<Time> getTime() {
        return parseTimeJson(this.timeJson);
    }

    // JSON을 List<Taste>로 변환
    private List<Taste> parseTasteJson(String jsonArray) {
        try {
            List<String> tasteNames = objectMapper.readValue(jsonArray, List.class);
            return tasteNames.stream().map(Taste::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // JSON을 List<Allergy>로 변환
    private List<Allergy> parseAllergyJson(String jsonArray) {
        try {
            List<String> allergyNames = objectMapper.readValue(jsonArray, List.class);
            return allergyNames.stream().map(Allergy::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // JSON을 List<Time>로 변환
    private List<Time> parseTimeJson(String jsonArray) {
        try {
            List<String> timeNames = objectMapper.readValue(jsonArray, List.class);
            return timeNames.stream().map(Time::valueOf).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // MemberPreference를 MemberPreferenceEntity로 변환
    public static MemberPreferenceEntity of(MemberPreference memberPreference) {
        MemberPreferenceEntity entity = new MemberPreferenceEntity();
        entity.setMemberId(memberPreference.getMemberId());
        entity.setTasteJson(toTasteJsonString(memberPreference.getTaste()));  // taste 값을 JSON으로 변환하여 설정
        entity.setAllergyJson(toAllergyJsonString(memberPreference.getAllergy()));  // allergy 값을 JSON으로 변환하여 설정
        entity.setTimeJson(toTimeJsonString(memberPreference.getTime()));  // time 값을 JSON으로 변환하여 설정
        return entity;
    }

    // List<Taste>를 JSON 문자열로 변환
    public static String toTasteJsonString(List<Taste> list) {
        try {
            List<String> tasteNames = list.stream().map(Taste::name).collect(Collectors.toList());
            return objectMapper.writeValueAsString(tasteNames);
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";  // 변환 실패 시 빈 배열을 반환
        }
    }

    // List<Allergy>를 JSON 문자열로 변환
    public static String toAllergyJsonString(List<Allergy> list) {
        try {
            List<String> allergyNames = list.stream().map(Allergy::name).collect(Collectors.toList());
            return objectMapper.writeValueAsString(allergyNames);
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";  // 변환 실패 시 빈 배열을 반환
        }
    }

    // List<Time>을 JSON 문자열로 변환
    public static String toTimeJsonString(List<Time> list) {
        try {
            List<String> timeNames = list.stream().map(Time::name).collect(Collectors.toList());
            return objectMapper.writeValueAsString(timeNames);
        } catch (IOException e) {
            e.printStackTrace();
            return "[]";  // 변환 실패 시 빈 배열을 반환
        }
    }
}
