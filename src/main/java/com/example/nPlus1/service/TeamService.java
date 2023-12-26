package com.example.nPlus1.service;

import com.example.nPlus1.domain.Team;
import com.example.nPlus1.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Long addTeam(String name) {
        Team newTeam = new Team(name);
        Team team = teamRepository.save(newTeam);
        return team.getId();
    }

    public Team findById(Long id) {
        return teamRepository.findById(id).orElseThrow();
    }
}
