/*
5128. 最深叶节点的最近公共祖先

给你一个有根节点的二叉树，找到它最深的叶节点的最近公共祖先。

回想一下：

叶节点 是二叉树中没有子节点的节点
树的根节点的 深度 为 0，如果某一节点的深度为 d，那它的子节点的深度就是 d+1
如果我们假定 A 是一组节点 S 的 最近公共祖先，s 中的每个节点都在以 A 为根节点的子树中，且 A 的深度达到此条件下可能的最大值。
 

示例 1：

输入：root = [1,2,3]
输出：[1,2,3]
示例 2：

输入：root = [1,2,3,4]
输出：[4]
示例 3：

输入：root = [1,2,3,4,5]
输出：[2,4,5]
 

提示：

给你的树中将有 1 到 1000 个节点。
树中每个节点的值都在 1 到 1000 之间。
*/
import java.util.*;

public class P5128 {
    public static void main(String[] args) {
        String root = "[1,2,null,4,3]";
        //root = "[1,2,3,null,null,null,4]";
        //root = "[1,2,3,4,5,6,7]";

        TreeNode treeRoot = stringToTreeNode(root);
        prettyPrintTree(treeRoot);
        TreeNode results = new Solution().lcaDeepestLeaves(treeRoot);

        prettyPrintTree(var);
    }

    static class Solution {
        public TreeNode lcaDeepestLeaves(TreeNode root) {
            
        }

        public static TreeNode getDeepesNode(TreeNode root, int level) {
            TreeNode treeNode = null;
            if (pRoot == null || pLeft.val > pRight.val) {
                return null;
            }
            if (pRoot.val >= pRight.val) {
                treeNode = getLastCommonNode(pRoot.left, pLeft, pRight);
            }
            if (pRoot.val <= pLeft.val) {
                treeNode = getLastCommonNode(pRoot.right, pLeft, pRight);
            }
            if (pRoot.val >= pLeft.val && pRoot.val <= pRight.val) {
                return pRoot;
            }
            return treeNode;
        }

        public static TreeNode getLastCommonNode(TreeNode pRoot, TreeNode pLeft, TreeNode pRight) {
            TreeNode treeNode = null;
            if (pRoot == null || pLeft.val > pRight.val) {
                return null;
            }
            if (pRoot.val >= pRight.val) {
                treeNode = getLastCommonNode(pRoot.left, pLeft, pRight);
            }
            if (pRoot.val <= pLeft.val) {
                treeNode = getLastCommonNode(pRoot.right, pLeft, pRight);
            }
            if (pRoot.val >= pLeft.val && pRoot.val <= pRight.val) {
                return pRoot;
            }
            return treeNode;
        }
    }

    static class TreeNode {
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