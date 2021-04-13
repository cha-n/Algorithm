package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2564_경비원 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		int length = (w + h) * 2;

		int n = Integer.parseInt(br.readLine());
		int[] locations = new int[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int dis = Integer.parseInt(st.nextToken());
			locations[i] = getLocation(dir, dis, w, h);
		}
		st = new StringTokenizer(br.readLine());
		int dir = Integer.parseInt(st.nextToken());
		int dis = Integer.parseInt(st.nextToken());

		int dgLoc = getLocation(dir, dis, w, h);
		int totDistance = 0;
		for (int location : locations) {
			int tempDistance = Math.abs(location - dgLoc);
			totDistance += Math.min(tempDistance, length - tempDistance);
		}
		System.out.println(totDistance);
	}

	static int getLocation(int dir, int dis, int w, int h) {
		if (dir == 1) {
			return dis;
		} else if (dir == 2) {
			return (2 * h + 2 * w) - h - dis;
		} else if (dir == 3) {
			return (2 * h + 2 * w) - dis;
		} else {
			return w + dis;
		}
	}
}
