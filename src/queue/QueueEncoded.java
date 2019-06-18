package queue;

import java.util.Scanner;


public class QueueEncoded {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedQueue<Integer> encoded = new LinkedQueue<>();
        String content = scanner.nextLine();
        String[] encodedKey = scanner.nextLine().split("\\s");

        for (String s : encodedKey) {
            encoded.add(Integer.parseInt(s));
        }

        StringBuilder temp = new StringBuilder();


        for (int i = 0; i < content.length(); i++) {

            Integer poll = encoded.poll();
            int ASCLL = content.charAt(i) + poll;

            if (ASCLL > 122)
                ASCLL = 96 + (ASCLL - 122);
            if (ASCLL > 90 && ASCLL < 97)
                ASCLL = 64 + (ASCLL - 90);

            temp.append((char) (ASCLL));

            encoded.add(poll);
        }

        System.out.println(temp);
    }
}
