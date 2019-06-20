package title43;


import java.util.LinkedList;

public class BinaryTree<T> {
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
            System.out.print(p.data.toString() + " ");
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
            System.out.print(p.data.toString() + " ");
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

    /**
     * 先根遍历
     */
    public void preorderTraverse() {
        LinkedList<BinaryNode<T>> stack = new LinkedList<>();
        BinaryNode<T> p = this.root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                System.out.print(p.data + " ");
                stack.push(p);
                p = p.left;
            } else {
                System.out.print("^ ");
                p = stack.pop();
                p = p.right;
            }
            System.out.print("");
        }
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

    public void leaf() {
        leaf(this.root);
        System.out.println();
    }

    /**
     * 叶子结点
     *
     * @param p
     */
    public void leaf(BinaryNode<T> p) {
        if (p != null) {
            leaf(p.left);
            leaf(p.right);
            if (p.isLeaf()) {
                System.out.print(p.data.toString() + " ");
            }
        }
    }

    public void serach(T x) {
        serach(this.root, x);
        System.out.println();
    }

    /**
     * 查找结点
     *
     * @param p
     * @param x
     * @return
     */
    private T serach(BinaryNode<T> p, T x) {
        if (p != null) {
            if (p.data.toString().equals(x)) {
                System.out.print(p.data.toString());
                return x;
            }
            serach(p.left, x);
            serach(p.right, x);
        }
        return null;
    }

    public void swap() {
        swap(this.root);
        System.out.println();
    }

    /**
     * 交换孩子
     *
     * @param p
     */
    public void swap(BinaryNode<T> p) {
        if (p != null) {
            System.out.print(p.data.toString() + " ");
            BinaryNode old = null;
            old = p.left;
            p.left = p.right;
            p.right = old;
            swap(p.left);
            swap(p.right);
        }
    }

}
