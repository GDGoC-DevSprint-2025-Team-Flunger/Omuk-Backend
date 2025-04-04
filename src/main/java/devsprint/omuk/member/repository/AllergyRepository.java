package devsprint.omuk.member.repository;

import devsprint.omuk.member.entity.AllergyEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergyRepository extends JpaRepository<AllergyEntity, Long> {
    @Transactional
    void deleteByMemberId(Long memberId);
}
