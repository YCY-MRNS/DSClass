package title60;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T;
        int n;
        String a;
        String b;
        String c;
        int i;
        String aMax = null;
        String bMin = null;
        String cMax = null;

        T = scanner.nextInt();

        while (T != 0) {
            T--;
            n = scanner.nextInt();

            for (i = 0; i < n; i++) {
                a = scanner.next();
                b = scanner.next();
                c = scanner.next();

                if (i == 0) {
                    aMax = a;
                    bMin = b;
                    cMax = c;
                    continue;
                }
                if (a.compareTo(aMax) > 0) {
                    aMax = a;
                    bMin = b;
                    cMax = c;
                } else if (a.compareTo(aMax) == 0) {
                    if (b.compareTo(bMin) < 0) {
                        aMax = a;
                        bMin = b;
                        cMax = c;
                    } else if (b.compareTo(bMin) == 0) {
                        if (c.compareTo(cMax) > 0) {
                            aMax = a;
                            bMin = b;
                            cMax = c;
                        }
                    }
                }
            }

            System.out.println(cMax);


        }
    }


}