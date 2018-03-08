package algorithm.c2_sort;

/**
 * 三向切分的快速排序(大量重复元素)
 *
 * @author CKH
 * @date 2018/3/8 9:55
 */
public class Quick3way extends Quick {
    @Override
    public void sort(Comparable[] a, int lo, int hi) {
        // 调用此方法的公有方法sort()为Quick中的sort
        if (hi <= lo) return;
        int lt = lo, i = lo + 1, gt = hi;
        Comparable v = a[lo];

        /*
        a[i] < v, 将lt, i交换, 将lt, i加1
        a[i] > v, 将gt, i交换, 将gt减1
        a[i] = v, 将i加1
         */
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0) {
                exch(a, lt++, i++);
            } else if (cmp > 0) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        } // 现在 a[lo...lt-1] < v = a[lt...gt] <= a[gt+1...hi]成立
        sort(a, lo, lt - 1);
        sort(a, gt + 1, hi);
    }


    public static void main(String[] args) {
        Quick3way s = new Quick3way();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
