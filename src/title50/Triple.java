package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��30��
//��5.2.2 ϡ������ѹ���洢
//1.  ��ʾϡ��������Ԫ�ص���Ԫ��

//��7.2.1   ͼ���ڽӾ����ʾ��ʵ��

//ϡ��������Ԫ����Ԫ���࣬ͼ��Ȩֵ�ı���
public class Triple implements Comparable<Triple>, Addible<Triple>
{
    int row, column, value;                                //�кš��кš�Ԫ��ֵ��Ĭ�Ϸ���Ȩ��
                                                       //�кţ��ߵ������ţ����кţ��յ���ţ���Ԫ��ֵ��Ȩֵ����7.2.2��
    
    //���췽��������ָ���кš��кš�Ԫ��ֵ�����кš��к�Ϊ�������׳���Ч�����쳣
    public Triple(int row, int column, int value)
    {
        if (row>=0 && column>=0)
        {
            this.row = row;
            this.column = column;
            this.value = value;
        }
        else throw new IllegalArgumentException("�С��кŲ���Ϊ������row="+row+"��column="+column);
    }
    public Triple(Triple tri)                             //�������췽��������һ����Ԫ��
    {
        this(tri.row, tri.column, tri.value);
    }

    public String toString()                               //������Ԫ�������ַ���
    {
        return "("+row+","+column+","+value+")";
    }

    //7.2.2��ͼɾ��������//ϰ��5��ת�þ�����
    public Triple toSymmetry()                            //���ؾ���Գ�λ��Ԫ�ص���Ԫ�顣
    {
        return new Triple(this.column, this.row, this.value);
    }
    
    public boolean equals(Object obj)                      //�Ƚ�������Ԫ���Ƿ���ȣ��Ƚ�λ�ú�Ԫ��ֵ
    {
        if (this==obj)
            return true;
        if (!(obj instanceof Triple))
            return false;
        Triple tri = (Triple)obj;
        return this.row==tri.row && this.column==tri.column && this.value==tri.value;
    }

    //�����С���λ�ñȽ���Ԫ������С����Ԫ��ֵ�޹أ�Լ����Ԫ���������
    public int compareTo(Triple tri)
    {
        if (this.row==tri.row && this.column==tri.column)
            return 0;                                      //��ȣ���equals()�������岻ͬ
        return (this.row<tri.row || this.row==tri.row && this.column<tri.column)?-1:1;
    }

    public void add(Triple term)                           //�ӷ�����=���㣩��ʵ��Addible<T>�ӿ�
    {
        if (this.compareTo(term)==0)
            this.value += term.value;
        else
            throw new IllegalArgumentException("�����ָ����ͬ��������ӡ�");
    }
    public boolean removable()                             //Լ��ɾ��Ԫ��������ʵ��Addible<T>�ӿ�
    {
        return this.value==0;                              //���洢ֵΪ0��Ԫ��
    }
}
//����5.2.2��
//@author��Yeheya��2015-10-11

/*û���õ�
    public Triple plus(Triple term)                 //�ӷ��������������
    {
    	Triple tmp = new Triple(this);             //�������췽��
        tmp.add(term);
    	return tmp;
    } * 
 * û��
    public Triple()
    {
        this(10,10,0);
    }
 */