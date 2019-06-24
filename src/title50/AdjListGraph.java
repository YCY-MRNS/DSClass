package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��31��
//7.2.2   ͼ���ڽӱ��ʾ��ʵ��

//�ڽӱ��ʾ�Ĵ�Ȩͼ�࣬T��ʾ����Ԫ�����ͣ��̳г���ͼ��
public class AdjListGraph<T> extends AbstractGraph<T>
{
    protected LinkedMatrix adjlist;                        //ͼ���ڽӱ��ṹͬ�����еĵ�����
    
    //�����ͼ��������Ϊ0������Ϊ0��lengthָ������˳����������ڽӱ�����
    public AdjListGraph(int length)
    {
        super(length);                                     //��������Ϊlength�Ŀ�˳���
        this.adjlist = new LinkedMatrix(length, length);   //����length��length����
    }
    
    //���¹��췽���ķ�����ͬMatrixGraph�࣬ʡ��
    public AdjListGraph()                                  //�����ͼ��������Ϊ0������Ϊ0
    {
        this(10);                                          //˳�����ڽӾ����Ĭ������Ϊ10
    }
    public AdjListGraph(T[] vertices)                      //��vertices���㼯�Ϲ���ͼ������Ϊ0
    {
        this(vertices.length);                             //����ָ�������Ŀ�ͼ
        for (int i=0; i<vertices.length; i++)
            this.insertVertex(vertices[i]);                //����һ������
    } 
    public AdjListGraph(T[] vertices, Triple[] edges)      //��vertices���㼯�Ϻ�edges�߼��Ϲ���ͼ
    {
        this(vertices);
        for (int j=0; j<edges.length; j++)
            this.insertEdge(edges[j]);                     //����һ����
    }

    public String toString()                               //����ͼ�Ķ��㼯�Ϻ��ڽӱ������ַ���
    {
        return super.toString()+"���߱�\n"+this.adjlist.toString();
    }

    //��1�������
    public void insertEdge(int i, int j, int weight)       //����ߡ�vi,vj����ȨֵΪweight
    {
        if (i!=j)                                          //���ܱ�ʾ����
        {
            if (weight<0 || weight>=MAX_WEIGHT)            //�ߵ�Ȩֵ�ݴ���Ϊ�ޱߣ�ȡֵ0
                weight=0;
            this.adjlist.set(i,j,weight);                  //���õ�i���ߵ������С�vi,vj���ߵ�ȨֵΪweight��
            //��0<weight<�ޣ�����߻��滻�ߵ�Ȩֵ����weight==0��ɾ���ñߡ���i��jԽ�磬�׳����Խ���쳣
        }
        else throw new IllegalArgumentException("���ܲ���������i="+i+"��j="+j);
    }
    public void insertEdge(Triple edge)                    //����һ���ߡ�������ͬͼ���ڽӾ���ʡ��
    {
        this.insertEdge(edge.row, edge.column, edge.value);
    }
    
    //��2�����붥��
    public int insertVertex(T x)                           //����Ԫ��Ϊx�Ķ��㣬����x�������
    {
        int i = this.vertexlist.insert(x);                 //����˳���β���붥��x������x������ţ����ȼ�1���Զ�����
        if (i >= this.adjlist.getRows())                   //���ڽӱ�����������
            this.adjlist.setRowsColumns(i+1, i+1);         //�����ݣ������ڽӱ�����ͬͼ�Ķ�����
        return i;                                          //���ز��붥�����
    }
    
    //��3��ɾ����
    public void removeEdge(int i, int j)                   //ɾ��һ���ߡ�vi,vj��������Ȩֵ
    {
        if (i!=j)
            this.adjlist.set(new Triple(i,j,0));           //���ñߵ�ȨֵΪ0�����ڵ�i���ߵ�������ɾ���߽��
    }
    public void removeEdge(Triple edge)                    //ɾ��һ���ߡ�������ͬͼ���ڽӾ���ʡ��
    {
        this.removeEdge(edge.row, edge.column);
    }

    //��4��ɾ������
    public void removeVertex(int i)                        //ɾ������vi��������ı�
    {
        int n=this.vertexCount();                          //ɾ��֮ǰ�Ķ�����
        if (i>=0 && i<n)
        {
            //ɾ�����i���ߵ����������н��ԳƵıߣ����ڵ�i������ıߵ������У�ɾ��������iΪ�յ�ı�
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i);
            for (Node<Triple> p=link.head.next; p!=null; p=p.next)  //������i���ߵ�����
                this.removeEdge(p.data.toSymmetry());               //ɾ����p���ԳƵı�

            n--;                                 //��������1
            this.adjlist.rowlist.remove(i);      //ɾ����ָ��˳���ĵ�i���ߵ����������������
            this.adjlist.setRowsColumns(n, n);   //���þ�������������һ��

            for (int j=0; j<n; j++)                        //����ÿ���ߵ�������>i�Ķ�����ż�1
            {
                link = this.adjlist.rowlist.get(j);
                for (Node<Triple> p=link.head.next; p!=null; p=p.next)//������j���ߵ�����
                {   if (p.data.row > i)
                        p.data.row--; 
                    if (p.data.column > i)
                        p.data.column--;
                }
            }
            this.vertexlist.remove(i);           //ɾ������vi��i�󶥵���ż�1��ͼ��������1
        }
        else throw new IndexOutOfBoundsException("i="+i);//�׳����Խ���쳣
    }
    
    //��5�� ����ڽӶ���ͱߵ�Ȩֵ����
    public int weight(int i, int j)                  //����<vi,vj>�ߵ�Ȩֵ������ͼ����С�����������·�����㷨
    {     
        if (i==j)
            return 0;
        int weight = this.adjlist.get(i,j);         //���ؾ���Ԫ��[i,j]ֵ����i��jԽ�磬�׳����Խ���쳣
        return weight!=0 ? weight : MAX_WEIGHT;     //������0��ʾû�бߣ���ߵ�Ȩֵ���ء�
    }
    
    //���ض���vi��vj��ĺ���ڽӶ�����ţ���j=-1������vi�ĵ�һ���ڽӶ�����ţ��������ں���ڽӶ��㣬����-1������7.3��ͼ�ı����㷨
    protected int next(int i, int j)
    {
        int n=this.vertexCount();
        if (i>=0 && i<n && j>=-1 && j<n && i!=j)
        {
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i);//��i����������
            Node<Triple> find=link.head.next;          //�������0��Ԫ��
            if (j==-1)
                return find!=null ? find.data.column : -1; //���ص�һ���ڽӶ�������
            find = link.search(new Triple(i,j,0));     //˳�����<vi,vj>�ߵĽ��
            if (find!=null)                            //���ҳɹ�
            {
                find = find.next;                      //���<vi,vj>�ߵĺ�̽��
                if (find!=null)                
                    return find.data.column;           //���غ���ڽӶ������
            }
        }
        return -1;
    }
/*
    //��10�£�10.2 ʵ�ֵ�����
    //10.2.2   ���ڵ������Ĳ���
    //��˼����10-4��
    public void removeVertex(int i)              //ɾ������vi��������ıߡ�ʹ�õ����������������û���õ�ɾ��
    {
        int n=this.vertexCount();                          //ɾ��֮ǰ�Ķ�����
        if (i>=0 && i<n)
        {
            //ɾ�����i���ߵ����������н��ԳƵıߣ����ڵ�i������ıߵ������У�ɾ��������iΪ�յ�ı�
            SortedSinglyList<Triple> link = this.adjlist.rowlist.get(i);//��õ�i����������
            java.util.Iterator<Triple> it = link.iterator();//��õ��������������
            while (it.hasNext())                           //������i���ߵ�����
                this.removeEdge(it.next().toSymmetry());   //ɾ����p���ԳƵı�

            n--;                                           //��������1
            this.adjlist.rowlist.remove(i);                //ɾ����ָ��˳���ĵ�i���ߵ����������������
            this.adjlist.setRowsColumns(n, n);             //���þ�������������һ��

            for (int j=0; j<n; j++)                        //����ÿ���ߵ�������>i�Ķ�����ż�1
            {
                link = this.adjlist.rowlist.get(j);
//                for (Node<Triple> p=link.head.next; p!=null; p=p.next)//������j���ߵ�����
                it = link.iterator();                      //��õ�i�������������������
                while (it.hasNext())                       //������i���ߵ�����
                {
                    Triple tri= it.next();
                    if (tri.row > i)
                        tri.row--; 
                    if (tri.column > i)
                        tri.column--;
                }
            }
            this.vertexlist.remove(i);                     //ɾ������vi��i�󶥵���ż�1��ͼ��������1
        }
        else throw new IndexOutOfBoundsException("i="+i);  //�׳����Խ���쳣
    }*/
}
//@author��Yeheya��2015-3-15