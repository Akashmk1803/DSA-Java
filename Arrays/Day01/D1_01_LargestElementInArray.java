package Day01;

/*
 * ============================================================
 * Day 01 - Problem 01
 * Largest Element in an Array
 * ============================================================
 *
 * Problem Statement:
 * Given an array of integers, find the largest element
 * present in the array.
 *
 * Examples:
 *
 * Example 1:
 * Input  : [2, 5, 1, 3, 0]
 * Output : 5
 *
 * Example 2:
 * Input  : [8, 10, 5, 7, 9]
 * Output : 10
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Sort the array in ascending order. The last element
 * will be the largest element.
 *
 * Algorithm:
 * 1. Sort the array using Arrays.sort().
 * 2. Return the last element of the sorted array.
 *
 * Time Complexity:
 * O(n log n)
 *
 * Space Complexity:
 * Depends on the sorting implementation.
 *
 * Drawback:
 * Sorting the entire array is unnecessary because we only
 * need to find the largest element.
 *
 * -----------------------------------------------------------
 *
 * Why Optimal?
 *
 * The brute force approach sorts the entire array, which takes
 * O(n log n) time. However, sorting is unnecessary because we
 * only need to find the largest element.
 *
 * We can find the largest element by traversing the array once,
 * reducing the time complexity from O(n log n) to O(n).
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Approach:
 * Traverse the array once while maintaining the largest
 * element found so far.
 *
 * Algorithm:
 * 1. Assume the first element is the largest.
 * 2. Traverse the remaining elements.
 * 3. Compare each element with the current largest.
 * 4. If the current element is greater, update largest.
 * 5. Return the largest element.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * ============================================================
 *
 * Key Learning:
 * - Array traversal
 * - Maintaining a running maximum
 * - Comparing brute force and optimal approaches
 * - Time and space complexity
 *
 * ============================================================
 */

public class D1_01_LargestElementInArray {

    public static void main(String[] args) {

        int[] arr = {3, 5, 7, 2, 8, 1, 9};

        int largest = findLargestElement(arr);

        System.out.println(
                "The largest element in the array is: " + largest
        );
    }

    public static int findLargestElement(int[] arr) {

        int largest = arr[0];

        for (int i = 1; i < arr.length; i++) {

            if (arr[i] > largest) {
                largest = arr[i];
            }
        }

        return largest;
    }
}