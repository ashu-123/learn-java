package udemy.concurrency;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    public static void main(String[] args) {
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread firstThread = new Thread(new FirstThread(exchanger));
        Thread secondThread = new Thread(new SecondThread(exchanger));

        firstThread.start();
        secondThread.start();
    }
}

class FirstThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger;

    public FirstThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {

        while (true) {
            this.counter++;
            System.out.println("First thread incremented the counter: " + counter);
            try {
                counter = exchanger.exchange(counter);
                System.out.println("First thread received the counter with value: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class SecondThread implements Runnable {

    private int counter;
    private final Exchanger<Integer> exchanger;

    public SecondThread(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        while (true) {
            this.counter--;
            System.out.println("Second thread decremented the counter: " + counter);
            try {
                counter = exchanger.exchange(counter);
                System.out.println("Second thread received the counter with value: " + counter);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
