package algo.searching;

import algo.sorting.SelectionSort;

/**
 * Similar to Binary search only difference is partition index will be calc using below formula
 * loc = lowEnd + (highEnd - lowEnd) * (item - data[lowEnd]) / (data[highEnd] - data[lowEnd])
 * item - value to search
 * highEnd - the highest value index
 * lowEnd - the lowest value index
 * data - sorted input array
 */
public class InterpolationSearch {
    public static void main(String[] args) {
        SelectionSort srtClass = new SelectionSort();
        int[] intArray = {26, 11, -46, -1, 78, 2, 7, 52, 98, 156, 20, 18};
        srtClass.selectionSort(intArray);
        InterpolationSearch search = new InterpolationSearch();
//        System.out.println(search.interpolationSearchImpl(intArray, 2));
//        System.out.println(search.interpolationSearchImpl(intArray, -14));
        System.out.println(search.interpolationSearchIterative(intArray, 78));
        System.out.println(search.interpolationSearchImpl(intArray, 54));
    }

    /**
     * Recursive implementation of interpolation search algorithm
     * @param intArray sorted input list
     * @param searchValue value to be searched
     * @return item index value if present else -1
     */
    public int interpolationSearchImpl(int[] intArray, int searchValue) {
        System.out.println("value to search is " + searchValue);
        return interpolationSearching(intArray, searchValue, 0, intArray.length - 1);
    }

    private int interpolationSearching(int[] arr, int searchValue, int l, int u) {
        if (l <= u && searchValue >= arr[l] && searchValue <= arr[u]) { // check if the search value lies between highest and lowest value and low is lesser than upper
            int loc = (((searchValue - arr[l]) * (u - l)) / (arr[u] - arr[l])) + l;

            System.out.println("location for partition is " + loc + " value is " + arr[loc]);
            if (arr[loc] == searchValue) return loc;
            if (arr[loc] > searchValue) {
                return interpolationSearching(arr, searchValue, l, loc - 1);
            } else {
                return interpolationSearching(arr, searchValue, loc + 1, u);
            }
        }
        return -1;
    }

    /**
     * iterative implementation on interpolation search algorithm
     * @param data sorted input list
     * @param item value to be searched
     * @return item index value if present else -1
     */
    public int interpolationSearchIterative(int[] data, int item) {
        System.out.println("value to search is " + item);
        int highEnd = (data.length - 1);
        int lowEnd = 0;

        while (item >= data[lowEnd] && item <= data[highEnd] && lowEnd <= highEnd) {
            int probe = lowEnd + (highEnd - lowEnd) * (item - data[lowEnd]) / (data[highEnd] - data[lowEnd]);
            System.out.println("location for partition is " + probe + " value is " + data[probe]);
            if (highEnd == lowEnd) {
                if (data[lowEnd] == item) {
                    return lowEnd;
                } else {
                    return -1;
                }
            }

            if (data[probe] == item)  return probe;
            if (data[probe] < item) {
                lowEnd = probe + 1;
            } else {
                highEnd = probe - 1;
            }
        }
        return -1;
    }
}
