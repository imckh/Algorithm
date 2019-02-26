/*
297. 二叉树的序列化与反序列化

https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/52/design/109/

序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。

请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

示例: 

你可以将以下二叉树：

    1
   / \
  2   3
     / \
    4   5

序列化为 "[1,2,3,null,null,4,5]"
提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。
*/
import java.util.*;
public class P297 {
    public static void main(String[] args) {
        Codec c = new Codec();
        TreeNode root = c.deserialize("[1,2,3,4,5,6,7,8]");
        prettyPrintTree(root);
        System.out.println(c.serialize(root));
        System.out.println(treeNodeToString(root));
    }
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
     * 层序遍历
     * queue
     */
    public static class Codec {

        public String serialize(TreeNode root) {
            if (root == null) {
                return "[]";
            }

            StringBuilder output = new StringBuilder();
            // 使用队列进行层序遍历
            Queue<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            while (!nodeQueue.isEmpty()) {
                TreeNode node = nodeQueue.remove();

                if (node == null) {
                    output.append("null,");
                    continue;
                }

                output.append(node.val).append(",");
                nodeQueue.add(node.left);
                nodeQueue.add(node.right);
            }

            // 去除最后一个逗号
            return "[" + output.substring(0, output.length() - 1) + "]";
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String input) {
            input = input.trim();
            input = input.substring(1, input.length() - 1);
            if (input.length() == 0) {
                return null;
            }

            String[] parts = input.split(",");
            String item = parts[0];
            TreeNode root = new TreeNode(Integer.parseInt(item));
            // 使用队列进行层序遍历
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
    }

    /**
     * 递归
     */
    public static class Codec_r {
        public List<Integer> serialize(TreeNode root) {
            List<Integer> res = new ArrayList<>();
            dfs(res, root);
            return res;
        }
    
        private void dfs(List<Integer> res, TreeNode root) {
            if (root == null) {
                res.add(null);
            } else {
                res.add(root.val);
                dfs(res, root.left);
                dfs(res, root.right);
            }
        }
    
        // Decodes your encoded data to tree.
        public TreeNode deserialize(List<Integer> data) {
            int index[] = {0};
            TreeNode root = build(index, data);
            return root;
        }
    
        private TreeNode build(int[] index, List<Integer> data) {
            Integer val = data.get(index[0]);
            index[0] = index[0] + 1;
            if (val == null) {
                return null;
            } else {
                TreeNode node = new TreeNode(val);
                node.left = build(index, data);
                node.right = build(index, data);
                return node;
            }
        }
       
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