package title22;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Man man;
        SortedSinglyList<Man> men = new SortedSinglyList<>();

        while (in.hasNext()) {
            String str = in.nextLine();
            if (str.equals("END")) {
                System.out.println(men);
                for (int i = 0; i < men.size(); i++) {
                    if (men.get(i).getAge() % 2 == 0) {
                        men.remove(i);
                    }
                }
                System.out.println(men);
                men.clear();
                break;
            }
            String[] manStr = str.split("\\s+");
            man = new Man(manStr[0], Integer.parseInt(manStr[1]));
            men.insert(man);
        }
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


class Man implements Comparable<Man> {
    private String name;
    private int age;

    Man(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int getAge() {
        return age;
    }

    @Override
    public int compareTo(Man o) {
        return this.age - o.getAge();
    }

    @Override
    public String toString() {
        return "(" + name + " " + age + ')';
    }
}


class SinglyList<T> {
    public Node<T> head;

    public SinglyList() {
        this.head = new Node<T>();
    }


    public T get(int i) {
        Node<T> p = this.head.next;
        for (int j = 0; p != null && j < i; j++)
            p = p.next;
        return (i >= 0 && p != null) ? p.data : null;
    }

    public void clear() {
        this.head.next = null;
    }

    public String toString() {
        String str = "";

        for (Node<T> p = this.head.next; p != null; p = p.next) {
            str += p.data.toString();

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

    public T remove(int i) {
        Node<T> front = this.head;
        for (int j = 0; front.next != null && j < i; j++)
            front = front.next;
        if (i >= 0 && front.next != null) {
            T old = front.next.data;
            front.next = front.next.next;
            return old;
        }
        return null;
    }
}

class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T> {

    SortedSinglyList() {
        super();
    }

    public Node<T> insert(int i, T x) {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }

    public Node<T> insert(T x) {
        Node<T> front = this.head, p = front.next;
        while (p != null && x.compareTo(p.data) > 0) {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);
        return front.next;
    }
}




