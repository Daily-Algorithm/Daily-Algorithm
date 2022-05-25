package DFSBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 벽 부수고 이동하기 참고
 */
public class BJ2665_미로만들기_F01 {

	static int N;
	static char[][] maze;
	static int[][] isVisted;
	static Queue<node> queue;
	static int[] dx = {-1, 0, 0, 1};
	static int[] dy = {0, -1, 1, 0};

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		queue = new LinkedList<>();
		maze = new char[N][N];
		isVisted = new int[N][N];

		for(int i = 0 ; i < N ; i++){
			maze[i] = br.readLine().toCharArray();
			Arrays.fill(isVisted[i], Integer.MAX_VALUE);
		}

		queue.offer(new node(0,0));
		isVisted[0][0] = 0;

		BFS();

		System.out.println(isVisted[N-1][N-1]);
	}

	private static void BFS() {

		while(!queue.isEmpty()){

			node now = queue.poll();

			for(int i = 0 ; i < 4; i++){
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if(nx < 0 || N <= nx || ny < 0 || N <= ny)
					continue;

				// 흰 방
				if(maze[nx][ny] == '1'){
					if(isVisted[nx][ny] > isVisted[now.x][now.y]){
						isVisted[nx][ny] = isVisted[now.x][now.y];
						queue.offer(new node(nx, ny));
					}
				}
				// 검은 방
				else{
					if(isVisted[nx][ny] > isVisted[now.x][now.y] + 1){
						isVisted[nx][ny] = isVisted[now.x][now.y] + 1;
						queue.offer(new node(nx, ny));
					}
				}
			}
		}

	}

	public static class node{
		int x;
		int y;

		node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
}
