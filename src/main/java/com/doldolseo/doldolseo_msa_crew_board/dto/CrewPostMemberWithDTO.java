package com.doldolseo.doldolseo_msa_crew_board.dto;

import com.doldolseo.doldolseo_msa_crew_board.domain.CrewPost;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrewPostMemberWithDTO {
    private Long crewMemberWithNo;
    private CrewPost crewPost;
    private String memberId;
}
