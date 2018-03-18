package algorithm.c3_searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 基于线性探测的符号表
 *
 * 线性探测法
 * 当发生碰撞时, 直接检测散列表的下一个位置(将索引加1)
 * 直到找到键在数组中的索引, 检查其中的键是否和被查找的键相同,
 * 如果不同则继续查找, 直到遇到该键和一个空元素
 *
 * @author CKH
 * @date 2018/3/17 9:55
 */
public class LinearProbingHashST<Key, Value> {
    private static final int INIT_CAPACITY = 4;
    private int N;      // 符号表中的键值对总数
    private int M; // 线性探测表的大小
    private Key[] keys;
    private Value[] vals;

    public LinearProbingHashST() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashST(int capacity) {
        M = capacity;
        N = 0;
        keys = (Key[])   new Object[M];
        vals = (Value[]) new Object[M];
    }


    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    public int hash(Key key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    // 保持 N/M 在 1/2 ~ 1/4之间
    private void resize(int capacity) {
        LinearProbingHashST<Key, Value> t;
        t = new LinearProbingHashST<>(capacity);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                t.put(keys[i], vals[i]);
            }
        }

        keys = t.keys;
        vals = t.vals;

        M = t.M;
    }

    public void put(Key key, Value val) {
        if (N >= M/2) {
            resize(2 * M);
        }

        /*
        如果新建散列值是一个空元素, 则直接保存
        若不是, 则顺序查找一个空位置保存它
         */
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                vals[i] = val;
                return;
            }
        }

        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public Value get(Key key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M) {
            if (keys[i].equals(key)) {
                return vals[i];
            }
        }

        return null;
    }

    /*
    删除元素后, 需要将后边的元素重新插入
     */
    public void delete(Key key) {
        if (!contains(key)) {
            return;
        }
        int i = hash(key);
        // 找到元素
        while (!key.equals(keys[i])) {
            i = (i + 1) % M;
        }

        // 该索引清空
        keys[i] = null;
        vals[i] = null;

        // 查找下一个索引位置是否存在元素, 重新插入, 防止出现元素隔断
        i = (i + 1) % M;
        while (keys[i] != null) {
            Key keyToRedo = keys[i];
            Value valToRedo = vals[i];

            // 删除该索引
            keys[i] = null;
            vals[i] = null;

            N--;
            // 重新插入
            put(keyToRedo, valToRedo);

            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N == M / 8) {
            resize(M / 2);
        }
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<Key>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }


    public static void main(String[] args) {
        LinearProbingHashST<String, Integer> st = new LinearProbingHashST<String, Integer>();

        String[] ar = {"f", "a", "g", "g", "b", "z", "s", "a", "g", "h"};

        for (int i = 0; i < ar.length; i++) {
            String key = ar[i];
            st.put(key, i);
        }

        // print keys
        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));

        st.delete("a");

        for (String s : st.keys())
            StdOut.println(s + " " + st.get(s));
    }
}
