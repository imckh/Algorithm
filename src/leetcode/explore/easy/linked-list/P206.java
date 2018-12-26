/*
206. 反转链表

https://leetcode-cn.com/problems/reverse-linked-list/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/43/

反转链表
反转一个单链表。

示例:

输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
进阶:
你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
*/
public class P206 {
    public static void main(String[] args) {
        ListNode l = stringToListNode("[1,2,3,4,5]");
        prettyPrintLinkedList(l);
        prettyPrintLinkedList(new Solution().reverseList(l));
    }

    static class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            
            ListNode first = new ListNode(0);

            while (head != null) {
                // 遍历head, 依次插入first后边
                ListNode temp = head;
                head = head.next;
                temp.next = first.next;
                first.next = temp;
            }
            return first.next;
        }
    }

    // 递归
    static public class SolutionR {

        ListNode newHead = null;

        public ListNode reverse(ListNode head) {
            if (head == null) {
                return null;
            }
            ListNode node = reverseNodes(head);
            node.next = null;
            return newHead;
        }

        private ListNode reverseNodes(ListNode head) {
            if (head.next == null) {
                newHead = head;
                return head;
            }
            ListNode node = reverseNodes(head.next); // 把子结点传给了父亲，并修改儿子
            node.next = head;
            return head;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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
        for(int index = 0; index < parts.length; index++) {
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