package title12;

import java.util.Scanner;

/**
 * @Description:
 * @Param:12:非递减有序集合合并
 * @TestData: 11   12
 * 2 4 6 7 8 9 12 34 56 78 89
 * 3 5 7 9 12 34 56 98 234 456 789 1234
 * @Author: YuanChangYue
 * @date:
 */
public class Main {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);
        int aLen = in.nextInt();
        int bLen = in.nextInt();

        SeqList<Integer> LA = new SeqList<>(aLen);
        SeqList<Integer> LB = new SeqList<>(bLen);

        for (int i = 0; i < aLen; i++) {
            LA.insert(in.nextInt());
        }

        for (int i = 0; i < bLen; i++) {
            LB.insert(in.nextInt());
        }

        LA.removeAll(LB);
        LA.addAll(LB);

        int temp;
        for (int i = 0; i < LA.size() - 1; i++) {
            for (int j = 0; j < LA.size(); j++) {
                if (LA.get(i) < LA.get(j)) {
                    temp = LA.get(j);
                    LA.set(j, LA.get(i));
                    LA.set(i, temp);
                }
            }
        }
        System.out.println(LA.size());
        System.out.println(LA);
    }
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

    public void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else throw new java.lang.IndexOutOfBoundsException(i + "");
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

    public int search(T key) {
        for (int i = 0; i < this.n; i++) {
            if (key.equals(this.element[i]))
                return i;
        }
        return -1;
    }

    public T remove(T key) {
        return this.remove(this.search(key));
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

    void addAll(SeqList<? extends T> list) {
        for (int i = 0; i < list.n; i++)
            this.insert(list.get(i));
    }

    boolean removeAll(SeqList<? extends T> list) {
        T old = null;
        for (int i = 0; i < list.n; i++)
            old = this.remove(list.get(i));
        return old != null;
    }
}



