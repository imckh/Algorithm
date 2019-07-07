/*
5119. 删点成林
给出二叉树的根节点 root，树上每个节点都有一个不同的值。

如果节点值在 to_delete 中出现，我们就把该节点从树上删去，最后得到一个森林（一些不相交的树构成的集合）。

返回森林中的每棵树。你可以按任意顺序组织答案。

 

示例：



输入：root = [1,2,3,4,5,6,7], to_delete = [3,5]
输出：[[1,2,null,4],[6],[7]]
 

提示：

树中的节点数最大为 1000。
每个节点都有一个介于 1 到 1000 之间的值，且各不相同。
to_delete.length <= 1000
to_delete 包含一些从 1 到 1000、各不相同的值。
*/

import java.util.*;

public class P5119 {
    public static void main(String[] args) {
        String root = "[1,2,null,4,3]";
        //root = "[1,2,3,null,null,null,4]";
        //root = "[1,2,3,4,5,6,7]";
        int[] to_delete = new int[]{4,2};

        TreeNode treeRoot = stringToTreeNode(root);
        prettyPrintTree(treeRoot);
        List<TreeNode> results = new Solution().delNodes(treeRoot, to_delete);

        for (TreeNode var : results) {
            prettyPrintTree(var);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static class Solution {
        List<TreeNode> ans = new ArrayList<>();
        public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
            if (!isIncludeNode(to_delete, root.val)) {
                ans.add(root);
            } else {
                if (root.left != null && !isIncludeNode(to_delete, root.left.val)) {
                    ans.add(root.left);
                }
                if (root.right != null && !isIncludeNode(to_delete, root.right.val)) {
                    ans.add(root.right);
                }
            }

            if (root.left != null) {
                helper(root.left, to_delete, root, true);
            }
            if (root.right != null) {
                helper(root.right, to_delete, root, false);
            }

            return ans;
        }

        public void helper(TreeNode root, int[] to_delete, TreeNode father, boolean left) {
            
            if (isIncludeNode(to_delete, root.val)) {
                if (left) {
                    father.left = null;
                } else {
                    father.right = null;
                }


                if (root.left != null && !isIncludeNode(to_delete, root.left.val)) {
                    ans.add(root.left);
                }
                if (root.right != null && !isIncludeNode(to_delete, root.right.val)) {
                    ans.add(root.right);
                }
            }
            if (root.left != null) {
                helper(root.left, to_delete, root, true);
            }
            if (root.right != null) {
                helper(root.right, to_delete, root, false);
            }
        }

        public boolean isIncludeNode(int[] to_delete, int i) {
            for (int d : to_delete) {
                if (i == d) {
                    return true;
                }
            }
            return false;
        }
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
