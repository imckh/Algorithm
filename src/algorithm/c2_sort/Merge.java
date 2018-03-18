package algorithm.c2_sort;

/**
 * 自顶向下的归并排序
 *
 * @author CKH
 * @date 2018/3/7 20:59
 */
public class Merge extends BaseSort{
    // 归并所需的复制数组
    protected static Comparable[] aux;

    @Override
    public void sort(Comparable[] a) {
        aux = new Comparable[a.length];

        sort(a, 0, a.length - 1);
    }

    public void sort(Comparable[] a, int lo, int hi) {
        // 将数组a[lo...hi]排序
        if (hi <= lo) return;

        /*
        计算中间位置时不应该使用(high+ low) / 2的方式，
        因为加法运算可能导致整数越界，
        这里应该使用以下三种方式之一：
            low + (high - low) / 2
            或low + (high – low) >> 1
            或(low + high) >>> 1（>>>是逻辑右移，是不带符号位的右移）
         */
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid); // 排序左边
        sort(a, mid + 1, hi); // 排序右边

        merge(a, lo, mid, hi); // 归并结果
    }

    // 原地归并
    // 将a[lo, mid]和a[mid+1, hi]归并
    public void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;

        // 将a[lo..hi]复制到aux[lo...hi]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            // 归并到a[lo...hi]
            if (i > mid) {
                // 左边用尽
                a[k] = aux[j++];
            } else if (j > hi){
                // 右边用尽
                a[k] = aux[i++];
            } else if (less(aux[i], aux[j])) {
                // 左边当前元素小于右边当前元素
                a[k] = aux[i++];
            } else {
                // 右边当前元素小于左边当前元素
                a[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        Merge s = new Merge();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
