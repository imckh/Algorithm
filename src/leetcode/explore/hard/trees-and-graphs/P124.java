/*
124. 二叉树中的最大路径和

https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-hard/57/trees-and-graphs/140/

给定一个非空二叉树，返回其最大路径和。

本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。

示例 1:

输入: [1,2,3]

       1
      / \
     2   3

输出: 6
示例 2:

输入: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

输出: 42
*/
import java.util.*;
public class P124 {
    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[3,5,1,6,2,0,8,null,null,7,4]");
        prettyPrintTree(root);
        System.out.println(new Solution().maxPathSum(root));
    }

    static class Solution {
        private int res = Integer.MIN_VALUE;
        public int maxPathSum(TreeNode root) {
            helper(root);
            return res;
        }

        /*
        指定某个节点为根时，一条经过root的最大路径，这条路径可能是： 
            1) 左边某条路径 + root + 右边某条路径 (左右子树的路径加上当前节点) 
            2) 左边某条路径 + root (左子树的路径加上当前节点) 
            3) root + 右边某条路径 (右子树的路径加上当前节点) 
            4) root
        然而这四种情况只是用来计算以当前节点根的最大路径，如果当前节点上面还有节点，那它的父节点是不能累加第1种情况的。

        所以要保存两个最大值，
            一个是当前节点下最大路径和maxCurrent（没有父节点）
            另一个是如果要连接父节点时最大的路径和maxSum
         */
        private int helper(TreeNode root) {
            if (root == null) return 0;
            int left = helper(root.left); //经过左节点的路径的最大和
            int right = helper(root.right); //经过右节点的路径的最大和

            int maxSum = Math.max(Math.max(left + root.val, right + root.val), root.val); // 当前节点下最大路径和, 没有父节点
            int maxCurrent = Math.max(maxSum, root.val + left + right); // 连接父节点时最大的路径
            System.out.println(maxSum);
            res = Math.max(res, maxCurrent);
            return maxSum;
        }
    }

    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }
    
        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
    
            if (node == null) {
              output += "null, ";
              continue;
            }
    
            output += String.valueOf(node.val) + ", ";
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
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
        while(!nodeQueue.isEmpty()) {
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
        prettyPrintTree(node,  "", true);
    }
}