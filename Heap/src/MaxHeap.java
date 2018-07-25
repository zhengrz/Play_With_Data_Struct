/**
 * @author zhengrz
 * @date 2018/7/25 10:00
 */
public class MaxHeap<E extends Comparable<E>> {


    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(int capacity) {
        data = new Array<E>(capacity);
    }

    /**
     * heapify构造完全二叉堆,复杂度: O(n)
     * @param arr
     */
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i --)
            siftDown(i);
    }

    /**
     * 获取完全二叉堆的元素个数
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 完全二叉堆是否为空
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-0 doesn't have parent.");
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中,一个索引所表示的元素的右孩子节点的索引
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 完全二叉堆的上浮操作, 复杂度: log(N)
     * @param index
     */
    private void siftUp(int index) {
        // 1. 索引值合法判断 2. 当父节点比其子节点小，不满足最大堆性质，子节点上浮
        while ( index > 0 && data.get(parent(index)).compareTo(data.get(index)) < 0) {
            data.swap(index, parent(index));  // 父节点与当前遍历子节点交换位置
            index = parent(index);            // 父节点索引赋值index，进行下一次上浮操作，直至index = 0
        }
    }

    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    public E findMax() {
        if (data.getSize() == 0) throw new IllegalArgumentException("Can not findMax when heap is empty");
        return data.get(0);
    }

    /**
     * 完全二叉堆的下沉操作, 复杂度: log(N)
     * @param index
     */
    private void siftDown(int index) {

        while(leftChild(index) < data.getSize()) {

            int j = leftChild(index);

            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(index);
            // data[j] 是 leftChild 和 rightChild 中最大值

            // leftChild 和 rightChild 中最大值不大于父节点，满足最大堆性质，退出遍历
            if(data.get(j).compareTo(data.get(index)) < 0 ) break;

            // 不满足最大堆性质，父节点与  leftChild 和 rightChild 中最大值交换位置
            data.swap(j, index);
            // 赋值 leftChild 和 rightChild 中最大值的索引  进行后续上浮操作
            index = j;
        }


    }

    /**
     * 取出堆中最大元素, 复杂度: log(N), 主要体现在下沉操作
     * @return
     */
    public E extractMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    /**
     * 替换最大堆中最大的元素
     * @param e
     * @return
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

}
