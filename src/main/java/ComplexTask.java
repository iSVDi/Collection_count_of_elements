import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

import static java.lang.Thread.sleep;

public class ComplexTask implements Callable<Integer> {
    private int id;
    private CyclicBarrier barrier;
    ComplexTask(int id, CyclicBarrier barrier) {
        this.id = id;
        this.barrier = barrier;
    }

    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 5; i++) {
            System.out.println("TASK " + id + " complete subtask " + i);
            sleep(1000);
            barrier.await();
        }

        return 0;
    }
}
