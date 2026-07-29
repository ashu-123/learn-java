package udemy.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();
        list.addAll(Arrays.asList(0, 0, 0,0 ,0 ,0 ,0 , 0, 0, 0, 0, 0));

        Thread read = new Thread(new ReadTask(list));
        Thread writeOne = new Thread(new WriteTask(list));
        Thread writeTwo = new Thread(new WriteTask(list));
        Thread writeThree = new Thread(new WriteTask(list));

        read.start();
        writeOne.start();
        writeTwo.start();
        writeThree.start();
    }
}

class ReadTask implements Runnable {

    public ReadTask(List<Integer> list) {
        this.list = list;
    }

    private final List<Integer> list;

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(100);
                System.out.println(list);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class WriteTask implements Runnable {

    private final List<Integer> list;

    private final Random random;

    public WriteTask(List<Integer> list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public void run() {

        try {
            while (true) {
                Thread.sleep(150);
                list.set(random.nextInt(list.size()), random.nextInt(20));
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}