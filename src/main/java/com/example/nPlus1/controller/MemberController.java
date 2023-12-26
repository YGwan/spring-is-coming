package com.example.nPlus1.controller;

import com.example.nPlus1.domain.Member;
import com.example.nPlus1.dto.MemberResponse;
import com.example.nPlus1.repository.MemberRepository;
import com.example.nPlus1.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;

    public MemberController(MemberRepository memberRepository, MemberService memberService) {
        this.memberRepository = memberRepository;
        this.memberService = memberService;
    }

    @GetMapping("members")
    public List<MemberResponse> memberList() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberResponse::of).collect(Collectors.toList());
    }

    @GetMapping("members/fj")
    public List<MemberResponse> memberListUsedFetchJoin() {
        List<Member> members = memberRepository.findALlUsedFetchJoin();
        return members.stream().map(MemberResponse::of).collect(Collectors.toList());
    }

    @GetMapping("members/teamName")
    public List<String> membersTeamName() {
        List<Member> members = memberRepository.findAll();
        return memberService.getMembersTeamName(members);
    }

    @GetMapping("members/teamName/fj")
    public List<String> membersTeamNameUsedFetchJoin() {
        List<Member> members = memberRepository.findALlUsedFetchJoin();
        return memberService.getMembersTeamName(members);
    }
}
