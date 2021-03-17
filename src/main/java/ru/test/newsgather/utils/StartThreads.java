package ru.test.newsgather.utils;

import org.springframework.beans.factory.annotation.Autowired;
import ru.test.newsgather.dto.ArticleDto;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartThreads {
    private final int numberThreads;
    private final int totalDowRec;
    private final int oneThreadDowRec;
    @Autowired
    private BadWords badWords;
    @Autowired
    private BuffArticles buffArticles;
    public StartThreads(int numberThreads, int totalDowRec, int oneThreadDowRec) {
        this.numberThreads = numberThreads;
        this.totalDowRec = totalDowRec;
        this.oneThreadDowRec = oneThreadDowRec;

    }

    private void start(){
        ExecutorService service= Executors.newFixedThreadPool(numberThreads);
        int sumTasks= totalDowRec/numberThreads; // сколько записей должен считать каждый поток
        for (int i = 1; i <= totalDowRec; i=i+sumTasks) {
            final int downStartPosit=i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    List<ArticleDto> list = new LinkedList<>();
                    for (int j = downStartPosit; j < downStartPosit+sumTasks; j=j+oneThreadDowRec) {
                        list.addAll(Arrays.asList(LoadArticle.retunLoadArticle(oneThreadDowRec,j)));
                    }
                    removeBadArticle(list);
                    Collections.sort(list, new Comparator<ArticleDto>() {
                        @Override
                        public int compare(ArticleDto o1, ArticleDto o2) {
                            return o1.getPublishedAt().compareTo(o2.getPublishedAt());
                        }

                    });
                    Map<String ,List<ArticleDto>> map = new HashMap<>();
                    for (ArticleDto a : list) {
                        String[] temp = a.getUrl().split("/");
                        map.put(temp[2],new LinkedList<ArticleDto>().add(a))
                    }

                }
            });

        }
        service.shutdown();

    }

    private void removeBadArticle(List<ArticleDto> list) {
        for (ArticleDto a : list) {
            String[] title = a.getTitle().trim().toLowerCase().split(" ");
            for (String t : title) {
                if (badWords.contains(t)){
                    list.remove(a);
                }
            }
        }
    }


}
