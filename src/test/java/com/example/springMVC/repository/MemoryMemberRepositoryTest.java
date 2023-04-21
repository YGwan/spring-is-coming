package com.example.springMVC.repository;

import com.example.springBsc.domain.Member;
import com.example.springBsc.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();
    Member member1;
    Member member2;

    @BeforeEach
    public void beforeEach() {
        member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);
    }

    @AfterEach
    public void afterEach() {
        repository.clear();
    }

    @Test
    public void save() {
        Member result = repository.findById(member1.getId()).get();
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
        assertThat(result).isNotEqualTo(member2);
    }

    @Test
    public void findAll() {
        List<Member> members = repository.findAll();
        assertThat(members.size()).isEqualTo(2);
    }
}
