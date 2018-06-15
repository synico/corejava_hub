package javac.concurrent.forkjoin;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ProductMain {
    
    public static void main(String args[]) {
        ProductListGenerator generator = new ProductListGenerator();
        List<Product> products = generator.generate(10000);
        
        ProductHandleTask phTask = new ProductHandleTask(products, 0, products.size(), 0.2);
        
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(phTask);
        
        do {
            System.out.println("Main: Thread Count: " + pool.getActiveThreadCount());
            System.out.println("Main: Thread Steal: " + pool.getStealCount());
            System.out.println("Main: Parallelism: " + pool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!phTask.isDone());
        
        if(!phTask.isDone()) {
            pool.shutdown();
            System.out.println("Main: Pool closing");
        }
    }

}
