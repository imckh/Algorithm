/*
141. 环形链表

https://leetcode-cn.com/problems/linked-list-cycle/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/46/

给定一个链表，判断链表中是否有环。

为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

 

示例 1：

输入：head = [3,2,0,-4], pos = 1
输出：true
解释：链表中有一个环，其尾部连接到第二个节点。
https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png

示例 2：

输入：head = [1,2], pos = 0
输出：true
解释：链表中有一个环，其尾部连接到第一个节点。
https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png
示例 3：

输入：head = [1], pos = -1
输出：false
解释：链表中没有环。
https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png


 

进阶：

你能用 O(1)（即，常量）内存解决此问题吗？
*/

public class P141 {
    public static void main(String[] args) {
        ListNode s = stringToListNode("[0,1,2,3,4,5,6,7,8,9,11,12,13,14,15]");
        System.out.println(new Solution().hasCycle(s));

        addCycle(s, 1);

        System.out.println(new Solution().hasCycle(s));
    }

    public static void addCycle(ListNode head, int pos) {
        int i = 0;
        ListNode posNode = null;
        ListNode tail = null; // 尾
        while (head != null) {
            if (i == pos) {
                posNode = head;
            }
            if (head.next == null) {
                tail = head;
            }
            head = head.next;
            i++;
        }
        tail.next = posNode;
    }
    public static class Solution {
        public boolean hasCycle(ListNode head) {
            if (head == null || head.next == null) return false;
            // slow走一步 fast走两步  如果相遇 有环
            /*
            想象成在操场跑圈
            一个是另一个速度的两倍, 慢的跑一圈快的跑了两圈
            慢的那个肯定会在一圈之内被追上
            */
            ListNode slow = head;
            ListNode fast = head.next;
            while (fast != null && fast.next != null) {
                //System.out.printf("slow: %d, fast: %d\n", slow.val, fast.val);
                if (fast == slow) return true;
                slow = slow.next;
                fast = fast.next.next;
            }

            return false;
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