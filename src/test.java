import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {

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

        System.out.println(ne.palindromeRearranging("aabb"));
        System.out.println(ne.areEquallyStrong(15,10,15,10));
        System.out.println(ne.isIPv4Address(".254.255.0"));
    }
/*
Check if all digits of the given integer are even.
Example
For n = 248622, the output should be
evenDigitsOnly(n) = true;
For n = 642386, the output should be
evenDigitsOnly(n) = false.
Input/Output
[execution time limit] 3 seconds (java)
[input] integer n
Guaranteed constraints:
1 ≤ n ≤ 109.
[output] boolean
true if all digits of n are even, false otherwise.
 */
boolean evenDigitsOnly(int n) {
    String temp = Integer.toString(n);
    char[] strAry = temp.toCharArray();
    for (char s: strAry) {
        int cur = Integer.parseInt(Character.toString(s));
        if(cur%2!=0){
            return false;
        }
    }
    return true;
}


/*
Given an array of integers, replace all the occurrences of elemToReplace with substitutionElem.

Example

For inputArray = [1, 2, 1], elemToReplace = 1, and substitutionElem = 3, the output should be
arrayReplace(inputArray, elemToReplace, substitutionElem) = [3, 2, 3].
 */
int[] arrayReplace(int[] inputArray, int elemToReplace, int substitutionElem) {
    for (int i = 0; i < inputArray.length; i++) {
        if(inputArray[i]==elemToReplace){
            inputArray[i] = substitutionElem;
        }
    }
    return inputArray;
}


/*
In the popular Minesweeper game you have a board with some mines and those cells that don't contain a mine have a number in it that indicates the total number of mines in the neighboring cells. Starting off with some arrangement of mines we want to create a Minesweeper game setup.
Example
For
matrix = [[true, false, false],
          [false, true, false],
          [false, false, false]]
the output should be
minesweeper(matrix) = [[1, 2, 1],
                       [2, 1, 1],
                       [1, 1, 1]]
 */

    int[][] minesweeper(boolean[][] matrix) {
        int rowL = matrix.length;
        int colL = matrix[0].length;
        int[][] sol = new int[rowL][colL];
        for (int i = 0; i < rowL; i++) {
            for (int j = 0; j < colL; j++) {
                sol[i][j] = findNeighbors(matrix,i,j);
            }
        }
        return sol;
    }

    int findNeighbors(boolean[][] matrix,int i,int j){
        int count = 0;
        int rowL = matrix.length;
        int colL = matrix[0].length;
        //Case 1 , [1 2 3,
        //          4 5 6,
        //          7 8 9] , 1 position to be check.
        if(i >=1 && j >=1){//when meet, position 1 exist
            if(matrix[i-1][j-1]) count++;
        }
        //Case 2, check 2rd postion.
        if(i>=1){
            if(matrix[i-1][j]) count++;
        }
        //Case 3, check 3rd postion.
        if(i>=1 && j < colL-1){
            if(matrix[i-1][j+1]) count++;
        }
        //Case 4, check 4th postion.
        if(j>=1){
            if(matrix[i][j-1]) count++;
        }
        //Case 5, check 6nd position.
        if(j<colL-1){
            if(matrix[i][j+1]) count++;
        }
        //Case 6, check 7th position.
        if(i<rowL-1 && j >= 1){
            if(matrix[i+1][j-1]) count++;
        }
        //Case 7, check 8th position.
        if(i<rowL-1){
            if(matrix[i+1][j]) count++;
        }
        //Case 8, check 9th postion.
        if(i<rowL-1 && j < colL-1){
            if(matrix[i+1][j+1]) count++;
        }
        return count;
    }



    /*
    You are given an array of integers representing coordinates of obstacles situated on a straight line.

Assume that you are jumping from the point with coordinate 0 to the right. You are allowed only to make jumps of the same length represented by some integer.

Find the minimal length of the jump enough to avoid all the obstacles.

Example

For inputArray = [5, 3, 6, 7, 9], the output should be
avoidObstacles(inputArray) = 4.
     */

    int avoidObstacles(int[] inputArray) {
        // Insert all array elements in a hash table
        // and find the maximum value in the array
        HashSet<Integer> hs = new HashSet<Integer>();
        int max = inputArray[0];
        for (int i=0; i<inputArray.length; i++)
        {
            hs.add(inputArray[i]);
            max = Math.max(max, inputArray[i]);
        }

        // checking for every possible length which
        // yield us solution
        for (int i = 1; i <= max; i++) {
            int j;
            for (j = i; j <= max; j = j + i) {

                // if there is obstacle, break the loop.
                if (hs.contains(j))
                    break;
            }

            // If above loop did not break
            if (j > max)
                return i;
        }

        return max+1;
    }


    /*
    An IP address is a numerical label assigned to each device (e.g., computer, printer) participating in a computer network that uses the Internet Protocol for communication. There are two versions of the Internet protocol, and thus two versions of addresses. One of them is the IPv4 address.

Given a string, find out if it satisfies the IPv4 address naming rules.

Example

For inputString = "172.16.254.1", the output should be
isIPv4Address(inputString) = true;

For inputString = "172.316.254.1", the output should be
isIPv4Address(inputString) = false.

316 is not in range [0, 255].

     */

    boolean isIPv4Address(String inputString) {
        String[] splStr = inputString.split("\\.");
        if(splStr.length<4){
            return false;
        }
        int flag = 0;
        for (String s:
             splStr) {
            //System.out.println(s+" char: "+s.charAt(0));
            if(s.length()<=0 || s.length()>3){
                return false;
            }
            Matcher mer = Pattern.compile("^[+-]?[0-9]+$").matcher(s);
            if (s.charAt(0) == '0' && s.length()>1) {
                return false;
            }

            if (mer.find()) {//String is valid integer
                if (Integer.parseInt(s) <= 255) {//Digits in the range
                    flag++;
                }
            }
        }
        if(flag==4){
            return true;
        }else {
            return false;
        }
    }



    /*
    Given an array of integers, find the maximal absolute difference between any two of its adjacent elements.

Example

For inputArray = [2, 4, 1, 0], the output should be
arrayMaximalAdjacentDifference(inputArray) = 3.
     */
    int arrayMaximalAdjacentDifference(int[] inputArray) {
        int max = 0;
        for (int i = 0; i < inputArray.length-1; i++) {
            if( Math.abs(inputArray[i]-inputArray[i+1]) >=max ){
                max = Math.abs(inputArray[i]-inputArray[i+1]);
            }
        }
        return max;
    }

    /*
    Call two arms equally strong if the heaviest weights they each are able to lift are equal.

Call two people equally strong if their strongest arms are equally strong (the strongest arm can be both the right and
the left), and so are their weakest arms.

Given your and your friend's arms' lifting capabilities find out if you two are equally strong.

Example

For yourLeft = 10, yourRight = 15, friendsLeft = 15, and friendsRight = 10, the output should be
areEquallyStrong(yourLeft, yourRight, friendsLeft, friendsRight) = true;
For yourLeft = 15, yourRight = 10, friendsLeft = 15, and friendsRight = 10, the output should be
areEquallyStrong(yourLeft, yourRight, friendsLeft, friendsRight) = true;
For yourLeft = 15, yourRight = 10, friendsLeft = 15, and friendsRight = 9, the output should be
areEquallyStrong(yourLeft, yourRight, friendsLeft, friendsRight) = false.
     */
    boolean areEquallyStrong(int yourLeft, int yourRight, int friendsLeft, int friendsRight) {
            int max1=0;
            int min1 = 0;
            int max2 = 0;
            int min2 = 0;

            if(yourLeft<=yourRight){
                max1 = yourRight;
                min1 = yourLeft;
            }else{
                max1 = yourLeft;
                min1 = yourRight;
            }

            if(friendsLeft<=friendsRight){
                max2 = friendsRight;
                min2 = friendsLeft;
            }else{
                max2 = friendsLeft;
                min2 = friendsRight;
            }

            if(max1==max2 && min1==min2){
                return true;
            }else{
                return false;
            }


    }



    /*
    Given a string, find out if its characters can be rearranged to form a palindrome.

Example

For inputString = "aabb", the output should be
palindromeRearranging(inputString) = true.

We can rearrange "aabb" to make "abba", which is a palindrome.
     */
    boolean palindromeRearranging(String inputString) {
        Stack<Character> check = new Stack<>();
        char[] inputAry = inputString.toCharArray();
        for (Character c:
             inputAry) {
            if (check.contains(c)){
                boolean re = check.remove(c);
                //System.out.println("remove: "+c+" "+re);
            }else{
                //System.out.println("Push: "+c);
                check.add(c);
            }
        }
        if(check.size()>1){
            //System.out.println("size: "+ check.size());
            return false;
        }else{
            return true;
        }
    }

    /*
You are given an array of integers. On each move you are allowed to increase exactly one of its element by one. Find the minimal number of moves required to obtain a strictly increasing sequence from the input.

Example

For inputArray = [1, 1, 1], the output should be
arrayChange(inputArray) = 3.
     */
    int arrayChange(int[] inputArray) {
        int differ = 0;
        int prev = inputArray[0];
        int output = 0;
        for (int i = 1; i < inputArray.length; i++) {
            if (inputArray[i] <= prev){// less or equal to prev
                differ = prev - inputArray[i] + 1;
                output += differ;
                inputArray[i] = inputArray[i] + differ;
                prev = inputArray[i];
            }else {//larger than prev
                prev = inputArray[i];
            }
        }
        return output;
    }

    /*
Two arrays are called similar if one can be obtained from another by swapping at most one pair of elements in one of the arrays.

Given two arrays a and b, check whether they are similar.

Example

For a = [1, 2, 3] and b = [1, 2, 3], the output should be
areSimilar(a, b) = true.

The arrays are equal, no need to swap any elements.

For a = [1, 2, 3] and b = [2, 1, 3], the output should be
areSimilar(a, b) = true.

We can obtain b from a by swapping 2 and 1 in b.
     */

    boolean areSimilar(int[] a, int[] b) {
        ArrayList<Integer> difference = new ArrayList<>();
        //Find differences
        for (int i = 0; i < a.length; i++) {
            if ( a[i] != b[i] ) {
                difference.add(i);
            }
        }
        //Case 1 : ==
        if (difference.size() == 0) {
            return true;
        }
        //Case 2 : not 2 positions(swap impossible!)
        if (difference.size() != 2) {
            return false;
        }
        //Case 3 : 2 positions:
        int id1 = difference.get(0);
        int id2 = difference.get(1);
        //If differences could be swap in same value:
        if (a[id1] == b[id2] && a[id2] == b[id1]) {
            return true;
        }
        //Case 4 : 1,3,4,5...... invalid positions, or 2 positions with differ values
        return false;
    }


    /*
    Given a rectangular matrix of characters, add a border of asterisks(*) to it.

Example

For

picture = ["abc",
           "ded"]
the output should be

addBorder(picture) = ["*****",
                      "*abc*",
                      "*ded*",
                      "*****"]
     */

    String[] addBorder(String[] picture) {
        String stars = "";
        ArrayList<String> starlist = new ArrayList<>();
        //For the first row, we should " * " + " *****...." + "*" format:
        for(int i = 0; i<picture[0].length()+2;i++){//Column size with +2 sapce of *
            stars +="*";
        }
        //Save the stars row in the list to be converted.
        starlist.add(stars);
        //Now for star columns, its mixed with "*" + chars + "*" format:
        for(int i = 0; i<picture.length; i++){
            starlist.add("*"+picture[i]+"*");
        }
        //Than add the final row:
        starlist.add(stars);
        //Convert the List to the String Array here
        return starlist.toArray(new String[starlist.size()]);
    }



    /*
Several people are standing in a row and need to be divided into two teams.
The first person goes into team 1, the second goes into team 2,
the third goes into team 1 again, the fourth into team 2, and so on.
You are given an array of positive integers -
the weights of the people. Return an array of two integers,
where the first element is the total weight of team 1,
and the second element is the total weight of team 2 after the division is complete.

Example

For a = [50, 60, 60, 45, 70], the output should be
alternatingSums(a) = [180, 105].
 */
    int[] alternatingSums(int[] a) {
        int output1 = 0;
        int output2 = 0;
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            if(count == 0){//team 1
                output1 += a[i];
                count = 1;
            }else{//team 2
                output2 += a[i];
                count = 0;
            }
        }

        return new int[]{output1, output2};
    }



    /*
    Write a function that reverses characters in (possibly nested) parentheses in the input string.

Input strings will always be well-formed with matching ()s.

Example

For inputString = "(bar)", the output should be
reverseInParentheses(inputString) = "rab";
For inputString = "foo(bar)baz", the output should be
reverseInParentheses(inputString) = "foorabbaz";
     */

    String reverseInParentheses(String inputString) {
        int n = inputString.length();
        Stack<Integer> opened = new Stack<>();
        int[] pair = new int[n];
        //find the ( ) positions
        for (int i = 0; i < n; ++i) {
            if (inputString.charAt(i) == '(')
                opened.push(i);
            if (inputString.charAt(i) == ')') {
                int j = opened.pop();
        //Save target positions to pair
                pair[i] = j;
                pair[j] = i;
            }
        }

        StringBuilder sb = new StringBuilder();
        //Use d as index moving left or right
        for (int i = 0, d = 1; i < n; i += d) {
            //when face (, replace cur index i to saved index of ), transfer for loop direction as -d(left).
            if (inputString.charAt(i) == '(' || inputString.charAt(i) == ')') {
                i = pair[i];
                d = -d;
            } else {//If fine, just append to string output.
                sb.append(inputString.charAt(i));
            }
        }
        return sb.toString();
    }

/*
Some people are standing in a row in a park. There are trees between them which cannot be moved.
Your task is to rearrange the people by their heights in a non-descending order without moving the trees.
People can be very tall!

Example

For a = [-1, 150, 190, 170, -1, -1, 160, 180], the output should be
sortByHeight(a) = [-1, 150, 160, 170, -1, -1, 180, 190].
 */
    int[] sortByHeight(int[] a) {
        int[] acopy = a.clone();
        int[] scopy = a.clone();
        List<Integer> purified = new ArrayList<Integer>();
        Arrays.sort(scopy);//get sorted order
        for (int e:
             scopy) { //trim -1 values
            if(e!=-1){
                purified.add(e);
            }
        }
        //compare and relocate
        int j = 0;//Start index
        for (int i = 0; i < a.length; i++) {
            if(acopy[i]!=-1){
                acopy[i] = purified.get(j);//relocate 1st position to 1st target.
                j++;//move the index to next position.
            }
        }
        return acopy;
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


}
