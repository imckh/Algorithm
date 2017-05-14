/**
 * 归并排序
 * Created by CKH on 2017/4/22.
 *
 * 归并排序（Merge Sort，台湾译作：合并排序）是建立在归并操作上的一种有效的排序算法。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。
 * 归并操作(Merge)，也叫归并算法，指的是将两个已经排序的序列合并成一个序列的操作。
 * 归并排序算法依赖归并操作。归并排序有多路归并排序、两路归并排序 , 可用于内排序，也可以用于外排序。
 * 这里仅对内排序的两路归并方法进行讨论。
 *
 * 算法思路：
 * 1. 把 n 个记录看成 n 个长度为 l 的有序子表
 * 2. 进行两两归并使记录关键字有序，得到 n/2 个长度为 2 的有序子表
 * 3. 重复第 2 步直到所有记录归并成一个长度为 n 的有序表为止。
 */


/**
 * 由于要两两归并的子数组都是有序的数组，
 * 同时我们在希尔排序中提到过“插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率”，
 * 所以我们可以将其中一个子数组中的元素依次插入到另一个数组当中，使其归并后成为一个有序的数组。
 * @param array
 */
function mergeSort(array) {
    function sort(array, first, last) {
        first = (first === undefined) ? 0 : first;  //未定义为0
        last = (last === undefined) ? array.length - 1 : last;  //未定义为 length - 1
        if (last - first < 1) {
            return;
        }
        var middle = Math.floor((first + last) / 2);    // 从序列中间
        sort(array, first, middle); //分别前后进行递归
        sort(array, middle + 1, last);
        // 递归的最深层每个序列长度为1  然后一层层返回已经排序好的单独的小序列 进行归并--> 用插入排序的思想

        var f = first,
            m = middle,
            i, temp;
        // 运行到这里 序列已经分开 开始层层归并了
        while (f <= m && m + 1 <= last) {   //
            // 这里使用插入排序的思想 大的后移
            // 归并两个序列 : 后边的往前边的序列中插入
            if (array[f] >= array[m + 1]) { // 若新序列的小
                temp = array[m + 1];
                for (i = m; i >= f; i--) {  // 老序列元素往后移 将新元素插入
                    // 注意 由于是从最深层由序列长度为1层层向上归并
                    //      所以每一层都可以看作一个已经排序好的序列 然后归并插入新序列
                    array[i + 1] = array[i];
                }
                array[f] = temp;
                m++;
            } else {
                f++;
            }
        }
        return array;
    }
    return sort(array);
}

var array = [6, 5, 3, 1, 8, 7, 2, 4];
console.log(mergeSort(array).toString());