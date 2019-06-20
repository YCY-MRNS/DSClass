package title20;


import java.util.Scanner;

public class Main {

    public Main(int number, int start, int distance) {

        SeqList<Integer> list = new SeqList<>(number);

        for (int i = 1; i < number + 1; i++)
            list.insert(i);
        int i = start;
        while (list.size() > 1) {
            i = (i + distance - 1) % list.size();
            list.remove(i);
        }
        System.out.println(list.get(0).toString());
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        new Main(in.nextInt(), 0, in.nextInt());
        in.close();
    }
}


class SeqList<T> {
    protected Object[] element;
    protected int n;


    public SeqList(int length) {
        this.element = new Object[length];

        this.n = 0;
    }

    public SeqList() {
        this(64);
    }

    public SeqList(T[] values) {
        this(values.length);

        for (int i = 0; i < values.length; i++)
            this.element[i] = values[i];
        this.n = element.length;


    }


    public int size() {
        return this.n;
    }


    public T get(int i) {
        if (i >= 0 && i < this.n)
            return (T) this.element[i];

        return null;
    }


    public void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else throw new IndexOutOfBoundsException(i + "");
    }


    public String toString() {
        String str = this.getClass().getName() + "(";

        if (this.n > 0)
            str += this.element[0].toString();
        for (int i = 1; i < this.n; i++)
            str += ", " + this.element[i].toString();
        return str + ") ";
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



