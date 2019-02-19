# Algorithm

这是一个算法题解的仓库, 包括书籍算法, 程序员算法面试指南, 还有leetcode算法题

## leetcode

leetcode主要是从[leetcode-cn](https://leetcode-cn.com/)上的题目, 都有中文翻译, 有些官方题解都有中文翻译.
使用的语言是java, 代码都是可以直接运行的.

```java
    cd **/**/
    javac Test.java
    java Test
```

我会尽量每天更新一道算法题, 由于我现在还很菜, 所以题目也并不是太难, 见谅哈哈哈.

目前正在做leetcode里的探索(explore)

题目的题解以及思路都在代码里注释了.

### Problems

| ID            | problem         | 题目   | 官方题解 | 代码 |
| :------------ |:---------------:| :-----: | :-----: | :-----: |
| 2    |  Add Two Numbers | [两数相加](https://leetcode-cn.com/problems/add-two-numbers/) | [官方题解](https://leetcode-cn.com/problems/add-two-numbers/solution/) | [Java](src/leetcode/explore/medium/linked-list/P2.java) |
| 328    | odd-even-linked-list | [奇偶链表](https://leetcode-cn.com/problems/odd-even-linked-list/) | 无官方题解 | [Java](src/leetcode/explore/medium/linked-list/P328.java) |
| 94   | binary-tree-inorder-traversal | [中序遍历二叉树](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | 无官方题解 | [Java](src/leetcode/explore/medium/trees-and-graphs/P94.java) |
| 104   | maximum-depth-of-binary-tree | [二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | [无官方题解](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/solution/) | [Java](src/leetcode/explore/medium/trees-and-graphs/P104.java) |
| 105   | construct-binary-tree-from-preorder-and-inorder-traversal | [从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | 无官方题解 | [Java](src/leetcode/explore/medium/trees-and-graphs/P105.java) |
| 106   | construct-binary-tree-from-inorder-and-postorder-traversal | [ 从中序与后序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/) | 无官方题解 | [Java](src/leetcode/explore/medium/trees-and-graphs/P106.java) |
| 116   | populating-next-right-pointers-in-each-node | [填充同一层的兄弟节点](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/) | 无官方题解 | [Java](src/leetcode/explore/medium/trees-and-graphs/P116.java) |
| 230   | kth-smallest-element-in-a-bst | [二叉搜索树中第K小的元素](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/) | 无官方题解 | [Java](src/leetcode/explore/medium/trees-and-graphs/P230.java) |
| 200   | number-of-islands | [岛屿的个数](https://leetcode-cn.com/problems/number-of-islands/) | [官方题解](https://leetcode-cn.com/problems/number-of-islands/solution/) | [Java](src/leetcode/explore/medium/trees-and-graphs/P200.java) |
| 17   | letter-combinations-of-a-phone-number | [电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/) | [官方题解](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/) | [Java](src/leetcode/explore/medium/backtracking/P17.java) |
| 22   | generate-parentheses | [生成括号](https://leetcode-cn.com/problems/generate-parentheses/) | [官方题解](https://leetcode-cn.com/problems/generate-parentheses/solution/) | [Java](src/leetcode/explore/medium/backtracking/P22.java) |
| 46   | permutations | [全排列](https://leetcode-cn.com/problems/permutations/) | [官方题解](https://leetcode-cn.com/problems/permutations/solution/) | [Java](src/leetcode/explore/medium/backtracking/P46.java) |
| 78   | subsets | [子集](https://leetcode-cn.com/problems/subsets/) | [官方题解](https://leetcode-cn.com/problems/subsets/solution/) | [Java](src/leetcode/explore/medium/backtracking/P78.java) |
| 90   | subsets-ii | [子集II](https://leetcode-cn.com/problems/subsets-ii/) | [官方题解](https://leetcode-cn.com/problems/subsets-ii/solution/) | [Java](src/leetcode/Medium/P90.java) |
| 79   | word-search | [单词搜索](https://leetcode-cn.com/problems/word-search/) | [官方题解](https://leetcode-cn.com/problems/word-search/solution/) | [Java](src/leetcode/explore/medium/backtracking/P79.java) |
| 75   | sort-colors | [颜色分类](https://leetcode-cn.com/problems/sort-colors/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P75.java) |
| 347   | top-k-frequent-elements | [前k个高频元素](https://leetcode-cn.com/problems/top-k-frequent-elements/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P347.java) |
| 215   | kth-largest-element-in-an-array | [数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P215.java) |
| 162   | find-peak-element | [寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P162.java) |
| 162   | find-first-and-last-position-of-element-in-sorted-array | [在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P34.java) |
| 56   | merge-intervals | [合并区间](https://leetcode-cn.com/problems/merge-intervals/) | 官方题解 | [Java](src/leetcode/explore/medium/sorting-and-searching/P56.java) |
