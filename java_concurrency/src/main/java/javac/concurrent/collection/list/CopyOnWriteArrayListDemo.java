package javac.concurrent.collection.list;

import java.util.ArrayList;
import java.util.List;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        List<String> strList = new ArrayList<String>();
        strList.add("1");
        strList.add("2");
        List<Object> objs = new ArrayList<Object>();
        objs.addAll(strList);
        if (strList instanceof List) {
            System.out.println(true);
            System.out.println(strList.getClass());
        }
        for (Object s : objs) {
            System.out.println(s);
        }
        List aStrs = new ArrayList();
        aStrs.addAll(objs);

    }

}
