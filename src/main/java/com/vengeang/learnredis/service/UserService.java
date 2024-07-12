package com.vengeang.learnredis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.vengeang.learnredis.entity.User;
import com.vengeang.learnredis.exception.UserNotFoundException;
import com.vengeang.learnredis.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Cacheable(value = "userList",key = "#apiKey")
    public User getUser(String apiKey){
        doLongRunningTask();
        return userRepository.findByApiKey(apiKey).orElseThrow(
            ()->new UserNotFoundException("User not found")
        );
    }
    @CacheEvict(value = "userList",allEntries = true)
    @Scheduled(fixedDelayString = "${caching.spring.userListTTL}",initialDelay = 50000)
    public void deleteUserList(){
        System.out.println("Evict user list");
    }
    private void doLongRunningTask() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
}
