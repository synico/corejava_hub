package javac.generic;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;

@SuppressWarnings("all")
public class UnsafeDemo {

    public static Unsafe getUnsafeInstance() throws Exception {
        Field unsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        unsafeInstance.setAccessible(true);
        return (Unsafe) unsafeInstance.get(Unsafe.class);
    }

    public static void main(String[] args) {
        try {
            Unsafe unsafe = getUnsafeInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] lastFactors = { 1 };
        Integer[] add = { 2 };
        lastFactors = Arrays.copyOf(add, add.length);
        for (Integer v : lastFactors) {
            System.out.println(v);
        }
    }

}
