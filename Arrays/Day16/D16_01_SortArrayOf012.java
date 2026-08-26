package Day16;

/*
 * ============================================================
 * Day 16 - Problem 01
 * Sort an Array of 0s, 1s and 2s
 * ============================================================
 *
 * Problem Statement:
 * Given an array nums consisting only of 0, 1 and 2, sort
 * the array in non-decreasing order.
 *
 * The sorting must be done in-place without creating a copy
 * of the original array.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [1, 0, 2, 1, 0]
 *
 * Output:
 * [0, 0, 1, 1, 2]
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [0, 0, 1, 1, 1]
 *
 * Output:
 * [0, 0, 1, 1, 1]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH - SORTING
 *
 * Approach:
 * Use the built-in sorting algorithm to sort the array.
 *
 * Example:
 *
 * Arrays.sort(nums);
 *
 * Time Complexity:
 * O(n log n)
 *
 * Space Complexity:
 * Depends on the sorting implementation.
 *
 * Drawback:
 * We can do better because the array contains only 0, 1 and 2.
 *
 * ============================================================
 *
 * BETTER APPROACH - COUNTING
 *
 * Approach:
 * Count the number of 0s, 1s and 2s.
 *
 * Then overwrite the array:
 *
 * First  -> all 0s
 * Next   -> all 1s
 * Last   -> all 2s
 *
 * Example:
 *
 * Input:
 * [1, 0, 2, 1, 0]
 *
 * Counts:
 *
 * 0 -> 2
 * 1 -> 2
 * 2 -> 1
 *
 * Result:
 *
 * [0, 0, 1, 1, 2]
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 * Dutch National Flag Algorithm
 *
 * Approach:
 * Use three pointers:
 *
 * low
 * mid
 * high
 *
 * The array is divided into four regions:
 *
 * [0 ... low-1]       -> 0s
 * [low ... mid-1]     -> 1s
 * [mid ... high]      -> Unknown elements
 * [high+1 ... n-1]    -> 2s
 *
 * ============================================================
 *
 * INITIALIZATION:
 *
 * low = 0
 * mid = 0
 * high = nums.length - 1
 *
 * ============================================================
 *
 * ALGORITHM:
 *
 * While mid <= high:
 *
 * CASE 1:
 *
 * nums[mid] == 0
 *
 * Swap nums[low] and nums[mid].
 *
 * Then:
 *
 * low++
 * mid++
 *
 * Reason:
 * The 0 belongs on the left side.
 *
 * ------------------------------------------------------------
 *
 * CASE 2:
 *
 * nums[mid] == 1
 *
 * Just move:
 *
 * mid++
 *
 * Reason:
 * 1 already belongs in the middle region.
 *
 * ------------------------------------------------------------
 *
 * CASE 3:
 *
 * nums[mid] == 2
 *
 * Swap nums[mid] and nums[high].
 *
 * Then:
 *
 * high--
 *
 * IMPORTANT:
 * Do NOT increment mid.
 *
 * Reason:
 * The element coming from the high position has not yet
 * been checked.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [1, 0, 2, 1, 0]
 *
 * Initially:
 *
 * low = 0
 * mid = 0
 * high = 4
 *
 * ------------------------------------------------------------
 *
 * Step 1:
 *
 * nums[mid] = 1
 *
 * 1 belongs in the middle.
 *
 * mid++
 *
 * Array:
 * [1, 0, 2, 1, 0]
 *
 * low = 0
 * mid = 1
 * high = 4
 *
 * ------------------------------------------------------------
 *
 * Step 2:
 *
 * nums[mid] = 0
 *
 * Swap nums[low] and nums[mid].
 *
 * Array:
 * [0, 1, 2, 1, 0]
 *
 * low++
 * mid++
 *
 * low = 1
 * mid = 2
 * high = 4
 *
 * ------------------------------------------------------------
 *
 * Step 3:
 *
 * nums[mid] = 2
 *
 * Swap nums[mid] and nums[high].
 *
 * Array:
 * [0, 1, 0, 1, 2]
 *
 * high--
 *
 * low = 1
 * mid = 2
 * high = 3
 *
 * Do NOT increment mid.
 *
 * ------------------------------------------------------------
 *
 * Step 4:
 *
 * nums[mid] = 0
 *
 * Swap nums[low] and nums[mid].
 *
 * Array:
 * [0, 0, 1, 1, 2]
 *
 * low++
 * mid++
 *
 * low = 2
 * mid = 3
 * high = 3
 *
 * ------------------------------------------------------------
 *
 * Step 5:
 *
 * nums[mid] = 1
 *
 * mid++
 *
 * mid = 4
 *
 * Now:
 *
 * mid > high
 *
 * Stop.
 *
 * Final array:
 *
 * [0, 0, 1, 1, 2]
 *
 * ============================================================
 *
 * WHY DON'T WE INCREMENT MID WHEN nums[mid] == 2?
 *
 * Suppose:
 *
 * nums[mid] = 2
 *
 * We swap it with nums[high].
 *
 * The new element that comes into nums[mid] could be:
 *
 * 0
 * 1
 * or
 * 2
 *
 * We haven't checked it yet.
 *
 * Therefore, mid must remain at the same position so that
 * the new element can be processed.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * Each element is processed at most once.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * The array is modified in-place and only a few variables
 * are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Three-pointer technique
 * - Dutch National Flag Algorithm
 * - In-place array manipulation
 * - Swapping elements
 * - Maintaining array regions
 * - O(n) time
 * - O(1) space
 *
 * ============================================================
 */

public class D16_01_SortArrayOf012 {

    public static void main(String[] args) {

        int[] nums = {1, 0, 2, 1, 0};

        sortColors(nums);

        System.out.print("Sorted array: ");

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void sortColors(int[] nums) {

        int low = 0;
        int mid = 0;
        int high = nums.length - 1;

        while (mid <= high) {

            if (nums[mid] == 0) {

                swap(nums, low, mid);

                low++;
                mid++;

            } else if (nums[mid] == 1) {

                mid++;

            } else {

                swap(nums, mid, high);

                high--;
            }
        }
    }

    public static void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}