/*
将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

示例:

给定有序数组: [-10,-3,0,5,9],

一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

      0
     / \
   -3   9
   /   /
 -10  5
*/
public class P108 {
    public static void main(String[] args) {
        int[] q = new int[]{-10,-3,0,5,9};
        TreeNode t = new Solution().sortedArrayToBST(q);
        printTreeNode(t);
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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        // 注意是排好序的数组
        return halfArrayToBST(nums, 0, nums.length - 1);
    }

    /**
     * 左右分别递归构建子树
     * 
     * 1. 若任意节点的左子树不空,则左子树上所有节点的值均小于它的根节点的值;
     * 2. 若任意节点的右子树不空,则右子树上所有节点的值均大于它的根节点的值;
     * 3. 任意节点的左/右子树也分别为二叉查找树;
     * 4. 没有键值相等的节点.
     * 
     * 注意是排好序的数组
     * 用递归来构建一棵二叉搜索树, 每次把数组分为两半, 把数组中间的值作为其父节点, 然后把数组的左右两部分继续构造其左右子树
     * 
     * @param nums 数组
     * @param start 起始位置
     * @param end 终止位置
     * @return
     */
    private TreeNode halfArrayToBST(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        // 父节点
        TreeNode t = new TreeNode(nums[mid]);
        // 两个子节点
        TreeNode leftArrayNode = halfArrayToBST(nums, start, mid - 1);
        TreeNode rightArrayNode = halfArrayToBST(nums, mid + 1, end);

        t.left = leftArrayNode;
        t.right = rightArrayNode;

        return t;
    }
}