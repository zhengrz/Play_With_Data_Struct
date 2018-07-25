import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * https://leetcode-cn.com/problems/top-k-frequent-elements/description/
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 例如:
 *   给定数组 [1,1,1,2,2,3] , 和 k = 2，返回 [1,2]。
 * 注意：
 * 1. 你可以假设给定的 k 总是合理的，1 ≤ k ≤ 数组中不相同的元素的个数。
 * 2. 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 *
 * @author zhengrz
 * @date 2018/7/25 15:25
 */
public class PriorityQueueSolution {

    private class Array<E> {

        private E data[];
        private int size;

        // 无参数的构造函数，默认数组的容量capacity=10
        public Array() {
            this(10);
        }

        // 构造函数，传入数组的容量capacity构造Array
        public Array(int capacity) {
            data = (E[]) new Object[capacity];
        }

        public Array(E[] arr) {
            data = (E[]) new Object[arr.length];
            for( int i = 0; i < arr.length; i++ ) data[i] = arr[i];
            size = data.length;
        }

        /**
         * 在index索引的位置插入一个新元素e, 复杂度O(1)
         * @param index 索引
         * @param e     添加元素
         */
        public void add(int index, E e) {
            if (index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
            if (size == data.length)
                resize(2 * data.length);
            for (int i = size - 1; i >= index; i--)
                data[i+1] = data[i];
            data[index] = e;
            size++;
        }

        /**
         * 在所有元素前添加一个新元素
         * @param e 添加元素
         */
        public void addFirst(E e) {
            add(0, e);
        }

        /**
         * 向所有元素后添加一个新元素
         * @param e 添加的元素
         */
        public void addLast(E e) {
            add(size, e);
        }

        /**
         * 查找数组中是否有元素e
         * @param e 检测是否包含元素
         * @return
         */
        public boolean contains(E e) {
            for (int i = 0; i < size -1; i++) {
                if (data[i].equals(e))
                    return true;
            }
            return false;
        }

        /**
         * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
         * @param e 查找的元素
         * @return  元素所在索引位置
         */
        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) return i;
            }
            return -1;
        }

        /**
         * 获取index索引位置的元素
         * @param index 元素索引位置
         * @return      对应元素索引位置的元素值
         */
        public E get(int index) {
            if (index < 0 || index > size)
                throw new ArrayIndexOutOfBoundsException("Get failed. Required index >= 0 and index <= size.");
            return data[index];
        }

        public E getFirst() {
            return data[0];
        }

        /**
         * 获取数组的容量
         * @return 数组的容量
         */
        public int getCapacity() {
            return data.length;
        }

        /**
         * 获取数组中的元素个数
         * @return
         */
        public int getSize() {
            return size;
        }

        /**
         * 返回数组是否为空
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 从数组中删除index位置的元素, 返回删除的元素, 复杂度：O（1）
         * @param index 元素索引位置
         * @return      删除的元素
         */
        public E remove(int index) {
            if (index < 0 || index > size)
                throw new ArrayIndexOutOfBoundsException("Remove failed. Required index >= 0 and index <= size.");
            E ret = data[index];
            for (int i = index + 1; i < size; i++)
                data[i - 1] = data[i];
            size--;
            data[size] = null; // 触发垃圾回收
            if (size == data.length / 4 && data.length / 2 != 0) // 之所以除以4  是因为当 删除操作与添加操作重复操作, 会产生复杂度震荡（即： 本来均摊复杂度是O（1）, 但是因为震荡复杂度在O(n)）,之所以以均摊复杂度评估，是因为只有当容量不够才出发最坏情况，因此不用最坏情况评估，也就是O（n）
                resize(data.length / 2);
            return ret;
        }

        /**
         * 从数组中删除元素e
         * @param e
         */
        public void removeElement(E e) {
            int index = find(e);
            if (index != -1)
                remove(index);
        }

        /**
         * 从数组中删除第一个元素, 返回删除的元素
         * @return
         */
        public E removeFirst() {
            return remove(0);
        }

        /**
         * 从数组中删除最后一个元素, 返回删除的元素
         * @return
         */
        public E removeLast() {
            return remove(size - 1);
        }

        /**
         * 将数组空间的容量变成newCapacity大小
         * @param newCapacity  新数组capacity大小
         */
        private void resize(int newCapacity) {
            E[] newData = (E[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
                newData[i] = data[i];
            data = newData;
        }

        // 修改index索引位置的元素为e
        public void set(int index, E e) {
            if (index < 0 || index > size)
                throw new ArrayIndexOutOfBoundsException("Remove failed. Required index >= 0 and index <= size.");
            data[index] = e;
        }

        public void swap(int i, int j) {
            if (i < 0 || i >= size || j < 0 || j >= size) throw new IllegalArgumentException("index is illegal");
            E tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Array: size = ");
            sb.append(size)
                    .append(" , ")
                    .append("capacity = ")
                    .append(data.length)
                    .append("\n[");
            for(int i = 0; i < size; i++) {
                if (i < size-1)
                    sb.append(data[i]).append(", ");
            }
            sb.append(data[size-1]).append("]");
            return sb.toString();
        }
    }
    private interface Queue<E> {

        int getSize();
        boolean isEmpty();
        void enqueue(E e);
        E dequeue();
        E getFront();

    }
    private class PriorityQueue<E extends Comparable<E>> implements Queue<E>{

        private class MaxHeap<E extends Comparable<E>> {


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

        private MaxHeap<E> maxHeap;

        public PriorityQueue(int capacity) {
            maxHeap = new MaxHeap(capacity);
        }

        public PriorityQueue() {
            maxHeap = new MaxHeap();
        }

        @Override
        public int getSize() {
            return maxHeap.size();
        }

        @Override
        public boolean isEmpty() {
            return maxHeap.isEmpty();
        }

        @Override
        public void enqueue(E e) {
            maxHeap.add(e);
        }

        @Override
        public E dequeue() {
            return maxHeap.extractMax();
        }

        @Override
        public E getFront() {
            return maxHeap.findMax();
        }




    }

    private class Freq implements Comparable<Freq> {

        private int e;      // 对应数字
        private int freq;   // 对应词频

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) return 1;
            else if (this.freq > another.freq) return -1;
            else return 0;
        }
    }

    /**
     * 复杂度: O(n log k)
     * 一次进队出队复杂度为log K, 遍历n个元素
     * @param nums
     * @param k
     * @return
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 遍历数组，记录词频，  key是对应数字   value是对应数字的词频
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }

        PriorityQueue<Freq> pq = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pq.getSize() < k) pq.enqueue(new Freq(key, map.get(key)));
            else if (map.get(key) > pq.getFront().freq) {  // 对k个元素的优先队列进行比较，如果当前map中key对应的值 大于 队列的 就进行出队进队 更优先的元素
                pq.dequeue();
                pq.enqueue(new Freq(key, map.get(key)));
            }
        }
        LinkedList<Integer> list = new LinkedList<>();
        while (!pq.isEmpty()) list.add(pq.dequeue().e);
        return list;
    }

    public static void main(String[] args) {
        int[] n = new int[] { 1, 1, 1, 2, 2, 3 };
        System.out.println(new PriorityQueueSolution().topKFrequent(n, 2));
    }


}
