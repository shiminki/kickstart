import java.util.*;
import java.io.*;

public class MinDist {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<Integer> S = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                int s = Integer.parseInt(st.nextToken());

                if (S.size() == 0 || S.get(S.size() - 1) != s) {
                    S.add(s);
                }
            }
            int M = Integer.parseInt(br.readLine());
            Map<Integer, List<Integer>> K = new HashMap<>();
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int k = Integer.parseInt(st.nextToken());

                if (!K.containsKey(k)) {
                    K.put(k, new ArrayList<>());
                }
                K.get(k).add(j);
            }

            List<List<Integer>> V = new ArrayList<>();
            List<List<Long>> dist = new ArrayList<>();
            List<List<Boolean>> visited = new ArrayList<>();

            V.add(Arrays.asList(-1));
            dist.add(Arrays.asList((long) 0));
            visited.add(Arrays.asList(false));

            for (int s : S) {
                V.add(K.get(s));
                List<Long> d = new ArrayList<>();
                List<Boolean> v = new ArrayList<>();
                for (int i = 0; i < K.get(s).size(); i++) {
                    d.add(Long.MAX_VALUE);
                    v.add(false);
                }
                dist.add(d);
                visited.add(v);
            }
            V.add(Arrays.asList(-1));
            dist.add(Arrays.asList(Long.MAX_VALUE));
            visited.add(Arrays.asList(false));

            int L = V.size();

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(0, 0, 0));

            // System.out.println(V);

            while (!visited.get(L - 1).get(0)) {
                Node node = pq.poll();

                if (visited.get(node.level).get(node.idx)) {
                    continue;
                } else {
                    visited.get(node.level).set(node.idx, true);
                    dist.get(node.level).set(node.idx, node.distance);

                    if (node.level + 1 < L) {
                        for (int i = 0; i < V.get(node.level + 1).size(); i++) {
                            if (!visited.get(node.level + 1).get(i)) {
                                int d = 0;

                                if (V.get(node.level).get(node.idx) != -1 && V.get(node.level + 1).get(i) != -1) {
                                    d = Math.abs(V.get(node.level + 1).get(i) - V.get(node.level).get(node.idx));
                                }
                                pq.add(new Node(node.level + 1, i, node.distance + d));
                            }
                        }
                    }
                }
            }

            long ans = dist.get(L - 1).get(0);
            // System.out.println(dist);

            String output = String.format("Case #%d: %d", t, ans);
            pw.println(output);
        }
        pw.flush();
        pw.close();
        br.close();
    }
}

class Node implements Comparable<Node> {
    int level, idx;
    long distance;

    public Node(int level, int idx, long distance) {
        this.level = level;
        this.idx = idx;
        this.distance = distance;
    }

    public int compareTo(Node other) {
        long a = this.distance - other.distance;

        if (a > 0) {
            return 1;
        } else if (a == 0) {
            return 0;
        } else {
            return -1;
        }
    }

    public String toString() {
        return String.format("%d %d %d", level, idx, distance);
    }
}