package devsprint.omuk.member.repository;

import devsprint.omuk.member.entity.MemberPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberPreferenceRepository extends JpaRepository<MemberPreferenceEntity, Long> {
    MemberPreferenceEntity findByMemberId(Long memberId);
}
