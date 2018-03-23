package algorithm.c4_graph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * 有向无环图中基于深度优先搜索的定点排序
 * 1. 前序, 在递归调用之前将顶点加入队列
 * 2. 后序, 在递归调用之后将顶点加入队列
 * 3. 逆后续, 在递归调用之后将顶点加入栈
 *
 * @author CKH
 * @date 2018/3/21 19:38
 */
public class DepthFirstOrder {
    private boolean[] marked;
    private Queue<Integer> pre;             // 所有顶点的前序排序
    private Queue<Integer> post;            // 所有顶点的后序排序
    private Stack<Integer> reversePost;    // 所有顶点的逆后序排列

    public DepthFirstOrder(Digraph G) {
        this.marked = new boolean[G.V()];
        this.pre = new Queue<>();
        this.post = new Queue<>();
        this.reversePost = new Stack<>();

        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.enqueue(v);
        marked[v] = true;

        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return this.pre;
    }

    public Iterable<Integer> post() {
        return this.post;
    }

    public Iterable<Integer> reversePost() {
        return this.reversePost;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        DepthFirstOrder dfs = new DepthFirstOrder(G);

        StdOut.println("   v  pre post");
        StdOut.println("--------------");

        StdOut.print("Preorder:  ");
        for (int v : dfs.pre()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Postorder: ");
        for (int v : dfs.post()) {
            StdOut.print(v + " ");
        }
        StdOut.println();

        StdOut.print("Reverse postorder: ");
        for (int v : dfs.reversePost()) {
            StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
