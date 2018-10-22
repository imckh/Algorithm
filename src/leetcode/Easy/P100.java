/*
.
给定两个二叉树，编写一个函数来检验它们是否相同。

如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

示例 1:

输入:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

输出: true
示例 2:

输入:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

输出: false
示例 3:

输入:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

输出: false
 */
public class P100 {
    public static void main(String[] args) {
        System.out.println("哈哈");
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(1);
        q1.left = q2;
        q1.right = q3;
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(1);
        TreeNode p3 = new TreeNode(3);
        p1.left = p2;
        p1.right = p3;

        System.out.println(new Solution().isSameTree(p1, q1));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
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
    public boolean isSameTree(TreeNode p, TreeNode q) { //深搜比较各个节点
        // 验证节点存在
        //if ((p == null && q != null) || (p != null && q == null)) return false;
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        // 递归只需要关注当前的节点即可
        // if ((p.left == null && q.left != null) || (p.left != null && q.left == null)) return false;
        // if ((p.right == null && q.right != null) || (p.right != null && q.right == null)) return false;

        if (p.val == q.val) {
            // 验证值相同
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}