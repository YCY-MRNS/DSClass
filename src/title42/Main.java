package title42;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: YuanChangYue
 * 找父母的方法 有两个
 * 0.可以往前遍历 遇到第一个空格数小于他的 就是 他的 父母
 * 1.可以用一个数组来维护 当前空格数/2 的那个人 那么 当前空格数/2 - 1 的人就是他父母然后要注意
 * <p>
 * 0.sibling 是 父母相同的人 而不是 空格数相同的人
 * 1.ancestor 是 父母或 父母的父母的父母。。。 而不是单单的 空格数 小于 就可以了
 * <p>
 * 6 5
 * John
 * Robert
 * Frank
 * Andrew
 * Nancy
 * David
 * Robert is a child of John
 * Robert is an ancestor of Andrew
 * Robert is a sibling of Nancy
 * Nancy is the parent of Frank
 * John is a descendant of Andrew
 * @TestData
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String numStr = in.nextLine();
        String[] num = numStr.split("\\s+");

        Map<String, String> relation = new HashMap<>();
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);
        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            int blackNum = getBlackNum(name);
            if (blackNum == 0) {
                relation.put(name, "ancestor");
                names[0] = name;
            } else {
                relation.put(name.trim(), names[blackNum / 2 - 1]);
                names[blackNum / 2] = name.trim();
            }
        }

        /* Robert is a child of John
         * Robert is an ancestor of Andrew
         * Robert is a sibling of Nancy
         * Nancy is the parent of Frank
         * John is a descendant of Andrew
         * */
        String temp = "";
        for (int i = 0; i < m; i++) {
            String judge = in.nextLine();
            String[] judgeSentence = judge.split("\\s+");
            switch (judgeSentence[3]) {
                case "parent":
                    temp = judgeSentence[0];
                    judgeSentence[0] = judgeSentence[5];
                    judgeSentence[5] = temp;
                case "child":
                    if (relation.get(judgeSentence[0]).equals(judgeSentence[5]))
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;
                case "sibling":
                    if (relation.get(judgeSentence[0]).equals(relation.get(judgeSentence[5])))
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;
                case "ancestor":
                    temp = judgeSentence[0];
                    judgeSentence[0] = judgeSentence[5];
                    judgeSentence[5] = temp;
                case "descendant":
                    while (!relation.get(judgeSentence[0]).equals(relation.get(judgeSentence[5])) && !relation.get(judgeSentence[0]).equals("ancestor")) {
                        judgeSentence[0] = relation.get(judgeSentence[0]);
                    }

                    if (relation.get(judgeSentence[0]).equals(relation.get(judgeSentence[5]))) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }

            }
        }
    }

    private static int getBlackNum(String str) {
        int blackNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                blackNum++;
        }
        return blackNum;
    }

}