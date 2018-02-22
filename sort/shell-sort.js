/**
 * 希尔排序
 * Created by CKH on 2017/4/22.
 *
 * 希尔排序算法是按其设计者希尔（Donald Shell）的名字命名，该算法由1959年公布，是插入排序的一种更高效的改进版本。
 * 它的作法不是每次一个元素挨一个元素的比较。而是初期选用大跨步（增量较大）间隔比较，使记录跳跃式接近它的排序位置；
 * 然后增量缩小；最后增量为 1 ，这样记录移动次数大大减少，提高了排序效率。希尔排序对增量序列的选择没有严格规定。
 *
 * 希尔排序是基于插入排序的以下两点性质而提出改进方法的：
 * 1. 插入排序在对几乎已经排好序的数据操作时， 效率高， 即可以达到线性排序的效率
 * 2. 但插入排序一般来说是低效的， 因为插入排序每次只能将数据移动一位
 *
 * 算法思路：
 * 1. 先取一个正整数 d1(d1 1 个组，所有距离为 d1 的倍数的记录看成一组，然后在各组内进行插入排序
 * 2. 然后取 d2(d2 1)
 * 3. 重复上述分组和排序操作；直到取 di = 1(i >= 1) 位置，即所有记录成为一个组，最后对这个组进行插入排序。
 *    一般选 d1 约为 n/2，d2 为 d1 /2， d3 为 d2/2 ，…， di = 1。
 */


function shellSort(array) {
    function swap(array, i, k) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    var length = array.length,
        gap = Math.floor(length / 2);   // 一般选 d1 约为 n/2，d2 为 d1 /2， d3 为 d2/2 ，…， di = 1

    while (gap > 0) {   // 分组和排序操作；直到取 di = 1(i >= 1) 位置，即所有记录成为一个组
        for (var i = gap; i < length; i++) {
            for (var j = i; j > 0; j -= gap) {  // 所有距离为 d1 的倍数的记录看成一组，
                if (array[j - gap] > array[j]) {    // 然后在各组内进行插入排序
                    swap(array, j - gap, j);    // 小的往前插入 大的后移
                } else {
                    break;
                }
            }
        }
        gap = Math.floor(gap / 2);  // 然后一般取 d2 = d1 / 2
    }
    return array;
}
var array = [80, 93, 60, 12, 42, 30, 68, 85, 10];
console.log(array.toString());
// shellSort(array);
console.log(shellSort(array).toString());










