package title50;

import java.util.Scanner;

/**
 * @Author: 袁阊越  2018520612
8 9
0 1
0 2
0 5
1 2
1 3
2 3
3 5
4 5
6 7
 * @date: 2019/5/19 0019
 */
public class graphic {

    public static void main(String[] args) {

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
            triple = new Triple(Integer.parseInt(inte[0]), Integer.parseInt(inte[1]), 2);
            e[i] = triple;
        }
      /*  Triple[] edges2 = {new Triple(1, 2, 1), new Triple(1, 3, 0), new Triple(1, 6, 1),
                new Triple(2, 3, 1), new Triple(2, 4, 1), new Triple(3, 4, 1),
                new Triple(4, 6, 1), new Triple(5, 6, 1), new Triple(7, , 1),
        };//G3的边集合（除F）
        */
        String[] vv = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};
        System.out.println("从个点出发：");
        AdjListGraph<String> graph = new AdjListGraph<>(vv, e);
        System.out.println(graph);

        for (int i = 0; i < graph.vertexCount(); i++) {
            System.out.println("从顶点为" + vv[i] + "出发");
            graph.DFSTraverse(i);
        }

        System.out.println("===========");
        for (int i = 0; i < graph.vertexCount(); i++) {
            System.out.println("从顶点为" + vv[i] + "出发");
            graph.BFSTraverse(i);
        }
    }

}





