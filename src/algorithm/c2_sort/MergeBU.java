package algorithm.c2_sort;

/**
 * 自底向上的归并排序
 *
 * @author CKH
 * @date 2018/3/7 21:18
 */
public class MergeBU extends Merge {
    @Override
    public void sort(Comparable[] a) {
        // 进行lgN次两两归并
        int N = a.length;
        aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {    // sz: 子数组大小
            for (int lo = 0; lo < N - sz; lo += sz + sz) {  // lo: 子数组索引
                merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N -1));
            }
        }
    }

    public static void main(String[] args) {
        Merge s = new MergeBU();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
