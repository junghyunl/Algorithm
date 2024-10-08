import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, start, maxDepth, ans;
	static ArrayList<Integer>[] adjList;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		for (int tc = 1; tc <= 10; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			adjList = new ArrayList[101];
			maxDepth = 0; ans = 0;
			for (int i = 1; i < 101; i++) {
				adjList[i] = new ArrayList<>();
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from].add(to);
			}
			bfs();
			System.out.println("#" + tc + " " + ans);
		}
	}
	public static void bfs() {
		Queue<Cur> queue = new LinkedList<Cur>();
		boolean visited[] = new boolean[101];
		
		queue.offer(new Cur(start,0));
		visited[start] = true;
		
		while(!queue.isEmpty()) {
			Cur c = queue.poll();
			if (c.depth > maxDepth) {
				maxDepth = c.depth;
				ans = c.v;
			}else if (c.depth == maxDepth) ans = Math.max(ans, c.v);
			
			for (int temp : adjList[c.v]) {
				if(!visited[temp]) {
					queue.offer(new Cur(temp, c.depth+1));
					visited[temp] = true;
				}
			}
		}
	}
	static class Cur {
		int v;
		int depth;
		public Cur(int v, int depth) {
			this.v = v;
			this.depth = depth;
		}
	}
}
