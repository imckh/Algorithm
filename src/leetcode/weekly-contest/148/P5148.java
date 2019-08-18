import java.util.*;

public class P5148 {
    public static void main(String[] args) {
        String root = "[1,2,null,4,3]";
        root = "[1,2,3,null,null,null,4]";
         root = "[1,2,3,4,5,6,7,8,9,10,11]";

        TreeNode treeRoot = stringToTreeNode(root);
        prettyPrintTree(treeRoot);
        boolean results = new Solution().btreeGameWinningMove(treeRoot, 11,3);

        System.out.println(results);
    }

    static class Solution {
        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            if (n == 1) return false;
            
            TreeNode xNode = find(root, x);
            int xPsize = sizeParent(root, xNode, 0);
            int xSize = size(xNode);

            return n / 2 > (xSize + xPsize);
        }

        public TreeNode find(TreeNode root, int x) {
            if (root == null) return null;
            if (root.val == x) return root;

            TreeNode l = find(root.left, x);
            TreeNode r = find(root.right, x);

            return l == null ? r : l;
        }

        public int size(TreeNode node) {
            if (node == null) return 0;

            return size(node.left) + size(node.right) + 1;
        }
        public int sizeParent(TreeNode root, TreeNode node, int size) {
            if (root == null) return 0;
            if (root == node) return size;
            int sl = sizeParent(root.left, node, size + 1);
            int sr = sizeParent(root.right, node, size + 1);

            return sl == 0 ? sr : sl;
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