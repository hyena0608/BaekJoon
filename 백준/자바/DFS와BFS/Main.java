package 백준.자바.DFS와BFS;

import java.io.*;
import java.util.*;

public class Main {

    static int[][] graphArr;
    static List<Integer> dfsAnswerList = new ArrayList<>();
    static List<Integer> bfsAnswerList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());

        graphArr = new int[n + 1][n + 1];

        for (int idx = 0; idx < m; idx++) {
            StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
            int startV = Integer.parseInt(stringTokenizer.nextToken());
            int endV = Integer.parseInt(stringTokenizer.nextToken());

            graphArr[startV][endV] = 1;
            graphArr[endV][startV] = 1;
        }

        bfs(n, v);
        dfs(n, v);

        for (Integer integer : dfsAnswerList) {
            System.out.print(integer + " ");
        }

        for (Integer integer : bfsAnswerList) {
            System.out.print(integer + " ");
        }

    }

    private static void bfs(int n, int v) {
        Queue<Integer> Q = new LinkedList<>();

        Q.offer(v);
        while (!Q.isEmpty()) {
            Integer polledV = Q.poll();
            bfsAnswerList.add(polledV);

            if (bfsAnswerList.size() == n) {
                break;
            }

            for (int idx = 1; idx <= n; idx++) {
                if (graphArr[polledV][idx] == 1
                        && !bfsAnswerList.contains(idx)) {
                    Q.offer(idx);
                }
            }
        }

    }

    private static void dfs(int n, int v) {
        if (dfsAnswerList.size() == n) {
            return;
        }

        for (int idx = 1; idx <= n; idx++) {
            if (graphArr[v][idx] == 1 || graphArr[idx][v] == 1) {
                if (!dfsAnswerList.contains(v)) {
                    dfsAnswerList.add(v);
                }
                dfs(n, idx);
            }
        }
    }


}
