import java.util.concurrent.locks.ReentrantLock;

public class FairorNofairLock {
    public static void main(String[] args) {
        final Service service = new Service(false);//true为公平锁，false为非公平锁

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("线程" + Thread.currentThread().getName()
                        + "运行了");
                service.serviceMethod();
            }
        };

        Thread[] threadArray = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threadArray[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threadArray[i].start();
        }
        /*
        公平
        线程Thread-7运行了
        线程Thread-5运行了
        线程Thread-2运行了
        线程Thread-9运行了
        线程Thread-1运行了
        线程Thread-6运行了
        线程Thread-3运行了
        线程Thread-8运行了
        线程Thread-0运行了
        线程Thread-4运行了
        ThreadName=Thread-5获得锁定
        ThreadName=Thread-7获得锁定
        ThreadName=Thread-2获得锁定
        ThreadName=Thread-9获得锁定
        ThreadName=Thread-1获得锁定
        ThreadName=Thread-6获得锁定
        ThreadName=Thread-3获得锁定
        ThreadName=Thread-8获得锁定
        ThreadName=Thread-0获得锁定
        ThreadName=Thread-4获得锁定
        */
        /* 非公平
        线程Thread-6运行了
        线程Thread-7运行了
        线程Thread-0运行了
        线程Thread-1运行了
        线程Thread-5运行了
        线程Thread-4运行了
        线程Thread-3运行了
        线程Thread-9运行了
        线程Thread-2运行了
        线程Thread-8运行了
        ThreadName=Thread-6获得锁定
        ThreadName=Thread-7获得锁定
        ThreadName=Thread-0获得锁定
        ThreadName=Thread-1获得锁定
        ThreadName=Thread-5获得锁定
        ThreadName=Thread-4获得锁定
        ThreadName=Thread-3获得锁定
        ThreadName=Thread-9获得锁定
        ThreadName=Thread-2获得锁定
        ThreadName=Thread-8获得锁定
        */
    }

    static public class Service {
        private ReentrantLock lock;
        public Service(boolean isFair) {
            super();
            /*
            Lock锁分为：公平锁 和 非公平锁。
            公平锁表示线程获取锁的顺序是按照线程加锁的顺序来分配的，即先来先得的FIFO先进先出顺序。
            而非公平锁就是一种获取锁的抢占机制，是随机获取锁的，
            和公平锁不一样的就是先来的不一定先的到锁，这样可能造成某些线程一直拿不到锁，结果也就是不公平的了
            */
            lock = new ReentrantLock(isFair);
        }

        public void serviceMethod() {
            lock.lock();
            try {
                System.out.println("ThreadName=" + Thread.currentThread().getName()
                        + "获得锁定");
            } finally {
                lock.unlock();
            }
        }
    }
}