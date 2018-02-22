/**
 * 基数排序 (Radix Sort) 是一种非比较型整数排序算法，其原理是将整数按位数切割成不同的数字，然后按每个位数分别比较。
 * Created by CKH on 2017/4/23.
 * 排序过程：将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
 * 然后，从最低位开始，依次进行一次排序。这样从最低位排序一直到最高位排序完成以后, 数列就变成一个有序序列。
 *
 * 基数排序法会使用到桶 (Bucket)，顾名思义，通过将要比较的位（个位、十位、百位…），
 * 将要排序的元素分配至 0~9 个桶中，借以达到排序的作用，在某些时候，基数排序法的效率高于其它的比较性排序法。
 */

/**
 * 基数排序的方式可以采用 LSD (Least sgnificant digital) 或 MSD (Most sgnificant digital)，
 * LSD 的排序方式由键值的最右边开始，而 MSD 则相反，由键值的最左边开始。 以 LSD 为例
 * 首先根据个位数的数值，按照个位置等于桶编号的方式，将它们分配至编号0到9的桶子中
 * 然后，将这些数字按照桶以及桶内部的排序连接起来
 * 接着按照十位的数值，分别对号入座
 * 最后按照次序重现连接，完成排序
 * @param array
 */
function radixSort(array) {
    var bucket = [],
        l = array.length,
        loop,
        str,
        i, j, k, t,
        max = array[0];

    for (i = 1; i < l; i++) {
        if (array[i] > max) {
            max = array[i];
        }
    }
    loop = (max + '').length;   // 找出最大数的位数

    for (i = 0; i < 10; i++) {
        // 初始化桶
        bucket[i] = [];
    }
    // 从低位到
    for (i = 0; i < loop; i++) {
        for (j = 0; j < l; j++) {
            str = array[j] + '';
            // 以最大数的位数为基准 小的数位数向他对齐
            // 也就是若位数比最大数的小 则前边补0
            // 如 12345 01234 00123 00012 00001
            if (str.length >= i + 1) {
                k = parseInt(str[str.length - i - 1]);  // 插入最后一位的值的桶
                bucket[k].push(array[j]);
            } else {    // 高位为0 直接插入0号桶
                bucket[0].push(array[j]);
            }
        }

        array.splice(0, l);     // 清空数组   splice(要删除的起始下标, 要删除的项数)
        // 将这一个loop最后一位按这一位的大小排序放回array数组
        // 下一个循环将按高位的顺序重新放一遍
        for (j = 0; j < 10; j++) {
            t = bucket[j].length;
            for (k = 0; k < t; k++) {
                array.push(bucket[j][k]);
            }
            bucket[j] = [];     //清空桶  以便进行更高位的桶排序
        }
    }
    return array;
}

var array = [36, 9, 0, 25, 1, 49, 64, 16, 81, 4];
console.log(radixSort(array));