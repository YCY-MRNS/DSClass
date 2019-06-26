package title51;

import java.util.Arrays;
import java.util.Scanner;

public class PrimAlgorithm {

    public static void main(String[] args) {

        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F'};

        Scanner in = new Scanner(System.in);
        String conditionStr = in.nextLine();
        String[] cond = conditionStr.split("\\s+");
        int length = Integer.parseInt(cond[0]);


        int[][] weight = new int[length][length];

        for (int i = 0; i < length; i++) {
            String lineStr = in.nextLine();
            String[] line = lineStr.split("\\s+");
            for (int j = 0; j < line.length; j++) {
                weight[i][j] = Integer.parseInt(line[j]);
            }
        }


        //创建一个图类
        MGraph graph = new MGraph(length);
        //创建一个最小树
        MinTree minTree = new MinTree();
        //创建图
        minTree.createGraph(graph, length, data, weight);
        //从第一个点开始生成
        minTree.prim(graph, 0);
    }
}


class MinTree {

    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    public void showGraph(MGraph graph) {
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void prim(MGraph graph, int v) {

        int visited[] = new int[graph.verxs];


        visited[v] = 1;

        int h1 = -1;
        int h2 = -1;
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {

                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.print(minWeight + " ");

            visited[h2] = 1;

            minWeight = 10000;
        }

    }
}

class MGraph {
    int verxs;
    char[] data;
    int[][] weight;

    public MGraph(int verxs) {
        this.verxs = verxs;
        data = new char[verxs];
        weight = new int[verxs][verxs];
    }
}

