package queue;

import java.util.Scanner;


public class QueueBankProcess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        LinkedQueue<Integer> oddLq = new LinkedQueue<>();
        LinkedQueue<Integer> evenLq = new LinkedQueue<>();

        for (int j = 0; j < length; j++) {
            int number = scanner.nextInt();
            if (number % 2 == 0) {
                evenLq.add(number);
            } else {
                oddLq.add(number);
            }
        }

        for (int i = 0; i < length; i++) {
            if (!oddLq.isEmpty()) {
                System.out.print(oddLq.poll()+" ");
            }
            if (!oddLq.isEmpty()) {
                System.out.print(oddLq.poll()+" ");
            }
            if (!evenLq.isEmpty()){
                System.out.print(evenLq.poll()+" ");
            }
        }

    }


}

