package javac.concurrent.executor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.FutureTask;

public class FutureTaskDemo {

    public static void main(String args[]) {
        Queue<FutureTask> ftQueue = new LinkedList<>();
        for(int i = 1; i < 20; i++) {
            ftQueue.add(new FutureTask(new LoadDataTask(i)));
        }

        FutureTask ft = ftQueue.poll();
        while(!ftQueue.isEmpty()) {
            ft.run();
            while(ft.isDone()) {
                System.out.println("Task has been completed");
                ft = ftQueue.poll();
            }
        }

    }
}
