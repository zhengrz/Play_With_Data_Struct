/**
 *
 * 基于链表的Map集合
 *
 * @author zhengrz
 * @date 2018/7/19 11:51
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        K key;
        V value;
        Node next;

        public Node (K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node () {
            this(null, null, null);
        }

    }

    private Node dumyHead;
    private int size;

    public LinkedListMap() {
        this.dumyHead = new Node();
        this.size = 0;
    }

    private Node getNode(K key) {
        Node cur = this.dumyHead.next;
        while(cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            this.dumyHead.next = new Node(key, value, this.dumyHead.next);
            size ++;
        }
        else
            node.value = value;
    }

    @Override
    public V remove(K key) {
        Node prev = this.dumyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))  break;
            prev = prev.next;
        }
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size --;
            return delNode.value;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) == null ? false : true;
    }

    @Override
    public V get(K key) {
        Node n = getNode(key);
        if (n != null) return n.value;
        return null;
    }

    @Override
    public void set(K key, V newValue) {
        Node n = getNode(key);
        if (n == null) throw new IllegalArgumentException(key + " not exsit");
        n.value = newValue;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }


}
