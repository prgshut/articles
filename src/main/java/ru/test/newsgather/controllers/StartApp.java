package ru.test.newsgather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.test.newsgather.component.BadWords;
import ru.test.newsgather.component.BuffArticles;
import ru.test.newsgather.component.StartThreads;


@Controller
@RequestMapping("/start")
public class StartApp {
    private BadWords badWords;
    private BuffArticles buffArticles;
    private StartThreads startThreads;
    @Autowired
    public void setBadWords(BadWords badWords) {
        this.badWords = badWords;
    }

    @Autowired
    public void setBuffArticles(BuffArticles buffArticles) {
        this.buffArticles = buffArticles;
    }

    @Autowired
    public void setStartThreads(StartThreads startThreads) {
        this.startThreads = startThreads;
    }

    @GetMapping
    public String start() {
        return "start";
    }

    @GetMapping("/run")
    public String runLoading(@RequestParam(defaultValue = "1", name = "th") Integer numberThreads,
                             @RequestParam(defaultValue = "1", name = "tdr") Integer totalDowRec,
                             @RequestParam(defaultValue = "1", name = "otdr") Integer oneThreadDowRec,
                             @RequestParam(defaultValue = "1", name = "numart") Integer numArticlesOneSite) {
        buffArticles.setNumArticlesOneSite(numArticlesOneSite);

        System.out.println("получили задание");
        startThreads.start(numberThreads,totalDowRec,oneThreadDowRec);
        return "redirect:/start";
    }

    @GetMapping("/badword")
    public String addBadWord(@RequestParam(name = "word") String word) {
        if (!word.trim().isEmpty()) {
            badWords.addBadWord(word.trim().toLowerCase());
        }
        return "redirect:/start";
    }
}
