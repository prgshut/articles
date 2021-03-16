package ru.test.newsgather.utils;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.test.newsgather.dto.ArticleDto;

import java.util.List;

@Data
@Component
@NoArgsConstructor
public class LoadArticle {
    private int limit;
    private int start;

    public LoadArticle(int limit, int start) {
        this.limit = limit;
        this.start = start;
    }

    public void retunLoadArticle(){
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("https://test.spaceflightnewsapi.net/api/v2/articles")
            .append("?_limit=").append(limit).append("&_start=").append(start);
    ArticleDto articleDtoList= restTemplate.getForObject(stringBuilder.toString(),ArticleDto.class);
        System.out.println();
    System.out.println(articleDtoList.toString());
    }

}
