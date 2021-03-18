package ru.test.newsgather.controllers;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.test.newsgather.component.BadWords;
import ru.test.newsgather.component.StartThreads;

@Log4j2
@Controller
@RequestMapping("/start")
public class StartApp {
    private BadWords badWords;
    private StartThreads startThreads;
    @Autowired
    public void setBadWords(BadWords badWords) {
        this.badWords = badWords;
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
        if (numberThreads<1){
            numberThreads=1;
        }
        if (totalDowRec<1){
            totalDowRec=1;
        }
        if (oneThreadDowRec<1){
            oneThreadDowRec=1;
        }
        if (numArticlesOneSite<1){
            numArticlesOneSite=1;
        }
        log.info("Получили запрос на загрузку");
        startThreads.start(numberThreads,totalDowRec,oneThreadDowRec, numArticlesOneSite);
        return "redirect:/start";
    }

    @GetMapping("/badword")
    public String addBadWord(@RequestParam(name = "word") String word) {
        if (word!=null) {
            if (!word.trim().isEmpty()) {
                badWords.addBadWord(word.trim().toLowerCase());
            }
        }
        return "redirect:/start";
    }
}
