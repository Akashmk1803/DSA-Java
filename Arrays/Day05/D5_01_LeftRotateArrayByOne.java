package Day05;

/*
 * ============================================================
 * Day 05 - Problem 01
 * Left Rotate the Array by One
 * ============================================================
 *
 * Problem Statement:
 * Given an integer array, rotate the array to the left by one
 * position.
 *
 * The array must be modified in-place. There is no need to
 * return anything.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 * Input  : [1, 2, 3, 4, 5]
 * Output : [2, 3, 4, 5, 1]
 *
 * Explanation:
 * The first element 1 is moved to the last position and all
 * remaining elements are shifted one position to the left.
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 * Input  : [-1, 0, 3, 6]
 * Output : [0, 3, 6, -1]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Create a temporary array of the same size and store the
 * rotated elements in it.
 *
 * Algorithm:
 *
 * 1. Create a temporary array of size n.
 * 2. Copy elements from index 1 onward to the previous index
 *    in the temporary array.
 * 3. Store the first element of the original array at the
 *    last position of the temporary array.
 * 4. The temporary array now contains the rotated array.
 *
 * Example:
 *
 * Original:
 * [1, 2, 3, 4, 5]
 *
 * Temporary:
 * [2, 3, 4, 5, 1]
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Drawback:
 * An additional array is used even though the rotation can
 * be performed directly on the original array.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Approach:
 * Perform the rotation directly on the original array.
 *
 * Since the first element will be moved to the last position,
 * first store it in a temporary variable.
 *
 * Then shift every remaining element one position to the left.
 *
 * Finally, place the stored first element at the last index.
 *
 * Algorithm:
 *
 * 1. Store the first element in a variable called first.
 *
 * 2. Traverse the array from index 1 to n - 1.
 *
 * 3. Move each element one position to the left:
 *
 *    arr[i - 1] = arr[i]
 *
 * 4. Place the stored first element at the last position:
 *
 *    arr[n - 1] = first
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * [1, 2, 3, 4, 5]
 *
 * Step 1:
 * first = 1
 *
 * Step 2:
 * i = 1
 * arr[0] = arr[1]
 * [2, 2, 3, 4, 5]
 *
 * Step 3:
 * i = 2
 * arr[1] = arr[2]
 * [2, 3, 3, 4, 5]
 *
 * Step 4:
 * i = 3
 * arr[2] = arr[3]
 * [2, 3, 4, 4, 5]
 *
 * Step 5:
 * i = 4
 * arr[3] = arr[4]
 * [2, 3, 4, 5, 5]
 *
 * Step 6:
 * arr[n - 1] = first
 *
 * [2, 3, 4, 5, 1]
 *
 * ============================================================
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
 *
 * - In-place array modification
 * - Array traversal
 * - Shifting elements
 * - Using a temporary variable to preserve data
 * - Understanding O(n) time and O(1) space
 *
 * ============================================================
 */

public class D5_01_LeftRotateArrayByOne {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5};

        leftRotateByOne(nums);

        System.out.print("Array after left rotation: ");

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void leftRotateByOne(int[] nums) {

        int first = nums[0];

        for (int i = 1; i < nums.length; i++) {
            nums[i - 1] = nums[i];
        }

        nums[nums.length - 1] = first;
    }
}