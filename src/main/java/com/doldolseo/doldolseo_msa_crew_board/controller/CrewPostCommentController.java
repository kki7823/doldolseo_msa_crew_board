package com.doldolseo.doldolseo_msa_crew_board.controller;

import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostCommentDTO;
import com.doldolseo.doldolseo_msa_crew_board.service.CrewPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CrewPostCommentController {
    @Autowired
    CrewPostCommentService service;

    @GetMapping(value = "/crew/post/{crewPostNo}/comment")
    public ResponseEntity<List<CrewPostCommentDTO>> getCrewPostComment(@PathVariable("crewPostNo") Long crewPostNo) {
        return ResponseEntity.ok(service.getComments(crewPostNo));
    }

    @PostMapping("/crew/post/{crewPostNo}/comment")
    public ResponseEntity<String> insertCrewPostComment(@PathVariable("crewPostNo") Long crewPostNo,
                                                      @RequestBody CrewPostCommentDTO dto) {
        service.insertComment(dto);
        return ResponseEntity.ok("댓글 삽입 완료");
    }

    @DeleteMapping("/crew/post/{crewPostNo}/comment/{commentNo}")
    public void deleteCrewPostComment(@PathVariable("crewPostNo") Long crewPostNo,
                                    @PathVariable("commentNo") Long commentNo) {
        service.deleteComment(commentNo);
        System.out.println(commentNo + "댓글 삭제 완료");
    }

    @PutMapping("/crew/post/{crewPostNo}/comment/{commentNo}")
    public void putCrewPostComment(@PathVariable("crewPostNo") Long crewPostNo,
                                 @PathVariable("commentNo") Long commentNo,
                                 @RequestBody CrewPostCommentDTO dto) {

        service.updateComment(commentNo, dto);
        System.out.println(commentNo + "번 댓글 수정 완료");
    }

}
