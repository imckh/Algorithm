/*
155. 最小栈

https://leetcode-cn.com/problems/min-stack/
https://leetcode-cn.com/explore/interview/card/top-interview-questions-easy/24/design/59/

设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

push(x) -- 将元素 x 推入栈中。
pop() -- 删除栈顶的元素。
top() -- 获取栈顶元素。
getMin() -- 检索栈中的最小元素。
示例:

MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.getMin();   --> 返回 -2.

*/
import java.util.*;
public class P155 {
    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(512);
        minStack.push(-1024);
        minStack.push(-1024);
        minStack.push(512);
        minStack.pop();
        
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
        minStack.pop();
        System.out.println(minStack.getMin());
    }

    static class MinStack {
        private LinkedList<Integer> stack;
        private LinkedList<Integer> minStacks;

        /** 
         * 维护原始栈的同时维护一个最小栈, 其栈顶是最小值
         * 入栈时检查x是否小于栈顶的数,若更小则插入原始栈的同时也插入最小栈
         * 出栈时检查x是否同最小栈顶的值相同, 若相同, 说明该最小值已经出栈了
        */
        public MinStack() {
            stack = new LinkedList<>();
            minStacks = new LinkedList<>();
        }
        
        public void push(int x) {
            stack.push(x);
            if (minStacks.isEmpty()) {
                minStacks.push(x);
            } else {
                // x大于剩余的最小
                if (minStacks.peek() >= x) {
                    minStacks.push(x);
                }
            }
        }
        
        public void pop() {
            System.out.println("   st " + stack);
            System.out.println("minst " + minStacks);
            System.out.printf("stack.peek() %d == minStacks.peek() %d \n" , stack.peek(), minStacks.peek());
            if (stack.peek().equals(minStacks.peek())) {
                minStacks.pop();
            }
            stack.pop();
        }
        
        public int top() {
            return stack.peek();
        }
        
        public int getMin() {
            return minStacks.peek();
        }
    }
    
    /**
     * Your MinStack object will be instantiated and called as such:
     * MinStack obj = new MinStack();
     * obj.push(x);
     * obj.pop();
     * int param_3 = obj.top();
     * int param_4 = obj.getMin();
     */
}