package algo.searching;

import algo.sorting.SelectionSort;

public class BinarySearch {
    public static void main(String[] args) {
        BinarySearch search = new BinarySearch();
        int[] intArray = {89, 23, 56, 93, 5, -4, -14, 58};

        SelectionSort sort = new SelectionSort();
        sort.selectionSort(intArray); // sort the array before searching

        // iterative search works better for performance, recursive has overhead of calling functions
        System.out.println(search.iterativeSearchImpl(intArray, -2));
        System.out.println(search.iterativeSearchImpl(intArray, -14));
        System.out.println(search.iterativeSearchImpl(intArray, 93));
        System.out.println(search.iterativeSearchImpl(intArray, 0));
//
//        System.out.println(search.recursiveSearchImpl(intArray, -2));
//        System.out.println(search.recursiveSearchImpl(intArray, -14));
//        System.out.println(search.recursiveSearchImpl(intArray, 93));
//        System.out.println(search.recursiveSearchImpl(intArray, 0));
    }

    public int iterativeSearchImpl(int[] input, int searchValue) {

        int start = 0, end = input.length;
        while (start < end) {
            int mid = (start + end) / 2; // split the array into half
            if (input[mid] == searchValue) return mid;
            if (input[mid] > searchValue) {
                end = mid; // if mid-value is greater than the search value the look at left of the array
            } else {
                start = mid + 1; // if mid-value is lesser than the search value look at the right of the array
            }
        }
        return -1;
    }

    public int recursiveSearchImpl(int[] input, int searchValue) {
        return recursiveSearch(input, 0, input.length, searchValue);
    }

    private int recursiveSearch(int[] input, int start, int end, int searchValue) {
        if (start >= end) return -1;
        int mid = (start + end) / 2;
        if (input[mid] == searchValue) return mid;
        if (input[mid] > searchValue){
            return recursiveSearch(input, start, mid, searchValue);
        } else {
            return recursiveSearch(input, mid+1, end, searchValue);
        }
    }
}
