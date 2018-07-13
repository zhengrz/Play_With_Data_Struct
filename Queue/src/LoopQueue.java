import java.util.Arrays;

/**
 *  动态循环队列
 *
 *   难点在于 队首指针(front)与队尾指针(tail)移动与计算
 *           队首指针移动下一个位置计算公式:  (front + 1) % data.length
 *           队尾指针移动下一个位置计算公式:  (tail + 1) % data.length
 *           队列是否为空:   队首指针与队尾指针指的是否同一个内存地址, 即:  front == tail
 *
 * @author zhengrz
 * @date 2018/7/13 13:58
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;       // 队列长度
    private int front;      // 队首指针
    private int tail;       // 队尾指针
    private int size;       // 元素个数

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if (( tail + 1 ) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        E e = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize( getCapacity() / 2 );
        return e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    private void resize(int capacity) {
        E[] newQueue = (E[]) new Object[capacity + 1];
        for (int i = 0; i < size; i++)
            newQueue[i] = data[(i + front) % data.length];
        front = 0;
        tail = size;
        data = newQueue;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer(String.format("LoopQueue: size: %d, capacity: %d\nfront [ ", size, getCapacity()));
        for (int i = front; i != tail; i = (i+1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail)
                sb.append(", ");
        }
        sb.append(" ] tail \n");
        return sb.toString();
    }
}
