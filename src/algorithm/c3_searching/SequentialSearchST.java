package algorithm.c3_searching;

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
}
