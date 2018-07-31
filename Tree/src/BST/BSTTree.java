package BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhengrz
 * @date 2018/7/31 11:00
 */
public class BSTTree< K extends Comparable, V > {

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node root;  // 根节点

    /**
     * 获取二叉树元素数量
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 判断二叉树是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断某个元素是否存在二叉树
     * @param key
     * @return
     */
    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) return false;
        if (key.compareTo(node.key) == 0) return true;
        else if (key.compareTo(node.key) < 0) return contains(node.left, key);
            // key.compareTo(node.key) > 0
        else return contains(node.right, key);
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node getNode(Node node, K key) {
        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    /**
     * 添加二叉树节点
     * @param node
     * @param key
     * @return
     */
    private Node add (Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    public void add (K key, V value) {
        root = add(root, key, value);
    }

    /**
     * 递归查找二叉树最小值
     * @return
     */
    public K mininum() {
        if (root == null) return null;
        return mininum(root).key;
    }

    private Node mininum(Node node) {
        if (node.left == null) return node;
        return mininum(node.left);
    }

    /**
     * 递归查找二叉树最大值
     */
    public K maxnum() {
        if (root == null) return null;
        return maxnum(root).key;
    }

    private Node maxnum(Node node) {
        if (node.right == null) return node;
        return maxnum(node.right);
    }

    /**
     * 删除二叉树最小值
     * @return
     */
    public K removeMin() {
        K min = mininum();
        root = removeMin(root);
        return min;
    }

    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 刪除二叉树最大值
     * @return
     */
    public K removeMax() {
        K max = maxnum();
        root = removeMax(root);
        return max;
    }

    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.right = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除任意位置的元素
     * @param key
     */
    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) return node;

        // 移除元素e 比 当前遍历子节点元素小
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }

        // 移除元素e 比  当前遍历子节点元素大
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        }

        else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            // 移除元素e 等于 当前遍历子节点元素
            Node successor = mininum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }


    /**
     * 层序遍历二叉树(广度遍历二叉树)
     */
    public void levelOrder() {
        if (root == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node last = root;  //
        Node nlast = null;

        while (!q.isEmpty()) {
            Node n = q.poll();
            System.out.print(n.key);

            if (n.left != null) {
                q.add(n.left);
                nlast = n.left;
            }
            if (n.right != null) {
                q.add(n.right);
                nlast = n.right;
            }

            if (n.equals(last)) {
                System.out.print("\n");
                last = nlast;
            }

        }


    }


    /**
     * 利用栈替代系统栈，实现非递归前序遍历二叉树
     */
    public void preOrderNR() {
        if (root == null) return;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node n = stack.pop();
            System.out.println(n.key);
            if (n.right != null)
                stack.push(n.right);
            if (n.left != null) {
                stack.push(n.left);
            }
        }
    }


    /**
     * 递归前序遍历二叉树
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 递归中序遍历二叉树
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }

    /**
     * 递归后序遍历二叉树
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }



}
