import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = -Integer.parseInt(st.nextToken());
			}
		} 
		Queue<Cheese> q = new PriorityQueue<>((a,b) -> a.time - b.time);
		q.offer(new Cheese(0, 0, 0, false));
		visited[0][0] = true;
		
		int ans = 0, count = 0;
		
		while (!q.isEmpty()) {
			Cheese cur = q.poll();
			
			if (cur.time > ans) {
				ans = cur.time;
				count = 0;
			}
			if (cur.isCheese) count++;
			map[cur.y][cur.x] = 0;
			
			for (int i = 0; i < 4; i++) {
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				if (ny < 0 || ny >= N || nx < 0 || nx >= M || visited[ny][nx]) continue;
				if (map[ny][nx] == 0) {
					visited[ny][nx] = true;
					q.offer(new Cheese(ny, nx, cur.time, false));
				}
				else if (map[ny][nx] == -1) {
					visited[ny][nx] = true;
					q.offer(new Cheese(ny, nx, cur.time+1, true));
				}
			}
		}
		
		System.out.println(ans + "\n" + count);
	}
	
	static class Cheese {
		int y, x, time;
		boolean isCheese;
		public Cheese(int y, int x, int time, boolean isCheese) {
			this.y = y;
			this.x = x;
			this.time = time;
			this.isCheese = isCheese;
		}
	}
}
