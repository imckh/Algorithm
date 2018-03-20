package algorithm.c4_graph;

/**
 * 深度优先搜索
 *
 * @author CKH
 * @date 2018/3/18 18:03
 */
public class DepthFirstSearch {
    private boolean[] marked;
    private int count;

    public DepthFirstSearch(Graph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // 深度优先遍历
    private void dfs(Graph G, int v) {
        marked[v] = true;           // 将它的访问标记为已访问
        count++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {       // 递归的访问他的所没有访问过的邻居顶点
                dfs(G, w);
            }
        }
    }

    public boolean marked(int w) {
        return marked[w];
    }

    public int count() {
        return count;
    }
}
