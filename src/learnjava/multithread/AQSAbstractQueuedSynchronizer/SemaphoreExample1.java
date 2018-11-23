import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

// 需要一次性拿一个许可的情况
public class SemaphoreExample1 {
	// 请求的数量
	private static final int threadCount = 550;

	public static void main(String[] args) throws InterruptedException {
		// 创建一个具有固定线程数量的线程池对象（如果这里线程池的线程数量给太少的话你会发现执行的很慢）
		ExecutorService threadPool = Executors.newFixedThreadPool(300);
		// 一次只能允许执行的线程数量。
		final Semaphore semaphore = new Semaphore(20);

		for (int i = 0; i < threadCount; i++) {
			final int threadnum = i;
			threadPool.execute(() -> {// Lambda 表达式的运用
				try {
                    semaphore.acquire();// 获取一个许可，所以可运行线程数量为20/1=20
                    // 执行 acquire 方法阻塞，直到有一个许可证可以获得然后拿走一个许可证
					test(threadnum);
                    semaphore.release();// 释放一个许可
                    // 每个 release 方法增加一个许可证，这可能会释放一个阻塞的acquire方法

                    /*
                    其实并没有实际的许可证这个对象，Semaphore只是维持了一个可获得许可证的数量。 
                    Semaphore经常用于限制获取某种资源的线程数量
                    */

                    // 也可以一次拿取和释放多个许可
                    // semaphore.acquire(5);// 获取5个许可，所以可运行线程数量为20/5=4
					// test(threadnum);
					// semaphore.release(5);// 获取5个许可，所以可运行线程数量为20/5=4
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			});
		}
		threadPool.shutdown();
		System.out.println("finish");
	}

	public static void test(int threadnum) throws InterruptedException {
		Thread.sleep(1000);// 模拟请求的耗时操作
		System.out.println("threadnum:" + threadnum);
		Thread.sleep(1000);// 模拟请求的耗时操作
	}
}