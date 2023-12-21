package io.goorm.jeongrae.springdemo.controller;

import io.goorm.jeongrae.springdemo.controller.form.MemberForm;
import io.goorm.jeongrae.springdemo.domain.Member;
import io.goorm.jeongrae.springdemo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    private String create(MemberForm form){
        Member member = new Member(form.getName() );


        try {
            memberService.join(member);
        }
        catch (IllegalArgumentException exception){
            return "redirect:/";
        }

        return "redirect:/";
    }

    @GetMapping("members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);

        return "members/memberList";
    }


}
