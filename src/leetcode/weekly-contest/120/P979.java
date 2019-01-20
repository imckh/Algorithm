/*
979. 在二叉树中分配硬币  

https://leetcode-cn.com/contest/weekly-contest-120/problems/distribute-coins-in-binary-tree/

给定一个有 N 个结点的二叉树的根结点 root，树中的每个结点上都对应有 node.val 枚硬币，并且总共有 N 枚硬币。

在一次移动中，我们可以选择两个相邻的结点，然后将一枚硬币从其中一个结点移动到另一个结点。(移动可以是从父结点到子结点，或者从子结点移动到父结点。)。

返回使每个结点上只有一枚硬币所需的移动次数。

 

示例 1：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree1.png

输入：[3,0,0]
输出：2
解释：从树的根结点开始，我们将一枚硬币移到它的左子结点上，一枚硬币移到它的右子结点上。

示例 2：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree2.png

输入：[0,3,0]
输出：3
解释：从根结点的左子结点开始，我们将两枚硬币移到根结点上 [移动两次]。然后，我们把一枚硬币从根结点移到右子结点上。

示例 3：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree3.png

输入：[1,0,2]
输出：2


示例 4：

https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2019/01/19/tree4.png

输入：[1,0,0,null,3]
输出：4
 

提示：

1<= N <= 100
0 <= node.val <= N
*/
import java.util.*;
public class P979 {
    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[3,0,0]");
        prettyPrintTree(root);
    }

    static class Solution {
        /*
        定义root, left, right分别代表 根 左 右
        左右翻转都一样
        1. 只有root.val = 1 移动0次
        2. 当只有【根左】或【根右】时有两种情况
            1. root.val = 2, left||right.val == 0 这种情况需要移动[root.val-1]次
            2. root.val = 0, left||right.val == 2 这种情况需要移动[left||right.val-1]次
            3. root.val = 1, left||right.val == 1 不需要移动
        3. 当三个都存在时
            1. root.val = 3, left.val = 0, right.val = 0 移动 2 次
            2. root.val = 2, left.val = 1, right.val = 0 移动 1 次
            3. root.val = 1, left.val = 2, right.val = 0 移动 2 次
            4. root.val = 0, left.val = 3, right.val = 0 移动 3 次
            5. root.val = 1, left.val = 1, right.val = 1 移动 0 次
        */
        public int distributeCoins(TreeNode root) {
            if (root == null) return 0;
            int result = 0;

            return result;
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