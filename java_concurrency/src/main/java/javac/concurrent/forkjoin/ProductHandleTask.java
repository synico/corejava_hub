package javac.concurrent.forkjoin;

import java.util.List;
import java.util.concurrent.RecursiveAction;

public class ProductHandleTask extends RecursiveAction {
    
    private static final long serialVersionUID = 1L;

    private List<Product> products;
    
    private int first;
    private int last;
    
    private double increment;
    
    public ProductHandleTask(List<Product> products, int first, int last, double increment) {
        this.products = products;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if(last - first < 10) {
            updatePrices();
        } else {
            int middle = (last + first) / 2;
            System.out.println("Task: Pending task: " + super.getQueuedTaskCount());
            ProductHandleTask t1 = new ProductHandleTask(products, first, middle + 1, increment);
            ProductHandleTask t2 = new ProductHandleTask(products, middle + 1, last + 1, increment);
            
            invokeAll(t1, t2);
        }
    }
    
    private void updatePrices() {
        Product product = null;
        for(int i = first; i < last; i++) {
            product = products.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }

}
