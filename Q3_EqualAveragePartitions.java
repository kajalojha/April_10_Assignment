package April_10_Assignment;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Q3_EqualAveragePartitions {
    static class Pair {
        List<Integer> A;
        List<Integer> B;

        Pair(List<Integer> A, List<Integer> B) {
            this.A = A;
            this.B = B;
        }
    }

    public static Pair equalAveragePartitions(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int num : A) {
            sum += num;
        }

        // dp[i][j] indicates if a sum of j can be achieved using first i elements
        boolean[][] dp = new boolean[n + 1][sum + 1];
        dp[0][0] = true;

        // Calculate all possible subset sums
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j >= A[i - 1]) {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - A[i - 1]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        Pair result = new Pair(null, null);
        int minLen = Integer.MAX_VALUE;

        // Iterate through all possible sums to find one which is half of the total sum
        for (int i = 1; i < sum; i++) {
            if (dp[n][i]) {
                int sumA = i;
                int sumB = sum - i;
                List<Integer> AList = getSubset(A, sumA);
                List<Integer> BList = getRemainingSubset(A, AList);
                if (AList.size() <= BList.size() && AList.size() < minLen) {
                    result.A = AList;
                    result.B = BList;
                    minLen = AList.size();
                }
            }
        }

        return result;
    }

    // Helper method to get the subset with given sum
    private static List<Integer> getSubset(int[] A, int sum) {
        List<Integer> subset = new ArrayList<>();
        int n = A.length;
        for (int i = n - 1; i >= 0; i--) {
            if (sum >= A[i]) {
                subset.add(A[i]);
                sum -= A[i];
            }
        }
        return subset;
    }

    // Helper method to get the remaining subset
    private static List<Integer> getRemainingSubset(int[] A, List<Integer> subset) {
        List<Integer> remainingSubset = new ArrayList<>();
        int i = 0, j = 0;
        while (i < A.length && j < subset.size()) {
            if (A[i] != subset.get(j)) {
                remainingSubset.add(A[i]);
            } else {
                j++;
            }
            i++;
        }
        while (i < A.length) {
            remainingSubset.add(A[i]);
            i++;
        }
        return remainingSubset;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the number of elements in the array
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        // Input the elements of the array
        int[] A = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        // Find partitions with equal averages
        Pair result = equalAveragePartitions(A);

        // Display the result
        if (result.A == null) {
            System.out.println("No solution exists.");
        } else {
            System.out.println("Solution exists:");
            System.out.println("Partition A: " + result.A);
            System.out.println("Partition B: " + result.B);
        }
    }
}
