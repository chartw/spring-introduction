package twcha.h2project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twcha.h2project.repository.MemberRepository;
import twcha.h2project.repository.MemoryMemberRepository;
import twcha.h2project.service.MemberService;

/*
* 원래 bean 자동 등록을 하면 편하지만,
* 현재 Repository를 어떤형식으로 할지 확정되지 않았으므로
* 직접 자바 configuration을 통해 bean을 등록한다.
* */
@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
