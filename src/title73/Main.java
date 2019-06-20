package title73;

import java.util.Scanner;

/**
 * @program: DSClass
 * @description: 73:牛的选举
 * @author: ChangYue
 * @create: 2019-06-20 19:15
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String kn = in.nextLine();
        String[] knArr = kn.split("\\s+");
        int k = Integer.parseInt(knArr[0]);
        int n = Integer.parseInt(knArr[1]);

        int[] firstArr = new int[k];
        int[] secondArr = new int[k];
        int[] temp = new int[k];

        for (int i = 0; i < k; i++) {
            String[] num = in.nextLine().split("\\s");
            firstArr[i] = Integer.parseInt(num[0]);
            secondArr[i] = Integer.parseInt(num[1]);
            temp[i] = firstArr[i];
        }


        quickSort(firstArr);


        for (int j = 0; j < temp.length; j++) {
            for (int i = 0; i < n; i++) {
                if (temp[j] == firstArr[i]) {
                    firstArr[i] = j;
                }
            }
        }

        int[] result = new int[n];
        for (int l = 0; l < n; l++) {
            result[l] = secondArr[firstArr[l]];
        }
        quickSort(result);

    }


    private static void quickSort(int[] keys) {
        quickSort(keys, 0, keys.length - 1);
    }

    private static void quickSort(int[] keys, int begin, int end) {

        if (begin >= 0 && begin < keys.length && end >= 0 && end < keys.length && begin < end) {
            int i = begin, j = end;
            int vot = keys[i];
            while (i != j) {

                while (i < j && vot >= keys[j])
                    j--;
                if (i < j)
                    keys[i++] = keys[j];

                while (i < j && keys[i] >= vot)
                    i++;
                if (i < j)
                    keys[j--] = keys[i];

            }
            keys[i] = vot;
            quickSort(keys, begin, j - 1);
            quickSort(keys, i + 1, end);
        }

    }


}
