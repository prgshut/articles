package ru.test.newsgather.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.service.ArticleService;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class BuffArticles {
    private ConcurrentHashMap<String, List<ArticleDto>> map = new ConcurrentHashMap<>();
    @Autowired
    private ArticleService articleService;

    public void addArticle(String key, List<ArticleDto> list){
        if (map.containsKey(key)){
            map.get(key).addAll(list);
        }else {
            map.put(key, list);
        }
    }

    private void removeArticles(String key){
        map.remove(key);
    }


}
