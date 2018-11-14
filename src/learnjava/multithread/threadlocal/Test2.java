public class Test2 {
    public static ThreadLocalExt t1 = new ThreadLocalExt();

    public static void main(String[] args) {
        if (t1.get() == null) {
            System.out.println("从未放过值");
            t1.set("我的值");
        }
        System.out.println(t1.get());
        System.out.println(t1.get());
    }
    /*
    第一次调用ThreadLocal对象的get()方法时返回的值是null,通过调用set()方法可以为ThreadLocal对象赋值。

    如果想要解决get()方法null的问题，可以使用ThreadLocal对象的initialValue方法
    */
    static public class ThreadLocalExt extends ThreadLocal {
        @Override
        protected Object initialValue() {
            return "我是默认值 第一次get不再为null";
        }
    }
}