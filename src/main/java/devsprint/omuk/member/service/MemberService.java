package devsprint.omuk.member.service;

import devsprint.omuk.member.domain.Member;
import devsprint.omuk.member.domain.MemberPreference;
import devsprint.omuk.member.dto.*;
import devsprint.omuk.member.entity.MemberEntity;
import devsprint.omuk.member.entity.MemberPreferenceEntity;
import devsprint.omuk.member.repository.MemberPreferenceRepository;
import devsprint.omuk.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;

    public MemberService(MemberRepository memberRepository, MemberPreferenceRepository memberPreferenceRepository) {
        this.memberRepository = memberRepository;
        this.memberPreferenceRepository = memberPreferenceRepository;
    }


    public void saveMember(MemberSaveRequest memberRequest){
        Member member = memberRequest.toDomain();
        memberRepository.save(MemberEntity.of(member));
    }

    public MemberResponse getMemberData(Long memberId){
        MemberEntity memberEntity = memberRepository.findById(memberId).get();
        return new MemberResponse(memberEntity);
    }

//    public void saveMemberPreference(MemberPreferenceRequest memberPreferenceRequest){
//        MemberPreference memberPreference = memberPreferenceRequest.toDomain();
//        memberPreferenceRepository.save(MemberPreferenceEntity.of(memberPreference));
//    }

    public MemberPreferenceResponse getPreferenceByMemberId(Long memberId) {
        MemberPreferenceProjection projection = memberPreferenceRepository.findByMemberId(memberId);

        if (projection == null) {
            throw new IllegalArgumentException("해당 memberId에 대한 데이터가 없습니다.");
        }

        return new MemberPreferenceResponse(projection);
    }

}
