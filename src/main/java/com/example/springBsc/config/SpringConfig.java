package com.example.springBsc.config;

import com.example.springBsc.repository.JdbcMemberRepository;
import com.example.springBsc.repository.MemberRepository;
import com.example.springBsc.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource datasource;

    public SpringConfig(DataSource datasource) {
        this.datasource = datasource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(datasource);
    }
}
