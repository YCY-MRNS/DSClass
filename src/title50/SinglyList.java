package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��6��21��
//��2.3 ���Ա����ʽ�洢��ʵ��
//3. ��ͷ���ĵ�������
//��10.2.1   �ṩ����������

//�������࣬ʵ��ADT List<T>�����ķ�����T��ʾ����Ԫ�ص���������
public class SinglyList<T> extends Object implements Iterable<T>  //10.2.1��ʵ�ֿɵ����ӿ�
//public class SinglyList<T> extends MyAbstractList<T>       //�������࣬�̳г����б���//��10�£�10.2 ʵ�ֵ����� 
{
    public Node<T> head;                                   //ͷָ�룬ָ�������ͷ���
    //ע�⣬head����������Ȩ�ޣ���Ϊ7.2.2�ڣ�ͼ���ڽӱ��У�ɾ������Ҫ������2014��8��3��
    
    //��1�����췽��
    public SinglyList()                                    //���췽��������յ�����
    {
        this.head = new Node<T>();                         //����ͷ��㣬data��nextֵ��Ϊnull
    }
    
    //���쵥������values�����ṩԪ�أ��������пն��󡣲���β���룬������Ԫ�ش���������Ԫ�ش�����ͬ
    public SinglyList(T[] values)
    {
        this();                                            //�����յ�����ֻ��ͷ���
        Node<T> rear=this.head;                            //rearָ���������һ�����
        for (int i=0; i<values.length; i++)                //��values.length==0�����������
            if (values[i]!=null)
            {
                rear.next=new Node<T>(values[i],null);     //β���룬�����������rear���֮��
                rear = rear.next;                          //rearָ���µ���β���
            }
    }

    public boolean isEmpty()                               //�жϵ������Ƿ�գ�O(1)
    {
        return this.head.next==null;
    }

    //��2����ȡ
    public T get(int i)                                    //���ص�i��Ԫ�أ�0��i<���ȡ���iԽ�磬����null��O(n)
    {
        Node<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)                 //����������Ѱ�ҵ�i����㣨pָ��
            p = p.next;
        return (i>=0 && p!=null) ? p.data : null;          //��pָ���i����㣬������Ԫ��ֵ
    }
   
    //��˼����2-5��  
    //���õ�i��Ԫ��Ϊx��0��i<n����iԽ�磬�׳����Խ���쳣����x==null���׳��ն����쳣��O(n)
    public void set(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");     //�׳��ն����쳣
        Node<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)                 //����Ѱ�ҵ�i����㣨pָ��
            p = p.next;
        if (i>=0 && p!=null)
            p.data = x;                                    //pָ���i�����
        else throw new IndexOutOfBoundsException(i+"");    //�׳����Խ���쳣
    }     
    
    //���ص���������Ԫ�ص������ַ�������ʽΪ��(,)��������Object���toString()������O(n)
    public String toString()
    {
        String str=this.getClass().getName()+"(";          //��������
//        String str="(";
        for (Node<T> p=this.head.next;  p!=null;  p=p.next)//p����������
        {   str += p.data.toString();
            if (p.next!=null) 
                str += ",";                                //�������һ�����ʱ���ӷָ���
        }
        return str+")";                                    //�ձ���()
    }
 
    //��˼����2-5��  
    public int size()                                      //���ص������ȣ�O(n)
    {
        int i=0; 
        for (Node<T> p=this.head.next;  p!=null;  p=p.next) //p����������
            i++;
        return i;
    }
    
    //��3������
    //����x��Ϊ��i��Ԫ�أ�x!=null�����ز����㡣
    //�����i��ȡ�ݴ��ʩ����i<0������x����ǰ����i>n������x�����O(n)
    public Node<T> insert(int i, T x)
    {
        if (x==null)
            throw new NullPointerException("x==null");     //�׳��ն����쳣
        Node<T> front=this.head;                           //frontָ��ͷ���
        for (int j=0;  front.next!=null && j<i;  j++)      //Ѱ�ҵ�i-1�������һ����㣨frontָ��
            front = front.next;
        front.next = new Node<T>(x, front.next);           //��front֮�����ֵΪx��㣬����ͷ���롢�м�/β����
        return front.next;                                 //���ز�����
    }
    public Node<T> insert(T x)                             //�ڵ�����������x����O(n)
    {
        return insert(Integer.MAX_VALUE, x);
                             //����insert(i,x)�����������ֵָ����������󣬱���һ�Σ�i�����ݴ�
//        return insert(this.count(), x);                  //��������������Σ�Ч�ʽϵ�
    }
      
    //��4��ɾ��
    public T remove(int i)    //ɾ����i��Ԫ�أ�0��i<n�����ر�ɾ��Ԫ�أ���iԽ�磬����null��O(n)
    {
        Node<T> front=this.head;                           //frontָ��ͷ���
        for (int j=0;  front.next!=null && j<i;  j++)      //����Ѱ�ҵ�i-1��㣨frontָ��
            front = front.next;
        if (i>=0 && front.next!=null)                      //��front�ĺ�̽����ڣ���ɾ��֮
        {
            T old = front.next.data;                       //��ô�ɾ��������õĶ���
            front.next = front.next.next;                  //ɾ��front�ĺ�̽�㣬����ͷɾ�����м�/βɾ��
                                                           //��Java������Ժ��ͷŽ��ռ�ô洢��Ԫ
            return old;
        }
        return null;                                       //��i<0��i>��
//        throw new IndexOutOfBoundsException(i+"");       //�׳����Խ���쳣
    }

    public void clear()                                    //ɾ������������Ԫ��
    {
        this.head.next=null;                               //Java�Զ��ջ����н��ռ�õĴ洢�ռ�
    }

    //��5�����ң�ɢ�б���
    //���ܼ������������׸���key���Ԫ�ؽ�㣬�����Ҳ��ɹ�����null
    //�����������keyΪ�ն���Java���׳��ն����쳣
    //�㷨��Ч�ʣ�˳����ң�O(n)
    //����7.2.2��ͼ���ڽӱ����뷵�ؽ�㣬��ΪҪ���̽�㡣2014��8��6�գ�������Ӱ��δ�޸�

    //˳����ҹؼ���ΪkeyԪ�أ������״γ��ֵ�Ԫ�أ������Ҳ��ɹ�����null
    //key����ֻ�����ؼ����������T���equals()�����ṩ�Ƚ϶�����ȵ�����
    public Node<T> search(T key) 
    {
        for (Node<T> p=this.head.next;  p!=null;  p=p.next)
            if (key.equals(p.data))                        //ִ��T���equals(Object)����������ʱ��̬
                return p;
        return null;
    }

    public boolean contains(T key)               //�ж��Ƿ�����ؼ���ΪkeyԪ��
    {
        return this.search(key)!=null;                     //�Բ��ҽ������жϽ��
    }

    //���¡�˼����2-7��  
    //Ҳ�ɡ�ʵ����2-10����������ʵ�֣����쵥������
    //β���뻥��Ԫ��x�������ҵ���x�Ĺؼ�����ͬԪ�أ������룬����x��㣻���ǡ�//ɢ�б���
    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;             //front��p��ǰ�����
        while (p!=null && !p.data.equals(x))               //˳�����
        {
            front = p; 
            p = p.next;
        }
        if (p!=null)                                       //���ҳɹ���Ԫ���ظ��������룬����p���
        {
            System.out.println("x="+x+"��Ԫ���ظ���δ���롣");
            return p;
        }
        return front.next = new Node<T>(x,null);           //β����ֵΪx��㣬���ز�����
    }
    
    public T remove(T key)             //ɾ���׸���key���Ԫ�ؽ�㣬���ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null��O(n)ɢ�б���
    {
        Node<T> front=this.head, p=front.next;
        while (p!=null && !key.equals(p.data))             //˳������״γ��ֵ���key���Ԫ��
        {
            front = p;                                     //frontָ��p��ǰ�����
            p=p.next;
        }
        if (p!=null)                                       //�����ҳɹ���ɾ��front�ĺ�̣�p��㣩
        {
            front.next = p.next;                           //����ͷɾ�����м�/βɾ��
            return p.data;
        }
        return null;
    }
    //˼���⣺remove(x)�����ܷ����search(x)������λ��Ϊʲô��
    //����ʵ��ADT List����2��  
    
    //��4����ߵ��������Ч�ʵĴ�ʩ
    
    //����2.5��  ��������ת��
    
    //5. �������ǳ���������
    //��˼����2-8��
//����    public SinglyList(SinglyList<? extends T> list)   //������췽�������Ƶ�����list�����н��
   //�൱��Node<? extends T>����Node<?>
    
    public SinglyList(SinglyList<T> list)                  //������췽�������Ƶ�����list�����н��
    {
        this();                                            //�����յ�����ֻ��ͷ���
        Node<T> rear=this.head;
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)  //p����list������
        {
            rear.next = new Node<T>(p.data, null);        //���ƽ��β���뵽this������
            rear = rear.next;                             //ָ��this������β
        }
    }
    
    public boolean equals(Object obj)            //�Ƚ������������Ƿ���ȣ�����Object���equals(obj)����
    {
        if (obj == this)
            return true;
        if (!(obj instanceof SinglyList<?>))
            return false;
        Node<T> p=this.head.next;
        Node<T> q=((SinglyList<T>)obj).head.next;
        while (p!=null && q!=null && p.data.equals(q.data))
        {
            p=p.next;
            q=q.next;
        }
        return p==null && q==null;
    }   
    
    //��ʵ��2-7 ϰ����  ������ĵ��������� 
    //(1)����4�������ṩ��������������ʽ
    public Node<T> first()                                 //���ص������һ��Ԫ�ؽ�㣬O(1)
    {
        return this.head.next;                             //��������շ���null
    }
    public Node<T> next(Node<T> p)                         //����p�ĺ�̽�㣬O(1)
    {
        return (this.head.next==null || p==null) ? null : p.next;
    }
    public Node<T> previous(Node<T> p)                     //����p��ǰ����㣬O(n)
    {
        if (this.head.next==null || p==null || p==this.head.next)
            return null;
        Node<T> front=this.head.next;
        while (front.next!=p)
            front = front.next;
        return front;
    }
    public Node<T> last()                                  //���ص��������һ��Ԫ�ؽ�㣬O(n)
    {
        Node<T> p=this.head.next;
        while (p!=null && p.next!=null)
            p = p.next;
        return p;                                          //��������շ���null
    }  
    
    
    //��ʵ��2-6��  ��������ӱ�Ĳ�����    
    //��ϰ������2.1����������Ϊ���������뷵��ֵ�������ۡ�
    //��this������֮������list��������β��Ӻϲ���һ������������listΪ�յ�����O(n)
    public void concat(SinglyList<T> list)
    {
        Node<T> rear=this.head;
        while (rear.next!=null)                            //Ѱ��this����������һ�������Ϊ����λ��
            rear = rear.next;
        rear.next = list.head.next;                        //��list������this������֮�󣬺ϲ������������һ��������
        list.head.next=null;                               //����listΪ�յ����������߼�����������ʽ�������κ��޸ľ�������ʵ�ʲ���
    }

    public void addAll(SinglyList<T> list)                 //����list���н����뵽this������֮�󣬼��ϲ������ı�list��
    {
        this.concat(new SinglyList<T>(list));              //��ִ�е�����������ٴ��ݶ�������
        //����ͬ���¡�
/*        Node<T> rear=this.head;
        while (rear.next!=null)                            //Ѱ��this����������һ�������Ϊ����λ��
            rear = rear.next;
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)  //p����list������
        {
            rear.next = new Node<T>(p.data, null);        //���ƽ��β���뵽this������
            rear = rear.next;                             //ָ��this������β
        }*/
    }

    //���ظ���this��list�ϲ����ӵĵ��������������ı�this��list��
    //˵����ֻ�ܷ���SinglyList<T>�����ܷ�������ʵ�������������Ҫ���븲�ǣ���ˣ�������û�����á�
    public SinglyList<T> union(SinglyList<T> list)
    {
        SinglyList<T> result = new SinglyList<T>(this);   //���������
        result.addAll(list);
        return result;
    }
    
    
    //��10�£�10.2 ʵ�ֵ�����
    public java.util.Iterator<T> iterator()      //����Java����������
    {
        return new SinglyIterator();
    }

    private class SinglyIterator implements java.util.Iterator<T> //˽���ڲ��࣬ʵ�ֵ������ӿ�
    {
        Node<T> current=SinglyList.this.head;    //��ǰ��㣬��ֵΪ�ⲿ�൥����ͷ���
        Node<T> front=null;                      //��ǰ����ǰ�����

        public boolean hasNext()                 //���к��Ԫ�أ�����true
        {
            return this.current!=null && this.current.next!=null;
        }

        public T next()                          //���غ��Ԫ��
        {
            if (this.hasNext())
            {
                this.front = this.current;
                this.current = this.current.next;
                return this.current.data;
            }
            else throw new java.util.NoSuchElementException();  //�׳��޴�Ԫ���쳣
        }

        public void remove()                     //ɾ�������������ʾ�ļ��ϵ�ǰԪ��
        {
            if (this.front!=null)
            {
                this.front.next = this.current.next; //ɾ����ǰ���
                this.current = this.front;
                this.front=null;                     //���ò�������ɾ��
            }
            else throw new IllegalStateException();//�׳���Ч״̬�쳣
//            throw new UnsupportedOperationException();     //��֧�ָò������׳��쳣
        }
    }//�ڲ������
    //��˼����10-2��
}
//@author��Yeheya��2014-10-30
