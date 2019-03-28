/*
148. 排序链表

https://leetcode-cn.com/problems/sort-list/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/56/linked-list/135/

在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5
*/
public class P148 {
    public static void main(String[] args) {
        ListNode l1 = stringToListNode("[5,4,3,2,1,-1]");
        ListNode l2 = stringToListNode("[5,4,3,2,1,-1]");
        // ListNode l3 = stringToListNode("[1,2,3,4,7,8]");
        // ListNode l4 = stringToListNode("[3,4,5,6]");
        // // prettyPrintLinkedList(new Solution_merge().mergeTwoLists(l3, l4));
        // prettyPrintLinkedList(new Solution_merge().merge(l3, l4));
        // ListNode l5 = stringToListNode("[2]");
        // ListNode l6 = stringToListNode("[1]");
        // // prettyPrintLinkedList(new Solution_merge().mergeTwoLists(l5, l6));
        // prettyPrintLinkedList(new Solution_merge().merge(l5, l6));
        //prettyPrintLinkedList(new Solution_quick_sort().sortList(l1));
        prettyPrintLinkedList(new Solution_merge().sortList(l1));
    }

    // 需要注意的是快速排序跟数据的顺序有关系, 所以最坏情况是可能O(n^2)的
    // 所以开始之前要使整个shuffle数据
    // 提交到leetcode  巨慢,  288ms 战胜 17.02 % 的 java 提交记录
    static class Solution_quick_sort {
        public void swap(ListNode a, ListNode b) {
            int t = a.val;
            a.val = b.val;
            b.val = t;
        }

        public ListNode partation(ListNode start, ListNode end) {
            if (start == end) return start;

            ListNode p1 = start;
            ListNode p2 = p1.next;
            int key = start.val;

            while (p2 != end) {
                if (p2.val < key) {
                    p1 = p1.next;
                    swap(p1, p2); //找到一个比key小的数字，与p1到p2间的数交换
                }
                p2 = p2.next;
            }
            swap(start, p1); // 找到划分位置
            return p1;
        }

        public void quickSort(ListNode start, ListNode end) {
            if (start != end) {
                ListNode pt = partation(start, end);
                quickSort(start, pt);
                quickSort(pt.next, end);
            }
        }

        public ListNode sortList(ListNode head) {
            quickSort(head, null);
            return head;
        }
    }

    // 归并排序
    // 算法交换链表节点，时间复杂度O（nlogn）,不考虑递归栈空间的话空间复杂度是O（1）
    // 首先用快慢指针的方法找到链表中间节点，然后递归的对两个子链表排序，
    //把两个排好序的子链表合并成一条有序的链表。
    //归并排序应该算是链表排序最佳的选择了，保证了最好和最坏时间复杂度都是nlogn，
    //而且它在数组排序中广受诟病的空间复杂度在链表排序中也从O(n)降到了O(1)
    static class Solution_merge {
        public ListNode sortList(ListNode head) {
            if (head == null || head.next == null) return head;

            // 快慢指针找中点
            ListNode fast = head, slow = head;
            while (fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }
            fast = slow;
            slow = slow.next;
            fast.next = null;
            fast = sortList(head); // 排序前半段
            slow = sortList(slow); // 排序后半段
            return mergeTwoLists(fast, slow); // 两部分归并
        }

        // 归并两个已经排好序的数组
        // leetcode problem 21
        // src\leetcode\explore\easy\linked-list\P21.java
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
        ListNode(int x) { val = x; }
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