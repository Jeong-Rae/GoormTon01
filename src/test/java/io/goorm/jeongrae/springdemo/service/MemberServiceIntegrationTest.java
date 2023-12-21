package io.goorm.jeongrae.springdemo.service;

import io.goorm.jeongrae.springdemo.domain.Member;
import io.goorm.jeongrae.springdemo.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join() throws Exception {
        Member member = new Member(0L, "spring");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void duplicateMemberJoin() throws Exception {
        Member member1 = new Member(0L, "spring");
        Member member2 = new Member(0L, "spring");

        memberService.join(member1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> memberService.join(member2));

        assertThat(exception.getMessage()).isEqualTo("이미 존재하는 회원명");
    }

}