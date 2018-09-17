// https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
/*
给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

示例 1:

输入: 1->1->2
输出: 1->2
示例 2:

输入: 1->1->2->3->3
输出: 1->2->3
*/
public class P83 {
    public static void main(String[] args) {
        System.out.println("------P83------");
        
        Solution solution = new Solution();

        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        ListNode l3 = new ListNode(2);
        // ListNode l4 = new ListNode(4);
        // ListNode l5 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        // l3.next = l4;
        // l4.next = l5;
        ListNode head = l1;
        // while (head != null) {
        //     System.out.println(head.val);
        //     head = head.next;
        // }
        solution.deleteDuplicates(l1);

        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }

        System.out.println("------P83------");
    }
}
/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode first = head;
        while (head != null) {
            if (head.next == null) {
                break;
            }
            if (head.val == head.next.val) {
                remove(head, head.next);
            } else {
                head = head.next;
            }
        }
        return first;
    }

    public void remove(ListNode lastNode, ListNode node) {
        lastNode.next = node.next;
        node = null;
    }
}