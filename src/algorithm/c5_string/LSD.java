package algorithm.c5_string;

import java.util.Arrays;

/**
 * 低位优先的字符串排序
 * 跟基数排序( RADIX SORT)类似
 * 关于基数排序 https://visualgo.net/zh/sorting?slide=15
 * @author CKH
 * @date 2018/7/29 16:39
 */
public class LSD {

    /**
     * 通过前W个字符将a[]排序
     * @param a 字符串
     * @param W 前W的字符
     */
    public static void sort(String a[], int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        //
        for (int d = W - 1; d >= 0; d--) {
            // 根据第d个字符用建索引计数法排序: 使用每个字母在字母表中的索引
            // 因为为了方便只有英文, 用了256的索引
            int[] count = new int[R + 1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d) + 1]++;    // 计算出现频率
            }

            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];   // 将频率转化为索引
            }

            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];    // 将元素分类
            }

            for (int i = 0; i < N; i++) {
                a[i] = aux[i];  // 回写
            }
            System.out.println(Arrays.toString(a));
        }
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

        LSD.sort(a, 6);

        System.out.println(Arrays.toString(a));
        /*
        [4mhd3d, arwo1d, cbvv0f, abcf2h, zvkp0n, aicy1r, sihw6x]
        [cbvv0f, zvkp0n, arwo1d, aicy1r, abcf2h, 4mhd3d, sihw6x]
        [4mhd3d, abcf2h, arwo1d, zvkp0n, cbvv0f, sihw6x, aicy1r]
        [abcf2h, aicy1r, 4mhd3d, sihw6x, zvkp0n, cbvv0f, arwo1d]
        [abcf2h, cbvv0f, aicy1r, sihw6x, 4mhd3d, arwo1d, zvkp0n]
        [4mhd3d, abcf2h, aicy1r, arwo1d, cbvv0f, sihw6x, zvkp0n]
         */
        /*
        [4mhd3d,
        abcf2h,
        aicy1r,
        arwo1d,
        cbvv0f,
        sihw6x,
        zvkp0n]
         */
    }
}
