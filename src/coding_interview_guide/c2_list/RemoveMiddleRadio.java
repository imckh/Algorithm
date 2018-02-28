package coding_interview_guide.c2_list;

/**
 * 删除链表的中间节点和a/b处的节点
 *
 * @author CKH
 * @date 2018/2/28 21:52
 */
public class RemoveMiddleRadio {
    /**
     * 单向链表节点
     */
    static class Node {
        public int value;
        public Node next;

        public Node (int data) {
            this.value = data;
        }
    }

    /**
     * 删除链表中间元素
     * [1, 2]:1
     * [1, 2, 3]:2
     * [1, 2, 3, 4]:2
     * [1, 2, 3, 4, 5]:3
     * 链表每加2, 要删除的就后移一个节点
     *
     * @param head
     * @return
     */
    public static Node removeMidNode(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        if (head.next.next == null) {
            return head.next;
        }

        Node pre = head;
        Node cur = head.next.next;

        // 链表每加2, 要删除的就后移一个节点
        while (cur.next != null && cur.next.next != null) {
            pre = pre.next;
            cur = cur.next.next;
        }

        pre.next = pre.next.next;

        return head;
    }

    /**
     * 先计算double r = ((double)(a * n))/((double)b)的值,
     * 然后r向上取整之后的整数值代表该删除的节点是第几个节点
     * @param head
     * @param a 分子
     * @param b 分母
     * @return
     */
    public static Node removeByRadio(Node head, int a, int b) {
        if (a < 1 || b < a) {
            return head;
        }
        // 总长n
        int n = 0;
        Node cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        n = (int) Math.ceil((double)(a * n) / (double)b);
        if (n == 1) {
            head = head.next;
        }

        if (n > 1) {
            cur = head;

            // 移动链表当前节点到double r = ((double)(a * n))/((double)b)处
            while (--n != 1) {
                cur = cur.next;
            }
            cur.next = cur.next.next;
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

        // 删除中间
        //Node curr = removeMidNode(n1);
        // 删除a/b
        Node curr = removeByRadio(n1, 2, 3);

        while (curr != null) {
            System.out.print(" " + curr.value);
            curr = curr.next;
        }
        System.out.println();
    }
}
