package ru.test.newsgather.utils;

import lombok.Data;
import org.springframework.web.client.RestTemplate;

@Data
public class LoadArticle {
    private int limit;
    private int start;

public void retunLoadArticle(){
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.get
    }

}
