/**
 * @author zhengrz
 * @date 2018/7/12 10:45
 */
public class ArrayStack<E> implements Stack<E> {

    private Array<E> data;

    public ArrayStack() {
        data = new Array<>();
    }

    public ArrayStack(int capacity) {
        data = new Array<>(capacity);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void push(E e) {
        data.addLast(e);
    }

    @Override
    public E pop() {
        return data.removeLast();
    }

    @Override
    public E peek() {
        return data.getLast();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ArrayStack: ");

        for (int i = 0; i < data.getSize(); i++) {
            sb.append(data.get(i));
            if (i < data.getSize() - 1)
                sb.append(",");
        }
        sb.append("");
        return sb.toString();
    }
}
