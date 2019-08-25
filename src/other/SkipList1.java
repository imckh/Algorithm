import java.util.*;

public class SkipList1<T> {
    /**
     * 跳跃表的节点,包括key-value和上下左右4个指针
     * 
     * @param <T>
     */
    private class SkipListNode<T> {
        public int key;
        public T value;
        public SkipListNode<T> up, down, left, right; // 上下左右

        public static final int HEAD_KEY = Integer.MIN_VALUE; // 负无穷
        public static final int TAIL_KEY = Integer.MAX_VALUE; // 正无穷

        public SkipListNode(int k, T v) {
            key = k;
            value = v;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (!(o instanceof SkipListNode)) {
                return false;
            }
            SkipListNode<T> ent;
            try {
                ent = (SkipListNode<T>) o; // 检测类型
            } catch (ClassCastException ex) {
                return false;
            }
            return (ent.key == key) && (ent.value == value);
        }

        @Override
        public String toString() {
            return "key-value:"+key+"-"+value;
        }
    }

    private SkipListNode<T> head, tail;
    private int nodes; // 节点层数
    private int listLevel; // 层数
    private Random random; // 用于投掷硬币
    private static final double PROBABILITY = 0.5; // 向上提升一层的概率

    public SkipList1() {
        random = new Random();
        clear();
    }

    /**
     * 清空跳跃表
     */
    public void clear() {
        head = new SkipListNode(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode(SkipListNode.TAIL_KEY, null);

        horizontalLink(head, tail);
        listLevel = 0;
        nodes = 0;
    }

    public boolean isEmpty() {
        return nodes == 0;
    }

    public int size() {
        return nodes;
    }

    /**
     * 在最下面一层，找到要插入的位置前面的那个key
     * @param key
     * @return
     */
    private SkipListNode<T> findNode(int key) {
        SkipListNode<T> p = head;

        while (true) {
            while (p.right.key != SkipListNode.TAIL_KEY && p.right.key <= key) { // 向右找 直到遇到比key大的
                p = p.right;
            }
            if (p.down != null) {
                p = p.down;
            } else {
                break;
            }
        }

        return p;
    }

    /**
     * 查找是否存储key，存在则返回该节点，否则返回null
     * @param key
     * @return
     */
    public SkipListNode<T> search(int key) {
        SkipListNode<T> p = findNode(key);
        if (key == p.key) {
            return p;
        }
        return null;
    }

    /**
     * 向跳跃表中添加key-value
     * @param k
     * @param v
     */
    public void put(int k, T v) {
        SkipListNode<T> p = findNode(k);
        // key相同，替换
        if (k == p.key) {
            p.value = v;
            return;
        }

        SkipListNode<T> q = new SkipListNode<T>(k, v);
        backLink(p, q);

        int currentLevel = 0; // 当前所在层级 0

        // 抛硬币 是否向上一层
        while (random.nextDouble() < PROBABILITY) {
            // 如果超出了高度， 需要新建一个顶层
            if (currentLevel >= listLevel) {
                listLevel++;

                SkipListNode<T> p1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> p2 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                horizontalLink(p1, p2);
                vertiacallLink(p1, head);
                vertiacallLink(p2, tail);

                head = p1;
                tail = p2;
            }

            // 将p移动到上一层
            while (p.up == null) {
                p = p.left;
            }
            p = p.up;

            SkipListNode<T> e = new SkipListNode<T>(k, null); // 指保存key就好 v从最底层取
            backLink(p, e);
            vertiacallLink(e, q);
            q = e;
            currentLevel++;
        }

        nodes++;
    }

    /* ============== 工具方法 ================ */

    /**
     * 1 后边 插入2
     * @param node1
     * @param node2
     */
    private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.left = node1;
        node2.right = node1.right;
        node1.right.left = node2;
        node1.right = node2;
    }

    /**
     * 水平双向链表
     * @param node1
     * @param node2
     */
    private void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.left = node2;
        node2.left = node1;
    }

     /**
     * 垂直双向连接
     */
    private void vertiacallLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "跳跃表为空！";
        }
        StringBuilder builder=new StringBuilder();
        SkipListNode<T> p=head;
        while (p.down!=null) {
            p=p.down;
        }

        while (p.left!=null) {
            p=p.left;
        }
        if (p.right!=null) {
            p=p.right;
        }
        while (p.right!=null) {
            builder.append(p);
            builder.append("\n");
            p=p.right;
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        SkipList1<String> list=new SkipList1<String>();
        System.out.println(list);
        list.put(2, "yan");
        list.put(1, "co");
        list.put(3, "feng");
        list.put(1, "cao");//测试同一个key值
        list.put(4, "曹");
        list.put(6, "丰");
        list.put(5, "艳");
        System.out.println(list);
        System.out.println(list.size());
    }
}