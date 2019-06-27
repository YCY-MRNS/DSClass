package title70;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int arrLen = in.nextInt();
        int[] arr = new int[arrLen];

        for (int i = 0; i < arrLen; i++)
            arr[i] = in.nextInt();

        in.close();
        sort(arr);
    }


    /**
     * 这道题根据题目可以知道，利用基数排序能够完成，将队列当成一个桶
     * 首先将从控制台获取的值中遍历出最大的那个值，有多少位意味着后面需要遍历多少次
     * 创建一个ArrayList存放队列桶，一个数只有0-9 所以整好也对应list的下标
     * 另外需要创建一个数组来存放每个桶已经存放的数量，以便之后出队
     * 循环判断个十百千..位，将对应值放进同号的桶队列中
     * 当循环最多为位数时既是排序完成的时候
     *
     * @param arr
     */
    private static void sort(int[] arr) {


        int max = arr[0];
        for (int anA1 : arr) {
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

            for (int anA : arr) {
                int numberOfDigits = anA / n % 10;
                qArr.get(numberOfDigits).add(anA);
                eleCount[numberOfDigits]++;
            }

            int index = 0;
            for (int i = 0; i < qArr.size(); i++) {
                System.out.print("Queue" + i + ":");

                if (eleCount[i] != 0) {
                    for (int i1 = 0; i1 < eleCount[i]; i1++) {
                        arr[index++] = qArr.get(i).poll();
                    }
                } else {
                    System.out.print("");
                }
                eleCount[i] = 0;
                System.out.println();
            }
        }

        for (int i : arr) {
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

    void add(T x) {
        if (x == null)
            return;
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
