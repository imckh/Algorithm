/*
5164. 从链表中删去总和值为零的连续节点

给你一个链表的头节点 head，请你编写代码，反复删去链表中由 总和 值为 0 的连续节点组成的序列，直到不存在这样的序列为止。

删除完毕后，请你返回最终结果链表的头节点。

 

你可以返回任何满足题目要求的答案。

（注意，下面示例中的所有序列，都是对 ListNode 对象序列化的表示。）

示例 1：

输入：head = [1,2,-3,3,1]
输出：[3,1]
提示：答案 [1,2,1] 也是正确的。
示例 2：

输入：head = [1,2,3,-3,4]
输出：[1,2,4]
示例 3：

输入：head = [1,2,3,-3,-2]
输出：[1]
 

提示：

给你的链表中可能有 1 到 1000 个节点。
对于链表中的每个节点，节点的值：-1000 <= node.val <= 1000.
*/
import java.util.*;

public class P5164 {
    public static void main(String[] args) {
        ListNode head = stringToListNode("[1,9,-3,3,1]");
        head = stringToListNode("[0]");
        prettyPrintLinkedList(new Solution().removeZeroSumSublists(head));
    }

    static class Solution {
        public ListNode removeZeroSumSublists(ListNode head) {
            ListNode f = new ListNode(0);
            f.next = head;
            ListNode first = head;

            Map<ListNode, ListNode> parent = new HashMap<>(); // 保存每个节点和父节点的关系
            parent.put(head, null);
            while (first.next != null) {
                parent.put(first.next, first);
                first = first.next;
            }

            first = head;

            while (first != null) {
                int t = 0;
                ListNode last = first;

                while (last != null) { // 每个数字向前找和
                    t += last.val;
                    System.out.print(t);
                    System.out.println(" last = " + last.val);
                    if (t == 0) {

                        // System.out.println(last.val);

                        parent.put(first.next, parent.get(last)); // next指向last的上一个节点

                        ListNode temp = last;
                        ListNode lastlast = parent.get(last);
                        while (temp != first.next) { // 区间内的删除
                            parent.remove(temp); // 更新父节点map
                            temp = temp.next;
                        }
                        // System.out.println(last.val);
                        if (last == f.next) {
                            f.next = first.next;
                        } else {
                            lastlast.next = first.next;
                        }
                        break;
                    }
                    last = parent.get(last);
                }

                first = first.next;
            }

            return f.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
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