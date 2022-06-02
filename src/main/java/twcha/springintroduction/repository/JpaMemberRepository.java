package twcha.springintroduction.repository;

import twcha.springintroduction.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // Jpa 사용을 위해 EntityManager를 주입받아야 한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

//  PK 기반의 기능들은 간단하게 구현가능
    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

//    나머지는 jpql 작성해서 조회해야함.
//    data jpa를 사용하면 jpql 안짜도 된다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select  m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        Ctrl + Alt + N -> Inline
//        jpql 쿼리 언어
//        객체(Entity)를 대상으로 쿼리를 날리면 sql로 번역이 된다.
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
