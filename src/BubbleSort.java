import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class BubbleSort {
    public static int[] bsort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for(int k =0;k<arr.length;k++) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    int next = arr[i + 1];
                    int cur = arr[i];
                    arr[i + 1] = cur;
                    arr[i] = next;
                }
            }
        }
        return arr;
    }

    public static int[] selsort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i != min) {
                int tmp = arr[i];
                arr[i] = arr[min];
                arr[min] = tmp;
            }
        }
        return arr;
    }
    public static void main(String[] args) throws Exception {
        int[] input = new int[] {5,4,3,2,1,6,9,99999};
        int[] result = selsort(input);
        for (int i=0;i<result.length;i++) {
            System.out.println(result[i]);
        }

    }
}