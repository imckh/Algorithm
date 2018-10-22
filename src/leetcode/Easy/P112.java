/*
给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，

              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
*/
public class P112 {
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

        boolean t = new Solution().hasPathSum(q, 3);
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        // 剩余总和
        int sumRemaining = sum - root.val;
        // 恰好等于0, 左右子节点为空
        if (sumRemaining == 0 && root.left == null && root.right == null ) {
            return true;
        }
        // 分别对左右子树判断
        if (hasPathSum(root.left, sumRemaining)) {
            return true;
        }
        if (hasPathSum(root.right, sumRemaining)) {
            return true;
        }

        return false;
    }
}