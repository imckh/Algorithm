/*
5174. 健身计划评估


你的好友是一位健身爱好者。前段日子，他给自己制定了一份健身计划。现在想请你帮他评估一下这份计划是否合理。

他会有一份计划消耗的卡路里表，其中 calories[i] 给出了你的这位好友在第 i 天需要消耗的卡路里总量。

一份计划的周期通常是 k 天，你需要计算他在这 k 天内消耗的总卡路里 T：

如果 T < lower，那么这份计划相对糟糕，并失去 1 分； 
如果 T > upper，那么这份计划相对优秀，并获得 1 分；
否则，这份计划普普通通，分值不做变动。
请返回统计完所有 calories.length 天后得到的总分作为评估结果。

注意：总分可能是负数。

 

示例 1：

输入：calories = [1,2,3,4,5], k = 1, lower = 3, upper = 3
输出：0
解释：calories[0], calories[1] < lower 而 calories[3], calories[4] > upper, 总分 = 0.
示例 2：

输入：calories = [3,2], k = 2, lower = 0, upper = 1
输出：1
解释：calories[0] + calories[1] > upper, 总分 = 1.
示例 3：

输入：calories = [6,5,0,0], k = 2, lower = 1, upper = 5
输出：0
解释：calories[0] + calories[1] > upper, calories[2] + calories[3] < lower, 总分 = 0.
 

提示：

1 <= k <= calories.length <= 10^5
0 <= calories[i] <= 20000
0 <= lower <= upper
*/
public class P5174 {
    public static void main(String[] args) {
        // int[] calories = {1,2,3,4,5};
        // int k = 1, lower = 3, upper = 3;
        int[] calories = {6,13,8,7,10,1,12,11};
        int k = 6, lower = 5, upper = 37;
        System.out.println(new Solution().dietPlanPerformance(calories, k, lower, upper));
    }

    static class Solution {
        public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
            int result = 0;

            int i=0, j=0;
            for (i = 0; i + k <= calories.length ; i++) {
                int kSum = 0;
                for (j = i; j < i+k && j < calories.length; j++) {
                    kSum += calories[j];
                }
                if (kSum < lower) {
                    result--;
                } else if (kSum > upper) {
                    result++;
                }
                //System.out.println(kSum + " " + result);
            }
            //System.out.println(j);
            if (j < calories.length) {
                int kSum = 0;
                for (; j < calories.length; j++) {
                    kSum += calories[j];
                }
                if (kSum < lower) {
                    result--;
                } else if (kSum > upper) {
                    result++;
                }
            }

            return result;
        }
    }
}