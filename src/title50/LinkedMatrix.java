package title50;//�����ݽṹ��Java�棩����5�棩�������ߣ�Ҷ���ǣ�2014��7��17��
//��5.2.2 ϡ������ѹ���洢
//4.  ϡ������еĵ�����
//��7.2.1 ͼ���ڽӱ��ʾ��ʵ��  ��
//��ʵ����5-3��



//��1�������еĵ����������
public class LinkedMatrix                                  //��Ԫ���еĵ�����洢�ľ�����
{
    //private 
    int rows, columns;                             //��������������
    SeqList<SortedSinglyList<Triple>> rowlist;     //��ָ��˳���Ԫ������������Ĭ��Ȩ��
//    SeqList<PolySinglyList<Triple>> rowlist;     //��ָ��˳���Ԫ���Ƕ���ʽ��������Ĭ��Ȩ��
    //private //���ϲ��ܣ���Ϊ7.2.2�� ͼ���ڽӱ� Ҫ�����ߵ�����
//    SeqList<SortedSeqList<Triple>> rowlist;     //��ָ��˳���Ԫ��������˳���

    public LinkedMatrix(int m, int n)            //����m��n�������m��n��0���׳���Ч�����쳣
    {
        if (m>0 && n>0)
        {
            this.rows = m;
            this.columns = n;
            this.rowlist = new SeqList<SortedSinglyList<Triple>>();  //����˳���Ĭ����������ֵΪnull
//            this.rowlist = new SeqList<PolySinglyList<Triple>>();  //����˳���Ĭ����������ֵΪnull
            for (int i=0; i<m; i++)                                  //˳�������m���յ�����
                this.rowlist.insert(new SortedSinglyList<Triple>()); //˳���β���룬�Զ�����
//              this.rowlist.insert(new PolySinglyList<Triple>()); //˳���β���룬�Զ�����
        }
        else throw new IllegalArgumentException("�������������ܡ�0��m="+m+"��n="+n);
    }
    public LinkedMatrix(int m)                             //����m��m�����
    {
        this(m, m);
    }
    
    public LinkedMatrix(int m, int n, Triple[] tris)       //����m��n��������Ԫ������tris�ṩ�����ֵ
    {
        this(m, n);
        for (int i=0; i<tris.length; i++)
            this.set(tris[i]);                             //�����������һ��Ԫ�ص���Ԫ��
    }

    public int getRows()                                   //���ؾ���������������ʡ��
    {
        return this.rows;        
    }
    public int getColumns()                                //���ؾ���������������ʡ��
    {
        return this.columns;        
    }
       
    //��2�� ���ؾ���Ԫ��
    //���ؾ����i�е�j��Ԫ�ء���i��jԽ�磬���׳����Խ���쳣�������㷨�Ƚ���Ԫ���С
    public int get(int i, int j)
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
            //�ڵ�i������������˳�������Ԫ��(i,j,0)���Ƚ���Ԫ���С
            Node<Triple> find=this.rowlist.get(i).search(new Triple(i,j,0));
            return (find!=null) ? find.data.value : 0;     //�����ҳɹ�������Ԫ��ֵ�����򷵻�0
        }
        throw new IndexOutOfBoundsException("i="+i+"��j="+j);
    }
    
    //��3�� ���þ���Ԫ��
    //���þ����i�е�j��Ԫ��Ϊx����i��jԽ�磬���׳����Խ���쳣��
    //���ҡ����롢ɾ���㷨���Ƚ���Ԫ���С
    public void set(int i, int j, int x)
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
            SortedSinglyList<Triple> link = this.rowlist.get(i);//��õ�i����������
            if (x==0) 
                link.remove(new Triple(i,j,0));  //�����ҳɹ���ɾ��(i,j,?)���
            else
            {   //���²����ٲ�����滻Ԫ�ز���������link�����������
                Triple tri = new Triple(i,j,x);
                Node<Triple> find=link.search(tri); //˳�����tri������Ԫ��>tri������Ҳ��ɹ�
                if (find!=null)
                    find.data.value = x;                   //���ҳɹ����޸��޸�find���ö���ĳ�Ա����ֵ
                else link.insert(tri);                     //���Ҳ��ɹ�����������link��(i,j)���������Ԫ��tri

/*                //Ҳ�ɣ�һ�α���
                Node<Triple> find = link.insertDifferent(new Triple(i,j,x));//���벻�ظ�Ԫ�أ����ز��ҵ�������� 
                if (find.data.value!=x)          //���������ز��ҵ���㣬�޸ĸý��Ԫ��
                    find.data.value = x;
*/
            }
        }
        else throw new IndexOutOfBoundsException("i="+i+"��j="+j);//�׳����Խ���쳣
    }
    
    public void set(Triple tri)                  //����Ԫ��tri���þ���Ԫ��
    {
        this.set(tri.row, tri.column, tri.value);
    }

    //��4�� �������
    public String toString()                               //����ϡ�������Ԫ���еĵ����������ַ���
    {
        String str="";
        for (int i=0; i<this.rowlist.size(); i++)         //ѭ������Ϊ��ָ��˳�����
            str += i+" -> "+this.rowlist.get(i).toString()+"\n";//��õ�i����������������ַ���
        return str;
    }
        
    public void printMatrix()                               //�������
    {
        System.out.println("����"+this.getClass().getName()+"��"+rows+"��"+columns+"����");
//        for (int i=0; i<this.rowlist.size(); i++)
        for (int i=0; i<this.rows; i++)
        {
            Node<Triple> p = this.rowlist.get(i).head.next;//������i����������
            for (int j=0; j<this.columns; j++)
               if (p!=null && j==p.data.column)            //��i==p.data.row 
               {
                   System.out.print(String.format("%4d", p.data.value));
                   p = p.next;
               }               
               else System.out.print(String.format("%4d", 0));
            System.out.println();
        }   
    }
    
    //���У�Ч�ʵͣ����ڲ���get(i,j)�����Ƿ���ȷ
    public void printMatrix2()                             //�������
    {
        System.out.println("����"+this.getClass().getName()+"��"+rows+"��"+columns+"����");
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                System.out.print(String.format("%4d", this.get(i,j)));
            System.out.println();
        }
    } 

    //��5�� �ȽϾ����Ƿ����
    public boolean equals(Object obj)                      //�Ƚ����������Ƿ���ȣ��㷨ͬSeqSparseMatrix��
    {
        if (this==obj)
            return true;
        if (!(obj instanceof LinkedMatrix))
            return false;
        LinkedMatrix mat=(LinkedMatrix)obj;        
        return this.rows==mat.rows && this.columns==mat.columns && this.rowlist.equals(mat.rowlist);
                                                           //�Ƚ�������Ԫ��˳����Ƿ����
    }
    
    //��6�� ���þ���������
    //���þ���Ϊm��n�С���mָ�������ϴ�����ָ��˳������ݣ�ʹ��ԭ���е�����
    //����7.2.2��ͼ���ڽӱ�洢�ṹ��
    public void setRowsColumns(int m, int n)
    {
        if (m>0 && n>0)
        {
            if (m > this.rows)                             //��m����ָ�������ϴ�
                for (int i=this.rows; i<m; i++)
//                    this.rowlist.insert(new SortedSinglyList<Triple>());//˳���β���룬�Զ�����
                  this.rowlist.insert(new PolySinglyList<Triple>());//˳���β���룬�Զ�����
            this.rows = m;
            this.columns = n;
        }
        else throw new IllegalArgumentException("�������������ܡ�0��m="+m+"��n="+n);
    }

    //��7�� �������
    public void addAll(LinkedMatrix mat)
    {
        if (this.rows==mat.rows && this.columns==mat.columns)
            for (int i=0; i<this.rows; i++)
                this.rowlist.get(i).addAll(mat.rowlist.get(i));        //���ö���ʽ����������㷨
        else throw new IllegalArgumentException("�������������ͬ���������");
    }
    //����5.2.2��
    
    //���¡�ʵ����5-3��
    //�������
    //�������췽������ȿ���������˳�������˳��������е������������н���Ԫ�ض���
    public LinkedMatrix(LinkedMatrix mat)
    {
        this(mat.rows, mat.columns);             //����rows��columns���������ָ��˳�����rows���յ�����
        for (int i=0; i<this.rows; i++)   
        {
            SortedSinglyList<Triple> link=new SortedSinglyList<Triple>(mat.rowlist.get(i));
//            PolySinglyList<Triple> link = new PolySinglyList<Triple>(mat.rowlist.get(i));
                                       //����SortedSinglyList(SortedSinglyList<T> list)��O(n) 
                                       //��������������������н�㣬û�и���Ԫ�ض���                
            for (Node<Triple> p=link.head.next;  p!=null;  p=p.next) 
                p.data = new Triple(p.data);     //����һ���������и�������õ�Ԫ�ض���
            this.rowlist.set(i, link);           //�����ƺ�ĵ���������Ϊ˳����i��Ԫ��
        }
    }
    
    public LinkedMatrix union(LinkedMatrix mat)  //����this+mat��ӵľ��󣬲��ı�this��mat
    {
        LinkedMatrix matc=new LinkedMatrix(this);//��ȿ���
        matc.addAll(mat);
        return matc;                             //���ض�������
    }
    
    public LinkedMatrix transpose()                        //����this��ת�þ���
    {
        LinkedMatrix trans=new LinkedMatrix(columns, rows);//����columns��rows�������
        //����ÿ����������������Ԫ��ĶԳ�Ԫ�أ����뵽trans����
        for (int i=0; i<this.rows; i++)
            for (Node<Triple> p=this.rowlist.get(i).head.next; p!=null; p=p.next)
                trans.set(p.data.toSymmetry());
        return trans;
    }    
}    
//@author��Yeheya��2015-10-12
    
    //��10�£�10.2 ʵ�ֵ�����
    //10.2.1   �ṩ����������
    //����10.2��  ʹ�õ���������˳���͵�����
/*    public void printMatrix()                    //�������ʹ��˳���͵�����ĵ���������
    {
        System.out.println("����"+this.getClass().getName()+"��"+rows+"��"+columns+"����");
//        java.util.Iterator<PolySinglyList<Triple>> seqit = this.rowlist.iterator();//���˳��������
//        while (seqit.hasNext())                           //����˳���
        for (PolySinglyList<Triple> link : this.rowlist)  //��Ԫѭ����link���rowlist�����е�ÿ��Ԫ��
        {
//            java.util.Iterator<Triple> it = seqit.next().iterator();//��õ�ǰ�е�����ĵ�����
            java.util.Iterator<Triple> it = link.iterator();//��õ�����ĵ�����
            Triple triple= it.hasNext() ? it.next() : null;//��õ������ĵ�1��Ԫ��
            for (int j=0; j<this.columns; j++)             //������i����������
                if (triple!=null && triple.column==j)      //����Ԫ�飬��0Ԫ��
                {
                    System.out.print(String.format("%4d", triple.value));
                    triple= it.hasNext() ? it.next() : null;//��õ���������һ��Ԫ��
                }
                else System.out.print(String.format("%4d", 0));
            System.out.println();
        }   
    } 
}

    //ʹ�õ��������������������ɾ�������滻δ�ɹ�����Ϊ�����ҵ���Ԫ��Ҫ�滻�������ǰ�����
/*    public void set(Triple tri)        //����Ԫ��tri���þ���Ԫ�ء���tri����/�����Խ�磬�׳����Խ���쳣
    {
        int i=tri.row, j=tri.column;
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
        {
//            SortedSinglyList<Triple> link = this.rowlist.get(i);//��õ�i����������
            java.util.Iterator<Triple> it = this.rowlist.get(i).iterator();//��õ�i����������ĵ���������
            while (it.hasNext())                           	//���к��Ԫ�أ�ʹ�õ���������һ������
//          System.out.print(it.next().toString()+" ");       	//���غ��Ԫ��
            if (tri.value==0) 
                it.remove();                          //ɾ��(i,j,?)��㣨˳����ң�����У�
//            link.remove(tri);                          //ɾ��(i,j,?)��㣨˳����ң�����У�
            else
            {
                Node<Triple> find=link.search(tri);        //˳������״γ���Ԫ��
                if (find!=null)
                    find.data.value = tri.value;           //���ҳɹ����޸ľ���Ԫ��ֵ
                else link.insert(tri);                     //���Ҳ��ɹ�����(i,j)�������tri��// ���α���

//Ҳ�ɣ�һ�α���
//                Node<Triple> find = link.insertUnrepeatable(tri);    //���벻�ظ�Ԫ��x������x���
//                find.data.value = tri.value;      //�޸ĸý��Ԫ��
            }
        }
        else throw new IndexOutOfBoundsException("i="+i+"��j="+j);//�׳����Խ���쳣
    }*/
    
//@author��Yeheya��2014-10-31

