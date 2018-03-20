package algorithm.c4_graph;

/**
 * 无环图
 *
 * @author CKH
 * @date 2018/3/19 20:15
 */
public class Cycle {
    private boolean[] marked;
    private boolean hasCycle;

    public Cycle(Graph G) {
        this.marked = new boolean[G.V()];
        for (int s = 0; s < G.V(); s++) {
            if (!marked[s]) {
                dfs(G, s, s);
            }
        }
    }

    private void dfs(Graph G, int v, int u) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w, v);   // v --> 记录该邻接表的顶点, 进入下一次递归
            } else if (w == u) { // 深度遍历的时候遇到了相同的结点
                hasCycle = true;
            }
        }
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}
