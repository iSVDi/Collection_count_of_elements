import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private final Integer n;

    public FactorialTask(Integer n) {
        this.n = n;
    }

    @Override
    protected Integer compute() {
        if (n == 1)
            return n;
        FactorialTask task = new FactorialTask(n - 1);
        task.fork();
        return n * task.join();
    }
}
