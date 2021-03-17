package ru.test.newsgather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.OffsetDateTime;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    private String id;
    private boolean featured;
    private String title;
    private String url;
    private String imageUrl;
    private String newsSite;
    private String summary;
    private OffsetDateTime publishedAt;

}
