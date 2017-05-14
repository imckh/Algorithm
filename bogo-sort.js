/**
 * 猴子排序 (Bogo Sort)
 * Created by CKH on 2017/4/23.
 * 猴子排序 (Bogo Sort) 是个既不实用又原始的排序算法，
 * 其原理等同将一堆卡片抛起，落在桌上后检查卡片是否已整齐排列好，若非就再抛一次。
 * 其名字源自 Quantum bogodynamics，又称 bozo sort、blort sort 或猴子排序。
 * 并且在最坏的情况下所需时间是无限的。
 */
function bogoSort(array) {

    function swap(array, i, j) {
        var temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // 随机交换顺序
    function shuffle(array) {
        var length = array.length;
        for (var i = 0; i < l; i++) {
            var j = Math.floor((Math.random() * length));
            swap(array, i, j);
        }
    }

    // 判断是否排好序
    function isSorted(array) {
        var length = array.length;
        for (var i = 1; i < length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    var sorted = false;
    while (sorted == false) {   // 效率低下
        shuffle(array);
        sorted = isSorted(array);
    }

    return array;
}
