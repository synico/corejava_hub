package javac.map;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemoV8 {

    private Map<String, String> v8Map = new HashMap<String, String>();

    private void demo4Put() {
        v8Map.put("key1", "value1");
        v8Map.put("key2", "value2");
        v8Map.put("key3", "value3");
        v8Map.put("key4", "value4");
        v8Map.put("key5", "value5");
        v8Map.put("key6", "value6");
    }

    public static void main(String[] args) {
        HashMapDemoV8 demo = new HashMapDemoV8();
        demo.demo4Put();
    }

}
