package twcha.h2project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import twcha.h2project.domain.Member;
import twcha.h2project.repository.MemberRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

// 스프링 엮어서 테스트할때 아래 두개 어노테이션 추가
@SpringBootTest
// 테스트 케이스에서만 이 어노테이션을 사용하면, 테스트 시작전 트랜젹션을 시작하고,
// 테스트 완료후 항상 롤백한다. 이렇게 하여 db와 테스트의 의존성을 제거한다.
@Transactional
class MemberServiceIntegrationTest {

//    테스트 케이스에서는 필드 인젝션하는것이 편하다. 문제없다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
//    Transactional을 해도, Commit을 하면 롤백이 되지 않는다.
//    @Commit
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");

    }

}