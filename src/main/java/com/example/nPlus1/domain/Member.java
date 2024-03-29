package com.example.nPlus1.domain;

import lombok.Getter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;

@BatchSize(size = 100)
@Getter
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Team team;

    public Member(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    protected Member() {
    }
}
