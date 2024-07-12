package com.vengeang.learnredis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vengeang.learnredis.entity.Tutorial;
import com.vengeang.learnredis.repository.TutorialRepository;

@Service
public class TutorialService {
  @Autowired
  private TutorialRepository tutorialRepository;

  @Cacheable("tutorails")
  public List<Tutorial> getAll() {
    System.out.println("get in service");
    doLongRunningTask();
    return tutorialRepository.findAll();
  }

  private void doLongRunningTask() {
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
