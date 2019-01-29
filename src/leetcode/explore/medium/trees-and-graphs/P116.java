
/*
116. 填充同一层的兄弟节点

https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/88/

给定一个二叉树

struct TreeLinkNode {
  TreeLinkNode *left;
  TreeLinkNode *right;
  TreeLinkNode *next;
}
填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。

初始状态下，所有 next 指针都被设置为 NULL。

说明:

你只能使用额外常数空间。
使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
你可以假设它是一个完美二叉树（即所有叶子节点都在同一层，每个父节点都有两个子节点）。
示例:

给定完美二叉树，

     1
   /  \
  2    3
 / \  / \
4  5  6  7
调用你的函数后，该完美二叉树变为：

     1 -> NULL
   /  \
  2 -> 3 -> NULL
 / \  / \
4->5->6->7 -> NULL
*/
import java.util.*;

public class P116 {
    public static void main(String[] args) {
        TreeLinkNode root = (TreeLinkNode) stringToTreeLinkNode("[1,2,3,4,5,6,7]");
        new Solution().connect(root);
        prettyPrintTree(root);
    }

    public static class Solution {
        public void connect(TreeLinkNode root) {
            if (root == null) {
                return;
            }
            // 合理使用数据结构中的next节点
            while (root.left != null) {
                // 循环外可以不需要额外pointer,直接用root.next做下去
                // 每行
                TreeLinkNode pre = root;
                while (root != null) {// 循环内用一个pre记录循环开始的位置
                    System.out.printf("%d <- %d\n", root.left.val, root.right.val);
                    root.left.next = root.right;
                    System.out.printf("%d <- %d\n", root.right.val, (root.next != null) ? root.next.val : -1);
                    root.right.next = (root.next != null) ? root.next.left : null;
                    root = root.next;
                }
                root = pre.left;
            }
        }

        // 递归版
        public void connect_r(TreeLinkNode root) {
            if (root == NULL || root.left == NULL)
                return;
            root.left.next = root.right; // 连接左右
            if (root.next != null) // 如果存在next
                root.right.next = root.next.left; // 将右子节点的next连接到root.next的左子节点
            connect(root.left);
            connect(root.right);
        }
    }

    public static class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;

        TreeLinkNode(int x) {
            val = x;
        }
    }

    public static TreeLinkNode stringToTreeLinkNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeLinkNode root = new TreeLinkNode(Integer.parseInt(item));
        Queue<TreeLinkNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeLinkNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeLinkNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeLinkNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeLinkNode node, String prefix, boolean isLeft) {
        if (node == null) {
            System.out.println("Empty tree");
            return;
        }

        if (node.right != null) {
            prettyPrintTree(node.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + node.val);

        if (node.left != null) {
            prettyPrintTree(node.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }

    public static void prettyPrintTree(TreeLinkNode node) {
        prettyPrintTree(node, "", true);
    }
}