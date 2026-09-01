package Day22;

/*
 * ============================================================
 * Day 22 - Problem 01
 * Next Permutation
 * ============================================================
 *
 * Problem Statement:
 * Given an array of integers, rearrange the elements into the
 * lexicographically next greater permutation.
 *
 * If no greater permutation exists, rearrange the array into
 * the lowest possible order (ascending order).
 *
 * The rearrangement must be performed in-place using
 * O(1) extra space.
 *
 * ============================================================
 *
 * Example 1:
 *
 * Input:
 * [1, 2, 3]
 *
 * Output:
 * [1, 3, 2]
 *
 * Explanation:
 *
 * The next permutation after [1, 2, 3] is [1, 3, 2].
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * [3, 2, 1]
 *
 * Output:
 * [1, 2, 3]
 *
 * Explanation:
 *
 * [3, 2, 1] is already the largest permutation.
 * Therefore, we return the smallest permutation.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 *
 * Input:
 * [1, 1, 5]
 *
 * Output:
 * [1, 5, 1]
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Generate all possible permutations, sort them in
 * lexicographical order, find the current permutation, and
 * return the next permutation.
 *
 * This approach is extremely inefficient because the number
 * of permutations can be n!.
 *
 * Time Complexity:
 * O(n!)
 *
 * Space Complexity:
 * O(n!)
 *
 * Drawback:
 * Generating all permutations is unnecessary.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * We can find the next permutation directly in four steps.
 *
 * The four important steps are:
 *
 * 1. Find the breakpoint.
 * 2. Find the element just greater than the breakpoint.
 * 3. Swap them.
 * 4. Reverse the suffix.
 *
 * ============================================================
 *
 * STEP 1 - FIND THE BREAKPOINT
 *
 * Start from the right side of the array.
 *
 * Find the first index i such that:
 *
 *      nums[i] < nums[i + 1]
 *
 * This index is called the breakpoint.
 *
 * Why start from the right?
 *
 * Because the suffix from the breakpoint onward is arranged
 * in non-increasing order.
 *
 * We need to make the smallest possible increase to obtain
 * the next permutation.
 *
 * ============================================================
 *
 * STEP 2 - FIND THE NEXT GREATER ELEMENT
 *
 * If a breakpoint exists, search from the right side again.
 *
 * Find the first element greater than nums[breakpoint].
 *
 * Because the suffix is in descending order, the first element
 * found from the right is the smallest element greater than
 * the breakpoint.
 *
 * ============================================================
 *
 * STEP 3 - SWAP
 *
 * Swap:
 *
 *      nums[breakpoint]
 *
 * with:
 *
 *      nums[indexOfNextGreaterElement]
 *
 * This makes the permutation slightly larger.
 *
 * ============================================================
 *
 * STEP 4 - REVERSE THE SUFFIX
 *
 * Reverse the portion after the breakpoint.
 *
 * The suffix was originally in descending order.
 *
 * Reversing it puts it into ascending order, which gives the
 * smallest possible arrangement after increasing the prefix.
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 *
 * [1, 3, 5, 4, 2]
 *
 * ------------------------------------------------------------
 *
 * STEP 1:
 * Find breakpoint from the right.
 *
 * Check:
 *
 * 4 < 2  -> false
 * 5 < 4  -> false
 * 3 < 5  -> true
 *
 * Therefore:
 *
 * breakpoint = 1
 *
 * Value:
 *
 * nums[1] = 3
 *
 * ------------------------------------------------------------
 *
 * STEP 2:
 * Find the first element from the right greater than 3.
 *
 * Check:
 *
 * 2 > 3 -> false
 * 4 > 3 -> true
 *
 * Therefore:
 *
 * next greater element = 4
 *
 * ------------------------------------------------------------
 *
 * STEP 3:
 * Swap 3 and 4.
 *
 * Before:
 *
 * [1, 3, 5, 4, 2]
 *
 * After:
 *
 * [1, 4, 5, 3, 2]
 *
 * ------------------------------------------------------------
 *
 * STEP 4:
 * Reverse the suffix after breakpoint.
 *
 * Suffix:
 *
 * [5, 3, 2]
 *
 * Reverse:
 *
 * [2, 3, 5]
 *
 * Final:
 *
 * [1, 4, 2, 3, 5]
 *
 * ============================================================
 *
 * CASE: NO BREAKPOINT
 *
 * Input:
 *
 * [3, 2, 1]
 *
 * Check from right:
 *
 * 2 < 1 -> false
 * 3 < 2 -> false
 *
 * No breakpoint exists.
 *
 * This means the array is already the largest permutation.
 *
 * Therefore, reverse the entire array:
 *
 * [3, 2, 1]
 *
 * becomes:
 *
 * [1, 2, 3]
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n)
 *
 * We perform a few linear scans/reversals of the array.
 *
 * ============================================================
 *
 * SPACE COMPLEXITY:
 * O(1)
 *
 * The array is modified in-place and only constant extra
 * variables are used.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Lexicographical order
 * - Finding a breakpoint
 * - Greedy approach
 * - Swapping
 * - Reversing a suffix
 * - In-place array manipulation
 * - O(n) time
 * - O(1) space
 *
 * ============================================================
 */

public class D22_01_NextPermutation {

    public static void main(String[] args) {

        int[] nums = {1, 3, 5, 4, 2};

        nextPermutation(nums);

        System.out.print("Next permutation: ");

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }

    public static void nextPermutation(int[] nums) {

        int n = nums.length;

        // Step 1: Find the breakpoint
        int breakpoint = -1;

        for (int i = n - 2; i >= 0; i--) {

            if (nums[i] < nums[i + 1]) {
                breakpoint = i;
                break;
            }
        }

        // If no breakpoint exists, the array is the last
        // permutation. Reverse the entire array.
        if (breakpoint == -1) {

            reverse(nums, 0, n - 1);
            return;
        }

        // Step 2: Find the first element from the right
        // that is greater than the breakpoint element.
        for (int i = n - 1; i > breakpoint; i--) {

            if (nums[i] > nums[breakpoint]) {

                // Step 3: Swap breakpoint and next greater element
                swap(nums, breakpoint, i);
                break;
            }
        }

        // Step 4: Reverse the suffix
        reverse(nums, breakpoint + 1, n - 1);
    }

    public static void swap(int[] nums, int i, int j) {

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void reverse(int[] nums, int left, int right) {

        while (left < right) {

            swap(nums, left, right);

            left++;
            right--;
        }
    }
}