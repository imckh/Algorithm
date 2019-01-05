/*
102. 二叉树的层次遍历
https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/50/

给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
例如:
给定二叉树: [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回其层次遍历结果：

[
  [3],
  [9,20],
  [15,7]
]
*/
import java.util.*;
public class P102 {
    public static void main(String[] args) {
        String str = "[3,9,20,null,null,15,7]";
        TreeNode tree = stringToTreeNode(str);
        prettyPrintTree(tree);
        System.out.println(new Solution().levelOrder(tree));
        System.out.println(new Solution2().levelOrder(tree));
    }

    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) return Collections.emptyList();
            List<List<Integer>> list = new LinkedList<>();
            LinkedList<TreeNode> q = new LinkedList<>();

            // BFS 使用队列宽度优先
            q.offer(root);
            while (!q.isEmpty()) {
                int levelSize = q.size(); // 当前层大小
                List<Integer> sub = new LinkedList<>();

                for (int i = 0; i < levelSize; i++) {
                    TreeNode t = q.poll();
                    sub.add(t.val);
                    if (t.left != null) q.add(t.left);
                    if (t.right != null) q.add(t.right);
                }
                // 如果返回想自底向上的list, 使用list.add(0, sub), 这就是P107
                list.add(sub);
            }

            return list;
        }
    }

    static class Solution2 {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> list = new ArrayList<List<Integer>>();
            function(root, list, 0);
            return list;
        }
        // 递归方法
        public void function(TreeNode node, List<List<Integer>> list, int level) {
            if (node == null) {
                return;
            }
            if (list.size() <= level) {
                list.add(new ArrayList<Integer>());
            }
            list.get(level).add(node.val);
            function(node.left, list, level + 1);
            function(node.right, list, level + 1);
        }
    }
    
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
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