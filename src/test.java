import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class test {



    int[] sortByHeight(int[] a) {

    }


/*
Ticket numbers usually consist of an even number of digits.
A ticket number is considered lucky if the sum of the first half of the digits is equal to the sum of the second half.
Given a ticket number n, determine if it's lucky or not.

Example

For n = 1230, the output should be
isLucky(n) = true;
For n = 239017, the output should be
isLucky(n) = false.
 */
    boolean isLucky(int n) {
        String nstr = Integer.toString(n);
        char[] nA = nstr.toCharArray();
        int half1 = 0;
        int half2 = 0;
        for (int i = 0; i < nA.length; i++) {
            if(i<nA.length/2){
                int temp = nA[i];
                half1 += temp;
            }else{
                int temp = nA[i];
                half2 += temp;
            }
        }
        if(half1==half2){
            return true;
        }else{
            return false;
        }
    }

    int commonCharacterCount(String s1, String s2) {
        char[] A1 =  s1.toCharArray();
        char[] A2 = s2.toCharArray();
        List<Character> listC1 = new ArrayList<Character>();
        for (char c : A1) {
            listC1.add(c);
        }
        List<Character> listC2 = new ArrayList<Character>();
        for (char c : A2) {
            listC2.add(c);
        }
        int count=0;
        for (int i = 0; i < listC1.size(); i++) {
            for (int j = 0; j < listC2.size(); j++) {
                if(listC2.get(j) == listC1.get(i)){
                    count++;
                    listC2.set(j,'.');//use '.' as marker finished.
                    break;
                }
            }
        }
        return count;
    }

    String[] allLongestStrings(String[] inputArray) {
        int maxlength = 0;
        for (int i = 0; i < inputArray.length; i++) {
            if(inputArray[i].length()>maxlength) {
                maxlength = inputArray[i].length();
            }
        }
        int finalMaxlength = maxlength;
        return Arrays.stream(inputArray).filter(el -> el.length() == finalMaxlength).toArray(String[]::new);
    }


    int matrixElementsSum(int[][] matrix) {
        int total = 0;
        for(int i=0; i<matrix[0].length; i++){//column
            for (int j = 0; j < matrix.length; j++) {//row
                if(matrix[j][i]==0){
                    break;
                }else{
                    total += matrix[j][i];
                }
            }
        }
        return total;
    }


    boolean almostIncreasingSequence(int[] sequence) {
        int count = 0;
        for(int i=1; i<=sequence.length;i++){
            if (sequence[i]<=sequence[i-1]){
                count++;

                if(count>1){
                    return false;
                }

                if(sequence[i] <= sequence[i-2] && sequence[i+1] <= sequence[i-1]) {
                    return false;
                }
            }
        }


        return true;
    }


    public static void main(String[] args) {
        int[] arr = new int[]{1,2,4,5,6,22,1,3};

        //Array to list
        List<Integer> arrList = new ArrayList<>(arr.length);
        for (int i : arr) {
         arrList.add(i);
        }
        System.out.println(arrList);

        //Sort list
        Collections.sort(arrList);
        System.out.println(arrList);

        int[][] matrix =  { { 0, 1, 1, 2 }, { 0, 5, 0, 0} , { 2, 1, 3, 10 } };
        test ne = new test();
        System.out.println("column: "+matrix[0].length+" row: "+matrix.length);
        System.out.println(ne.matrixElementsSum(matrix));
    }
}
