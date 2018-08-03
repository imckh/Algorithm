//package coding_interview_guide.c1_stack_queue;

import java.util.Stack;

/**
 * 用两个栈实现队列
 *
 * @author CKH
 * @date 2018/2/23 18:21
 */
public class TwoStacksQueue<T> {
    public Stack<T> stackPush;
    public Stack<T> stackPop;

    public TwoStacksQueue() {
        stackPush = new Stack<T>();
        stackPop = new Stack<T>();
    }

    public void add(T pushVal) {
        stackPush.push(pushVal);
    }

    public T poll() {
        transfer();

        return stackPop.pop();
    }

    /**
     * without removing it
     * @return
     */
    public T peek() {
        transfer();

        return stackPop.peek();
    }

    /**
     * 先将stackPush中的全部压入stackPop
     */
    private void transfer() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty");
        } else if (stackPop.isEmpty()) {
            while (!stackPush.isEmpty()) {
                stackPop.push(stackPush.pop());
            }
        }
    }

    public static void main(String[] args) {
        TwoStacksQueue<Integer> queue = new TwoStacksQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println("queue.peek() = " + queue.peek());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        queue.add(4);
        queue.add(5);
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
        System.out.println("queue.poll() = " + queue.poll());
    }
}
