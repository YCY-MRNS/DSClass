package title70;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        String num = in.nextLine();

        int arrLen = Integer.parseInt(num);
        int[] arr = new int[arrLen];

        String arrData = in.nextLine();
        String[] arrDataInt = arrData.split("\\s+");

        for (int i = 0; i < arrLen; i++)
            arr[i] = Integer.parseInt(arrDataInt[i]);

        in.close();
        sort(arr);

    }

    private static void sort(int[] a) {

        int max = a[0];
        for (int anA1 : a) {
            if (max < anA1)
                max = anA1;
        }

        int m = (max + "").length();

        ArrayList<SeqQueue<Integer>> qArr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            qArr.add(new SeqQueue<>());
        }

        for (int k = 1, n = 1; k < m + 1; k++, n *= 10) {
            System.out.println("Step" + k + ".");
            int[] eleCount = new int[10];

            for (int anA : a) {
                int numberOfDigits = anA / n % 10;
                qArr.get(numberOfDigits).add(anA);
                eleCount[numberOfDigits]++;
            }

            int index = 0;
            for (int i = 0; i < qArr.size(); i++) {
                System.out.print("Queue" + i + ":");
                if (eleCount[i] != 0) {
                    for (int i1 = 0; i1 < eleCount[i]; i1++) {
                        a[index++] = qArr.get(i).poll();
                    }
                } else {
                    System.out.print("");
                }
                eleCount[i] = 0;
                System.out.println();
            }
        }
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

final class SeqQueue<T> {
    private Object element[];
    private int front, rear;

    private SeqQueue(int length) {
        this.element = new Object[length];
        this.front = this.rear = 0;
    }

    SeqQueue() {
        this(64);
    }

    public boolean isEmpty() {
        return this.front == this.rear;
    }

    public boolean add(T x) {
        if (x == null)
            return false;

        if (this.front == (this.rear + 1) % this.element.length) {
            Object[] temp = this.element;
            this.element = new Object[temp.length * 2];
            int j = 0;
            for (int i = this.front; i != this.rear; i = (i + 1) % temp.length)
                this.element[j++] = temp[i];
            this.front = 0;
            this.rear = j;
        }
        this.element[this.rear] = x;
        this.rear = (this.rear + 1) % this.element.length;
        return true;
    }

    T poll() {
        if (this.isEmpty())
            return null;
        T temp = (T) this.element[this.front];
        this.front = (this.front + 1) % this.element.length;
        System.out.print(temp + " ");
        return temp;
    }


}
