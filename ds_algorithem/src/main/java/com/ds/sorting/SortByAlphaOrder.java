package com.ds.sorting;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class SortByAlphaOrder {

    private List<String> mfList = new LinkedList<String>();

    private boolean loadData() {
        mfList.add("mfName_ntk_cs%3A%22ADLINK%22");
        mfList.add("mfName_ntk_cs%3A%22AIC%22");
        mfList.add("mfName_ntk_cs%3A%22Aaeon+Electronics%22");
        mfList.add("mfName_ntk_cs%3A%22Aavid+Thermalloy%22y");
        mfList.add("mfName_ntk_cs%3A%22Advantech%22");
        mfList.add("mfName_ntk_cs%3A%22Axiomtek%22");
        mfList.add("mfName_ntk_cs%3A%22Cooler+Master%22");
        mfList.add("mfName_ntk_cs%3A%22Delta+Group%22");
        mfList.add("mfName_ntk_cs%3A%22Edgecore+Networks%22");
        mfList.add("mfName_ntk_cs%3A%22Intel%22");
        mfList.add("mfName_ntk_cs%3A%22Mellanox+Technologies%22");
        mfList.add("mfName_ntk_cs%3A%22Murata+Manufacturing%22");
        mfList.add("mfName_ntk_cs%3A%22Nidec+Copal+Electronics%22");
        mfList.add("mfName_ntk_cs%3A%22OMRON%22");
        mfList.add("mfName_ntk_cs%3A%22ROHM%22");
        mfList.add("mfName_ntk_cs%3A%22SUNON%22");
        mfList.add("mfName_ntk_cs%3A%22ebm-papst%22");
        return true;
    }

    public void sortMF() {
        if (loadData()) {
            Collections.sort(mfList, new Comparator<String>() {

                Collator usCollator = Collator.getInstance(Locale.US);

                @Override
                public int compare(String o1, String o2) {
                    return usCollator.compare(o1, o2);
                }

            });

            for (String mfName : mfList) {
                System.out.println(mfName);
            }
        }
    }

    public static void main(String[] args) {
        SortByAlphaOrder sort = new SortByAlphaOrder();
        sort.sortMF();
    }

}
