package Day36;

import java.util.Arrays;

/*
 * Day 36: Merge Sorted Array
 *
 * Problem Statement:
 * ------------------
 * Given two sorted integer arrays nums1 and nums2,
 * merge them into a single sorted array.
 *
 * nums1 has a length of m + n.
 *
 * The first m elements of nums1 contain actual values.
 * The remaining n positions are empty space represented by 0.
 *
 * nums2 contains n elements.
 *
 * The final sorted array must be stored inside nums1.
 *
 *
 * Example:
 *
 * nums1 = [1, 2, 3, 0, 0, 0]
 * m = 3
 *
 * nums2 = [2, 5, 6]
 * n = 3
 *
 * Output:
 *
 * [1, 2, 2, 3, 5, 6]
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Copy all elements of nums2 into the empty positions
 * of nums1.
 *
 * Then sort nums1.
 *
 * Example:
 *
 * nums1 = [1,2,3,0,0,0]
 * nums2 = [2,5,6]
 *
 * After copying:
 *
 * nums1 = [1,2,3,2,5,6]
 *
 * Then sort:
 *
 * [1,2,2,3,5,6]
 *
 *
 * Time Complexity:
 *
 * O((m+n) log(m+n))
 *
 * Space Complexity:
 *
 * Depends on sorting implementation.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 2: TWO POINTERS FROM THE FRONT
 * ------------------------------------------------------------
 *
 * Maintain:
 *
 * i = index for nums1
 * j = index for nums2
 *
 * Compare:
 *
 * nums1[i] and nums2[j]
 *
 * Put the smaller element into another array.
 *
 * This takes:
 *
 * O(m+n)
 *
 * time.
 *
 * But requires:
 *
 * O(m+n)
 *
 * extra space.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 3: OPTIMAL - TWO POINTERS FROM THE BACK
 * ------------------------------------------------------------
 *
 * Since nums1 already contains empty positions at the end,
 * we can use those positions to store the merged result.
 *
 *
 * Three pointers:
 *
 * i = m - 1
 *
 * Points to the last actual element in nums1.
 *
 *
 * j = n - 1
 *
 * Points to the last element in nums2.
 *
 *
 * k = m + n - 1
 *
 * Points to the last available position in nums1.
 *
 *
 * Compare:
 *
 * nums1[i] and nums2[j]
 *
 * Put the larger element at nums1[k].
 *
 * Then move the corresponding pointer backward.
 *
 *
 * Why from the BACK?
 * ------------------
 *
 * Suppose:
 *
 * nums1 = [1, 3, 5, 0, 0, 0]
 * nums2 = [2, 4, 6]
 *
 * If we start from the front:
 *
 * We may overwrite values in nums1 that we still need.
 *
 * But the empty positions are at the END.
 *
 * Therefore, filling from the END is safe.
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Set:
 *
 *      i = m - 1
 *      j = n - 1
 *      k = m + n - 1
 *
 * 2. While i >= 0 and j >= 0:
 *
 *      Compare nums1[i] and nums2[j].
 *
 * 3. Put the larger value at nums1[k].
 *
 * 4. Move the appropriate pointer backward.
 *
 * 5. Move k backward.
 *
 * 6. After one array is exhausted, copy any remaining elements
 *    from nums2 into nums1.
 *
 * 7. Remaining elements of nums1 do not need to be copied
 *    because they are already in the correct position.
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT OBSERVATION
 * ------------------------------------------------------------
 *
 * We only need:
 *
 *      while (j >= 0)
 *
 * after the main loop.
 *
 * Why?
 *
 * If nums2 is exhausted, the remaining elements of nums1
 * are already where they belong.
 *
 * But if nums1 is exhausted first, the remaining elements
 * of nums2 must be copied.
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * Every element is processed at most once.
 *
 * Time Complexity:
 *
 * O(m + n)
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * We use only three pointers.
 *
 * No extra array is created.
 *
 * Auxiliary Space:
 *
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * When merging into an array that has free space at the END:
 *
 *      MERGE FROM THE BACK.
 *
 *
 * This avoids overwriting useful elements.
 *
 *
 * Pattern:
 *
 *      Two Sorted Arrays
 *              ↓
 *       Two Pointer Technique
 *              ↓
 *        Start from BACK
 *              ↓
 *          O(m + n)
 *          O(1) space
 */
public class D36_01_MergeSortedArray {

    public static void main(String[] args) {

        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;

        int[] nums2 = {2, 5, 6};
        int n = 3;

        merge(nums1, m, nums2, n);

        System.out.println(
                "Merged array: " + Arrays.toString(nums1)
        );
    }


    /*
     * Optimal Approach:
     *
     * Two Pointers from the Back
     *
     * Time Complexity:
     * O(m + n)
     *
     * Space Complexity:
     * O(1)
     */
    public static void merge(
            int[] nums1,
            int m,
            int[] nums2,
            int n
    ) {

        /*
         * i points to the last actual element
         * in nums1.
         */
        int i = m - 1;

        /*
         * j points to the last element
         * in nums2.
         */
        int j = n - 1;

        /*
         * k points to the last position of nums1.
         *
         * This is where we place the largest remaining element.
         */
        int k = m + n - 1;


        /*
         * Compare elements from the back.
         */
        while (i >= 0 && j >= 0) {

            /*
             * If nums1[i] is greater:
             *
             * place nums1[i] at nums1[k].
             */
            if (nums1[i] > nums2[j]) {

                nums1[k] = nums1[i];

                i--;

            } else {

                /*
                 * Otherwise nums2[j] is greater
                 * or equal.
                 *
                 * Place nums2[j] at nums1[k].
                 */
                nums1[k] = nums2[j];

                j--;
            }

            /*
             * Move the destination position backward.
             */
            k--;
        }


        /*
         * If elements are remaining in nums2,
         * copy them into nums1.
         *
         * Any remaining elements in nums1 do not need
         * to be copied because they are already in place.
         */
        while (j >= 0) {

            nums1[k] = nums2[j];

            j--;
            k--;
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
 * nums1 = [1, 2, 3, 0, 0, 0]
 * m = 3
 *
 * nums2 = [2, 5, 6]
 * n = 3
 *
 *
 * Initial pointers:
 *
 * i = 2  -> nums1[i] = 3
 * j = 2  -> nums2[j] = 6
 * k = 5
 *
 *
 * ------------------------------------------------------------
 *
 * Compare:
 *
 * 3 and 6
 *
 * 6 is larger.
 *
 * nums1[5] = 6
 *
 * Array:
 *
 * [1, 2, 3, 0, 0, 6]
 *
 * j--
 * k--
 *
 *
 * ------------------------------------------------------------
 *
 * i = 2 -> 3
 * j = 1 -> 5
 * k = 4
 *
 * Compare:
 *
 * 3 and 5
 *
 * 5 is larger.
 *
 * nums1[4] = 5
 *
 * Array:
 *
 * [1, 2, 3, 0, 5, 6]
 *
 *
 * ------------------------------------------------------------
 *
 * i = 2 -> 3
 * j = 0 -> 2
 * k = 3
 *
 * Compare:
 *
 * 3 and 2
 *
 * 3 is larger.
 *
 * nums1[3] = 3
 *
 * Array:
 *
 * [1, 2, 3, 3, 5, 6]
 *
 *
 * ------------------------------------------------------------
 *
 * i = 1 -> 2
 * j = 0 -> 2
 * k = 2
 *
 * Compare:
 *
 * 2 and 2
 *
 * nums2[j] is selected because of <= handling.
 *
 * nums1[2] = 2
 *
 * Array:
 *
 * [1, 2, 2, 3, 5, 6]
 *
 *
 * ------------------------------------------------------------
 *
 * j becomes -1.
 *
 * nums2 is exhausted.
 *
 * The remaining element:
 *
 * nums1[1] = 2
 *
 * is already in the correct position.
 *
 *
 * FINAL RESULT:
 *
 * [1, 2, 2, 3, 5, 6]
 *
 *
 * ------------------------------------------------------------
 * EDGE CASES
 * ------------------------------------------------------------
 *
 * Case 1:
 *
 * nums2 is empty.
 *
 * nums1 = [1,2,3]
 * nums2 = []
 *
 * Nothing needs to be done.
 *
 *
 * Case 2:
 *
 * nums1 has no actual elements.
 *
 * nums1 = [0]
 * m = 0
 *
 * nums2 = [1]
 *
 * Result:
 *
 * [1]
 *
 *
 * Case 3:
 *
 * All nums2 elements are smaller.
 *
 * nums1 = [4,5,6,0,0,0]
 * nums2 = [1,2,3]
 *
 * Result:
 *
 * [1,2,3,4,5,6]
 *
 *
 * Case 4:
 *
 * Duplicate values.
 *
 * nums1 = [1,2,2,0,0]
 * nums2 = [2,3]
 *
 * Result:
 *
 * [1,2,2,2,3]
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * nums1 has EMPTY SPACE at the END.
 *
 * Therefore:
 *
 *       DON'T MERGE FROM FRONT
 *
 *              ↓
 *
 *       MERGE FROM BACK
 *
 *
 * Three pointers:
 *
 * i = m - 1
 * j = n - 1
 * k = m + n - 1
 *
 *
 * Pick the LARGER element.
 *
 * Put it at nums1[k].
 *
 * Move backward.
 *
 *
 * Final complexity:
 *
 *      O(m + n) time
 *      O(1) extra space
 */