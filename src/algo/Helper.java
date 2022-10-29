package algo;

public class Helper {
    public void swap(int[] intArray, int first, int next){
        if (first == next) return;
        int temp = intArray[first];
        intArray[first] = intArray[next];
        intArray[next] = temp;
    }
    public void printArr(int intArray[]){
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i] + " ");
        }
        System.out.println();
    }
}
