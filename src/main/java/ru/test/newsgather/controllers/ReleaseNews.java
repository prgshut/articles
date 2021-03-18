package ru.test.newsgather.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.entities.Article;
import ru.test.newsgather.exceptions.ResourceNotFoundException;
import ru.test.newsgather.service.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Controller
@RequestMapping("/request")
@AllArgsConstructor
public class ReleaseNews {
    private ArticleService articleService;

    @GetMapping("/all")
    @ResponseBody
    public List<ArticleDto> getOneArticle(){
        return articleService.getAllArticles().stream().map(ArticleDto::new).collect(Collectors.toList());
    }

    @GetMapping("/id/{id}")
    @ResponseBody
    public Article getOneArticle(@PathVariable String id){
            return articleService.getOneId(id).orElseThrow(() -> new ResourceNotFoundException("Unable to find article with id: " + id));
    }

    @GetMapping("/newssite/{newsSite}")
    @ResponseBody
    public List<Article> getArticleNewsSite(@PathVariable String newsSite){
        return articleService.getArticlesNewsSite(newsSite);
    }

}
