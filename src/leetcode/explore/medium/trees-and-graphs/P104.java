/*
103. 二叉树的锯齿形层次遍历

https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/32/trees-and-graphs/86/

给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
返回锯齿形层次遍历如下：

[
  [3],
  [20,9],
  [15,7]
]
*/
import java.util.*;
public class P104{
    public static void main(String[] args) {
        TreeNode root = stringToTreeNode("[1,2,3,4,5,6,7,8,9,null,11,12,null,13,14,15]");
        prettyPrintTree(root);

        System.out.println(new Solution().zigzagLevelOrder(root));
        System.out.println(new Solution2().zigzagLevelOrder(root));
    }

    /**
     * 迭代实现
     * 
     * 思想:
     * 
     * 比如一个树是[1,2,3,4,5,6,7,8,9,null,11,12,null,13,14,15] 5层的一个二叉树
     * 
     * 初始状态
     * queue: [1]
     * 
     * 上层结果: [1] 
     * 原始的queue: [3, 2][1]
     * queue: [3, 2] 从尾移出, 向头添加
     * 
     * 上层结果: [3, 2] 
     * 原始的queue: [3, 2][7, 6, 5, 4]
     * queue: [7, 6, 5, 4] 从头移出, 向尾添加
     * 
     * 上层结果: [4, 5, 6, 7] 
     * 原始的queue: [14, 13, 12, 11, 9, 8][7, 6, 5, 4]
     * queue: [14, 13, 12, 11, 9, 8] 从尾移出, 向头添加
     * 
     * 上层结果: [14, 13, 12, 11, 9, 8]
     * 原始的queue: [14, 13, 12, 11, 9, 8][15]
     * queue: [15] 从头移出, 向尾添加
     * 
     * 上层结果: [15]
     * queue: [] queue为空, 终止操作
     */
    static class Solution {
        
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if (root == null) {
                return result;
            }
            // 说是叫queue其实用的是双向链表
            // 一开始想用队列, 发现做不出来 😄
            LinkedList<TreeNode> queue = new LinkedList<>();
            boolean fromLeft2Right = true; // 从左到右, 一开始是从右到左

            queue.offer(root); // 将根节点插入

            int levelNumber = -1; // 当前层的元素个数
            int nextlevelNumber = 1; // 下一层的元素个数
            while (!queue.isEmpty()) {
                levelNumber = nextlevelNumber;
                nextlevelNumber = 0;
                List<Integer> levelVals = new ArrayList<>(levelNumber);
                for (int i = 0; i < levelNumber; i++) {
                    if (fromLeft2Right) {
                        TreeNode cur = queue.removeLast();
                        levelVals.add(cur.val); // 移出链表的最后一个元素
                        // 添加的时候, 要将树的子节点按从左到右的顺序添加至链表尾
                        if (cur.left != null) {
                            queue.addFirst(cur.left);
                            nextlevelNumber++; // 若不为空, 下一层元素个数加1
                        }
                        if (cur.right != null) {
                            queue.addFirst(cur.right);
                            nextlevelNumber++;
                        }
                    } else {
                        TreeNode cur = queue.removeFirst();
                        levelVals.add(cur.val);// 移出链表的第一个元素
                        // 添加的时候, 要将树的子节点按从右到左的顺序添加至链表头
                        if (cur.right != null) {
                            queue.addLast(cur.right);
                            nextlevelNumber++;
                        }
                        if (cur.left != null) {
                            queue.addLast(cur.left);
                            nextlevelNumber++;
                        }
                    }
                }

                System.out.println("queue: " + queue);
                System.out.println(levelNumber + " " + levelVals);
                
                result.add(levelVals);
                fromLeft2Right = !fromLeft2Right; // 变换左右
            }

            return result;
        }
    }

    /**
     * 递归实现
     * 递归是树的层序遍历的升级版, 
     * 就是根据level找到结果list中的index将值插入
     * 
     * 这里注意是插入第一个还是最后一个
     */
    static class Solution2 {
        public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
            List<List<Integer>> res = new ArrayList();
            travel(res, 0, root);
            return res;
        }
        private void travel(List<List<Integer>> res, int level, TreeNode cur) {
            if (cur == null) return;
            if (res.size() <= level) {
                res.add(new ArrayList<Integer>());
            }
            if (level % 2 == 0) {
                res.get(level).add(cur.val);
            }   else {
                res.get(level).add(0, cur.val);
            }
            travel(res, level + 1, cur.left);
            travel(res, level + 1, cur.right);
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