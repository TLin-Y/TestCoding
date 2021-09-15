import java.util.*;

public class MorganStanleyInterview {
    public static void main(String[] args) {
        String input = "12321";
        MorganStanleyInterview test = new MorganStanleyInterview();
        System.out.println(test.solution(input));
        //test case1: odd input
        input = "aabcdedcbaa";
        System.out.println(test.solution(input));
        //Test case2: even input
        input = "1aabcde22edcbaa1";
        System.out.println(test.solution(input));
        //Test case3: empty input
        input = "";
        System.out.println(test.solution(input));
        //Test case4: symbol
        input = ".";
        System.out.println(test.solution(input));

        int[] nums = new int[]{1,2,3,5,6,7,8,9};
        int target = 9;
        int[] twoSum = test.twoSum(nums, target);
        System.out.println(twoSum[0]+","+twoSum[1]);


        System.out.println(test.reverse(789));
        System.out.println(test.reverse(-1000000000));
        System.out.println(test.longestCommonPrefix(new String[]{"ab", "abc", "abcd"}));
        System.out.println(test.removeDuplicates(new int[]{1,1,1,2,3,3,3,4,5}));
    }

    //

    //去掉重复数组中的重复数字
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n==0){
            return 0;
        }
        int fast = 1;
        int slow = 1;
        while(fast<n){
            if(nums[fast]!=nums[fast-1]){
                nums[slow]=nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }


    //合并排列双链表
     public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode head = new ListNode();
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 == null) {
            p.next = l2;
        }
        if (l2 == null) {
            p.next = l1;
        }
        return head.next;
    }

//有效的括号成对, 顺序要符合()[], ([)]无效, 栈解法
    public boolean isValid2(String s) {
        Stack<Character>stack = new Stack<Character>();
        for(char c: s.toCharArray()){
            if(c=='(')stack.push(')');
            else if(c=='[')stack.push(']');
            else if(c=='{')stack.push('}');
            else if(stack.isEmpty()||c!=stack.pop())return false;
        }
        return stack.isEmpty();
    }

//有效的括号成对, 顺序要符合()[], ([)]无效
    public boolean isValid(String s) {
        int length = s.length() / 2;
        for (int i = 0; i < length; i++) {
            s = s.replace("()", "").replace("{}", "").replace("[]", "");
        }

        return s.length() == 0;
    }

    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if(strs.length==0) return "";
        String result = strs[0];
        for(int i =1; i< strs.length;i++){//BigO(n)
            int j=0;
            for (;j<result.length() && j<strs[i].length();j++){
                if(result.charAt(j) != strs[i].charAt(j))
                    break;
            }
            result = result.substring(0,j);
            if(result.equals("")) return result;
        }
        return result;
    }

    //判断整数回文
        public boolean isPalindrome(int x) {
        //负数及末尾为0的数(除了0)不为回文
            if (x < 0 || (x % 10 == 0 && x != 0)) return false;
            int revertedNumber = 0;
            while (x > revertedNumber) {//每次x除以10, 过半时, 或者过半1次后, 就跳出循环
                //反转整数
                revertedNumber = revertedNumber * 10 + x % 10;
                x /= 10;
            }
            //奇数,12321, x为12应该等于123反转数/10 = 12
            //偶数, 1221, x为12应该等于反转数12
            return x == revertedNumber || x == revertedNumber / 10;
        }




 //反转整数
     public int reverse(int x) {
         int result=0;
         //find last digit
         while(x!=0) {
             if (result<Integer.MIN_VALUE/10 ||result > Integer.MAX_VALUE/10){
                 return 0;// out of boundary
             }
             int last = x % 10;
             x = x / 10;
             result = result * 10 + last;
         }
         return result;
     }

 //查数组中两数相加是否能等于目标值
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> sav = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(sav.containsValue(target-nums[i]) ){
               return new int[]{sav.get(target-nums[i]),i};
            }
            sav.put(i,nums[i]);
        }
        return new int[]{0,0};
    }

// String回文判断
    boolean solution(String input) {
        if(input.length()==0){
            return false;
        }
        StringBuilder sb = new StringBuilder(input).reverse();
        String revStr = new String(sb);
        System.out.println(revStr);
        if(input.equals(revStr)){
            return true;
        }else{
            return false;
        }
    }
}
