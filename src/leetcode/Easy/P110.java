/*
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]

    3
   / \
  9  20
    /  \
   15   7
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]

       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
返回 false 。
*/
public class P110 {
    public static void main(String[] args) {
        TreeNode q = new TreeNode(0);
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);
        TreeNode q7 = new TreeNode(3);
        q.left = q1;
        q1.left = q2;
        q2.left = q3;
        q3.left = q7;
        TreeNode q4 = new TreeNode(1);
        TreeNode q5 = new TreeNode(2);
        TreeNode q6 = new TreeNode(3);
        TreeNode q8 = new TreeNode(3);
        q.right = q4;
        q4.right = q5;
        q5.right = q6;
        q6.right = q8;

        boolean t = new Solution().isBalanced(q);
        System.out.println(t);
    }

    public static void printTreeNode(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        printTreeNode(treeNode.left);
        System.out.print(treeNode.val + " ");
        printTreeNode(treeNode.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    /**
     * https://github.com/Blankj/awesome-java-leetcode/blob/master/note/110/README.md
     * 
     * 二叉树高度平衡指的是二叉树的每个节点的两棵子树的高度差都不超过 1，
     * 那么我们只需计算左右子树的高度，判断其高度差是否不超过 1 即可，
     * 如果超过 1，就代表其不是高度平衡的，立即返回不是即可，
     * 这里用返回 -1 代表不是
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return helper(root) != -1;
    }

    public int helper(TreeNode node) {
        if (node == null) return 0;

        // 左右子树高度
        int left = helper(node.left);
        if (left == -1) return -1;
        int right = helper(node.right);
        if (right == -1) return -1;

        // 左右子树高度差, 大于1直接返回-1代表不平衡
        if (Math.abs(left - right) > 1) return -1;

        // 底到该层的高度(深度)
        return 1 + Math.max(left, right);
    }
}