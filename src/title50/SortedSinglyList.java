package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��6��22��
//2.3 ���Ա����ʽ�洢��ʵ��
//2.3.3  ��������

//���������ࣨ���򣩣�T��T��ĳ��������ʵ��Comparable<T>�ӿڣ��̳е�������
public class SortedSinglyList<T extends Comparable<? super T>> extends SinglyList<T>
{    
    public SortedSinglyList()                              //�������������
    {
        super();                                           //Ĭ�ϵ��ø��๹�췽��SinglyList()
    }
    
    public SortedSinglyList(T[] values)                    //���죬��ֵ����values����Ԫ�ء�ֱ�Ӳ��������㷨
    {
        super();                                           //����յ�����Ĭ�ϵ���SinglyList()
        for (int i=0;  i<values.length;  i++)              //ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(values[i]);                        //��������ֵ����
    }

    public SortedSinglyList(SortedSinglyList<T> list)      //������췽��
    {
        super(list);                                       //���ø���ͬ�����Ĺ��췽��������ʡ��
    }   

    //�ɵ�����list���죨����������ع��췽����ֱ�Ӳ�������
    public SortedSinglyList(SinglyList<T> list)
    {
        super();                                           //����յ�����
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(p.data);                           //��������ֵ����
    }
    
    //��֧�ָ���������������������串�ǲ��׳��쳣��������ͬ����˳���
    public void set(int i, T x) 
    {
        throw new UnsupportedOperationException("set(int i, T x)");
    }
    public Node<T> insert(int i, T x) 
    {
        throw new UnsupportedOperationException("insert(int i, T x)");
    }
    
    //����x��x!=null������x�����С˳�����ȷ������λ�ã������ڵ�ֵ���֮ǰ��
    //���ز����㡣O(n)�����Ǹ����insert(x)����
    public Node<T> insert(T x)
    {
        Node<T> front=this.head, p=front.next;             //frontָ��p��ǰ�����
        while (p!=null && x.compareTo(p.data)>0)           //Ѱ�Ҳ���λ��
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                    //��front֮��p֮ǰ����ֵΪx���
        return front.next;                                 //���ز�����
    }
    
    //���¡�˼����2-9��  
    //˳������׸���key���Ԫ�أ����ؽ�㣬�����Ҳ��ɹ�����null��O(n)�����ǡ�    //����5.2.2��ϡ������еĵ�����
    public Node<T> search(T key) 
    {
        for (Node<T> p=this.head.next;  p!=null && key.compareTo(p.data)>=0;  p=p.next)
        {
//            System.out.print(p.data+"��");
            if (key.compareTo(p.data)==0)                  //��compareTo()�ṩ�Ƚ϶����С����ȵ�����
                return p;
        }
//        System.out.println();
        return null; 
    }
    
    //���벻�ظ�Ԫ��x������x��㣻���ǡ�//5.2.2�ھ����еĵ�������ã���4��̲�û��д
    //Ҳ�ɡ�ʵ����2-10����������ʵ�֣���������������
    public Node<T> insertDifferent(T x)
    {
        Node<T> front=this.head, p=front.next;             //frontָ��p��ǰ�����
        while (p!=null && x.compareTo(p.data)>0)           //Ѱ�Ҳ���λ��
        {
            front = p;
            p = p.next;
        }
        if (p!=null && x.compareTo(p.data)==0)
            return p;                                      //���ҳɹ���Ԫ���ظ��������룬���ظý��
        return front.next = new Node<T>(x, p);             //��front֮��p֮ǰ����ֵΪx��㣬���ز�����
    }

    //ɾ���׸���key���Ԫ�ؽ�㣬���ر�ɾ��Ԫ�أ����Ҳ��ɹ�����null��O(n)������
    public T remove(T key)
    {
        Node<T> front=this.head, p=front.next;             //front��p��ǰ�����
        while (p!=null && key.compareTo(p.data)>0)         //������key���Ԫ�ؽ�㣨pָ��
        {
            front = p;
            p = p.next;
        }
        if (p!=null && key.compareTo(p.data)==0)           //�����ҳɹ���ɾ��p��㣬���ر�ɾ��Ԫ��
        {
            front.next = p.next;                           //ɾ��p���
            return p.data;
        }
        return null;
    }
    //���ϵ�2��
    
    //��ʵ��2-6��  ��������ӱ�Ĳ�����    
    
    //��˼����2-9 ADT��ϰ������2.1����������Ϊ���������뷵��ֵ�������ۡ� δд
    public void concat(SinglyList<T> list)                 //���ǣ���֧��ֱ�Ӻϲ����ӣ��׳��쳣
    {
        throw new UnsupportedOperationException("concat(SinglyList<T> list)");
    }
    //����list����Ԫ�ذ�ֵ���뵽this�����������ı�list�����ϲ���
    //���ǣ������븸����ͬ���㷨ʵ�ֲ�ͬ��ÿ�β���ȫ��Ԫ�غ��ٲ��룬O(n*n)
    public void addAll(SinglyList<T> list)
    {
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)//ֱ�Ӳ�������ÿ�˲���1��Ԫ��
            this.insert(p.data);                           //��������ֵ����
    }    
    
    //���ظ���this��list���н��������������������ı�this��list��
    //���ǣ���������ʵ�������Ƿ����ķ���ֵ���ͱ����븸�෽����ֵ����
    public SortedSinglyList<T> union(SinglyList<T> list)
    {
        SortedSinglyList<T> result = new SortedSinglyList<T>(this);   //�����������
        result.addAll(list);
        return result;
    }
//  public void union(SinglyList<T> list)                //��������Ƿ����ķ������ͱ����븸�෽����ͬ
    
    //��9��
    //9.5.2 ������������㷨
    //��9.5.2�㷨������ʵ��9-3����  ����������췽�����ɵ�����list������������
    //ֱ��ѡ�������㷨��ÿ�˽������Ԫ��ֵ����ɾ���Ͳ�����
/*    public SortedSinglyList(SinglyList<T> list)
    {
        super(list);                                       //this���list������
        for (Node<T> first=this.head.next;  first.next!=null;  first=first.next)
        {                                                  //firstָ������������׸����  
            Node<T> min=first;                             //minָ����Сֵ���
            for (Node<T> p=min.next;  p!=null;  p=p.next)  //����������Ѱ����Сֵ���
                if (p.data.compareTo(min.data)<0)          //�Ƚϴ�С
                    min = p;                               //min��ס��Сֵ���
            if (min!=first)                                //����minԪ�ص�ǰ��first���
            {
                T temp = min.data;
                min.data = first.data;
                first.data = temp;
            }
//            System.out.println(this.toString());
        }
    }*/
    
    //9.5.2�㷨����������
    public void merge(SortedSinglyList<T> list)            //��list���н��鲢��this�У��鲢������listΪ��
    {
        Node<T> front=this.head, p=front.next;             //p����this������front��p��ǰ��
        Node<T> q=list.head.next;                          //q����list������
        while (p!=null && q!=null)                         //����������������
            if ((p.data).compareTo(q.data)<0)              //��p���ֵС����p����ǰ��
            {
                front = p;
                p = p.next;
            }
            else
            {                                              //���򣬽�q�����뵽front���֮��
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        if (q!=null)                                       //��list��ʣ���㲢��thisβ
            front.next=q;
        list.head.next=null;                               //����listΪ�յ�����
    }
    
    //����this��list�鲢��������������ı�this������listΪ�ա����α�����Ч�ʵ�
/*    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        SortedSinglyList<T> slist = new SortedSinglyList<T>(this);   //���������this��O(n)
        slist.merge(new SortedSinglyList<T>(list));                  //slist�鲢list������listΪ�ա�O(n)
        return slist;
    }*/
    
    //��ʵ����9-3�� 
    //����this��list�鲢��������������ı�this��list��Ч�ʸߣ���
    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        Node<T> p=this.head.next, q=list.head.next;        //p��q�ֱ����this��list������
        SortedSinglyList<T> result = new SortedSinglyList<T>();
        Node<T> rear = result.head;                        //rearָ��result������β��׼������
        while (p!=null || q!=null)                         //p��q�ֱ����this��list������
            if (p!=null && (q!=null && (p.data).compareTo(q.data)<=0 || q==null))
            {                                              //����this��㵽result����p���ֵС����q�ѽ���
                rear.next = new Node<T>(p.data,null);
                rear = rear.next;
                p = p.next;
            }
            else if (q!=null && (p!=null && (p.data).compareTo(q.data)>0 || p==null))
            {                                              //���򣬸���list��㵽result����q���ֵС����p�ѽ���
                rear.next = new Node<T>(q.data,null);
                rear = rear.next;
                q = q.next;
            }
        return result;
    }
}
//@author��Yeheya��2015-4-6

    //����this��list�鲢�����򣩺�������������ı�this��list��һ�ι鲢�㷨����ȷ����3�����
/*    public SortedSinglyList<T> mergeWith(SortedSinglyList<T> list)  
    {
        SortedSinglyList<T> result = new SortedSinglyList<T>();
        Node<T> p=this.head.next;                //p����this������
        Node<T> q=list.head.next;                //q����list������
        Node<T> rear=result.head;                //rearָ��result������β��׼������        
        while (p!=null && q!=null)               //����������������
            if ((p.data).compareTo(q.data)<0)    //��p���ֵС������p��㣬p����ǰ��
            {
                rear.next = new Node<T>(p.data,null);
                rear = rear.next;
                p = p.next;
            }
            else
            {                                    //���򣬸���q��㣬q����ǰ��
                rear.next = new Node<T>(q.data,null);
                rear = rear.next;
                q = q.next;
            }
        while (p!=null)                          //��this��ʣ���㲢��resultβ
        {
            rear.next = new Node<T>(p.data,null);
            rear = rear.next;
            p = p.next;
        }
        while (q!=null)                          //��list��ʣ���㲢��resultβ
        {
            rear.next = new Node<T>(q.data,null);
            rear = rear.next;
            q = q.next;
        }
        return result;
    }*/

//��9�£�������췽�����ɵ�����list������������ֱ��ѡ������
/*    //��3�����ÿ��Ѱ����Сֵ��㣬������ǰ�棬ͨ��ɾ���������㷽ʽʵ�ֽ���
    public SortedSinglyLinkedList(SinglyLinkedList<T> list)
    {
        super(list);                                       //���list������
        Node<T> srear=head;                                //ָ����������β
        while (srear.next!=null)                           //ԭ��������
        {
            Node<T> mfront=srear, min=mfront.next;         //minָ����Сֵ��㣬mfrontָ��min��ǰ��
            Node<T> pfront=min, p=min.next;                //p����������pfrontָ��p��ǰ�����
            while (p!=null)
            {
                if (p.data.compareTo(min.data)<0)          //�Ƚϣ�min��ס��Сֵ���
                {   mfront = pfront;                       //mfront��min��ǰ�����
                    min = p;
                }
                pfront = p;                                //pfront��p��ǰ�����
                p = p.next;
            }  
            if (mfront!=srear)
            {
                mfront.next = min.next;                    //������ԭλ��ɾ��min���
                min.next=srear.next;                       //��min������srear֮��
                srear.next = min;
            }
            srear = min;                                   //srearָ����������β 
        }
    }

*/
