package com.vengeang.learnredis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vengeang.learnredis.entity.Tutorial;
import com.vengeang.learnredis.service.TutorialService;

@RestController
public class TutorialController {

    @Autowired
    private TutorialService tutorialService;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAll(){
        System.out.println("Get in controller");
        return ResponseEntity.ok(tutorialService.getAll());
    }
}
