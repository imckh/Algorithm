/*
82. 删除排序链表中的重复元素 II

https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/

给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

示例 1:

输入: 1->2->3->3->4->4->5
输出: 1->2->5
示例 2:

输入: 1->1->1->2->3
输出: 2->3
*/
public class P82 {
    public static void main(String[] args) {
        String arr = "[1,2,3,3,4,4,5]";
        ListNode head = stringToListNode(arr);
        prettyPrintLinkedList(new Solution().deleteDuplicates(head));
    }

    static public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            if (head == null || head.next == null)
                return head;

            ListNode dummy = new ListNode(0);
            dummy.next = head;
            head = dummy;

            while (head.next != null && head.next.next != null) {
                // 判断后两个, 而不是当前的和下一个
                if (head.next.val == head.next.next.val) {
                    int val = head.next.val;
                    while (head.next != null && head.next.val == val) {
                        head.next = head.next.next;
                    }
                } else {
                    head = head.next;
                }
            }

            return dummy.next;
        }

        /*
        找到与当前节点值不同的下一个节点，如果判断其为相邻，
        则将当前节点的下一个指向递归后的返回节点。
        否则直接返回递归节点。
        时间复杂度为O(nodes) 空间复杂度为O(1)
        */
        public ListNode deleteDuplicates_recursively (ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode cur = head.next;
            while (cur != null && cur.val == head.val) {
                cur = cur.next;
            }
            
            if (head.next == cur) {
                head.next = deleteDuplicates(cur);
                return head;
            }
            return deleteDuplicates(cur);
        }
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
}