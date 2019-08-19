public class PostArrayTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 判断arr是否可能是搜索二叉树后序遍历的结果
    public boolean isPostArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        return isPost(arr, 0, arr.length - 1);
    }

    // 先左，后右，最后跟
    // 头结点一定是最后一个元素，比后序数组最后一个元素值小的数组在左边，比后序数组最后一个元素值大的数组在右边
    // 比如arr[2,1,3,6,5,7,4] 最后一个元素是4，比4小的部分是 2,1,3 ，比4大的部分是 6,5,7
    // 如果不满足这种情况，说明数组一定不是后序遍历的搜索二叉树
    private boolean isPost(int[] arr, int start, int end) {
        if (start == end) {
            return true;
        }

        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }
        if (less == - 1 || more == end) {
            return isPost(arr, start, end - 1);
        }
        if (less != more - 1) {
            return false;
        }

        return isPost(arr, start, less) && isPost(arr, more, end - 1);
    }

    // 通过数组重构二叉树
    public TreeNode posArrayToBST(int[] posArr) {
        if (posArr == null) {
            return null;
        }

        return posToBST(posArr, 0, posArr.length - 1);
    }

    private TreeNode posToBST(int[] posArr, int start, int end) {
        if (start > end) {
            return null;
        }

        TreeNode head = new TreeNode(posArr[end]);
        int less = -1;
        int more = end;
        for (int i = start; i < end; i++) {
            if (posArr[end] > posArr[i]) {
                less = i;
            } else {
                more = more == end ? i : more;
            }
        }

        head.left = posToBST(posArr, start, less);
        head.right = posToBST(posArr, more, end - 1);
        return head;
    }
}