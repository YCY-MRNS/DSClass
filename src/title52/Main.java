package title52;

import java.util.Comparator;
import java.util.Scanner;

/**
 * @program: DSClass-master
 * @description:
 * @author: ChangYue
 * @TestData 9
 * A 2 B 12 I 25
 * B 3 C 10 H 40 I 8
 * C 2 D 18 G 55
 * D 1 E 44
 * E 2 F 60 G 38
 * F 0
 * G 1 H 35
 * H 1 I 35
 * 3
 * A 2 B 10 C 40
 * B 1 C 20
 * 0
 * @create: 2019-06-19 10:38
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int villages = Integer.valueOf(in.nextLine());
        while (villages != 0) {
            int count = 0;

            String[] villagesVertex = getVillagesVertex(villages);
            Triple[] edges1 = new Triple[74];

            for (int i = 0; i < villages - 1; i++) {
                String villagesAndValuesStr = in.nextLine();
                String[] villagesAndValues = villagesAndValuesStr.split("\\s+");

                int currentVertex = Integer.valueOf(villagesAndValues[1]);

                for (int j = 0, q = 2; j < currentVertex; j++, q += 2) {
                    if (getIndex(villagesAndValues[2], villagesVertex) != -1) {

                        int value = Integer.valueOf(villagesAndValues[q + 1]);
                        int c = getIndex(villagesAndValues[q], villagesVertex);

                        edges1[count++] = new Triple(i, c, value);

                    }
                }
            }

            Triple[] edges = new Triple[count];
            System.arraycopy(edges1, 0, edges, 0, count);

            MinSpanTree mst = new MinSpanTree(villages, edges, new TripleComparator());

            System.out.println(mst.getMinvalue());

            villages = Integer.valueOf(in.nextLine());

        }
        in.close();
    }

    /**
     * 根据k获得对应村落的顶点数
     *
     * @param k 村落数
     * @return 对应村落数的集合
     */
    private static String[] getVillagesVertex(int k) {
        String[] villagesVertex = new String[k];
        char a = 'A';
        for (int i = 0; i < k; i++) {
            villagesVertex[i] = String.valueOf(a++);
        }
        return villagesVertex;
    }

    /**
     * 获得对应村落数的集合的index
     *
     * @param m 查询的值
     * @param n 集合
     * @return index
     */
    private static int getIndex(String m, String[] n) {
        for (int i = 0; i < n.length; i++) {
            if (m.equals(n[i])) {
                return i;
            }
        }
        return -1;
    }
}

class MinSpanTree {
    private Triple[] mst;

    MinSpanTree(int n, Triple[] edges, Comparator<Triple> cmpr) {
        this.mst = new Triple[n - 1];
        Heap<Triple> minheap = new Heap<Triple>(true, edges, cmpr);
        UnionFindSet ufset = new UnionFindSet(n);
        int i = 0;
        for (int j = 0; j < edges.length; j++) {
            Triple minedge = minheap.removeRoot();
            if (ufset.union(minedge.row, minedge.column)) {
                this.mst[i++] = minedge;
            }
        }
    }

    int getMinvalue() {
        int totalValue = 0;
        for (Triple triple : mst) {
            totalValue += triple.value;
        }
        return totalValue;
    }
}

class Heap<T> {
    private boolean minheap;
    private SeqList<T> heap;
    private Comparator<T> cmpr;

    private Heap(boolean minheap, Comparator<T> cmpr) {
        this.minheap = minheap;
        this.heap = new SeqList<>();
        this.cmpr = cmpr;
    }

    Heap(boolean minheap, T[] values, Comparator<T> cmpr) {
        this(minheap, cmpr);
        for (T value : values) {
            this.insert(value);
        }
    }

    private void insert(T x) {
        this.heap.insert(x);
        for (int i = this.heap.size() / 2 - 1; i > 0; i = (i - 1) / 2) {
            sift(i);
        }
        sift(0);
    }

    private boolean isEmpty() {
        return this.heap.isEmpty();
    }

    private int size() {
        return this.heap.size();
    }

    private void sift(int parent) {
        int end = this.size() - 1;
        int child = 2 * parent + 1;
        T value = this.heap.get(parent);
        while (child <= end) {
            int comp = 0;
            if (child < end) {
                T left = this.heap.get(child), right = this.heap.get(child + 1);
                if (this.cmpr == null)
                    comp = ((Comparable<T>) left).compareTo(right);
                else
                    comp = this.cmpr.compare(left, right);
                if (this.minheap ? comp > 0 : comp < 0) {
                    child++;
                }
            }
            if (this.cmpr == null) {
                comp = ((Comparable<T>) value).compareTo(this.heap.get(child));
            } else
                comp = this.cmpr.compare(value, this.heap.get(child));
            if (this.minheap ? comp > 0 : comp < 0) {
                this.heap.set(parent, this.heap.get(child));
                parent = child;
                child = 2 * parent + 1;
            } else
                break;

        }
        this.heap.set(parent, value);
    }

    T removeRoot() {
        if (this.isEmpty()) {
            return null;
        }
        T x = (T) this.heap.get(0);
        this.heap.set(0, this.heap.get(this.heap.size() - 1));
        this.heap.remove(this.heap.size() - 1);
        if (this.heap.size() > 1) {
            sift(0);
        }
        return x;
    }
}

class SeqList<T> {

    private Object[] element;
    private int n;

    private SeqList(int length) {
        this.element = new Object[length];
        this.n = 0;
    }

    SeqList() {
        this(64);
    }

    private int insert(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i < 0) i = 0;
        if (i > this.n) i = this.n;
        Object[] source = this.element;
        if (this.n == element.length) {
            this.element = new Object[source.length * 2];
            System.arraycopy(source, 0, this.element, 0, i);
        }
        if (this.n - i >= 0) System.arraycopy(source, i, this.element, i + 1, this.n - i);
        this.element[i] = x;
        this.n++;
        return i;
    }

    int insert(T x) {
        return this.insert(this.n, x);
    }

    int size() {
        return this.n;
    }

    boolean isEmpty() {
        return this.n == 0;
    }

    T get(int i) {
        if (i >= 0 && i < this.n)
            return (T) this.element[i];
        return null;
    }

    void set(int i, T x) {
        if (x == null)
            throw new NullPointerException("x==null");
        if (i >= 0 && i < this.n)
            this.element[i] = x;
        else throw new java.lang.IndexOutOfBoundsException(i + "");
    }

    T remove(int i) {
        if (this.n > 0 && i >= 0 && i < this.n) {
            T old = (T) this.element[i];
            if (this.n - 1 - i >= 0) System.arraycopy(this.element, i + 1, this.element, i, this.n - 1 - i);
            this.element[this.n - 1] = null;
            this.n--;
            return old;
        }
        return null;
    }
}

class Triple {
    int row, column, value;

    public Triple(int row, int column, int value) {
        if (row >= 0 && column >= 0) {
            this.row = row;
            this.column = column;
            this.value = value;
        } else throw new IllegalArgumentException("s=" + row + "s=" + column);
    }

    @Override
    public String toString() {
        return "Triple{" +
                "row=" + row +
                ", column=" + column +
                ", value=" + value +
                '}';
    }
}

class TripleComparator implements java.util.Comparator<Triple> {
    public int compare(Triple t1, Triple t2) {
        return (int) (t1.value - t2.value);
    }
}

class UnionFindSet {
    private int parent[];

    UnionFindSet(int n) {
        this.parent = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = -1;
        }
    }

    private int find(int i) {
        while (this.parent[i] >= 0) {
            i = this.parent[i];
        }
        return i;
    }

    boolean union(int i, int j) {
        int rooti = find(i), rootj = find(j);
        if (rooti != rootj) {
            if (parent[rooti] <= parent[rootj]) {
                this.parent[rooti] += this.parent[rootj];
                this.parent[rootj] = rooti;
            } else {
                this.parent[rootj] += this.parent[rooti];
                this.parent[rooti] = rootj;
            }
        }
        return rooti != rootj;
    }
}