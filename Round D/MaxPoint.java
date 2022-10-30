import java.util.*;
import java.io.*;

public class MaxPoint {
    public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter pw = new PrintWriter(System.out);
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] A = new int[N];
            long[] prefixA = new long[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
                prefixA[i + 1] = prefixA[i] + A[i];
            }
            int M = Integer.parseInt(br.readLine());
            int[] B = new int[M];
            long[] prefixB = new long[M + 1];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                B[j] = Integer.parseInt(st.nextToken());
                prefixB[j + 1] = prefixB[j] + B[j];
            }
            int K = Integer.parseInt(br.readLine());

            long score = 0;

            for (int k1 = 0; k1 <= M + N - K; k1++) {
                int k2 = M + N - K - k1;

                long currentScore = prefixA[N] + prefixB[M] - (getMin(prefixA, k1) + getMin(prefixB, k2));
                score = Math.max(score, currentScore);
            }

            pw.println(String.format("Case #%d: %d", t, score));
		}
		pw.flush();
		pw.close();
		br.close();
	}
    static long getMin(long[] arr, int k) {
        long minSum = Long.MAX_VALUE;

        for (int i = 0; i + k < arr.length; i++) {
            minSum = Math.min(minSum, arr[i + k] - arr[i]);
        }
        return minSum;
    }
}