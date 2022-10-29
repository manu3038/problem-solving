package algo.sorting;

import algo.Helper;

public class SelectionSort {
    public static void main(String[] args) {
        SelectionSort srtClass= new SelectionSort();
        int[] intArray = {26, 11, -46, -1, 78, 2, 7};
        srtClass.selectionSort(intArray);

    }

    public void selectionSort(int intArray[]){
        Helper helper = new Helper();
        for (int lastUnsrtIdx = intArray.length - 1; lastUnsrtIdx > 0; lastUnsrtIdx--) {
            int largest = 0; // considering first element as the largest in the array

            for (int i = 0; i <= lastUnsrtIdx; i++) {
                if (intArray[i] > intArray[largest]) { // check if there is another larger value than the assumed large value idx
                    largest = i; // finding the largest index in unsorted portion
                }
            }
            System.out.print("largest value in unsorted portion is " + intArray[largest] + "\nlast unsorted idx is " + lastUnsrtIdx + "\nafter swap ");
            helper.swap(intArray, largest, lastUnsrtIdx); // swap the largest idx value with the last unsorted idx value
            helper.printArr(intArray);
            System.out.println();
        }
    }
}
