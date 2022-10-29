package algo.searching;

import algo.sorting.SelectionSort;

public class LinearSearch {
    public static void main(String[] args) {
        int[] intArray = {10, 45, -1, 56, 71, 0, 22, 4};
        System.out.println(sequentialSearch(intArray, 9));
        System.out.println(sequentialSearch(intArray, 4));
        System.out.println(sequentialSearch(intArray, 0));
        System.out.println(sequentialSearch(intArray, 456));
        System.out.println(sequentialSearch(intArray, -1));

        SelectionSort sort = new SelectionSort();
        sort.selectionSort(intArray); // sort the array before searching

        System.out.println(sortedSequentialSearch(intArray, 9));
        System.out.println(sortedSequentialSearch(intArray, 4));
        System.out.println(sortedSequentialSearch(intArray, 0));
        System.out.println(sortedSequentialSearch(intArray, 456));
        System.out.println(sortedSequentialSearch(intArray, -1));
    }

    // search in unsorted array
    static int sequentialSearch(int[] intArray, int srchVal) {
        for (int i = 0; i < intArray.length; i++) { // loop through all elements
            if (intArray[i] == srchVal) { // check if the intArray has the search value (srchVal)
                return i; // return idx if srchVal is found
            }
        }
        return -1;
    }

    // search for asc sorted array
    static int sortedSequentialSearch(int[] intArray, int srchVal) {
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == srchVal) {
                return i;
            } else if (intArray[i] > srchVal) {
                return -1;
            }
        }
        return -1;
    }
}
