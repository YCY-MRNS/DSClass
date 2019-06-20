package title43;

import java.util.Scanner;

/**
 * * AB#D##CE###
 */
public class BinaryTreeMain {
    public static void main(String[] args) {


        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        String[] test = {"A", "B", "#", "D", "#", "#", "C", "E", "#", "#"};
        BinaryTree<String> binaryTree = new BinaryTree<>(test);

        binaryTree.inorder();
        binaryTree.postorder();
        System.out.println();
        binaryTree.leaf();
        System.out.println(binaryTree.height());
    }
}
