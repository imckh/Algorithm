/*
230.二叉搜索树中第K小的元素

https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/89/

给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。

说明：
你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。

示例 1:

输入: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
输出: 1
示例 2:

输入: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
输出: 3
进阶：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
*/
import java.util.*;
public class P230 {
    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[5,3,6,2,4,null,null,1]");
        prettyPrintTree(root);
        System.out.println(new Solution().kthSmallest(root, 2));
        System.out.println(new Solution_stack().kthSmallest(root, 2));
    }

    /*
    二叉搜索树的性质
        1. 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
        2. 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
        2. 任意节点的左、右子树也分别为二叉查找树；
        2. 没有键值相等的节点。
    
    根据这些直接中序遍历找到第k个即可
    */
    static class Solution {
        private int result = 0;
        private int count = 0;
         
        public int kthSmallest(TreeNode root, int k) {
            helper(root, k);
            return result;
        }
        
        private void helper(TreeNode root, int k) {
            if (root == null) {
                return;
            }
            helper(root.left, k);
            count++;
            if (count == k) {
                result = root.val;
            }
            helper(root.right, k);
        }
    }

    static class Solution_stack {
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            /*
            类似173  
                https://leetcode-cn.com/problems/binary-search-tree-iterator/
            这个题

            
            用 stack，从第一个点开始，走 k-1 步，就是第 k 个点了。
            时间复杂度是 O(h + k)O(h+k) h 是树的高度
            */
            for (int i = 0; i < k - 1; i++) {
                TreeNode node = stack.peek();
                
                if (node.right == null) {
                    node = stack.pop();
                    while (!stack.isEmpty() && stack.peek().right == node) {
                        node = stack.pop();
                    }
                } else {
                    node = node.right;
                    while (node != null) {
                        stack.push(node);
                        node = node.left;
                    }
                }
            }
            
            return stack.peek().val;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }

    public static void prettyPrintTree(TreeNode node, String prefix, boolean isLeft) {
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

    public static void prettyPrintTree(TreeNode node) {
        prettyPrintTree(node, "", true);
    }
}