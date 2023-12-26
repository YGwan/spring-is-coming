package com.example.nPlus1.dto;

import com.example.nPlus1.domain.Member;
import com.example.nPlus1.domain.Team;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class MemberResponse {

    private Long id;

    private String name;

    @JsonIgnore
    private Team team;

    public MemberResponse() {
    }

    public MemberResponse(Long id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public static MemberResponse of(Member member) {
        return new MemberResponse(member.getId(), member.getName(), member.getTeam());
    }
}
