import java.util.Random;

/**
 * Author: stk
 * Date: 2016/5/16
 * Time: 19:16
 *
 * 排序算法
 * 1 插入排序
 *     1.1 直接插入排序
 *     1.2 希尔排序
 * 2 选择排序
 *     2.1 简单选择排序
 *     2.2 堆排序
 * 3 交换排序
 *     3.1 冒泡排序
 *     3.2 快速排序
 * 4 归并排序
 * 5 基数排序
 * |排序方法   |            时间复杂度             |空间复杂度 |稳定性     |
 * |           |平均情况   |最好情况   |最坏情况   |           |           |
 * |直接插入   |O(n^2)     |O(n)       |O(n^2)     |O(1)       |稳定       |
 * |希尔排序   |O(n^1.3)   |O(n)       |O(n^2)     |O(1)       |不稳定     |
 * |直接选择   |O(n^2)     |O(n^2)     |O(n^2)     |O(1)       |不稳定     |
 * |堆排序     |O(nlogn)   |O(nlogn)   |O(nlogn)   |O(1)       |不稳定     |
 * |冒泡排序   |O(n^2)     |O(n)       |O(n^2)     |O(1)       |稳定       |
 * |快速排序   |O(nlogn)   |O(nlogn)   |O(n^2)     |O(logn)    |不稳定     |
 * |归并排序   |O(nlogn)   |O(nlogn)   |O(nlogn)   |O(n)       |稳定       |
 * |基数排序   |O(d(r+n))  |O(d(n+rd)) |O(d(r+n))  |O(rd+n)    |稳定       |
 * 注：基数排序中，r代表关键字的基数，d代表长度，n代表关键字的个数
 */
public class Sort {
    /**
     * 1.1 直接插入排序(Straight Insertion Sort)
     * @param data 待排序序列
     */
    public static void insertSort(int[] data) {
        for (int i = 1; i < data.length; i++) {
            int pivot = data[i];//复制为哨兵，即存储待排序元素
            int j;
            for (j = i - 1; j >= 0 && data[j] > pivot; j--)//查找在有序表的插入位置
                data[j + 1] = data[j];//元素后移
            data[j + 1] = pivot;//插入到正确位置
        }
    }

    /**
     * 1.2 希尔排序(Shell's Sort)
     * @param data 待排序序列
     */
    public static void shellSort(int[] data) {
        for (int d = data.length / 2; d >= 1; d /= 2) {//d为缩小增量，d={n/2, n/4, n/8, ... , 1}
            //插入排序
            for (int i = d; i < data.length; i++) {
                int pivot = data[i];
                int j;
                for (j = i - d; j >= 0 && data[j] > pivot; j -= d)
                    data[j + d] = data[j];
                data[j + d] = pivot;
            }
        }
    }

    /**
     * 2.1 简单选择排序(Simple Selection Sort)
     * @param data 待排序序列
     */
    public static void selectSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            //选择最小的元素
            int pivot = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[pivot])
                    pivot = j;
            }
            //最小元素与第i位置元素互换
            if (i != pivot) {
                int temp = data[i]; data[i] = data[pivot]; data[pivot] = temp;
            }
        }
    }

    /**
     * 2.2 堆排序(Heap Sort)
     * @param data 待排序序列
     */
    public static void heapSort(int[] data) {
        for (int i = data.length / 2; i >= 0; i--)//最后一个有孩子的节点的位置
            heapAdjust(data, i, data.length);
        for (int i = data.length - 1; i > 0; i--) {
            int temp = data[0]; data[0] = data[i]; data[i] = temp;//交换堆顶元素和堆中最后一个元素
            heapAdjust(data, 0, i);
        }
    }

    /**
     * 调整堆
     * @param data 待调整的堆数组
     * @param i 待调整的数组元素的位置
     * @param n 待调整堆的大小
     */
    public static void heapAdjust(int[] data, int i, int n) {
        int temp, child;
        for (temp = data[i]; 2 * i + 1 < n; i = child) {
            child = 2 * i + 1;//左孩子
            if (child + 1 < n && data[child + 1] > data[child])//如果右孩子大于左孩子
                child++;//找到比当前待调整结点大的孩子结点
            if (data[child] > temp)//如果较大的子结点大于父结点
                data[i] = data[child];
            else
                break;
        }
        data[i] = temp;
    }

    /**
     * 3.1 冒泡排序(Bubble Sort)
     * @param data 待排序序列
     */
    public static void bubbleSort(int[] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1] > data[j]) {//大数往下沉
                    int temp = data[j - 1]; data[j - 1] = data[j]; data[j] = temp;
                }
            }
        }
    }

    /**
     * 3.2 快速排序(Quick Sort)
     * @param data 待排序序列
     * @param low 下标
     * @param high 上标
     */
    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int l = low, h = high;//存储下标上标
            int pivotkey = data[low];//基准元素
            while (low < high) {//从表的两端交替地向中间扫描
                while (low < high && data[high] >= pivotkey) high--;
                int temp = data[low]; data[low] = data[high]; data[high] = temp;
                while (low < high && data[low] <= pivotkey) low++;
                temp = data[low]; data[low] = data[high]; data[high] = temp;
            }
            quickSort(data, l, low - 1);
            quickSort(data, low + 1, h);
        }
    }

    /**
     * 4 归并排序(Merge Sort)
     * @param data 待排序序列
     */
    public static void mergeSort(int[] data) {
        int[] temp = new int[data.length];
        mergeRecursive(data, temp, 0, data.length - 1);
    }

    /**
     * 递归分割合并
     * @param data 待操作数组
     * @param temp 辅助存储数组
     * @param begin 分割起始位置
     * @param end 分割结束位置
     */
    public static void mergeRecursive(int[] data, int[] temp, int begin, int end) {
        if (begin < end) {
            int mid = (begin + end) / 2;//分割
            mergeRecursive(data, temp, begin, mid);
            mergeRecursive(data, temp, mid + 1, end);
            merge(data, temp, begin, mid, end);//合并
        }
    }

    /**
     * 合并两个序列
     * @param data 待合并的两个数组
     * @param temp 辅助存储数组
     * @param begin 第一个数组的初始位置
     * @param mid 第一个数组的结束位置
     * @param end 第二个数组的结束位置
     */
    public static void merge(int[] data, int[] temp, int begin, int mid, int end) {
        int i = begin, j = mid + 1, k = begin;
        for (; i <= mid && j <= end; k++) {
            if (data[i] < data[j])
                temp[k] = data[i++];
            else
                temp[k] = data[j++];
        }
        //将两个数组剩下的元素存储
        while (i <= mid)
            temp[k++] = data[i++];
        while (j <= end)
            temp[k++] = data[j++];
        //将排好的元素存回
        for (i = begin; i < k; i++)
            data[i] = temp[i];
    }

    /**
     * 打印数组
     * @param data 数组
     */
    public static void print(int[] data) {
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }

    public static void main(String[] args) {
        int[] test1 = {6, 3, 1, 9, 4, 7, 2, 5, 8};
        int[] test2 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] test3 = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] test4 = new int[1000];
        for (int i = 0; i < 1000; i++)
            test4[i] = new Random().nextInt(1000);

//		Sort.insertSort(test1); Sort.insertSort(test2); Sort.insertSort(test3);
//		Sort.shellSort(test1); Sort.shellSort(test2); Sort.shellSort(test3);
//		Sort.selectSort(test1); Sort.selectSort(test2); Sort.selectSort(test3);
//		Sort.heapSort(test1); Sort.heapSort(test2); Sort.heapSort(test3);
//		Sort.bubbleSort(test1); Sort.bubbleSort(test2); Sort.bubbleSort(test3);
//		Sort.quickSort(test1, 0, test1.length - 1); Sort.quickSort(test2, 0, test2.length - 1); Sort.quickSort(test3, 0, test3.length - 1);
        Sort.mergeSort(test1); Sort.mergeSort(test2); Sort.mergeSort(test3); Sort.mergeSort(test4);

        Sort.print(test1); Sort.print(test2); Sort.print(test3);
        Sort.print(test4);
    }
}
