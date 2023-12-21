package io.goorm.jeongrae.springdemo.repository;

import io.goorm.jeongrae.springdemo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
        public Member save(Member member);

        public Optional<Member> findById(Long id);

        public Optional<Member> findByName(String name);

        public List<Member> findAll();

}
