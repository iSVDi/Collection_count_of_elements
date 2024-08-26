import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ComplexTaskExecutor {
    private ExecutorService executorService;
    private CyclicBarrier barrier;

    void executeTasks(int numOfTasks) throws InterruptedException {
        barrier = new CyclicBarrier(numOfTasks);
        executorService = Executors.newFixedThreadPool(numOfTasks);
        List<Callable<Integer>> taskList = new ArrayList<>();
        IntStream.range(0, numOfTasks).forEach(i ->  {
                ComplexTask task = new ComplexTask(i, barrier);
                taskList.add(task);
        });
        List<Future<Integer>> futures = executorService.invokeAll(taskList);
        executorService.shutdown();
    }
}
