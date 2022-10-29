package dataStructures.tree;

import java.util.LinkedList;

/**
 * Linked list implementation of the Max heap tree
 *
 * @param <T>
 */
public class MaxHeap<T extends Comparable> {
    LinkedList<T> heapList = new LinkedList<>();

    public void addToHeap(T item) {
        heapList.add(item);
        fixHeapAbove(heapList.size());
    }

    public void deleteFromHeap(T item) {
        int idx = heapList.indexOf(item);
        if (!heapList.isEmpty()) {
            int parentIdx = getParentIdx(idx);
            T replacementValue = heapList.removeLast();
            heapList.set(idx, replacementValue); // swap element to delete with last value to maintain the complete Binary tree structure

            if (idx == 0 || replacementValue.compareTo(heapList.get(parentIdx)) <= 0) { // if root or if the replacement value is less than parent then fix the below as above tree stays intact.
                fixHeapBelow(idx, heapList.size() - 1);
            } else {
                fixHeapAbove(idx);
            }
        }
    }

    public void sortHeap() {
        int lastHeapIdx = heapList.size() - 1;
        for (int i = 0; i < lastHeapIdx; i++) {
            T temp = heapList.get(0);
            heapList.set(0, heapList.get(lastHeapIdx - i));
            heapList.set(lastHeapIdx - i, temp);

            fixHeapBelow(0, lastHeapIdx - i - 1);
        }
    }

    private void fixHeapAbove(int size) {
        int idx = size - 1;
        T newValue = heapList.get(idx);
        while (idx > 0 && heapList.get(getParentIdx(idx)).compareTo(newValue) < 0) {
            heapList.set(idx, heapList.get(getParentIdx(idx)));
            idx = getParentIdx(idx);
        }
        heapList.set(idx, newValue);
    }

    private void fixHeapBelow(int idx, int heapLastIdx) {
        int childToSwap;
        while (idx <= heapLastIdx) {

            int leftChild = getChild(idx, true);
            int rightChild = getChild(idx, false);

            if (leftChild <= heapLastIdx) { // left child is present for the idx
                if (rightChild > heapLastIdx) { // there is only left child. Right child idx is after the last idx
                    childToSwap = leftChild;
                } else { // if both left and right child is present then swap with the highest of both the children
                    T left = heapList.get(leftChild);
                    T right = heapList.get(rightChild);
                    childToSwap = left.compareTo(right) < 0 ? rightChild : leftChild; // compare left and right values return whichever is bigger idx
                }
                if (heapList.get(idx).compareTo(heapList.get(childToSwap)) < 0) { // if the replaced value is less than child to swap then swap the elements
                    T temp = heapList.get(idx);
                    heapList.set(idx, heapList.get(childToSwap));
                    heapList.set(childToSwap, temp);
                } else {
                    break;
                }
                idx = childToSwap; // repeat till the idx doesn't have any children or reach end heap end
            } else {
                break; // no children for idx so end the iteration
            }
        }
    }

    private int getParentIdx(int idx) {
        return (idx - 1) >> 1;
    }

    private int getChild(int idx, boolean left) {
        return (2 * idx) + (left ? 1 : 2);
    }
}
