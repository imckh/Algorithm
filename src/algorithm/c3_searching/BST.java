package algorithm.c3_searching;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * 参考: https://www.cnblogs.com/penghuwan/p/8057482.html#_label10
 *
 * @author CKH
 * @date 2018/3/11 11:12
 */
public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node{
        private Key key;            // 键
        private Value val;          // 值
        private Node left, right;   // 指向子树的链接
        private int N;              // 以该结点为跟的子树中的结点数

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    /**
     * 二叉树查找
     * @param key
     * @return
     */
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        // 在以x为跟结点的子树中查找并返回key对应的值
        // 找不到返回null
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp > 0) {
            return get(x.right, key);
        } else if (cmp < 0) {
            return get(x.left, key);
        } else {
            return x.val;
        }
    }

    /**
     * 插入并排序
     * @param key
     * @param val
     */
    public void put(Key key, Value val) {
        // 查找key, 找到则更新它的值, 否则创建一个结点
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        // 如果key存在于以x为跟结点的子树中则更新他的值
        // 否则将以key, val为键值对的新结点插入到该子树中
        if (x == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(x.key);

        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }

        x.N  = size(x.left) + size(x.right) + 1;

        return x;
    }

    // 最小值为做左边的结点
    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    // 最大值为最右边的结点
    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    // 小于等于key的最大键
    public Key floor(Key key) {
        Node x = floor(root, key);

        if (x == null) {
            return null;
        }

        return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        if (cmp == 0) {
            return x;
        }
        // key小于等于二叉树的根结点,
        // 那floor一定在根结点的左子树中
        if (cmp < 0) {
            return floor(x.left, key);
        }

        // 给定的key大于等于根结点,
        Node t = floor(x.right, key);
        // 那么只有当根结点的右子树中存在小于等于key的结点时,
        // 小于等于key的最大值才会出现在右子树中
        if (t != null) {
            return t;
        } else { // 否则跟结点就是floor
            return x;
        }
    }

    // 跟floor是相反的
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }

        return x.key;
    }

    private Node ceiling(Node x, Key key) {

        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }

        if (cmp > 0) {
            return ceiling(x.right, key);
        }

        Node t = floor(x.left, key);
        if (t != null) {
            return t;
        } else {
            return x;
        }
    }

    // 查找排名为k的键
    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        // 返回排名为k的键
        if (x == null) {
            return null;
        }

        // t为左子树的结点数
        int t = size(x.left);

        // 如果左子树中的结点数t大于k
        // 则继续在左子树中找排名为k的键
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            // 如果t小于k
            // 则在右子树中查找(k - t - 1)的键
            // 查找排名 减去左子树的个数 减去当前结点 剩下的是在右子树中的结点数
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    // 返回给定键的排名
    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }

        int cmp = key.compareTo(x.key);

        // 如果给定结点小于根结点, 返回该键在左子树的排名
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            // 如果给定结点大于根结点,
            // 返回 左子树的总结点数 + 1(根结点) + 它在右子树的排名
            return size(x.left) + 1 + rank(key, x.right);
        } else {
            // 如果给定结点和根结点相等, 返回左子树的总结点t
            return size(x.left);
        }
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        // 不断深入根结点的左子树直至遇到空链接
        // 然后将指向该结点的链接指向该结点的右子树

        if (x.left == null) {
            return x.right;
        }
        // 正确设置父结点
        x.left = deleteMin(x.left);
        // 更新它到根节点的路径上所有结点的计数器的值
        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }

        x.right = deleteMax(x.right);
        x.N = size(x.right) + size(x.right) + 1;

        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    // 对结点的即时删除
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);

        // 找到该结点的位置
        if (cmp < 0) {
            x.left = delete(x.left, key);   // 向左子树查找键为key的结点
        } else if (cmp > 0) {
            x.right = delete(x.right, key); // 向右子树查找键为key的结点
        } else { // 在这个else里结点已经被找到，就是当前的x

            // 第一种情况和第二种情况：左子树为null或右子树为null（或都为null）
            if (x.right == null) {
                return x.left;  // 如果左子树为空，则将右子树赋给父节点的链接
            }
            if (x.left == null) {
                return x.right; // 如果右子树为空，则将左子树赋给父节点的链接
            }
            // 将即将被删除的结点保存为t
            Node t = x;
            // 将x 替换为 他的后继节点min(t.right)
            x = min(t.right);
            // 将x的右链接(右子树/原本指向一棵所有结点都大于x.key的二叉查找树)指向deleteMin(t.right)
            // 也就是删除后所有结点仍然都大于x.key的子二叉查找树
            x.right = deleteMin(t.right);
            // 将x的左链接(左子树, 本为空)设为t.left(其下所有的键都小于被杉属的结点和他的后继结点)
            x.left = t.left;
        }

        x.N = size(x.left) + size(x.right) + 1;

        return x;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null) {
            return;
        }

        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        // 递归查找根结点的左子树
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }

        // 查找根结点, 进入队列
        // lo <= key <= hi
        if (cmplo <= 0 && cmphi >= 0) {
            queue.enqueue(x.key);
        }

        // 递归查找根节点右子树
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }


    public static void main(String[] args) {
        BST<String, Integer> st = new BST<String, Integer>();
        String[] ar = {"f", "a", "g", "g", "b", "z", "s", "a", "g", "h"};
        for (int i = 0; i < 10; i++) {
            st.put(ar[i], i);
            System.out.println("st.put(" + i + ")");
        }

        StdOut.println("=====================");

        for (String s : st.keys())
            StdOut.print("[" + s + "," + st.get(s) + "] ");
        System.out.println();
        System.out.println("st.rank(\"f\") = " + st.rank("f"));
        st.delete("f");
        st.delete("z");

        for (String s : st.keys())
            StdOut.print("[" + s + "," + st.get(s) + "] ");
    }
}
