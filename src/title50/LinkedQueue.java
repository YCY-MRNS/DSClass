package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��3��
//4.2.3   ��ʽ����

//��ʽ�����࣬�����࣬ʵ�ֶ��нӿڣ�T��ʾ����Ԫ�ص���������
public final class LinkedQueue<T>  implements Queue<T> 
{
    private Node<T> front, rear;                           //front��rear�ֱ�ָ���ͷ�Ͷ�β���

    public LinkedQueue()                                   //����ն���
    {
        this.front=this.rear=null;
    }
    public boolean isEmpty()                               //�ж϶����Ƿ�գ����շ���true
    {
        return this.front==null && this.rear==null;
    }

    public boolean add(T x)                                //Ԫ��x��ӣ��ն��������
    {
        if (x==null)
            return false;
//            throw new NullPointerException("x==null");      //�׳��ն����쳣
        Node<T> q = new Node<T>(x, null);
        if (this.front==null)
            this.front=q;                                  //�նӲ���
        else 
            this.rear.next=q;                              //����β����
        this.rear=q;
    	return true;
    } 

    public T peek()                                       //���ض�ͷԪ�أ�û��ɾ���������пգ��򷵻�null
    {
        return this.isEmpty() ? null : this.front.data;
    }
        
    public T poll()                                        //���ӣ����ض�ͷԪ�أ������пշ���null 
    {
        if (isEmpty())
            return null;
        T x = this.front.data;                             //ȡ�ö�ͷԪ��
        this.front = this.front.next;                      //ɾ����ͷ���
        if (this.front==null)
            this.rear=null;
        return x;
    } 

    public String toString()                               //���ض�������Ԫ�ص������ַ�������ʽΪ��(,)��
    {                                                      //�㷨ͬ����ͷ���ĵ�����
        StringBuffer strbuf = new StringBuffer(this.getClass().getName()+"(");
        for (Node<T> p=this.front;  p!=null;  p=p.next)
            strbuf.append(p.data.toString()+",");          //���ն���Ԫ�ش���������Ԫ��
        strbuf.setCharAt(strbuf.length()-1, ')');          //�����������һ���ַ�','��Ϊ')'
        return new String(strbuf);                         //��StringBuffer������String����
    }
}
//@author��Yeheya��2014-9-23