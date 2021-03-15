package ru.test.newsgather.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.test.newsgather.repositories.ArticleRepository;

@Service
@AllArgsConstructor
public class ArticleService {
    private ArticleRepository articleRepository;

}
