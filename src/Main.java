import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        var executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        List<MyCallable> tasks = new ArrayList<>();

        tasks.add(new MyCallable("поток 1"));
        tasks.add(new MyCallable("поток 2"));
        tasks.add(new MyCallable("поток 3"));
        tasks.add(new MyCallable("поток 4"));

        List<Future<Integer>> futures;
        futures = executorService.invokeAll(tasks);

        System.out.println("Выведено сообщений в консоль: " + sumFuturesTask(futures) + "\n");

        int res = executorService.invokeAny(tasks);
        System.out.println("Результат выполнения самого быстрого потока invokeAny(tasks): " + res);

        executorService.shutdown();
    }

    private static int sumFuturesTask(List<Future<Integer>> futures) throws ExecutionException, InterruptedException {
        int count = 0;
        for (Future<Integer> future : futures) {
            count += future.get();
        }
        return count;
    }
}
