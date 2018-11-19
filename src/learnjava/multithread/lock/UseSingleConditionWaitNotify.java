import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseSingleConditionWaitNotify {
    public static void main(String[] args) throws InterruptedException {

        MyService service = new MyService();
        // 使用单个Condition实例实现等待/通知机制
        ThreadA a = new ThreadA(service);
        a.start();

        Thread.sleep(3000);

        service.signal();
    }

    static public class MyService {
        private Lock lock = new ReentrantLock();
        public Condition condition = lock.newCondition();

        public void await() {
            // 必须在condition.await()方法调用之前调用lock.lock()代码获得同步监视器，不然会报错
            lock.lock();
            try {
                System.out.println(" await时间为" + System.currentTimeMillis());
                condition.await();
                System.out.println("这是condition.await()方法之后的语句，condition.signal()方法之后我才被执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 在使用wait/notify实现等待通知机制的时候我们知道必须执行完notify()方法所在的synchronized代码块后才释放锁。
        // 在这里也差不多，必须执行完signal所在的try语句块之后才释放锁，condition.await()后的语句才能被执行。
        public void signal() throws InterruptedException {
            lock.lock();
            try {
                System.out.println("signal时间为" + System.currentTimeMillis());
                condition.signal();
                Thread.sleep(3000);
                System.out.println("这是condition.signal()方法之后的语句");
            } finally {
                lock.unlock();
            }
        }
    }

    static public class ThreadA extends Thread {
        private MyService service;
        public ThreadA(MyService service) {
            super();
            this.service = service;
        }

        @Override
        public void run() {
            service.await();
        }
    }
}