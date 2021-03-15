package ru.test.newsgather.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articles")
@Data
@NoArgsConstructor
public class Article {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "title")
    private String title;

    @Column(name = "news_site")
    private  String newsSite;

    @Column(name = "published_date")
    private String publishedDate;

    @Column(name = "article")
    private String article;

    public Article(String id, String title, String newsSite, String publishedDate, String article) {
        this.id = id;
        this.title = title;
        this.newsSite = newsSite;
        this.publishedDate = publishedDate;
        this.article = article;
    }
}
