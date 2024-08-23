import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {

        BlockingQueue blockingQueue=new BlockingQueue();
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
// Класс Магазин, хранящий произведенные товары
class BlockingQueue {
    private Queue<String> queue = new PriorityQueue<>();
    public synchronized void deque() {
        while (queue.size() < 1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        String removedTask = queue.remove();
        System.out.println("Потребитель сделал таску "+ removedTask);
        System.out.println("Всего таск: " + queue.size());
        notify();
    }
    public synchronized void enqueue(String task) {
        while (queue.size()>=5) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        queue.add(task);
        System.out.println("Производитель добавил таску " + task);
        System.out.println("Всего таск: " + queue.size());
        notify();
    }
}
class Producer implements Runnable{

    BlockingQueue blockingQueue;
    Producer(BlockingQueue blockingQueue){
        this.blockingQueue =blockingQueue;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            blockingQueue.enqueue("task " + i);
        }
    }
}

class Consumer implements Runnable{

    BlockingQueue blockingQueue;
    Consumer(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }
    public void run(){
        for (int i = 1; i < 50; i++) {
            blockingQueue.deque();
        }
    }
}