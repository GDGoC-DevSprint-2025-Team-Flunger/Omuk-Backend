package devsprint.omuk.member.repository;

import devsprint.omuk.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByName(String name);
    Optional<MemberEntity> findByEmail(String email);
//    void saveMemberInformation(MemberEntity member);
//    void saveMemberPreference(MemberPreference memberPreference);
//    Optional<MemberEntity> findById(Long id);
//    Optional<MemberEntity> findByName(String name);

}
