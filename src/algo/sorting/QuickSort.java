package algo.sorting;

import algo.Helper;

public class QuickSort {
    private Helper helper = new Helper();

    public static void main(String[] args) {
        QuickSort sort = new QuickSort();
        int[] intArray = {26, 11, -46, -1, 78, 2, 7};
        sort.helper.printArr(intArray);
        sort.quickSortRecursive(intArray, 0, intArray.length);
    }

    public void quickSortRecursive(int arr[], int low, int high) {
        if (high - low < 2)  return; // break if you encounter one element array
        if (low < high) {
            int partitionPosition = this.partitionArr(arr, low, high);
            quickSortRecursive(arr, low, partitionPosition); // sort left side
            quickSortRecursive(arr, partitionPosition + 1, high); // sort right side
//            System.out.println(partitionPosition);
        }
    }

    private int partitionArr(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;
        while (i < j) {
            do { // move i pointer forward
                i++;
            } while (arr[i] <= pivot);

            do { // move j pointer backward
                j--;
            } while (arr[j] > pivot);

            if (i < j) { // swap if element violates the sort cond
                helper.swap(arr, i, j);
            }
        }
        helper.swap(arr, low, j);
        helper.printArr(arr);
        return j;
    }
}
