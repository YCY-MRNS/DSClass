package title43;

import java.util.Scanner;

public class SeqBinaryTree {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String textByIn = in.next();

        int inLength = textByIn.length();
        char[] seqBinaryTree = new char[inLength];

        double c = Math.log(textByIn.length()) / (Math.log(2)) + 1;
        System.out.println("该二叉树的高度为：" + (int) c);


        System.out.print("该二叉树的叶子结点：");
        for (int i = 0; i < inLength; i++) {
            seqBinaryTree[i] = textByIn.charAt(i);
            if ((2 * i + 1) > inLength || (2 * i + 2) > inLength) {
                System.out.print(textByIn.charAt(i));
            }
        }


    }
}
