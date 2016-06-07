/**
 * Author: stk
 * Date: 2016/5/16
 * Time: 19:14
 */
public class Search {
    public static int binarySearch(int[] data, int begin, int end, int key) {
        if (begin > end)
            return -1;
        int mid = begin + (end - begin) / 2;
        if (key < data[mid])
            return binarySearch(data, begin, mid - 1, key);
        else if (key > data[mid])
            return binarySearch(data, mid + 1, end, key);
        else
            return mid;
    }

    public static void main(String[] args) {
        int[] test1 = {0, 1, 4, 7, 9, 12, 15, 17, 21};
        System.out.println(Search.binarySearch(test1, 0, test1.length - 1, 21));
    }
}
