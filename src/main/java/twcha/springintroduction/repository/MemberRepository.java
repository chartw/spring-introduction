package twcha.springintroduction.repository;

import twcha.springintroduction.domain.Member;

import java.util.List;
import java.util.Optional;

/*
* 현재 어떤 db를 사용할지 정해지지 않은 상황
* 인터페이스를 활용함으로써 미리 개발후, db 정할수 있다.
* 인터페이스는 C에서 선언하는것과 비슷
* 최소한의 형식을 정해준다고 생각하자.
* 즉, 인터페이스로 정의된 내용을 꼭 포함하고, 다른 변수나 함수를 추가해도 된다.*/
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
