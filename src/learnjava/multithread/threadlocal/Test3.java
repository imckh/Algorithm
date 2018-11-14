import java.util.*;
/**
 *TODO 验证线程变量间的隔离性
 */
public class Test3 {

    public static void main(String[] args) {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.println("       ThreadLocalExt在Main线程中取值=" + Tools.tl.get());
                System.out.println("       InheritableThreadLocalExt在Main线程中取值=" + Tools2.tl.get());
                Thread.sleep(100);
            }
            Thread.sleep(5000);
            ThreadA a = new ThreadA();
            a.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    static public class Tools {
        public static ThreadLocalExt tl = new ThreadLocalExt();
    }
    static public class ThreadLocalExt extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }
    }

    static public class Tools2 {
        public static InheritableThreadLocalExt tl = new InheritableThreadLocalExt();
    }
    // ThreadLocal类固然很好，但是子线程并不能取到父线程的ThreadLocal类的变量，InheritableThreadLocal类就是解决这个问题的
    static public class InheritableThreadLocalExt extends InheritableThreadLocal {
        @Override
        protected Object initialValue() {
            return new Date().getTime();
        }

        // 取父线程的值并修改
        // 如果子线程在取得值的同时，主线程将InheritableThreadLocal中的值进行更改，那么子线程取到的还是旧值
        @Override
        protected Object childValue(Object parentValue) {
            return parentValue + " 我在子线程加的~!";
        }
    }

    static public class ThreadA extends Thread {

        @Override
        public void run() {
            try {
                // 可以看出子线程和父线程各自拥有各自的值
                for (int i = 0; i < 10; i++) {
                    System.out.println("ThreadLocalExt在ThreadA线程中取值=" + Tools.tl.get());
                    System.out.println("InheritableThreadLocalExt在ThreadA线程中取值=" + Tools2.tl.get());
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}