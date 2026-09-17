package Day38;

import java.util.Arrays;

/*
 * Day 38: Count Inversions
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums, count the number of inversions.
 *
 * A pair (i, j) is called an inversion if:
 *
 *      i < j
 *
 * and:
 *
 *      nums[i] > nums[j]
 *
 *
 * Example:
 *
 * nums = [2, 3, 7, 1, 3, 5]
 *
 * Inversions:
 *
 * (2,1)
 * (3,1)
 * (7,1)
 * (7,3)
 * (7,5)
 *
 * Total = 5
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Check every possible pair of elements.
 *
 * For every i:
 *
 *      for every j > i:
 *
 *          if nums[i] > nums[j]
 *
 *              count++
 *
 *
 * Example:
 *
 * [5, 4, 3, 2, 1]
 *
 * 5 forms inversions with:
 *
 * 4, 3, 2, 1
 *
 * -> 4 inversions
 *
 * 4 forms inversions with:
 *
 * 3, 2, 1
 *
 * -> 3 inversions
 *
 * and so on.
 *
 *
 * Total:
 *
 * 4 + 3 + 2 + 1 = 10
 *
 *
 * Time Complexity:
 *
 * O(n^2)
 *
 * Space Complexity:
 *
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH: MERGE SORT
 * ------------------------------------------------------------
 *
 * We can modify Merge Sort to count inversions.
 *
 * Merge Sort divides the array into smaller parts and then
 * merges them in sorted order.
 *
 * The important observation is during the MERGE step.
 *
 *
 * Suppose we have:
 *
 * Left half:
 *
 * [2, 3, 7]
 *
 * Right half:
 *
 * [1, 3, 5]
 *
 *
 * Both halves are already sorted.
 *
 *
 * Suppose:
 *
 * left[i] > right[j]
 *
 * Because the left half is sorted, everything after left[i]
 * is also >= left[i].
 *
 * Therefore ALL remaining elements in the left half are
 * greater than right[j].
 *
 *
 * Number of inversions:
 *
 *      mid - i + 1
 *
 *
 * This allows us to count many inversions at once.
 *
 *
 * ------------------------------------------------------------
 * WHY DOES mid - i + 1 WORK?
 * ------------------------------------------------------------
 *
 * Consider:
 *
 * Left:
 *
 * [2, 3, 7]
 *       ^
 *       i
 *
 * Right:
 *
 * [1, 3, 5]
 *  ^
 *  j
 *
 *
 * Compare:
 *
 * 7 > 1
 *
 * Since the left half is sorted:
 *
 * [2, 3, 7]
 *
 * both 7 and all elements after i would be greater
 * than 1.
 *
 * If i points to 2:
 *
 * [2, 3, 7]
 *  ^
 *
 * and right[j] = 1,
 *
 * then:
 *
 * 2 > 1
 * 3 > 1
 * 7 > 1
 *
 * Three inversions are created.
 *
 * Count:
 *
 *      mid - i + 1
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Divide the array into two halves.
 *
 * 2. Recursively count inversions in the left half.
 *
 * 3. Recursively count inversions in the right half.
 *
 * 4. During merging, count cross inversions.
 *
 * 5. Merge the two halves into sorted order.
 *
 * 6. Return:
 *
 *      left inversions
 *      + right inversions
 *      + cross inversions
 *
 *
 * ------------------------------------------------------------
 * FORMULA
 * ------------------------------------------------------------
 *
 * Total Inversions =
 *
 *      Left Inversions
 *      +
 *      Right Inversions
 *      +
 *      Cross Inversions
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT
 * ------------------------------------------------------------
 *
 * Use long for the inversion count.
 *
 * Why?
 *
 * Maximum inversions occur when the array is sorted
 * in descending order.
 *
 * Maximum number:
 *
 *      n * (n - 1) / 2
 *
 * For n = 100000:
 *
 *      100000 * 99999 / 2
 *
 * which is much larger than the maximum int value.
 *
 * Therefore:
 *
 *      long count
 *
 * should be used.
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * Merge Sort performs:
 *
 * O(log n)
 *
 * levels.
 *
 * Each level processes:
 *
 * O(n)
 *
 * elements.
 *
 * Therefore:
 *
 *      O(n log n)
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * Temporary array used during merging:
 *
 *      O(n)
 *
 * Recursion stack:
 *
 *      O(log n)
 *
 * Overall:
 *
 *      O(n)
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * Normal Merge Sort:
 *
 *      Divide + Sort + Merge
 *
 *
 * Inversion Count:
 *
 *      Divide
 *        ↓
 *      Count left inversions
 *        ↓
 *      Count right inversions
 *        ↓
 *      Count cross inversions while merging
 *        ↓
 *      Merge
 *
 *
 * The key line is:
 *
 *      count += mid - i + 1
 *
 * when:
 *
 *      left[i] > right[j]
 *
 *
 * This lets us count multiple inversions at once instead
 * of checking every pair individually.
 */
public class D38_01_CountInversions {

    public static void main(String[] args) {

        int[] nums = {2, 3, 7, 1, 3, 5};

        long result = countInversions(nums);

        System.out.println("Number of inversions: " + result);
    }


    /*
     * Optimal Approach:
     *
     * Merge Sort + Inversion Counting
     *
     * Time Complexity:
     * O(n log n)
     *
     * Space Complexity:
     * O(n)
     */
    public static long countInversions(int[] nums) {

        /*
         * A single element cannot form an inversion.
         */
        if (nums.length < 2) {
            return 0;
        }

        return mergeSort(nums, 0, nums.length - 1);
    }


    /*
     * Recursively divide the array.
     */
    private static long mergeSort(
            int[] nums,
            int low,
            int high
    ) {

        /*
         * Base case:
         *
         * Only one element exists.
         */
        if (low >= high) {
            return 0;
        }

        /*
         * Find the middle.
         *
         * This formula avoids possible integer overflow.
         */
        int mid = low + (high - low) / 2;


        /*
         * Count inversions in the left half.
         */
        long leftInversions =
                mergeSort(nums, low, mid);


        /*
         * Count inversions in the right half.
         */
        long rightInversions =
                mergeSort(nums, mid + 1, high);


        /*
         * Count inversions between the left and right halves
         * while merging them.
         */
        long crossInversions =
                merge(nums, low, mid, high);


        /*
         * Total inversions:
         *
         * Left + Right + Cross
         */
        return leftInversions
                + rightInversions
                + crossInversions;
    }


    /*
     * Merge two sorted halves:
     *
     * nums[low ... mid]
     *
     * and
     *
     * nums[mid+1 ... high]
     *
     * while counting cross inversions.
     */
    private static long merge(
            int[] nums,
            int low,
            int mid,
            int high
    ) {

        /*
         * Temporary array for the merged result.
         */
        int[] temp = new int[high - low + 1];

        int i = low;
        int j = mid + 1;
        int k = 0;

        long inversionCount = 0;


        /*
         * Compare elements from both sorted halves.
         */
        while (i <= mid && j <= high) {

            /*
             * If left element <= right element:
             *
             * No inversion.
             *
             * We use <= instead of < because equal values
             * do NOT form an inversion.
             */
            if (nums[i] <= nums[j]) {

                temp[k] = nums[i];

                i++;
                k++;

            } else {

                /*
                 * nums[i] > nums[j]
                 *
                 * Therefore nums[j] forms an inversion
                 * with nums[i].
                 *
                 * But because the left half is sorted,
                 * every element from i to mid is also
                 * greater than nums[j].
                 *
                 * Therefore:
                 *
                 * Number of inversions =
                 *
                 * mid - i + 1
                 */
                temp[k] = nums[j];

                inversionCount += (mid - i + 1);

                j++;
                k++;
            }
        }


        /*
         * Copy remaining elements from the left half.
         */
        while (i <= mid) {

            temp[k] = nums[i];

            i++;
            k++;
        }


        /*
         * Copy remaining elements from the right half.
         */
        while (j <= high) {

            temp[k] = nums[j];

            j++;
            k++;
        }


        /*
         * Copy sorted values back into nums.
         */
        for (int index = 0; index < temp.length; index++) {

            nums[low + index] = temp[index];
        }


        return inversionCount;
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * [2, 3, 7, 1, 3, 5]
 *
 *
 * Merge Sort divides the array:
 *
 *              [2,3,7,1,3,5]
 *                 /       \
 *             [2,3,7]    [1,3,5]
 *
 *
 * Left side:
 *
 * [2,3,7]
 *
 * Right side:
 *
 * [1,3,5]
 *
 *
 * Both halves eventually become sorted.
 *
 *
 * ------------------------------------------------------------
 * MERGING [2,3,7] AND [1,3,5]
 * ------------------------------------------------------------
 *
 * Compare:
 *
 * 2 and 1
 *
 * 2 > 1
 *
 * Therefore 1 creates inversions with:
 *
 * 2
 * 3
 * 7
 *
 * Number of inversions:
 *
 * mid - i + 1
 *
 * = 2 - 0 + 1
 *
 * = 3
 *
 * Inversions:
 *
 * (2,1)
 * (3,1)
 * (7,1)
 *
 *
 * ------------------------------------------------------------
 *
 * Now compare:
 *
 * 2 and 3
 *
 * 2 < 3
 *
 * No inversion.
 *
 *
 * Compare:
 *
 * 3 and 3
 *
 * 3 == 3
 *
 * No inversion.
 *
 *
 * Compare:
 *
 * 7 and 3
 *
 * 7 > 3
 *
 * Remaining left elements:
 *
 * [7]
 *
 * Number of inversions:
 *
 * 1
 *
 * Inversion:
 *
 * (7,3)
 *
 *
 * Compare:
 *
 * 7 and 5
 *
 * 7 > 5
 *
 * Remaining left elements:
 *
 * [7]
 *
 * Number of inversions:
 *
 * 1
 *
 * Inversion:
 *
 * (7,5)
 *
 *
 * Total cross inversions:
 *
 * 3 + 1 + 1 = 5
 *
 *
 * FINAL ANSWER:
 *
 * 5
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT EXAMPLE
 * ------------------------------------------------------------
 *
 * nums = [5,4,3,2,1]
 *
 * Every pair is an inversion.
 *
 * Number of inversions:
 *
 * 4 + 3 + 2 + 1
 *
 * = 10
 *
 *
 * Formula for a reverse sorted array:
 *
 * n * (n - 1) / 2
 *
 *
 * ------------------------------------------------------------
 * WHY <= DURING MERGE?
 * ------------------------------------------------------------
 *
 * Suppose:
 *
 * left = [2]
 * right = [2]
 *
 * 2 > 2 is FALSE.
 *
 * Therefore they are NOT an inversion.
 *
 * This is why we use:
 *
 *      if (nums[i] <= nums[j])
 *
 * rather than:
 *
 *      if (nums[i] < nums[j])
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * During merge:
 *
 * left[i] <= right[j]
 *        ↓
 *   No inversion
 *
 *
 * left[i] > right[j]
 *        ↓
 *   Inversion!
 *        ↓
 * Count ALL remaining
 * left elements
 *
 *        ↓
 *
 * count += mid - i + 1
 *
 *
 * FINAL:
 *
 * Brute Force:
 *      O(n²)
 *
 * Merge Sort:
 *      O(n log n)
 *
 * Space:
 *      O(n)
 */