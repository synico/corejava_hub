package com.ds.sorting;

import com.ds.util.PrintUtil;

public class InsertionSort {

    public static int[] insertionSort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int flag = array[i];
            for (int j = i; j > 0;) {
                j--;// continue to move ahead
                if (flag < array[j]) {
                    array[i] = array[j];// bigger element transfer to position
                                        // of 'flag'
                    array[j] = flag;
                    i = j;
                }
                // debugInfo(i, j, array);
            }
        }
        return array;
    }

    public static int[] insertionSort2(int[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            int temp = array[p];
            for (j = p; j > 0; j--) {
                if (temp < array[j - 1]) {
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }

    public static int[] insertionSort3(int[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            int temp = array[p];// Element need to be checked from array[0] to array[p]
            for (j = p; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
            }
            array[j] = temp;
        }
        return array;
    }

    public static int[] insertionSort4(int[] array) {
        int j;
        for (int p = 1; p < array.length; p++) {
            int temp = array[p];
            j = p;
            while (j > 0 && temp < array[j - 1]) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        return array;
    }

    public static int[] insertionSort5(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j;
            for (j = i; j > 0 && temp < array[j - 1]; j--) {
                array[j] = array[j - 1];
                System.out.println(j);
            }
            array[j] = temp;
        }

        return array;
    }

    public static int[] binaryInsertSort(int[] array) {
        for (int p = 1; p < array.length; p++) {
            int temp = array[p];
            int low = 0, high = p, j = 0;
            while (low <= high) {
                j = (low + high) / 2;
                if (temp < array[j]) {
                    high = j - 1;
                } else {
                    low = j + 1;
                }
            }
            // performance tuning - avoid manual array copy
//            for(int i = p; i > j; i--) {
//                array[i] = array[i - 1];
//            }
            System.arraycopy(array, j, array, j + 1, p - j);
            array[j] = temp;
        }
        return array;
    }

    public static void main(String[] args) {
        int[] demo = { 34, 8, 64, 51, 32, 21 };
        PrintUtil.printList(demo);
        int[] sortedArray = binaryInsertSort(demo);
        PrintUtil.printList(sortedArray);
    }

}
