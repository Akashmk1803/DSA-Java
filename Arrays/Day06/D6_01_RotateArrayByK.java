package Day06;

/*
 * ============================================================
 * Day 06 - Problem 01
 * Rotate Array by K Elements
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array, rotate the array by k positions
 * either to the left or to the right.
 *
 * The rotation must be performed in-place.
 *
 * ============================================================
 *
 * EXAMPLE 1 - RIGHT ROTATION
 *
 * Input:
 * nums = [1, 2, 3, 4, 5, 6, 7]
 * k = 2
 *
 * Output:
 * [6, 7, 1, 2, 3, 4, 5]
 *
 * Explanation:
 *
 * Rotate 1 step:
 * [7, 1, 2, 3, 4, 5, 6]
 *
 * Rotate 2 steps:
 * [6, 7, 1, 2, 3, 4, 5]
 *
 * ============================================================
 *
 * EXAMPLE 2 - LEFT ROTATION
 *
 * Input:
 * nums = [1, 2, 3, 4, 5, 6]
 * k = 2
 *
 * Output:
 * [3, 4, 5, 6, 1, 2]
 *
 * Explanation:
 *
 * Rotate 1 step:
 * [2, 3, 4, 5, 6, 1]
 *
 * Rotate 2 steps:
 * [3, 4, 5, 6, 1, 2]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Rotate the array by one position repeatedly for k times.
 *
 * For every rotation, shift the elements by one position and
 * move the element that goes outside the array to the other
 * end.
 *
 * Time Complexity:
 * O(n * k)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * If k is large, the array is traversed multiple times.
 *
 * ============================================================
 *
 * BETTER APPROACH
 *
 * Approach:
 * Use a temporary array to store the rotated elements.
 *
 * For right rotation:
 * - The last k elements move to the beginning.
 * - The remaining elements move after them.
 *
 * For left rotation:
 * - The first k elements move to the end.
 * - The remaining elements move to the beginning.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Drawback:
 * Requires an additional array, so the solution is not
 * completely in-place.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - REVERSAL ALGORITHM
 *
 * RIGHT ROTATION:
 *
 * For right rotation by k:
 *
 * 1. Reverse the entire array.
 * 2. Reverse the first k elements.
 * 3. Reverse the remaining n-k elements.
 *
 * Example:
 *
 * Input:
 * [1, 2, 3, 4, 5, 6, 7]
 * k = 3
 *
 * Step 1 - Reverse entire array:
 * [7, 6, 5, 4, 3, 2, 1]
 *
 * Step 2 - Reverse first 3 elements:
 * [5, 6, 7, 4, 3, 2, 1]
 *
 * Step 3 - Reverse remaining elements:
 * [5, 6, 7, 1, 2, 3, 4]
 *
 * Final result:
 * [5, 6, 7, 1, 2, 3, 4]
 *
 * ============================================================
 *
 * LEFT ROTATION:
 *
 * For left rotation by k:
 *
 * 1. Reverse the first k elements.
 * 2. Reverse the remaining n-k elements.
 * 3. Reverse the entire array.
 *
 * Example:
 *
 * Input:
 * [1, 2, 3, 4, 5, 6]
 * k = 2
 *
 * Step 1 - Reverse first 2 elements:
 * [2, 1, 3, 4, 5, 6]
 *
 * Step 2 - Reverse remaining elements:
 * [2, 1, 6, 5, 4, 3]
 *
 * Step 3 - Reverse entire array:
 * [3, 4, 5, 6, 1, 2]
 *
 * Final result:
 * [3, 4, 5, 6, 1, 2]
 *
 * ============================================================
 *
 * WHY k % n?
 *
 * If k is greater than the length of the array, performing
 * more than n rotations repeats the same arrangement.
 *
 * Therefore:
 *
 * k = k % n
 *
 * Example:
 *
 * n = 5
 * k = 7
 *
 * 7 % 5 = 2
 *
 * Rotating 7 times is equivalent to rotating 2 times.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * We perform a constant number of array reversals.
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only a few variables are used for swapping elements.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Array rotation
 * - Left and right rotation
 * - In-place modification
 * - Reversal algorithm
 * - Modulo operator
 * - Two-pointer reversal
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D6_01_RotateArrayByK {

    public static void main(String[] args) {

        // Right rotation example
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k1 = 3;

        rotateRight(nums, k1);

        System.out.print("Array after right rotation: ");

        for (int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();

        // Left rotation example
        int[] nums2 = {1, 2, 3, 4, 5, 6};
        int k2 = 2;

        rotateLeft(nums2, k2);

        System.out.print("Array after left rotation: ");

        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }

    /*
     * ========================================================
     * RIGHT ROTATION
     * ========================================================
     */
    public static void rotateRight(int[] nums, int k) {

        int n = nums.length;

        k = k % n;

        // Reverse the entire array
        reverse(nums, 0, n - 1);

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements
        reverse(nums, k, n - 1);
    }

    /*
     * ========================================================
     * LEFT ROTATION
     * ========================================================
     */
    public static void rotateLeft(int[] nums, int k) {

        int n = nums.length;

        k = k % n;

        // Reverse the first k elements
        reverse(nums, 0, k - 1);

        // Reverse the remaining elements
        reverse(nums, k, n - 1);

        // Reverse the entire array
        reverse(nums, 0, n - 1);
    }

    /*
     * ========================================================
     * REVERSE ARRAY
     * ========================================================
     */
    public static void reverse(int[] nums, int left, int right) {

        while (left < right) {

            int temp = nums[left];

            nums[left] = nums[right];
            nums[right] = temp;

            left++;
            right--;
        }
    }
}