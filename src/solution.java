import java.util.*;

public class solution {

    public String solution(int N, int K){
// write your code in Java SE 8
        String name;
        List<String> nameAry = new ArrayList<String>();

        for(int i = 1;i<=K;i++){
            name = String.valueOf((char)(96+i)).toLowerCase();
            nameAry.add(name);
        }

        char[] curChar = new char[N];
        int start = 0;
        int end = N-1;
        int half = (int)Math.ceil(N/2);
        //System.out.println(half);
        Iterator<String> pointer = nameAry.iterator();
        boolean flag = true;
        char selectChar = pointer.next().charAt(0);

        //for even length  aaabccbaaa or odd length aabcbaa
        //greedy approach
            while (start <= end) {
                if(flag==true) {
                    flag = false;
                    //System.out.println("For");
                    for (int i = 0; i < half - K; i++) {
                        curChar[start] = selectChar;
                        curChar[end] = selectChar;
                        start++;
                        end--;
                    }
                }
                curChar[start] = selectChar;
                curChar[end] = selectChar;
                start++;
                end--;
                if (pointer.hasNext()) {
                    selectChar = pointer.next().charAt(0);
                }
            }

        return String.valueOf(curChar);
    }


    public static void main(String[] args) {
        //int[] demo = new int[] {4, 3};
        int n = 9;
        int k = 7;
        solution s = new solution();
        System.out.println(s.solution(n,k));
    }
}
