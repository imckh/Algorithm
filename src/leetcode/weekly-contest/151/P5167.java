/*
5167. 查询无效交易

如果出现下述两种情况，交易 可能无效：

交易金额超过 ¥1000
或者，它和另一个城市中同名的另一笔交易相隔不超过 60 分钟（包含 60 分钟整）
每个交易字符串 transactions[i] 由一些用逗号分隔的值组成，这些值分别表示交易的名称，时间（以分钟计），金额以及城市。

给你一份交易清单 transactions，返回可能无效的交易列表。你可以按任何顺序返回答案。

 

示例 1：

输入：transactions = ["alice,20,800,mtv","alice,50,100,beijing"]
输出：["alice,20,800,mtv","alice,50,100,beijing"]
解释：第一笔交易是无效的，因为第二笔交易和它间隔不超过 60 分钟、名称相同且发生在不同的城市。同样，第二笔交易也是无效的。
示例 2：

输入：transactions = ["alice,20,800,mtv","alice,50,1200,mtv"]
输出：["alice,50,1200,mtv"]
示例 3：

输入：transactions = ["alice,20,800,mtv","bob,50,1200,mtv"]
输出：["bob,50,1200,mtv"]
 

提示：

transactions.length <= 1000
每笔交易 transactions[i] 按 "{name},{time},{amount},{city}" 的格式进行记录
每个交易名称 {name} 和城市 {city} 都由小写英文字母组成，长度在 1 到 10 之间
每个交易时间 {time} 由一些数字组成，表示一个 0 到 1000 之间的整数
每笔交易金额 {amount} 由一些数字组成，表示一个 0 到 2000 之间的整数
*/
import java.util.*;

public class P5167 {
    public static void main(String[] args) {
        //String[] transactions = {"alice,20,800,mtv","alice,50,100,beijing"};
        String[] transactions = {"bob,689,1910,barcelona","alex,696,122,bangkok","bob,832,1726,barcelona","bob,820,596,bangkok","chalicefy,217,669,barcelona","bob,175,221,amsterdam"};
        System.out.println(new Solution().invalidTransactions(transactions));
    }

    static class Solution {
        public List<String> invalidTransactions(String[] transactions) {
            Map<String, Map<String, List<String>>> map = new HashMap<>();
            Set<String> set = new HashSet<>();

            for (int i = 0; i < transactions.length; i++) {
                String[] transaction = transactions[i].split(",");
                String name = transaction[0];
                int amount = Integer.valueOf(transaction[2]);
                String city = transaction[3];

                if (amount > 1000) {
                    set.add(transactions[i]);
                }
                
                if (!map.containsKey(name)) {
                    map.put(name, new HashMap<>());
                }
                if (!map.get(name).containsKey(city)) {
                    map.get(name).put(city, new ArrayList<>());
                }
                map.get(name).get(city).add(transactions[i]);
            }
            System.out.println(map);
            for (Map.Entry<String, Map<String, List<String>>> e : map.entrySet()) { // name
                System.out.println();
                for (Map.Entry<String, List<String>> v : e.getValue().entrySet()) { // city
                    String city = v.getKey();

                    for (Map.Entry<String, List<String>> c : e.getValue().entrySet()) {
                        if (!c.getKey().equals(city)) {
                            // name相同 city不同

                            for (String s1 : v.getValue()) {
                                String[] transaction1 = s1.split(",");
                                int time1 = Integer.valueOf(transaction1[1]);

                                for (String s2 : c.getValue()) {
                                    String[] transaction2 = s2.split(",");
                                    int time2 = Integer.valueOf(transaction2[1]);
                                    System.out.println(time1 + " " + time2);
                                    if (Math.abs(time1 - time2) <= 60) {
                                        set.add(s1);
                                        set.add(s2);
                                    }
                                }
                            }

                        }
                    }
                }
            }

            return new ArrayList<>(set);
        }
    }
}