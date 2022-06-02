package twcha.springintroduction.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import twcha.springintroduction.domain.Member;
import twcha.springintroduction.repository.MemoryMemberRepository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    MemberService memberService = new MemberService(memberRepository);

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();

    }

    @Test
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
//        아래 try catch보단 위의 방식으로 에러 캐치 및 테스트 하자.
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }
//


        //then

    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}