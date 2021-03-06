package twcha.springintroduction.service;

import twcha.springintroduction.domain.Member;
import twcha.springintroduction.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

// jpa를 쓰려면 항상 Service 계층에 Transactional이 있어야함.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 금지
        validateDuplicateMember(member); // 리팩터링 extract method로 빼기
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                System.out.println(m.toString());
                throw new IllegalStateException("이미 존재하는 회원입니다");
            });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();

    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
