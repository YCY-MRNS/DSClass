package title42;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Description: 42:家谱处理
 * @return:
 * @Author: YuanChangYue
 * @TestData :
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
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String numStr = in.nextLine();
        String[] num = numStr.split("\\s+");

        Map<String, String> link = new HashMap<>();
        int n = Integer.parseInt(num[0]);
        int m = Integer.parseInt(num[1]);
        String[] names = new String[n];

        /*
         * 这道题实际上是类似于找父亲的过程,利用Map存放每个人和他的父亲
         *      1.如何存放Map中的？
         *           根据空格数判断 K+2的父亲为k， k/2 的父亲的就是 k/2-1
         */
        for (int i = 0; i < n; i++) {
            String name = in.nextLine();
            int blackNum = getBlackNum(name);
            if (blackNum == 0) {
                link.put(name, "ancestor");
                names[0] = name;
            } else {
                link.put(name.trim(), names[blackNum / 2 - 1]);
                names[blackNum / 2] = name.trim();
            }
        }





        /*
         * child      第一个值在map中的’父亲‘ 和最后的值是否相同
         * parent     Nancy is the parent of Frank --> Frank is the child of Nancy
         * sibling    判断父亲是不是相同的人
         * descendant Robert is an ancestor of Andrew --> Andrew is a descendant of Robert
         * ancestor   不是兄弟和第一个不是祖先 可能是父子关系 将一个值赋直为比较对象的值（确定是不是父子关系）这个两个值的父亲相同也是
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
                    if (link.get(judgeSentence[0]).equals(judgeSentence[5]))
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;
                case "sibling":
                    if (link.get(judgeSentence[0]).equals(link.get(judgeSentence[5])))
                        System.out.println("True");
                    else
                        System.out.println("False");
                    break;
                case "ancestor":
                    //Robert is an ancestor of Andrew --> Andrew is a descendant of Robert
                    temp = judgeSentence[0];
                    judgeSentence[0] = judgeSentence[5];
                    judgeSentence[5] = temp;
                case "descendant":
                    //Frank is a descendant of John --> John is a descendant of John
                    while (!link.get(judgeSentence[0]).equals(link.get(judgeSentence[5]))
                            && !link.get(judgeSentence[0]).equals("ancestor")) {
                        judgeSentence[0] = link.get(judgeSentence[0]);
                    }

                    //John is a descendant of Andrew
                    if (link.get(judgeSentence[0]).equals(link.get(judgeSentence[5]))) {
                        System.out.println("True");
                    } else {
                        System.out.println("False");
                    }
            }
        }
    }

    /**
     * 获得名字的空格数
     *
     * @param str 包含空格名称
     * @return 空格数
     */
    private static int getBlackNum(String str) {
        int blackNum = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ')
                blackNum++;
        }
        return blackNum;
    }

}