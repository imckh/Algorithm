import java.util.*;

/*
969. 煎饼排序
https://leetcode-cn.com/contest/weekly-contest-118/problems/pancake-sorting/
题目难度 Medium
给定数组 A，我们可以对其进行煎饼翻转：我们选择一些正整数 k <= A.length，然后反转 A 的前 k 个元素的顺序。我们要执行零次或多次煎饼翻转（按顺序一次接一次地进行）以完成对数组 A 的排序。

返回能使 A 排序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * A.length 范围内的有效答案都将被判断为正确。

 

示例 1：

输入：[3,2,4,1]
输出：[4,2,4,3]
解释：
我们执行 4 次煎饼翻转，k 值分别为 4，2，4，和 3。
初始状态 A = [3, 2, 4, 1]
第一次翻转后 (k=4): A = [1, 4, 2, 3]
第二次翻转后 (k=2): A = [4, 1, 2, 3]
第三次翻转后 (k=4): A = [3, 2, 1, 4]
第四次翻转后 (k=3): A = [1, 2, 3, 4]，此时已完成排序。 
示例 2：

输入：[1,2,3]
输出：[]
解释：
输入已经排序，因此不需要翻转任何内容。
请注意，其他可能的答案，如[3，3]，也将被接受。
 

提示：

1 <= A.length <= 100
A[i] 是 [1, 2, ..., A.length] 的排列
*/
import java.util.*;

public class P969 {
    public static void main(String[] args) {
        int[] arr = { 3, 2, 4, 1 };

        System.out.println(new Solution().pancakeSort(arr));
    }

    static class Solution {
        List<Integer> list = new ArrayList<>();

        public List<Integer> pancakeSort(int[] arr) {
            PancakeSort pancakes = new PancakeSort(arr);

            return pancakes.step;
        }

        public class PancakeSort {
            int[] heap;
            List<Integer> step = new ArrayList<>();
            public String toString() {
                String info = "";
                for (int x : heap)
                    info += x + " ";
                return info;
            }
        
            // 将数组从0到n翻转
            public void flip(int n) {
                step.add(n + 1);
                for (int i = 0; i < (n + 1) / 2; ++i) {
                    int tmp = heap[i];
                    heap[i] = heap[n - i];
                    heap[n - i] = tmp;
                }
                System.out.println("flip(0.." + n + "): " + toString());
            }
        
            public int[] minmax(int n) {
                int xm, xM;
                xm = xM = heap[0];
                int posm = 0, posM = 0;
        
                for (int i = 1; i < n; ++i) {
                    if (heap[i] < xm) {
                        xm = heap[i];
                        posm = i;
                    } else if (heap[i] > xM) {
                        xM = heap[i];
                        posM = i;
                    }
                }
                return new int[] { posm, posM };
            }
        
            public void sort(int n, int dir) {
                if (n == 0)
                    return;
        
                int[] mM = minmax(n); // 找到数组的最大最小位置
                int bestXPos = mM[dir]; 
                int altXPos = mM[1 - dir];
                boolean flipped = false;
                System.out.println(Arrays.toString(mM) + " dir=" + dir + " bestXPos=" + bestXPos + " altXPos=" + altXPos + " n=" + n + " flipped=" + flipped);
        
                if (bestXPos == n - 1) {
                    // 只使用排序数组
                    // n前移, 直到未排序数组
                    // n是未排序数组的最后一个
                    --n;
                } else if (bestXPos == 0) {
                    // 最大的在第一个
                    flip(n - 1);
                    --n;
                } else if (altXPos == n - 1) {
                    dir = 1 - dir;
                    --n;
                    flipped = true;
                } else {
                    flip(bestXPos);
                }
                sort(n, dir);
        
                if (flipped) {
                    flip(n);
                }
            }
        
            PancakeSort(int[] numbers) {
                heap = numbers;
                sort(numbers.length, 1);
            }
        }
    }
}