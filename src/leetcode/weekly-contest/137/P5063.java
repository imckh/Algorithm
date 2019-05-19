/*
5063. 最后一块石头的重量
有一堆石头，每块石头的重量都是正整数。

每一回合，从中选出两块最重的石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：

如果 x == y，那么两块石头都会被完全粉碎；
如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。

 

提示：

1 <= stones.length <= 30
1 <= stones[i] <= 1000
*/
import java.util.*;
public class P5063 {
    public static void main(String[] args) {
        //int[] stones = {2,7,4,1,8,1};
        int[] stones = {2,2};

        System.out.println(new Solution().lastStoneWeight(stones));
    }

    static class Solution {
        public int lastStoneWeight(int[] stones) {
            if (stones == null || stones.length == 0) {
                return 0;
            }

            Arrays.sort(stones);

            PriorityQueue<Integer> stoneQueue = new PriorityQueue<>((a, b) -> b - a);
            for (int s : stones) {
                stoneQueue.offer(s);
            }

            while (stoneQueue.size() > 1) {
                int x = stoneQueue.poll();
                int y = stoneQueue.poll();
                if (x == y) {
                    continue;
                }
                if (x != y) {
                    stoneQueue.offer(Math.abs(x - y));
                }
            }

            return stoneQueue.size() == 0 ? 0 : stoneQueue.poll();
        }
    }
}