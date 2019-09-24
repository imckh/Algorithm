/*
2. 分式化简

有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？



连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。

 

输入的cont代表连分数的系数（cont[0]代表上图的a0，以此类推）。返回一个长度为2的数组[n, m]，使得连分数的值等于n / m，且n, m最大公约数为1。

 

示例 1：

输入：cont = [3, 2, 0, 2]
输出：[13, 4]
解释：原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。
示例 2：

输入：cont = [0, 0, 3]
输出：[3, 1]
解释：如果答案是整数，令分母为1即可。
限制：

cont[i] >= 0
1 <= cont的长度 <= 10
cont最后一个元素不等于0
答案的n, m的取值都能被32位int整型存下（即不超过2 ^ 31 - 1）。
*/

class Solution {
    public int[] fraction(int[] cont) {
        long[] arr = new long[2];
        int[] result = new int[2];

        if (cont.length == 1) {
            result[0] = cont[0];
            result[1] = 1;
            return result;
        }

        arr[0] = cont[cont.length-1];
        arr[1] = 1;

        // if (cont.length < 2) return arr;

        for (int i = cont.length-2; i >= 0; i--) {
            long t = arr[1];
            arr[1] = arr[0];
            arr[0] = t;

            // System.out.println(Arrays.toString(arr));

            long a = cont[i];
            // System.out.println("a = " + a);
            arr[0] = arr[0] + (arr[1] * a);

            // System.out.println("arr = " + arr[0] + "/" + arr[1]);
        }

        // reducing(arr);

        result[0] = (int)arr[0];
        result[1] = (int)arr[1];

        return result;
    }
    // 不需要求最大公约数
    public void reducing(long[] arr) {
        long denominator = arr[0];
        long numerator = arr[1];
        // 即求出最大公因数
        long smaller = numerator > denominator ? numerator : denominator;
        long maxCommonFactor = 1;
        for (int i = 1; i <= smaller; i++) {
            if (numerator % i == 0 && denominator % i == 0) {
                maxCommonFactor = i;
            }
        }
        arr[0] = denominator / maxCommonFactor;
        arr[1] = numerator / maxCommonFactor;
        // System.out.println("result:" + denominator / maxCommonFactor + "/" + numerator / maxCommonFactor);
    }
}