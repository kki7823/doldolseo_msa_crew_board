package com.doldolseo.doldolseo_msa_crew_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CrewPostDTO {
    private Long crewPostNo;
    private Long crewNo;
    private String writerId;
    private int category;
    private String title;
    private String content;
    private String imageUUID;
    private LocalDateTime wDate;
    private int hit;
}
