package algo.sorting;

import algo.Helper;

/*
Bubble sort:

Two pointer and stable algorithm. O(n^2) tim complexity.

the outer loop keep track of the sorted portion.
the inner loop will find the heaviest element in unsorted portion and push to last of the array and increment the sorted portion index.

 */
public class BubbleSort {
    public static void main(String[] args) {
        BubbleSort srtClass = new BubbleSort();
        int[] intArray = {26, 11, -46, -1, 78, 2, 7};
        srtClass.bubbleSort(intArray);
    }

    public void bubbleSort(int[] intArray) {
        Helper helper = new Helper();
        for (int lstUnsrtIdx = intArray.length - 1; lstUnsrtIdx > 0; lstUnsrtIdx--) { // outer loop
            for (int i = 0; i < lstUnsrtIdx; i++) { // inner loop
                System.out.print("last unsorted index = " + lstUnsrtIdx + "; i = " + i + "; intArr[i] = " + intArray[i] + " and intArr[i+1] = " + intArray[i + 1] + ";--> ");
                if (intArray[i + 1] < intArray[i]) {
                    System.out.print(" After swapping => ");
                    helper.swap(intArray, i, i + 1); // swap smaller element with larger element
                    helper.printArr(intArray);
                }
                System.out.println();
            }
        }
    }

}
