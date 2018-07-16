
/**
 * @author zhengrz
 * @date 2018/7/16 10:29
 */
public class LinkedListQueue<E> implements Queue<E>{

    private class ListNode {
        public E v;
        public ListNode next;

        public ListNode() {
            this(null, null);
        }

        public ListNode(E v) {
            this.v = v;
            this.next = null;
        }

        public ListNode(E v, ListNode next) {
            this.v = v;
            this.next = next;
        }

        @Override
        public String toString() {
            return v.toString();
        }
    }

    private ListNode head, tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new ListNode(e);
            head = tail;
        } else {
            tail.next = new ListNode(e);
            tail = tail.next;
        }
        size ++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        ListNode retNode = head;
        head = retNode.next;
        retNode.next = null;
        if (head == null)
            tail = null;
        size --;
        return retNode.v;
    }

    @Override
    public E getFront() {
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return head.v;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (ListNode c = head; c != null; c = c.next) {
            sb.append(c.v);
            if (c.next != null) {
                sb.append("->");
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();


        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);
            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }

        System.out.println(queue.getFront());
    }

}
