package title61;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }
        selectSort(arr);
    }

    private static void swap(int[] keys, int i, int j) {
        int temp = keys[j];
        keys[j] = keys[i];
        keys[i] = temp;
    }

    private static void selectSort(int[] keys) {
        for (int i = 0; i < keys.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < keys.length; j++)
                if (keys[j] < keys[min])
                    min = j;
            if (min != i)
                swap(keys, i, min);
            for (int key : keys) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }
}
