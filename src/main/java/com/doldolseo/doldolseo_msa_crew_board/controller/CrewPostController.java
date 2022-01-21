package com.doldolseo.doldolseo_msa_crew_board.controller;

import com.doldolseo.doldolseo_msa_crew_board.service.CrewPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CrewPostController {
    @Autowired
    CrewPostService crewPostService;
}
