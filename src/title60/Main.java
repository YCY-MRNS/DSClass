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
        int z = in.nextInt();

        int[] temp = new int[3];

        while (z != 0) {
            z--;

            int len = in.nextInt();

            int[][] arr = new int[len][3];

            for (int i = 0; i < len; i++) {
                arr[i][0] = in.nextInt();
                arr[i][1] = in.nextInt();
                arr[i][2] = in.nextInt();

                if (i == 0) {
                    temp[0] = arr[i][0];
                    temp[1] = arr[i][1];
                    temp[2] = arr[i][2];
                }

                for (int j = 0; j < arr.length; j++) {
                    if (j < arr.length - 1 && optimalChoc(temp, arr[j])) {
                        temp[0] = arr[j][0];
                        temp[1] = arr[j][1];
                        temp[2] = arr[j][2];
                    }
                }
                System.out.println(temp[2]);
            }
        }
    }

    private static boolean optimalChoc(int[] aChoc, int[] bChoc) {
        if (aChoc[0] != bChoc[0])
            return aChoc[0] > bChoc[0];
        else if (aChoc[1] != bChoc[1])
            return aChoc[1] < bChoc[1];
        else return aChoc[2] > bChoc[2];
    }

}



































