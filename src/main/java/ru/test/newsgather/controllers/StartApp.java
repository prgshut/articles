package ru.test.newsgather.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/start")
public class StartApp {

    @GetMapping
    public void startLoading(@RequestParam(defaultValue = "1", name = "th") Integer threads,
                             @RequestParam(defaultValue = "1", name = "tdr") Integer totalDowRec,
                             @RequestParam(defaultValue = "1", name = "otdr") Integer oneThreadDowRec){
        System.out.println(threads+" "+totalDowRec+" "+oneThreadDowRec);

    }
}
