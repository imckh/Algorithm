var arr = [5, 6, 3, 1, 8, 7, 2, 4];

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
console.log('原始数据', arr.toString());
arr2 = binarySearchingInsertionSort(arr).toString();
// console.log('原始插入排序算法: ', arrt);
// console.log('原始插入排序算法2: ', arrt2);
// console.log('减少交换次数: ', insertionSort2(arr2));
console.log('二分查找排序: ', binarySearchingInsertionSort(arr2));

