import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

public class MockInterview {
    public static void main(String[] args) {
        int[] input = {0,1,0,3,12};
        System.out.println(Arrays.toString(input));
        solution(input);
        System.out.println(Arrays.toString(input));
        input = new int[] {0};
        solution(input);
        System.out.println(Arrays.toString(input));
        input = new int[] {1,2,3};
        solution(input);
        System.out.println(Arrays.toString(input));
        input = new int[] {0,0,0};
        solution(input);
        System.out.println(Arrays.toString(input));
    }

    static void solution(int[] input) {
        // edge cases: sizes 0 or 1
        if (input.length <= 1) {
            return;
        }
        System.out.println("not edge case");
        // main algo
        // find 0s and shift everything to replace one at a time O(n^2)
        // traverse array to find count of 0's
        int zeroCount = 0;
        LinkedList<Integer> nonZeroVals = new LinkedList<>();
        for (int i = 0; i < input.length; i++) {
            if (input[i] == 0) {
                zeroCount++;
            } else {
                nonZeroVals.add(input[i]);
            }
        }
        if (zeroCount == 0 || nonZeroVals.isEmpty()) {
            return;
        }
        System.out.println("has [no] zeroes");
        // save appropriate number of non-zero values from end of array
        // placing non zero values
        for (int j = 0; j < nonZeroVals.size(); j++) {
            input[j] = nonZeroVals.get(j);
        }
        // put 0's at end of array
        int index = input.length - 1;
        while (zeroCount > 0) {
            input[index] = 0;
            index--;
            zeroCount--;
        }
    }
}



// b b b b b
//

// c   b
// set: cab















