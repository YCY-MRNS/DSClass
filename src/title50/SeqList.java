package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��6��22��
//��2.2  ���Ա��˳��洢��ʵ��
//��2.2.2  ˳���
//��10.2.1   �ṩ����������
//����5�棩�Ľ���2015��4��19��

//1.  ˳��������������ȡ������Ч�ʷ���
//package dataStructure;                                   //������ǰ.java�ļ��е�����ָ������

//˳����࣬ʵ��ADT List<T>�����ķ�����T��ʾ����Ԫ�ص���������
public class SeqList<T> extends Object  implements Iterable<T>  //10.2.1��ʵ�ֿɵ����ӿ�
//public class SeqList<T>  extends MyAbstractList<T> //˳����࣬�̳г����б��ࡣ�ṩ����������
{
    protected Object[] element;                            //�������飬������Ա
    protected int n;                                       //˳���Ԫ�ظ��������ȣ�

    //1. ���졢��ȡ
    public SeqList(int length)                             //��������Ϊlength�Ŀձ�
    {
        this.element = new Object[length];                 //��������Ĵ洢�ռ䣬Ԫ��Ϊnull��
                //��length<0��Java�׳������鳤���쳣 java.lang.NegativeArraySizeException
        this.n = 0;
    }
    public SeqList()                                       //����Ĭ�������Ŀձ����췽������
    {
        this(64);                                          //���ñ�����������ָ�������б�Ĺ��췽��
    }
    
    public SeqList(T[] values)                             //����˳�����values�����ṩԪ�أ��������пն���
    {
        this(values.length);                               //��������Ϊvalues.length�Ŀձ�
                   //��values==null���ÿն�����÷�����Java�׳��ն����쳣NullPointerException
        for (int i=0; i<values.length; i++)                //��������Ԫ�أ�O(n)
            this.element[i] = values[i];                   //�������ø�ֵ
        this.n = element.length;
//Ҳ��              this.insert(values[i]);                          //β���룬this.n++�����Ҳ��ã���Ϊ��û�н���
        
//Ҳ��        for (T x : values)                                  //������Ԫѭ��
//            this.insert(x);                                //β���룬this.n+1
    }
    
/*    //��5��
    public SeqList(T[] values)                            //����˳�����values�����ṩԪ�أ��������пն���
    {
        this(values.length*2);                             //����2��values���������Ŀձ�
                   //��values==null���ÿն�����÷�����Java�׳��ն����쳣NullPointerException
        for (int i=0; i<values.length; i++)                //���Ʒǿյ�����Ԫ�ء�O(n)
            if (values[i]!=null)
                this.element[this.n++] = values[i];        //�������ø�ֵ
    }*/    
    
    
    public boolean isEmpty()                               //�ж�˳����Ƿ�գ����շ���true��O(1)
    {
        return this.n==0;
    }

    public int size()                                      //����˳���Ԫ�ظ�����O(1)
    {
        return this.n;
    }

    //��ȡ����
    public T get(int i)                                    //���ص�i��Ԫ�أ�0��i<n����iԽ�磬����null��O(1)
    {
        if (i>=0 && i<this.n)
            return (T)this.element[i];                     //��������Ԫ�����õĶ��󣬴��ݶ�������
//      return this.element[i];                            //�����Object�����ܷ���T����
        return null;
    }    

    //���õ�i��Ԫ��Ϊx��0��i<n����iԽ�磬�׳����Խ���쳣����x==null���׳��ն����쳣��O(1)
    public void set(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");     //�׳��ն����쳣
        if (i>=0 && i<this.n)
            this.element[i] = x;
        else throw new IndexOutOfBoundsException(i+"");//�׳����Խ���쳣
    }

    //����˳�������Ԫ�ص������ַ�������ʽΪ��(,)��������Object���toString()����
    public String toString()
    {
        String str=this.getClass().getName()+"(";          //��������
//        String str="(";
        if (this.n>0)
            str += this.element[0].toString();             //ִ��T���toString()����������ʱ��̬
        for (int i=1; i<this.n; i++)
            str += ", "+this.element[i].toString();        //ִ��T���toString()����������ʱ��̬
        return str+") ";                                   //�ձ���()
    }
/*    ���У�Ч��ͬ��
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
    //�������Ա�����Ԫ�ص������ַ�����Ԫ�ش���Ӻ���ǰ����O(n)�����裬���ȶ����á�������ʡ��
    public String toPreviousString()
    {
        return "";
    }
    
    //2. ˳���Ĳ������
    //����x��Ϊ��i��Ԫ�أ�x!=null������x��š���x==null���׳��ն����쳣��O(n)
    //�����i��ȡ�ݴ��ʩ����i<0������x����ǰ����i>n������x�����
    public int insert(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");     //�׳��ն����쳣
        if (i<0)       i=0;                                //����λ��i�ݴ���������ǰ
        if (i>this.n)  i=this.n;                           //���������
        Object[] source = this.element;                    //����������ø�ֵ��sourceҲ����element����
        if (this.n==element.length)                        //����������������˳������������
        {
            this.element = new Object[source.length*2];    //��������һ���������������
            for (int j=0; j<i; j++)                        //���Ƶ�ǰ����ǰi-1��Ԫ��
                this.element[j] = source[j];
        }
        for (int j=this.n-1; j>=i; j--)                    //��i��ʼ����β��Ԫ������ƶ�������Ӻ���ǰ
            this.element[j+1] = source[j];
        this.element[i] = x;
        this.n++;
        return i;                                          //����x���
    }
    public int insert(T x)                                 //˳���β����xԪ�أ�����x��š���Ա��������
    {
        return this.insert(this.n, x);                     //��������У�this.n��1
    }
    
    //3. ˳����ɾ������
    public T remove(int i)          //ɾ����i��Ԫ�أ�0��i<n�����ر�ɾ��Ԫ�ء���iԽ�磬����null��//������iԽ�磬�׳����Խ���쳣
    {
        if (this.n>0 && i>=0 && i<this.n) 
        {
            T old = (T)this.element[i];                    //old�д洢��ɾ��Ԫ��
            for (int j=i; j<this.n-1; j++)
                this.element[j] = this.element[j+1];       //Ԫ��ǰ��һ��λ��
            this.element[this.n-1]=null;                   //��������Ԫ�ض���Ϊ�գ��ͷ�ԭ����ʵ��
            this.n--;
            return old;                                    //����old�ֲ��������õĶ��󣬴��ݶ�������
        }
        return null;
        //throw new IndexOutOfBoundsException(i+"");         //�׳����Խ���쳣
    }
    public void clear()                                    //ɾ�����Ա�����Ԫ��
    {
        this.n=0;                                          //���ó���Ϊ0��δ�ͷ�����ռ�
    }
    //����2.1�����Josephus�����⡣
    
    //4. ˳�����
    //˳������״γ��ֵ���key���Ԫ�أ�����Ԫ�����i��0��i<n�����Ҳ��ɹ�����-1��
    //��key==null��Java�׳��ն����쳣NullPointerException
    public int search(T key)
    {
//      System.out.print(this.getClass().getName()+".indexOf("+key+","+start+")��");
        for (int i=0; i<this.n; i++)
        {
//          System.out.print(this.element[i]+"��");
            if (key.equals(this.element[i]))               //ִ��T���equals(Object)����������ʱ��̬
               return i;
        }
        return -1;                                         //�ձ��δ�ҵ�ʱ
    }//��������Ԫѭ�����޷�����i
    
    public boolean contains(T key)                         //�ж��Ƿ�����ؼ���ΪkeyԪ��
    {
        return this.search(key)!=-1;
    }
    //��˼����2-2�����ڲ��ҵĲ���
    public T remove(T key)                                 //ɾ���׸���key���Ԫ�أ����ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null
    {
        return this.remove(this.search(key));              //�Ȳ��ң��ٵ���remove(i)�������Ҳ��ɹ�������-1����ɾ��
    }  

    //5.  ˳����ǳ���������
/*    public SeqList(SeqList<T> list)                      //ǳ�������췽��
    {
        this.n = list.n;                                   //int������ֵ������������ֵ
        this.element = list.element;                       //�������ø�ֵ��������������һ�����飬����
    }*/
    public SeqList(SeqList<? extends T> list)              //�������췽�������������list
    {
//      super();                                           //Ĭ�ϵ��ã�ִ��Object()
        this.n = list.n; 
        this.element = new Object[list.element.length];    //����һ������
        for (int i=0; i<list.n; i++)                       //����list����Ԫ�أ�O(n)
            this.element[i] = list.element[i];             //�������ø�ֵ��û�д����¶���
//Ҳ�ɣ����              this.insert(values[i]);                     //β���룬this.n++
    //����̳У�����ʱ��̬�������������������췽��SortedSeqList(SeqList<? extends T> list)���㷨��ͬ
        
//          this.element[i] = new T(list.element[i]);    //�﷨����ΪJavaû���ṩĬ�Ͽ������췽��
//      this.element[i] = new Object(list.element[i]);    //�﷨����ΪObjectû���ṩ�������췽�����ҹ��췽�����ܼ̳� 
    }
    //��5��
/*    public SeqList(SeqList<? extends T> list)            //�������췽�������������list
    {
        this(list.element.length);                         //�����˳���
        for (int i=0; i<list.n; i++)                       //����list����Ԫ�أ�O(n)
            this.insert((T)list.element[i]);               //β���룬this.n++
//Ҳ��        this.addAll(list);
//          this.element[i] = new T(list.element[i]);    //�﷨����ΪJavaû���ṩĬ�Ͽ������췽��
//      this.element[i] = new Object(list.element[i]);    //�﷨����ΪObjectû���ṩ�������췽�����ҹ��췽�����ܼ̳� 
    }*/
    

    //6. ˳���Ƚ����
    public boolean equals(Object obj)                      //�Ƚ�����˳����Ƿ���ȡ����ǡ�O(n)
    {
        if (this==obj)                                     //��this��obj����ͬһ��˳���ʵ���������
            return true;
        if (obj instanceof SeqList<?>)                     //��obj����˳���ʵ����SeqList<?>������SeqList<T>�ĸ���
        {
            SeqList<T> list = (SeqList<T>)obj;             //����listҲ����obj���õ�ʵ��
            if (this.n==list.n)                            //�Ƚ����߳����Ƿ����
            {
                for (int i=0; i<this.n; i++)               //�Ƚ�����˳��������Ԫ���Ƿ����
                    if (!(this.get(i).equals(list.get(i))))//equals(Object)����ʱ��̬
                        return false; 
                return true;
            }
        }
        return false;
    }    
    //����ʵ��ADT List����2��2.2.2������
    
    //��˼����2-2����ʵ��2-1��  ˳������ӳ�Ա������Ҳ������������DifferentSeqListʵ�֣�
/*    public int insertDifferent(int i, T x)       //���벻�ظ�Ԫ�ء����Ҳ��ɹ�ʱ������
    {
        return this.search(x)==-1 ? this.insert(i,x) : -1;
    }
    public int insertDifferent(T x)              //���벻�ظ�Ԫ�ء����Ҳ��ɹ�ʱ��β����
    {
        return this.search(x)==-1 ? this.insert(x) : -1;
    }*/
    //��ʵ����2-1�����죬���ڲ����㷨
    public boolean isDifferent()                           //�ж��Ƿ���
    {
        for (int i=0; i<this.n; i++) 
            for (int j=0; j<i; j++)                        //��ǰi��Ԫ�رȽ��Ƿ���ȡ������ò��ң����֣��������㷨Ҳ�����
                if (this.element[j].equals(this.element[i])) 
                    return false;
        return true;
    }
    
    //��ʵ��2-1��  
    public void removeAll(T key)                           //ɾ��������key���Ԫ�أ�O(n)���㷨ÿԪ���ƶ����
    {
        int i=0;
        while (i<this.n)
            if (key.equals(this.element[i]))
                this.remove(i);                            //���ҵ�ɾ����this.n��1��i����
            else i++;
    }//������for��䣿���Ľ����ƶ�һ��
    
    public void replaceFirst(T key, T x)                   //���״γ��ֵ���key���Ԫ���滻Ϊx��O(n)
    {
        if (key!=null && x!=null)
        {
            int i = this.search(key);                      //����key�״γ���λ��
            if (i!=-1)
                this.element[i] = x;                       //���ҵ����滻
        }
    }
    public void replaceAll(T key, T x)                     //��������key���Ԫ���滻Ϊx��O(n)
    {
        if (key!=null && x!=null)
            for (int i=0; i<this.n; i++)
                if (key.equals(this.element[i]))
                    this.element[i] = x;                   //���ҵ����滻
    }
    
    public int searchLast(T key)                           //����Ԫ��key������λ�ã���δ�ҵ�����-1
    {
        for (int i=this.n-1; i>=0; i--)
            if (key.equals(this.element[i]))
                return i;
        return -1;                                         //�ձ�keyΪ�ն����δ�ҵ�ʱ
    }
    
    //�жϵ�ǰ˳����Ƿ��������򣩡�T����ĳ�����������ʵ��Comparable<T>�ӿ�
    public <T extends Comparable<? super T>> boolean isSorted()
    {
        for (int i=0; i<this.n; i++)
            if (((T)this.element[i]).compareTo((T)this.element[i+1])>0)
                return false;
        return true;
    }
    
    
    //��˼����2-3������2.5��  ���Ӽ�������ʵ��Set<T>
    //����̳У�����Ҫ���ǣ���������ʱ��̬
    //��this˳���֮�����list����Ԫ�أ����ϲ����㣻
    //? extends T ��ʾ˳���Ԫ��֧��T������ʵ��
    public void addAll(SeqList<? extends T> list)
    {
        for (int i=0; i<list.n; i++) 
            this.insert(list.get(i));               //����ʱ��̬��˳���β���룻����˳���ֵ����
        //this.insert((T)list.element[i]);               //����ʱ��̬��˳���β���룻����˳���ֵ����
    }
//���  public void addAll(SeqList<T> list)          //�򲻽���T������
//���  public void addAll(SeqList<?> list)          //�����this.insert(x)�������
//���  public void insert(SeqList<T> list)          //���أ�����ʱ��������Ϊnullʱ��������޷�ʶ�����ط����е���һ��


/*  //���಻֧��
    public void insert(int i,SeqList<T> list)             //��list����Ԫ����ӵ���ǰ˳������//˳������ӣ���β���
    {
        for (int j=0; j<list.n; j++) 
            this.insert(i,list.get(j));                    //����ʱ��̬��˳���β���룻����˳���ֵ����
    }*/

    //��˼����2-4��ϰ����
    public SeqList<T> union(SeqList<? extends T> list)     //������this˳���֮������list��˳������ı�this��list
    {
        SeqList<T> result = new SeqList<T>(this);          //���this���޷���������ʵ��   //����?
        result.addAll(list);                               //˳���ϲ����ӣ�β����
        return result;                                     //ֻ�ܷ���SeqList<T>����
        
//����        return new SeqList<T>(this).addAll(list);
    }
    //9.5.1 ����mergeAll�ϲ����鲢�����ã�������

    boolean containsAll(SeqList<? extends T> list)  //�ж��Ƿ����set������Ԫ�أ��Ƿ��Ӽ���
    {
        for (int i=0; i<list.n; i++) 
            if (!this.contains(list.get(i)))
                return false;
        return true;
    }
//����    boolean containsAll(SeqList<?> list)
    
    boolean removeAll(SeqList<? extends T> list)           //ɾ��Ҳ������set��Ԫ�أ����ϲ�
    {
        T old=null;
        for (int i=0; i<list.n; i++) 
            old=this.remove(list.get(i));
        return old!=null;
    }
    boolean retainAll(SeqList<? extends T> list)           //��������ЩҲ������set��Ԫ�أ����ϲ�
    {
        return false;
    }

    //���ش�begin��endԪ����ɵ��ӱ���Ϊ����T��ĳ������
//    SeqList<? extends T> subList(int begin, int end)   //����Ҫ���﷨ 
    SeqList<T> subList(int begin, int end)             //���ش�begin��end��ɵ��ӱ���������ͬ   
    {
        SeqList<T> list = new SeqList<T>();
        for (int i=begin; i<end; i++) 
            list.insert(this.get(i));
        return list;
    }    
   
    
    //��10�µ�����
    //10.2.1   �ṩ����������
    //1.  ˳������ṩ������
    
    public java.util.Iterator<T>  iterator()               //����Java����������
    {   return new SeqIterator();
    }
    private class SeqIterator  implements java.util.Iterator<T>//˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        int index=-1, succ=0;                              //��ǰԪ�غͺ��Ԫ�����
        public boolean hasNext()                           //���к��Ԫ�أ�����true
        {
            return this.succ<SeqList.this.n;               //SeqList.this.n���ⲿ�൱ǰʵ���ĳ�Ա����
        }
        public T next()                                    //���غ��Ԫ�أ���û�к��Ԫ�أ�����null
        {
            T value = SeqList.this.get(this.succ);         //�����ⲿ��SeqList��ǰʵ���ĳ�Ա����
            if (value!=null)
            {   this.index = this.succ++;
                return value;
            }
            throw new java.util.NoSuchElementException();  //�׳��޴�Ԫ���쳣
        }
        public void remove()                               //ɾ�������������ʾ�ļ��ϵ�ǰԪ��
        {
            if (this.index>=0 && this.index<SeqList.this.n)
            {   SeqList.this.remove(this.index);           //�����ⲿ��SeqList��ǰʵ���ĳ�Ա����
                                                           //ɾ����index��Ԫ�أ�����SeqList.this.n-1
                if (this.succ>0)
                    this.succ--;
                this.index=-1;                             //���ò�������ɾ��
            }
            else throw new IllegalStateException(); //�׳���Ч״̬�쳣
        }
    }//SeqIterator�ڲ������


    //��˼����10-3��˳������ṩ�б��������
    public java.util.ListIterator<T> listIterator()        //����Java�б����������
    {
        return new SeqListIterator(0);
    }
    public java.util.ListIterator<T> listIterator(final int index) //����Java�б����������
    {
        if (index>=0 && index<this.n)
            return new SeqListIterator(index);
        else throw new IndexOutOfBoundsException("Index: "+index);
    }

    //˽���ڲ��࣬�̳�ʵ�ֵ������ӿڵ�SeqIterator�ڲ��࣬ʵ���б�������ӿ�
    private class SeqListIterator extends SeqIterator implements java.util.ListIterator<T>
    {
        public SeqListIterator(int index)
        {
            this.succ=index;
        }
        public boolean hasPrevious()                       //����ǰ��Ԫ�أ�����true
        {
            return this.succ!=0;
        }

        public T previous()                                //����ǰ��Ԫ��
        {
            T value = SeqList.this.get(this.succ-1);
            if (value!=null)
            {
                this.index = this.succ--;
                return value;
            }
            throw new java.util.NoSuchElementException();  //�׳��޴�Ԫ���쳣
        }
      
        public int nextIndex()                             //���غ��Ԫ�����
        {
            return this.succ;
        }
        public int previousIndex()                         //����ǰ��Ԫ�����
        {
            return this.succ-1;
        } 
      
        public void set(T x)                               //�����ϵ�ǰԪ���滻Ϊx
        {
            if (this.index>=0 && this.index<SeqList.this.n)
                SeqList.this.set(this.index, x);           //�����ⲿ�൱ǰʵ���ĳ�Ա����
            else throw new IllegalStateException(); //�׳���Ч״̬�쳣
        }
        public void add(T x)                               //����Ԫ��x
        {
            SeqList.this.insert(this.succ, x);             //�����ⲿ�൱ǰʵ���ĳ�Ա����
            this.succ++;                                   //����Ԫ��Ϊ��ǰԪ��
        }
    }//SeqListIterator�ڲ������
}
//@author��Yeheya��2015-4-19


/*
 * ����δ������
�������˵�����¡�
    
    public SeqList(int length, T x)                        //����˳���length��Ԫ�س�ֵΪx��x!=null�����췽������
    {
        this(length);                                      //��������Ϊlength�Ŀձ�
        if (x==null)
            throw new java.lang.NullPointerException("x==null"); //�׳��ն����쳣������ֹͣ����
        for (int i=0; i<length; i++)
            this.element[i] = x;                           //�������ø�ֵ
        this.n = length;
    }//5.2.2�ھ����еĵ����������ã���ΪxΪͬһ�����������ʵ����Ҫ�������������
     //���鼯Ҳ�����ã�x����Ϊint����Ҫ-1��C++����
    //@author��Yeheya��2014-8-5
 
*/
//��2��4. ˳�����
/*    //��start��0��start<n����ʼ�����״γ��ֵ���key���Ԫ�أ�����Ԫ�����i��0��i<n��
  //�����Ҳ��ɹ�����-1����key==null��Java�׳��ն����쳣NullPointerException
  public int indexOf(T key, int start)
  {
//      System.out.print(this.getClass().getName()+".indexOf("+key+","+start+")��");
      for (int i=start; i<this.n; i++)
      {
//        System.out.print(this.element[i]+"��");
          if (key.equals(this.element[i]))     //ִ��T���equals(Object)����������ʱ��̬
          return i;
      }
      return -1;                               //�ձ��δ�ҵ�ʱ
  }
      
  public int indexOf(T key)                    //�����״γ��ֵ���key���Ԫ�أ�����Ԫ�����
  {
      return this.indexOf(key, 0);             //��0��ʼ����
  }
  public int search(T key)                       //˳����ң������״γ��ֵ���key���Ԫ�أ������Ҳ��ɹ�����null
  {
//      int find=this.indexOf(key,0);
//      return find==-1 ? null : (T)this.element[find];

//    System.out.print(this.getClass().getName()+".indexOf("+key+","+start+")��");
      for (int i=0; i<this.n; i++)
      {
//        System.out.print(this.element[i]+"��");
          if (key.equals(this.element[i]))     //ִ��T���equals(Object)����������ʱ��̬
          return i;
      }
      return -1;                               //�ձ��δ�ҵ�ʱ
  }
  public T remove(T key)                       //ɾ���״γ��ֵ���key���Ԫ�أ����ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null
  {
//      return this.remove(this.indexOf(key,0));
      return this.remove(this.search(key));
  }    

  
  //ʵ��2 ˳�����ϰ��
  public void insertUnrepeatable(T x)          //���벻�ظ�Ԫ��
  {
      if (this.indexOf(x)==-1)                 //���Ҳ��ɹ�ʱ��β����
          this.insert(x);
  }    
  public int lastIndexOf(T key)                //����Ԫ��key������λ�ã���δ�ҵ�����-1
  {
      if (key!=null)
          for (int i=this.n-1; i>=0; i--)
              if (key.equals(this.element[i]))
                  return i;
      return -1;                               //�ձ�keyΪ�ն����δ�ҵ�ʱ
  }

  public void removeAll(T key)                 //ɾ��������key���Ԫ�أ�O(n)�������Ľ����ƶ�һ��
  {
      if (key!=null)
      {
          int i=0;
          while (i<this.n)
              if (key.equals(this.element[i]))
                  this.remove(i);              //ɾ��Ԫ�أ�this.n��1��i����
              else i++;
      }
  }
  
  public void replace(T key, T x)              //���״γ��ֵ���key���Ԫ���滻Ϊx��O(n)
  {
      if (key!=null && x!=null)
      {
          int i = this.indexOf(key);           //����key�״γ���λ��
          if (i==-1)
          this.element[i] = x;
      }
  }
  public void replaceAll(T key, T x)           //��������key���Ԫ���滻Ϊx��O(n)
  {
      if (key!=null && x!=null)
          for (int i=0; i<this.n; i++)
              if (key.equals(this.element[i]))
                  this.element[i] = x;
  }

  //ʵ��2
  //�жϵ�ǰ˳����Ƿ��������򣩡�T����ĳ�����������ʵ��Comparable<T>�ӿ�
  public <T extends java.lang.Comparable<? super T>> boolean isSorted()
  {
      for (int i=0; i<this.n; i++)
          if (((T)this.element[i]).compareTo((T)this.element[i+1])>0)
              return false;
      return true;
  }

/*
���У�Ч��ͬ��
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
//author��Yeheya��2014-7-3

/*����   //��10��
//˳�����ָ��Ԫ�أ������ҳɹ������״γ���λ�ã����򷵻�-1
//���Ѱ��������е�value�����������۰���ҹؼ���Ϊkey��Ԫ�أ��ɱȽ�������comparatorָ������Ƚϴ�С�Ĺ���
public int indexOf(T key, java.util.Comparator<? super T> comparator)
{
    if (key!=null)
        for (int i=0; i<this.n; i++)
            if (this.element[i].equals(key))  //�������equals()�����Ƚ��Ƿ����
                return i;
    return -1;                               //�ձ�xΪ�ն����δ�ҵ�ʱ
}
}
*/