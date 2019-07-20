import java.util.ArrayList;

public class Solution {
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int p = 0, q = array.length - 1;
        while (p < q) {
            int mid = (p + q + 1) / 2;
            if (array[0] <= array[mid]) {
                p = mid;
                if (p + 1 < array.length && array[p] > array[p + 1]) {
                    p++;
                }
            }
            if (array[array.length - 1] >= array[mid]) {
                q = mid;
                if (q - 1 >= 0 && array[q - 1] <= array[q]) {
                    q--;
                }
            }
        }
        return array[p];
    }

    class haha{
        private int n;
    }

    public static void main(String[] args){
//        ArrayList
    }
}