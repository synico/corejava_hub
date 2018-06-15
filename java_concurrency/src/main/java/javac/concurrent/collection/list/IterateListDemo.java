package javac.concurrent.collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class IterateListDemo {

    private final static List<String> list = new ArrayList<String>();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            list.add(Integer.toString(i));
        }
        List<String> syncList = Collections.synchronizedList(list);
        Iterator<String> listitor = syncList.iterator();
        synchronized (syncList) {
            while (listitor.hasNext()) {
                if (syncList.contains("3")) {
                    syncList.remove("3");
                }
                listitor.next();
            }
        }
    }

}
