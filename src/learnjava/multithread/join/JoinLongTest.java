
public class JoinLongTest {
    public static void main(String[] args) {
        try {
            MyThread threadTest = new MyThread();
            threadTest.start();

            // Thread.sleep(2000)不会释放锁，threadTest.join(2000)会释放锁
            threadTest.join(2000);// 只等2秒
            // Waits at most millis milliseconds for this thread to die
            // 就是设定的等待时间
            //Thread.sleep(2000);

            System.out.println("  end timer=" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static public class MyThread extends Thread {

        @Override
        public void run() {
            try {
                System.out.println("begin Timer=" + System.currentTimeMillis());
                Thread.sleep(10000);
                System.out.println("MyThread END Timer=" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}