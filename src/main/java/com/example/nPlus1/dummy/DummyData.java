package com.example.nPlus1.dummy;

import com.example.nPlus1.domain.Team;
import com.example.nPlus1.service.MemberService;
import com.example.nPlus1.service.TeamService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class DummyData {

    private final MemberService memberService;

    private final TeamService teamService;

    public DummyData(MemberService memberService, TeamService teamService) {
        this.memberService = memberService;
        this.teamService = teamService;
    }

    public void createTeam() {
        teamService.addTeam("team1");
        teamService.addTeam("team2");
        teamService.addTeam("team3");
        teamService.addTeam("team4");
        teamService.addTeam("team5");
    }

    public void createMember() {
        Team team1 = teamService.findById(1L);
        Team team2 = teamService.findById(2L);
        Team team3 = teamService.findById(3L);

        memberService.addMember("member1", team1);
        memberService.addMember("member2", team2);
        memberService.addMember("member3", team3);
    }
}
