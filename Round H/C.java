import java.util.*;
import java.io.*;

public class C {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
            List<Set<Integer>> adj = new ArrayList<>();
            adj.add(new HashSet<>());

            for (int i = 1; i <= N; i++) {
                adj.add(new HashSet<>());
                A[i] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < N - 1; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            int[] num = new int[N + 1];

            PriorityQueue<Vertex> pq = new PriorityQueue<>();

            for (int i = 1; i <= N; i++) {
                pq.add(new Vertex(i, A[i]));
            }

            while (! pq.isEmpty()) {
                int v = pq.poll().v;
                num[v] = 1;

                for (int u : adj.get(v)) {
                    if (A[u] < A[v]) {
                        num[v] += num[u];
                    }
                }
            }

			int ans = 0;

            for (int n : num) {
                ans = Math.max(ans, n);
            }

			pw.println(String.format("Case #%d: %d", t, ans));
			
		}
		pw.flush();
		pw.close();
		br.close();
	}
}

class Vertex implements Comparable<Vertex> {
    int v;
    int cap;

    public Vertex(int v, int cap) {
        this.v = v;
        this.cap = cap;
    }

    public int compareTo(Vertex other) {
        return this.cap - other.cap;
    }
}