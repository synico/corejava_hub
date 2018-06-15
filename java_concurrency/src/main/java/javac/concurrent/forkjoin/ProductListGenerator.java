package javac.concurrent.forkjoin;

import java.util.List;
import java.util.ArrayList;

public class ProductListGenerator {
    
    private List<Product> ret = new ArrayList<Product>();
    
    public List<Product> generate(int size) {
        Product pd = null;
        for(int i = 0; i < size; i++) {
            pd = new Product();
            pd.setName("Product: " + i);
            pd.setPrice(10);
            ret.add(pd);
        }
        return ret;
    }

}
