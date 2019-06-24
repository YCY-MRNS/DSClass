package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��10��23��
//4.2.1   ���г�����������

//ADT Queue<T>                        //���г����������ͣ�T��ʾ����Ԫ�ص���������
//����ͬjava.util.Queue<T>

public interface Queue<T>              //���нӿڣ��������г����������ͣ�T��ʾ����Ԫ�ص���������
{
    public abstract boolean isEmpty();           //�ж϶����Ƿ��
    public abstract boolean add(T x);            //Ԫ��x��ӣ�����ӳɹ����򷵻�true�����򷵻�false
    public abstract T peek();                    //���ض�ͷԪ�أ�û��ɾ���������пգ��򷵻�null
    public abstract T poll();                    //���ӣ����ض�ͷԪ�ء������пգ��򷵻�null
}

//@author��Yeheya��2014-10-26