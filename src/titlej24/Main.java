package titlej24;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: DSClass
 * @description: 24:双向链表的创建与遍历
 * @author: ChangYue
 * @create: 2019-06-20 09:01
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
       /* int x = in.nextInt();
        while (0 != x) {
            x = in.nextInt();
        }

        in.close();*/

        String len = in.nextLine();
        int arrLen = Integer.parseInt(len);
        System.out.println(arrLen);
        char[] chars = new char[arrLen];

        String arrChar = in.nextLine();
        String[] arrCharSplit = arrChar.split("\\s+");
        System.out.println(arrChar);
        System.out.println(Arrays.toString(arrCharSplit));

        for (String s : arrCharSplit) {
            char c = s.charAt(0);
        }

        String weigth = in.nextLine();
        String[] w = weigth.split("\\s+");
        System.out.println(Arrays.toString(w));


    }

}
