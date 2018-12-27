/*
21. 合并两个有序链表

https://leetcode-cn.com/problems/merge-two-sorted-lists/submissions/
https://leetcode-cn.com/explore/featured/card/top-interview-questions-easy/6/linked-list/44/

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
*/
public class P21 {
    public static void main(String[] args) {
        ListNode l = stringToListNode("[1,2,3,4,5]");
        ListNode l2 = stringToListNode("[3,4,7,9,10]");
        prettyPrintLinkedList(new Solution().mergeTwoLists(l, l2));
    }

    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if (l1 == null && l2 == null) {
                return null;
            }
            
            ListNode first = new ListNode(0);
            ListNode t = first;
            
            while(l1 != null && l2 != null) {
                if (l1.val < l2.val) {
                    t.next = l1;
                    l1 = l1.next;
                } else {
                    t.next = l2;
                    l2 = l2.next;
                }
                t = t.next;
            }
            
            // 链表最后一个
            t.next = (l1 != null) ? l1 : l2;
            
            return first.next;
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