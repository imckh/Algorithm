package algorithm.c3_searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author CKH
 * @date 2018/3/11 9:59
 */
public class BinarySearchST<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] vals;
    private int N;
    private static final int INIT_CAPACITY = 2;

    public BinarySearchST() {
        this(INIT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private void resize(int capacity) {
        Key[] tempk = (Key[]) new Comparable[capacity];
        Value[] tempv = (Value[]) new Object[capacity];

        for (int i = 0; i < N; i++) {
            tempk[i] = keys[i];
            tempv[i] = vals[i];
        }

        keys = tempk;
        vals = tempv;
    }

    public Value get(Key key) {
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            return vals[i];
        } else {
            return null;
        }
    }

    public int rank(Key key) {
        int lo = 0, hi = N-1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);

            // 二分查找
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }

        // 非递归的rank()在循环结束时lo的值正好等于表中小于被查找的键的数量
        return lo;
    }

    public void put(Key key, Value value) {
        // 查找键, 找到就更新, 找不到就创建新元素
        int i = rank(key);
        if (i < N && keys[i].compareTo(key) == 0) {
            vals[i] = value;
            return;
        }

        // 下边是插入新的键值对

        // 扩容
        if (N == keys.length) {
            resize(2 * keys.length);
        }

        // 插入的话, 后边所有元素后移一个
        for (int j = N; j > i; j--) {
            keys[j] = keys[j-1];
            vals[j] = vals[j-1];
        }

        keys[i] = key;
        vals[i] = value;

        N++;
    }

    public Value delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        if (isEmpty()) {
            return null;
        }

        Value tv;
        // key不在表中
        int i = rank(key);
        if (i == N && keys[i].compareTo(key) != 0) {
            return null;
        }
        tv = vals[i];


        if (N == keys.length) {
            resize(2 * keys.length);
        }

        // 删除元素, 后边所有元素前移一个
        for (int j = i; j < N - 1; j++) {
            keys[j] = keys[j+1];
            vals[j] = vals[j+1];
        }

        N--;

        vals[i] = null;
        keys[i] = null;

        // 如果1/4满的时候缩小容量
        if (N > 0 && N == keys.length / 4) {
            resize(keys.length / 2);
        }

        return tv;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Key min() {
        return keys[0];
    }

    public Key max() {
        return keys[N-1];
    }

    public Key select(int k) {
        return keys[k];
    }

    // 符号表中的大于或等于key的最小值
    public Key ceiling(Key key) {
        int i = rank(key);
        if (i == N) {
            return null;
        }
        return keys[i];
    }

    // 符号表中的小于或等于key的最大值
    public Key floor(Key key) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        } else {
            return keys[i - 1];
        }
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<>();

        for (int i = rank(lo); i < rank(hi); i++) {
            q.enqueue(keys[i]);
        }

        if (contains(hi)) {
            q.enqueue(keys[rank(hi)]);
        }

        return q;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public static void main(String[] args) {
        BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
        for (int i = 0; i < 10; i++) {
            String key = StdIn.readString();
            st.put(key, i);
        }
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
