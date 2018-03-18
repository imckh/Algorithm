package algorithm.c3_searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * 基于拉链法的散列表
 *
 * @author CKH
 * @date 2018/3/17 9:21
 */
public class SeparateChainingHashST<Key, Value>{
    private static final int INIT_CAPACITY = 4;

    private int N;  // 键值对总数
    private int M;  // 散列表总大小
    private SequentialSearchST<Key, Value>[] st;    // 存放链表的对象

    public SeparateChainingHashST() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashST(int M) {
        // 创建M条链表
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    public int size() {
        return N;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }


    /**
     * 使用默认的hashCode和除留余数法结合产生一个0到M-1的整数
     * @param key
     * @return
     */
    private int hash(Key key) {
        // 屏蔽符号位(将32位整数变为31位非负整数), 然后用除留余数法计算除M的余数
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public Value get(Key key) {
        return (Value) st[hash(key)].get(key);
    }

    public void put(Key key, Value value) {
        if (N >= 10 * M) {
            resize(2 * M);
        }

        int i = hash(key);

        if (!st[i].contains(key)) {
            N++;
        }

        st[hash(key)].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }

        int i = hash(key);
        if (st[i].contains(key)) {
            N--;
        }
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (M > INIT_CAPACITY && N <= 2*M) resize(M/2);
    }

    private void resize(int chains) {
        SeparateChainingHashST<Key, Value> temp = new SeparateChainingHashST<>(chains);
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.M  = temp.M;
        this.N  = temp.N;
        this.st = temp.st;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < M; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }

        return queue;
    }

    public static void main(String[] args) {
        SeparateChainingHashST<String, Integer> st = new SeparateChainingHashST<String, Integer>();

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
