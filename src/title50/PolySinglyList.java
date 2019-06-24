package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��9��15��
//��2.4   ���Ա��Ӧ�ã�����ʽ�ı�ʾ������
//��2.4.1   һԪ����ʽ�ı�ʾ������

//����ʽ���������࣬�̳����������࣬�ṩ��������Ķ���ʽ������㣻
//T��T��ĳ�����������ʵ��Comparable<T>�ӿڣ�Լ������Ƚϴ�С�Ĺ���Tʵ��Addible<T>����ӽӿ�
public class PolySinglyList<T extends Comparable<? super T> & Addible<T>>
        extends SortedSinglyList<T>
{
    public PolySinglyList()                                //���췽��
    {
        super();                                           //�����յ�����
    }
    public PolySinglyList(T terms[])                       //���췽������������ָ������ʽ����ֵ
    {
        super(terms);
    }
    public PolySinglyList(PolySinglyList<T> list)          //�������췽��
    {
        super(list);                                       //������������������н�㣬û�и��ƶ���
    }
       
    public void addAll(PolySinglyList<T> list)             //����ʽ��ӣ�this��=list���ܣ����ı�list
    {
        Node<T> front=this.head, p=front.next;
        Node<T> q=list.head.next;
        while (p!=null && q!=null)
            if (p.data.compareTo(q.data)==0)               //�����С��ͬ
            {
                p.data.add(q.data);                        //������ӣ�add()������Addible�ӿ�Լ��
                if (p.data.removable())                    //��Ӻ�Ԫ������ɾ������
                {                                          //removable()������Addible�ӿ�Լ��
                    front.next=p.next;                     //��Ӻ�Ԫ�ز���Ҫ�洢��ɾ��p���
                    p=front.next;
                }
                else 
                {
                    front = p;                             //front��p��ǰ�����
                    p = p.next;
                }
                q = q.next;
            }
            else if (p.data.compareTo(q.data)<0)
                 {
                     front = p;       
                     p = p.next;
                 }
                 else
                 {
                     front.next = new Node<T>(q.data, p);  //����q��㲢���뵽front���֮��
                     q = q.next;
                 }
        while (q!=null)                                    //��list��������ʣ���㸴�Ʋ����뵽��ǰ����β
        {
            front.next = new Node<T>(q.data, null);
            front = front.next;
            q = q.next;
        }
    }
}
//@author��Yeheya��2014-9-16
/* 
public void insert(Term term)                          //������
{
    list.insert(term);                                 //�����������в����㣬����λ����term��ָ������
}*/
/*
//��2�� ����ʽ����㷨
public Polynomial (Polynomial pol)                 //�ӷ�����C=this��pol
{
    Polynomial cpol = new Polynomial();
    Node<Term> p=this.list.head.next;            
    Node<Term> q=pol.list.head.next;
    Node<Term> rear=cpol.list.head;
    while (p!=null && q!=null)
    {
        if (p.data.compareTo(q.data)==0)               //����ָ����ͬʱ
        {
            double sum=p.data.coef + q.data.coef;      //����ϵ�����
            if (Math.abs(sum)>0.00001)                 //�������Ƿ�Ϊ0�ɾ���ȷ��
            {
                rear.next=new Node<Term>(new Term(sum, p.data.exp), null);//���������ӷ�0ϵ�����
                rear=rear.next;
            }
            p = p.next;
            q = q.next;
        }
        else if (p.data.compareTo(q.data)<0)
        {
            rear.next = new Node<Term>(p.data, null);  //����p��㲢���뵽rear���֮��
            rear=rear.next;
            p = p.next;
        }
        else
        {
            rear.next = new Node<Term>(q.data, null);  //����q��㲢���뵽rear���֮��
            rear=rear.next;
            q = q.next;
        }
    }
    if (p==null)
        p=q;
    while (p!=null)                                    //����ǰ�����pol������ʣ���㸴�Ʋ����뵽cpol����β
    {
        rear.next = new Node<Term>(p.data, null);
        rear=rear.next;
        p = p.next;
    }
    return cpol;  
}
    public PolySLinkedList<T> plus(PolySLinkedList<T> list)    //�ӷ�����C=this��list
    {
    	PolySLinkedList<T> polyc=new PolySLinkedList<T>(this);   //���
        polyc.add(list);
        return polyc;                                      //���ض�������
    }*/
