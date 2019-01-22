/*
328. 奇偶链表

https://leetcode-cn.com/problems/odd-even-linked-list/

https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/31/linked-list/83/

给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。

请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。

示例 1:

输入: 1->2->3->4->5->NULL
输出: 1->3->5->2->4->NULL
示例 2:

输入: 2->1->3->5->6->4->7->NULL 
输出: 2->3->6->7->1->5->4->NULL
说明:

应当保持奇数节点和偶数节点的相对顺序。
链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class P328{
    public static void main(String[] args) {
        ListNode l1 = Wrapper.stringToListNode("[1,2,3,4,5]");
        Wrapper.prettyPrintLinkedList(new Solution().oddEvenList(l1));
        ListNode l2 = Wrapper.stringToListNode("[1,2,3,4,5,6,7]");
        Wrapper.prettyPrintLinkedList(new Solution().oddEvenList(l2));
    }

    static class Solution {
        public ListNode oddEvenList(ListNode head) {
            if (head == null || head.next == null) {
                return head;
            }
            ListNode odd = new ListNode(0);
            ListNode even = new ListNode(0);
            ListNode oddFirst = odd;
            ListNode evenFirst = even;

            // 双链表, 一个奇一个偶, 最后连接起来
            boolean isOdd = true;
            while (head != null) {
                ListNode cur = head;
                head = head.next;
                cur.next = null;
                if (isOdd) {
                    odd.next = cur;
                    odd = odd.next;
                } else {
                    even.next = cur;
                    even = even.next;
                }
                isOdd = !isOdd;
            }
            odd.next = evenFirst.next;
            return oddFirst.next;
        }
    }
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    
    static class Wrapper {
        /**
         * Definition for singly-linked list.
         * public class ListNode {
         *     int val;
         *     ListNode next;
         *     ListNode(int x) { val = x; }
         * }
         */
        
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
    }
}

