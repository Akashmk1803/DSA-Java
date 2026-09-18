package Day39;

/*
 * Day 39: Reverse Pairs
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums, count the number of reverse pairs.
 *
 * A pair (i, j) is a reverse pair if:
 *
 *      i < j
 *
 * and:
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * Example:
 *
 * Input:
 *
 * nums = [1, 3, 2, 3, 1]
 *
 * Output:
 *
 * 2
 *
 *
 * Reverse pairs:
 *
 * (1, 4):
 * nums[1] = 3
 * nums[4] = 1
 *
 * 3 > 2 * 1
 * 3 > 2
 *
 * TRUE
 *
 *
 * (3, 4):
 * nums[3] = 3
 * nums[4] = 1
 *
 * 3 > 2 * 1
 * 3 > 2
 *
 * TRUE
 *
 *
 * Total = 2
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Check every possible pair (i, j).
 *
 * For every i:
 *
 *      for every j > i:
 *
 *          if nums[i] > 2 * nums[j]
 *
 *              count++
 *
 *
 * Time Complexity:
 *
 * O(n^2)
 *
 *
 * Space Complexity:
 *
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * WHY BRUTE FORCE IS TOO SLOW
 * ------------------------------------------------------------
 *
 * n can be:
 *
 *      5 * 10^4
 *
 * Therefore O(n^2) would require an enormous number
 * of comparisons.
 *
 * We need a better approach.
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH: MERGE SORT
 * ------------------------------------------------------------
 *
 * Just like the inversion-counting problem from Day 38,
 * we use Merge Sort.
 *
 *
 * Merge Sort divides the array into:
 *
 *      left half
 *      right half
 *
 *
 * During the recursive process:
 *
 *      left half is sorted
 *      right half is sorted
 *
 *
 * Then we count reverse pairs where:
 *
 *      i belongs to left half
 *      j belongs to right half
 *
 *
 * These are called CROSS REVERSE PAIRS.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DIFFERENCE FROM NORMAL INVERSIONS
 * ------------------------------------------------------------
 *
 * Day 38:
 *
 *      nums[i] > nums[j]
 *
 *
 * Day 39:
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * So we cannot simply use the normal inversion-counting
 * condition.
 *
 *
 * We perform a special counting step BEFORE merging.
 *
 *
 * ------------------------------------------------------------
 * WHY CAN WE COUNT EFFICIENTLY?
 * ------------------------------------------------------------
 *
 * Suppose the left half is sorted:
 *
 *      [2, 5, 8, 12]
 *
 * and the right half is sorted:
 *
 *      [1, 3, 4, 6]
 *
 *
 * For a fixed left element:
 *
 *      nums[i]
 *
 * we move a pointer j through the right half while:
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * Because the right half is sorted, once the condition
 * becomes false, later values will also fail.
 *
 * Therefore we can count multiple pairs at once.
 *
 *
 * ------------------------------------------------------------
 * COUNTING CROSS REVERSE PAIRS
 * ------------------------------------------------------------
 *
 * For every i in the left half:
 *
 *      while j <= high
 *      and nums[i] > 2 * nums[j]:
 *
 *          j++
 *
 *
 * Then:
 *
 *      j - (mid + 1)
 *
 * is the number of valid right-side elements for nums[i].
 *
 *
 * IMPORTANT:
 * ----------
 * The pointer j does NOT need to reset for every i.
 *
 * Because the left half is sorted, as i increases,
 * nums[i] also increases.
 *
 * Therefore j can only move forward.
 *
 * This makes the counting step linear.
 *
 *
 * ------------------------------------------------------------
 * WHY USE long?
 * ------------------------------------------------------------
 *
 * nums[i] can be as large as:
 *
 *      2^31 - 1
 *
 * and nums[j] can be:
 *
 *      -2^31
 *
 * Multiplying an int by 2 can overflow.
 *
 * Therefore use:
 *
 *      (long) nums[i] > 2L * nums[j]
 *
 *
 * This is very important.
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Divide the array into two halves.
 *
 * 2. Recursively count reverse pairs in the left half.
 *
 * 3. Recursively count reverse pairs in the right half.
 *
 * 4. Count cross reverse pairs:
 *
 *      left element
 *      versus
 *      right element
 *
 * 5. Merge the two sorted halves.
 *
 * 6. Return:
 *
 *      left count
 *      + right count
 *      + cross count
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * Merge Sort has:
 *
 *      O(log n)
 *
 * levels.
 *
 * Each level performs:
 *
 *      O(n)
 *
 * work.
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
 * Temporary array:
 *
 *      O(n)
 *
 * Recursion:
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
 * Day 38:
 *
 * Count inversions while merging.
 *
 *
 * Day 39:
 *
 * Count reverse pairs BEFORE merging.
 *
 *
 * Pattern:
 *
 *      Divide
 *        ↓
 *      Count left
 *        ↓
 *      Count right
 *        ↓
 *      Count cross reverse pairs
 *        ↓
 *      Merge
 *
 *
 * The key condition:
 *
 *      (long) nums[i] > 2L * nums[j]
 *
 *
 * And the key counting formula:
 *
 *      count += j - (mid + 1)
 */
public class D39_01_ReversePairs {

    public static void main(String[] args) {

        int[] nums = {1, 3, 2, 3, 1};

        long result = reversePairs(nums);

        System.out.println("Number of reverse pairs: " + result);
    }


    /*
     * Optimal Approach:
     *
     * Merge Sort + Two Pointer Counting
     *
     * Time Complexity:
     * O(n log n)
     *
     * Space Complexity:
     * O(n)
     */
    public static long reversePairs(int[] nums) {

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
         * A single element cannot form a pair.
         */
        if (low >= high) {
            return 0;
        }

        int mid = low + (high - low) / 2;


        /*
         * Count reverse pairs completely inside
         * the left half.
         */
        long leftCount =
                mergeSort(nums, low, mid);


        /*
         * Count reverse pairs completely inside
         * the right half.
         */
        long rightCount =
                mergeSort(nums, mid + 1, high);


        /*
         * Count reverse pairs where:
         *
         * i is in left half
         * j is in right half
         */
        long crossCount =
                countReversePairs(nums, low, mid, high);


        /*
         * Merge both sorted halves.
         */
        merge(nums, low, mid, high);


        /*
         * Total:
         *
         * left + right + cross
         */
        return leftCount + rightCount + crossCount;
    }


    /*
     * Count cross reverse pairs.
     *
     * Left half:
     *
     * nums[low ... mid]
     *
     * Right half:
     *
     * nums[mid + 1 ... high]
     *
     * Both halves are already sorted.
     */
    private static long countReversePairs(
            int[] nums,
            int low,
            int mid,
            int high
    ) {

        long count = 0;

        /*
         * j starts at the beginning of the right half.
         */
        int j = mid + 1;


        /*
         * Traverse every element in the left half.
         */
        for (int i = low; i <= mid; i++) {

            /*
             * Move j while the reverse-pair condition
             * is satisfied.
             *
             * IMPORTANT:
             *
             * Cast to long to avoid integer overflow.
             */
            while (j <= high
                    && (long) nums[i] > 2L * nums[j]) {

                j++;
            }


            /*
             * All elements from:
             *
             * mid + 1
             *
             * to:
             *
             * j - 1
             *
             * satisfy:
             *
             * nums[i] > 2 * nums[j]
             *
             *
             * Number of such elements:
             *
             * j - (mid + 1)
             */
            count += j - (mid + 1);
        }

        return count;
    }


    /*
     * Standard Merge Sort merge operation.
     *
     * Both halves are already sorted.
     */
    private static void merge(
            int[] nums,
            int low,
            int mid,
            int high
    ) {

        int[] temp = new int[high - low + 1];

        int i = low;
        int j = mid + 1;
        int k = 0;


        /*
         * Merge two sorted halves.
         */
        while (i <= mid && j <= high) {

            if (nums[i] <= nums[j]) {

                temp[k] = nums[i];

                i++;

            } else {

                temp[k] = nums[j];

                j++;
            }

            k++;
        }


        /*
         * Copy remaining left elements.
         */
        while (i <= mid) {

            temp[k] = nums[i];

            i++;
            k++;
        }


        /*
         * Copy remaining right elements.
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
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * nums = [1, 3, 2, 3, 1]
 *
 *
 * Expected answer:
 *
 * 2
 *
 *
 * The valid reverse pairs are:
 *
 * (3,1)
 * (3,1)
 *
 *
 * ------------------------------------------------------------
 * SORTED HALVES
 * ------------------------------------------------------------
 *
 * Eventually Merge Sort gives us sorted halves.
 *
 * Left:
 *
 * [1, 3]
 *
 * Right:
 *
 * [1, 2, 3]
 *
 *
 * ------------------------------------------------------------
 * COUNT CROSS REVERSE PAIRS
 * ------------------------------------------------------------
 *
 * i points to 1.
 *
 * Check:
 *
 * 1 > 2 * 1
 *
 * 1 > 2
 *
 * FALSE.
 *
 *
 * Move i.
 *
 *
 * i points to 3.
 *
 * Check:
 *
 * 3 > 2 * 1
 *
 * 3 > 2
 *
 * TRUE.
 *
 * Move j.
 *
 *
 * Next right value:
 *
 * 2
 *
 * Check:
 *
 * 3 > 2 * 2
 *
 * 3 > 4
 *
 * FALSE.
 *
 *
 * Therefore only one right-side value works for
 * this particular left-side 3.
 *
 * That gives one cross reverse pair.
 *
 *
 * Other recursive merge levels identify the second pair.
 *
 *
 * Final answer:
 *
 *      2
 *
 *
 * ------------------------------------------------------------
 * TUF EXAMPLE
 * ------------------------------------------------------------
 *
 * nums = [6, 4, 1, 2, 7]
 *
 *
 * Reverse pairs:
 *
 * (0,2):
 *
 * 6 > 2 * 1
 * 6 > 2
 *
 * TRUE
 *
 *
 * (0,3):
 *
 * 6 > 2 * 2
 * 6 > 4
 *
 * TRUE
 *
 *
 * (1,2):
 *
 * 4 > 2 * 1
 * 4 > 2
 *
 * TRUE
 *
 *
 * Total:
 *
 * 3
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT DIFFERENCE FROM DAY 38
 * ------------------------------------------------------------
 *
 * Day 38:
 *
 * Inversion:
 *
 *      nums[i] > nums[j]
 *
 *
 * Day 39:
 *
 * Reverse Pair:
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * Day 38 counts during the merge comparison.
 *
 * Day 39 counts BEFORE the actual merge.
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * Reverse Pair:
 *
 *      i < j
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * Use:
 *
 *      MERGE SORT
 *
 *
 * For each left element:
 *
 *      Move j while:
 *
 *      nums[i] > 2 * nums[j]
 *
 *
 * Then:
 *
 *      count += j - (mid + 1)
 *
 *
 * IMPORTANT:
 *
 *      (long) nums[i] > 2L * nums[j]
 *
 * prevents integer overflow.
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