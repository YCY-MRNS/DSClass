package title50;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();//获取第一行数据
        String[] split1 = str1.split(" ");//将第一行数据存放入数组中
        int edge = Integer.parseInt(split1[1]);//获取边数
        String str2 = sc.nextLine();//获取第二行所有的顶点名称
        String[] vertex = str2.split(" ");//将所有的顶点名称放入数组中
        Triple edges[] = new Triple[edge];//构建并实例化图带权值的边类的数组，将其长度设置为获到的变的条数
        for (int i = 0; i < edge; i++) {//循环获取所有的边（从第三行开始），并将这些边存放在数组中
            String Edge = sc.nextLine();
            String[] split = Edge.split(" ");
            //获取所有有连接边的两个顶点的名称
            String num1 = split[0].substring(split[0].length() - 1);
            String num2 = split[1].substring(split[1].length() - 1);
            //实例化权值为1的三元组边类，存放在总的边数组中
            edges[i] = new Triple(Integer.parseInt(num1) - 1, Integer.parseInt(num2) - 1, 1);
        }
        //实例化以vertices顶点集合和edges边集合构造图
        MatrixGraph<String> mG = new MatrixGraph<>(vertex, edges);
        mG.DFSTraverse(0);//图的深度优先搜索
        mG.BFSTraverse(0);//图的广度优先搜索
    }

    public static class MatrixGraph<T> extends AbstractGraph<T> {
        protected Matrix matrix;                               //矩阵对象，存储图的邻接矩阵

        //构造空图，顶点数为0，边数为0；length指定顶点顺序表容量和邻接矩阵容量
        MatrixGraph(int length) {
            super(length);                                   //构造容量为length的空顺序表
            this.matrix = new Matrix(length);                //构造length×length矩阵，初值为0
        }

        MatrixGraph(T[] vertices)                       //以vertices顶点集合构造图，边数为0
        {
            this(vertices.length);                             //构造指定容量的空图
            for (int i = 0; i < vertices.length; i++) {
                this.insertVertex(vertices[i]);                //插入一个顶点
            }
        }

        MatrixGraph(T[] vertices, Triple[] edges)       //以vertices顶点集合和edges边集合构造图
        {
            this(vertices);                                    //以vertices顶点集合构造图，没有边
            for (Triple edge : edges)
                this.insertEdge(edge);                     //插入一条边
        }

        public String toString()                               //返回图的顶点集合和邻接矩阵描述字符串
        {
            String str = super.toString() + "邻接矩阵:  \n";
            int n = this.vertexCount();                        //顶点数
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++)
                    str += String.format("%6d", this.matrix.get(i, j));
                str += "\n";
            }
            return str;
        }

        //【例7.1】 带权无向图3的构造、插入及删除操作。
        //（1）插入边
        public void insertEdge(int i, int j, int weight)       //插入边〈vi,vj〉，权值为weight
        {
            if (i != j)                                          //不能表示自身环
            {
                if (weight <= 0 || weight > MAX_WEIGHT)            //边的权值容错，视为无边，取值∞
                    weight = 0;
                this.matrix.set(i, j, weight);                   //设置矩阵元素[i,j]值为weight。若i、j越界，抛出序号越界异常
            } else throw new IllegalArgumentException("不能插入自身环，i=" + i + "，j=" + j);
        }

        public void insertEdge(Triple edge)                    //插入一条边
        {
            this.insertEdge(edge.row, edge.column, edge.value);
            this.insertEdge(edge.column, edge.row, edge.value);
        }

        //（2）插入顶点
        public int insertVertex(T x)                           //插入元素为x的顶点，返回x顶点序号
        {
            int i = this.vertexlist.insert(x);                 //顶点顺序表尾插入x，返回x序号，自动扩容
            if (i >= this.matrix.getRows())                    //若邻接矩阵容量不够，
                this.matrix.setRowsColumns(i + 1, i + 1);           //矩阵扩容。保持邻接矩阵行列数同图的顶点数
            for (int j = 0; j < i; j++)                            //初始化第i行、列元素值为∞。i==j值已为0
            {
                this.matrix.set(i, j, 0);
                this.matrix.set(j, i, 0);
            }
            return i;                                          //返回插入顶点序号
        }

        //（3）删除边
        public void removeEdge(int i, int j)                   //删除边〈vi,vj〉，忽略权值
        {
            if (i != j)
                this.matrix.set(i, j, MAX_WEIGHT);             //设置边的权值为∞。若i、j越界，抛出序号越界异常
        }

        public void removeEdge(Triple edge)                    //删除边，忽略权值
        {
            this.removeEdge(edge.row, edge.column);
        }

        //（4）删除顶点
        public void removeVertex(int i)                        //删除顶点vi及其所有关联的边
        {
            int n = this.vertexCount();                          //原顶点数
            if (i >= 0 && i < n) {
                this.vertexlist.remove(i);                     //删除顶点顺序表第i个元素，顶点数减1。  //顺序表删除，若i越界，返回null
                for (int j = i + 1; j < n; j++)                      //第i+1～n-1行元素上移一行，n为原顶点数
                    for (int k = 0; k < n; k++)
                        this.matrix.set(j - 1, k, this.matrix.get(j, k));
                for (int j = 0; j < n; j++)
                    for (int k = i + 1; k < n; k++)                  //第i+1～n-1列元素左移一列
                        this.matrix.set(j, k - 1, this.matrix.get(j, k));
                this.matrix.setRowsColumns(n - 1, n - 1);          //邻接矩阵少一行一列
            } else throw new IndexOutOfBoundsException("i=" + i);  //抛出序号越界异常
        }

        //（5） 获得邻接顶点和边的权值属性
        public int weight(int i, int j)           //返回<vi,vj>边的权值。用于图的最小生成树、最短路径等算法
        {
            return this.matrix.get(i, j);             //返回矩阵元素[i,j]值。若i、j越界，抛出序号越界异常
        }

        //返回顶点vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。用于7.3节图的遍历算法
        protected int next(int i, int j) {
            int n = this.vertexCount();
            if (i >= 0 && i < n && j >= 0 && j <= n && i != j)
                for (int k = j - 1; k >= 0; k--)                      //当j=-1时，k从0开始寻找后继邻接顶点
                    if (this.matrix.get(i, k) > 0)    //权值表示有边
                        return k;
            return -1;
        }

        public void removeVertex(T vertex)           //删除顶点vertex及其关联的边
        {
            int i = this.vertexlist.search(vertex);    //在顺序表中查找值为vertex的元素，返回序号
            this.removeVertex(i);                    //删除顶点vi及其关联的边
        }
    }

    //7.2.1   图的邻接矩阵表示和实现    //2.  声明抽象图类表示顶点集合
    public static abstract class AbstractGraph<T>                     //抽象图类，T表示顶点元素类型
    {
        protected static final int MAX_WEIGHT = 99999;//0x0000ffff;      //最大权值（表示无穷大∞），不能用Integer.MAX_VALUE;
        protected SeqList<T> vertexlist;                       //顶点顺序表，存储图的顶点集合

        public AbstractGraph(int length)                       //构造空图，顶点数为0，length指定顶点顺序表容量
        {
            this.vertexlist = new SeqList<T>(length);          //构造容量为length的空顺序表。//若length<0，Java抛出负数组长度异常
        }

        public AbstractGraph()                                 //构造空图，顶点数为0
        {
            this(10);                                          //顺序表默认容量为10
        }

        public int vertexCount()                               //返回图的顶点数
        {
            return this.vertexlist.size();                     //返回顶点顺序表的元素个数
        }

        public String toString()                               //返回图的顶点集合描述字符串
        {
            return "顶点集合：" + this.vertexlist.toString() + "\n";
        }

        public T getVertex(int i)                              //返回顶点vi元素
        {
            return this.vertexlist.get(i);                     //若i越界，则返回null
        }//遍历用

        public void setVertex(int i, T x)                      //设置顶点vi元素为x
        {
            this.vertexlist.set(i, x);                          //若i越界，则抛出异常
        }


        //返回vi在vj后的后继邻接顶点序号 ；若j=-1，返回vi的第一个邻接顶点序号；若不存在后继邻接顶点，返回-1。
        protected abstract int next(int i, int j);

        //7.3   图的遍历
        //7.3.1   图的深度优先搜索遍历
        public void DFSTraverse(int i)                         //非连通图的一次深度优先搜索遍历，从顶点vi出发
        {
            boolean[] visited = new boolean[this.vertexCount()]; //访问标记数组，元素初值为false，表示未被访问
            int j = i;
            do {
                if (!visited[j])                               //若顶点vj未被访问。若i越界，Java将抛出数组下标序号越界异常
                {
                    //System.out.print("{ ");
                    this.depthfs(j, visited);                  //从顶点vj出发的一次深度优先搜索
                    //System.out.print("} ");
                }
                j = (j + 1) % this.vertexCount();                //在其他连通分量中寻找未被访问顶点
            } while (j != i);
            System.out.println();
        }

        //从顶点vi出发的一次深度优先搜索，遍历一个连通分量；visited指定访问标记数组。递归算法
        private void depthfs(int i, boolean[] visited) {
            System.out.print(this.getVertex(i) + " ");           //访问顶点vi
            visited[i] = true;  //设置访问标记
            //从一个顶点出发如果有2个以上的顶点可以访问时，先访问编号大的那个顶点
            for (int j = this.next(i, visited.length); j != -1; j = this.next(i, j))//j依次获得vi的所有邻接顶点序号
                if (!visited[j])                                //若邻接顶点vj未被访问
                    depthfs(j, visited);                       //从vj出发的深度优先搜索遍历，递归调用
        }

        //7.3.2   图的广度优先搜索遍历
        public void BFSTraverse(int i)                         //非连通图的一次广度优先搜索遍历，从顶点vi出发
        {
            boolean[] visited = new boolean[this.vertexCount()]; //访问标记数组
            int j = i;
            do {
                if (!visited[j])                               //若顶点vj未被访问
                {
                    breadthfs(j, visited);                     //从vj出发的一次广度优先搜索
                }
                j = (j + 1) % this.vertexCount();                //在其他连通分量中寻找未被访问顶点
            } while (j != i);
            System.out.println();
        }

        //从顶点vi出发的一次广度优先搜索，遍历一个连通分量，使用队列
        private void breadthfs(int i, boolean[] visited) {
            System.out.print(this.getVertex(i) + " ");           //访问顶点vi
            visited[i] = true;                                 //设置访问标记
//	        SeqQueue<Integer> que = new SeqQueue<Integer>(this.vertexCount());   //创建顺序队列
            LinkedQueue<Integer> que = new LinkedQueue<>();   //创建链式队列
            que.add(i);                                        //访问过的顶点vi序号入队，自动转换成Integer(i))
            while (!que.isEmpty())                             //当队列不空时循环
            {
                i = que.poll();                                //出队，自动转换成int;
                //从一个顶点出发如果有2个以上的顶点可以访问时，先访问编号大的那个顶点
                for (int j = next(i, visited.length); j != -1; j = next(i, j))     //j依次获得vi的所有邻接顶点
                    if (!visited[j])                           //若顶点vj未访问过
                    {
                        System.out.print(this.getVertex(j) + " ");//访问顶点vj
                        visited[j] = true;
                        que.add(j);                            //访问过的顶点vj序号入队
//	                    System.out.println("顶点队列："+que.toString());
                    }
            }
        }

    }

    //链式队列类，最终类，实现队列接口，T表示数据元素的数据类型
    static class LinkedQueue<T> {
        private Node<T> front, rear;                           //front和rear分别指向队头和队尾结点

        public LinkedQueue()                                   //构造空队列
        {
            this.front = this.rear = null;
        }

        public boolean isEmpty()                               //判断队列是否空，若空返回true
        {
            return this.front == null && this.rear == null;
        }

        public boolean add(T x)                                //元素x入队，空对象不能入队
        {
            if (x == null)
                return false;
            Node<T> q = new Node<T>(x, null);
            if (this.front == null)
                this.front = q;                                  //空队插入
            else
                this.rear.next = q;                              //队列尾插入
            this.rear = q;
            return true;
        }

        public T peek()                                       //返回队头元素，没有删除。若队列空，则返回null
        {
            return this.isEmpty() ? null : this.front.data;
        }

        public T poll()                                        //出队，返回队头元素，若队列空返回null
        {
            if (isEmpty())
                return null;
            T x = this.front.data;                             //取得队头元素
            this.front = this.front.next;                      //删除队头结点
            if (this.front == null)
                this.rear = null;
            return x;
        }

        public String toString()                               //返回队列所有元素的描述字符串，形式为“(,)”
        {                                                      //算法同不带头结点的单链表
            StringBuffer strbuf = new StringBuffer(this.getClass().getName() + "(");
            for (Node<T> p = this.front; p != null; p = p.next)
                strbuf.append(p.data.toString() + ",");          //按照队列元素次序复制数组元素
            strbuf.setCharAt(strbuf.length() - 1, ')');          //将串最后多余的一个字符','改为')'
            return new String(strbuf);                         //由StringBuffer对象构造String对象
        }

    }

    //图带权值的边类
    public static class Triple implements Comparable<Triple> {
        int row, column, value;     //行号、列号、元素值，默认访问权限

        //行号（边的起点序号），列号（终点序号）、元素值（权值），7.2.2节
        public Triple(int row, int column, int value) {
            if (row >= 0 && column >= 0) {
                this.row = row;
                this.column = column;
                this.value = value;
            } else throw new IllegalArgumentException("行、列号不能为负数：row=" + row + "，column=" + column);
        }

        //构造方法，参数指定行号、列号、元素值。若行号、列号为负，则抛出无效参数异常

        public Triple(Triple tri)                             //拷贝构造方法，复制一个三元组
        {
            this(tri.row, tri.column, tri.value);
        }

        public String toString()                               //返回三元组描述字符串
        {
            return "(" + row + "," + column + "," + value + ")";
        }

        //根据行、列位置比较三元组对象大小，与元素值无关，约定三元组排序次序
        public int compareTo(Triple tri) {
            if (this.row == tri.row && this.column == tri.column)
                return 0;                                      //相等，与equals()方法含义不同
            return (this.row < tri.row || this.row == tri.row && this.column < tri.column) ? -1 : 1;
        }
    }

    public static class SeqList<T> {
        protected Object[] element;                            //对象数组，保护成员
        protected int n;                                       //顺序表元素个数（长度）

        //1. 构造、存取
        public SeqList(int length)                             //构造容量为length的空表
        {
            this.element = new Object[length];                 //申请数组的存储空间，元素为null。
            //若length<0，Java抛出负数组长度异常 java.lang.NegativeArraySizeException
            this.n = 0;
        }

        public boolean isEmpty()                               //判断顺序表是否空，若空返回true，O(1)
        {
            return this.n == 0;
        }

        public int size()                                      //返回顺序表元素个数，O(1)
        {
            return this.n;
        }

        //存取操作
        public T get(int i)                                    //返回第i个元素，0≤i<n。若i越界，返回null。O(1)
        {
            if (i >= 0 && i < this.n)
                return (T) this.element[i];                     //返回数组元素引用的对象，传递对象引用
            return null;
        }

        //设置第i个元素为x，0≤i<n。若i越界，抛出序号越界异常；若x==null，抛出空对象异常。O(1)
        public void set(int i, T x) {
            if (x == null)
                throw new NullPointerException("x==null");     //抛出空对象异常
            if (i >= 0 && i < this.n)
                this.element[i] = x;
            else throw new java.lang.IndexOutOfBoundsException(i + "");//抛出序号越界异常
        }

        public String toString() {
            String str = this.getClass().getName() + "(";          //返回类名
            if (this.n > 0)
                str += this.element[0].toString();             //执行T类的toString()方法，运行时多态
            for (int i = 1; i < this.n; i++)
                str += ", " + this.element[i].toString();        //执行T类的toString()方法，运行时多态
            return str + ") ";                                   //空表返回()
        }


        public int insert(int i, T x) {
            if (x == null)
                throw new NullPointerException("x==null");     //抛出空对象异常
            if (i < 0) i = 0;                                //插入位置i容错，插入在最前
            if (i > this.n) i = this.n;                           //插入在最后
            Object[] source = this.element;                    //数组变量引用赋值，source也引用element数组
            if (this.n == element.length)                        //若数组满，则扩充顺序表的数组容量
            {
                this.element = new Object[source.length * 2];    //重新申请一个容量更大的数组
                for (int j = 0; j < i; j++)                        //复制当前数组前i-1个元素
                    this.element[j] = source[j];
            }
            for (int j = this.n - 1; j >= i; j--)                    //从i开始至表尾的元素向后移动，次序从后向前
                this.element[j + 1] = source[j];
            this.element[i] = x;
            this.n++;
            return i;                                          //返回x序号
        }

        public int insert(T x)                                 //顺序表尾插入x元素，返回x序号。成员方法重载
        {
            return this.insert(this.n, x);                     //插入操作中，this.n加1
        }

        //3. 顺序表的删除操作
        public T remove(int i)          //删除第i个元素，0≤i<n，返回被删除元素。若i越界，返回null。//？？若i越界，抛出序号越界异常
        {
            if (this.n > 0 && i >= 0 && i < this.n) {
                T old = (T) this.element[i];                    //old中存储被删除元素
                for (int j = i; j < this.n - 1; j++)
                    this.element[j] = this.element[j + 1];       //元素前移一个位置
                this.element[this.n - 1] = null;                   //设置数组元素对象为空，释放原引用实例
                this.n--;
                return old;                                    //返回old局部变量引用的对象，传递对象引用
            }
            return null;
        }


        public int search(T key) {
            for (int i = 0; i < this.n; i++) {
                if (key.equals(this.element[i]))               //执行T类的equals(Object)方法，运行时多态
                    return i;
            }
            return -1;                                         //空表或未找到时
        }


        public T remove(T key)                                 //删除首个与key相等元素，返回被删除元素；查找不成功返回null
        {
            return this.remove(this.search(key));              //先查找，再调用remove(i)。若查找不成功，返回-1，则不删除
        }


    }

    public static class Matrix                                        //矩阵类
    {
        protected int rows, columns;                           //矩阵行数、列数
        protected int[][] element;                             //二维数组，存储矩阵元素

        public Matrix(int m, int n)                            //构造m×n零矩阵。若m或n为负数，Java抛出负数组长度异常
        {
            this.element = new int[m][n];                      //数组元素初值为0
            this.rows = m;
            this.columns = n;
        }

        public Matrix(int n)                                   //构造n×n零方阵
        {
            this(n, n);
        }

        public Matrix(int m, int n, int[][] value)             //构造m×n矩阵，由value[][]提供元素
        {
            this(m, n);
            for (int i = 0; i < value.length && i < m; i++)          //value元素不足时补0，忽略多余元素
                for (int j = 0; j < value[i].length && j < n; j++)
                    this.element[i][j] = value[i][j];
        }

        public int getRows()                                   //返回矩阵行数
        {
            return this.rows;
        }

        public int getColumns()                                //返回矩阵列数
        {
            return this.columns;
        }

        public int get(int i, int j)                 //返回矩阵第i行第j列元素。若i、j越界，抛出序号越界异常
        {
            if (i >= 0 && i < this.rows && j >= 0 && j < this.columns)
                return this.element[i][j];
            throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
        }

        public void set(int i, int j, int x)         //设置矩阵第i行第j列元素为x。若i、j越界，抛出序号越界异常
        {
            //System.out.println("row:"+rows+" colums:"+columns);
            if (i >= 0 && i < this.rows && j >= 0 && j < this.columns)
                this.element[i][j] = x;
            else throw new IndexOutOfBoundsException("i=" + i + "，j=" + j);
        }

        public String toString()                               //返回矩阵所有元素的描述字符串，行主序遍历
        {
            String str = " 矩阵" + this.getClass().getName() + "（" + this.rows + "×" + this.columns + "）：\n";
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++)
                    str += String.format("%6d", this.element[i][j]); //"%6d"格式表示十进制整数占6列
                str += "\n";
            }
            return str;
        }

        //设置矩阵为m行n列。若参数指定行列数较大，则将矩阵扩容，并复制原矩阵元素。
        //用于7.2.1节图的邻接矩阵存储结构
        public void setRowsColumns(int m, int n) {
            if (m > 0 && n > 0) {
                if (m > this.element.length || n > this.element[0].length) {                                                  //参数指定的行数或列数较大时，扩充二维数组容量
                    int[][] source = this.element;
                    this.element = new int[m * 2][n * 2];              //重新申请二维数组空间，元素初值为0
                    for (int i = 0; i < this.rows; i++)                //复制原二维数组元素
                        for (int j = 0; j < this.columns; j++)
                            this.element[i][j] = source[i][j];
                }
                this.rows = m;
                this.columns = n;
            } else throw new IllegalArgumentException("矩阵行列数不能≤0，m=" + m + "，n=" + n);
        }
    }

    public static class Node<T>                             //单链表结点类，T指定结点的元素类型
    {
        public T data;                               //数据域，存储数据元素
        public Node<T> next;                         //地址域，引用后继结点

        public Node(T data, Node<T> next)            //构造结点，data指定数据元素，next指定后继结点
        {
            this.data = data;                        //T对象引用赋值
            this.next = next;                        //Node<T>对象引用赋值
        }

        public Node() {
            this(null, null);
        }

        public String toString()                     //返回结点数据域的描述字符串
        {
            return this.data.toString();
        }
    }

}
