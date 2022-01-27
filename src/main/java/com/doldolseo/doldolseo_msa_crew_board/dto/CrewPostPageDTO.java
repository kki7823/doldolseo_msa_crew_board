package com.doldolseo.doldolseo_msa_crew_board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CrewPostPageDTO {
    private List<CrewPostDTO> crewPostList;
    private int startBlockPage;
    private int endBlockPage;
    private int totalPages;
}
