package ru.test.newsgather.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.test.newsgather.utils.BadWords;
import ru.test.newsgather.utils.LoadArticle;
import ru.test.newsgather.utils.StartThreads;


@Controller
@RequestMapping("/start")
public class StartApp {
    @Autowired
    private BadWords badWords;

    @GetMapping
    public String start() {
        return "start";
    }

    @GetMapping("/run")
    public String runLoading(@RequestParam(defaultValue = "1", name = "th") Integer numberThreads,
                             @RequestParam(defaultValue = "1", name = "tdr") Integer totalDowRec,
                             @RequestParam(defaultValue = "1", name = "otdr") Integer oneThreadDowRec) {
        StartThreads startThreads = new StartThreads(numberThreads,totalDowRec,oneThreadDowRec);
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
