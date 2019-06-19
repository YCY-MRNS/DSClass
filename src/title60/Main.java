package title60;

import java.util.Scanner;

/**
 * @program: DSClass
 * @description: 60:精挑细选
 * @author: ChangYue
 * @create: 2019-06-19 18:27
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int len = in.nextInt();

        if (len == 1) {
            len = in.nextInt();
        }

        int[] cho = new int[len];
        int[][] arr = new int[cho.length][3];


        for (int i = 0; i < cho.length; i++) {
            arr[i][0] = in.nextInt();
            arr[i][1] = in.nextInt();
            arr[i][2] = in.nextInt();
        }

        int[] optimal = new int[3];

        for (int i = 0; i < arr.length; i++) {
            if (i < arr.length - 1 && optimalChoc(arr[i], arr[i + 1])) {
                optimal = arr[i];
            }
        }
        System.out.println(optimal[2]);

    }

    private static boolean optimalChoc(int[] aChoc, int[] bChoc) {
        if (aChoc[0] != bChoc[0])
            return aChoc[0] > bChoc[0];
        else if (aChoc[1] != bChoc[1])
            return aChoc[1] < bChoc[1];
        else return aChoc[2] > bChoc[2];
    }
}
