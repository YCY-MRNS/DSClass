package title10;

import java.util.Scanner;

/**
 * @program: DSClass
 * @description: 10:顺序表的合并
 * @author: ChangYue
 * @create: 2019-06-21 10:37
 */
public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int aLen = in.nextInt();
        int bLen = in.nextInt();

        SeqList<Integer> l1 = new SeqList<>(aLen);
        SeqList<Integer> l2 = new SeqList<>(bLen);

        for (int i = 0; i < aLen; i++) {
            l1.insert(in.nextInt());
        }

        for (int i = 0; i < bLen; i++) {
            l2.insert(in.nextInt());
        }

        l1.addAll(l2);

        int temp;
        for (int i = 0; i < l1.size(); i++) {
            for (int j = 0; j < l1.size(); j++) {
                if (l1.get(i) < l1.get(j)) {
                    temp = l1.get(j);
                    l1.set(j, l1.get(i));
                    l1.set(i, temp);
                }
            }
        }
        System.out.println(l1);
    }
}


class SeqList<T> {
    protected Object[] element;                            //对象数组，保护成员
    protected int n;                                       //顺序表元素个数（长度）

    public SeqList(int length)                             //构造容量为length的空表
    {
        this.element = new Object[length];                 //申请数组的存储空间，元素为null。
        this.n = 0;
    }

    public SeqList()                                       //创建默认容量的空表，构造方法重载
    {
        this(64);                                          //调用本类已声明的指定参数列表的构造方法
    }

    public SeqList(T[] values)                             //构造顺序表，由values数组提供元素，忽略其中空对象
    {
        this(values.length);                               //创建容量为values.length的空表
        //若values==null，用空对象调用方法，Java抛出空对象异常NullPointerException
        for (int i = 0; i < values.length; i++)                //复制数组元素，O(n)
            this.element[i] = values[i];                   //对象引用赋值
        this.n = element.length;

//也可              this.insert(values[i]);                          //尾插入，this.n++。暂且不用，因为还没有讲到

//也可        for (T x : values)                                  //数组逐元循环
//            this.insert(x);                                //尾插入，this.n+1
    }

/*    //第5版
    public SeqList(T[] values)                            //构造顺序表，由values数组提供元素，忽略其中空对象
    {
        this(values.length*2);                             //创建2倍values数组容量的空表
                   //若values==null，用空对象调用方法，Java抛出空对象异常NullPointerException
        for (int i=0; i<values.length; i++)                //复制非空的数组元素。O(n)
            if (values[i]!=null)
                this.element[this.n++] = values[i];        //对象引用赋值
    }*/


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
//      return this.element[i];                            //编译错，Object对象不能返回T对象
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

    //返回顺序表所有元素的描述字符串，形式为“(,)”。覆盖Object类的toString()方法
    public String toString() {
        String str = this.getClass().getName() + "(";          //返回类名
//        String str="(";
        if (this.n > 0)
            str += this.element[0].toString();             //执行T类的toString()方法，运行时多态
        for (int i = 1; i < this.n; i++)
            str += ", " + this.element[i].toString();        //执行T类的toString()方法，运行时多态
        return str + ") ";                                   //空表返回()
    }

    /*    可行，效率同上
        public String toString()
        {
            String str="(";
            if (this.n()!=0)
            {
                for(int i=0; i<this.n()-1; i++)
                    str += this.get(i).toString()+", ";
                str += this.get(this.n()-1).toString();
            }
            return str+")";
        }
    */
    //返回线性表所有元素的描述字符串（元素次序从后向前），O(n)。必需，优先队列用。方法体省略
    public String toPreviousString() {
        return "";
    }

    //2. 顺序表的插入操作
    //插入x作为第i个元素，x!=null，返回x序号。若x==null，抛出空对象异常。O(n)
    //对序号i采取容错措施，若i<0，插入x在最前；若i>n，插入x在最后
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
        //throw new IndexOutOfBoundsException(i+"");         //抛出序号越界异常
    }

    public void clear()                                    //删除线性表所有元素
    {
        this.n = 0;                                          //设置长度为0，未释放数组空间
    }
    //【例2.1】求解Josephus环问题。

    //4. 顺序查找
    //顺序查找首次出现的与key相等元素，返回元素序号i，0≤i<n；查找不成功返回-1。
    //若key==null，Java抛出空对象异常NullPointerException
    public int search(T key) {
//      System.out.print(this.getClass().getName()+".indexOf("+key+","+start+")，");
        for (int i = 0; i < this.n; i++) {
//          System.out.print(this.element[i]+"？");
            if (key.equals(this.element[i]))               //执行T类的equals(Object)方法，运行时多态
                return i;
        }
        return -1;                                         //空表或未找到时
    }//不能用逐元循环，无法返回i

    public boolean contains(T key)                         //判断是否包含关键字为key元素
    {
        return this.search(key) != -1;
    }

    //【思考题2-2】基于查找的操作
    public T remove(T key)                                 //删除首个与key相等元素，返回被删除元素；查找不成功返回null
    {
        return this.remove(this.search(key));              //先查找，再调用remove(i)。若查找不成功，返回-1，则不删除
    }

    //5.  顺序表的浅拷贝与深拷贝
/*    public SeqList(SeqList<T> list)                      //浅拷贝构造方法
    {
        this.n = list.n;                                   //int整数赋值，复制了整数值
        this.element = list.element;                       //数组引用赋值，两个变量共用一个数组，错误
    }*/
    public SeqList(SeqList<? extends T> list)              //拷贝构造方法，深拷贝，复制list
    {
//      super();                                           //默认调用，执行Object()
        this.n = list.n;
        this.element = new Object[list.element.length];    //申请一个数组
        for (int i = 0; i < list.n; i++)                       //复制list所有元素，O(n)
            this.element[i] = list.element[i];             //对象引用赋值，没有创建新对象
//也可，如果              this.insert(values[i]);                     //尾插入，this.n++
        //子类继承，运行时多态，则子类声明拷贝构造方法SortedSeqList(SeqList<? extends T> list)，算法相同

//          this.element[i] = new T(list.element[i]);    //语法错，因为Java没有提供默认拷贝构造方法
//      this.element[i] = new Object(list.element[i]);    //语法错，因为Object没有提供拷贝构造方法，且构造方法不能继承
    }
    //第5版
/*    public SeqList(SeqList<? extends T> list)            //拷贝构造方法，深拷贝，复制list
    {
        this(list.element.length);                         //构造空顺序表
        for (int i=0; i<list.n; i++)                       //复制list所有元素，O(n)
            this.insert((T)list.element[i]);               //尾插入，this.n++
//也可        this.addAll(list);
//          this.element[i] = new T(list.element[i]);    //语法错，因为Java没有提供默认拷贝构造方法
//      this.element[i] = new Object(list.element[i]);    //语法错，因为Object没有提供拷贝构造方法，且构造方法不能继承
    }*/


    //6. 顺序表比较相等
    public boolean equals(Object obj)                      //比较两个顺序表是否相等。覆盖。O(n)
    {
        if (this == obj)                                     //若this和obj引用同一个顺序表实例，则相等
            return true;
        if (obj instanceof SeqList<?>)                     //若obj引用顺序表实例。SeqList<?>是所有SeqList<T>的父类
        {
            SeqList<T> list = (SeqList<T>) obj;             //声明list也引用obj引用的实例
            if (this.n == list.n)                            //比较两者长度是否相等
            {
                for (int i = 0; i < this.n; i++)               //比较两个顺序表的所有元素是否相等
                    if (!(this.get(i).equals(list.get(i))))//equals(Object)运行时多态
                        return false;
                return true;
            }
        }
        return false;
    }
    //以上实现ADT List，第2章2.2.2节内容

    //【思考题2-2】【实验2-1】  顺序表增加成员方法（也可以声明子类DifferentSeqList实现）
/*    public int insertDifferent(int i, T x)       //插入不重复元素。查找不成功时，插入
    {
        return this.search(x)==-1 ? this.insert(i,x) : -1;
    }
    public int insertDifferent(T x)              //插入不重复元素。查找不成功时，尾插入
    {
        return this.search(x)==-1 ? this.insert(x) : -1;
    }*/
    //【实验题2-1】互异，基于查找算法
    public boolean isDifferent()                           //判断是否互异
    {
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < i; j++)                        //与前i个元素比较是否相等。若调用查找（部分）方法，算法也不清楚
                if (this.element[j].equals(this.element[i]))
                    return false;
        return true;
    }

    //【实验2-1】
    public void removeAll(T key)                           //删除所有与key相等元素，O(n)。算法每元素移动多次
    {
        int i = 0;
        while (i < this.n)
            if (key.equals(this.element[i]))
                this.remove(i);                            //查找到删除，this.n减1，i不变
            else i++;
    }//不能用for语句？？改进，移动一次

    public void replaceFirst(T key, T x)                   //将首次出现的与key相等元素替换为x，O(n)
    {
        if (key != null && x != null) {
            int i = this.search(key);                      //查找key首次出现位置
            if (i != -1)
                this.element[i] = x;                       //查找到，替换
        }
    }

    public void replaceAll(T key, T x)                     //将所有与key相等元素替换为x，O(n)
    {
        if (key != null && x != null)
            for (int i = 0; i < this.n; i++)
                if (key.equals(this.element[i]))
                    this.element[i] = x;                   //查找到，替换
    }

    public int searchLast(T key)                           //返回元素key最后出现位置，若未找到返回-1
    {
        for (int i = this.n - 1; i >= 0; i--)
            if (key.equals(this.element[i]))
                return i;
        return -1;                                         //空表、key为空对象或未找到时
    }

    //判断当前顺序表是否排序（升序）。T或其某个祖先类必须实现Comparable<T>接口
    public <T extends java.lang.Comparable<? super T>> boolean isSorted() {
        for (int i = 0; i < this.n; i++)
            if (((T) this.element[i]).compareTo((T) this.element[i + 1]) > 0)
                return false;
        return true;
    }


    //【思考题2-3】【例2.5】  对子集操作，实现Set<T>
    //子类继承，不需要覆盖，插入运行时多态
    //在this顺序表之后添加list所有元素，集合并运算；
    //? extends T 表示顺序表元素支持T的子类实例
    public void addAll(SeqList<? extends T> list) {
        for (int i = 0; i < list.n; i++)
            this.insert(list.get(i));               //运行时多态，顺序表尾插入；排序顺序表按值插入
        //this.insert((T)list.element[i]);               //运行时多态，顺序表尾插入；排序顺序表按值插入
    }
//如果  public void addAll(SeqList<T> list)          //则不接受T的子类
//如果  public void addAll(SeqList<?> list)          //则调用this.insert(x)，编译错
//如果  public void insert(SeqList<T> list)          //重载，调用时，当参数为null时，编译错，无法识别重载方法中的哪一个


/*  //子类不支持
    public void insert(int i,SeqList<T> list)             //将list所有元素添加到当前顺序表最后//顺序表连接，首尾相接
    {
        for (int j=0; j<list.n; j++)
            this.insert(i,list.get(j));                    //运行时多态，顺序表尾插入；排序顺序表按值插入
    }*/

    //【思考题2-4】习题解答
    public SeqList<T> union(SeqList<? extends T> list)     //返回在this顺序表之后连接list的顺序表，不改变this和list
    {
        SeqList<T> result = new SeqList<T>(this);          //深拷贝this，无法创建子类实例   //反射?
        result.addAll(list);                               //顺序表合并连接，尾插入
        return result;                                     //只能返回SeqList<T>对象

//不行        return new SeqList<T>(this).addAll(list);
    }
    //9.5.1 子类mergeAll合并、归并，不用，不声明

    boolean containsAll(SeqList<? extends T> list)  //判断是否包含set的所有元素（是否子集）
    {
        for (int i = 0; i < list.n; i++)
            if (!this.contains(list.get(i)))
                return false;
        return true;
    }
//不能    boolean containsAll(SeqList<?> list)

    boolean removeAll(SeqList<? extends T> list)           //删除也包含在set的元素，集合差
    {
        T old = null;
        for (int i = 0; i < list.n; i++)
            old = this.remove(list.get(i));
        return old != null;
    }

    boolean retainAll(SeqList<? extends T> list)           //仅保留那些也包含在set的元素，集合差
    {
        return false;
    }

    //返回从begin～end元素组成的子表。意为返回T的某个子类
//    SeqList<? extends T> subList(int begin, int end)   //不需要该语法
    SeqList<T> subList(int begin, int end)             //返回从begin～end组成的子表。含义与上同
    {
        SeqList<T> list = new SeqList<T>();
        for (int i = begin; i < end; i++)
            list.insert(this.get(i));
        return list;
    }


}
