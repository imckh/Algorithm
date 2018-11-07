package other;

/**
 * @author CKH
 * @date 2018/10/30 21:28
 */
public class Climb {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 10, 1, 7, 8, 1, 1, 4, 5, 6};
        outputMou(5, arr);
        int height = 5;
        int from = height;
        int to = 1;
        int index = 0; // 左下角
        int r = minTime(height, arr, from, to, index);
    }

    static int min = Integer.MAX_VALUE;
    static int result = 0;
    /**
     * 从from层到to层的最短路径
     * @param height 高度
     * @param times 时间
     * @param from 从第几层
     * @param to 到第几层
     * @param index 从from的第几个位置开始
     * @return 从from层的index开始出发, 到to层的最短路径所到达的to层的index
     */
    public static int minTime(int height, int[] times, int from, int to, int index) {
        if (最短路径) {
            int thisindex = 到达的index;
            result += times[thisindex];

        }

        return minTime(height - 1, times, from - 1, to, thisindex);;
    }

    public static void outputMou(int height, int[] times) {
        for (int i = 1; i <= height; i++) {
            for (int j = (1 + i) * i / 2 - i, k = 0; k < i; k++, j++) {
                System.out.print(times[j] + " ");
            }
            System.out.println();
        }
    }
}
1
2 3
8 9 1
1 2 3 5