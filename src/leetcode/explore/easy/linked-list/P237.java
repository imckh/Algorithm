/*
237. 删除链表中的节点
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/6/linked-list/41/
https://leetcode-cn.com/problems/delete-node-in-a-linked-list/comments/
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。

现有一个链表 -- head = [4,5,1,9]，它可以表示为:

    4 -> 5 -> 1 -> 9
示例 1:

输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2:

输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
说明:

链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。
*/
public class P237 {
    public static void main(String[] args) {

    }

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public void deleteNode(ListNode node) {
            // 把当前的node变成下一个
            // 一个ListNode包含就两个变量，都改成下一个的值就可以了
            
            // 虽然题目没这个要求, 加入删最后一个的情况
            if (node.next == null){
                node = null;
                return;
            }

            node.val = node.next.val;
            node.next = node.next.next;
        }
    }
}