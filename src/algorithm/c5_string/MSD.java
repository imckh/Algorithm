package algorithm.c5_string;

import edu.princeton.cs.algs4.Insertion;

import java.util.Arrays;

/**
 * 高位优先的字符串排序
 *  在将一个字符串数组a[]排序时, 首先根据他们的首字母用键索引计数法进行排序,
 *  然后(递归的)根据子数组中的字符串的首字母将子数组排序
 * @author CKH
 * @date 2018/7/30 20:30
 */
public class MSD {
    private static int R = 256;     // 基数
    private static final int M = 15;// 小数组的切换阈值
    private static String[] aux;    // 数据分类的辅助数组

    private static int chatAt(String s, int d) {
        return d < s.length() ? s.charAt(d) : -1;
    }

    private static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N-1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 以第d个字符为键 将a[lo]至a[hi]排序
        if (hi <= lo + M) {
            // 插入排序
            insertion(a, lo, hi, d);
            return;
        }

        int[] count = new int[R + 2];   //计算频率
        for (int i = lo; i <= hi; i++) {
            count[chatAt(a[i], d) + 2]++;
        }

        for (int r = 0; r < R + 2; r++) {   // 将频率转换为索引
            count[r + 1] += count[r];
        }

        for (int i = lo; i <= hi ; i++) {   // 数据分类
            aux[count[chatAt(a[i], d) + 1]++] = a[i];
        }

        for (int i = lo; i <= hi; i++) {    // 回写
            a[i] = aux[i - lo];
        }

        // 递归的以每个字符为键进行排序
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    // copy from edu.princeton.cs.algs4.MSD
    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                exch(a, j, j-1);
    }

    // exchange a[i] and a[j]
    private static void exch(String[] a, int i, int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    private static boolean less(String v, String w, int d) {
        // assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) return true;
            if (v.charAt(i) > w.charAt(i)) return false;
        }
        return v.length() < w.length();
    }

    public static void main(String[] args) {
        String[] a = {
                "aicy1r",
                "sihw6x",
                "abcf2h",
                "4mhd3d",
                "cbvv0f",
                "arwo1d",
                "zvkp0n"
        };

        MSD.sort(a);

        System.out.println(Arrays.toString(a));
        // [4mhd3d, abcf2h, aicy1r, arwo1d, cbvv0f, sihw6x, zvkp0n]
        // [4mhd3d, abcf2h, aicy1r, arwo1d, cbvv0f, sihw6x, zvkp0n]
    }
}
