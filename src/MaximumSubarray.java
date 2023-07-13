import java.util.Arrays;

public class MaximumSubarray {

    // Counters for operations
    private static long bruteForceOperations = 0;
    private static long recursiveOperations = 0;

    // Brute force method
    public static int[] findMaxSubarrayBruteForce(int[] A) {
        int n = A.length;
        int left = 0;
        int right = 0;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                bruteForceOperations++; // Count each comparison operation
                currentSum += A[j];
                if (currentSum > maxSum) {
                    maxSum = currentSum;
                    left = i;
                    right = j;
                }
            }
        }

        return Arrays.copyOfRange(A, left, right + 1);
    }

    // Recursive method
    public static int[] findMaxSubarrayRecursive(int[] A) {
        recursiveOperations++; // Count each recursive call
        return findMaxSubarrayRecursiveHelper(A, 0, A.length - 1);
    }

    private static int[] findMaxSubarrayRecursiveHelper(int[] A, int low, int high) {
        if (low == high) {
            return new int[] { A[low] };
        }

        int mid = (low + high) / 2;

        int[] leftSubarray = findMaxSubarrayRecursiveHelper(A, low, mid);
        int[] rightSubarray = findMaxSubarrayRecursiveHelper(A, mid + 1, high);
        int[] crossingSubarray = findMaxCrossingSubarray(A, low, mid, high);

        if (getSum(leftSubarray) >= getSum(rightSubarray) && getSum(leftSubarray) >= getSum(crossingSubarray)) {
            return leftSubarray;
        } else if (getSum(rightSubarray) >= getSum(leftSubarray) && getSum(rightSubarray) >= getSum(crossingSubarray)) {
            return rightSubarray;
        } else {
            return crossingSubarray;
        }
    }

    private static int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = mid;

        for (int i = mid; i >= low; i--) {
            sum += A[i];
            recursiveOperations++; // Count each comparison operation
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = mid + 1;

        for (int j = mid + 1; j <= high; j++) {
            sum += A[j];
            recursiveOperations++; // Count each comparison operation
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return Arrays.copyOfRange(A, maxLeft, maxRight + 1);
    }

    private static int getSum(int[] subarray) {
        int sum = 0;
        for (int num : subarray) {
            sum += num;
            bruteForceOperations++; // Count each addition operation
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
        final int NUM_ITERATIONS = 2000;

        // Reset operation counters
        bruteForceOperations = 0;
        recursiveOperations = 0;

        // Brute force method
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            findMaxSubarrayBruteForce(arr);
        }
        long bruteForceCount = bruteForceOperations / NUM_ITERATIONS;

        System.out.println("Brute Force Method:");
        System.out.println("Average operation count: " + bruteForceCount);

        // Reset operation counters
        bruteForceOperations = 0;
        recursiveOperations = 0;

        // Recursive method
        for (int i = 0; i < NUM_ITERATIONS; i++) {
            findMaxSubarrayRecursive(arr);
        }
        long recursiveCount = recursiveOperations / NUM_ITERATIONS;

        System.out.println("\nRecursive Method:");
        System.out.println("Average operation count: " + recursiveCount);
    }
}
