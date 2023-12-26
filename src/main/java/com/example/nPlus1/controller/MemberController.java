package com.example.nPlus1.controller;

import com.example.nPlus1.domain.Member;
import com.example.nPlus1.dto.MemberResponse;
import com.example.nPlus1.repository.MemberRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MemberController {

    private final MemberRepository memberRepository;

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("members")
    public List<MemberResponse> memberList() {
        List<Member> members = memberRepository.findAll();
        return members.stream().map(MemberResponse::of).collect(Collectors.toList());
    }
}
