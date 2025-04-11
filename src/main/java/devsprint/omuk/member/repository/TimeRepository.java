package devsprint.omuk.member.repository;

import devsprint.omuk.member.entity.TimeEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<TimeEntity, Long> {
    @Transactional
    void deleteByMemberId(Long memberId);
}
