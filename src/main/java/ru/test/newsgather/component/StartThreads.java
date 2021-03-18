package ru.test.newsgather.component;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.test.newsgather.component.BadWords;
import ru.test.newsgather.component.BuffArticles;
import ru.test.newsgather.dto.ArticleDto;
import ru.test.newsgather.utils.LoadArticle;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Component
@NoArgsConstructor
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StartThreads {

    private BadWords badWords;
    private BuffArticles buffArticles;

    @Autowired
    public void setBadWords(BadWords badWords) {
        this.badWords = badWords;
    }

    @Autowired
    public void setBuffArticles(BuffArticles buffArticles) {
        this.buffArticles = buffArticles;
    }

    public void start(int numberThreads, int totalDowRec, int oneThreadDowRec, int numArticlesOneSite) {
        buffArticles.setNumArticlesOneSite(numArticlesOneSite);
        ExecutorService service = Executors.newFixedThreadPool(numberThreads);
        log.info("Создали потоки " + numberThreads);
        int sumTasks = totalDowRec / numberThreads; // сколько записей должен считать каждый поток
        if (sumTasks == 0) {
            sumTasks = 1;
        }
        if (totalDowRec <= oneThreadDowRec) {
            sumTasks = totalDowRec;
        }
        final int taskSum = sumTasks;
        for (int i = 1; i <= totalDowRec; i = i + taskSum) {
            final int downStartPosit = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    List<ArticleDto> list = new LinkedList<>();
                    for (int j = downStartPosit; j < downStartPosit + taskSum; j = j + oneThreadDowRec) {
                        list.addAll(Arrays.asList(LoadArticle.retunLoadArticle(oneThreadDowRec, j)));
                    }
                    removeBadArticle(list);
                    list.sort(new Comparator<ArticleDto>() {
                        @Override
                        public int compare(ArticleDto o1, ArticleDto o2) {
                            return o1.getPublishedAt().compareTo(o2.getPublishedAt());
                        }

                    });
                    Map<String, List<ArticleDto>> map = new HashMap<>();
                    for (ArticleDto a : list) {
                         map.computeIfAbsent(a.getNewsSite(), k -> new LinkedList<>()).add(a);
                    }
                    for (Map.Entry<String, List<ArticleDto>> entry : map.entrySet()) {
                        buffArticles.addArticle(entry.getKey(), entry.getValue());
                    }
                    log.info("Поток отправил все в буфер ");

                }
            });

        }
        service.shutdown();
        buffArticles.saveBuffer();
    }

    private void removeBadArticle(List<ArticleDto> list) {
        for (ArticleDto a : list) {
            String[] title = a.getTitle().trim().toLowerCase().split(" ");
            for (String t : title) {
                if (badWords.contains(t)) {
                    list.remove(a);
                }
            }
        }
    }


}
