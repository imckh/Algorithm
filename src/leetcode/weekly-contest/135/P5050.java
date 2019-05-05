/*
5050. 从二叉搜索树到更大和树 

给出二叉搜索树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node 的新值等于原树的值之和，这个值应该大于或等于 node.val。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键小于节点键的节点。
节点的右子树仅包含键大于节点键的节点。
左右子树也必须是二叉搜索树。
 

示例：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/05/03/tree.png

输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 

提示：

树中的节点数介于 1 和 100 之间。
每个节点的值介于 0 和 100 之间。
给定的树为二叉搜索树。
*/
import java.util.*;
public class P5050 {
    public static void main(String[] args) {
        String tree = "[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]";
        TreeNode root = stringToTreeNode(tree);
        prettyPrintTree(root);

        new Solution().bstToGst(root);
        System.out.println("====================================");
        prettyPrintTree(root);
    }

    static class Solution {
        public TreeNode bstToGst(TreeNode root) {
            inOrderTraverse(root);

            return root;
        }

        // 中序遍历 从右子树开始
        // 右子树的最左节点+自身的值
        public void inOrderTraverse(TreeNode root) {
            if (root == null) return;
            
            if (root.right == null) return;
            inOrderTraverse(root.right);
            root.val = root.val + rightMax(root.right);

            if (root.left == null) return;

            // 左子树的最右
            leftRightMin(root.left, root.val);

            inOrderTraverse(root.left);
        }

        public void leftRightMin(TreeNode root, int num) {
            //if (root == null) return;
            if (root.right == null) {
                root.val = num + root.val;
                return;
            }
            leftRightMin(root.right, num);
        }

        public int rightMax(TreeNode root) {
            if (root.left == null) {
                return root.val;
            }
            return rightMax(root.left);
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