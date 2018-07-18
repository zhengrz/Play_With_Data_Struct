/**
 * 链表
 *
 * @author zhengrz
 * @date 2018/7/13 16:18
 */
public class LinkedList<E> {

    private Node dumyHead;
    private int size;

    private class Node{
        public E elem;      // 值域
        public Node next;   // 指向下一个节点指针

        public Node() {
            this(null, null);
        }

        public Node(E e) {
            this.elem = e;
            this.next = null;
        }
        public Node(E e, Node next) {
            this.elem = e;
            this.next = next;
        }

        public String toString() {
            return elem.toString();
        }
    }

    public Node getHead() {
        return dumyHead.next;
    }

    public LinkedList() {
        this.dumyHead = new Node(null, null);
        this.size = 0;
    }

    /**
     * 返回链表是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取链表中的元素个数
     * @return
     */
    public int getSize() {
        return size;
    }


    /**
     * 在链表头添加新的元素e
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在链表的index(0-based)位置添加新的元素e
     * 在链表中不是一个常用的操作，练习用：）
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        Node prev = dumyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 在链表末尾添加新的元素e
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获得链表的第index(0-based)个位置的元素
     * 在链表中不是一个常用的操作，练习用：）
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        Node cur = dumyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur.elem;
    }

    /**
     * 获得链表的第一个元素
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 获得链表的最后一个元素
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }

    /**
     * 修改链表的第index(0-based)个位置的元素为e
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        Node cur = dumyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.elem = e;
    }

    /**
     * 查找链表中是否有元素e
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node cur = dumyHead.next;
        while (cur != null) {
            if (cur.elem.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }


    /**
     * 从链表中删除第一个元素, 返回删除的元素
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 根据index删除链表的元素
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
        Node prev = dumyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        Node ret = prev.next;
        prev.next = ret.next;
        ret.next = null;
        size --;
        return ret.elem;
    }

    /**
     * 从链表中删除元素e
     * @param e
     * @return
     */
    public E removeElement(E e) {
        Node prev = dumyHead;
        while(prev.next != null) {
            if (prev.next.elem.equals(e)) {
                Node ret = prev.next;
                prev.next = ret.next;
                ret.next = null;
                size --;
                break;
            }
            prev = prev.next;
        }
        return prev.elem;
    }

    /**
     * 从链表中删除最后一个元素, 返回删除的元素
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (Node c = dumyHead.next; c != null; c = c.next)
            sb.append(c.elem).append("->");
        sb.append("NULL");
        return sb.toString();
    }


}
