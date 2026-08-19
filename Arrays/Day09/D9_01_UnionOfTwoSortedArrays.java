package Day09;

import java.util.ArrayList;
import java.util.List;

/*
 * ============================================================
 * Day 09 - Problem 01
 * Union of Two Sorted Arrays
 * ============================================================
 *
 * Problem Statement:
 * Given two sorted arrays arr1 and arr2, find the union of
 * both arrays.
 *
 * The union contains all distinct elements present in either
 * of the two arrays.
 *
 * The elements in the union must be in ascending order.
 *
 * ============================================================
 *
 * Examples:
 *
 * Example 1:
 *
 * Input:
 * arr1 = [1, 2, 3, 4, 5]
 * arr2 = [2, 3, 4, 4, 5]
 *
 * Output:
 * [1, 2, 3, 4, 5]
 *
 * Explanation:
 * Common elements:
 * [2, 3, 4, 5]
 *
 * Distinct element in arr1:
 * [1]
 *
 * Distinct elements in arr2:
 * None
 *
 * Therefore:
 * Union = [1, 2, 3, 4, 5]
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 *
 * Input:
 * arr1 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
 * arr2 = [2, 3, 4, 4, 5, 11, 12]
 *
 * Output:
 * [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
 *
 * ============================================================
 *
 * APPROACH 1 - USING MAP
 *
 * Approach:
 * Use a TreeMap to store all elements from both arrays.
 *
 * A map stores each element as a key. Since TreeMap keeps
 * keys sorted, the keys will automatically be in ascending
 * order.
 *
 * Duplicate elements are naturally represented by the same
 * key, so they appear only once in the union.
 *
 * Algorithm:
 *
 * 1. Create a TreeMap.
 *
 * 2. Traverse arr1 and insert every element into the map.
 *
 * 3. Traverse arr2 and insert every element into the map.
 *
 * 4. Traverse the keys of the map.
 *
 * 5. Store each key in the union result.
 *
 * Time Complexity:
 * O((n + m) log(n + m))
 *
 * Space Complexity:
 * O(n + m)
 *
 * Drawback:
 * The map requires additional memory and the logarithmic
 * insertion cost makes it slower than the two-pointer approach.
 *
 * ============================================================
 *
 * APPROACH 2 - USING SET
 *
 * Approach:
 * Use a TreeSet to store elements from both arrays.
 *
 * A Set does not allow duplicate elements and TreeSet keeps
 * the elements in ascending order.
 *
 * Algorithm:
 *
 * 1. Create a TreeSet.
 *
 * 2. Insert all elements from arr1.
 *
 * 3. Insert all elements from arr2.
 *
 * 4. Convert the TreeSet into a list.
 *
 * 5. Return the result.
 *
 * Time Complexity:
 * O((n + m) log(n + m))
 *
 * Space Complexity:
 * O(n + m)
 *
 * Drawback:
 * Additional memory is required for the Set.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH - TWO POINTERS
 *
 * Approach:
 * Since both arrays are already sorted, we can use two
 * pointers to find the union efficiently.
 *
 * Pointer i -> Traverses arr1.
 * Pointer j -> Traverses arr2.
 *
 * At every step, compare arr1[i] and arr2[j].
 *
 * ------------------------------------------------------------
 *
 * Algorithm:
 *
 * 1. Initialize two pointers:
 *
 *      i = 0
 *      j = 0
 *
 * 2. While both pointers are within their arrays:
 *
 *      If arr1[i] < arr2[j]:
 *          Add arr1[i] if it is not a duplicate.
 *          Move i.
 *
 *      If arr2[j] < arr1[i]:
 *          Add arr2[j] if it is not a duplicate.
 *          Move j.
 *
 *      If arr1[i] == arr2[j]:
 *          Add the element only once.
 *          Move both i and j.
 *
 * 3. After one array is completely traversed, process the
 *    remaining elements of the other array.
 *
 * 4. Skip duplicate elements while adding to the result.
 *
 * 5. Return the union.
 *
 * ============================================================
 *
 * TRACE:
 *
 * arr1 = [1, 2, 3, 4, 5]
 * arr2 = [2, 3, 4, 4, 5]
 *
 * Initially:
 *
 * i = 0
 * j = 0
 *
 * ------------------------------------------------------------
 *
 * Step 1:
 *
 * arr1[i] = 1
 * arr2[j] = 2
 *
 * 1 < 2
 *
 * Add 1.
 *
 * Union:
 * [1]
 *
 * Move i.
 *
 * ------------------------------------------------------------
 *
 * Step 2:
 *
 * arr1[i] = 2
 * arr2[j] = 2
 *
 * 2 == 2
 *
 * Add 2 only once.
 *
 * Union:
 * [1, 2]
 *
 * Move both i and j.
 *
 * ------------------------------------------------------------
 *
 * Step 3:
 *
 * arr1[i] = 3
 * arr2[j] = 3
 *
 * 3 == 3
 *
 * Add 3.
 *
 * Union:
 * [1, 2, 3]
 *
 * Move both pointers.
 *
 * ------------------------------------------------------------
 *
 * Step 4:
 *
 * arr1[i] = 4
 * arr2[j] = 4
 *
 * 4 == 4
 *
 * Add 4.
 *
 * Union:
 * [1, 2, 3, 4]
 *
 * Move both pointers.
 *
 * ------------------------------------------------------------
 *
 * Step 5:
 *
 * arr1[i] = 5
 * arr2[j] = 4
 *
 * 4 < 5
 *
 * 4 is already present in the union.
 * Therefore, do not add it again.
 *
 * Move j.
 *
 * ------------------------------------------------------------
 *
 * Step 6:
 *
 * arr1[i] = 5
 * arr2[j] = 5
 *
 * 5 == 5
 *
 * Add 5.
 *
 * Union:
 * [1, 2, 3, 4, 5]
 *
 * Move both pointers.
 *
 * ------------------------------------------------------------
 *
 * Both arrays are now completely traversed.
 *
 * Final Union:
 *
 * [1, 2, 3, 4, 5]
 *
 * ============================================================
 *
 * WHY DOES THE TWO-POINTER APPROACH WORK?
 *
 * Both arrays are already sorted.
 *
 * Therefore, the smaller element between arr1[i] and arr2[j]
 * can safely be added to the union first.
 *
 * If both elements are equal, only one copy is required.
 *
 * This allows us to process both arrays from left to right
 * without sorting or using a Set/Map.
 *
 * ============================================================
 *
 * TIME COMPLEXITY:
 * O(n + m)
 *
 * Each element of both arrays is visited at most once.
 *
 * SPACE COMPLEXITY:
 * O(n + m)
 *
 * The result list can contain up to n + m unique elements.
 *
 * Auxiliary Space:
 * O(1)
 *
 * The two-pointer algorithm itself uses only a constant
 * amount of extra space, excluding the output list.
 *
 * ============================================================
 *
 * Key Learning:
 *
 * - Union of arrays
 * - Two-pointer technique
 * - Working with two sorted arrays
 * - Handling duplicate elements
 * - Comparing elements from two arrays
 * - Maintaining sorted order
 * - Difference between auxiliary space and output space
 * - O(n + m) optimal traversal
 *
 * ============================================================
 */

public class D9_01_UnionOfTwoSortedArrays {

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {2, 3, 4, 4, 5};

        List<Integer> union = findUnion(arr1, arr2);

        System.out.println("Union of the two arrays: " + union);
    }

    public static List<Integer> findUnion(int[] arr1, int[] arr2) {

        List<Integer> union = new ArrayList<>();

        int i = 0;
        int j = 0;

        while (i < arr1.length && j < arr2.length) {

            if (arr1[i] < arr2[j]) {

                addIfNotDuplicate(union, arr1[i]);
                i++;

            } else if (arr2[j] < arr1[i]) {

                addIfNotDuplicate(union, arr2[j]);
                j++;

            } else {

                addIfNotDuplicate(union, arr1[i]);

                i++;
                j++;
            }
        }

        while (i < arr1.length) {

            addIfNotDuplicate(union, arr1[i]);
            i++;
        }

        while (j < arr2.length) {

            addIfNotDuplicate(union, arr2[j]);
            j++;
        }

        return union;
    }

    private static void addIfNotDuplicate(List<Integer> union, int value) {

        if (union.isEmpty()
                || union.get(union.size() - 1) != value) {

            union.add(value);
        }
    }
}