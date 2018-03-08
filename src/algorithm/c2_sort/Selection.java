package algorithm.c2_sort;

/**
 * @author CKH
 * @date 2018/3/2 11:40
 */
public class Selection extends BaseSort {

    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }
            exch(a, i, min);
        }
    }

    public static void main(String[] args) {
        Selection s = new Selection();
        Integer[] a = {5, 2, 4, 1, 0};
        s.show(a);
        s.sort(a);
        s.show(a);
    }
}
