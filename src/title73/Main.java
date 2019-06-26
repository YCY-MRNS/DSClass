package title73;

import java.util.*;

/**
 * @program: DSClass
 * @description: Test
 * @author: ChangYue
 * @create: 2019-06-21 08:40
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] num = in.nextLine().split("\\s+");
        int N = Integer.parseInt(num[0]);
        int K = Integer.parseInt(num[1]);

        List<cow> c = new ArrayList<>(N);

        for (int i = 0; i < N; i++) {
            String[] corws = in.nextLine().split("\\s+");
            c.add(new cow(Integer.parseInt(corws[0]), Integer.parseInt(corws[1]), i + 1));
        }
        c.sort(new corwFirstComparator());

        for (int i = 0; i < (N - K); i++) {
            c.remove(K);
        }

        c.sort(new corwSecondComparator());
        System.out.println(c.get(0).getNum());
        in.close();
    }
}

class cow {

    private int first;
    private int second;
    private int num;

    public cow(int first, int second, int num) {
        this.first = first;
        this.second = second;
        this.num = num;
    }

    public int getFirst() {
        return first;
    }


    public int getSecond() {
        return second;
    }


    public int getNum() {
        return num;
    }

}

class corwFirstComparator implements Comparator<cow> {
    @Override
    public int compare(cow o1, cow o2) {
        return o2.getFirst() - o1.getFirst();
    }
}

class corwSecondComparator implements Comparator<cow> {
    @Override
    public int compare(cow o1, cow o2) {
        return o2.getSecond() - o1.getSecond();
    }
}

