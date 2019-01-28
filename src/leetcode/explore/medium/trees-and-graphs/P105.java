/*
105. 从前序与中序遍历序列构造二叉树
https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/87/
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
*/

import java.util.*;
public class P105 {
    public static void main(String[] args) {
        int[] preorder = new int[] { 3, 9, 20, 15, 7 };
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };
        prettyPrintTree(new Solution().buildTree(preorder, inorder));
    }

    static class Solution {
        // 前序遍历:根-->左-->右,中序遍历:左-->根-->右
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if ((preorder == null || preorder == null) || preorder.length != inorder.length) {
                return null;
            }
            if (preorder.length == inorder.length && inorder.length == 1) {
                return new TreeNode(preorder[0]);
            }

            return buildTree(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
        }
        /*
              B
            /  \
           Z    S
          / \  / \
         H  J F   D
                    left    right
        pre : [B,  Z,H,J,  S,F,D]
        in  : [H,Z,J,  B,  F,S,D]
               left         right
        

        然后分别对B的左右子树[Z,H,J][S,F,D]构造树
        首先看前序遍历第一位置,就是根节点,再看中序遍历,用这个根节点把中序遍历分成两部分
        */
        /**
         * 
         * Description:
         * @param inorder
         * @param instart   
         * @param inend     
         * @param preorder
         * @param prestart  
         * @param preend    
         * @return
         * @author CuiKaihui 2019年1月28日
         */
        private TreeNode buildTree(int[] inorder, int instart, int inend, int[] preorder, int prestart, int preend) {
            if (instart > inend) {
                return null;
            }

            TreeNode root = new TreeNode(preorder[prestart]);
            int position = findPosition(inorder, instart, inend, preorder[prestart]);

            //                            前序起始位置      在中序遍历中的长度
            // 左子树的前序遍历结束位置 == (prestart + 1) + ((position - 1) - (instart))
            // 右子树同理
            root.left = buildTree(inorder, instart, position - 1, preorder, prestart + 1, prestart + position - instart);
            root.right = buildTree(inorder, position + 1, inend, preorder, position - inend + preend + 1, preend);

            return root;
        }

        private int findPosition(int[] arr, int start, int end, int key) {
            for (int i = start; i <= end; i++) {
                if (arr[i] == key) {
                    return i;
                }
            }
            return -1;
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