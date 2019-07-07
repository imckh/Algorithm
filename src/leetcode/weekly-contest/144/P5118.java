/*
5118. 航班预订统计
这里有 n 个航班，它们分别从 1 到 n 进行编号。

我们这儿有一份航班预订表，表中第 i 条预订记录 bookings[i] = [i, j, k] 意味着我们在从 i 到 j 的每个航班上预订了 k 个座位。

请你返回一个长度为 n 的数组 answer，按航班编号顺序返回每个航班上预订的座位数。

 

示例：

输入：bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5
输出：[10,55,45,25,25]
 

提示：

1 <= bookings.length <= 20000
1 <= bookings[i][0] <= bookings[i][1] <= n <= 20000
1 <= bookings[i][2] <= 10000
*/

import java.util.Arrays;

public class P5118 {
    public static void main(String[] args) {
        int[][] bookings = new int[][]{
            {1,2,10},
            {2,3,20},
            {2,5,25}
        };
        int n = 5;
        System.out.println(
            Arrays.toString(
                new Solution().corpFlightBookings(bookings, n))
            );
    }
}
class Solution {
    public int[] corpFlightBookings_timeLimit(int[][] bookings, int n) {
        int[] ans = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            int from = bookings[i][0] - 1;
            int to = bookings[i][1] - 1;
            int seats = bookings[i][2];

            for (int j = from; j <= to; j++) {
                ans[j] += seats;
            }
        }

        return ans;
    }

    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];

        for (int i = 0; i < bookings.length; i++) {
            int from = bookings[i][0] - 1;
            int to = bookings[i][1] - 1;
            int seats = bookings[i][2];
            if (seats > 0) {
                for (int j = from; j <= to; j++) {
                    ans[j] += seats;
                }
            }
        }

        return ans;
    }
}