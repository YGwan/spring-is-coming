package com.example.springBsc;

import com.example.springBsc.repository.MemberRepository;
import com.example.springBsc.repository.MemoryMemberRepository;
import com.example.springBsc.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
