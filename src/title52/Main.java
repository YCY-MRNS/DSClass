package title52;

import title53.KruskalCase;

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
        int villages = Integer.valueOf(in.nextLine());

        char[] vertexes = getTotalVertex(villages);
        int[][] weight = new int[villages][villages];

        while (villages != 0) {

            String[] villagesVertex = getVillagesVertex(villages);

            for (int i = 0; i < villages - 1; i++) {
                String villagesAndValuesStr = in.nextLine();
                String[] villagesAndValues = villagesAndValuesStr.split("\\s+");

                int currentVertex = Integer.valueOf(villagesAndValues[1]);

                for (int j = 0, q = 2; j < currentVertex; j++, q += 2) {
                    if (getIndex(villagesAndValues[2], villagesVertex) != -1) {

                        int value = Integer.valueOf(villagesAndValues[q + 1]);
                        int c = getIndex(villagesAndValues[q], villagesVertex);

                        weight[i][c] = value;
                    }
                }
            }

            for (int i = 0; i < weight.length; i++) {
                for (int j = 0; j < weight.length; j++) {
                    if (weight[i][j] == 0) {
                        weight[i][j] = 100;
                    }
                }
            }


            KruskalCase kruskalCase = new KruskalCase(vertexes, weight);
            kruskalCase.kruskal();
            System.out.println(kruskalCase.totalValue);


            for (int i = 0; i < weight.length; i++) {
                for (int j = 0; j < weight.length; j++) {
                    weight[i][j] = 100;
                }
            }

            villages = Integer.valueOf(in.nextLine());

        }
        in.close();
    }


    /**
     * 获得顶点
     *
     * @param VertexLen 顶点数
     * @return 顶点集合
     */
    private static char[] getTotalVertex(int VertexLen) {
        char[] v = new char[VertexLen];
        for (int i = 0; i < VertexLen; i++) {
            v[i] = (char) (i + 97);
        }
        return v;
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
