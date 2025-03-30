package devsprint.omuk.member;

import devsprint.omuk.member.repository.MemberRepository;
import devsprint.omuk.member.service.MemberService;
import org.springframework.context.annotation.Bean;

public class Config {
    private final MemberRepository memberRepository;

    public Config(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
}
