package ru.test.newsgather.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartThreads {
    private final int numberThreads;
    private final int totalDowRec;
    private final int oneThreadDowRec;

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
                    for (int j = downStartPosit; j < downStartPosit+sumTasks; j=j+oneThreadDowRec) {

                    }

                }
            });

        }
        service.shutdown();

    }
}
