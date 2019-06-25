package title32;

import java.util.Scanner;


public class Main {
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

        String str = " ";
        int count = 0;
        for (int i = 0; i < length; i++) {

            if (!oddLq.isEmpty()) {
                count++;
                System.out.print(oddLq.poll() + str);
            }
            if (!oddLq.isEmpty()) {
                count++;
                System.out.print(oddLq.poll() + str);
            }
            if (!evenLq.isEmpty()) {
                count++;
                System.out.print(evenLq.poll() + str);
            }
            if (count == length) {
                str = "";
            }
        }
    }
}

interface Queue<T> {
    public abstract boolean isEmpty();

    public abstract boolean add(T x);

    public abstract T peek();

    public abstract T poll();
}

class Node<T> {
    public T data;
    public Node<T> next;

    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    public Node() {
        this(null, null);
    }

    public String toString() {
        return this.data.toString();
    }
}


final class LinkedQueue<T> implements Queue<T> {
    private Node<T> front, rear;

    public LinkedQueue() {
        this.front = this.rear = null;
    }

    public boolean isEmpty() {
        return this.front == null && this.rear == null;
    }

    public boolean add(T x) {
        if (x == null)
            return false;
        Node<T> q = new Node<T>(x, null);
        if (this.front == null)
            this.front = q;
        else
            this.rear.next = q;
        this.rear = q;
        return true;
    }

    public T peek() {
        return this.isEmpty() ? null : this.front.data;
    }

    public T poll() {
        if (isEmpty())
            return null;
        T x = this.front.data;
        this.front = this.front.next;
        if (this.front == null)
            this.rear = null;
        return x;
    }

}


