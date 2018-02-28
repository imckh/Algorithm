package coding_interview_guide.c2_list;

/**
 * 在单链表和双链表中删除倒数第K个节点
 *
 * @author CKH
 * @date 2018/2/28 21:01
 */
public class RemoveLastKthNode {
    // 单向链表节点
    static class Node {
        public int value;
        public Node next;

        public Node (int data) {
            this.value = data;
        }
    }

    /**
     * 链表长N, 要删除倒数第K个节点, 很明显,
     * 倒数第K个节点的前一个节点就是第N-k个节点.
     * 在第一次遍历后. K的值变为K-N.
     * 第二次遍历时, K的值不断加1, 加到0就停止遍历
     * 第二次遍历当然会停到第N-K个节点的位置
     *
     * @param head
     * @param lastKth
     * @return
     */
    public static Node removeLastKthNode(Node head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        Node cur = head;
        // 链表从头开始, 每走一步, K值减一
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        // k最后等于0 说明倒数第k个正好是头节点
        if (lastKth == 0) {
            head = head.next;
        }

        if (lastKth < 0) {
            cur = head;
            // 倒数第K个节点的前一个节点就是第N-k个节点
            while (++lastKth != 0) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
        }

        return head;
    }

    /**
     * 双向列表节点
     */
    static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        public DoubleNode (int data) {
            this.value = data;
        }
    }

    public static DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
        if (head == null || lastKth < 1) {
            return head;
        }

        DoubleNode cur = head;
        // 链表从头开始, 每走一步, K值减一
        while (cur != null) {
            lastKth--;
            cur = cur.next;
        }

        // k最后等于0 说明倒数第k个正好是头节点
        if (lastKth == 0) {
            head = head.next;
            head.last = null;
        }

        if (lastKth < 0) {
            cur = head;
            // 倒数第K个节点的前一个节点就是第N-k个节点
            while (++lastKth != 0) {
                cur = cur.next;
            }

            // 双向链表得处理两边的指针
            DoubleNode newNext = cur.next.next;
            cur.next = newNext;
            if (newNext != null) {
                newNext.last = cur;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = null;

        Node cur = n1;
        while (cur != null) {
            System.out.print(" " + cur.value);
            cur = cur.next;
        }
        System.out.println();
        Node curr = removeLastKthNode(n1, 3);
        while (curr != null) {
            System.out.print(" " + curr.value);
            curr = curr.next;
        }
        System.out.println();

        DoubleNode dn1 = new DoubleNode(1);
        DoubleNode dn2 = new DoubleNode(2);
        DoubleNode dn3 = new DoubleNode(3);
        DoubleNode dn4 = new DoubleNode(4);
        DoubleNode dn5 = new DoubleNode(5);
        dn1.next = dn2;
        dn2.next = dn3;
        dn3.next = dn4;
        dn4.next = dn5;
        dn5.next = null;
        dn1.last = null;
        dn2.last = dn1;
        dn3.last = dn2;
        dn4.last = dn3;
        dn5.last = dn4;

        DoubleNode dcur = dn1;
        while (dcur != null) {
            System.out.print(" " + dcur.value);
            dcur = dcur.next;
        }
        System.out.println();
        DoubleNode dcurr = removeLastKthNode(dn1, 3);
        while (dcurr != null) {
            System.out.print(" " + dcurr.value);
            dcurr = dcurr.next;
        }
        System.out.println();
    }
}
