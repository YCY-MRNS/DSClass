package title50;//《数据结构（Java版）（第4版）》，作者：叶核亚，2014年7月24日

public class G3u {
    public static void main(String args[]) {
        //【例7.1】  带权图的存储及操作。
     String[] vertices = {"v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"};           //带权无向图G3的顶点集合（除F） ，图7.13
        Triple[] edges = {new Triple(0, 1, 45), new Triple(0, 2, 28), new Triple(0, 3, 10),
                new Triple(1, 0, 45), new Triple(1, 2, 12), new Triple(1, 4, 21),
                new Triple(2, 0, 28), new Triple(2, 1, 12), new Triple(2, 3, 17), new Triple(2, 4, 26),
                new Triple(3, 0, 10), new Triple(3, 2, 17), new Triple(3, 4, 15),
                new Triple(4, 1, 21), new Triple(4, 2, 26), new Triple(4, 3, 15)};//G3的边集合（除F）




     /*   Triple[] edges2 = {new Triple(0, 1, 0), new Triple(0, 2, 28), new Triple(0, 5, 10),
                new Triple(1, 2, 0), new Triple(1, 3, 12), new Triple(2, 3, 21),
                new Triple(2, 0, 0), new Triple(2, 1, 12), new Triple(2, 3, 17), new Triple(2, 4, 26),
                new Triple(3, 5, 0), new Triple(4, 5, 17), new Triple(6, 7, 15),
                };//G3的边集合（除F）*/
        MatrixGraph<String> graph = new MatrixGraph<String>(vertices, edges);  //邻接矩阵表示的图

        int i = 0;
        System.out.println("深度优先遍历连通无向图G3：");
        graph.DFSTraverse(0);

        System.out.println("广度优先遍历连通图无向图G3：");
        graph.BFSTraverse(0);

    }
}

