import java.util.*;

/*
4. 寻找两个有序数组的中位数

https://leetcode-cn.com/problems/median-of-two-sorted-arrays/

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0
示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3)/2 = 2.5
*/

public class P4 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3, 4};
        System.out.println(new Solution().findMedianSortedArrays_ONM(nums1, nums2));
        System.out.println(new Solution().findMedianSortedArrays_Interpolation(nums1, nums2));
    }
    /**
     * 中位数: https://zh.wikipedia.org/wiki/%E4%B8%AD%E4%BD%8D%E6%95%B8
     * 如果数据的个数是奇数，则中间那个数据就是这群数据的中位数；
     * 如果数据的个数是偶数，则中间那2个数据的算术平均值就是这群数据的中位数。
     */
    static class Solution {
        // 两个有序数组合并后的中位数
        public double findMedianSortedArrays_ONM(int[] nums1, int[] nums2) {
            // 时间复杂度 O(M+N)
            int length = nums1.length + nums2.length;
            int[] sumNums = new int[length];

            int i = 0, j = 0, k = 0; // 这里的sumNums的长度可以设置为length的一半
            for ( ; i < nums1.length && j < nums2.length; k++) {
                sumNums[k] = nums1[i] > nums2[j] ? nums2[j++] : nums1[i++];
            }
            if (i == nums1.length) {
                // nums1 全部复制到了sum中
                // 原数组, 原数组的开始位置, 目标数组, 目标数组的开始位置, 拷贝个数
                System.arraycopy(nums2, j, sumNums, k, nums2.length - j);
            } else {
                System.arraycopy(nums1, i, sumNums, k, nums1.length - i);
            }

            //System.out.println(Arrays.toString(sumNums));

            if (length % 2 == 0) {
                return (double)(sumNums[length/2] + sumNums[length/2 - 1]) / 2;
            } else {
                return (double)sumNums[length/2];
            }
        }

        // https://blog.csdn.net/hk2291976/article/details/51107778
        // https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2471/Very-concise-O(log(min(MN)))-iterative-solution-with-detailed-explanation
        // O(log(M + N))
        /*
        把有序数组切一刀, 能够把有序数组分成左右两个部分, 切的那一刀就被称为切点(Cut), 切点的左右会有两个元素, 分别是左边最大值和右边最小值

        Ci 为第i个数组的切点
        Li为第i个数组切点后的左元素
        Ri为第i个数组切点后的右元素

        从双数组里取出第k个元素
            1. 首先Li<=Ri是肯定的(因为数组有序, 左边肯定小于右边)
            2. 如果我们让L1<=R2 && L2<=R1
                1. 那么左半边 全小于右半边，如果左边的元素个数相加刚好等于k，那么第k个元素就是Max(L1,L2)
            3. 如果 L1>R2, 说明数组1的左边元素太大(多), 我们把C1减小, 把C2增大
            4. L2>R1同理, 把C1增大, C2减小
        
        manacher算法中加入#来解决奇偶问题这里也是, 加完之后. 每个位置可以通过/2得到原来元素的位置

        分治比较L1,L2和R1,R2
            - L1>R2, 把C1减小, C2增大 —> C1向左二分 
            - L2>R1, 把C1增大, C2减小 —> C1向右二分
        如果有个数组完全小于或大于中值 ,
            - C1 = 0 —— 数组1整体都比中值大，L1 >R2，中值在2中 
            - C2 = 0 —— 数组1整体都比中值小，L1
        */
        public double findMedianSortedArrays_Interpolation(int[] nums1, int[] nums2) {
            int N1 = nums1.length;
            int N2 = nums2.length;

            if (N1 < N2) {// 保证数组1一定最短
                return findMedianSortedArrays_Interpolation(nums2, nums1);
            }

            int lo = 0, hi = N2 * 2; // 虚拟加了'#'所以数组1是2*n长度
            while (lo <= hi) {
                int mid2 = (lo + hi) / 2; // 找到数组2的切点
                int mid1 = N1 + N2 - mid2; // 计算出数组1的切点

                // 得到L1, R1, L2, R2
                double L1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1 - 1) / 2];
                double L2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2 - 1) / 2];
                double R1 = (mid1 == N1 * 2) ? Integer.MAX_VALUE : nums1[mid1 / 2];
                double R2 = (mid2 == N2 * 2) ? Integer.MAX_VALUE : nums2[mid2 / 2];
                System.out.printf("L1:%.2f, R1:%.2f, mid1:%d | L2:%.2f, R2:%.2f, mid2:%d\n", L1, R1, mid1, L2, R2, mid2);
                if (L1 > R2) lo = mid2 + 1;
                else if (L2 > R1) hi = mid2 - 1;
                else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
            }

            return -1;
        }

        // 为求两数组中第 k 大的元素
        public double findMedianSortedArrays_Recursively(int[] nums1, int[] nums2) {
            int len = nums1.length + nums2.length;
            if (len % 2 == 0) { //偶数个
                return (findKBig(nums1, 0, nums2, 0, len / 2) + findKBig(nums1, 0, nums2, 0, len / 2 + 1)) / 2.0;
            }
            return findKBig(nums1, 0, nums2, 0, (len + 1) / 2);
        }

        /**
         * 求两数组中第 k 大的元素
         * 
         * 思想是, 分别找到两个数组中的第(k/2-1)个进行比较
         * 并将较小的那个数组舍弃前k/2个, 同时将k缩小一半
         * @param nums1
         * @param m 当前的nums1中元素的开始位置
         * @param nums2
         * @param n 当前的nums2中元素的开始位置
         * @param k 第k大数
         * @return 两数组中第 k 大的元素
         */
        private int findKBig(int[] nums1, int m, int[] nums2, int n, int k) {
            // 当某个数组的数都被取完了，那么直接返回另一个数组的后 k 个元素即可
            if (m >= nums1.length) return nums2[n + k - 1];
            if (n >= nums2.length) return nums1[m + k - 1];
            if (k == 1) // 当 k = 1 时，也就是只需再找一个数即可，也就是取两者当前较小的那个即可
                return Math.min(nums1[m], nums2[n]);

            int p1 = m + k/2 - 1; // 本次递归中nums1的从m开始的第(k/2-1)大数
            int p2 = n + k/2 - 1; // 本次递归中nums2的从n开始的第(k/2-1)大数
            int mid1 = p1 < nums1.length ? nums1[p1] : Integer.MAX_VALUE;
            int mid2 = p2 < nums2.length ? nums2[p2] : Integer.MAX_VALUE;
            if (mid1 < mid2) {
                // 如果nums1[]到切点位置 < nums2[]到切点位置
                // 舍弃掉nums1到切点位置前边的元素, k减半
                return findKBig(nums1, m + k / 2, nums2, n, k - k / 2);
            }
            return findKBig(nums1, m, nums2, n + k / 2, k - k / 2);
        }

        // https://leetcode-cn.com/articles/median-of-two-sorted-arrays/
        // 官方题解
        public double findMedianSortedArrays_official(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) { // to ensure m<=n
                int[] temp = A;
                A = B;
                B = temp;
                int tmp = m;
                m = n;
                n = tmp;
            }
            int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = halfLen - i;
                if (i < iMax && B[j - 1] > A[i]) {
                    iMin = i + 1; // i is too small
                } else if (i > iMin && A[i - 1] > B[j]) {
                    iMax = i - 1; // i is too big
                } else { // i is perfect
                    int maxLeft = 0;
                    if (i == 0) {
                        maxLeft = B[j - 1];
                    } else if (j == 0) {
                        maxLeft = A[i - 1];
                    } else {
                        maxLeft = Math.max(A[i - 1], B[j - 1]);
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft;
                    }

                    int minRight = 0;
                    if (i == m) {
                        minRight = B[j];
                    } else if (j == n) {
                        minRight = A[i];
                    } else {
                        minRight = Math.min(B[j], A[i]);
                    }

                    return (maxLeft + minRight) / 2.0;
                }
            }
            return 0.0;
        }
    }

}