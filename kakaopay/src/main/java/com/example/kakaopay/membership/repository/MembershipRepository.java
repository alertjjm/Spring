package com.example.kakaopay.membership.repository;

import com.example.kakaopay.membership.domain.Membership;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface MembershipRepository extends CrudRepository<Membership, Long> {
    Optional<Membership> findByMembershipId(String membershipId);
}
