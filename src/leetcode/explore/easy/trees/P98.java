/*
98. 验证二叉搜索树

https://leetcode-cn.com/problems/validate-binary-search-tree/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/7/trees/48/

给定一个二叉树，判断其是否是一个有效的二叉搜索树。

假设一个二叉搜索树具有如下特征：

节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
示例 1:

输入:
    2
   / \
  1   3
输出: true
示例 2:

输入:
    5
   / \
  1   4
     / \
    3   6
输出: false
解释: 输入为: [5,1,4,null,null,3,6]。
     根节点的值为 5 ，但是其右子节点值为 4 。
*/
import java.util.*;
public class P98 {
    public static void main(String[] args) {
        //String str = "[5,1,4,null,null,3,6]";
        String str = "[5,3,9,1,4,7,10]";
        TreeNode root = stringToTreeNode(str);
        prettyPrintTree(root);
        System.out.println(new Solution().isValidBST(root));
        System.out.println(new Solution2().isValidBST(root));
        System.out.println(new Solution3().isValidBST(root));
    }

    static class Solution {
        private int lastVal = Integer.MIN_VALUE;
        private boolean firstNode = true;
        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            if (!isValidBST(root.left)) { 
                // 左子树不是二叉搜索树
                return false;
            }
            
            //System.out.println(root.val + " " + lastVal);
            if (!firstNode && root.val <= lastVal) { // 每轮只要和前一个值比较即可, 相当于中序遍历
                return false;
            }
            firstNode = false; // 是不是树根
            lastVal = root.val; // 上一个节点
            if (!isValidBST(root.right)) {
                // 右子树不是二叉搜索树
                return false;
            }
            return true;
        }
    }

    public static class Solution2 {
        /**
         * @param root: The root of binary tree.
         * @return: True if the binary tree is BST, or false
         */
        public boolean isValidBST(TreeNode root) {
            List<Integer> list = new ArrayList<Integer>();
    
            //遍历
            inOrder(root, list);
    
            //校验
            boolean flag = true;
            int cur = Integer.MIN_VALUE;
            if(list.size() > 0){
                cur = list.get(0);
                list.remove(0);
            }
            for(int i : list){
                if(i <= cur){
                    flag = false;
                    break;
                }else{
                    cur = i;
                }
            }
            return flag;
        }
        
        /**
         * 二叉查找树中序遍历可以得到一个递增的序列
         * 
         **/
        private void inOrder(TreeNode node, List<Integer> list){
            if(node == null){
                return;
            }
            
            inOrder(node.left, list);
            list.add(node.val);
            inOrder(node.right, list);
            return;
        }
    }

    public static class Solution3 {
        public boolean isValidBST(TreeNode root) {        //1、使用遍历二叉树的方式  2、使用递归去做一个一个检查   
            if(root == null) return true;
            return isValidBST(root, Long.MAX_VALUE, Long.MIN_VALUE);
        }
        private boolean isValidBST(TreeNode root, long max, long min){
            if(root == null) return true;
            if(root.val >= max || root.val <= min) return false;
            return isValidBST(root.left, root.val, min) && isValidBST(root.right, max, root.val);
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