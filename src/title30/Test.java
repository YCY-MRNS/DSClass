package title30;

import java.util.Scanner;

/**
 * @program: DSClass
 * @description:
 * @author: ChangYue
 * @create: 2019-06-26 20:28
 */
public class Test {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String str=sc.nextLine();//输入一个括号序列，长度不超过10000。
        char[] str1=str.toCharArray();//将这个序列转换成Char数组
        boolean flag=true;//创建一个开关变量,默认真
        SeqStack<Character>staffs=new SeqStack<Character>();
        for(int i=0;i<str1.length;++i){//开始判断括号序列的括号
            if(str1[i]=='('||str1[i]=='{'||str1[i]=='[') {//或运算必须是三种前括号之一
                staffs.push(str1[i]);//入栈
            }else if(str1[i]==')'||str1[i]=='}'||str1[i]==']')//或运算必须是三种后括号之一
            {
                if(staffs.isEmpty())//判空
                {
                    flag=false;//开关变量为假
                    break;//退出循环
                }
                char tmp=staffs.peek();//temp等于 栈顶元素
                staffs.pop();	//出栈
                if(str1[i]==')'&&tmp!='('||str1[i]==']'&&tmp!='['||str1[i]=='}'&&tmp!='{')//str1[i]等于)，temp不等于(//判断是否美观
                {
                    flag=false;//开关变量为假
                    break;//退出循环
                }
            }
        }
        if(flag)//开关变量为真
            System.out.print("Yes");
        else//开关变量为假
            System.out.print("No");

    }
}
