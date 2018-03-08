package algorithm.c2_sort;

import edu.princeton.cs.algs4.StdIn;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 基于堆的优先队列
 *
 * @author CKH
 * @date 2018/3/8 11:35
 */
public class MaxPQ<Key extends Comparable<Key>>{
    private Key[] pq;   // 基于堆的完全二叉树
    private int N = 0;  // 存储与pq[1...N]中, pq[0]没有用

    public MaxPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key v) {
        pq[++N] = v;
        swim(N);
    }

    public Key delMax() {
        Key max = pq[1]; // 从跟结点得到最大值
        exch(1, N--);   //将其和最后一个结点交换
        sink(1);        // 恢复堆的有序性
        pq[N+1] = null; // 防止对象游离
        return max;
    }


    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void sink(int k) {
        while (2 * k <= N) {
            int j = 2 * k;

            // 左右结点选大的下沉
            if (j < N && less(j, j + 1)) {
                j++;
            }

            // k >= j 不下沉
            if (!less(k, j)) {
                break;
            }

            exch(k, j);
            // 继续下沉
            k = j;
        }
    }

    // 上浮
    private void swim(int k) {
        // 父节点比子节点小 上浮
        while (k > 1 && less(k / 2, k)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    @Override
    public String toString() {
        MaxPQ<Key> copy = new MaxPQ<>(size());

        for (int i = 1; i <= N; i++)
            copy.insert(pq[i]);

        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < N; i++) {
            // 每次取头结点
            Key key = copy.delMax();
            if (key != null) {
                sb.append(key);
                if (i + 1 < N && pq[i + 1] != null)
                    sb.append(",");
            }
        }
        sb.append("]");

        return "MaxPQ{" +
                "pq=" + sb.toString() +
                ", N=" + N +
                '}';
    }

    public static void main(String[] args) {
        MaxPQ<Integer> pq = new MaxPQ<>(6);
        pq.insert(4);
        pq.insert(5);
        pq.insert(0);
        pq.insert(10);
        pq.insert(2);
        pq.insert(3);

//        pq.insert("4");
//        pq.insert("5");
//        pq.insert("0");
//        pq.insert("10");
//        pq.insert("2");
//        pq.insert("3");

        System.out.println(pq);

        pq.delMax();

        System.out.println(pq);

        edu.princeton.cs.algs4.MaxPQ<Integer> pq2 = new edu.princeton.cs.algs4.MaxPQ<>(20);
        pq2.insert(4);
        pq2.insert(5);
        pq2.insert(0);
        pq2.insert(10);
        pq2.insert(2);
        pq2.insert(3);

        for (Integer integer : pq2) {
            System.out.print(" " + integer);
        }
        System.out.println();

        pq2.delMax();

        for (Integer integer : pq2) {
            System.out.print(" " + integer);
        }
    }
}
