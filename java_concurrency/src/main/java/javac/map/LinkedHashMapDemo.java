package javac.map;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
public class LinkedHashMapDemo {

    private static volatile Map<String, String> map = new LinkedHashMap<String, String>();

    private List list;

    private List initList() {
        list = new ArrayList();
        List temp = new ArrayList();
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "one");
        map.put("2", "two");
        map.put("3", "three");
        temp.add(map);
        list.add(temp);
        return list;
    }

    private void traverseList() {
        // Need pay attention to the lifecycle of generic type
        List<Map<String, String>> theList = initList();
        System.out.println(theList);
    }

    public static void main(String[] args) {
        map.put("a", "A");
        new LinkedHashMapDemo().traverseList();
    }

}
