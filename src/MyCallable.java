import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    private String name;

    public MyCallable(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Integer call() throws InterruptedException {
        int count = 0;

        while (count < 3) {
            Thread.sleep(2500);
            System.out.println("Я " + getName() + ". Всем привет!");
            count++;
        }

        return count;
    }
}
