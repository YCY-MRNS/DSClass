package title30;

import java.util.Scanner;

public class Main {

    public static String isMatched(String infix) {
        SeqStack<String> stack = new SeqStack<String>(infix.length());
        String str = "";

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            switch (ch) {
                case '(':
                    stack.push(ch + "");
                    str += ")";
                    break;
                case ')':
                    if (stack.isEmpty() || !stack.pop().equals("("))
                        return "No";
                    break;
                case '{':
                    stack.push(ch + "");
                    str += "}";
                    break;
                case '}':
                    if (stack.isEmpty() || !stack.pop().equals("{"))
                        return "No";
                    break;
                case '[':
                    stack.push(ch + "");
                    str += "]";
                    break;
                case ']':
                    if (stack.isEmpty() || !stack.pop().equals("["))
                        return "No";
                    break;
            }
        }
        return (stack.isEmpty()) ? "Yes" : "No";
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(Main.isMatched(str));
    }
}

interface Stack<T> {
    public abstract boolean isEmpty();

    public abstract void push(T x);

    public abstract T peek();

    public abstract T pop();
}

class SeqList<T> {
    protected Object[] element;
    protected int n;

    public SeqList(int length) {
        this.element = new Object[length];
        this.n = 0;
    }

    public boolean isEmpty() {
        return this.n == 0;
    }

    public int size() {
        return this.n;
    }

    public T get(int i) {
        if (i >= 0 && i < this.n)
            return (T) this.element[i];
        return null;
    }

    public String toString() {
        String str = "";
        if (this.n > 0)
            str += this.element[0].toString();
        for (int i = 1; i < this.n; i++)
            str += " " + this.element[i].toString();
        return str;
    }

    public int insert(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i < 0) i = 0;
        if (i > this.n) i = this.n;
        Object[] source = this.element;
        if (this.n == element.length) {
            this.element = new Object[source.length * 2];
            for (int j = 0; j < i; j++)
                this.element[j] = source[j];
        }
        for (int j = this.n - 1; j >= i; j--)
            this.element[j + 1] = source[j];
        this.element[i] = x;
        this.n++;
        return i;
    }

    public int insert(T x) {
        return this.insert(this.n, x);
    }

    public T remove(int i) {
        if (this.n > 0 && i >= 0 && i < this.n) {
            T old = (T) this.element[i];
            for (int j = i; j < this.n - 1; j++)
                this.element[j] = this.element[j + 1];
            this.element[this.n - 1] = null;
            this.n--;
            return old;
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof SeqList<?>) {
            SeqList<T> list = (SeqList<T>) obj;
            if (this.n == list.n) {
                for (int i = 0; i < this.n; i++)
                    if (!(this.get(i).equals(list.get(i))))
                        return false;
                return true;
            }
        }
        return false;
    }

}

final class SeqStack<T> implements Stack<T> {
    private SeqList<T> list;

    public SeqStack(int length) {
        this.list = new SeqList<T>(length);
    }

    public SeqStack() {
        this(64);
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public void push(T x) {
        this.list.insert(x);
    }

    public T peek() {
        return this.list.get(list.size() - 1);
    }

    public T pop() {
        return this.list.remove(list.size() - 1);
    }

    public String toString() {
        return "SeqStack:" + this.list.toString();
    }
}

