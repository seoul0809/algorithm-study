package heeheejj.boj;

// 네트워크 연결
// 600ms, 49352kb

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1922 {
public static class Edge implements Comparable<Edge> {

    int from, to, weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return this.weight - o.weight;
    }
}

    public static int V, E;
    public static int[] parents;
    public static Edge[] edgeList;

    public static void make() {
        parents = new int[V];
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    public static int find(int a) {
        if(parents[a] == a)		return a;
        return parents[a] = find(parents[a]);
    }

    public static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if(aRoot == bRoot)	return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(in.readLine());
        E = Integer.parseInt(in.readLine());
        StringTokenizer st;

        edgeList = new Edge[E];
        parents = new int[V];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(in.readLine());
            edgeList[i] = new Edge(Integer.parseInt(st.nextToken())-1, // from
                    Integer.parseInt(st.nextToken())-1, // to
                    Integer.parseInt(st.nextToken())); // weight
        }
        make();
        Arrays.sort(edgeList);

        long result = 0;
        int count = 0;

        for (Edge edge : edgeList) {
            if(union(edge.from, edge.to)) {
                result += edge.weight;
                if(++count == V-1)	break;
            }
        }

        System.out.println(result);
    }
}
