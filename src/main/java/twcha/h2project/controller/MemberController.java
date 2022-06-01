package twcha.h2project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import twcha.h2project.domain.Member;
import twcha.h2project.service.MemberService;

import java.util.List;

/*
Spring bean 자동 등록 후 의존성 주입
@Controller, @Service, @Repository
@Autowired로 등록되어 있는 bean에 대하여 의존성 주입
이때, 의존성 주입 방법은 생성자, setter, 필드가 있는데, 생성자가 일반적이다.
*/
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

/*
* Mapping은 Method별로 수행됨
* Get : 보통 Url을 통해 진입했을때
* Post : 데이터 입력하여 보낼때
* */
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

/*
* Model을 이용하여 html로 데이터를 보내줄수 있다.
* addAttribute("tag", variable)
* */
    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
