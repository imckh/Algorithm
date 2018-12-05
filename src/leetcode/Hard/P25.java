/*
25. k个一组翻转链表

https://leetcode-cn.com/problems/reverse-nodes-in-k-group/

给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
*/
public class P25 {
    public static void main(String[] args) {
        ListNode l1 = stringToListNode("[1, 2, 3, 4, 5]");

        //prettyPrintLinkedList(new Solution().reverseKGroup(nums));
        // ListNode head = new ListNode(0);
        // head.next = l1;
        // prettyPrintLinkedList(new Solution().reverse(head, l1.next.next.next));
        prettyPrintLinkedList(new Solution().reverseKGroup(l1, 3));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {
            if (head == null || k == 1) return head;

            ListNode node = new ListNode(0), pre = node;
            node.next = head;
            for (int i = 1; head != null; ++i) {
                if (i % k == 0) {
                    // 每k次  翻转
                    pre = reverse(pre, head.next);
                    head = pre.next;
                } else {
                    head = head.next;
                }
            }

            return node.next;
        }

        public ListNode reverse(ListNode start, ListNode end) {
            ListNode head = start.next;
            // 从start, end 之间的逆序
            ListNode move = head.next;
            while (move != end) {
                head.next = move.next;
                //prettyPrintLinkedList(start);
                move.next = start.next;
                //prettyPrintLinkedList(start);
                start.next = move;
                //prettyPrintLinkedList(start);
                move = head.next;
                //prettyPrintLinkedList(start);
                System.out.println("==========");
            }
            return head;
        }
    }

    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for (int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);

        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        return dummyRoot.next;
    }

    public static void prettyPrintLinkedList(ListNode node) {
        while (node != null && node.next != null) {
            System.out.print(node.val + "->");
            node = node.next;
        }

        if (node != null) {
            System.out.println(node.val);
        } else {
            System.out.println("Empty LinkedList");
        }
    }
}