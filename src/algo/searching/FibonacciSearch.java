package algo.searching;

/**
 * https://www.baeldung.com/cs/fibonacci-search -> refer this link for explanation
 */
public class FibonacciSearch {
    public static void main(String[] args) {

        int[] intArray = {4, 16, 29, 36, 47, 55, 67, 88, 99, 101, 119, 124}; // consider a sorted array for searching

        FibonacciSearch search = new FibonacciSearch();
        System.out.println(search.fibonacciSearch(intArray, 101));
        System.out.println(search.fibonacciSearch(intArray, 4));
    }

    public int fibonacciSearch(int[] data, int key) {
        int a = 0, b = 1;
        int c = a + b;
        while (c <= data.length) {
            a = b;
            b = c;
            c = b + a;
        }
        int offset = -1;
        while (a >= 0) {
            System.out.println("a = " + a + "; b = " + b + "; c = " + c + "; offset = " + offset);
            int idx = Integer.min(offset + a, data.length - 1);
            System.out.println("idx = " + idx + "; data[idx] = " + data[idx]);
            if (data[idx] == key) return idx;
            if (data[idx] > key) {
                c = a;
                b = b - a;
                a = c - b;
            } else {
                c = b;
                b = a;
                a = c - b;
                offset = idx;
            }
        }
        if (data[offset + 1] == key) {
            return offset + 1;
        }
        return -1;
    }
}
