package dataStructures.tree;

public class MaxHeapTest {
    public static void main(String[] args) {
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>();
        maxHeap.addToHeap(10);
        maxHeap.addToHeap(15);
        maxHeap.addToHeap(32);
        maxHeap.addToHeap(36);
        maxHeap.addToHeap(42);
        maxHeap.addToHeap(51);
        maxHeap.addToHeap(24);
//        maxHeap.addToHeap(48);
        System.out.println(maxHeap.heapList);

        maxHeap.deleteFromHeap(maxHeap.heapList.getFirst()); // remove the root.
        System.out.println(maxHeap.heapList);
//        maxHeap.deleteFromHeap(maxHeap.heapList.getFirst()); // remove the root.
//        System.out.println(maxHeap.heapList);
//        maxHeap.deleteFromHeap(maxHeap.heapList.getFirst()); // remove the root.
//        System.out.println(maxHeap.heapList);
//        maxHeap.deleteFromHeap(maxHeap.heapList.getFirst()); // remove the root.
//        System.out.println(maxHeap.heapList);

        maxHeap.sortHeap(); // sort the above heap --> this is the heap sort algorithm
        System.out.println(maxHeap.heapList);
    }
}
