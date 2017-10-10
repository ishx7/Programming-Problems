import java.io.*;
import java.util.*;

/**
 * Problem is here: http://www.spoj.com/problems/UCV2013A/
 *
 * I'm not even passing one of the samples here... :(
 */
public class FTB9 {
    private static final long MOD = 1000000007;

    /**
     * Computes base^exp modulo MOD
     *
     * I'm willing to bet my bug is in here...
     */
    public static long fastExp(long base, long exp) {
        long ans = 1;
        long basePower = base; // base^k
        for (long k = 1; k <= exp; k <<= 1) {
            if ((exp & k) > 0) {
                ans *= basePower;
                ans %= MOD;
            }

            basePower *= basePower;
            basePower %= MOD;
        }

        return ans;
    }

    /**
     * Compute the inverse of a mod MOD
     */
    public static long inverse(long a) {
        return fastExp(a, MOD - 2);
    }

    public static long solve(long N, long L) {
        // Answer is N^1 + N^2 + N^3 + ... + N^L
        // Sum               = N^1 + N^2 + N^3 + ... + N^L
        // Sum * N           = N^2 + N^3 + N^4 + ... + N^(L + 1)
        // Sum * N + N       = N + N^2 + N^3 + N^4 + ... + N^(L + 1)
        // Sum * N + N       = Sum + N^(L + 1)
        // Sum * (N - 1) + N = N^(L + 1)
        // Sum               = (N^(L + 1) - N) / (N - 1)

        long numerator = fastExp(N, L + 1) - N;
        // Any time a value can be negative in a mod problem, fix it ASAP
        // For example 3^4 (mod 10) = 1. Subtract 3 to get -2.
        numerator += MOD;
        numerator %= MOD;

        long denominatorInv = inverse(N - 1);

        return (numerator * denominatorInv) % MOD;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int N = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            if (N == 0 && L == 0) {
                break;
            }

            System.out.println(solve(N, L));
        }
    }
}
