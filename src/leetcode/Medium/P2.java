/*
2. 两数相加

给定两个非空链表来表示两个非负整数。位数按照逆序方式存储，它们的每个节点只存储单个数字。将两数相加返回一个新的链表。

你可以假设除了数字 0 之外，这两个数字都不会以零开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class P2{
    public static void main(String[] args) {
        ListNode l1 = Wrapper.stringToListNode("[2,4,3]");
        ListNode l2 = Wrapper.stringToListNode("[9,9,9, 3]");
        Wrapper.prettyPrintLinkedList(new Solution().addTwoNumbers(l1, l2));
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode result = new ListNode(0);
        ListNode head = result;
        int carry = 0; // 进位
        while (l1 != null || l2 != null) {
            int l1Val = l1 == null ? 0: l1.val;
            int l2Val = l2 == null ? 0: l2.val;
            int val = l1Val + l2Val + carry;
            int cur = val > 9 ? val - 10 : val;
            carry = val > 9 ? 1 : 0;
            result.next = new ListNode(cur);

            result = result.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        if (carry != 0) {
            result.next = new ListNode(carry);
        }

        return head.next;
    }
    // 改进: 减少运算量
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(0);
        ListNode pointer = ans;
        int carry = 0;
        while (!(l1 == null && l2 == null)) {
            carry = l1 == null ? carry : carry + l1.val;
            carry = l2 == null ? carry : carry + l2.val;
            pointer.next = new ListNode(carry % 10);
            carry /= 10;
            pointer = pointer.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry != 0) pointer.next = new ListNode(carry);
        return ans.next;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Wrapper {
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