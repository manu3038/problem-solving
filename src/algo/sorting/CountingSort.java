package algo.sorting;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CountingSort {
    public static void main(String[] args) {
        List<Integer> input = new LinkedList<>(Arrays.asList(10, 2,  10)) ;


        CountingSort sort = new CountingSort();
        sort.countingSortImpl(input);
    }

    private List<Integer> createCountArray(List<Integer> input, Integer max, Integer min) {

        IntSupplier sup = () -> 0;
        List<Integer> countArr = IntStream.generate(sup).limit((max - min)+1).boxed().collect(Collectors.toList());

        // find the frequency of each element
        for(Integer i : input){
            countArr.set(i, countArr.get(i) + 1);
        }

        // prefix sum array to find out what is the count of elements less than the given index element
        for (int i = 1; i < countArr.size(); i++){
            countArr.set(i, countArr.get(i) + countArr.get(i-1));
        }

        System.out.println(countArr);
        return countArr;
    }

    public List<Integer> countingSortImpl(List<Integer> input) {
        Integer maxVal = Collections.max(input);
        Integer minVal = Collections.min(input);

        List<Integer> countArr = createCountArray(input,maxVal,minVal);

        return countArr;
    }
}
