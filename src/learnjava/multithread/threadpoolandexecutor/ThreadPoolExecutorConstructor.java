import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorConstructor {
    /**
     * 用给定的初始参数创建一个新的ThreadPoolExecutor。
     * @param corePoolSize 核心线程池的大小
     * @param maximumPoolSize 最大线程池的大小
     * @param keepAliveTime 当线程池中的线程数量大于corePoolSize的时候，如果这时没有新的任务提交，
     * 核心线程外的线程不会立即销毁，而是会等待，直到等待的时间超过了keepAliveTime；
     * @param unit  keepAliveTime参数的时间单位
     * @param workQueue 等待队列，当任务提交时，如果线程池中的线程数量大于等于corePoolSize的时候，把该任务封装成一个Worker对象放入等待队列；
     *
     * @param threadFactory 执行者创建新线程时使用的工厂
     * @param handler RejectedExecutionHandler类型的变量，表示线程池的饱和策略。
     * 如果阻塞队列满了并且没有空闲的线程，这时如果继续提交任务，就需要采取一种策略处理该任务。
     * 线程池提供了4种策略：
     * <p>
     *   1.AbortPolicy：直接抛出异常，这是默认策略；
     *   2.CallerRunsPolicy：用调用者所在的线程来执行任务；
     *   3.DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；
     *   4.DiscardPolicy：直接丢弃任务；
     * </p>
     */
    /*
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
                maximumPoolSize <= 0 ||
                maximumPoolSize < corePoolSize ||
                keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }*/


    /**
     * 创建一个可重用固定数量线程的线程池
     *  在任何时候至多有n个线程处于活动状态
     *  如果在所有线程处于活动状态时提交其他任务，则它们将在队列中等待，
     *  直到线程可用。 如果任何线程在关闭之前的执行期间由于失败而终止，
     *  如果需要执行后续任务，则一个新的线程将取代它。池中的线程将一直存在
     *  直到调用shutdown方法
     * @param nThreads 线程池中的线程数
     * @param threadFactory 创建新线程时使用的factory
     * @return 新创建的线程池
     * @throws NullPointerException 如果threadFactory为null
     * @throws IllegalArgumentException if {@code nThreads <= 0}
     */
    /*
    public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(),
                threadFactory);
    }
    public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }
    */

    /**
     *创建使用单个worker线程运行无界队列的Executor
     *并使用提供的ThreadFactory在需要时创建新线程
     *
     * @param threadFactory 创建新线程时使用的factory
     *
     * @return 新创建的单线程Executor
     * @throws NullPointerException 如果ThreadFactory为空
     */
    /*
    public static ExecutorService newSingleThreadExecutor(ThreadFactory threadFactory) {
        return new FinalizableDelegatedExecutorService
                (new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>(),
                        threadFactory));
    }
    */
    /**
     * CachedThreadPool的corePoolSize被设置为空（0），
     * maximumPoolSize被设置为Integer.MAX.VALUE，即它是无界的，
     * 这也就意味着如果主线程提交任务的速度高于maximumPool中线程处理任务的速度时，
     * CachedThreadPool会不断创建新的线程。极端情况下，这样会导致耗尽cpu和内存资源。
     *
     * 创建一个线程池，根据需要创建新线程，但会在先前构建的线程可用时重用它，
     * 并在需要时使用提供的ThreadFactory创建新线程。
     * @param threadFactory 创建新线程使用的factory
     * @return 新创建的线程池
     * @throws NullPointerException 如果threadFactory为空
     */
    /*public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                threadFactory);
    }
    public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }*/

}
