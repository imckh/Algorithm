
/*
24. 两两交换链表中的节点

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.
说明:

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

*/
public class P24 {
    public static void main(String[] args) {
        ListNode l = stringToListNode("[1,2,3,4,5]");
        prettyPrintLinkedList(l);
        prettyPrintLinkedList(new Solution().swapPairs(l));
    }

    static class Solution {
        public ListNode swapPairs(ListNode head) {
            if (head == null || head.next == null) return head;

            ListNode node = new ListNode(0);
            ListNode cur = node;
            node.next = head;
            while (cur.next != null && cur.next.next != null) {
                cur = swapWithNext(cur);
            }

            return node.next;
        }

        /**
         * 交换node的后两个并返回后两个的后边的
         * @param node
         * @return
         */
        public ListNode swapWithNext(ListNode node) {
            ListNode first = node.next;
            ListNode second = first.next;

            // 这个注释掉的是错误的, 链表操作一定要注意next的位置
            // 一不小心就是错误
            // node.next = second;
            // second.next = first;
            // first.next = second.next;

            first.next = second.next;
            second.next = first;
            node.next = second;

            return node.next.next;
        }
    }


    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public static ListNode stringToListNode(String input) {
        // Generate array from the input
        int[] nodeValues = stringToIntegerArray(input);
    
        // Now convert that list into linked list
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for(int item : nodeValues) {
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
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }
}