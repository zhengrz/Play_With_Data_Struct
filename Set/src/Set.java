/**
 * @author zhengrz
 * @date 2018/7/18 16:07
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();

}
