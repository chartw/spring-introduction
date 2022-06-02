/*
공통 관심사항과 핵심 관심사항을 분리하는것. 공통관심사항 : 시간측정 / 핵심관심사항 : 회원가입... 등
공통 관심사항을 따로 구현하여 뿌리듯이 적용.
많은 코드를 수정하지 않고도, 여러 메서드, 인터페이스에 적용가능.

호출 중간에 인터셉트해서 필터링 할수도 있다. ex) 호출시간이 몇초이상 걸리면 캔슬

Aop는 실제 서비스가 아닌, 프록시(가짜) 서비스를 호출하고, 그 이후 실제 서비스를 호출하는 방식.
 */
package twcha.h2project.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// Aop 어노테이션
@Aspect
// Component 스캔통해 등록하지 않고, Spring Bean에 직접 등록해서 사용하는것 권장.
@Component
public class TimeTraceAop {

    // twcha.h2project 패키지 하위 항목에 모두 적용
    // 원하는 관심사항 선택 가능. 클래스, 아규먼트... 등
    @Around("execution(* twcha.h2project..*(..))")
    public Object excute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
