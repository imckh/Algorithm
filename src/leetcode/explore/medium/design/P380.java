/*
380. 常数时间插入、删除和获取随机元素

https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-medium/52/design/110/

设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构。

insert(val)：当元素 val 不存在时，向集合中插入该项。
remove(val)：元素 val 存在时，从集合中移除该项。
getRandom：随机返回现有集合中的一项。每个元素应该有相同的概率被返回。
示例 :

// 初始化一个空的集合。
RandomizedSet randomSet = new RandomizedSet();

// 向集合中插入 1 。返回 true 表示 1 被成功地插入。
randomSet.insert(1);

// 返回 false ，表示集合中不存在 2 。
randomSet.remove(2);

// 向集合中插入 2 。返回 true 。集合现在包含 [1,2] 。
randomSet.insert(2);

// getRandom 应随机返回 1 或 2 。
randomSet.getRandom();

// 从集合中移除 1 ，返回 true 。集合现在包含 [2] 。
randomSet.remove(1);

// 2 已在集合中，所以返回 false 。
randomSet.insert(2);

// 由于 2 是集合中唯一的数字，getRandom 总是返回 2 。
randomSet.getRandom();
*/
import java.util.*;
public class P380 {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(2);
        boolean param_2 = obj.remove(3);
        int param_3 = obj.getRandom();
        System.out.println(param_3);
    }

    static class RandomizedSet {
        // 使用数组来保存当前集合中的元素，同时用一个hashMap来保存数字与它在数组中下标的对应关系。
        private ArrayList<Integer> nums;
        private HashMap<Integer, Integer> num2index;
        private Random rand;

        public RandomizedSet() {
            nums = new ArrayList<Integer>();
            num2index = new HashMap<Integer, Integer>();  
            rand = new Random();
        }
        /*
        插入操作时：
            若已存在此元素返回false
            不存在时将新的元素插入数组最后一位，同时更新hashMap
        */
        public boolean insert(int val) {
            if (num2index.containsKey(val)) {
                return false;
            }
            num2index.put(val, nums.size());
            nums.add(val);
            return true;
        }
        
        /*
        删除操作时：

            若不存在此元素返回false
            存在时先根据hashMap得到要删除数字的下标，
            再将数组的最后一个数放到需要删除的数的位置上，删除数组最后一位，同时更新hashMap。
        */
        public boolean remove(int val) {
            if (!num2index.containsKey(val)) {
                return false;
            }
            
            int index = num2index.get(val);
            if (index < nums.size() - 1) {
                int last = nums.get(nums.size() - 1);
                nums.set(index, last);
                num2index.put(last, index);
            }
            num2index.remove(val);
            nums.remove(nums.size() - 1);
            return true;
        }
        
        /**
        获取随机数操作时：

            根据数组的长度来获取一个随机的下标，再根据下标获取元素。
        */
        public int getRandom() {
            return nums.get(rand.nextInt(nums.size()));
        }
    }
    
    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */
}