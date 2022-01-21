package com.doldolseo.doldolseo_msa_crew_board.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CREW_BOARD_MEMBERWITH_TBL")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CrewMemberWith {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CREW_MEMBERWITH_NO")
    private Long crewMemberWithNo;

    @ManyToOne
    @JoinColumn(name = "CREW_POST_NO")
    private CrewPost crewPost;

    @Column(name = "MEMBER_ID")
    private String memberId;
}
