/**
 * @author zhengrz
 * @date 2018/7/11 17:40
 */
public interface Stack<E> {

    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();



}
