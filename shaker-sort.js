/**
 * 鸡尾酒排序 cocktail sort
 * 其实它还有很多奇怪的名称，比如双向冒泡排序 (Bidirectional Bubble Sort)、波浪排序 (Ripple Sort)、摇曳排序 (Shuffle Sort)、飞梭排序 (Shuttle Sort) 和欢乐时光排序 (Happy Hour Sort)
 * Created by CKH on 2017/4/23.
 *
 * 鸡尾酒排序是冒泡排序的轻微变形。
 * 不同的地方在于，鸡尾酒排序是从低到高然后从高到低来回排序，
 * 而冒泡排序则仅从低到高去比较序列里的每个元素。他可比冒泡排序的效率稍微好一点，
 * 原因是冒泡排序只从一个方向进行比对(由低到高)，每次循环只移动一个项目。
 *
 * 以序列(2,3,4,5,1)为例，鸡尾酒排序只需要访问一次序列就可以完成排序，但如果使用冒泡排序则需要四次。
 * 但是在乱数序列状态下，鸡尾酒排序与冒泡排序的效率都很差劲，优点只有原理简单这一点。
 * 排序过程：
 * 1. 先对数组从左到右进行冒泡排序（升序），则最大的元素去到最右端
 * 2. 再对数组从右到左进行冒泡排序（降序），则最小的元素去到最左端
 * 3. 以此类推，依次改变冒泡的方向，并不断缩小未排序元素的范围，直到最后一个元素结束
 */


function shakerSort(array) {
    function swap(array, i, j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    var length = array.length,
        left = 0,
        right = length - 1,
        lastSwappedLeft = left,
        lastSwappedRight = right,
        i, j;

    while (left < right) {  // 依次改变冒泡的方向，并不断缩小未排序元素的范围，直到最后一个元素结束
        // 先对数组从左到右进行冒泡排序（升序），则最大的元素去到最右端
        lastSwappedRight = 0;
        for (i = left; i < right; i++) {
            if (array[i] > array[i + 1]) {
                swap(array, i, i + 1);
                lastSwappedRight = i;   // lastSwappedRight 一定是从左往右最大元素左边的位置
            }
        }
        right = lastSwappedRight;   // 然后缩小范围 不再比较最大的元素
        // 再对数组从右到左进行冒泡排序（降序），则最小的元素去到最左端
        lastSwappedLeft = length - 1;
        for (j = right; left < j; j--) {
            if (array[j - 1] > array[j]) {  // 小的往左移
                swap(array, j - 1, j);
                lastSwappedLeft = j;
            }
        }
        left = lastSwappedLeft; // 缩小范围 不再比较最小的元素
    }
    return array;
}

var array = [45, 19, 77, 81, 13, 28, 18, 19, 77];
console.log(shakerSort(array).toString());