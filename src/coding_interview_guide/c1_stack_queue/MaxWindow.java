package coding_interview_guide.c1_stack_queue;

import java.util.LinkedList;

/**
 * @author CKH
 * @date 2018/2/26 17:20
 */
public class MaxWindow {
    /**
     * 生成窗口最大值数组
     *
     * [4, 3, 5, 4, 3, 3, 6, 7]
     * [4, 3, 5],4, 3, 3, 6, 7  窗口最大值为5
     * ...
     *  4, 3, 5, 4,[3, 3, 6], 7 窗口最大值为6
     *  4, 3, 5, 4, 3,[3, 6, 7] 窗口最大值为7
     * 数组长n, 窗口大小w
     * 共产生n - w + 1个窗口最大值
     * {5, 5, 5, 4, 6, 7}
     *
     * @param arr 数组
     * @param w 窗口大小
     * @return 返回每个窗口的最大值 大小应该为n-w+1
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }

        // 双向队列 存放数组下标
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;

        for (int i = 0; i < arr.length; i++) {
            // qmax空直接放入当前遍历到的数组下标
            // 以qmax队尾为下标的数组若小于当前遍历到的数组元素, 将qmax队尾弹出
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.poll();
            }
            qmax.addLast(i);
            // 如果qmax队头等于i - w, 说明当前qmax队头下标已经过期, 弹出队头
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            // 把每个窗口的最大值保存下来
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res;
    }
}