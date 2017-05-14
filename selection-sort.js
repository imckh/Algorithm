/**
 * Created by CKH on 2017/4/21.
 * 选择排序（Selection Sort）是一种简单直观的排序算法。
 * 它的工作原理如下，首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 以此类推，直到所有元素均排序完毕。
 *
 * 选择排序的主要优点与数据移动有关。如果某个元素位于正确的最终位置上，则它不会被移动。
 * 选择排序每次交换一对元素，它们当中至少有一个将被移到其最终位置上，
 * 因此对n个元素的序列进行排序总共进行至多n-1次交换。
 * 在所有的完全依靠交换去移动元素的排序方法中，选择排序属于非常好的一种。
 */
var arr = [8, 5, 2, 6, 9, 3, 1, 4, 0, 7];

function selectionSort(array) {
    var length = array.length,
        i,
        j,
        minIndex,
        minValue,
        temp;
    for (i = 0; i < length - 1; i++) {
        minIndex = i;
        minValue = array[minIndex];
        for (j = i + 1; j < length; j++) {
            if (array[j] < minValue){
                minIndex = j;
                minValue = array[minIndex];
            }
        }
        // 交换位置
        temp = array[i];
        array[i] = minValue;
        array[minIndex] = temp;
    }
    return array;
}

console.log('original array: ' + arr);
console.log('After selection sort: ' + selectionSort(arr));