/**
 * 堆排序
 * Created by CKH on 2017/4/21.
 * 堆排序就是把最大堆堆顶的最大数取出，将剩余的堆继续调整为最大堆，再次将堆顶的最大数取出，
 * 这个过程持续到剩余数只有一个时结束。在堆中定义以下几种操作：
 *   最大堆调整（Max-Heapify）：将堆的末端子节点作调整，使得子节点永远小于父节点
 *   创建最大堆（Build-Max-Heap）：将堆所有数据重新排序，使其成为最大堆
 *   堆排序（Heap-Sort）：移除位在第一个数据的根节点，并做最大堆调整的递归运算
 * 注意: 数组都是 Zero-Based
 *  Parent(i) = floor((i-1)/2)，i 的父节点下标
 *  Left(i) = 2i + 1，i 的左子节点下标
 *  Right(i) = 2(i + 1)，i 的右子节点下标
 */

var arr = [16, 14, 10, 8, 7, 9, 3, 2, 4, 1];

function heapSort(array) {
    function swap(array, i, j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 最大堆调整（MAX‐HEAPIFY）的作用是保持最大堆的性质，是创建最大堆的核心子程序
    // 下面是递归方法 recursion
    /**
     * 从index开始检查 并保持最大堆性质
     * @param array
     * @param index 检查的起始下标
     * @param heapSize 堆大小
     */
    function maxHeapify(array, index, heapSize) {
        var iMax = index,
            iLeft = 2 * index + 1,  // 左孩子
            iRight = 2 * (index + 1);   // 右孩子

        // 若父比子小保持交换子中最大者 // 先于heapSize比较 以防数组越界
        if (iLeft < heapSize && array[index] < array[iLeft])
            iMax = iLeft;
        if (iRight < heapSize && array[iMax] < array[iRight])
            iMax = iRight;

        // 其他情况便是父比两个子都大
        if (iMax != index) {
            swap(array, iMax, index);   // 交换父子  父向下一层
            maxHeapify(array, iMax, heapSize);  //递归调整
        }
    }

    /**
     * 递归主要用在分治法中，而这里并不需要分治。而且递归调用需要压栈/清栈，和迭代相比，性能上有略微的劣势
     * 最大堆调整（MAX‐HEAPIFY）通过迭代
     */
    function maxHeapifyByIter(array, index, heapSize) {
        var iMax, iLeft, iRight;
        while (true) {
            iMax = index;
            iLeft = 2 * index + 1;  // 左孩子
            iRight = 2 * (index + 1);   // 右孩子

            // 若父比子小保持交换子中最大者 // 先于heapSize比较 以防数组越界
            if (iLeft < heapSize && array[index] < array[iLeft])
                iMax = iLeft;
            if (iRight < heapSize && array[iMax] < array[iRight])
                iMax = iRight;

            // 其他情况便是父比两个子都大
            if (iMax != index) {
                swap(array, iMax, index);   // 交换父子  父向下一层
                index = iMax;  //迭代调整
            } else {
                break;
            }
        }
    }

    /**
     * 创建最大堆（Build-Max-Heap）的作用是将一个数组改造成一个最大堆，接受数组和堆大小两个参数，
     * Build-Max-Heap 将自下而上的调用 Max-Heapify 来改造数组，建立最大堆。
     * 因为 Max-Heapify 能够保证下标 i 的结点之后结点都满足最大堆的性质，
     * 所以自下而上的调用 Max-Heapify 能够在改造过程中保持这一性质。
     * 如果最大堆的数量元素是 n，那么 Build-Max-Heap 从 Parent(n) 开始，往上依次调用 Max-Heapify
     * @param array
     * @param heapSize 堆大小
     */
    function buildMaxHeap(array, heapSize) {
        var i,
            iParent = Math.floor((heapSize - 1) / 2);
        // 如果最大堆的数量元素是 n，那么 Build-Max-Heap 从 Parent(n) 开始，往上依次调用 Max-Heapify
        for (i = iParent; i >= 0; i--)
            maxHeapify(array, i, heapSize);
    }

    /**
     * 堆排序（Heap-Sort）是堆排序的接口算法，
     * Heap-Sort先调用Build-Max-Heap将数组改造为最大堆，
     * 然后将堆顶和堆底元素交换，之后将底部上升，最后重新调用Max-Heapify保持最大堆性质。
     * 由于堆顶元素必然是堆中最大的元素，所以一次操作之后，堆中存在的最大元素被分离出堆，
     * 重复n-1次之后，数组排列完毕
     * @param array
     */
    function sort(array) {
        buildMaxHeap(array);    //调用Build-Max-Heap将数组改造为最大堆

        for (var i = array.length - 1; i > 0; i--) {
            swap(array, 0, i);  //堆顶和堆底元素交换，之后将底部上升，最后重新调用Max-Heapify保持最大堆性质
            maxHeapify(array, 0, i);    //由于堆顶元素必然是堆中最大的元素，所以一次操作之后，堆中存在的最大元素被分离出堆
        }
        return array;
    }

    return sort(array);
}

console.log(arr);
console.log(heapSort(arr));





















