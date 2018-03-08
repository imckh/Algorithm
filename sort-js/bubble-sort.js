/**
 * 冒泡排序
 * Created by CKH on 2017/4/21.
 *
 * 冒泡排序算法的流程如下：
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。在这一点，最后的元素应该会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 */

arr = [5, 1, 4, 2, 8];

document.write(arr + '<br/>');
function bubbleSort(array) {
    var length = array.length,
        i,
        j,
        temp;
    for (i = length - 1; i > 0; i--) {
        for (j = 0; j < i; j++) {
            if (array[j] > array[j + 1]) {
                temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
            }
        }
    }
    return array;
}
document.write(bubbleSort(arr));
