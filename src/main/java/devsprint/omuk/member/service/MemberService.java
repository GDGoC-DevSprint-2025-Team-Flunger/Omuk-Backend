package devsprint.omuk.member.service;

import devsprint.omuk.member.domain.Member;
import devsprint.omuk.member.dto.MemberPreferenceRequest;
import devsprint.omuk.member.dto.MemberRequest;
import devsprint.omuk.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public void saveMember(MemberRequest memberRequest){
        memberRepository.save(memberRequest.toDomain());
    }
}
