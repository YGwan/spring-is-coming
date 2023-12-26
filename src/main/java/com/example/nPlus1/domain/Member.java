package com.example.nPlus1.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class Member {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String name;

    @ManyToOne
    private Team team;
}
