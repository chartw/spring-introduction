package twcha.h2project;

import org.apache.catalina.realm.DataSourceRealm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twcha.h2project.repository.JdbcMemberRepository;
import twcha.h2project.repository.MemberRepository;
import twcha.h2project.repository.MemoryMemberRepository;
import twcha.h2project.service.MemberService;

import javax.sql.DataSource;

/*
* 원래 bean 자동 등록을 하면 편하지만,
* 현재 Repository를 어떤형식으로 할지 확정되지 않았으므로
* 직접 자바 configuration을 통해 bean을 등록한다.
* */
@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        기존 코드를 수정하지 않고, 구현체만 memory -> jdbc로 교체할수 있다.
        return new JdbcMemberRepository(dataSource);
    }
}
