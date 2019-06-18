package queue;


public class Phone {
    public static void main(String[] args) {
        SeqQueue<Integer> missCall = new SeqQueue<>(11);
        SeqQueue<Integer> called = new SeqQueue<>(11);
        SeqQueue<Integer> recentCalled = new SeqQueue<>(11);

        int phone = 0;
        while (!missCall.isFull()) {
            phone = (int) (Math.random() * 1000000);



        }

        System.out.println(missCall);


    }
}
