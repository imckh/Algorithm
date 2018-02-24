package coding_interview_guide.c1_stack_queue;

import java.util.Stack;

/**
 * @author CKH
 * @date 2018/2/24 16:21
 */
public class SortStackByStack {
    public static void sortStackByStack(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            while (!help.isEmpty() && help.peek() > cur) {
                stack.push(help.pop());
            }
            help.push(cur);
        }

        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(5);
        stack.push(2);
        stack.push(1);
        stack.push(3);
        sortStackByStack(stack);
    }
}
