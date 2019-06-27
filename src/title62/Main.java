package title62;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String arrLen = in.nextLine();
        int[] arr = new int[Integer.parseInt(arrLen)];
        String arrDataStr = in.nextLine();
        String[] arrDataStrSplit = arrDataStr.split("\\s+");
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(arrDataStrSplit[i]);
        }
        bubbleSort(arr);
    }

    private static void swap(int[] keys, int i, int j) {
        int temp = keys[j];
        keys[j] = keys[i];
        keys[i] = temp;
    }

    private static void bubbleSort(int[] keys) {
        bubbleSort(keys, true);
    }

    private static void bubbleSort(int[] keys, boolean asc) {
        boolean exchange = true;
        for (int i = 1; i < keys.length && exchange; i++) {
            exchange = false;
            for (int j = 0; j < keys.length - i; j++)
                if (asc ? keys[j] > keys[j + 1] : keys[j] < keys[j + 1]) {
                    swap(keys, j, j + 1);
                    exchange = true;
                }
            for (int key : keys) {
                System.out.print(key + " ");
            }
            System.out.println();
        }
    }
}
