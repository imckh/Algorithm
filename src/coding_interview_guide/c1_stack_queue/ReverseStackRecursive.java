package coding_interview_guide.c1_stack_queue;

import java.util.Stack;

/**
 * 用递归和栈操作, 逆序一个栈
 *
 * @author CKH
 * @date 2018/2/23 19:41
 */
public class ReverseStackRecursive {
    public static <T> T getAndRemoveLastElemet(Stack<T> stack) {
        T result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            T last = getAndRemoveLastElemet(stack);
            stack.push(result);
            return last;
        }
    }

    public static <T> void reverse(Stack<T> stack) {
        if (stack.isEmpty()) {
            return;
        }

        T i = getAndRemoveLastElemet(stack);
        reverse(stack);
        stack.push(i);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        //System.out.println("getAndRemoveLastElemet(stack) = " + getAndRemoveLastElemet(stack));
        
        reverse(stack);

        for (int i = 0; i < 6; i++) {
            System.out.println("stack = " + stack.pop());
        }
    }
}
