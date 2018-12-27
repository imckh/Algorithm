/*
234. 回文链表

https://leetcode-cn.com/problems/palindrome-linked-list/
https://leetcode-cn.com/explore/featured/card/top-interview-questions-easy/6/linked-list/45/

请判断一个链表是否为回文链表。

示例 1:

输入: 1->2
输出: false
示例 2:

输入: 1->2->2->1
输出: true
进阶：
你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
*/

public class P234 {
    public static void main(String[] args) {
        ListNode l = stringToListNode("[3,4,7,4,3]");
        System.out.println(new Solution().isPalindrome(l));
    }
    public static class Solution {
        public boolean isPalindrome(ListNode head) {
            // 要实现 O(n) 的时间复杂度和 O(1) 的空间复杂度，需要翻转后半部分
            if (head == null || head.next == null) {
                return true;
            }
            ListNode fast = head;
            ListNode slow = head;
            // 根据快慢指针，找到链表的中点
            while(fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            slow = reverse(slow.next); // 中点
            while(slow != null) {
                if (head.val != slow.val) {
                    return false;
                }
                head = head.next;
                slow = slow.next;
            }
            return true;
        }
    
        private ListNode reverse(ListNode head){
            // 递归到最后一个节点，返回新的新的头结点
            if (head.next == null) {
                return head;
            }
            ListNode newHead = reverse(head.next);
            head.next.next = head;
            head.next = null;
            return newHead;
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