package coding_interview_guide.c2_list;

/**
 * 两个有序链表的公共部分
 *
 * 这个题没看懂
 *
 * @author CKH
 * @date 2018/2/27 21:41
 */
public class CommonPart {
    static class Node {
        public int value;
        public Node next;
        public Node(int data) {
            this.value = data;
        }
    }

    public static void printCommonPart(Node head1, Node head2) {
        System.out.print("Common part: ");
            /*
            (1)如果head1的值小于head2, 则head1往下移动
            (2)如果head2的值小于head1, 则head2往下移动
            (3)如果head1和head2的值相等, 则打印这个值,
            然后head1和head2都往下移动
            (4)head1或head2有任何一个移动到null, 整个过程停止
             */
        while (head1 != null && head2 != null) {
            if (head1.value < head2.value) {
                head1 = head1.next;
            } else if (head1.value > head2.value) {
                head2 = head2.next;
            } else {
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
    }

    public static void main(String[] args) {
        Node head1 = new Node(5);
        head1.next = new Node(7);
        head1.next.next = new Node(8);
        head1.next.next.next = new Node(9);
        head1.next.next.next.next = new Node(10);

        Node head2 = new Node(5);
        head2.next = new Node(6);
        head2.next.next = new Node(8);
        head2.next.next.next = new Node(9);
        head2.next.next.next.next = new Node(10);

        printCommonPart(head1, head2);
    }
}
