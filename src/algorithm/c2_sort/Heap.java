package algorithm.c2_sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author CKH
 * @date 2018/3/8 16:33
 */
public class Heap extends BaseSort{

    @Override
    public void sort(Comparable[] a) {
        // 去掉a[0]
        // 堆的构造, 跳过大小为1的子堆
        int N = a.length;
        for (int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }

        // 下沉排序
        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }

    private void sink(Comparable[] a, int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;

            if (j < n && less(a, j, j + 1)) {
                j++;
            }

            if (!less(a, k, j)) {
                break;
            }

            exch(a, k, j);

            // 继续下沉
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     * Indices are "off-by-one" to support 1-based indexing.
     * 堆排序数组索引是从1开始的
     ***************************************************************************/
    public boolean less(Comparable[] pq, int i, int j) {
        return less(pq[i-1], pq[j-1]);
    }

    @Override
    public void exch(Object[] pq, int i, int j) {
        super.exch(pq, i-1, j-1);
    }


    public static void main(String[] args) {
        Heap s = new Heap();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
