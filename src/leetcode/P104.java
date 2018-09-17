/*
����һ�����������ҳ��������ȡ�

�����������Ϊ���ڵ㵽��ԶҶ�ӽڵ���·���ϵĽڵ�����

˵��: Ҷ�ӽڵ���ָû���ӽڵ�Ľڵ㡣

ʾ����
���������� [3,9,20,null,null,15,7]��

    3
   / \
  9  20
    /  \
   15   7
�������������� 3
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

        System.out.println(new Solution().maxDepth(q));
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
    public int maxDepth(TreeNode root) {
        // ÿ����һ�νڵ��һ���ɣ�Ȼ��ȡ����������������
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}