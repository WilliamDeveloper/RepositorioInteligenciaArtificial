package algoritmos;

import java.util.ArrayDeque;
import java.util.Arrays;

public class KnightMoves {

    static int[][] dirs = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2},
    {-1, 2}, {1, -2}, {-1, -2}};

    public static int bfs(int nr, int nc, int tr, int tc, int n) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dist[i], -1);
        }
        dist[nr][nc] = 0;
        queue.add(new int[]{nr, nc});
        while (!queue.isEmpty()) {
            int[] v = queue.poll();
            int vdist = dist[v[0]][v[1]];
            if (tr == v[0] && tc == v[1]) {
                return vdist;
            }
            for (int[] d : dirs) {
                int r = v[0] + d[0], c = v[1] + d[1];
                if (r >= n || r < 0 || c >= n || c < 0) {
                    continue;
                }
                if (dist[r][c] >= 0) {
                    continue;
                }
                dist[r][c] = vdist + 1;
                queue.add(new int[]{r, c});
            }
        }
        return -1; //Only if unreachable
    }

    public static void main(String[] args) {
        System.out.println(bfs(0, 0, 7, 7, 8));
        System.out.println(bfs(0, 0, 7, 0, 8));
    }
}
