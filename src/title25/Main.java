package title25;


import java.util.Scanner;

/**
 * @program: DSClass
 * @description: 25:删除数组中的元素（链表）
 * @author: ChangYue
 * @create: 2019-06-21 09:26
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int len = in.nextInt();
        Integer[] arr = new Integer[len];

        for (int i = 0; i < len; i++) {
            arr[i] = in.nextInt();
        }

        SinglyList<Integer> list = new SinglyList<>(arr);

        int removeNum = in.nextInt();
        for (int i = 0; i < list.size(); i++) {
            if (list.contains(removeNum)) {
                list.remove(removeNum);
            }
        }
        System.out.println(list);
        in.close();
    }

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


class SinglyList<T> {
    public Node<T> head;


    public SinglyList() {
        this.head = new Node<T>();
    }


    public SinglyList(T[] values) {
        this();
        Node<T> rear = this.head;
        for (int i = 0; i < values.length; i++)
            if (values[i] != null) {
                rear.next = new Node<T>(values[i], null);
                rear = rear.next;
            }
    }

    public boolean isEmpty() {
        return this.head.next == null;
    }


    public T get(int i) {
        Node<T> p = this.head.next;
        for (int j = 0; p != null && j < i; j++)
            p = p.next;
        return (i >= 0 && p != null) ? p.data : null;
    }


    public String toString() {
        String str = "";

        for (Node<T> p = this.head.next; p != null; p = p.next) {
            str += p.data.toString() + " ";
        }
        return str;
    }


    public int size() {
        int i = 0;
        for (Node<T> p = this.head.next; p != null; p = p.next)
            i++;
        return i;
    }

    public Node<T> insert(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        Node<T> front = this.head;
        for (int j = 0; front.next != null && j < i; j++)
            front = front.next;
        front.next = new Node<T>(x, front.next);
        return front.next;
    }

    public Node<T> insert(T x) {
        return insert(Integer.MAX_VALUE, x);
    }


    public Node<T> search(T key) {
        for (Node<T> p = this.head.next; p != null; p = p.next)
            if (key.equals(p.data))
                return p;
        return null;
    }

    public boolean contains(T key) {
        return this.search(key) != null;
    }


    public T remove(T key) {
        Node<T> front = this.head, p = front.next;
        while (p != null && !key.equals(p.data)) {
            front = p;
            p = p.next;
        }
        if (p != null) {
            front.next = p.next;
            return p.data;
        }
        return null;
    }


    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof SinglyList<?>))
            return false;
        Node<T> p = this.head.next;
        Node<T> q = ((SinglyList<T>) obj).head.next;
        while (p != null && q != null && p.data.equals(q.data)) {
            p = p.next;
            q = q.next;
        }
        return p == null && q == null;
    }


}



