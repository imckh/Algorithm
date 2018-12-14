/*
1. 两数之和

https://leetcode-cn.com/problems/two-sum/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/1/array/29/

给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
*/
import java.util.*;
public class P1 {
    public static void main(String[] args) {
        int[] arr = { 2, 7, 11, 15};
        
        System.out.println(Arrays.toString(new Solution().twoSum(arr, 9)));
    }

    static class Solution {
        public int[] twoSum(int[] nums, int target) {
            int l = nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            
            for (int i = 0; i < l; ++i) {
                // 把target - nums[i] 当做key放进map中, 当前i是value
                // 如果map中包含该值说明这两个value的nums[value]相加等于target
                if (map.containsKey(nums[i])) {
                    return new int[]{map.get(nums[i]), i};
                }
                
                map.put(target - nums[i], i);
            }
            
            return null;
        }
    }

    static class Solution2 {
        // leetcode 第一个答案: 将map换为数组
        //Solution 2 by best solution 
        private int size = 2048;
        public int[] twoSum(int[] nums, int target) {
                                            
            int[] map = new int[size];   //定义一个超大数组能涵括大部分数
            int index;  //index 引用nums中得values 做map的数组下标
            for(int i = 0; i < nums.length; i++) {
                index = nums[i] & (size - 1);   //1)先定义第一个numes元素的数为 map的下标
                if(map[index] != 0) {            
                    return new int[]{map[index] - 1, i};   //4) 如果发现index数值为3）计算过的数字，能表明当前数组有求和为target的数字，返回index为下标map的元素值-1
                                                          // 为3）步时index的位置，和当前nums的下标位置。
                }else {                           // 2) 因为map为新建数组，所以其所有元素初始状态一定为0， 因此先执行else部分
                    map[(target - index) & (size - 1)] = i + 1;   // 3) 计算target的另一个数字，并以其为map的下标，并以当前nums的下标i+1为其数值
                                                                  // 不可直接用i，因为i已经是index数的下标 
                }
            }
            throw new IllegalArgumentException("No two sum solution");
          
            /*  Solution 1 by myself common solution
            int[] newA=new int[2];
            outer:for(int i=0; i<=nums.length-2;i++)
             for(int j=i+1;j<=nums.length-1;j++)   
                if(nums[i]+nums[j]==target)
                {
                newA[0]=i;
                newA[1]=j;
                    break outer;
                }
            return newA;
            */
        }
    }
}