package algorithm.c3_searching;

import edu.princeton.cs.algs4.Queue;

/**
 * @author CKH
 * @date 2018/3/8 21:16
 */
public class SequentialSearchST<Key, Value> {
    private Node first;

    private class Node {
        // 链表结点的定义
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first;  x != null ; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }

        return null;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    public void put(Key key, Value value) {
        // 查找给定的值, 存在则更新, 否则在表中新建结点
        for (Node x = first;  x != null ; x = x.next) {
            if (key.equals(x.key)) {
                x.val = value; // 命中, 更新
                return;
            }
        }

        first = new Node(key, value, first); // 添加结点
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            //N--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }

}
