package queue;

public final class LinkedQueue<T>  implements Queue<T>
{
    private Node<T> front, rear;

    public LinkedQueue()
    {
        this.front=this.rear=null;
    }
    public boolean isEmpty()
    {
        return this.front==null && this.rear==null;
    }

    public boolean add(T x)
    {
        if (x==null)
        return false;
        Node<T> q = new Node<T>(x, null);
        if (this.front==null)
            this.front=q;
        else
            this.rear.next=q;
        this.rear=q;
        return true;
    } 

    public T peek()
    {
        return this.isEmpty() ? null : this.front.data;
    }
        
    public T poll()
    {
        if (isEmpty())
            return null;
        T x = this.front.data;
        this.front = this.front.next;
        if (this.front==null)
            this.rear=null;
        return x;
    } 

    public String toString()
    {
        StringBuffer strbuf = new StringBuffer(this.getClass().getName()+"(");
        for (Node<T> p=this.front;  p!=null;  p=p.next)
            strbuf.append(p.data.toString()+",");
        strbuf.setCharAt(strbuf.length()-1, ')');
        return new String(strbuf);
    }
}
