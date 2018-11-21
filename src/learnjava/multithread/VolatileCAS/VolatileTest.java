public class VolatileTest {

    public static void main(String[] args) throws InterruptedException {

        DemoThread dt = new DemoThread();
        dt.start();

        while(true) {
            if (dt.isFlag()) {
                System.out.println("主线程终止");
                break;
            }
            //Thread.sleep(1);
        }

    }
}

class DemoThread extends Thread {
    // volatile 保证新值能立即同步到主内存，以及每次使用变量前立即从主内存刷新
    private volatile boolean flag;
//    当 DemoThread 线程修改 flag 值后立即同步回主内存，main 线程立即刷新主内存的 flag 值到工作内存中，读取 flag = true，终止 while 循环


//    private boolean flag;
    /*从结果可以看出 main 线程没有中断，而是一直循环执行
    从代码中我们可以简单的看出程序执行时产生 2 个线程（执行 main 方法的线程 和 新创建的 DemoThread 线程）。

    这 2 个线程先从主内存读取 flag （值为 false），拷贝到各自的工作内存中。

    当 DemoThread 线程执行 run 方法时，修改了自己工作内存的 flag 变量，并将修改的值同步回主内存中。
    而 main 线程执行 while 循环时，由于 while(true) 执行速度太快，
    导致 main 线程没有时间刷新主内存中修改的变量值，因此只能读取自己工作内存 flag 的变量，
    从而导致 while 循环一直进行下去

    有一个方法是加上Thread.sleep(1);但是治标不治本
    */

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("子线程-flag:" + isFlag());
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

}
