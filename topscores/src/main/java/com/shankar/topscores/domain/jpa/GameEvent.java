package com.shankar.topscores.domain.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;


public class GameEvent {

    @JsonProperty("name")
    String name;

    @JsonProperty("score")
    Integer score;

    public GameEvent(){};

    public GameEvent(String name,Integer score){
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getScore() { return score; }

    public void setScore(Integer score) { this.score = score; }

}
