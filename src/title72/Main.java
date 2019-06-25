package title72;

import java.util.Scanner;

/**
 * @program: DSClassDesign
 * @description:
 * @author: YuanChangYue
 * @TestData
 * 6634 9796 435 1405 6123 10001 11459 12018 10372 19874 12860 11326 7096 30205 27010
 * 6634,9796,435,1405,6123,10001,11459,12018,10372,19874,12860,11326,7096,30205,27010
 * @create: 2019-06-18 11:49
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arrays = new int[15];
        for (int i = 0; i < 15; i++)
            arrays[i] = in.nextInt();

        in.close();
        barrelSort(arrays);
    }

    private static void barrelSort(int[] arr) {

        int[][] bucket = new int[10][arr.length];
        int[] bucketEleCount = new int[arr.length];

        int max = arr[0];
        for (int anArr : arr) {
            if (max < anArr)
                max = anArr;
        }


        int len = (max + "").length();

        for (int m = 0, n = 1; m < len; m++, n *= 10) {

            for (int anArr : arr) {
                int numberOfDigits = anArr / n % 10;
                bucket[numberOfDigits][bucketEleCount[numberOfDigits]] = anArr;
                bucketEleCount[numberOfDigits]++;
                //  System.out.print(anArr + "--" + numberOfDigits + "-" + bucketEleCount[numberOfDigits] + " \n");
            }
            //    System.out.println(bucketEleCount.length);
            int index = 0;
            for (int j = 0; j < bucketEleCount.length; j++) {
                //  System.out.println(bucketEleCount[j]);
                if (bucketEleCount[j] != 0) {
                    for (int k = 0; k < bucketEleCount[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                }
                bucketEleCount[j] = 0;
            }

            for (int i : arr) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }


}
