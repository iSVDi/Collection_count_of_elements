import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Синхронизация потоков с использованием CyclicBarrier и ExecutorService
 * В этой задаче мы будем использовать CyclicBarrier и ExecutorService для синхронизации нескольких потоков, выполняющих сложную задачу,
 * и затем ожидающих, пока все потоки завершат выполнение, чтобы объединить результаты.


 Создайте класс ComplexTask, представляющий сложную задачу, которую несколько потоков будут выполнять.
 В каждой задаче реализуйте метод execute(), который выполняет часть сложной задачи.
 * Создайте класс ComplexTaskExecutor, в котором будет использоваться CyclicBarrier и ExecutorService для синхронизации выполнения задач.
 * Реализуйте метод executeTasks(int numberOfTasks), который создает пул потоков и назначает каждому потоку экземпляр сложной задачи для выполнения.
 * Затем используйте CyclicBarrier для ожидания завершения всех потоков и объединения результатов их работы.
 * В методе main создайте экземпляр ComplexTaskExecutor и вызовите метод executeTasks с несколькими задачами для выполнения.
 */

public class Main {
    public static void main(String[] args) {

        ComplexTaskExecutor executor = new ComplexTaskExecutor();
        try {
            executor.executeTasks(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }




}