import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
CountDownLatch是一个同步工具类，用来协调多个线程之间的同步。这个工具通常用来控制线程等待，它可以让某一个线程等待直到倒计时结束，再开始执行

两种典型用法
1. 某一线程在开始运行前等待n个线程执行完毕。将 CountDownLatch 的计数器初始化为n ：new CountDownLatch(n) ，
    每当一个任务线程执行完毕，就将计数器减1 countdownlatch.countDown()，
    当计数器的值变为0时，在CountDownLatch上 await() 的线程就会被唤醒。
    一个典型应用场景就是启动一个服务时，主线程需要等待多个组件加载完毕，之后再继续执行。

2. 实现多个线程开始执行任务的最大并行性。注意是并行性，不是并发，强调的是多个线程在某一时刻同时开始执行。
    类似于赛跑，将多个线程放到起点，等待发令枪响，然后同时开跑。
    做法是初始化一个共享的 CountDownLatch 对象，将其计数器初始化为 1 ：new CountDownLatch(1) ，
    多个线程在开始执行任务前首先 coundownlatch.await()，
    当主线程调用 countDown() 时，计数器变为0，多个线程同时被唤醒。


CountDownLatch是一次性的，计数器的值只能在构造方法中初始化一次，之后没有任何机制再次对其设置值，当CountDownLatch使用完毕后，它不能再次被使用
*/
public class CountDownLatchExample1 {
	// 请求的数量
	private static final int threadCount = 550;

	public static void main(String[] args) throws InterruptedException {
		// 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
		ExecutorService threadPool = Executors.newFixedThreadPool(300);
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		for (int i = 0; i < threadCount; i++) {
			final int threadnum = i;
			threadPool.execute(() -> {
				try {
					test(threadnum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					countDownLatch.countDown();// 表示一个请求已经被完成
				}

			});
		}
        countDownLatch.await(); // 注释掉这一句 会先输出finish
		threadPool.shutdown();
        System.out.println("finish");
        // 我们定义了请求的数量为550，当这550个请求被处理完成之后，才会执行System.out.println("finish");
	}

	public static void test(int threadnum) throws InterruptedException {
		Thread.sleep(1000);// 模拟请求的耗时操作
		System.out.println("threadnum:" + threadnum);
		Thread.sleep(1000);// 模拟请求的耗时操作
	}
}