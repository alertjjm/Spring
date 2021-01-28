package com.example.springresthateoas;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;

public class Greeting extends RepresentationModel<Greeting> {
    @JsonProperty("content")
    private final String content;
    @JsonCreator
    public Greeting(String content){
        this.content=content;
    }
    public String getContent(){
        return this.content;
    }
}
