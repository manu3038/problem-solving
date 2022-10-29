package algo.sorting;

import algo.Helper;

public class MergeSort {
    private Helper helper = new Helper();

    public static void main(String[] args) {
        MergeSort sort = new MergeSort();
        int[] intArray = {26, 11, -46, -1, 78, 2, 7};
        sort.helper.printArr(intArray);
//        sort.recursiveMergeSort(intArray, intArray.length);
        sort.mergeSort(intArray, 0, intArray.length);
        sort.helper.printArr(intArray);
    }

    public void recursiveMergeSort(int[] arr, int n) {
        if (n < 2) return; // break if you encounter one element array
        int mid = (n) >> 1; // bitwise operator for diving by 2
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        for (int i = 0; i < mid; i++) { // construct left sub-array
            l[i] = arr[i];
        }

        for (int i = mid; i < n; i++) { // construct right sub-array
            r[i - mid] = arr[i];
        }

        recursiveMergeSort(l, mid); // divide the left array further
        recursiveMergeSort(r, n - mid); // divide the right array further till the size is 1
        this.mergeArr(arr, l, r, mid, n - mid); // merge the left and right array use there last indexes to input array.

    }

    private void mergeArr(int[] input, int[] leftArr, int[] rightArr, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) { // choose the smallest of the both the array and add it to input array
            if (leftArr[i] <= rightArr[j]) {
                input[k++] = leftArr[i++];
            } else {
                input[k++] = rightArr[j++];
            }
        }

        // if pointer hasn't hit the end of the array add those to the last of input array.
        while (i < left) {
            input[k++] = leftArr[i++];
        }
        while (j < right) {
            input[k++] = rightArr[j++];
        }
    }

    // { 20, 35, -15, 7, 55, 1, -22 }
    public void mergeSort(int[] input, int start, int end) {

        if (end - start < 2) {
            return;
        }

        int mid = (start + end) >> 1;
        mergeSort(input, start, mid);
        mergeSort(input, mid, end);
        merge(input, start, mid, end);
    }

    // { 20, 35, -15, 7, 55, 1, -22 }
    private void merge(int[] input, int start, int mid, int end) {

        if (input[mid - 1] <= input[mid]) {
            return;
        }

        int i = start;
        int j = mid;
        int tempIndex = 0;

        int[] temp = new int[end - start];
        while (i < mid && j < end) {
            temp[tempIndex++] = input[i] <= input[j] ? input[i++] : input[j++];
        }

        System.arraycopy(input, i, input, start + tempIndex, mid - i);
        helper.printArr(input);
        System.out.println();
        System.arraycopy(temp, 0, input, start, tempIndex);
        helper.printArr(input);


    }
}
