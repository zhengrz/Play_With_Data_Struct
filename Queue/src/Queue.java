/**
 * @author zhengrz
 * @date 2018/7/12 15:08
 */
public interface Queue<E> {

    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();

}
