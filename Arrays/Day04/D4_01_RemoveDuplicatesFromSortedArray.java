package Day04;

/*
 * ============================================================
 * Day 04 - Problem 01
 * Remove Duplicates from Sorted Array
 * ============================================================
 *
 * Problem Statement:
 * Given a sorted array in non-decreasing order, remove the
 * duplicates in-place so that each unique element appears
 * only once.
 *
 * Return the number of unique elements.
 *
 * The first k elements of the array should contain the unique
 * elements. Elements after the first k positions do not matter.
 *
 * ============================================================
 *
 * Example:
 *
 * Input:
 * [1, 1, 2, 2, 2, 3, 3]
 *
 * Output:
 * [1, 2, 3, _, _, _, _]
 *
 * Number of unique elements:
 * 3
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Use a Set because a Set stores only unique elements.
 *
 * Algorithm:
 * 1. Create a Set.
 * 2. Insert every element of the array into the Set.
 * 3. Traverse the Set and place its elements into the
 *    beginning of the original array.
 * 4. Return the size of the Set.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(n)
 *
 * Drawback:
 * Additional memory is required for storing the unique
 * elements, so this does not satisfy the ideal in-place
 * requirement.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - TWO POINTERS
 *
 * Approach:
 * Since the array is already sorted, duplicate elements are
 * always next to each other.
 *
 * We use two pointers:
 *
 * i -> Points to the position of the last unique element.
 * j -> Traverses the array to find new unique elements.
 *
 * Algorithm:
 *
 * 1. Initialize i = 0.
 *
 * 2. Start j from index 1.
 *
 * 3. Compare arr[i] with arr[j].
 *
 * 4. If they are equal:
 *    - arr[j] is a duplicate.
 *    - Move j forward.
 *
 * 5. If they are different:
 *    - Move i forward.
 *    - Copy arr[j] to arr[i].
 *
 * 6. Continue until j reaches the end of the array.
 *
 * 7. Return i + 1 because i represents the index of the
 *    last unique element.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * ============================================================
 *
 * TRACE:
 *
 * Input:
 * [1, 1, 2, 2, 2, 3, 3]
 *
 * Initially:
 * i = 0
 * j = 1
 *
 * arr[i] = 1
 * arr[j] = 1
 * Same -> duplicate -> move j
 *
 * i = 0
 * j = 2
 *
 * arr[i] = 1
 * arr[j] = 2
 * Different -> i++ and copy arr[j]
 *
 * Array:
 * [1, 2, 2, 2, 2, 3, 3]
 *
 * Continue the same process.
 *
 * Final:
 * [1, 2, 3, _, _, _, _]
 *
 * i = 2
 *
 * Number of unique elements:
 * i + 1 = 3
 *
 * ============================================================
 *
 * Key Learning:
 * - Two pointer technique
 * - In-place array modification
 * - Using the sorted property of an array
 * - Handling duplicates
 * - O(n) time and O(1) space
 *
 * ============================================================
 */

public class D4_01_RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 2, 3, 3};

        int k = removeDuplicates(arr);

        System.out.println("Number of unique elements: " + k);

        System.out.print("Array after removing duplicates: ");

        for (int i = 0; i < k; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static int removeDuplicates(int[] arr) {

        int i = 0;

        for (int j = 1; j < arr.length; j++) {

            if (arr[i] != arr[j]) {

                i++;

                arr[i] = arr[j];
            }
        }

        return i + 1;
    }
}