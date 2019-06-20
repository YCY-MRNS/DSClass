package title40;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> array = new ArrayList<>();
        String line;

        while (!"0".equals(line = sc.nextLine())) {
            array.add(line);
        }

        BinarySortTree<String> binaryTree = new BinarySortTree<>();

        for (String m : array) {
            if (!binaryTree.contains(m)) {
                binaryTree.add(m);
                System.out.println(m + " inserted");
            } else {
                System.out.println(m);
            }
        }

    }
}

class BinarySortTree<T extends Comparable<? super T>> {
    private TriNode<T> root;

    BinarySortTree() {
        this.root = null;
    }

    public BinarySortTree(T[] values) {
        this();
        this.addAll(values);
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    private TriNode<T> searchNode(T key) {
        TriNode<T> p = this.root;
        while (p != null && key.compareTo(p.data) != 0) {
            if (key.compareTo(p.data) < 0) {
                System.out.print(p.data + " ");
                p = p.left;
            } else {
                System.out.print(p.data + " ");
                p = p.right;

            }

        }
        return p;
    }

    public T search(T key) {
        TriNode<T> find = this.searchNode(key);
        return find != null ? find.data : null;
    }

    boolean add(T x) {
        if (this.root == null)
            this.root = new TriNode<T>(x);
        else {
            TriNode<T> p = this.root, parent = null;
            while (p != null) {
                if (x.compareTo(p.data) == 0)
                    return false;
                parent = p;
                if (x.compareTo(p.data) < 0)
                    p = p.left;
                else p = p.right;
            }
            if (x.compareTo(parent.data) < 0)
                parent.left = new TriNode<T>(x, parent, null, null);
            else parent.right = new TriNode<T>(x, parent, null, null);
        }
        return true;
    }

    public String toString() {
        String str = "";
        TriNode<T> p = this.first(this.root);
        while (p != null) {
            str += p.data.toString() + " ";

            p = this.next(p);
        }
        return str;
    }


    private TriNode<T> first(TriNode<T> p) {
        if (p != null)
            while (p.left != null)
                p = p.left;
        return p;
    }

    private TriNode<T> next(TriNode<T> p) {
        if (p != null) {
            if (p.right != null)
                return this.first(p.right);
            while (p.parent != null) {
                if (p.parent.left == p)
                    return p.parent;
                p = p.parent;
            }
        }
        return null;
    }

    boolean contains(T key) {
        return this.searchNode(key) != null;

    }

    private void addAll(T[] values) {
        for (T value : values) {
            this.add(value);
        }
    }

    public void clear() {
        this.root = null;
    }

    public int size() {
        return 0;
    }

    private int level(TriNode<T> p) {
        int level = 0;
        while (p != null) {
            level++;
            p = p.parent;
        }
        return level;
    }
}


class TriNode<T> {
    T data;
    TriNode<T> parent, left, right;

    TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    TriNode(T data) {
        this(data, null, null, null);
    }

    public TriNode() {
        this(null, null, null, null);
    }

    public String toString() {
        return this.data.toString();
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }
}   