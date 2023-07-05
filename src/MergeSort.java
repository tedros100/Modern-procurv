import java.util.Arrays;

public class MergeSort {

    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Base case: the array is already sorted or empty
        }

        // Divide the array into two halves
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        System.arraycopy(arr, 0, left, 0, left.length);
        System.arraycopy(arr, mid, right, 0, right.length);

        // Recursively sort each half
        mergeSort(left);
        mergeSort(right);

        // Merge the sorted halves
        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        // Compare elements from left and right subarrays and merge them into the original array
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        // Copy the remaining elements from the left subarray (if any)
        while (i < left.length) {
            arr[k++] = left[i++];
        }

        // Copy the remaining elements from the right subarray (if any)
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        int[] arr = {8, 3, 5, 1, 9, 2, 7, 4};

        System.out.println("Original array: " + Arrays.toString(arr));

        mergeSort(arr);

        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}

