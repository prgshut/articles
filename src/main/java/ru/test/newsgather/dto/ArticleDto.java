package ru.test.newsgather.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.test.newsgather.entities.Article;

import java.time.OffsetDateTime;
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDto {
    private String id;
    private String title;
    private String url;
    private String newsSite;
    private String summary;
    private OffsetDateTime publishedAt;

    public ArticleDto(Article a) {
        this.id = a.getId();
        this.title = a.getTitle();
        this.newsSite = a.getNewsSite();
        this.summary = a.getArticle();
        this.publishedAt = a.getPublishedDate();
    }
}
