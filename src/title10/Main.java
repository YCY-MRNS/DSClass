package title10;

import java.util.Scanner;


/**
 * @program: DSClass
 * @description: 10:顺序表的合并
 * @author: ChangYue
 * @create: 2019-06-21 10:37
 */
public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int aLen = in.nextInt();
        int bLen = in.nextInt();

        SeqList<Integer> l1 = new SeqList<>(aLen);
        SeqList<Integer> l2 = new SeqList<>(bLen);

        for (int i = 0; i < aLen; i++) {
            l1.insert(in.nextInt());
        }

        for (int i = 0; i < bLen; i++) {
            l2.insert(in.nextInt());
        }

        l1.addAll(l2);

        int temp;
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l1.size(); j++) {
                if (l1.get(i) < l1.get(j)) {
                    temp = l1.get(j);
                    l1.set(j, l1.get(i));
                    l1.set(i, temp);
                }
            }
        }
        System.out.println(l1);
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


    public void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else throw new java.lang.IndexOutOfBoundsException(i + "");
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

    public void clear() {
        this.n = 0;
    }


    public int search(T key) {

        for (int i = 0; i < this.n; i++) {

            if (key.equals(this.element[i]))
                return i;
        }
        return -1;
    }

    public boolean contains(T key) {
        return this.search(key) != -1;
    }


    public T remove(T key) {
        return this.remove(this.search(key));
    }


    public SeqList(SeqList<? extends T> list) {

        this.n = list.n;
        this.element = new Object[list.element.length];
        for (int i = 0; i < list.n; i++)
            this.element[i] = list.element[i];

    }


    public void removeAll(T key) {
        int i = 0;
        while (i < this.n)
            if (key.equals(this.element[i]))
                this.remove(i);
            else i++;
    }


    public void addAll(SeqList<? extends T> list) {
        for (int i = 0; i < list.n; i++)
            this.insert(list.get(i));

    }


    boolean removeAll(SeqList<? extends T> list) {
        T old = null;
        for (int i = 0; i < list.n; i++)
            old = this.remove(list.get(i));
        return old != null;
    }

    boolean retainAll(SeqList<? extends T> list) {
        return false;
    }


}
