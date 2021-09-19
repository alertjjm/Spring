package com.example.kakaopay.membership.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long seq;

    @Column(nullable = false)
    private String membershipId;

    @Setter
    @Column(nullable = false, name = "MEMBER_ID")
    private Long userId;

    @Column(nullable = false)
    private String membershipName;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Setter
    @Column(nullable = false)
    private String membershipStatus;

    @Setter
    @Column(name="points")
    private int point;

    @Builder
    public Membership(Long seq, String membershipId, Long userId, String membershipName, LocalDateTime startDate, String membershipStatus, int point){
        this.seq=seq;
        this.membershipId=membershipId;
        this.membershipName=membershipName;
        this.userId=userId;
        this.startDate=startDate;
        this.membershipStatus=membershipStatus;
        this.point=point;
    }
}
