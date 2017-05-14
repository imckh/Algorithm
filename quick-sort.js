/**
 * Created by CKH on 2017/4/21.
 * 分治法的基本思想是：将原问题分解为若干个规模更小但结构与原问题相似的子问题。递归地解这些子问题，然后将这些子问题的解组合为原问题的解。
 * 利用分治法可将快速排序的分为三步：
 * 在数据集之中，选择一个元素作为”基准”（pivot）。
 * 所有小于”基准”的元素，都移到”基准”的左边；所有大于”基准”的元素，都移到”基准”的右边。这个操作称为分区 (partition) 操作，分区操作结束后，基准元素所处的位置就是最终排序后它的位置。
 * 对”基准”左边和右边的两个子集，不断重复第一步和第二步，直到所有子集只剩下一个元素为止。
 */

// 分区是快速排序的主要内容，用伪代码可以表示如下：
/*
function partition(a, left, right, pivotIndex) {
    pivotValue := a[pivotIndex]
    swap (a[pivotIndex], a[right])  //把pivot移到结尾
    storeIndex := left
    for i in left to right - 1
        if a[i] < pivotValue
            swap(a[storeIndex], a[i])
            storeIndex := storeIndex + 1
    swap(a[right], a[storeIndex])   //把pivot移到它最后的地方
    return storeIndex   //返回pivote的最终位置
}

// 然后快排
function quicksort(a, left, right) {
    if right > left
        select a pivot value a[pivotIndex]
        pivotNewIndex := partition(a, left, right, pivotIndex)
        quicksort(a, left, pivotNewIndex - 1)
        quicksort(a, pivotNewIndex + 1, right)
}
*/
var arr = [3,7,8,5,2,1,9,5,4];
console.log('original array: ' + arr);

function quickSortSimple(arr) {
    /**
     * 这个简单版本的缺点是，它需要Ω(n)的额外存储空间，也就跟归并排序一样不好。
     * 额外需要的存储器空间配置，在实际上的实现，也会极度影响速度和高速缓存的性能。
     * */
    if (arr.length <= 1)
        return arr;
    var pivotIndex = Math.floor(arr.length / 2);    //随便取一个pivot基准
    var pivot = arr.splice(pivotIndex, 1)[0];   // splice: 从pivotIndex起删除1个元素并取出
    var left = [];
    var right = [];
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] < pivot)     // 小的放左边 大的放右边 依次递归左边和右边
            left.push(arr[i]);
        else
            right.push(arr[i]);
    }
    return quickSortSimple(left).concat([pivot], quickSortSimple(right));     //合并三个数组
}
var arrs = quickSortSimple(arr);
console.log('Simple quick Sort: ' + arrs);

function quickSort(array) {
    /**
     * 按照维基百科中的原地(in-place)分区版本，实现快速排序方法
     */
    // 交换元素位置
    function swap(array, i, k) {
        var temp = array[i];
        array[i] = array[k];
        array[k] = temp;
    }
    // 数组分区 小的左边 大的右边
    function partition(array, left, right) {
        var storeIndex = left;
        var pivot = array[right];   //选取最右边为基准元素
        for (var i = left; i < right; i++) {
            if (array[i] < pivot) {
                swap(array, storeIndex, i);
                storeIndex++;   //交换位置后, storeIndex加一 代表下一即将要换的位置
            }
            swap(array, right, storeIndex); //最后将基准元素放到正确的位置上
            return storeIndex;
        }
    }

    function sort(array, left, right) {
        if (left > right)
            return;
        var storeIndex = partition(array, left, right);
        sort(array, left, storeIndex - 1);
        sort(array, storeIndex + 1, right);
    }

    sort(array, 0, array.length - 1);
    return array;
}
console.log('original array:' + arr);
arrs = quickSortSimple(arr);
console.log('(in-place) quick Sort:' + arrs);
















