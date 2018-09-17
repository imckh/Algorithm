/*

������������������дһ�����������������Ƿ���ͬ��

����������ڽṹ����ͬ�����ҽڵ������ͬ��ֵ������Ϊ��������ͬ�ġ�

ʾ�� 1:

����:       1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

���: true
ʾ�� 2:

����:      1          1
          /           \
         2             2

        [1,2],     [1,null,2]

���: false
ʾ�� 3:

����:       1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

���: false
 */
public class P100 {
    public static void main(String[] args) {
        System.out.println("����");
        TreeNode q1 = new TreeNode(1);
        TreeNode q2 = new TreeNode(2);
        TreeNode q3 = new TreeNode(1);
        q1.left = q2;
        q1.right = q3;
        TreeNode p1 = new TreeNode(1);
        TreeNode p2 = new TreeNode(1);
        TreeNode p3 = new TreeNode(3);
        p1.left = p2;
        p1.right = p3;

        System.out.println(new Solution().isSameTree(p1, q1));
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
    public boolean isSameTree(TreeNode p, TreeNode q) { //���ѱȽϸ����ڵ�
        // ��֤�ڵ����
        //if ((p == null && q != null) || (p != null && q == null)) return false;
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        // �ݹ�ֻ��Ҫ��ע��ǰ�Ľڵ㼴��
        // if ((p.left == null && q.left != null) || (p.left != null && q.left == null)) return false;
        // if ((p.right == null && q.right != null) || (p.right != null && q.right == null)) return false;

        if (p.val == q.val) {
            // ��ֵ֤��ͬ
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return false;
    }
}