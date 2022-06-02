package twcha.h2project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import twcha.h2project.domain.Member;

import java.util.Optional;

// Spring Data Jpa가 JpaRepository를 상속 받고 있으면,
// 구현체를 자동으로 만들어서 Spring bean에 등록함.
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
//    기본적인 CRUD는 인터페이스로 제공.
//    나머지 공통이아닌 특수한 인터페이스는 아래와 같이 구현.

//    JPQL select m from Member m where m.name = ?
//    findByNameAndId ....와 같이 네이밍 룰이 있다.
//    인터페이스의 이름만으로 구현이 가능하다.
    @Override
    Optional<Member> findByName(String name);
}


// Jpa로 해결하기 어려운경우, 네이티브 쿼리, JdbcTemplate, Querydsl을 사용.
