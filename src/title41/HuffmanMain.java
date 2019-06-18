package title41;

import java.util.Scanner;


public class HuffmanMain {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        String len = in.nextLine();
        String charset = in.nextLine();
        String arrStr = in.nextLine();

        String[] split = charset.split("\\s+");
        String[] arr = arrStr.split("\\s+");

        int[] weight = new int[Integer.parseInt(len)];

        for (int i = 0; i < split.length; i++) {
            weight[i] = Integer.parseInt(arr[i]);
        }

        HuffmanTree huffman = new HuffmanTree(weight);
        System.out.println(huffman.toString());

    }
}

class TriElement {
    int data;
    int parent, left, right;

    public TriElement(int data, int parent, int left, int right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public TriElement(int data) {
        this(data, -1, -1, -1);
    }

    public String toString() {
        return "(" + this.data + "," + this.parent + "," + this.left + "," + this.right + ")";
    }

}


class HuffmanTree {
    private String charset;
    private TriElement[] huftree;

    public HuffmanTree(int[] weights) {
        this.charset = "";
        for (int i = 0; i < weights.length; i++)
            this.charset += (char) ('a' + i);

        int n = weights.length;
        this.huftree = new TriElement[2 * n - 1];
        for (int i = 0; i < n; i++)
            this.huftree[i] = new TriElement(weights[i]);

        for (int i = n; i < 2 * n - 1; i++) {
            int min1 = Integer.MAX_VALUE, min2 = min1;
            int x1 = -1, x2 = -1;
            for (int j = 0; j < i; j++)
                if (this.huftree[j].parent == -1)
                    if (this.huftree[j].data < min1) {
                        min2 = min1;
                        x2 = x1;
                        min1 = this.huftree[j].data;
                        x1 = j;
                    } else if (this.huftree[j].data < min2) {
                        min2 = huftree[j].data;
                        x2 = j;
                    }

            this.huftree[x1].parent = i;
            this.huftree[x2].parent = i;
            this.huftree[i] = new TriElement(min1 + min2, -1, x1, x2);
        }
    }

    private String getCode(int i) {
        int n = 8;
        char hufcode[] = new char[n];
        int child = i, parent = this.huftree[child].parent;
        for (i = n - 1; parent != -1; i--) {
            hufcode[i] = (huftree[parent].left == child) ? '0' : '1';
            child = parent;
            parent = huftree[child].parent;
        }
        return new String(hufcode, i + 1, n - 1 - i);
    }

    public String toString() {
        String str = "";
        for (int i = 0; i < this.charset.length(); i++)
            str += this.charset.charAt(i) + "：" + getCode(i) + "\n";
        return str;
    }
}






