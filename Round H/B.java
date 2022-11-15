import java.util.*;
import java.io.*;

public class B {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			int L = Integer.parseInt(br.readLine());

            Vertex current = new Vertex(0, 0, 0);

			int ans = 0;
            PriorityQueue<Vertex> pq = new PriorityQueue<>();
            pq.add(current);

            while (current.l != L) {
                current = pq.poll();
                for (Vertex u : adj(current)) {
                    pq.add(u);
                }
            }
            ans = current.c;

			pw.println(String.format("Case #%d: %d", t, ans));
			
		}
		pw.flush();
		pw.close();
		br.close();
	}
    static List<Vertex> adj(Vertex v) {
        List<Vertex> adj = new ArrayList<>();

        adj.add(new Vertex(v.l + 1, v.r, v.c + 1));
        adj.add(new Vertex(v.l, v.l, v.c + 4));
        adj.add(new Vertex(v.l + v.r, v.r, v.c + 2));

        return adj;
    }
}

class Vertex implements Comparable<Vertex> {
    int l, r, c;

    public Vertex(int l, int r, int c) {
        this.l = l;
        this.r = r;
        this.c = c;
    }

    public int compareTo(Vertex other) {
        return this.c - other.c;
    }
}