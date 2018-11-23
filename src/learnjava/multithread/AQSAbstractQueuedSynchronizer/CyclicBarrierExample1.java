import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/*
CyclicBarrier 的字面意思是可循环使用（Cyclic）的屏障（Barrier）。
它要做的事情是，让一组线程到达一个屏障（也可以叫同步点）时被阻塞，
直到最后一个线程到达屏障时，屏障才会开门，所有被屏障拦截的线程才会继续干活。

CyclicBarrier默认的构造方法是 CyclicBarrier(int parties)，
其参数表示屏障拦截的线程数量，每个线程调用await方法告诉 CyclicBarrier 
我已经到达了屏障，然后当前线程被阻塞。
*/
public class CyclicBarrierExample1 {
	// 请求的数量
	private static final int threadCount = 550;
	// 需要同步的线程数量
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

	public static void main(String[] args) throws InterruptedException {
		// 创建线程池
		ExecutorService threadPool = Executors.newFixedThreadPool(10);

		for (int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			threadPool.execute(() -> {
				try {
					test(threadNum);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (BrokenBarrierException e) {
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
	}

	public static void test(int threadnum) throws InterruptedException, BrokenBarrierException {
		System.out.println("threadnum:" + threadnum + "is ready");
        try {
            cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("-----CyclicBarrierException------");
        }
		System.out.println("threadnum:" + threadnum + "is finish");
	}

}