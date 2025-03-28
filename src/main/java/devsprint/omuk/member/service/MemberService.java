package devsprint.omuk.member.service;

import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberSaveRequest;
//import devsprint.omuk.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
//    @Autowired
//    private MemberRepository memberRepository;

    public void saveMember(MemberSaveRequest memberRequest){
        System.out.println(memberRequest);
        //memberRepository.saveMemberInformation(memberRequest.toDomain());
    }

    public void saveMemberPreference(MemberPreferenceRequest memberPreferenceRequest){
        System.out.println(memberPreferenceRequest);
        //memberRepository.saveMemberPreference(memberPreferenceRequest.toDomain());
    }
}
