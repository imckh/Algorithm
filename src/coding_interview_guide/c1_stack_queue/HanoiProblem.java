package coding_interview_guide.c1_stack_queue;

import java.util.Stack;

/**
 * 汉诺塔问题
 *
 * ** 从左到右 从右到左 必须经过中间
 *
 * @author CKH
 * @date 2018/2/24 16:59
 */
public class HanoiProblem {
    /**
     * 递归方法
     * @param num 层数
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int hanoiProblem1(int num, String left, String mid, String right) {
        if (num < 1) {
            return 0;
        }

        return process(num, left, mid, right, left, right);
    }

    /**
     * 记录总移动次数
     */
    private static int count = 0;

    /**
     * 递归方法
     * @param num
     * @param left
     * @param mid
     * @param right
     * @param from
     * @param to
     * @return
     */
    private static int process(int num, String left, String mid, String right, String from, String to) {
        // 只剩最后一层需要移动
        if (num == 1) {
            if (from.equals(mid) || to.equals(mid)) {
                // 有关中间的移动, 只需要一步
                System.out.println(count++ + ". Move 1 from " + from + " to " + to);

                return 1;
            } else {
                // 从左到右 右到左 需要两步
                System.out.println(count++ + ". Move 1 from " + from + " to " + mid);
                System.out.println(count++ + ". Move 1 from " + mid + " to " + to);

                return 2;
            }
        }
        // 左到中, 中到左, 右到中, 中到右 需要三步
        // N-1个需要中介移动, 第N个直接移动
        // 比如左到中
        // 1. 将1~N-1全部从'左'移到'右', 递归
        // 2. 将第N层从'左'移到'中'
        // 3. 将1~N-1全部从右移到'中', 递归
        if (from.equals(mid) || to.equals(mid)) {
            // 移动的中介
            String another = (from.equals(left) || to.equals(left)) ? right : left;
            int part1 = process(num - 1, left, mid, right, from, another);
            int part2 = 1;
            System.out.println(count++ + ". Move " + num + " from " + mid + " to " + to);
            int part3 = process(num - 1, left, mid, right, another, to);
            return part1 + part2 + part3;
        }
        // 左到右, 右到左, 需要5步
        // 比如左到右
        // 1. 将1~N-1全部从'左'移到'右', 递归
        // 2. 将第N层从'左'移到'中'
        // 3. 将1~N-1从'右'移到'左', 递归
        // 4. 将第N层移到右
        // 5. 将1~N-1从'左'移到'右', 递归
        else {
            int part1 = process(num - 1, left, mid, right, from, to);
            int part2 = 1;
            System.out.println(count++ + ". Move " + num + " from " + from + " to " + mid);
            int part3 = process(num - 1, left, mid, right, to, from);
            int part4 = 1;
            System.out.println(count++ + ". Move " + num + " from " + mid + " to " + to);
            int part5 = process(num - 1, left, mid, right, from, to);

            return part1 + part2 + part3 + part4 + part5;
        }
    }

    /**
     * 一共只有四种动作
     * 左到中, 中到左, 中到右, 右到中
     */
    public enum Action {
        No,
        /**
         * 左到中
         */
        LToM,
        MToL,
        MToR,
        RToM
    }

    /**
     * 假设都在左边, 第一步肯定是从左边移到中间, (小压大, 相邻不可逆两个原则)
     * 而且只要知道上一步, 在走出最小步数的任意时刻, 下一步是确定的
     * 比如上一步是L->M
     * 则根据小压大下一步不可能是L->M
     * 相邻的步数是不可逆的, 则不可能是M->L
     * 根据小压大, M->R, R->M 只有可能一个达标
     * 其他三个动作同理
     *
     * @param num
     * @param left
     * @param mid
     * @param right
     * @return
     */
    public static int hanoiProblem2(int num, String left, String mid, String right) {
        // 构造三个栈 分别代表左中右, 最左边的为初始的汉诺塔
        Stack<Integer> ls = new Stack<>();
        Stack<Integer> ms = new Stack<>();
        Stack<Integer> rs = new Stack<>();
        ls.push(Integer.MAX_VALUE);
        ms.push(Integer.MAX_VALUE);
        rs.push(Integer.MAX_VALUE);

        for (int i = num; i > 0; i--) {
            ls.push(i);
        }

        // 记录上一次的动作
        Action[] record = {Action.No};
        int step = 0;

        while (rs.size() != num + 1) {
            // 每一步只有一个达标, 那么只要每走一部都根据这两个原则考察左右动作即可.
            // 那个动作达标就走哪一个, 反正每次只有一个动作达标

            // 第一步肯定是从左边移到中间, 第一次执行时可以执行这一步的
            step += fStackTotStack(record, Action.MToL, Action.LToM, ls, ms, left, mid);
            step += fStackTotStack(record, Action.LToM, Action.MToL, ms, ls, mid, left);
            step += fStackTotStack(record, Action.RToM, Action.MToR, ms, rs, mid, right);
            step += fStackTotStack(record, Action.MToR, Action.RToM, rs, ms, right, mid);
        }

        return step;
    }

    /**
     * from stack to dest stack
     * @param record 用于记录上一次动作
     * @param preNoAct 用于判断的上一次动作
     * @param nowAct 将要执行的下一次的动作
     * @param fStack 源栈
     * @param tStack 目的栈
     * @return 步数, 不达标则是0, 达标为1
     */
    public static int fStackTotStack(Action[] record, Action preNoAct, Action nowAct,
                                     Stack<Integer> fStack, Stack<Integer> tStack,
                                     String from, String to) {
        // 上一次的动作和这一次将要执行的动作是不可能相反的,
        // 所以判断上一次的动作跟这次动作的反向动作是不是一样
        // 而且要符合小压大原则
        if (record[0] != preNoAct && fStack.peek() < tStack.peek()) {
            tStack.push(fStack.pop());
            System.out.println("Move " + tStack.peek() + " from " + from + " to " + to);

            // 记录上一次的动作
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        //hanoiProblem1(2, "左", "中", "右");
        int counts = hanoiProblem2(5, "左", "中", "右");
        System.out.println("counts = " + counts);
    }
}
