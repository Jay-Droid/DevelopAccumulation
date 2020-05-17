package com.jay.java.算法.Sort;

public class SortTest {

    public static void main(String[] args) {
        int[] array = {1, 3, 11, 4, 6, 8, 2};
        printArray(array, "排序前: ");
        selectedSort(array);
        printArray(array, "选择排序: ");
    }

    private static void printArray(int[] array, String info) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(info);
        stringBuilder.append("[");
        for (int i : array) {
            stringBuilder.append(i).append(",");
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    /*
    选择排序算法
     */
    public static void selectedSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i];
            for (int j = i; j < array.length; j++) {
                if (min > array[j]) {
                    min = array[j];
                    int temp = array[i];
                    array[i] = min;
                    array[j] = temp;
                }
            }
        }
    }


}
