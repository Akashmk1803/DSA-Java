package Day08;

/*
 * ============================================================
 * Day 08 - Problem 01
 * Linear Search
 * ============================================================
 *
 * Problem Statement:
 * Given an array and an element num, find whether num is
 * present in the array.
 *
 * If num is present, return its index.
 * If num is not present, return -1.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 *
 * Input:
 * arr = [1, 2, 3, 4, 5]
 * num = 3
 *
 * Output:
 * 2
 *
 * Explanation:
 * Element 3 is present at index 2.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * arr = [5, 4, 3, 2, 1]
 * num = 5
 *
 * Output:
 * 0
 *
 * Explanation:
 * Element 5 is present at index 0.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 *
 * Input:
 * arr = [1, 2, 3, 4, 5]
 * num = 8
 *
 * Output:
 * -1
 *
 * Explanation:
 * Element 8 is not present in the array.
 *
 * ============================================================
 *
 * APPROACH - LINEAR SEARCH
 *
 * Approach:
 * Traverse the array from left to right and compare every
 * element with the target element.
 *
 * Since no sorting is required, the elements are checked
 * sequentially.
 *
 * Algorithm:
 *
 * 1. Start from index 0.
 *
 * 2. Traverse the array until the last element.
 *
 * 3. For every index i, check:
 *
 *      arr[i] == num
 *
 * 4. If the condition is true, return i.
 *
 * 5. If the complete array is traversed without finding num,
 *    return -1.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * arr = [1, 2, 3, 4, 5]
 * num = 3
 *
 * i = 0:
 * arr[0] = 1
 * 1 == 3 -> false
 *
 * i = 1:
 * arr[1] = 2
 * 2 == 3 -> false
 *
 * i = 2:
 * arr[2] = 3
 * 3 == 3 -> true
 *
 * Return 2.
 *
 * ============================================================
 *
 * BEST CASE:
 * O(1)
 *
 * If the target is found at the first index.
 *
 * WORST CASE:
 * O(n)
 *
 * If the target is at the last index or is not present.
 *
 * AVERAGE CASE:
 * O(n)
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only a constant amount of extra space is used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Linear traversal
 * - Searching an array
 * - Array indexing
 * - Early return
 * - Handling element-not-found cases
 * - Best, average and worst-case complexity
 *
 * ============================================================
 */

public class D8_01_LinearSearch {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int num = 3;

        int index = linearSearch(arr, num);

        System.out.println("Element found at index: " + index);
    }

    public static int linearSearch(int[] arr, int num) {

        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == num) {
                return i;
            }
        }

        return -1;
    }
}