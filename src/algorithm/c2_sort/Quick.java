package algorithm.c2_sort;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author CKH
 * @date 2018/3/8 8:53
 */
public class Quick extends BaseSort{
    @Override
    public void sort(Comparable[] a) {
        // 关于为什么要先打乱数据
        // http://blog.csdn.net/u012794757/article/details/53174441
        /*
        1. 在初始序列有序的情况下，随机化算法比确定性算法快很多，效率更高，
        而且随着数据量的增大，差距越来越明显。
        而确定性算法随着数据量的增长其计算时间呈指数增长，
        效率很差，时间复杂度达到最坏情况的O(n^2)。

        2. 初始序列为降序的随机性快排算法在数据量达到5千万到1亿之间，
        其排序所耗时间要比升序的随机性快排算法要多，效率要低。
        而确定性算法不管升序还是降序，其计算时间基本一致。
         */
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi) return;

        // 改进: 对小数组使用插入排序. M的最佳值是和系统相关的,一般在5-15比较令人满意
        // if (hi <= lo + M) {new Insertion().sort(a); return;}

        int j = partiton(a, lo, hi); // 快速排序的划分
        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }

    // 将数组切分为a[lo...i-1], a[i], a[i+1...hi]
    public int partiton(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1; // 左右扫描指针
        Comparable v = a[lo]; // 切分元素

        // 扫描左右, 检查扫描是否结束, 并交换元素
        while (true) {
            while (less(a[++i], v)) if(i==hi) break;
            while (less(v, a[--j])) if(j==lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j); // 将v = a[j]放入正确的位置
        return j;   // a[lo...j-1] <= a[j] <= a[j+1...hi]
    }

    /*
     * Rearranges the elements of the specified array in uniformly random order.
     *
     * @param  a the array to shuffle
     * @throws IllegalArgumentException if {@code a} is {@code null}
     */
    /*public static void shuffle(Object[] a) {
        if (a == null) throw new IllegalArgumentException("argument array is null");
        int n = a.length;
        for (int i = 0; i < n; i++) {
            int r = i + uniform(n-i);     // between i and n-1
            Object temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
    }*/

    public static void main(String[] args) {
        Quick s = new Quick();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
