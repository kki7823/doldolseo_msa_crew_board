package com.doldolseo.doldolseo_msa_crew_board.service;

import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostCommentDTO;

import java.util.List;

public interface CrewPostCommentService {
    List<CrewPostCommentDTO> getComments(Long crewPostNo);

    CrewPostCommentDTO insertComment(CrewPostCommentDTO dto);

    void updateComment(Long commentNo, CrewPostCommentDTO dto);

    void deleteComment(Long commentNo);
}
