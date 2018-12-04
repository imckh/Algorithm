import java.util.*;

/*
23. 合并K个排序链表

https://leetcode-cn.com/problems/merge-k-sorted-lists/

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6
*/
public class P23 {
    public static void main(String[] args) {
        ListNode l1 = stringToListNode("[1, 4, 5]"); 
        ListNode l2 = stringToListNode("[1, 3, 4]"); 
        ListNode l3 = stringToListNode("[2, 6]");
        //prettyPrintLinkedList(new Solution().mergeKLists(new ListNode[] {l1, l2, l3}));
        prettyPrintLinkedList(new Solution().mergeKListsHeap(new ListNode[] {l1, l2, l3}));
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Solution {
        // 两两合并
        // O(Nlogk) k 为总链表个数，N 为总元素个数
        public ListNode mergeKLists(ListNode[] lists) {
            if (lists == null || lists.length == 0) return null;
            return binaryMerge(lists, 0, lists.length - 1);
        }

        public ListNode binaryMerge(ListNode[] list, int left, int right) {
            if (left >= right) return list[left];
            int mid = left + right >>> 1;
            ListNode l0 = binaryMerge(list, left, mid);
            ListNode l1 = binaryMerge(list, mid + 1, right);
            return mergeTwoLists(l0, l1);
        }

        // 21. 合并两个有序链表
        // https://leetcode-cn.com/problems/merge-two-sorted-lists/
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


        //利用优先队列, 该数据结构用到的是堆排序, 所以对总链表个数为 k 的复杂度为 logk, 总元素为个数为 N 的话, 其时间复杂度也为 O(Nlogk)
        public ListNode mergeKListsHeap(ListNode[] lists) {
            if (lists.length == 0) return null;
            Queue<ListNode> queue = new PriorityQueue<>(lists.length, (o1, o2) -> {
                // if (o1.val < o2.val) return -1;
                // else if (o1.val == o2.val) return 0;
                // else return 1;
                return o1.val - o2.val;
            });

            ListNode node = new ListNode(0), tmp = node;
            for (ListNode l : lists) {
                if (l != null) queue.add(l);
            }

            while (!queue.isEmpty()) {
                //tmp.next = queue.poll();
                ListNode head = queue.poll();
                tmp.next = head;
                tmp = head;
                if (tmp.next != null) {
                    queue.add(tmp.next);
                    // 进一个出一个
                }
            }

            return node.next;
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