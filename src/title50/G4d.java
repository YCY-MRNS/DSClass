package title50;//《数据结构（Java版）（第4版）》，作者：叶核亚，2014年8月9日
//7.2   图的表示和实现
//7.3   图的遍历
//7.5.1   非负权值的单源最短路径（Dijkstra算法）

import java.util.Scanner;

public class G4d {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入两个整数n(3 <= n <= 100)和m，n为顶点的个数，m为边的条数：");
        String line = in.nextLine();
        String[] split = line.split("\\s");
        int edgess = Integer.parseInt(split[1]);
        int vertices = Integer.parseInt(split[0]);
        Triple e[] = new Triple[edgess];
        Triple triple = null;
        for (int i = 0; i < edgess; i++) {
            String s = in.nextLine();
            String[] inte = s.split("\\s");
            triple = new Triple(Integer.parseInt(inte[0]), Integer.parseInt(inte[1]), 1);
            e[i] = triple;
        }
      /*  Triple[] edges2 = {new Triple(1, 2, 1), new Triple(1, 3, 0), new Triple(1, 6, 1),
                new Triple(2, 3, 1), new Triple(2, 4, 1), new Triple(3, 4, 1),
                new Triple(4, 6, 1), new Triple(5, 6, 1), new Triple(7, , 1),
        };//G3的边集合（除F）
        */
        String[] vv = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};
        System.out.println("从个点出发：");
      /*
        String[] vertices={"A","B","C","D","E"};*/
        Triple edges[] = {new Triple(0, 1, 10), new Triple(0, 3, 30), new Triple(0, 4, 99),
                new Triple(1, 2, 50), new Triple(1, 3, 40),
                new Triple(2, 4, 10), new Triple(3, 2, 20), new Triple(3, 4, 60)};
//        MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);    //图7.12
        AdjListGraph<String> graph2 = new AdjListGraph<String>(vv, e);//图7.17

        MatrixGraph<String> graph = new MatrixGraph<String>(vv, e);
        System.out.print("带权有向图G4，" + graph.toString());

        //7.3   图的遍历
        System.out.println("深度优先遍历有向图G4：");                //图7.22（b），【思考题7-1~2，习题解答】遍历序列
        for (int i = 0; i < graph.vertexCount(); i++)
            graph.DFSTraverse(i);

        System.out.println("广度优先遍历有向图G4：");
        for (int i = 0; i < graph.vertexCount(); i++)
            graph.BFSTraverse(i);

    }
}

