package probems;

/**
 * https://practice.geeksforgeeks.org/problems/equilibrium-point-1587115620
 * find the equilibrium point in the array.
 * if the left sum and right sum are same then that index is the equilibrium index.
 * find the problem description in the above link
 */
public class EquilibriumIndex {
    public static void main(String[] args) {
        int arr[] = { -7, 1, 5, 2, -4, 3, 0 };
        int arr_size = arr.length;
        int idx = equilibrium(arr,arr_size);
        System.out.println(idx);
    }

    /**
     * create a prefix sum array for the input array. Refer to "PREFIX SUM ALGORITHM"
     * @param inputArr is the input array
     * @param size the size of the array
     * @return the array with prefix sum of the elements in each idx
     */
    static int[] getPrefixSumArr(int[] inputArr, int size){
        int[] prefixSum = new int[size];
        prefixSum[0] = inputArr[0];
        for (int i = 1; i < size; i++) {
            prefixSum[i] = prefixSum[i-1] + inputArr[i];
        }
        return prefixSum;
    }

    /**
     *
     * @param inputArr is the input array for which the equilibrium index is to be found.
     * @param size the size of the array
     * @return the equilibrium index of the input array if present else returns -1
     */
    static int equilibrium(int[] inputArr, int size){
        int[] prefixSumArr = getPrefixSumArr(inputArr, size);
        int totalSum = prefixSumArr[size-1];
        for (int i = 1; i < size; i++) {
            int rightSum = totalSum - prefixSumArr[i];
            if(prefixSumArr[i-1] == rightSum){
                return i; // this is the equilibrium index
            }
        }
        return -1;
    }
}
