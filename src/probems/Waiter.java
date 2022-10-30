package probems;

import java.math.BigInteger;
import java.util.*;

/**
 * https://www.hackerrank.com/challenges/waiter/problem?isFullScreen=false
 * refer the above link for the problem description.
 */
public class Waiter {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 3, 4, 4, 9);
        int q = 2;
        List<Integer> ans = waiter(numbers,q);
        System.out.println(ans);
    }
    public static List<Integer> waiter(List<Integer> number, int q) {
        int prime = 2;
        LinkedList<Integer> input = new LinkedList<>(number);
        LinkedList<Integer> ans = new LinkedList<>();
        LinkedList<Integer> A = new LinkedList<>();
        LinkedList<Integer> B;

        while (q > 0){
            A = new LinkedList<>();
            B = new LinkedList<>();

            while(!input.isEmpty()){
                Integer ele = input.pop();
                if(ele % prime == 0){
                    B.push(ele);
                } else {
                    A.push(ele);
                }
            }

            input.addAll(A);
            Collections.reverse(B);
            ans.addAll(B);

            BigInteger primeint = new BigInteger(Integer.toString(prime));
            BigInteger result = primeint.nextProbablePrime();
            prime = Integer.parseInt(result.toString());

            q--;
        }

        Collections.reverse(A);
        ans.addAll(A);

        return ans;
    }
}
