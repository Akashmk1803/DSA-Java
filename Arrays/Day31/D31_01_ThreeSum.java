package Day31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Day 31: 3Sum
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums, return all unique triplets
 * [nums[i], nums[j], nums[k]] such that:
 *
 *      i != j
 *      i != k
 *      j != k
 *
 * and:
 *
 *      nums[i] + nums[j] + nums[k] == 0
 *
 * The solution must not contain duplicate triplets.
 *
 *
 * Example:
 * Input:
 * nums = [-1, 0, 1, 2, -1, -4]
 *
 * Output:
 * [[-1, -1, 2], [-1, 0, 1]]
 *
 *
 * Important:
 * ----------
 * We need UNIQUE triplets.
 *
 * For example:
 *
 * [-1, 0, 1]
 * [0, 1, -1]
 *
 * represent the same triplet because the order does not matter.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Try every possible combination of three elements.
 *
 * Use three loops:
 *
 * i = 0 to n-1
 * j = i+1 to n-1
 * k = j+1 to n-1
 *
 * If:
 *
 * nums[i] + nums[j] + nums[k] == 0
 *
 * then we found a valid triplet.
 *
 * But we also need to remove duplicate triplets.
 *
 * We can store each triplet in a Set after sorting the triplet.
 *
 * Time Complexity:
 * O(n^3)
 *
 * Space Complexity:
 * O(number of unique triplets)
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: BETTER APPROACH
 * ------------------------------------------------------------
 *
 * Fix one element nums[i].
 *
 * Then the remaining problem becomes a Two Sum problem.
 *
 * For every i:
 *
 * target = -nums[i]
 *
 * Use a HashSet to find two numbers whose sum is target.
 *
 * This reduces the time complexity from O(n^3) to O(n^2).
 *
 * Time Complexity:
 * O(n^2) average
 *
 * Space Complexity:
 * O(n)
 *
 * Additional space is required for the HashSet and result.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 3: OPTIMAL APPROACH
 * ------------------------------------------------------------
 *
 * Sort the array first.
 *
 * Then use the Two Pointer technique.
 *
 * Example:
 *
 * nums = [-1, 0, 1, 2, -1, -4]
 *
 * After sorting:
 *
 * [-4, -1, -1, 0, 1, 2]
 *
 *
 * Step 1:
 * Fix nums[i].
 *
 * Step 2:
 * Use two pointers:
 *
 * left = i + 1
 * right = n - 1
 *
 * Calculate:
 *
 * sum = nums[i] + nums[left] + nums[right]
 *
 *
 * If sum == 0:
 * ----------------
 * We found a valid triplet.
 *
 * Move both pointers.
 *
 *
 * If sum < 0:
 * ----------------
 * We need a larger sum.
 *
 * Move left forward.
 *
 *
 * If sum > 0:
 * ----------------
 * We need a smaller sum.
 *
 * Move right backward.
 *
 *
 * DUPLICATE HANDLING:
 * -------------------
 *
 * Since the array is sorted, duplicate values are next to each other.
 *
 * For the first number:
 *
 * if (i > 0 && nums[i] == nums[i - 1])
 *
 * skip it.
 *
 * After finding a valid triplet:
 *
 * Skip duplicate values for both left and right pointers.
 *
 *
 * Why is this optimal?
 * --------------------
 *
 * The brute force approach checks every combination of three elements:
 *
 * O(n^3)
 *
 * The better approach fixes one element and uses HashSet:
 *
 * O(n^2)
 *
 * The optimal approach also fixes one element, but because the array
 * is sorted, two pointers can search for the remaining two elements
 * in linear time.
 *
 * Therefore:
 *
 * Sorting       -> O(n log n)
 * Two pointers  -> O(n^2)
 *
 * Overall:
 *
 * O(n^2)
 *
 * with O(1) extra auxiliary space apart from the output.
 */
public class D31_01_ThreeSum {

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = threeSum(nums);

        System.out.println("Unique triplets: " + result);
    }


    /*
     * Optimal Solution
     *
     * Time Complexity:
     * O(n log n) for sorting
     * + O(n^2) for two-pointer traversal
     *
     * Overall:
     * O(n^2)
     *
     * Space Complexity:
     * O(1) auxiliary space
     * (excluding the output list)
     */
    public static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;

        // Step 1: Sort the array.
        Arrays.sort(nums);

        // Step 2: Fix one element at a time.
        for (int i = 0; i < n - 2; i++) {

            /*
             * If the current element is the same as the previous
             * element, skip it.
             *
             * This prevents duplicate triplets.
             *
             * Example:
             *
             * [-1, -1, 0, 1]
             *
             * We don't need to process the second -1 as the
             * starting element again.
             */
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            /*
             * Since the array is sorted:
             *
             * nums[i] + nums[left] + nums[right]
             *
             * If nums[i] is already greater than 0,
             * all remaining numbers will also be >= 0.
             *
             * Therefore, the sum cannot become 0.
             */
            if (nums[i] > 0) {
                break;
            }

            int left = i + 1;
            int right = n - 1;

            // Step 3: Two-pointer search.
            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                /*
                 * Case 1:
                 * We found a valid triplet.
                 */
                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i],
                            nums[left],
                            nums[right]
                    ));

                    /*
                     * Move left and right.
                     */
                    left++;
                    right--;

                    /*
                     * Skip duplicate left values.
                     *
                     * Example:
                     *
                     * [-1, 0, 0, 0, 1]
                     *
                     * After finding [-1, 0, 1],
                     * another 0 should not create the same triplet.
                     */
                    while (left < right &&
                            nums[left] == nums[left - 1]) {
                        left++;
                    }

                    /*
                     * Skip duplicate right values.
                     */
                    while (left < right &&
                            nums[right] == nums[right + 1]) {
                        right--;
                    }
                }

                /*
                 * Case 2:
                 * Sum is too small.
                 *
                 * Because the array is sorted,
                 * moving left forward increases the sum.
                 */
                else if (sum < 0) {
                    left++;
                }

                /*
                 * Case 3:
                 * Sum is too large.
                 *
                 * Moving right backward decreases the sum.
                 */
                else {
                    right--;
                }
            }
        }

        return result;
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * [-1, 0, 1, 2, -1, -4]
 *
 *
 * After sorting:
 *
 * [-4, -1, -1, 0, 1, 2]
 *
 *
 * i = 0
 * nums[i] = -4
 *
 * left = 1 -> -1
 * right = 5 -> 2
 *
 * sum = -4 + (-1) + 2
 *     = -3
 *
 * sum < 0
 * Move left.
 *
 *
 * Eventually no valid triplet is found for -4.
 *
 *
 * i = 1
 * nums[i] = -1
 *
 * left = 2 -> -1
 * right = 5 -> 2
 *
 * sum = -1 + (-1) + 2
 *     = 0
 *
 * Triplet:
 *
 * [-1, -1, 2]
 *
 *
 * Move both pointers.
 *
 *
 * Next:
 *
 * left = 3 -> 0
 * right = 4 -> 1
 *
 * sum = -1 + 0 + 1
 *     = 0
 *
 * Triplet:
 *
 * [-1, 0, 1]
 *
 *
 * Move both pointers.
 *
 *
 * i = 2
 * nums[i] = -1
 *
 * Same as nums[i - 1].
 *
 * Skip it to avoid duplicate triplets.
 *
 *
 * Final result:
 *
 * [[-1, -1, 2], [-1, 0, 1]]
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * 1. 3Sum is essentially an extension of the Two Sum problem.
 *
 * 2. Sorting allows us to use two pointers.
 *
 * 3. If sum < 0, move left forward.
 *
 * 4. If sum > 0, move right backward.
 *
 * 5. If sum == 0, record the triplet and move both pointers.
 *
 * 6. Duplicate handling is extremely important.
 *
 * 7. More than one occurrence of a number can be used if they
 *    come from different indexes.
 *
 *    Example:
 *    [-1, -1, 2]
 *
 *    Both -1 values are valid because they are different elements.
 *
 * 8. But the same triplet should not be added multiple times.
 *
 * 9. General pattern:
 *
 *    2Sum -> HashMap / Two Pointers
 *    3Sum -> Fix one + Two Pointers
 *
 * This pattern is very useful for larger Sum problems.
 */