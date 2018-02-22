/**
 * 插入排序
 * Created by CKH on 2017/4/22.
 * 1. 从第一个元素开始，该元素可以认为已经被排序
 * 2. 取出下一个元素，在已经排序的元素序列中从后向前扫描
 * 3. 如果该元素（已排序）大于新元素，将该元素移到下一位置
 * 4. 重复步骤 3，直到找到已排序的元素小于或者等于新元素的位置
 * 5. 将新元素插入到该位置后
 * 6. 重复步骤 2~5
 *
 * 如果比较操作的代价比交换操作大的话，
 * 可以采用二分查找法来减少比较操作的数目。
 * 该算法可以认为是插入排序的一个变种，称为二分查找排序。
 *
 * 在每次比较操作发现新元素小于等于已排序的元素时，可以将已排序的元素移到下一位置，
 * 然后再将新元素插入该位置，接着再与前面的已排序的元素进行比较，这样做交换操作代价比较大。
 * 还有一个做法是，将新元素取出，从左到右依次与已排序的元素比较，
 * 如果已排序的元素大于新元素，那么将该元素移动到下一个位置，
 * 接着再与前面的已排序的元素比较，直到找到已排序的元素小于等于新元素的位置，
 * 这时再将新元素插入进去
 */

var arr = [5, 6, 3, 1, 8, 7, 2, 4];

// 原始排序算法
function insertionSort(array) {
    function swap(array, i, j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    var length = array.length, i, j;
    for (i = 1; i < length; i++) {  // 从第一个元素开始，该元素可以认为已经被排序
        for (j = i; j > 0; j--) {   // 取出下一个元素，在已经排序的元素序列中从后向前扫描
            if (array[j - 1] > array[j])
                // 如果该元素（已排序）大于新元素，将该元素移到下一位置
                // 重复 直到找到已排序的元素小于或者等于新元素的位置
                swap(array, j, j - 1);
            else
                break;
        }
    }
    return array;
}

// 可以减少交换次数
function insertionSort2(array) {
    var length = array.length, i, j;
    var temp;
    for (i = 1; i < length; i++) {
        temp = array[i];    // 将新元素取出
        for (j = i; j >= 0; j--) {
            if (array[j - 1] > temp) {  // 从右到左依次与已排序的元素比较
                array[j] = array[j - 1];    // 如果已排序的元素大于新元素，那么将该元素移动到下一个位置
            } else {    // 接着再与前面的已排序的元素比较，直到找到已排序的元素小于等于新元素的位置
                array[j] = temp;    // 这时再将新元素插入进去
                break;  // 不再往前比较
            }
        }
    }
    return array;
}

// 利用二分法实现 二分查找排序
function binarySearchingInsertionSort(array) {
    function binarySearching(array, start, end, temp) {
        var middle;     // 搜素过程从数组的中间元素开始
        while (start<= end) {
            /*
             如果某一特定元素大于或者小于中间元素，
             则在数组大于或小于中间元素的那一半中查找，
             而且跟开始一样从中间元素开始比较
             */
            middle = Math.floor((start + end) / 2);
            if (array[middle] < temp) {
                if (temp <= array[middle + 1]) {
                    // 要查找的元素 想要的位置元素是前小后大的
                    return middle + 1;  // 如果中间元素正好是要查找的元素，则搜素过程结束
                } else {
                    start = middle + 1;
                }
            } else {
                if (end == 0) { // 如果在某一步骤数组为空，则代表找不到
                    return 0;
                } else {
                    end = middle;
                }
            }
        }
    }

    function binarySort(array) {
        var length = array.length, i, j, k, temp;
        for (i = 1; i < length; i++) {
            temp = array[i];    // 将新元素取出
            if (array[i -1] <= temp) {
                // 若将要插入的元素比序列最后一个元素还要大, 插在最后
                k = i;
            } else {
                // 直接找到将要插入的元素在序列中应该的位置
                k = binarySearching(array, 0, i - 1, temp);
                // 然后将比他大的元素一个个后移
                for (j = i; j > k; j--) {
                    array[j] = array[j -1];
                }
            }
            // 最后插到k位置
            array[k] = temp;
        }
        return array;
    }

    return binarySort(array);
}
// t = arr;
// console.log('原始数据', arr.toString());
// a  = insertionSort(t).toString();
// console.log('原始插入排序算法: ', a);
// t = arr;
// console.log('原始数据', arr.toString());
// a  = insertionSort2(t).toString();
// console.log('减少交换次数: ', a);
// console.log('二分查找排序: ', binarySearchingInsertionSort(arr));















