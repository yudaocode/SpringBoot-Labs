package cn.iocoder.springboot.labs.lab09.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Graph {

    /**
     * 顶点的个数
     */
    private int v;
    /**
     * 邻接表
     */
    private List<Integer>[] adj;

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s, int t) {
        adj[s].add(t);
        adj[t].add(s);
    }

    public void print() {
        for (int i = 0; i < v; i++) {
            System.out.print(i + " ：");
            System.out.println(adj[i]);
        }
    }

    public void bfs(int s, int t) {
        if (s == t) {
            throw new IllegalStateException("参数不合理");
        }
        // 初始化已访问
        boolean[] visited = new boolean[v];
        visited[s] = true;

        // 当前走到的路径
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        // 记录哪个节点走到这里。通过它，实现路径的记录
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        while (queue.size() != 0) {
            int w = queue.poll();
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                if (!visited[q]) {
                    // 记录是从 w 走到 q
                    prev[q] = w;
                    // 判断是否到达目的地
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    public void dfs(int s, int t) {
        if (s == t) {
            throw new IllegalStateException("参数不合理");
        }
        // 创建，标记是否找到
        AtomicBoolean found = new AtomicBoolean(false);

        // 初始化已访问
        boolean[] visited = new boolean[v];

        // 记录哪个节点走到这里。通过它，实现路径的记录
        int[] prev = new int[v];
        Arrays.fill(prev, -1);

        // dfs 遍历
        dfs(s, t, found, visited, prev);

        if (found.get()) {
            print(prev, s, t);
        } else {
            System.out.println("未找到路径...");
        }
    }

    private void dfs(int w, int t, AtomicBoolean found, boolean[] visited, int[] prev) {
        // 判断是否已经找到
        if (found.get()) {
            return;
        }

        // 标记已完成
        visited[w] = true;

        // 判断是否到达目的地
        if (w == t) {
            found.set(true);
            return;
        }

        // 遍历
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if (!visited[q]) {
                // 记录是从 w 走到 q
                prev[q] = w;
                // 继续递归
                dfs(q, t, found, visited, prev);
            }
        }
    }

    // 递归打印从 s 到 t 的点
    public void print(int[] prev, int s, int t) {
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }

    public static void main(String[] args) {
        Graph graph = new Graph(10);
        graph.addEdge(1, 3);
        graph.addEdge(3, 5);
        graph.addEdge(5, 7);
//        graph.addEdge(9, 6);
//        graph.print();
//        graph.bfs(1, 7);
        graph.dfs(1, 7);

    }

}
