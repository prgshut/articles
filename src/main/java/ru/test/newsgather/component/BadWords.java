package ru.test.newsgather.component;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class BadWords {

    private CopyOnWriteArraySet<String> cowSet;

    @PostConstruct
    private void init() {
        this.cowSet = new CopyOnWriteArraySet<String>();
        System.out.println(cowSet);
    }

    public void addBadWord(String word) {
        cowSet.add(word);
    }

    public boolean contains(String word) {
        return cowSet.contains(word);
    }

}
