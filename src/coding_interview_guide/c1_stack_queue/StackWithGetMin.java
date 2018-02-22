package coding_interview_guide.c1_stack_queue;

import java.util.Stack;

/**
 * 设计一个有getMin功能的栈
 *
 * @author CKH
 * @date 2018/2/22 21:54
 */

class MyStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum <= this.getMin()) {
            this.stackMin.push(newNum);
        }

        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }

        int val = this.stackData.pop();

        if (val == this.getMin()) {
            this.stackMin.pop();
        }

        return val;
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }

        return this.stackMin.peek();
    }
}

class MyStack2 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack2() {
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int newNum) {
        if (this.stackMin.isEmpty()) {
            this.stackMin.push(newNum);
        } else if (newNum < this.getMin()) {
            this.stackMin.push(newNum);
        } else {
            this.stackMin.push(this.stackMin.peek());
        }

        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }

        this.stackMin.pop();

        return this.stackData.pop();
    }

    public int getMin() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("Your stack is empty");
        }

        return this.stackMin.peek();
    }
}

public class StackWithGetMin {
    public static void main(String[] args) {
        MyStack1 myStack1 = new MyStack1();
        myStack1.push(3);
        myStack1.push(4);
        myStack1.push(5);
        myStack1.push(1);
        myStack1.push(2);
        myStack1.push(1);
        System.out.println("myStack1.getMin() = " + myStack1.getMin());
        myStack1.pop();
        myStack1.pop();
        myStack1.pop();
        System.out.println("myStack1.getMin() = " + myStack1.getMin());


        MyStack2 myStack2 = new MyStack2();
        myStack2.push(3);
        myStack2.push(4);
        myStack2.push(5);
        myStack2.push(1);
        myStack2.push(2);
        myStack2.push(1);
        System.out.println("myStack2.getMin() = " + myStack2.getMin());
        myStack2.pop();
        myStack2.pop();
        myStack2.pop();
        System.out.println("myStack2.getMin() = " + myStack2.getMin());
    }
}
