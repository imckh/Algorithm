package algorithm.c2_sort;

/**
 * @author CKH
 * @date 2018/3/2 12:54
 */
public class Insertion extends BaseSort {
    @Override
    public void sort(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    public static void main(String[] args) {
        Insertion s = new Insertion();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);

        s.sort(a);

        s.show(a);
    }
}
