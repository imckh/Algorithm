import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        // 创建一个FixedThreadPool
        ExecutorService executor = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            // 创建WorkerThread对象
            Runnable worker = new WorkerThread("" + i);
            // 执行Runnable
            executor.execute(worker);
        }

        // 中止线程池
        /*
        shutdown() VS shutdownNow()
        shutdown()方法表明关闭已在Executor上调用,
            因此不会再向DelayedPool添加任何其他任务()ScheduledThreadPoolExecutor类在内部使用.
            但是, 已经在队列中提交的任务将被允许完成.
        另一方面, shutdownNow()方法试图终止当前正在运行的任务,
            并停止处理排队的任务并返回正在等待执行的List
         */
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}

class WorkerThread implements Runnable{
    private String command;

    public WorkerThread (String command) {
        this.command = command;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
        processCommand();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "ThreadPoolExecutorDemo{" +
                "command='" + command + '\'' +
                '}';
    }
}
