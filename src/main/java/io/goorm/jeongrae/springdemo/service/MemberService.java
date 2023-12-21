package io.goorm.jeongrae.springdemo.service;

import io.goorm.jeongrae.springdemo.domain.Member;
import io.goorm.jeongrae.springdemo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);

        Member response = memberRepository.save(member);

        log.info("[join] {} 신규 회원가입", member.getName());

        return response.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalArgumentException("이미 존재하는 회원명");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        List<Member> response = memberRepository.findAll();

        log.info("[findMembers] 전체 회원 조회");

        return response;
    }

    /**
     * 단일 회원 조회
     */
    public Member findMemberOne(Long memberId){
        Member response = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 id"));

        log.info("[findMemberOne] {} 회원 조회", memberId);

        return response;
    }
}
