package devsprint.omuk.member.service;

import devsprint.omuk.member.domain.Member;
import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.dto.MemberPreferenceResponse;
import devsprint.omuk.member.entity.MemberEntity;
import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberSaveRequest;
import devsprint.omuk.member.entity.MemberPreferenceEntity;
import devsprint.omuk.member.repository.MemberPreferenceRepository;
import devsprint.omuk.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    private MemberPreferenceRepository memberPreferenceRepository;

    public void saveMember(MemberSaveRequest memberRequest){
        Member member = memberRequest.toDomain();
        memberRepository.save(MemberEntity.of(member));
    }

    public void saveMemberPreference(MemberPreferenceRequest memberPreferenceRequest){
        MemberPreference memberPreference = memberPreferenceRequest.toDomain();
        memberPreferenceRepository.save(MemberPreferenceEntity.of(memberPreference));
    }

    public MemberPreferenceResponse getMemberPreference(Long memberId) {
        // memberId로 preference 정보 조회
        MemberPreferenceEntity memberPreferenceEntity = memberPreferenceRepository.findByMemberId(memberId);

        // 조회된 정보가 없으면 예외 처리
        if (memberPreferenceEntity == null) {
            throw new RuntimeException("Preference not found for memberId: " + memberId);
        }

        // DTO로 변환하여 반환
        return new MemberPreferenceResponse(memberPreferenceEntity);
    }
}
