package ru.test.newsgather.utils;


import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import ru.test.newsgather.dto.ArticleDto;



public class LoadArticle {

    public static ArticleDto[] retunLoadArticle(int limit, int start) {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier()).build();

        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://test.spaceflightnewsapi.net/api/v2/articles")
                .append("?_limit=").append(limit).append("&_start=").append(start);
        ArticleDto[] articleDtoArr = restTemplate.getForObject(stringBuilder.toString(), ArticleDto[].class);
        return articleDtoArr;

    }

}
