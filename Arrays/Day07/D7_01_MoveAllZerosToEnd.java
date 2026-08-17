package Day07;

/*
 * ============================================================
 * Day 07 - Problem 01
 * Move All Zeros to the End of the Array
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array, move all zeros to the end of the
 * array while maintaining the relative order of all non-zero
 * elements.
 *
 * The operation must be performed in-place.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 *
 * Input:
 * [1, 0, 2, 3, 0, 4, 0, 1]
 *
 * Output:
 * [1, 2, 3, 4, 1, 0, 0, 0]
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [1, 2, 0, 1, 0, 4, 0]
 *
 * Output:
 * [1, 2, 1, 4, 0, 0, 0]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Create a temporary array and first store all non-zero
 * elements in it while maintaining their original order.
 *
 * After placing all non-zero elements, fill the remaining
 * positions with zeros.
 *
 * Algorithm:
 *
 * 1. Create a temporary array of the same size.
 * 2. Traverse the original array.
 * 3. Copy every non-zero element into the temporary array.
 * 4. Fill the remaining positions with zero.
 * 5. Copy the result back to the original array if an
 *    in-place final array is required.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Drawback:
 * Requires an additional array.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - TWO POINTERS
 *
 * Approach:
 * Use two pointers to rearrange the array in-place.
 *
 * j -> Points to the position of the first zero that needs
 *      to be replaced.
 *
 * i -> Searches for the next non-zero element.
 *
 * Whenever a non-zero element is found, swap it with the zero
 * at position j.
 *
 * ============================================================
 *
 * Algorithm:
 *
 * 1. Find the first zero in the array and store its index
 *    in j.
 *
 * 2. If there is no zero, the array is already valid.
 *
 * 3. Start i from j + 1.
 *
 * 4. Traverse the remaining array.
 *
 * 5. If nums[i] is non-zero:
 *
 *      Swap nums[i] with nums[j].
 *
 * 6. Increment j.
 *
 * 7. Continue until the end of the array.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * [1, 0, 2, 3, 0, 4, 0, 1]
 *
 * First zero:
 *
 * j = 1
 *
 * i = 2:
 * nums[i] = 2
 *
 * Swap:
 * [1, 2, 0, 3, 0, 4, 0, 1]
 *
 * j = 2
 *
 * i = 3:
 * nums[i] = 3
 *
 * Swap:
 * [1, 2, 3, 0, 0, 4, 0, 1]
 *
 * j = 3
 *
 * i = 4:
 * nums[i] = 0
 *
 * Skip.
 *
 * i = 5:
 * nums[i] = 4
 *
 * Swap:
 * [1, 2, 3, 4, 0, 0, 0, 1]
 *
 * j = 4
 *
 * i = 6:
 * nums[i] = 0
 *
 * Skip.
 *
 * i = 7:
 * nums[i] = 1
 *
 * Swap:
 * [1, 2, 3, 4, 1, 0, 0, 0]
 *
 * Final result:
 * [1, 2, 3, 4, 1, 0, 0, 0]
 *
 * ============================================================
 *
 * WHY DOES THE ORDER REMAIN THE SAME?
 *
 * The scanning pointer i always moves from left to right.
 *
 * Therefore, non-zero elements are moved to the next available
 * position in the same order in which they appear.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * Each element is visited at most once after finding the first
 * zero.
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * Only pointer variables and a temporary variable for swapping
 * are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Two-pointer technique
 * - In-place array modification
 * - Swapping elements
 * - Maintaining relative order
 * - Minimizing unnecessary operations
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D7_01_MoveAllZerosToEnd {

    public static void main(String[] args) {

        int[] nums = {1, 0, 2, 3, 0, 4, 0, 1};

        moveZeroes(nums);

        System.out.print("Array after moving zeros: ");

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void moveZeroes(int[] nums) {

        int j = -1;

        // Find the first zero
        for (int i = 0; i < nums.length; i++) {

            if (nums[i] == 0) {
                j = i;
                break;
            }
        }

        // No zero found
        if (j == -1) {
            return;
        }

        // Find non-zero elements and swap
        for (int i = j + 1; i < nums.length; i++) {

            if (nums[i] != 0) {

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                j++;
            }
        }
    }
}