package coding_interview_guide.c1_stack_queue;

import java.util.HashMap;
import java.util.Stack;


class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int data) {
        this.value = data;
    }
}

/**
 * 一个数组的MaxTree定义如下:
 * 1, 数组必须没有重复元素;
 * 2, MaxTree是一棵二叉树, 数组的每一个值对应一个二叉树节点;
 * 3, 包括MaxTree树在内并且在其中的每一颗子树上, 值最大的节点都是树的头;
 *
 * 给定一个没有重复元素的数组arr,
 * 生成这个数组的MaxTree的函数.
 * 如果数组长度为N
 * 时间复杂度O(N)
 * 空间复杂度O(N)
 *
 * @author CKH
 * @date 2018/2/26 20:38
 */
public class MaxTree {
    public static Node getMaxTree(int[] arr) {
        Node[] nArr = new Node[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nArr[i] = new Node(arr[i]);
        }

        Stack<Node> stack = new Stack<>();
        // 为每一个数组元素构建左右第一个最大值
        HashMap<Node, Node> lBigMap = new HashMap<>();
        HashMap<Node, Node> rBigMap = new HashMap<>();


        /*
          为数组内的每个数据分别对应一个左端第一个最大值，没有则为空 (数组从左向右遍历)
          栈中保持递减序列, 新来的数不停利用pop()出栈顶, 直到栈顶比新数大或没有数
          比如[3, 1, 2],
            首先3入栈, 接下来1比3小, 无需pop出3, 1入栈, 并且确定了1往左第一个比他大的数是3
            接下来2比1大, 1出栈, 2比3小, 2入栈, 并且确定了2往左第一个比他大的数是3
          从右向左同理
         */
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i];

            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, lBigMap);
            }

            //注意，这里保存的是节点，而不是值
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, lBigMap);
        }

        //为数组内的每个数据分别对应一个右端第一个最大值，没有则为空 (数组从右向左遍历)
        for (int i = nArr.length - 1; i != -1; i--) {
            Node curNode = nArr[i];
            while (!stack.isEmpty() && stack.peek().value < curNode.value) {
                popStackSetMap(stack, rBigMap);
            }
            stack.push(curNode);
        }
        while (!stack.isEmpty()) {
            popStackSetMap(stack, rBigMap);
        }

        //构造Node的MaxTree
        Node head = null;
        for (int i = 0; i < nArr.length; i++) {
            Node curNode = nArr[i];

            // 取已经构建好的左右第一个最大值
            Node left = lBigMap.get(curNode);
            Node right = rBigMap.get(curNode);

            System.out.println("nArr[" + i + "] -> value = " + curNode.value);
            System.out.println("    lBigMap(first biggest left) = " + (left == null ? "null" : left.value));
            System.out.println("    rBigMap(first biggest right) = " + (right == null ? "null" : right.value));

            if (left == null && right == null) {
                head = curNode;
            } else if (left == null) {
                if (right.left == null) {
                    right.left = curNode;
                } else {
                    right.right = curNode;
                }
            } else if (right == null) {
                if (left.left == null) {
                    left.left = curNode;
                } else {
                    left.right = curNode;
                }
            } else {
                Node parent = left.value < right.value ? left : right;
                if (parent.left == null) {
                    parent.left = curNode;
                } else {
                    parent.right = curNode;
                }
            }
        }

        return head;
    }

    /**
     * 利用hash_map分别为每一个数的左边和右边第一个最大值（序号）设定关联
     *
     *
     *
     * @param stack
     * @param map
     */
    public static void popStackSetMap(Stack<Node> stack, HashMap<Node, Node> map) {
        Node popNode = stack.pop();
        if (stack.isEmpty()) {
            map.put(popNode, null);
        } else {
            map.put(popNode, stack.peek()); //序号对应序号的关系
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 2};
        getMaxTree(arr);
    }
}
