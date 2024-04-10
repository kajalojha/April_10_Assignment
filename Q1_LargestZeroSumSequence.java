package April_10_Assignment;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Q1_LargestZeroSumSequence {
    public static List<Integer> largestZeroSumSequence(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        int sum = 0;
        int maxLength = 0;
        int endIndex = -1;

        map.put(0, -1); // Initialize the map with 0 sum at index -1

        for (int i = 0; i < A.length; i++) {
            sum += A[i];

            if (map.containsKey(sum)) {
                int startIndex = map.get(sum) + 1;
                int length = i - startIndex + 1;

                if (length > maxLength) {
                    maxLength = length;
                    endIndex = i;
                }
            } else {
                map.put(sum, i);
            }
        }

        if (endIndex != -1) {
            int startIndex = endIndex - maxLength + 1;
            for (int i = startIndex; i <= endIndex; i++) {
                result.add(A[i]);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        int[] A = new int[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }

        List<Integer> sequence = largestZeroSumSequence(A);
        System.out.println("Largest continuous sequence with total sum of zero:");
        System.out.println(sequence);
    }
}
