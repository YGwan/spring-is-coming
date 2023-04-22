package com.example.springBsc.controller;

import com.example.springBsc.domain.Member;
import com.example.springBsc.dto.JoinRequest;
import com.example.springBsc.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    private final MemberService memberService;

    public HomeController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/members/new")
    public String join() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String createMember(JoinRequest request) {
        Member member = new Member();
        member.setName(request.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String memberList(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "/members/memberList";
    }
}
