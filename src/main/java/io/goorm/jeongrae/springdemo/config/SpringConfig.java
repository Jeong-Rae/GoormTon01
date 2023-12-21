package io.goorm.jeongrae.springdemo.config;

import io.goorm.jeongrae.springdemo.repository.MemberRepository;
import io.goorm.jeongrae.springdemo.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    private final MemberRepository memberRepository;
    

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

}
