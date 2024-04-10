package April_10_Assignment;
import java.util.Arrays;
import java.util.Scanner;

public class Q2_HorseStables {
    public static int arrangeHorses(String horses, int K) {
        int n = horses.length();

        // DP array to store the minimum cost of accommodating horses
        int[][] dp = new int[K + 1][n + 1];

        // Initialize DP array
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        // Calculate the cost for each stable and position
        for (int k = 1; k <= K; k++) {
            for (int i = 1; i <= n; i++) {
                int white = 0;
                int black = 0;
                for (int j = i; j >= 1; j--) {
                    if (horses.charAt(j - 1) == 'W') {
                        white++;
                    } else {
                        black++;
                    }
                    dp[k][i] = Math.min(dp[k][i], dp[k - 1][j - 1] + white * black);
                }
            }
        }

        // Find the minimum cost among all possible accommodations
        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            minCost = Math.min(minCost, dp[K][i]);
        }

        return minCost == Integer.MAX_VALUE ? -1 : minCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the sequence of black and white horses: ");
        String horses = scanner.next();

        System.out.print("Enter the number of stables (K): ");
        int K = scanner.nextInt();

        int result = arrangeHorses(horses, K);
        System.out.println("Minimum cost: " + (result == -1 ? "Not possible" : result));
    }

}
