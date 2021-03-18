package ru.test.newsgather.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.test.newsgather.entities.Article;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {
    List<Article> findArticlesByNewsSiteEquals(String newsSite);

}
