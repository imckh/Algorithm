import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor主要用来在给定的延迟后运行任务, 或者定期执行任务.
 *
 * ScheduledThreadPoolExecutor使用的任务队列DelayQueue封装了一个PriorityQueue,
 * PriorityQueue会对队列中的任务进行排序,
 * 执行所需时间短的放在前面先被执行(ScheduledFutureTask的time变量小的先执行),
 * 如果执行所需时间相同则先提交的任务将被先执行(ScheduledFutureTask的squenceNumber变量小的先执行)
 */
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个SchaduledThreadPoolExecutor对象
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        // 计划在某段时间内运行
        System.out.println("Current Time = "+new Date());

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            WorkerThread worker = new WorkerThread("do heavy processing");

            // 1.
            // 创建并执行在给定延迟后启用的单次操作
            // scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);

            // 2.
            // 我们可以使用ScheduledExecutorService scheduleAtFixedRate方法来安排任务在初始延迟后运行, 然后在给定的时间段内运行
            // 时间段是从池中第一个线程的开始, 因此如果您将period指定为1秒并且线程运行5秒, 那么只要第一个工作线程完成执行，下一个线程就会开始执行
            // schedule task to execute at fixed rate
            // scheduledThreadPool.scheduleAtFixedRate(worker, 0, 10, TimeUnit.SECONDS);

            // 3.
            // ScheduledExecutorService scheduleWithFixedDelay方法可用于以初始延迟启动周期性执行,
            // 然后以给定延迟执行.
            // 延迟时间是线程完成执行的时间
            // scheduledThreadPool.scheduleWithFixedDelay(worker, 0, 1, TimeUnit.SECONDS);

            /*
            scheduleAtFixedRate(...)将延迟视为两个任务开始之间的差异(即定期调用)
            scheduleWithFixedDelay(...)将延迟视为一个任务结束与下一个任务开始之间的差异
            */
        }

        //添加一些延迟让调度程序产生一些线程
        Thread.sleep(30000);
        System.out.println("Current Time = "+new Date());

        //关闭线程池
        scheduledThreadPool.shutdown();
        while(!scheduledThreadPool.isTerminated()){
            //等待所有任务完成
        }
        System.out.println("Finished all threads");
    }
}
