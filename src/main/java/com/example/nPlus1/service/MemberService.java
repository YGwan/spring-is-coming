package com.example.nPlus1.service;

import com.example.nPlus1.domain.Member;
import com.example.nPlus1.domain.Team;
import com.example.nPlus1.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long addMember(String name, Team team) {
        Member newMember = new Member(name, team);
        Member member = memberRepository.save(newMember);
        return member.getId();
    }

    public List<String> getMembersTeamName(List<Member> members) {
        List<String> membersTeamName =  new LinkedList<>();

        for (Member member : members) {
            Team team = member.getTeam();
            membersTeamName.add(team.getName());
        }

        return membersTeamName;
    }
}
