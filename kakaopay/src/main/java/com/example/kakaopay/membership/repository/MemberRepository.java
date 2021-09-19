package com.example.kakaopay.membership.repository;

import com.example.kakaopay.membership.domain.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MemberRepository extends CrudRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
}
