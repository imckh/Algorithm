/*
94. 二叉树的中序遍历

https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/85/


给定一个二叉树，返回它的中序 遍历。

示例:

输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,3,2]
进阶: 递归算法很简单，你可以通过迭代算法完成吗？
*/
import java.util.*;
public class P94{
    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[1,2,3,4,5,6,7,8]");
        prettyPrintTree(root);

        System.out.println(new Solution().inorderTraversal(root));
        System.out.println(new Solution().inorderTraversal_iter(root));
    }

    static class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            inorderTraversal_r(root, result);
            return result;
        }
        // 递归方式
        public void inorderTraversal_r(TreeNode root, List<Integer> list) {
            if (root == null) {
                return;
            }
            // 中序, 根节点在中间的顺序
            inorderTraversal_r(root.left, list);
            list.add(root.val);
            inorderTraversal_r(root.right, list);
        }
        // 迭代方式
        // 使用栈
        public List<Integer> inorderTraversal_iter(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            LinkedList<TreeNode> stack = new LinkedList<>();

            // 将所有的左子树先入栈
            while (root != null) {
                stack.push(root);
                //System.out.println("push left: " + stack);
                root = root.left;
            }

            while (!stack.isEmpty()) {
                TreeNode node = stack.peek();
                result.add(node.val);

                if (node.right == null) {
                    // 每pop一次检查：右边为空就一直pop
                    node = stack.pop();
                    //System.out.println(" pop" + stack);
                    while (!stack.isEmpty() && stack.peek().right == node) {
                        node = stack.pop();
                        //System.out.println("   pop" + stack);
                    }
                } else {
                    // 不为空就去右边，然后再一次将左边的一条下来全加进stack里
                    node = node.right;
                    while (node != null) {
                        stack.push(node);
                        //System.out.println("push" + stack);
                        node = node.left;
                    }
                }
            }
            return result;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
        @Override
        public String toString() {
            return val+"";
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