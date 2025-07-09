import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LearnThreadPool {
    public static void main(String[] args) {
        int numTasks = Integer.parseInt(args[0].trim());
        Runnable task = () -> {
            System.out.println("Current time = " + System.currentTimeMillis());
        };

        // Unlimited pool
        ExecutorService pool = Executors.newCachedThreadPool();

        for (int i = 0; i < numTasks; i++) {
            pool.execute(task);
        }

        pool.close();
    }
}
