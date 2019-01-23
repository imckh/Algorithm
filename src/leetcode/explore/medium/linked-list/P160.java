import java.util.HashSet;
import java.util.Set;

/*
160. 相交链表

https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/31/linked-list/84/

编写一个程序，找到两个单链表相交的起始节点。

如下面的两个链表：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_statement.png

在节点 c1 开始相交。

 

示例 1：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_1.png

输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
输出：Reference of the node with value = 8
输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 

示例 2：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_2.png

输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
输出：Reference of the node with value = 2
输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 

示例 3：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/14/160_example_3.png

输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
输出：null
输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
解释：这两个链表不相交，因此返回 null。
 

注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
*/

public class P160 {
    public static void main(String[] args) {
        ListNode l1 = Wrapper.stringToListNode("[1,2,3,4,5,2,3,4,5,2,3,4,5]");
        ListNode l2 = Wrapper.stringToListNode("[1,2,3,4,5,6,7,2,3,4,5,6,7,2,3,4,5,6,7]");
        ListNode l3 = Wrapper.stringToListNode("[10,11,12]");

        ListNode l1head = l1;
        ListNode l2head = l2;
        while (l1head.next != null) {
            l1head = l1head.next;
        }
        if (l1head.next == null) {
            //l1head.next = l3;
        }
        while (l2head.next != null) {
            l2head = l2head.next;
        }
        if (l2head.next == null) {
            //l2head.next = l3;
        }

        /*

        1->2->3->4->5        ↘
                                ->10->11->12
        1->2->3->4->5->6->7  ↗

        */

        // Wrapper.prettyPrintLinkedList(l1);
        // Wrapper.prettyPrintLinkedList(l2);

        // System.out.println(new Solution().getIntersectionNode_1(l1, l2));
        System.out.println(new Solution().getIntersectionNode_2(l1, l2));
    }

    static public class Solution {
        public ListNode getIntersectionNode_1(ListNode headA, ListNode headB) {
            /**
             * 定义两个指针, 第一轮让两个到达末尾的节点指向另一个链表的头部, 最后如果相遇则为交点(在第一
             * 移动中恰好抹除了长度差) 两个指针等于移动了相同的距离, 有交点就返回, 无交点就是各走了两
             * 指针的长度
             **/
            if (headA == null || headB == null)
                return null;
            ListNode pA = headA, pB = headB;
            // 在这里第一轮体现在pA和pB第一次到达尾部会移向另一链表的表头, 
            // 而第二轮体现在如果pA或pB相交就返回交点, 不相交最后就是null==null
            // 时间复杂度是O(lenA + lenB)
            while (pA != pB) {
                pA = pA == null ? headB : pA.next;
                pB = pB == null ? headA : pB.next;
                System.out.print("PA : ");
                Wrapper.prettyPrintLinkedList(pA);
                System.out.print("PB : ");
                Wrapper.prettyPrintLinkedList(pB);
            }
            return pA;
        }

        /*
        1. 分别找出两个长度
        2. 让长的先走到剩余长度和短的一样地方
        3. 然后让两个同时走到第一个node相同的地方, 返回结果即可

        每个LinkedList最多遍历两次, 时间复杂度 O(lengthA + lengthB)
        空间复杂度O(1)
        */
        public ListNode getIntersectionNode_2(ListNode headA, ListNode headB) {
            if (headA == null || headB == null) {
                return null;
            }
    
            ListNode currA = headA;
            ListNode currB = headB;
            int lengthA = 0;
            int lengthB = 0;
            while (currA != null) {
                currA = currA.next;
                lengthA++;
            }
            while (currB != null) {
                currB = currB.next;
                lengthB++;
            }
    
            currA = headA;
            currB = headB;
            while (lengthA > lengthB) {
                currA = currA.next;
                lengthA--;
            }
            while (lengthB > lengthA) {
                currB = currB.next;
                lengthB--;
            }
            
            while (currA != currB) {
                currA = currA.next;
                currB = currB.next;
            }
    
            return currA;
        }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override
        public String toString() {
            return val + "";
        }
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