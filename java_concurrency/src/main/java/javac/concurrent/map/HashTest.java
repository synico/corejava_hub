package javac.concurrent.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashTest {

    private static final Map hashMap = new HashMap();

    private static final Map hashtable = new Hashtable();

    public static void testNullAsKeyInMap() {
        hashMap.put(null, "null");
        System.out.println("nullAsKy value: " + hashMap.get(null));
    }

    public static void testNullAsValueInMap() {
        hashMap.put(null, null);
        System.out.println("nullAsValue value: " + hashMap.get(null));
    }

    public static void testNullAsKeyInTable() {
        hashtable.put(null, "null");
        System.out.println("nullAsKy value: " + hashtable.get(null));
    }

    public static void testNullAsValueInTable() {
        hashtable.put("null", null);
        System.out.println("nullAsValue value: " + hashtable.get("null"));
    }

    public static void main(String[] args) {
//        HashTest.testNullAsKeyInMap();
//        HashTest.testNullAsValueInMap();
//        HashTest.testNullAsKeyInTable();
        HashTest.testNullAsValueInTable();
    }
}
