package title20;

import java.util.ArrayList;
import java.util.Scanner;

public class Main20 {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();//实例化ArrayList
        Scanner sc = new Scanner(System.in);
        String a1 = sc.nextLine();//获取键盘输入的第一行数据，分别为总共的猴子个数、移除的猴子序号并将其存放于数组中
        String a11[] = a1.split(" ");
        int n = Integer.parseInt(a11[1]);//使用变量n存放每次将要移除的猴子的编号
        for (int j = 1; j <= Integer.parseInt(a11[0]); j++) {//将所有的猴子编号加入到ArrayList中
            list.add(j);
        }
        int count = 0;//初始化计数变量count，用于标记猴子编号使其循环遍历
        while (list.size() > 0) {//循环移除满足条件的猴子编号，循环条件为ArrayList不为空
            count = count + n;//将count的值变为每次将要移除的猴子的编号
            count = count % (list.size()) - 1;//将count转化为实际要删除的下标
            //当count和猴子数量相同时，count的值为-1，则进入判断 ，若猴子数量为1 则该猴子的编号 为最终的猴王编号
            if (count < 0) {
                if (list.size() == 1) {
                    System.out.println(list.get(list.size() - 1));
                }
                //猴子数量不为1 则执行删除最后一个
                list.remove(list.size() - 1);
                //形成环形 重新从第一猴子移除
                count = 0;
            } else {
                list.remove(count);
            }
        }
    }

}
