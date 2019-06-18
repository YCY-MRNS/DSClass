package title42;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


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