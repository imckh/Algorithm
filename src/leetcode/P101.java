/*

给定一个二叉树，检查它是否是镜像对称的。

例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

    1
   / \
  2   2
 / \ / \
3  4 4  3
但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

    1
   / \
  2   2
   \   \
   3    3
说明:

如果你可以运用递归和迭代两种方法解决这个问题，会很加分。

比较根结点的左右两棵子树是否对称，如果左右子树的值相同，那么再分别对左子树的左节点和右子树的右节点，左子树的右节点和右子树的左节点做比较即可

*/
public class P101 {
    public static void main(String[] args) {
        System.out.println("哈哈");
        TreeNode q = new TreeNode(0);
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);
        q.left = q1;
        q1.left = q2;
        q1.right = q3;
        TreeNode q4 = new TreeNode(1);
        TreeNode q5 = new TreeNode(2);
        TreeNode q6 = new TreeNode(3);
        q.right = q4;
        q4.left = q6;
        q4.right = q5;

        System.out.println(new Solution().isSymmetric(q));
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
    public boolean isSymmetric(TreeNode root) {
        //if (root == null) return true;
        // 左右都空
        //if (root.left == null && root.right == null) return true;
        // 仅左空或仅右空
        //if (root.left == null || root.right == null) return false;

        //TreeNode left = root.left;
        //TreeNode right = root.right;
        //if (left.val != right.val) return false; 
        // 判断子节点对称性

        // 前边的判断不需要, 这个题与100题类似
        return isSymmetricTree(root, root);
    }

    public boolean isSymmetricTree(TreeNode p, TreeNode q) { //深搜比较各个节点
        // 验证节点存在
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val == q.val) {
            // 验证值相同: 对称性将左右节点的比较反过来
            return isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
        }
        return false;
    }
}