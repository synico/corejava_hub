package javac.map;

import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapDemoV8 {

    private Map<String, String> v8Map = new LinkedHashMap<String, String>();

    private void demo4Put() {
        v8Map.put("key1", "value1");
        v8Map.put("key2", "value2");

        demo4Itr();
    }

    private void demo4Itr() {
        for (Map.Entry<String, String> entry : v8Map.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        LinkedHashMapDemoV8 demo = new LinkedHashMapDemoV8();
        demo.demo4Put();
    }

}
