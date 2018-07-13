/**
 *  动态数组队列
 *
 * @author zhengrz
 * @date 2018/7/13 11:41
 */
public class ArrayQueue<E> implements Queue<E> {

    private Array<E> arr;

    public ArrayQueue() {
        this(10);
    }

    public ArrayQueue(int capacity) {
        arr = new Array<>(capacity);
    }

    @Override
    public int getSize() {
        return arr.getSize();
    }

    @Override
    public boolean isEmpty() {
        return arr.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        arr.addLast(e);
    }

    @Override
    public E dequeue() {
        return arr.removeFirst();
    }

    @Override
    public E getFront() {
        return arr.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: ");
        res.append("front [");
        for(int i = 0 ; i < arr.getSize() ; i ++){
            res.append(arr.get(i));
            if(i != arr.getSize() - 1)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }
}
