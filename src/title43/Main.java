package title43;

import java.util.Scanner;

/**
 * * AB#D##CE###
 */
public class Main {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        String line = in.next();
        String[] treeArr = line.split("");
        String[] tree = new String[treeArr.length];
        for (int i = 0; i < treeArr.length; i++) {
            if (!treeArr[i].equals("#")) {
                tree[i] = treeArr[i];
            }
        }

        BinaryTree<String> binaryTree = new BinaryTree<>(tree);
        binaryTree.inorder();
        binaryTree.postorder();
        System.out.println(binaryTree.leaf());
        System.out.println(binaryTree.height());
    }
}

class BinaryNode<T> {

    public T data;
    public BinaryNode<T> left, right;

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BinaryNode(T data) {
        this(data, null, null);
    }

    public String toString() {
        return this.data.toString();
    }

    public boolean isLeaf() {
        return this.left == null && this.right == null;
    }

}


class BinaryTree<T> {
    public BinaryNode<T> root;

    public BinaryTree() {
        this.root = null;
    }

    public String toString() {
        return toString(this.root);
    }

    public String toString(BinaryNode<T> p) {
        if (p == null)
            return "^";
        return p.data.toString() + " " + toString(p.left) + toString(p.right);
    }

    private int i = 0;

    public BinaryTree(T[] prelist) {
        this.root = create(prelist);
    }

    private BinaryNode<T> create(T[] prelist) {
        BinaryNode<T> p = null;
        if (i < prelist.length) {
            T elem = prelist[i];
            i++;
            if (elem != null) {
                p = new BinaryNode<T>(elem);
                p.left = create(prelist);
                p.right = create(prelist);
            }
        }
        return p;
    }

    public void postorder() {
        postorder(this.root);
        System.out.println();
    }

    /**
     * 后根遍历
     *
     * @param p
     */
    private void postorder(BinaryNode<T> p) {
        if (p != null) {
            postorder(p.left);
            postorder(p.right);
            System.out.print(p.data.toString());
        }
    }


    public void inorder() {
        inorder(this.root);
        System.out.println();
    }

    /**
     * 中根遍历
     *
     * @param p
     */
    public void inorder(BinaryNode<T> p) {
        if (p != null) {
            inorder(p.left);
            System.out.print(p.data.toString());
            inorder(p.right);
        }
    }

    public int height() {
        return height(root);
    }


    /**
     * 二叉树高度
     *
     * @param p
     * @return
     */
    public int height(BinaryNode<T> p) {
        if (p == null)
            return 0;
        return (int) (Math.log(size()) / Math.log(2)) + 1;
    }


    public int size() {
        return size(this.root);
    }

    /**
     * 二叉树的结点数
     *
     * @param p
     * @return
     */
    public int size(BinaryNode<T> p) {
        if (p != null) {
            return 1 + size(p.right) + size(p.left);
        }
        return 0;
    }


    public int leaf() {
        return leaf(this.root);
    }

    /**
     * 叶子结点
     *
     * @param p
     */
    int leafNum = 0;

    public int leaf(BinaryNode<T> p) {
        if (p != null) {
            leaf(p.left);
            leaf(p.right);
            if (p.isLeaf()) {
                leafNum++;
                // System.out.print(p.data.toString() + " ");
            }
        }
        return leafNum;
    }

}
