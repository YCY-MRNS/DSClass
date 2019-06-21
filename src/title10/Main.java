package title10;

/**
 * @program: DSClass
 * @description: 10:顺序表的合并
 * @author: ChangYue
 * @create: 2019-06-21 10:37
 */
public class Main {
    public static void main(String[] args) {

        Integer[] a = {3, 7, 15, 10, 13};
        Integer[] b = {1, 9, 4, 5, 2, 15, 17};

        SeqList<Integer> l1 = new SeqList<>(a);
        SeqList<Integer> l2 = new SeqList<>(b);

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
    private Object[] element;
    protected int n;

    public SeqList(int length) {
        this.element = new Object[length];
        this.n = 0;
    }

    public void set(int i, T x) {
        if (i >= 0 && i < this.n) {
            this.element[i] = x;
        } else {
            throw new java.lang.IndexOutOfBoundsException(i + "");
        }
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


}

