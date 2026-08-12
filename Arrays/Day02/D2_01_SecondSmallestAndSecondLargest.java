package Day02;

/*
 * ============================================================
 * Day 02 - Problem 01
 * Second Smallest and Second Largest Element in an Array
 * ============================================================
 *
 * Problem Statement:
 * Given an array of integers, find the second smallest and
 * second largest distinct elements in the array.
 *
 * If a second smallest or second largest element does not
 * exist, return -1.
 *
 * ------------------------------------------------------------
 * Examples:
 *
 * Example 1:
 * Input  : [1, 2, 4, 7, 7, 5]
 * Output :
 * Second Smallest : 2
 * Second Largest  : 5
 *
 * Explanation:
 * The distinct elements in sorted order are:
 * [1, 2, 4, 5, 7]
 *
 * Therefore:
 * Second Smallest = 2
 * Second Largest  = 5
 *
 * ------------------------------------------------------------
 *
 * Example 2:
 * Input  : [1]
 * Output :
 * Second Smallest : -1
 * Second Largest  : -1
 *
 * Explanation:
 * There is only one distinct element, so a second smallest
 * and second largest element do not exist.
 *
 * ------------------------------------------------------------
 *
 * Example 3:
 * Input  : [5, 5, 5]
 * Output :
 * Second Smallest : -1
 * Second Largest  : -1
 *
 * Explanation:
 * There is only one distinct element in the array.
 *
 * ============================================================
 *
 * BRUTE FORCE APPROACH
 *
 * Approach:
 * Sort the array in ascending order.
 *
 * After sorting:
 * - The first distinct element is the smallest.
 * - The next distinct element is the second smallest.
 * - The last distinct element is the largest.
 * - The previous distinct element is the second largest.
 *
 * Algorithm:
 * 1. Sort the array in ascending order.
 * 2. Traverse from the beginning to find the second distinct
 *    smallest element.
 * 3. Traverse from the end to find the second distinct
 *    largest element.
 * 4. Return -1 if a second distinct element does not exist.
 *
 * Time Complexity:
 * O(n log n)
 *
 * Space Complexity:
 * Depends on the sorting implementation.
 *
 * Drawback:
 * Sorting the entire array is unnecessary because we only
 * need the second smallest and second largest elements.
 *
 * ============================================================
 *
 * BETTER APPROACH
 *
 * Approach:
 * Find the smallest and largest elements first.
 * Then perform another traversal to find the second smallest
 * and second largest elements.
 *
 * Algorithm:
 * 1. Find the smallest and largest elements.
 * 2. Traverse the array again.
 * 3. Find the smallest element greater than the smallest.
 * 4. Find the largest element smaller than the largest.
 * 5. Return -1 if either second element does not exist.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * Drawback:
 * The array needs to be traversed more than once.
 *
 * ============================================================
 *
 * OPTIMAL APPROACH
 *
 * Approach:
 * Find the second smallest and second largest elements in
 * a single traversal of the array.
 *
 * Maintain four variables:
 *
 * smallest       -> Smallest element found so far
 * secondSmallest -> Second smallest distinct element
 * largest        -> Largest element found so far
 * secondLargest  -> Second largest distinct element
 *
 * Algorithm:
 *
 * For every element in the array:
 *
 * Finding the smallest values:
 * 1. If the current element is smaller than smallest:
 *    - Move smallest to secondSmallest.
 *    - Update smallest with the current element.
 *
 * 2. Otherwise, if the current element is smaller than
 *    secondSmallest and different from smallest:
 *    - Update secondSmallest.
 *
 * Finding the largest values:
 * 3. If the current element is greater than largest:
 *    - Move largest to secondLargest.
 *    - Update largest with the current element.
 *
 * 4. Otherwise, if the current element is greater than
 *    secondLargest and different from largest:
 *    - Update secondLargest.
 *
 * 5. If a second distinct element does not exist, return -1.
 *
 * Time Complexity:
 * O(n)
 *
 * Space Complexity:
 * O(1)
 *
 * Why is this optimal?
 *
 * We only need one traversal of the array and maintain a
 * constant number of variables. Therefore, we avoid sorting
 * and avoid additional data structures.
 *
 * ============================================================
 *
 * Key Learning:
 * - Array traversal
 * - Finding second minimum and maximum values
 * - Handling duplicate elements
 * - Maintaining multiple variables during traversal
 * - Comparing brute force, better, and optimal approaches
 * - Time and space complexity analysis
 *
 * ============================================================
 */

public class D2_01_SecondSmallestAndSecondLargest {

    public static void main(String[] args) {
        int[] arr = {3, 5, 9, 7, 1, 2, 8, 1, 9, 4};

        int secondSmallest = findSecondSmallest(arr);
        int secondLargest = findSecondLargest(arr);

        System.out.println("The second smallest element in the array is: " + secondSmallest);
        System.out.println("The second largest element in the array is: " + secondLargest);
    }

    public static int findSecondSmallest(int[] arr) {
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < smallest) {
                secondSmallest = smallest;
                smallest = num;
            } else if (num < secondSmallest && num != smallest) {
                secondSmallest = num;
            }
        }

        return secondSmallest == Integer.MAX_VALUE ? -1 : secondSmallest;
    }

    public static int findSecondLargest(int[] arr) {
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest == Integer.MIN_VALUE ? -1 : secondLargest;
    }

}
