package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��6��22��
//��2.3 ���Ա����ʽ�洢��ʵ��
//1. ����������

public class Node<T>                             //���������࣬Tָ������Ԫ������
{
    public T data;                               //�����򣬴洢����Ԫ��
    public Node<T> next;                         //��ַ�����ú�̽��

    public Node(T data, Node<T> next)            //�����㣬dataָ������Ԫ�أ�nextָ����̽��
    {
        this.data = data;                        //T�������ø�ֵ
        this.next = next;                        //Node<T>�������ø�ֵ
    }
    public Node()
    {
        this(null, null);
    }
    public String toString()                     //���ؽ��������������ַ���
    {
        return this.data.toString();
    }
}
/*
    //�̲�û��д��û��ֱ�ӵ���
    public boolean equals(Object obj)            //�Ƚ��������ֵ�Ƿ���ȣ�����Object���equals(obj)����
    {
        return obj==this || obj instanceof Node<?> && this.data.equals(((Node<T>)obj).data);
    }
}
//@author��Yeheya��2014-10-9

/*
1��  ��һ����û���������췽��ʱ��Java�ṩĬ�Ϲ��췽��������
    public Node()                                  //�ṩĬ�Ϲ��췽��
    {
        super();                                   //Ĭ�ϵ������
    }
          ��һ���������˹��췽��ʱ��Java�����ṩĬ�Ϲ��췽����
���磬��Node��������Node(T data, Node<T> next)���췽��ʱ��Java�����ṩĬ�Ϲ��췽��Node()��
���Node����ҪNode()���췽���������Լ�������

2��  Java��������û��Ĭ��ֵ�����磬Node������������¹��췽��
    public Node(T data)
    {
        this(data, null);
    }
    �����ܽ�Node(T data, Node<T> next)���췽������Ϊ������ʽ��
    public Node(T data, Node<T> next=null)
    
3��Java���ṩĬ�Ͽ������췽����
  Node�಻��Ҫ�������췽�������������췽�����£�����p���õĽ�㣬
    public Node(Node<T> p)            //�������췽��
    {
        this(p.data, p.next); 
    }
�൱��
    public Node(Node<T> p)
    {
        this.data = p.data;
        this.next = p.next;        //��p�ĺ�̽����Ϊ��ǰ����ĺ�̽�㣬�߼�����
    }
    
5�������������£��ȽϽ��ֵ��С
public class Node<T> implements Comparable<Node<T>>   //����������
{
    public int compareTo(Node<T> p)                  //�Ƚ���ȣ��Ƚϴ�С
    {
        return this.data.compareTo(p.data);          //�����T��û��compareTo()����
    }
}
��������Ӧ��Ҫ��Ƚ�T�����С������Ҫ��ȽϽ���С��
*/

//@author��Yeheya��2014-9-8
