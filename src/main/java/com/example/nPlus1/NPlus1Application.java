package com.example.nPlus1;

import com.example.nPlus1.dummy.DummyData;
import com.example.nPlus1.service.MemberService;
import com.example.nPlus1.service.TeamService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class NPlus1Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(NPlus1Application.class, args);

        MemberService memberService = context.getBean(MemberService.class);
        TeamService teamService = context.getBean(TeamService.class);

        DummyData dummyData = new DummyData(memberService, teamService);
        dummyData.createTeam();
        dummyData.createMember();
    }
}
