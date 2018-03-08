package algorithm.c2_sort;

/**
 * @author CKH
 * @date 2018/3/2 11:33
 */
public class BaseSort {
    public void sort(Comparable[] a){}

    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    protected void exch(Object[] a, int i, int j) {
        Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    protected boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            if (less(a[i], a[i + 1])) {
                return false;
            }
        }
        return true;
    }
}
