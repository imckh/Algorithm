import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    public static void main(String[] args) {

        DemoRunnable dr = new DemoRunnable();

        for (int i = 0; i < 10; i++) {
            new Thread(dr).start();
        }
    }
}
class DemoRunnable implements Runnable {
    private volatile AtomicInteger ai = new AtomicInteger();
    /*
    支持原子操作
     */

    private volatile int count = 0;
    /*
    从结果我们可以发现 count 的值没有加到 10，且出现多个线程累加 count 值重复的问题，即出现了线程安全问题

    在 Java 语言中执行 ++ 操作实际上在底层被拆分为三个步骤，即“读-改-写”
    i = i++ 在底层操作如下：

    int temp = i; // 读
    i = i + 1;    // 改
    i = temp;     // 写
    同样被拆分为多个步骤，无法保证操作的原子性，从而当多个线程修改 count 时出现对旧值重复累加操作，进而出现打印相同结果的问题
     */

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程-AtomicInteger:" + (ai.incrementAndGet()));
        System.out.println("子线程-count:" + (count++));
    }
}