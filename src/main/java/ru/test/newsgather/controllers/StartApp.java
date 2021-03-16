package ru.test.newsgather.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.test.newsgather.utils.BadWords;


@Controller
@RequestMapping("/start")
public class StartApp {

    private BadWords badWords;
    @GetMapping
    public String start(){
        return "start";
    }
    @GetMapping("/run")
    public String runLoading(@RequestParam(defaultValue = "1", name = "th") Integer threads,
                             @RequestParam(defaultValue = "1", name = "tdr") Integer totalDowRec,
                             @RequestParam(defaultValue = "1", name = "otdr") Integer oneThreadDowRec){
        System.out.println(threads+" "+totalDowRec+" "+oneThreadDowRec);
//        LoadArticle loadArticle= new LoadArticle(threads,totalDowRec);
//        loadArticle.retunLoadArticle();
        return "redirect:/start";
    }
    @GetMapping("/badword")
    public String addBadWord(@RequestParam( name = "word") String word){
        if (!word.trim().isEmpty()){
            badWords.addBadWord(word.trim().toLowerCase());
        }
        return "redirect:/start";
    }
}
