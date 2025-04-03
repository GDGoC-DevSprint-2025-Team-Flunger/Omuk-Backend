package devsprint.omuk.member.repository;

import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.dto.MemberPreferenceProjection;
import devsprint.omuk.member.entity.MemberPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberPreferenceRepository extends JpaRepository<MemberPreferenceEntity, Long> {


    @Query(value = """
        SELECT 
            m.member_id AS memberId,
            COALESCE(t.taste_types, '') AS tasteTypes,
            COALESCE(a.allergy_types, '') AS allergyTypes,
            COALESCE(tm.time_types, '') AS timeTypes
        FROM 
            (SELECT DISTINCT member_id FROM taste 
             UNION 
             SELECT DISTINCT member_id FROM allergy 
             UNION 
             SELECT DISTINCT member_id FROM time) AS m
        LEFT JOIN 
            (SELECT member_id, STRING_AGG(type, ',') AS taste_types FROM taste GROUP BY member_id) AS t 
            ON m.member_id = t.member_id
        LEFT JOIN 
            (SELECT member_id, STRING_AGG(type, ',') AS allergy_types FROM allergy GROUP BY member_id) AS a 
            ON m.member_id = a.member_id
        LEFT JOIN 
            (SELECT member_id, STRING_AGG(type, ',') AS time_types FROM time GROUP BY member_id) AS tm 
            ON m.member_id = tm.member_id
        WHERE m.member_id = :memberId
    """, nativeQuery = true)
    MemberPreferenceProjection findByMemberId(Long memberId);
}
