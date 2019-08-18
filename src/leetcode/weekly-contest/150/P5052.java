/*
5052. 最大层内元素和

给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。

请你找出层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。

 

示例：
https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/08/17/capture.jpeg


输入：[1,7,0,7,-8,null,null]
输出：2
解释：
第 1 层各元素之和为 1，
第 2 层各元素之和为 7 + 0 = 7，
第 3 层各元素之和为 7 + -8 = -1，
所以我们返回第 2 层的层号，它的层内元素之和最大。
 

提示：

树中的节点数介于 1 和 10^4 之间
-10^5 <= node.val <= 10^5
*/
import java.util.*;

public class P5052 {
    public static void main(String[] args) {
        String str = "[1,7,0,7,-8,1,-9,6,32,-9]";
        TreeNode root = stringToTreeNode(str);
        prettyPrintTree(root);

        System.out.println(new Solution().maxLevelSum(root));
    }

    static class Solution {

        public int maxLevelSum(TreeNode root) {
            LinkedList<TreeNode> queue = new LinkedList<>();
            LinkedList<TreeNode> queueNextL = new LinkedList<>();
            int max = Integer.MIN_VALUE;
            int maxLevel = 1;
            int level = 0;
            int curLevel = 0; // 下一层在第几个

            queue.addLast(root);
            curLevel++;
            level = root.val;
            max = Math.max(level, max);
            
            while (!(queue.isEmpty() && queueNextL.isEmpty()) ) {
                level = 0;
                //System.out.println("queue  " + queue);
                //System.out.println("queNXT " + queueNextL);
                if (curLevel % 2 != 0) { // 从queue中全部取出, 放入queueNextL中
                    while (!queue.isEmpty()) {
                        TreeNode node = queue.removeFirst();
                        level += node.val;
                        if (node.left != null) {
                            queueNextL.addLast(node.left);
                        }
                        if (node.right != null) {
                            queueNextL.addLast(node.right);
                        }
                    }
                } else {
                    while (!queueNextL.isEmpty()) { // 从 queueNextL 中全部取出, 放入 queue 中
                        TreeNode node = queueNextL.removeFirst();
                        level += node.val;
                        if (node.left != null) {
                            queue.addLast(node.left);
                        }
                        if (node.right != null) {
                            queue.addLast(node.right);
                        }
                    }
                }
                if (level > max) {
                    maxLevel = curLevel;
                    max = level;
                }

                curLevel++;
            }

            return maxLevel;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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
}