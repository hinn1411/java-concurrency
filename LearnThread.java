import java.util.concurrent.Executor;

class CTask implements Runnable {
    @Override
    public void run() {
        System.out.println("I'm a bad boy (:");
    }
}

public class LearnThread {
    public static void main(String[] args) {
        Runnable lambdaTask = () -> {
            System.out.println("I'm a good boy (:");
        };
        Thread classTask = new Thread(new CTask());
        classTask.start();
        Thread lambdaThread = new Thread(lambdaTask);
        lambdaThread.start();

        try {
            classTask.join();
        } catch (InterruptedException e) {

        }
    }
}
