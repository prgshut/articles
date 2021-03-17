package ru.test.newsgather.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Log4j2
@Component
public class BuffArticles {
    private ConcurrentHashMap<String, List<ArticleDto>> map = new ConcurrentHashMap<>();
    private int numArticlesOneSite;
    private ArticleService articleService;
    private Lock lock = new ReentrantLock();
    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void setNumArticlesOneSite(int numArticlesOneSite) {
        this.numArticlesOneSite = numArticlesOneSite;
    }

    public void addArticle(String key, List<ArticleDto> list){
        lock.lock();
        if (map.containsKey(key)){
            map.get(key).addAll(list);
        }else{
            map.put(key, list);
        }
        if (map.get(key).size()>=numArticlesOneSite){
            articleService.save(key, map.get(key));
            map.remove(key);
        }
        lock.unlock();
    }

    public void saveBuffer(){
        for (Map.Entry<String, List<ArticleDto>> entry : map.entrySet()) {
            articleService.save(entry.getKey(),entry.getValue());
        }
        map.clear();
    }

    private void removeArticles(String key){
        map.remove(key);
    }


}
