import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result2 {

    public static int coinChange(int[] coins, int amount) {
        if (amount==0) return 0;
        int[] dp =new int[amount+1];  //dp[i]表示达到i用的最少硬币数  默认=初始化全为0
        for (int i = 1; i <= amount; i++) {  //目标为0 的不用计算
            dp[i]=999999;   //此处不能用int的最大值，最大值+1 会溢出变为最小值
            for (int coin :coins) {
                if (i-coin>=0) dp[i] =Math.min(dp[i-coin]+1,dp[i]);
            }
        }
        return dp[amount]==999999?-1:dp[amount];
    }

}



/*
6
a v i k e l
 */
public class Solution2 {
    public static void main(String[] args) throws IOException {
        {
            Scanner s = new Scanner(System.in);
            String str = s.nextLine();
            String sub = s.nextLine();
            int count1 = (str.length()-(str.replace(sub,"")).length())/sub.length();
            int count = 0;
            for(int i = 0; i < str.length();i++){
                int j = i;
                int k = 0;
                while(j<str.length() && k < sub.length()){
                    if(str.charAt(j)==sub.charAt(k)){
                        ++j;
                        ++k;
                        if(k==sub.length()-1){
                            ++count;
                        }
                    }else{
                        break;
                    }
                }
            }
            if(count>count1){
                System.out.println(count);
            }else{
                System.out.println(count1);
            }
            /*
            // Write your code here
            //Scanner
            Scanner s = new Scanner(System.in);
            String name2 = s.nextLine();             // Read input from STDIN
            String[] inputs = name2.split(" ");
            String strA = inputs[0];
            String strB = inputs[1];
            char[] a1 = strA.toCharArray();
            char[] a2 = strB.toCharArray();
            int max = 0;
            int index = 0;
            boolean f = false;
            if(strA.length()<strB.length()){
                max = strB.length();
            }else{
                max = strA.length();
            }
            for(int i=0;i<max;i++){
                if(i+1==max){
                    if(strA.length()!=strB.length()){
                        if(f==false) {
                            index = max-1;
                        }
                    }else{
                        if(a1[i]!=a2[i]){
                            index = i;
                            f = true;
                        }
                    }
                }else{
                    if(a1[i]!=a2[i]){
                        index = i;
                        f = true;
                    }
                }
                System.out.println("i:"+i+" index:" +index);
            }
            if(strA.length()<strB.length()){
                System.out.println(a2[index]+" index: "+index);
            }else{
                System.out.println(a1[index]+" index: "+index);
            }
        }
*/
        /*
        input
         */
        /*
        Scanner s = new Scanner(System.in);
        String numOfProducts = s.nextLine();             // Read input from STDIN
        int n = Integer.parseInt(numOfProducts);
        int inputs[] = new int[n];
        for (int i = 0; i < n; i++) {
            inputs[i] = s.nextInt();
        }
        /*
        5
        123 321 222 333 111
         */
        /*
        output the inputs here
         */
        /*
        for (int i = 0; i < n; i++) {

            if(i+1==n){
                System.out.print(inputs[i]);
            }else{
                System.out.print(inputs[i]+" ");
            }
        }
        System.out.println();
        //Solution:
        for(int i = 0; i<n;i++){
            char[] a = Integer.toString(inputs[i]).toCharArray();
            int total = 0;
            for (char c: a) {
                total += Integer.parseInt(Character.toString(c));
            }
            if(i+1==n){
                System.out.print(total);
            }else{
                System.out.print(total+" ");
            }
        }
        */









        /*
        // Write your code here
        Scanner s = new Scanner(System.in);
        String numOfProducts = s.nextLine();             // Read input from STDIN
        String NProducts = s.nextLine();
        String[] inputs = NProducts.split(" ");
        char c = '1';
        int ic = c;
        System.out.println(ic);
        System.out.println(numOfProducts);
        System.out.println(NProducts);
        String[] ProductList = {"a","e","i","o","u","A","E","I","O","U"};
        int total = 0;
        boolean flag = false;
        for(String str:inputs){
            for (String t:ProductList){
                if(str.equals(t)){
                    flag = true;
                }
            }
            if(flag == true){
                flag = false;
            }else{
                flag = false;
                total++;
            }
        }
        System.out.println(total);
        /*
        Scanner sc = new Scanner(System.in);
        int num;
        int temp;
        int x1=0;
        int x2=0;
        int position1=0;
        int position2=0;
        int maxi=0;
        System.out.println("Enter the number of houses: ");
        num = sc.nextInt();
        int house_number[] = new int[num];
        int position[] = new int[num];
        int copy_position[] = new int[num];
        System.out.println("Enter the house number and position of the house: ");
        for(int i=0; i<num; i++)
        {
            house_number[i] = sc.nextInt();
            position[i] = sc.nextInt();
        }
        System.out.println("Input: ");
        for(int i=0; i<num; i++)
        {
            System.out.print("[" + house_number[i] + "," + position[i] + "] ");
        }
        System.out.println("");
        for(int i=0; i<num; i++)
        {
            copy_position[i] = position[i];
        }
        Arrays.sort(copy_position);
        for(int i=0; i<num-1;i++){
            temp = copy_position[i+1]-copy_position[i];
            if(temp>maxi)
            {
                maxi = temp;
                x1 = copy_position[i];
                x2 = copy_position[i+1];
            }
        }
        for(int i=0; i<num; i++) //The elements position are found in the main position array
        {
            if(x1 == position[i])
            {
                position1 = i;
            }
            else if (x2 == position[i])
            {
                position2 = i;
            }
        }
        if(house_number[position1]>house_number[position2]) //The house number is displayed which is matched by the position obtained
        {
            System.out.println("Result: [" + house_number[position2] + "," + house_number[position1] + "] ");
        }
        else
        {
            System.out.println("Result: [" + house_number[position1] + "," + house_number[position2] + "] ");
        }
        /*
        5
        3 7 1 9 2 0 5 15 4 30
         */

        }
        /*
        // Write your code here
        Scanner s = new Scanner(System.in);
        String numOfProducts = s.nextLine();             // Read input from STDIN
        String NProducts = s.nextLine();
        String[] inputs = NProducts.split(" ");
        System.out.println(numOfProducts);
        System.out.println(NProducts);
        String[] ProductList = {"a","e","i","o","u","A","E","I","O","U"};
        int total = 0;
        boolean flag = false;
        for(String str:inputs){
            for (String t:ProductList){
                if(str.equals(t)){
                    flag = true;
                }
            }
            if(flag == true){
                flag = false;
            }else{
                flag = false;
                total++;
            }
        }
        System.out.println(total);
        */
    }
}
