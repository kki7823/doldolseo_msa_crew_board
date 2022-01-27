package com.doldolseo.doldolseo_msa_crew_board.controller;

import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostAndMembersWithDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostDTO;
import com.doldolseo.doldolseo_msa_crew_board.dto.CrewPostPageDTO;
import com.doldolseo.doldolseo_msa_crew_board.service.CrewPostService;
import com.doldolseo.doldolseo_msa_crew_board.utils.UploadCrewPostFileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class CrewPostController {
    @Autowired
    CrewPostService service;
    @Autowired
    UploadCrewPostFileUtil fileUtil;

    @GetMapping(value = "/crew/post")
    public ResponseEntity<CrewPostPageDTO> getCrewPostPage(CrewPostDTO dtoIn,
                                                           @PageableDefault(size = 30, sort = "wDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.getCrewPostPage(dtoIn, pageable));
    }

    @GetMapping(value = "/crew/post/{crewPostNo}")
    public ResponseEntity<CrewPostAndMembersWithDTO> getPost(@PathVariable(value = "crewPostNo") Long crewPostNo,
                                                             @RequestParam(required = false) String doHit) throws Exception {
        if (doHit.equals("yes"))
            return ResponseEntity.status(HttpStatus.OK).body(service.getCrewPostAndMembersWithAndHit(crewPostNo));
        else
            return ResponseEntity.status(HttpStatus.OK).body(service.getCrewPostAndMembersWith(crewPostNo));
    }

    @PutMapping(value = "/crew/post/{crewPostNo}")
    public ResponseEntity<?> updatePost(CrewPostDTO dtoIn,
                                        @PathVariable(value = "crewPostNo") Long crewPostNo) throws Exception {
        service.updateCrewPost(dtoIn, crewPostNo);
        return ResponseEntity.status(HttpStatus.OK).body("크루 게시글 수정 완료");
    }

    @DeleteMapping(value = "/crew/post/{crewPostNo}")
    public ResponseEntity<?> deletePost(@PathVariable(value = "crewPostNo") Long crewPostNo) throws Exception {
        service.deleteCrewPost(crewPostNo);
        return ResponseEntity.status(HttpStatus.OK).body("크루 게시글 삭제 완료");
    }

    @PostMapping(value = "/crew/post")
    public ResponseEntity<CrewPostDTO> createPost(CrewPostDTO dtoIn, String membersWith) throws Exception {
        service.saveCrewPost(dtoIn, membersWith);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping(value = "/crew/post/images/{imageUUID}")
    public ResponseEntity<String> insertCrewPostImage(@PathVariable("imageUUID") String uuid,
                                                      @RequestParam MultipartFile imgFile) {
        return ResponseEntity.status(HttpStatus.OK).body(fileUtil.saveCrewPostImg(uuid, imgFile));
    }

    @ResponseBody
    @GetMapping(value = "/crew/post/images/{imageUUID}/{imageFileName}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE})
    public byte[] getCrewImage(@PathVariable("imageUUID") String uuid,
                               @PathVariable("imageFileName") String imageFileName,
                               HttpServletRequest request) throws IOException, FileSizeLimitExceededException {

        String imgPath = System.getProperty("user.dir") + "/src/main/resources/static/crew_post_image/" + uuid + "/" + imageFileName;
        InputStream in = new FileInputStream(imgPath);
        byte[] imageByteArr = IOUtils.toByteArray(in);
        in.close();
        return imageByteArr;
    }

    @PostMapping(value = "/crew/post/{crewPostNo}/member")
    public ResponseEntity<?> addMemberToPost(@PathVariable(value = "crewPostNo") Long crewPostNo,
                                             @RequestParam String memberId) {
        service.saveMembers(crewPostNo, memberId);
        return ResponseEntity.status(HttpStatus.OK).body("member added");
    }

    @DeleteMapping(value = "/crew/post/{crewPostNo}/member")
    public ResponseEntity<?> deleteMemberInPost(@PathVariable(value = "crewPostNo") Long crewPostNo,
                                                @RequestParam(value = "membersWithNo") Long membersWithNo) {
        service.deleteMemberInPost(membersWithNo);
        return ResponseEntity.status(HttpStatus.OK).body("member deleted");
    }
}
