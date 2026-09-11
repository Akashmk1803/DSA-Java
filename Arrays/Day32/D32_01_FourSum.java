package Day32;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Day 32: 4Sum
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums and an integer target,
 * return all unique quadruplets:
 *
 *      [nums[a], nums[b], nums[c], nums[d]]
 *
 * such that:
 *
 *      a, b, c, d are distinct
 *
 * and:
 *
 *      nums[a] + nums[b] + nums[c] + nums[d] == target
 *
 * The solution must not contain duplicate quadruplets.
 *
 *
 * Example:
 *
 * Input:
 * nums = [1, 0, -1, 0, -2, 2]
 * target = 0
 *
 * Output:
 *
 * [[-2, -1, 1, 2],
 *  [-2, 0, 0, 2],
 *  [-1, 0, 0, 1]]
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Try every possible combination of four elements.
 *
 * We use four loops:
 *
 * i
 * j
 * k
 * l
 *
 * Check:
 *
 * nums[i] + nums[j] + nums[k] + nums[l] == target
 *
 * If the sum matches, add the quadruplet.
 *
 * To avoid duplicate quadruplets, we can sort each quadruplet
 * and store it in a Set.
 *
 * Time Complexity:
 * O(n^4)
 *
 * Space Complexity:
 * O(number of unique quadruplets)
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: BETTER APPROACH
 * ------------------------------------------------------------
 *
 * Fix two elements:
 *
 * nums[i]
 * nums[j]
 *
 * Then the remaining problem becomes a Two Sum problem.
 *
 * Required sum:
 *
 * remaining = target - nums[i] - nums[j]
 *
 * Use a HashSet to find two numbers that add up to
 * the remaining value.
 *
 * This reduces the time complexity from O(n^4) to O(n^3).
 *
 * Time Complexity:
 * O(n^3) average
 *
 * Space Complexity:
 * O(n)
 *
 *
 * ------------------------------------------------------------
 * APPROACH 3: OPTIMAL APPROACH
 * ------------------------------------------------------------
 *
 * Sort the array first.
 *
 * Then:
 *
 * 1. Fix the first element using i.
 * 2. Fix the second element using j.
 * 3. Use two pointers for the remaining two elements.
 *
 * Pointers:
 *
 * left  = j + 1
 * right = n - 1
 *
 *
 * Calculate:
 *
 * sum = nums[i] + nums[j] + nums[left] + nums[right]
 *
 *
 * If sum == target:
 * -----------------
 * We found a valid quadruplet.
 *
 * Add it to the result.
 *
 * Move both left and right.
 *
 * Skip duplicate values.
 *
 *
 * If sum < target:
 * ----------------
 * We need a larger sum.
 *
 * Move left forward.
 *
 *
 * If sum > target:
 * ----------------
 * We need a smaller sum.
 *
 * Move right backward.
 *
 *
 * DUPLICATE HANDLING:
 * -------------------
 *
 * Since the array is sorted, duplicate values are next to each
 * other.
 *
 * Skip duplicate i values:
 *
 * if (i > 0 && nums[i] == nums[i - 1])
 *
 * Skip duplicate j values:
 *
 * if (j > i + 1 && nums[j] == nums[j - 1])
 *
 * After finding a valid quadruplet, skip duplicate left and
 * right values as well.
 *
 *
 * ------------------------------------------------------------
 * WHY IS THIS OPTIMAL?
 * ------------------------------------------------------------
 *
 * Brute force:
 *
 * O(n^4)
 *
 * Better:
 *
 * O(n^3)
 *
 * Optimal:
 *
 * Sorting:
 * O(n log n)
 *
 * Two outer loops:
 * O(n^2)
 *
 * Two-pointer search:
 * O(n)
 *
 * Overall:
 *
 * O(n^3)
 *
 * Since O(n^3) dominates O(n log n):
 *
 * Final Time Complexity:
 * O(n^3)
 *
 *
 * Auxiliary Space:
 *
 * O(1), excluding the output list.
 *
 *
 * IMPORTANT:
 * ------------
 * Use long for the sum.
 *
 * nums[i] can be as large as 10^9.
 *
 * Adding four integers can exceed the range of int.
 *
 * Therefore:
 *
 * long sum = ...
 *
 * is safer.
 */
public class D32_01_FourSum {

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> result = fourSum(nums, target);

        System.out.println("Unique quadruplets: " + result);
    }


    /*
     * Optimal Solution
     *
     * Time Complexity:
     * O(n^3)
     *
     * Space Complexity:
     * O(1) auxiliary space
     * excluding the output list.
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();

        int n = nums.length;

        // A quadruplet requires at least 4 elements.
        if (n < 4) {
            return result;
        }

        // Step 1: Sort the array.
        Arrays.sort(nums);

        /*
         * Step 2:
         * Fix the first element.
         */
        for (int i = 0; i < n - 3; i++) {

            /*
             * Skip duplicate first elements.
             *
             * Example:
             *
             * [-2, -2, 0, 1, 2]
             *
             * We don't want to process -2 twice
             * as the first element.
             */
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            /*
             * Since the array is sorted, if the smallest
             * possible sum is already greater than target,
             * no future i can work.
             */
            long smallestSum =
                    (long) nums[i]
                            + nums[i + 1]
                            + nums[i + 2]
                            + nums[i + 3];

            if (smallestSum > target) {
                break;
            }

            /*
             * If the largest possible sum using this i
             * is still smaller than target, this i cannot
             * produce a valid quadruplet.
             */
            long largestSum =
                    (long) nums[i]
                            + nums[n - 1]
                            + nums[n - 2]
                            + nums[n - 3];

            if (largestSum < target) {
                continue;
            }


            /*
             * Step 3:
             * Fix the second element.
             */
            for (int j = i + 1; j < n - 2; j++) {

                /*
                 * Skip duplicate second elements.
                 *
                 * Important:
                 *
                 * j > i + 1
                 *
                 * because the first j value is valid
                 * and should not be skipped.
                 */
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                /*
                 * Optional pruning:
                 *
                 * Smallest possible sum using this i and j.
                 */
                long smallestWithJ =
                        (long) nums[i]
                                + nums[j]
                                + nums[j + 1]
                                + nums[j + 2];

                if (smallestWithJ > target) {
                    break;
                }

                /*
                 * Largest possible sum using this i and j.
                 */
                long largestWithJ =
                        (long) nums[i]
                                + nums[j]
                                + nums[n - 1]
                                + nums[n - 2];

                if (largestWithJ < target) {
                    continue;
                }


                /*
                 * Step 4:
                 * Use two pointers for the remaining
                 * two elements.
                 */
                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum =
                            (long) nums[i]
                                    + nums[j]
                                    + nums[left]
                                    + nums[right];


                    /*
                     * Case 1:
                     * Found a valid quadruplet.
                     */
                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[i],
                                nums[j],
                                nums[left],
                                nums[right]
                        ));

                        /*
                         * Move both pointers.
                         */
                        left++;
                        right--;

                        /*
                         * Skip duplicate left values.
                         */
                        while (left < right
                                && nums[left] == nums[left - 1]) {
                            left++;
                        }

                        /*
                         * Skip duplicate right values.
                         */
                        while (left < right
                                && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    }


                    /*
                     * Case 2:
                     *
                     * Sum is smaller than target.
                     *
                     * Since the array is sorted,
                     * moving left forward increases the sum.
                     */
                    else if (sum < target) {
                        left++;
                    }


                    /*
                     * Case 3:
                     *
                     * Sum is greater than target.
                     *
                     * Moving right backward decreases the sum.
                     */
                    else {
                        right--;
                    }
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
 * nums = [1, 0, -1, 0, -2, 2]
 * target = 0
 *
 *
 * After sorting:
 *
 * [-2, -1, 0, 0, 1, 2]
 *
 *
 * i = 0
 * nums[i] = -2
 *
 * j = 1
 * nums[j] = -1
 *
 * left = 2 -> 0
 * right = 5 -> 2
 *
 * Sum:
 *
 * -2 + (-1) + 0 + 2
 * = -1
 *
 * Sum < 0
 *
 * Move left.
 *
 *
 * left = 3 -> 0
 * right = 5 -> 2
 *
 * Sum:
 *
 * -2 + (-1) + 0 + 2
 * = -1
 *
 * Move left.
 *
 *
 * left = 4 -> 1
 * right = 5 -> 2
 *
 * Sum:
 *
 * -2 + (-1) + 1 + 2
 * = 0
 *
 * Valid quadruplet:
 *
 * [-2, -1, 1, 2]
 *
 *
 * ------------------------------------------------------------
 *
 * Still i = 0
 *
 * j = 2
 * nums[j] = 0
 *
 * left = 3 -> 0
 * right = 5 -> 2
 *
 * Sum:
 *
 * -2 + 0 + 0 + 2
 * = 0
 *
 * Valid quadruplet:
 *
 * [-2, 0, 0, 2]
 *
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * nums[i] = -1
 *
 * j = 2
 * nums[j] = 0
 *
 * left = 3 -> 0
 * right = 5 -> 2
 *
 * Sum:
 *
 * -1 + 0 + 0 + 2
 * = 1
 *
 * Sum > target
 *
 * Move right.
 *
 * right = 4 -> 1
 *
 * Sum:
 *
 * -1 + 0 + 0 + 1
 * = 0
 *
 * Valid quadruplet:
 *
 * [-1, 0, 0, 1]
 *
 *
 * Final result:
 *
 * [
 *   [-2, -1, 1, 2],
 *   [-2, 0, 0, 2],
 *   [-1, 0, 0, 1]
 * ]
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * 1. 4Sum is an extension of 3Sum.
 *
 * 2. 3Sum:
 *
 *    Fix 1 element + Two Pointers
 *
 * 3. 4Sum:
 *
 *    Fix 2 elements + Two Pointers
 *
 * 4. Sorting makes duplicate removal much easier.
 *
 * 5. If sum < target:
 *
 *    left++
 *
 * 6. If sum > target:
 *
 *    right--
 *
 * 7. If sum == target:
 *
 *    store the quadruplet
 *    then move both pointers.
 *
 * 8. Duplicate skipping is necessary at:
 *
 *    i
 *    j
 *    left
 *    right
 *
 * 9. Use long for the sum when values can be very large.
 *
 * 10. General pattern:
 *
 *    2Sum -> Fix 0 + Two Sum
 *    3Sum -> Fix 1 + Two Pointers
 *    4Sum -> Fix 2 + Two Pointers
 *
 * This is a very important pattern for interviews and
 * competitive programming.
 */