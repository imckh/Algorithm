/*
106. 从中序与后序遍历序列构造二叉树

https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

根据一棵树的中序遍历与后序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出

中序遍历 inorder = [9,3,15,20,7]
后序遍历 postorder = [9,15,7,20,3]
返回如下的二叉树：

    3
   / \
  9  20
    /  \
   15   7
*/
import java.util.*;
public class P106 {
    public static void main(String[] args) {
        int[] postorder = new int[] { 9,15,7,20,3 };
        int[] inorder = new int[] { 9, 3, 15, 20, 7 };
        prettyPrintTree(new Solution().buildTree(inorder, postorder));
    }

    static class Solution {
        // 后序遍历:左-->右-->根,中序遍历:左-->根-->右
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            if ((postorder == null || postorder == null) || postorder.length != inorder.length) {
                return null;
            }
            if (postorder.length == inorder.length && inorder.length == 1) {
                return new TreeNode(postorder[0]);
            }

            return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }
        /*
              B
            /  \
           Z    S
          / \  / \
         H  J F   D
                    left    right
        pre : [B,  Z,H,J,  S,F,D]
               left         right
        in  : [H,Z,J,  B,  F,S,D]
               left    right
        post: [H,J,Z,  F,D,S,  B]
        
        然后分别对B的左右子树[Z,H,J][S,F,D]构造树
        首先看后序遍历第一位置,就是根节点,再看中序遍历,用这个根节点把中序遍历分成两部分
        */
        private TreeNode buildTree(int[] inorder, int instart, int inend, int[] postorder, int poststart, int postend) {
            if (instart > inend) {
                return null;
            }

            TreeNode root = new TreeNode(postorder[postend]);
            int position = findPosition(inorder, instart, inend, postorder[postend]);

            //                           后序起始位置        在中序遍历中的长度
            // 左子树的后序遍历结束位置 == (poststart) + ((position - 1) - (instart))

            // 右子树的后序遍历开始位置 == (postend - 1) - (inend - (position + 1)) = postend - inend + position
            root.left = buildTree(inorder, instart, position - 1, postorder, poststart, poststart + position - instart - 1);
            root.right = buildTree(inorder, position + 1, inend, postorder, postend - inend + position, postend - 1);

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