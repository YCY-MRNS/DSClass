package title53;

import java.util.Scanner;

public class KruskalCase {

    private int edgeNum;
    private char[] vertexs;
    private int[][] matrix;


    public static void main(String[] args) {
        char[] vertexs = {'1', '2', '3', '4', '5', '6'};


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


        KruskalCase kruskalCase = new KruskalCase(vertexs, weight);

        kruskalCase.kruskal();

    }


    KruskalCase(char[] vertexs, int[][] matrix) {

        int vlen = vertexs.length;


        this.vertexs = new char[vlen];
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

    void kruskal() {
        int index = 0;
        int[] ends = new int[edgeNum];

        EData[] rets = new EData[edgeNum];


        EData[] edges = getEdges();


        sortEdges(edges);


        for (int i = 0; i < edgeNum; i++) {

            int p1 = getPosition(edges[i].start);

            int p2 = getPosition(edges[i].end);


            int m = getEnd(ends, p1);

            int n = getEnd(ends, p2);

            if (m != n) {
                ends[m] = n;
                rets[index++] = edges[i];
            }
        }


        for (int i = 0; i < index; i++) {
            System.out.print(rets[i] + " ");
        }
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


    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {
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


    private int getEnd(int[] ends, int i) {
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

}


class EData {
    char start;
    char end;
    int weight;


    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return start + " " + end;
    }

}
