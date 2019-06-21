package title77;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: DSClass
 * @description:
 * @author: ChangYue
 * @create: 2019-06-20 17:39
 */
public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] len = in.nextLine().split("\\s+");

        System.out.println(Arrays.toString(len));

        String[] mArr = in.nextLine().split("\\s+");
        System.out.println(Arrays.toString(mArr));

        int n = Integer.parseInt(len[1]);
        String[] nArr = new String[n];
        for (int i = 0; i < n; i++) {
            nArr[i] = in.nextLine();
        }
        System.out.println(Arrays.toString(nArr));
    }
}
