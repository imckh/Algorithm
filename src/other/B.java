import java.util.Arrays;
// 题意见 B.png
public class B {
    public static void main(String[] args) {
        int[] arr = {3, 4, 1, 1, 1, 2, 2};
        System.out.println("       " + Arrays.toString(arr));
        System.out.println(f(arr, arr.length));
    }

    public static int f(int[] arr, int len) {
        for (int i = len - 1; i > 0; i--) {
            int x = minPairIndex(arr, i);
            if (x == -1) return -1;
            arr[x+1] += arr[x]; // 两堆的和
            arr = delete(x, arr); // 删除数组中的一个元素
            
            System.out.println("x = " + x + "  " + Arrays.toString(arr));
        }

        return arr[0];
    }

    public static int minPairIndex(int[] arr, int len) {
        int sum = Integer.MAX_VALUE;
        int index = -1;

        // 求符合条件的数组中的和最小的相邻子序列
        // 没有符合的返回-1
        for (int i = 0; i < len; i++) {
            if (Math.min(arr[i], arr[i+1]) >= 10 && 
                Math.max(arr[i], arr[i+1]) % Math.min(arr[i], arr[i+1]) == 0) {
                continue;
            }
            if (arr[i] + arr[i + 1] < sum) {
                sum = arr[i] + arr[i + 1];
                index = i;
            }
        }
        return index;
    }

    public static int[] delete(int index, int array[]) {
        int[] arrNew = new int[array.length - 1];
        for (int i = index; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        System.arraycopy(array, 0, arrNew, 0, arrNew.length);
        return arrNew;
    }
}
