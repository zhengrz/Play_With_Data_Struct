/**
 * 动态数组
 * 复杂度评估使用: 均摊复杂度评估
 * @author zhengrz
 * @date 2018/7/11 15:03
 */
public class Array<E> {

    private E data[];
    private int size;

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array() {
        this(10);
    }

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
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

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
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
