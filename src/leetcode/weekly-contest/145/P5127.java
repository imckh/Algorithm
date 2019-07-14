/*
5127. 数组的相对排序

给你两个数组，arr1 和 arr2，

arr2 中的元素各不相同
arr2 中的每个元素都出现在 arr1 中
对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。

 

示例：

输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
输出：[2,2,2,1,4,3,3,9,6,7,19]
 

提示：

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
arr2 中的元素 arr2[i] 各不相同
arr2 中的每个元素 arr2[i] 都出现在 arr1 中
*/

import java.util.*;

public class P5127 {
    public static void main(String[] args) {
        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};

        //new Solution().relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(new Solution().relativeSortArray(arr1, arr2)));
    }

    static class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : arr1) {
                map.put(i, map.getOrDefault(i, 0) + 1);
            }

            LinkedList<Integer> list = new LinkedList<>();

            for (int a2 : arr2) {
                list.add(a2);
            }
            List<Integer> sort = new ArrayList<>();
            for (Map.Entry<Integer, Integer> el : map.entrySet()) {
                int n = el.getKey();
                int times = el.getValue();

                int index = list.indexOf(n);

                if (index > -1) {
                    for (int j = 0; j < times - 1; j++) {
                        list.add(index, n);
                    }
                } else {
                    for (int j = 0; j < times; j++) {
                        sort.add(n);
                    }
                }
            }

            //System.out.println(list);
            Collections.sort(sort);

            int[] result = new int[list.size() + sort.size()];
            int i = 0;
            for (int t : list) {
                result[i++] = t;
            }
            for (int t : sort) {
                result[i++] = t;
            }

            return result;
        }
    }
}
// [2,42,38,0,43,21,5,7,12,12,13,23,24,24,27,29,30,31,33,48]
// [2,42,38,0,43,21,5,7,12,12,13,23,24,24,27,29,30,31,33,48]
