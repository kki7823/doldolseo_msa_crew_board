package com.doldolseo.doldolseo_msa_crew_board.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "CREW_BOARD_COMMENT_TBL")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
@ToString
public class CrewPostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENT_NO")
    private Long commentNo;

    @ManyToOne
    @JoinColumn(name = "CREW_POST_NO")
    private CrewPost crewPost;

    private String id;
    private String content;

    @Column(name = "W_DATE")
    private LocalDateTime wDate;
}
