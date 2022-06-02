package twcha.h2project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twcha.h2project.repository.*;
import twcha.h2project.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/*
* 원래 bean 자동 등록을 하면 편하지만,
* 현재 Repository를 어떤형식으로 할지 확정되지 않았으므로
* 직접 자바 configuration을 통해 bean을 등록한다.
* */
@Configuration
public class SpringConfig {

//    DI
//    DataSource dataSource;
//
////    dataSource Error 무시해도 됨.
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {

//        return new MemoryMemberRepository();
//        인터페이스를 활용하여 기존 코드를 변경하지 않고, db 교체 가능.
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
