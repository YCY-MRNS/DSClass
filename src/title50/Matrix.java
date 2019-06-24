package title50;//�����ݽṹ��Java�棩����4�棩�������ߣ�Ҷ���ǣ�2014��7��11��
//5.1   ����
//����5.1��  �����ࡣ                                              //7.2.1 ͼ���ڽӾ����ʾ��ʵ��  ��

public class Matrix                                        //������
{
	protected int rows, columns;                           //��������������
    protected int[][] element;                             //��ά���飬�洢����Ԫ��

    public Matrix(int m, int n)                            //����m��n�������m��nΪ������Java�׳������鳤���쳣
    {
        this.element = new int[m][n];                      //����Ԫ�س�ֵΪ0
        this.rows = m;
        this.columns = n;
    }
    public Matrix(int n)                                   //����n��n�㷽��
    {
        this(n,n); 
    }
    public Matrix(int m, int n, int[][] value)             //����m��n������value[][]�ṩԪ��
    {
        this(m, n);
        for (int i=0; i<value.length && i<m; i++)          //valueԪ�ز���ʱ��0�����Զ���Ԫ��
            for (int j=0; j<value[i].length && j<n; j++)
               this.element[i][j] = value[i][j];
    }

    public int getRows()                                   //���ؾ�������
    {
        return this.rows;
    }
    public int getColumns()                                //���ؾ�������
    {
        return this.columns;
    }
    public int get(int i, int j)                 //���ؾ����i�е�j��Ԫ�ء���i��jԽ�磬�׳����Խ���쳣
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
            return this.element[i][j];
        throw new IndexOutOfBoundsException("i="+i+"��j="+j);
    }
    public void set(int i, int j, int x)         //���þ����i�е�j��Ԫ��Ϊx����i��jԽ�磬�׳����Խ���쳣
    {
        if (i>=0 && i<this.rows && j>=0 && j<this.columns) 
            this.element[i][j]=x;
        else throw new IndexOutOfBoundsException("i="+i+"��j="+j);
    }
    
    public String toString()                               //���ؾ�������Ԫ�ص������ַ��������������
    {
        String str=" ����"+this.getClass().getName()+"��"+this.rows+"��"+this.columns+"����\n";
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                str+=String.format("%6d", this.element[i][j]); //"%6d"��ʽ��ʾʮ��������ռ6��
            str += "\n";
        }
        return str;
    }
    
    //���þ���Ϊm��n�С�������ָ���������ϴ��򽫾������ݣ�������ԭ����Ԫ�ء�
    //����7.2.1��ͼ���ڽӾ���洢�ṹ
    public void setRowsColumns(int m, int n)
    {
        if (m>0 && n>0)
        {
            if (m>this.element.length || n>this.element[0].length)
            {                                                  //����ָ���������������ϴ�ʱ�������ά��������
                int[][] source = this.element;
                this.element = new int[m*2][n*2];              //���������ά����ռ䣬Ԫ�س�ֵΪ0
                for (int i=0; i<this.rows; i++)                //����ԭ��ά����Ԫ��
                    for(int j=0; j<this.columns; j++)
                        this.element[i][j] = source[i][j];
            }
            this.rows = m;
            this.columns = n;
        }
        else throw new IllegalArgumentException("�������������ܡ�0��m="+m+"��n="+n);
    }
}
/*
��֧������Ĭ�Ϲ��췽��������ָ��������
    public Matrix()                              //Ĭ�Ϲ��췽��������10��10�վ��󣬳�ֵΪ0
    {
        this(10,10);
    }
*/

//@author��Yeheya��2015-3-1
