package com.vengeang.learnredis.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements Serializable {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer limitToken;

    private String apiKey;

    public User() {
    }


    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getLimitToken() {
        return limitToken;
    }


    public void setLimitToken(Integer limitToken) {
        this.limitToken = limitToken;
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", limitToken=" + limitToken + ", apiKey=" + apiKey + "]";
    }

    
}
