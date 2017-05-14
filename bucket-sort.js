/**
 * 桶排序 (Bucket sort)或所谓的箱排序的原理是将数组分到有限数量的桶子里，然后对每个桶子再分别排序
 * (有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序），最后将各个桶中的数据有序的合并起来。
 * Created by CKH on 2017/4/23.
 *
 * 排序过程：
 * 1. 假设待排序的一组数统一的分布在一个范围中，并将这一范围划分成几个子范围，也就是桶
 * 2. 将待排序的一组数，分档规入这些子桶，并将桶中的数据进行排序
 * 3. 将各个桶中的数据有序的合并起来
 */

// 首先用最笨的方法，每一个桶只能放相同的数字，最大桶的数量为数组中的正数最大值加上负数最小值的绝对值。
// 桶的范围为1  step=1
function bucketSortStep1(array) {
    var bucket = [],    //正数桶
        negativeBucket = [],    //负数桶
        result = [],
        l = array.length,
        i, j, k, abs;

    // 入桶
    for (i = 0; i < l; i++) {
        if (array[i] < 0) {
            abs = Math.abs(array[i]);
            if (!negativeBucket[abs]) {
                // 若元素在桶内不存在任何元素
                // 先将该元素处定义成数组 从而再在后边添加元素
                negativeBucket[abs] = [];
            }
            negativeBucket[abs].push(array[i]);
            // 入桶时将元素插入以元素绝对值为序号的桶处
        } else {
            if (!bucket[array[i]]) {
                bucket[array[i]] = [];
            }
            bucket[array[i]].push(array[i]);
        }
    }

    // 出桶
    // 先出负数 从后往前 因为入桶的时候是按绝对值存放的  绝对值越大 值越小
    l = negativeBucket.length;
    for (i = l - 1; i >= 0; i--) {
        if (negativeBucket[i]) {    // 元素存在
            k = negativeBucket[i].length;   // 查看桶内的元素数量
            for (j = 0; j < k; j++) {
                result.push(negativeBucket[i][j]);
            }
        }
    }
    l = bucket.length;
    for (i = 0; i < l; i++) {
        if (bucket[i]) {
            k = bucket[i].length;
            for (j = 0; j < k; j++) {
                result.push(bucket[i][j]);
            }
        }
    }
    return result;
}

/**
 * 每个桶存放一定范围的数字，用 step 参数来设置该范围，取 step 为 1 就退化成前一种实现方式。
 * @param array 将要排序的数组
 * @param step 划分桶的步长. 如step=5 表示每个桶存放的范围是5: -6~-1, 0~5, 6~11
 */
function bucketSort(array, step) {
    var result = [],
        bucket = [],
        bucketCount,
        l = array.length,
        i, j, k, s,
        max = array[0],
        min = array[0],
        temp;

    // 找出最大最小的元素  以来确定桶的数量
    for (i = 1; i < l; i++) {
        if (array[i] > max) {
            max = array[i];
        }
        if (array[i] < min) {
            min = array[i];
        }
    }
    min = min - 1;
    // 需要的桶的数量
    bucketCount = Math.ceil((max - min) / step);    // ceil(x) 函数返回一个大于或等于 x 的的最小整数

    for (i = 0; i < l; i++) {   // 判断每一个数字将要放进哪个桶内
        temp = array[i];
        for (j = 0; j < bucketCount; j++) {
            // 判断放入哪个桶
            // 元素的值在桶的范围内
            if (temp > (min + step * j) && temp <= (min + step * (j + 1))){
                // 桶内不存在元素 初始化每个桶
                if (!bucket[j]) {
                    bucket[j] = [];
                }
                // 通过插入排序将数字插入到桶内的合适位置
                s = bucket[j].length;   // 桶内元素的数量
                if (s > 0) {
                    // 桶内已存在元素
                    // 用插入排序的思想在桶内内插入元素
                    for (k = s - 1; k >= 0; k--) {
                        if (bucket[j][k] > temp) {  // 大的后移
                            bucket[j][k + 1] = bucket[j][k];
                        } else {
                            break;
                        }
                    }
                    bucket[j][k + 1] = temp;
                } else {    // 桶内没有任何元素 直接push
                    bucket[j].push(temp);
                }
            }
        }
    }

    // 循环取出桶中的元素
    for (i = 0; i < bucketCount; i++) {
        if (bucket[i]) {
            k = bucket[i].length;
            for (j = 0; j < k; j++) {
                result.push(bucket[i][j]);
            }
        }
    }
    return result;
}

var array = [29, 25, 3, 49, 9, 37, 21, 43];
var at = array;
console.log(bucketSortStep1(array).toString());
console.log(bucketSort(array, 5).toString());