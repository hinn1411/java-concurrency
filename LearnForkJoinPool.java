import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Integer> {
    static final int THRESHOLD = 3;
    private int begin;
    private int end;
    private int[] array;

    public SumTask(int begin, int end, int[] array) {
        this.begin = begin;
        this.end = end;
        this.array = array;
    }

    @Override
    protected Integer compute() {
        if (end - begin < THRESHOLD) {
            int sum = 0;
            for (int i = begin; i <= end; i++) {
                sum += array[i];
            }
            return sum;
        }
        int mid = (begin + end) / 2;
        SumTask leftTask = new SumTask(begin, mid, array);
        SumTask rightTask = new SumTask(mid + 1, end, array);

        leftTask.fork();
        rightTask.fork();

        return  leftTask.join() + rightTask.join();
    }
}

public class LearnForkJoinPool {
    final static double MAX = 100;
    public static void main(String[] args) {
        final int SIZE = Integer.parseInt(args[0].trim());

        ForkJoinPool pool = new ForkJoinPool();
        int[] arr = genArray(SIZE);


        SumTask task = new SumTask(0, SIZE - 1, arr);
        int sum = pool.invoke(task);
        System.out.println("Sum = " + sum);
    }

    public static int[] genArray(int size) {
        int[] arr = new int[size];
        int current;
        for (int i = 0; i < size; i++) {
            current = (int) Math.rint(Math.random() * MAX);
            arr[i] = current;
        }
        System.out.println(Arrays.toString(arr));
        return arr;
    }
}
