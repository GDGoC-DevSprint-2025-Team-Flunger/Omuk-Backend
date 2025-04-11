package devsprint.omuk.member.repository;

import devsprint.omuk.member.entity.TasteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasteRepository extends JpaRepository<TasteEntity, Long> {
    @Transactional
    void deleteByMemberId(Long memberId);
}
