package devsprint.omuk.member;

import devsprint.omuk.member.repository.MemberPreferenceRepository;
import devsprint.omuk.member.repository.MemberRepository;
import devsprint.omuk.member.service.MemberService;
import org.springframework.context.annotation.Bean;

public class Config {
    private final MemberRepository memberRepository;
    private final MemberPreferenceRepository memberPreferenceRepository;

    public Config(MemberRepository memberRepository, MemberPreferenceRepository memberPreferenceRepository) {
        this.memberRepository = memberRepository;
        this.memberPreferenceRepository = memberPreferenceRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository, memberPreferenceRepository);
    }
}
