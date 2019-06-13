package cn.iocoder.springboot.labs.lab09.graph;

import java.util.LinkedList;
import java.util.List;

public class DijkstraTest {

    public static class Graph {

        /**
         * 顶点的个数
         */
        private int v;
        /**
         * 邻接表
         */
        private List<Edge>[] adj;

        public Graph(int v) {
            this.v = v;
            adj = new LinkedList[v];
            for (int i = 0; i < v; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int s, int t, int w) {
            adj[s].add(new Edge(s, t, w));
        }

    }

    public static class Edge {

        /**
         * 来源顶点
         */
        private int s;
        /**
         * 目标顶点
         */
        private int t;
        /**
         * 权重（距离）
         */
        private int w;

        public Edge(int s, int t, int w) {
            this.s = s;
            this.t = t;
            this.w = w;
        }

    }

    public static class Vertex {

        /**
         * 顶点编号 id
         */
        private int id;

        /**
         * 从起始顶点，到达此处的最短距离。
         */
        private int dist;

    }


}
