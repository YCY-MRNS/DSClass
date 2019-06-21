package title12;

import java.util.Scanner;

public class SortedSeqList_Integer {
    public static void main(String args[]) {

        Scanner in = new Scanner(System.in);

        String arrLenStr = in.nextLine();
        String[] arrLen = arrLenStr.split("\\s+");

        SortedSeqList<Integer> LA = new SortedSeqList<>(Integer.parseInt(arrLen[0]));
        SortedSeqList<Integer> LB = new SortedSeqList<>(Integer.parseInt(arrLen[1]));

        String dataForLAStr = in.nextLine();
        String[] dataForLA = dataForLAStr.split("\\s+");
        for (String a : dataForLA) {
            LA.insert(Integer.parseInt(a));
        }

        String dataForLBStr = in.nextLine();
        String[] dataForLB = dataForLBStr.split("\\s+");
        for (String b : dataForLB) {
            LB.insert(Integer.parseInt(b));
        }

        LA.removeAll(LB);
        LA.addAll(LB);

        System.out.println(LA);




    }
}

class SortedSeqList<T extends Comparable<? super T>> extends SeqList<T> {

    public SortedSeqList(int length) {
        super(length);
    }

    public int insert(int i, T x) {
        throw new java.lang.UnsupportedOperationException("insert(int i, T x)");
    }

    public int search(T key) {
        for (int i = 0; i < this.n && key.compareTo(this.get(i)) >= 0; i++) {
            if (key.compareTo(this.get(i)) == 0)
                return i;
        }
        return -1;
    }

    public int insert(T x) {
        int i = 0;
        if (this.isEmpty() || x.compareTo(this.get(this.n - 1)) > 0)
            i = this.n;
        else
            while (i < this.n && x.compareTo(this.get(i)) > 0)
                i++;
        super.insert(i, x);
        return i;
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



