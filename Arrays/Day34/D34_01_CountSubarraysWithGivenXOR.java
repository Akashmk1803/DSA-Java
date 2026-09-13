package Day34;

import java.util.HashMap;
import java.util.Map;

/*
 * Day 34: Count Subarrays with Given XOR K
 *
 * Problem Statement:
 * ------------------
 * Given an integer array nums and an integer k,
 * find the total number of contiguous subarrays whose
 * XOR of all elements is equal to k.
 *
 *
 * Example:
 *
 * Input:
 * nums = [4, 2, 2, 6, 4]
 * k = 6
 *
 * Output:
 * 4
 *
 * Valid subarrays:
 *
 * [4, 2]
 * [4, 2, 2, 6, 4]
 * [2, 2, 6]
 * [6]
 *
 *
 * ------------------------------------------------------------
 * IMPORTANT XOR PROPERTIES
 * ------------------------------------------------------------
 *
 * XOR has some very useful properties:
 *
 * 1. x ^ x = 0
 *
 * 2. x ^ 0 = x
 *
 * 3. XOR is reversible:
 *
 *      If:
 *
 *      a ^ b = c
 *
 *      then:
 *
 *      a = b ^ c
 *
 *
 * This third property is the key to this problem.
 *
 *
 * ------------------------------------------------------------
 * APPROACH 1: BRUTE FORCE
 * ------------------------------------------------------------
 *
 * Generate every possible subarray.
 *
 * For every starting index i:
 *
 *      xor = 0
 *
 *      For every ending index j:
 *
 *          xor = xor ^ nums[j]
 *
 *          if xor == k:
 *              count++
 *
 *
 * This avoids recalculating the XOR from scratch because
 * we keep extending the current subarray.
 *
 *
 * Time Complexity:
 * O(n^2)
 *
 * Space Complexity:
 * O(1)
 *
 *
 * ------------------------------------------------------------
 * OPTIMAL APPROACH
 * ------------------------------------------------------------
 *
 * Use:
 *
 *      Prefix XOR + HashMap
 *
 *
 * Suppose the XOR from the beginning of the array up to
 * the current index is:
 *
 *      prefixXor
 *
 *
 * Suppose an earlier prefix XOR is:
 *
 *      previousXor
 *
 *
 * The XOR of the subarray between them is:
 *
 *      previousXor ^ prefixXor
 *
 *
 * We want this to equal k:
 *
 *      previousXor ^ prefixXor = k
 *
 *
 * XOR both sides with prefixXor:
 *
 *      previousXor = prefixXor ^ k
 *
 *
 * Therefore, while traversing the array:
 *
 *      requiredXor = prefixXor ^ k
 *
 * If requiredXor has appeared before, every occurrence
 * represents a subarray ending at the current index
 * whose XOR is k.
 *
 *
 * ------------------------------------------------------------
 * WHY DO WE STORE FREQUENCY?
 * ------------------------------------------------------------
 *
 * This problem asks for the NUMBER of subarrays.
 *
 * Therefore, we don't just need to know whether a prefix XOR
 * exists.
 *
 * We need to know HOW MANY TIMES it has appeared.
 *
 * Example:
 *
 * If:
 *
 * requiredXor = 5
 *
 * and the HashMap says:
 *
 * 5 -> 3
 *
 * then there are 3 different starting positions that produce
 * a subarray ending at the current index with XOR k.
 *
 * Therefore:
 *
 *      count += frequency
 *
 *
 * ------------------------------------------------------------
 * WHY DO WE INITIALIZE 0 WITH FREQUENCY 1?
 * ------------------------------------------------------------
 *
 * We start with:
 *
 *      prefixXor = 0
 *
 * before processing any element.
 *
 * So:
 *
 *      map.put(0, 1);
 *
 *
 * This handles subarrays that start from index 0.
 *
 *
 * Example:
 *
 * nums = [5, 2]
 * k = 7
 *
 * At index 1:
 *
 * prefixXor = 5 ^ 2
 *           = 7
 *
 * requiredXor = 7 ^ 7
 *             = 0
 *
 * 0 exists in the map.
 *
 * Therefore the subarray:
 *
 * [5, 2]
 *
 * has XOR 7.
 *
 *
 * ------------------------------------------------------------
 * ALGORITHM
 * ------------------------------------------------------------
 *
 * 1. Create a HashMap:
 *
 *      prefixXor -> frequency
 *
 * 2. Initialize:
 *
 *      map.put(0, 1)
 *
 * 3. Set:
 *
 *      prefixXor = 0
 *      count = 0
 *
 * 4. Traverse the array.
 *
 * 5. Update:
 *
 *      prefixXor = prefixXor ^ nums[i]
 *
 * 6. Calculate:
 *
 *      requiredXor = prefixXor ^ k
 *
 * 7. If requiredXor exists in the map:
 *
 *      count += its frequency
 *
 * 8. Add the current prefixXor to the map:
 *
 *      frequency + 1
 *
 * 9. Return count.
 *
 *
 * ------------------------------------------------------------
 * TIME COMPLEXITY
 * ------------------------------------------------------------
 *
 * We traverse the array once.
 *
 * HashMap insertion and lookup take O(1) average time.
 *
 * Therefore:
 *
 *      O(n) average
 *
 *
 * ------------------------------------------------------------
 * SPACE COMPLEXITY
 * ------------------------------------------------------------
 *
 * In the worst case, there can be O(n) different prefix XORs.
 *
 * Therefore:
 *
 *      O(n)
 *
 *
 * ------------------------------------------------------------
 * KEY LEARNING
 * ------------------------------------------------------------
 *
 * For SUM:
 *
 *      previousPrefix = currentPrefix - k
 *
 *
 * For XOR:
 *
 *      previousPrefixXor = currentPrefixXor ^ k
 *
 *
 * The pattern is similar:
 *
 * PREFIX + HASHMAP
 *
 * But the mathematical operation changes.
 */
public class D34_01_CountSubarraysWithGivenXOR {

    public static void main(String[] args) {

        int[] nums = {4, 2, 2, 6, 4};
        int k = 6;

        int result = countSubarrays(nums, k);

        System.out.println("Number of subarrays with XOR " + k
                + ": " + result);
    }


    /*
     * Optimal Approach:
     *
     * Prefix XOR + HashMap
     *
     * Time Complexity:
     * O(n) average
     *
     * Space Complexity:
     * O(n)
     */
    public static int countSubarrays(int[] nums, int k) {

        /*
         * HashMap stores:
         *
         * Prefix XOR -> Number of times it has appeared
         */
        Map<Integer, Integer> prefixXorFrequency = new HashMap<>();

        /*
         * Prefix XOR 0 exists once before the array starts.
         *
         * This handles subarrays beginning at index 0.
         */
        prefixXorFrequency.put(0, 1);

        int prefixXor = 0;
        int count = 0;

        /*
         * Traverse the array.
         */
        for (int num : nums) {

            /*
             * Calculate the XOR from the beginning
             * up to the current element.
             */
            prefixXor = prefixXor ^ num;

            /*
             * We need an earlier prefix XOR such that:
             *
             * previousXor ^ prefixXor = k
             *
             * Therefore:
             *
             * previousXor = prefixXor ^ k
             */
            int requiredXor = prefixXor ^ k;

            /*
             * If requiredXor has appeared before,
             * every occurrence gives us one valid
             * subarray ending at the current position.
             */
            if (prefixXorFrequency.containsKey(requiredXor)) {

                count += prefixXorFrequency.get(requiredXor);
            }

            /*
             * Store/update the frequency of the current
             * prefix XOR.
             */
            prefixXorFrequency.put(
                    prefixXor,
                    prefixXorFrequency.getOrDefault(prefixXor, 0) + 1
            );
        }

        return count;
    }
}


/*
 * ------------------------------------------------------------
 * TRACE
 * ------------------------------------------------------------
 *
 * Input:
 *
 * nums = [4, 2, 2, 6, 4]
 * k = 6
 *
 *
 * Initially:
 *
 * prefixXor = 0
 * count = 0
 *
 * HashMap:
 *
 * {0 = 1}
 *
 *
 * ------------------------------------------------------------
 *
 * i = 0
 * num = 4
 *
 * prefixXor:
 *
 * 0 ^ 4 = 4
 *
 * requiredXor:
 *
 * 4 ^ 6 = 2
 *
 * 2 is not in the map.
 *
 * Store:
 *
 * 4 -> 1
 *
 *
 * ------------------------------------------------------------
 *
 * i = 1
 * num = 2
 *
 * prefixXor:
 *
 * 4 ^ 2 = 6
 *
 * requiredXor:
 *
 * 6 ^ 6 = 0
 *
 * 0 exists with frequency 1.
 *
 * count = 1
 *
 * Valid subarray:
 *
 * [4, 2]
 *
 *
 * Store:
 *
 * 6 -> 1
 *
 *
 * ------------------------------------------------------------
 *
 * i = 2
 * num = 2
 *
 * prefixXor:
 *
 * 6 ^ 2 = 4
 *
 * requiredXor:
 *
 * 4 ^ 6 = 2
 *
 * 2 is not in the map.
 *
 * Store:
 *
 * 4 -> 2
 *
 *
 * ------------------------------------------------------------
 *
 * i = 3
 * num = 6
 *
 * prefixXor:
 *
 * 4 ^ 6 = 2
 *
 * requiredXor:
 *
 * 2 ^ 6 = 4
 *
 * 4 exists with frequency 2.
 *
 * Therefore:
 *
 * count += 2
 *
 * count = 3
 *
 * Two valid subarrays end at index 3:
 *
 * [2, 2, 6]
 *
 * [6]
 *
 *
 * ------------------------------------------------------------
 *
 * i = 4
 * num = 4
 *
 * prefixXor:
 *
 * 2 ^ 4 = 6
 *
 * requiredXor:
 *
 * 6 ^ 6 = 0
 *
 * 0 exists with frequency 1.
 *
 * count = 4
 *
 * Valid subarray:
 *
 * [4, 2, 2, 6, 4]
 *
 *
 * ------------------------------------------------------------
 *
 * FINAL ANSWER:
 *
 * 4
 *
 *
 * Valid subarrays:
 *
 * [4, 2]
 * [4, 2, 2, 6, 4]
 * [2, 2, 6]
 * [6]
 *
 *
 * ------------------------------------------------------------
 * QUICK MEMORY TRICK
 * ------------------------------------------------------------
 *
 * For Sum K:
 *
 *      needed = prefixSum - k
 *
 *
 * For XOR K:
 *
 *      needed = prefixXor ^ k
 *
 *
 * And for counting:
 *
 *      count += frequency of needed
 *
 *
 * Always initialize:
 *
 *      map.put(0, 1);
 *
 *
 * This is the key pattern.
 */