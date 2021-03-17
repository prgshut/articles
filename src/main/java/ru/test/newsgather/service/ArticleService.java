package ru.test.newsgather.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.entities.Article;
import ru.test.newsgather.repositories.ArticleRepository;

import java.util.List;

@Log4j2
@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void save(String site, List<ArticleDto> articleDtoList){
        for (ArticleDto articleDto : articleDtoList) {
            Article article =new Article();
            article.setId(articleDto.getId());
            article.setTitle(site);
            article.setNewsSite(articleDto.getNewsSite());
            article.setPublishedDate(articleDto.getPublishedAt());
            article.setArticle(article.getArticle());
            articleRepository.save(article);
            log.info("save entities in base repository");
            log.info("articleDto " + articleDto);
            log.info("article "+article);
        }
    }
}
