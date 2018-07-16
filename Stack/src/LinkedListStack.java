/**
 *  链表实现栈
 *
 * @author zhengrz
 * @date 2018/7/16 9:43
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("LinkedListStack: top [ ");
        sb.append(linkedList);
        sb.append(" ] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++)
            linkedListStack.push(i);
        System.out.println(linkedListStack);

        linkedListStack.pop();

        System.out.println(linkedListStack);

    }


}
