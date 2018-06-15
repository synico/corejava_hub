package javac.concurrent.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NewCachedThreadPoolDemo {

    private ExecutorService exec = Executors.newCachedThreadPool();

    public void runBatchJobs() {
        List<LoadDataTask> jobs = new ArrayList<LoadDataTask>();
        for (int i = 0; i < 100; i++) {
            jobs.add(new LoadDataTask(i));
        }
        List<Future<String>> ret;
        try {
            ret = exec.invokeAll(jobs);
            for (Future<String> job : ret) {
                if (job.isDone()) {
                    System.out.println(job.get());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        exec.shutdown();
    }

    public static void main(String[] args) {
        new NewCachedThreadPoolDemo().runBatchJobs();
    }

}
