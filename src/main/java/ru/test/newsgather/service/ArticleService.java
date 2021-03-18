package ru.test.newsgather.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.entities.Article;
import ru.test.newsgather.repositories.ArticleRepository;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAllArticles(){
        return articleRepository.findAll();
    }
    public Optional<Article> getOneId(String id){
        return articleRepository.findById(id);
    }
    public List<Article> getArticlesNewsSite(String newSite){
        return articleRepository.findArticlesByNewsSiteEquals(newSite);
    }

    public void save(List<ArticleDto> articleDtoList){
        for (ArticleDto o : articleDtoList) {
            Article article =new Article(o.getId(),o.getTitle(),o.getNewsSite(),o.getPublishedAt(),o.getSummary());
            articleRepository.save(article);
            log.info("save entities in base repository");
            log.info("articleDto " + o);
            log.info("article "+article);
        }
    }
}
