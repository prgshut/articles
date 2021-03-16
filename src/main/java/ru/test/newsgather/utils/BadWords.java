package ru.test.newsgather.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class BadWords {
    List<String>
    private CopyOnWriteArraySet<String> cowSet;

    @PostConstruct
   private void init(){
       this.cowSet= new CopyOnWriteArraySet<String>();
   }

    public void addBadWord(String word){
        cowSet.add(word);
    }

    public boolean contains(String word){
        return cowSet.contains(word);
    }

}
