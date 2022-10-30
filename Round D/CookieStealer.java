import java.util.*;
import java.io.*;

public class CookieStealer {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			List<Set<Integer>> adj = new ArrayList<>();
			boolean[] picked = new boolean[N];

			for (int i = 0; i < N; i++) {
				adj.add(new HashSet<>());
			}
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken()) - 1;
				int B = Integer.parseInt(st.nextToken()) - 1;
				picked[B] = true;
				adj.get(A).add(B);
			}

			int cnt = 0;
			int[] dist = new int[N];
			boolean[] cycle = new boolean[N];

			for (int i = 0; i < N; i++) {
				if (!picked[i]) {
					List<Integer> queue = new ArrayList<>(Arrays.asList(i));
					dist[i] = 1;

					while (queue.size() > 0) {
						List<Integer> newQueue = new ArrayList<>();

						for (int u : queue) {
							for (int v : adj.get(u)) {
								if (dist[v] == 0) {
									newQueue.add(v);
								} else {
									cycle[v] = true;
								}
								dist[v] = dist[u] + 1;
							}
						}
						queue = newQueue;
					}
				}
			}

			for (int i = 0; i < N; i++) {
				if (!picked[i]) {
					List<Integer> queue = new ArrayList<>(Arrays.asList(i));

					while (queue.size() > 0) {
						List<Integer> newQueue = new ArrayList<>();

						for (int u : queue) {
							cnt++;
							for (int v : adj.get(u)) {
								if (dist[v] <= K || dist[v] <= K + 1 && cycle[v]) {
									newQueue.add(v);
								}
							}
						}
						queue = newQueue;
					}
				}
			}

			// pw.println(Arrays.toString(dist));

			String output = String.format("Case #%d: %d", t, N - cnt);
			pw.println(output);
		}
		pw.flush();
		pw.close();
		br.close();
	}
}