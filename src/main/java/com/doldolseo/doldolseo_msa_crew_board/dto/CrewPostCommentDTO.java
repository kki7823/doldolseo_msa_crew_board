package com.doldolseo.doldolseo_msa_crew_board.dto;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CrewPostCommentDTO {
    private Long commentNo;
    private CrewPost crewPost;
    private String id;
    private String content;
    private LocalDateTime wDate;
}
