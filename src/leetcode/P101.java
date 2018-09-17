/*

����һ����������������Ƿ��Ǿ���ԳƵġ�

���磬������ [1,2,2,3,4,4,3] �ǶԳƵġ�

    1
   / \
  2   2
 / \ / \
3  4 4  3
����������� [1,2,2,null,3,null,3] ���Ǿ���ԳƵ�:

    1
   / \
  2   2
   \   \
   3    3
˵��:

�����������õݹ�͵������ַ������������⣬��ܼӷ֡�

�Ƚϸ������������������Ƿ�Գƣ��������������ֵ��ͬ����ô�ٷֱ������������ڵ�����������ҽڵ㣬���������ҽڵ������������ڵ����Ƚϼ���

*/
public class P101 {
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

        System.out.println(new Solution().isSymmetric(q));
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
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
    public boolean isSymmetric(TreeNode root) {
        //if (root == null) return true;
        // ���Ҷ���
        //if (root.left == null && root.right == null) return true;
        // ����ջ���ҿ�
        //if (root.left == null || root.right == null) return false;

        //TreeNode left = root.left;
        //TreeNode right = root.right;
        //if (left.val != right.val) return false; 
        // �ж��ӽڵ�Գ���

        // ǰ�ߵ��жϲ���Ҫ, �������100������
        return isSymmetricTree(root, root);
    }

    public boolean isSymmetricTree(TreeNode p, TreeNode q) { //���ѱȽϸ����ڵ�
        // ��֤�ڵ����
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;

        if (p.val == q.val) {
            // ��ֵ֤��ͬ: �Գ��Խ����ҽڵ�ıȽϷ�����
            return isSymmetricTree(p.left, q.right) && isSymmetricTree(p.right, q.left);
        }
        return false;
    }
}