package title52;

import java.util.Scanner;

/**
 * @description: 52:丛林中的路
 * @author: YuanChangYue
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
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int villagesNum = Integer.valueOf(in.nextLine());

        /*
         * 首先根据顶点数创建村落顶点以及村落权值邻接表
         * 在while中循环取出每个村落到有关联村落的权值 放进邻接表中
         * 取出下标为1值，既是当前村落相关村落的数量
         * 下标为2的值（第一个相关联的村落）是否存在
         * 取出对应村落的权值以及下标 接着把当前村落和相关村落和权值存放在邻接表中
         * 循环将无关的村落的值变为100 表示无关
         * 创建克鲁斯卡尔 将顶点和权值传个过去
         * 获得最小生成树 将最小的权值放回
         */

        String[] villagesVertex = getVillagesVertex(villagesNum);
        int[][] weight = new int[villagesNum][villagesNum];

        while (villagesNum != 0) {

            for (int i = 0; i < villagesNum - 1; i++) {
                String villagesAndValuesStr = in.nextLine();
                String[] villagesAndValues = villagesAndValuesStr.split("\\s+");

                int numOfAcc = Integer.valueOf(villagesAndValues[1]);

                for (int j = 0, q = 2; j < numOfAcc; j++, q += 2) {
                    //if (getIndex(villagesAndValues[q], villagesVertex) != -1) {

                    int value = Integer.valueOf(villagesAndValues[q + 1]);
                    int c = getIndex(villagesAndValues[q], villagesVertex);

                    weight[i][c] = value;

                    //}
                }
            }

            for (int i = 0; i < weight.length; i++) {
                for (int j = 0; j < weight.length; j++) {
                    if (weight[i][j] == 0) {
                        weight[i][j] = 100;
                    }
                }
            }

            KruskalCase kruskalCase = new KruskalCase(villagesVertex, weight);
            kruskalCase.kruskal();
            kruskalCase.print();

            for (int i = 0; i < weight.length; i++) {
                for (int j = 0; j < weight.length; j++) {
                    weight[i][j] = 100;
                }
            }

            villagesNum = Integer.valueOf(in.nextLine());
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

class KruskalCase {

    private int edgeNum;
    private String[] vertexs;
    private int[][] matrix;

    public KruskalCase(String[] vertexs, int[][] matrix) {

        int vlen = vertexs.length;

        this.vertexs = new String[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < vlen; i++) {
            for (int j = i + 1; j < vlen; j++) {
                if (this.matrix[i][j] != 100) {
                    edgeNum++;
                }
            }
        }
    }

    public void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];
        int values = 0;
        EData[] rets = new EData[edgeNum];
        EData[] edges = getEdges();

        sortEdges(edges);

        for (int i = 0; i < edgeNum; i++) {

            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(ends, p1);
            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n; // <B,I> [0,8,0,0,0,0,0,0,0]
                rets[index++] = edges[i];
                values += edges[i].weight;
            }

        }

        System.out.println(values);

        for (int i = 0; i < index; i++) {
            System.out.print(rets[i]);
        }

    }

    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    private void sortEdges(EData[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    public void print() {
        System.out.println("邻接矩阵为: \n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    private int getPosition(String ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i].equals(ch)) {
                return i;
            }
        }
        return -1;
    }

    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != 100) {
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }
}

class EData {
    String start;
    String end;
    int weight;

    EData(String start, String end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return start + " " + end + " ";
    }

}


