package teat;//Programming in Java

import java.util.*;

public class radixSort {

    private static final HashMap<Character, Integer> alphaToNum = new HashMap();

    static {
        alphaToNum.clear();
     /*   initAlpha2Num('A', 'Z', alphaToNum);
        initAlpha2Num('a', 'z', alphaToNum);*/

    }

    private static final void initAlphaToNum(char c1, char c2, HashMap<Character, Integer> map) {
        for (char c = c1; c <= c2; c++)
            map.put(c, map.size());
    }

    public static void main(String[] args) {
        radixSort sortInstance = new radixSort();
        int[] data = {1, 4, 3, 3, 21, 12, 322, 44, 123, 2312, 765, 56, 8978, 10000, 14, 28, 65, 39, 81, 33, 100, 567};
        String[] data2 = {"abc", "Bde", "fad", "abd", "Bef", "fdd", "abe"};
        sortInstance.sort(data);
       // sortInstance.sort(data2);

        print(data);
        print(data2);
    }

    public static void print(String[] data) {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }

    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();
    }

    public void sort(int[] a) {
        int N = 10;
        ArrayList<Queue<Integer>> qArray;
        qArray = new ArrayList();
        for (int i = 0; i < N; i++)
            qArray.add(new <Integer>LinkedList());// 开辟空间
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; i++)
            max = Integer.max(max, a[i]);
        int[] length = new int[N];// 记录qArray的原始长度，防止对元素重复操作
        // radix sort
        for (int i = 0; i < a.length; i++)
            qArray.get(a[i] % 10).offer(a[i]);// 1 step
        for (int j = 10; j <= max; j *= 10) {
            for (int i = 0; i < N; i++)
                length[i] = qArray.get(i).size(); // 2 step update length
            for (int i = 0; i < N; i++) {
                Queue<Integer> q = qArray.get(i);
                while (length[i]-- > 0) {
                    int num = q.poll();
                    qArray.get((num / j) % 10).offer(num);
                }
            }
        }
        for (int i = 0, AIndex = 0; i < N; i++) // finally
            while (qArray.get(i).size() > 0)
                a[AIndex++] = qArray.get(i).poll();
    }

    public void sort(String[] s) {
        int N = alphaToNum.size();
        ArrayList<Queue<String>> qArray;
        qArray = new ArrayList();
        for (int i = 0; i < N; i++)
            qArray.add(new <String>LinkedList());// 开辟空间
        int strLen = s[0].length();
        int[] length = new int[N];// forbid duplicate operation
        for (int i = 0; i < s.length; i++)
            qArray.get(alphaIndex(s[i].charAt(s[i].length() - 1))).offer(s[i]);
        for (int j = strLen - 1; j > 0; j--) {
            for (int i = 0; i < N; i++)
                length[i] = qArray.get(i).size();
            for (int i = 0; i < N; i++) {
                Queue<String> q = qArray.get(i);
                while (length[i]-- > 0) {
                    String str = q.poll();
                    qArray.get(alphaIndex(str.charAt(j - 1))).offer(str);
                }
            }
        }
        for (int i = 0, AIndex = 0; i < N; i++) // finally
            while (qArray.get(i).size() > 0)
                s[AIndex++] = qArray.get(i).poll();
    }

    private int alphaIndex(char c) {
        return alphaToNum.get(c);
    }
}