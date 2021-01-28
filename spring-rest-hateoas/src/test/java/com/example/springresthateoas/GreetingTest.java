package com.example.springresthateoas;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
public class GreetingTest {
    @Test
    public void greetingtest() throws JsonProcessingException {
        Greeting greeting = new Greeting("User");
        String result = new ObjectMapper().writeValueAsString(greeting);
        assertThat(result, containsString("User"));
        assertThat(result, containsString("content"));
    }
}
