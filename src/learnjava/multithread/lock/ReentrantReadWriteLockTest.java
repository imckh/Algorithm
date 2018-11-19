// ReadWriteLock接口的实现类-ReentrantReadWriteLock读写锁

import java.util.concurrent.locks.*;

public class ReentrantReadWriteLockTest {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        // 运行两个使用同一个Service对象实例的线程a,b，
        //线程a执行上面的read方法，
        //线程b执行上面的write方法
        //记住：只要出现写操作的过程就是互斥的

        // 两个线程同时运行read方法，你会发现两个线程可以同时或者说是几乎同时运行lock()方法后面的代码，输出的两句话显示的时间一样
        ThreadA a = new ThreadA(service);
        ThreadA a1 = new ThreadA(service);
        a.setNameA("A");
        a.start();
        a1.setNameA("A1");
        a1.start();

        // 两个线程同时运行write方法，你会发现同一时间只允许一个线程执行lock()方法后面的代码
        Thread.sleep(1000);
        ThreadB b = new ThreadB(service);
        b.setNameB("B");
        b.start();
        ThreadB b1 = new ThreadB(service);
        b1.setNameB("B1");
        b1.start();
    }

    static public class ThreadA extends Thread {
        private String a;
        private Service service;

        public void setNameA(String a) {
            System.out.println("线程A setName:" + a);
            this.a = a;
        }

        public ThreadA (Service s) {
            this.service = s;
        }

        @Override
        public void run() {
            service.read();
        }
    }

    static public class ThreadB extends Thread {
        private String a;
        private Service service;

        public void setNameB(String a) {
            System.out.println("线程B setName:" + a);
            this.a = a;
        }

        public ThreadB (Service s) {
            this.service = s;
        }

        @Override
        public void run() {
            service.write();
        }
    }

    static public class Service {
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        public void read() {
            try {
                try {
                    lock.readLock().lock();
                    System.out.println("获得读锁" + Thread.currentThread().getName()
                            + " " + System.currentTimeMillis());
                    Thread.sleep(10000);
                } finally {
                    lock.readLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void write() {
            try {
                try {
                    lock.writeLock().lock();
                    System.out.println("获得写锁" + Thread.currentThread().getName()
                            + " " + System.currentTimeMillis());
                    Thread.sleep(10000);
                } finally {
                    lock.writeLock().unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}