public class IsBanlance {
    public static void main(String[] args) {
        
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 判断二叉树是否是平衡二叉树
    public boolean isBanlance(TreeNode root) {
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 1, res);
        return res[0]
    }

    public int getHeight(TreeNode root, int level, boolean[] res) {
        if (root == null) {
            return level;
        }
        int lH = getHeight(root.left, level + 1, res);
        if (!res[0]) {
            return level;
        }

        int rH = getHeight(root.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }
}