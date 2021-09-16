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
        test.testReverseList();
        System.out.println(test.isPalindrome("A man, a plan, a canal: Panama"));

    }




//DP: 最大子序列和
    //
    public int maxSubArray(int[] nums) {
        int pre = 0, maxAns = nums[0];
        for (int x : nums) {
            pre = Math.max(pre + x, x);
            maxAns = Math.max(maxAns, pre);
        }
        return maxAns;
    }



    //过滤无关字符, 只保留char和int的内容, 判断是否回文串
    public boolean isPalindrome(String s) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if(Character.isLetterOrDigit(cur)){
                strb.append(Character.toLowerCase(cur));
            }
        }
        String original = strb.toString();
        String rev = strb.reverse().toString();
        return original.equals(rev);
    }


    //冒泡排序
    public static void bubbleSort(int arr[]) {
        for(int i =0 ; i<arr.length-1 ; i++) {
            for(int j=0 ; j<arr.length-1-i ; j++) {
                if(arr[j]>arr[j+1]) {
                    int temp = arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }

    public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
    void testReverseList() {
        System.out.println("testReverseList");
        ListNode head = new ListNode(1);//创建头节点
        head.next = new ListNode(2);//再定义头节点的next域
        ListNode t = head.next;
        for(int i=3;i<10;i++) {//创建一个简单的链表{1,2,3,4,5,...,9}
            t.next = new ListNode(i);
            t = t.next;
        }
        ListNode newHead = reverseList(head);//调用反转链表方法
        System.out.println(newHead.val);//检查新的头节点的值
        printListNode(newHead);//打印新链表的全部节点
    }
    //为了便于查看结果，写的打印链表的方法
    public void printListNode(ListNode head) {
        while(head!=null) {
            System.out.print(head.val+" ");
            head = head.next;
        }
    }

    //链表回文: 查中点, 反转后半部分
    public boolean isPalindrome(ListNode head)
    {
        if (head == null || head.next == null)
            return true;
        //--------找中点
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = slow;
        //--------右半段翻转
        ListNode r_head = mid.next;
        ListNode pre = null;
        ListNode cur = r_head;
        while (cur != null)
        {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        ListNode reverse_r_head = pre;

        //--------比较
        ListNode l = head;
        ListNode r = reverse_r_head;
        while (l != null && r != null)
        {
            if (l.val != r.val)
                return false;
            l = l.next;
            r = r.next;
        }
        return true;
    }

    //查链表倒数第n个数据
    ListNode findRevN(ListNode head, int n){
            ListNode gapEnd = head;
            //Step1: move forward n nodes on gapEnd node
            for (int i = 0; i < n; i++) {
                gapEnd = gapEnd.next;
            }
            //Step2: move both nodes until reach the end of linkedlist
            while(gapEnd.next!=null){
                head = head.next;
                gapEnd = gapEnd.next;
            }
        return head;
    }
    //反转链表
    public ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;
        while(cur!=null){
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

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
//strstr(), 找子串出现在母串中的下标
public int strStr(String haystack, String needle) {
    int h = haystack.length();
    int n = needle.length();
    for(int i = 0; i + n <= h; ++i){
        if(needle.equals(haystack.substring(i, i + n))){
            return i;
        }
    }
    return -1;
}
    //合并排列双链表
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

    //查数组中两数相加是否能等于目标值, 数组为排序递增输入
    public int[] twoSumSorted(int[] numbers, int target) {
        int start = 0;
        int end = numbers.length-1;
        for (int i = 0; i < numbers.length; i++) {
            int sum = numbers[start]+numbers[end];
            if(sum<target){
                start++;
            }else if(sum>target){
                end--;
            }else{
                return new int[]{start,end};
            }
        }
        return new int[0];
    }

    //检查两个字符串是否相等
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder str = new StringBuilder();
        for (String s:
             word1) {
            str.append(s);
        }
        String s1= str.toString();

        StringBuilder str2 = new StringBuilder();
        for (String s:
                word2) {
            str2.append(s);
        }
        String s2= str2.toString();
        return s1.equals(s2);
    }

    //找两整数组的交集
    public int[] intersection(int[] nums1, int[] nums2) {
        List<Integer> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        for(int i=0;i<nums1.length;i++){
            list.add(nums1[i]);
        }
        for(int i=0;i<nums2.length;i++){
            if(list.contains(nums2[i]) && !list2.contains(nums2[i])){
                list2.add(nums2[i]);
            }
        }
        return list2.stream().mapToInt(Integer::intValue).toArray();
    }

 //查数组中两数相加是否能等于目标值
 public int[] twoSum(int[] nums, int target) {
     Map<Integer, Integer> sav = new HashMap<Integer, Integer>();
     for (int i = 0; i < nums.length; ++i) {
         if (sav.containsKey(target - nums[i])) {
             return new int[]{sav.get(target - nums[i]), i};
         }
         sav.put(nums[i], i);
     }
     return new int[0];
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
