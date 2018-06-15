package javac.generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Reduce<E> {

    public Reduce() {
        System.out.println("constructor");
    }

    static <E> E reduce(List<E> list, Function<E, E> f, E initVal) {
        // E[] snapshot = list.toArray();
        E result = initVal;
        // for(E e : snapshot) {
        // result = f.apply(e);
        // }
        return result;
    }

    private static <E> E getInstance() {
        return (E) new Reduce<E>();
    }

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<K, V>();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Reduce t = Reduce.getInstance();
        System.out.println(t.getClass());
    }

}
