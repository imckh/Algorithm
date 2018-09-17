import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
����һ����������������ڵ�ֵ�Ե����ϵĲ�α����� ��������Ҷ�ӽڵ����ڲ㵽���ڵ����ڵĲ㣬���������ұ�����

���磺
���������� [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
�������Ե����ϵĲ�α���Ϊ��

[
  [15,7],
  [9,20],
  [3]
]
*/
public class P104 {
    public static void main(String[] args) {
        System.out.println("����");
        TreeNode q = new TreeNode(0);
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(3);
        q.left = q1;
        q1.left = q2;
        q1.right = q3;
        TreeNode q4 = new TreeNode(1);
        TreeNode q5 = new TreeNode(2);
        TreeNode q6 = new TreeNode(3);
        q.right = q4;
        q4.left = q6;
        q4.right = q5;

        System.out.println(Solution().maxDepth(q));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // BFS
        if (root == null) return Collections.emptyList();
        List<List<Integer>> list = new LinkedList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        
        q.add(root);
        while(!q.isEmpty()) {
            List<Integer> sub = new LinkedList();
            int levelSize = q.size();
            
            for (int i = 0; i < levelSize; i++) {
                TreeNode t = q.getFirst();
                sub.add(t.val);
                q.removeFirst();
                if (t.left != null) q.add(t.left);
                if (t.right != null) q.add(t.right);
            }
            // ��Ϊ�Ǵ������ϣ����Բ����ʱ��ÿ�β嵽����ͷ����
            list.add(0, sub);
        }

        return list;
    }

    // ��һ��˼·�������ѣ����ѵ�ʱ��ͬʱ��¼��ȣ�Ȼ������Ӧ�Ĳ����ڵ�ֵ����
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        List<List<Integer>> list = new LinkedList<>();
        helper(list, root, 0);
        return list;
    }

    private void helper(List<List<Integer>> list, TreeNode root, int level) {
        if (root == null) return;
        if (level >= list.size()) {
            list.add(0, new LinkedList<>());
        }
        helper(list, root.left, level + 1);
        helper(list, root.right, level + 1);
        list.get(list.size() - level - 1).add(root.val);
    }
}