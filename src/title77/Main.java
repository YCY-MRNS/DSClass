package title77;

import java.util.Scanner;
import java.util.TreeMap;


public class Main {
    private static int[] table = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 0, 7, 7, 8, 8, 8, 9, 9, 9, 0};
    private static String[] leadingZero = {"000000", "00000", "0000", "000", "00", "0", ""};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();

        TreeMap<Integer, Integer> map = new TreeMap<>();

        for (int i = 0; i < N; ++i) {
            String raw = scanner.nextLine();
            int val = getNumber(raw);
            if (map.containsKey(val)) {
                map.replace(val, map.get(val) + 1);
            } else {
                map.put(val, 1);
            }
        }

        boolean flag = false;
        for (Integer x : map.keySet()) {
            int y = map.get(x);
            if (y > 1) {
                flag = true;
                String s = String.valueOf(x);
                s = leadingZero[s.length() - 1] + s;
                System.out.println(s.substring(0, 3) + '-' + s.substring(3, 7) + " " + y);
            }
        }

        if (!flag) System.out.println("No duplicates.");
    }

    private static int getNumber(String raw) {
        int res = 0;
        for (int i = 0; i < raw.length(); ++i) {
            char ch = raw.charAt(i);
            if (ch == '-') continue;
            if (Character.isDigit(ch)) {
                res = res * 10 + (ch - '0');
            } else {
                res = res * 10 + table[ch - 'A'];
            }
        }
        return res;
    }
}
