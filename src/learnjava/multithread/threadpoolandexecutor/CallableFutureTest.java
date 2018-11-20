import java.util.concurrent.*;

public class CallableFutureTest {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        Callable<Integer> task = () -> {
            System.out.println("子线程在进行计算");
            Thread.sleep(3000);
            int sum = 0;
            for(int i=0;i<100;i++)
                sum += i;
            return sum;
        };

        Future<Integer> result = executor.submit(task);
        executor.shutdown();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程在执行任务");

        try {
            // 方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回
            System.out.println("task运行结果: " + result.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("all task done.");
    }
}
