package AVLTree;

import BST.BST;
import Trie.FileOperation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhengrz
 * @date 2018/7/30 14:29
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {

        private K key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    private int size;
    private Node root;

    public int size() {
        return size;
    }

    /**
     * 判断AVL树是否为空
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 判断该二叉树是否是一颗二分搜索树
     * @return
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrderWithResult(keys);
        for (int i = 1; i < keys.size(); i ++)
            if (keys.get(i-1).compareTo(keys.get(i)) > 0)
                return false;
        return true;
    }

    private void inOrderWithResult(ArrayList<K> keys) {
        if (root == null) return;
        inOrderWithResult(root, keys);
    }

    private void inOrderWithResult(Node node, ArrayList<K> keys) {
        if (node == null) return;
        inOrderWithResult(node.left, keys);
        keys.add(node.key);
        inOrderWithResult(node.right, keys);
    }

    /**
     * 判断这个二叉树是否平衡
     * AVLTree是否平衡特性之一： 节点的左子树与右子树高度不大于1
     * @return
     */
    public boolean isBalance() {
        if (root == null) return false;
        return isBalance(root);
    }

    private boolean isBalance(Node node) {
        if (node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        return isBalance(node.left) && isBalance(node.right);
    }


    public boolean contains(K key) {
        return contains(root, key);
    }

    private boolean contains(Node node, K key) {
        if (node == null) return false;
        if (key.compareTo(node.key) < 0)
            return contains(node.left, key);
        else if (key.compareTo(node.key) > 0)
            return contains(node.right, key);
        else
            return true;
    }


    private int getHeight(Node node) {
        if (node == null) return 0;
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) return 0;
        return getHeight(node.left) - getHeight(node.right);
    }


    public void add (K key, V value) {
        root = add(root, key, value);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }

    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;
    }


    public Node add(Node node, K key, V value) {
        if (node == null) {
            size ++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;

        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 平衡因子
        int balanceFactor = getBalanceFactor(node);
//        if (Math.abs(balanceFactor) > 1)
//            System.out.println("Unbalance: " + balanceFactor);
        // 维护平衡
        // LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < - 1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }




    public K mininum() {
        if (root == null) return null;
        return mininum(root).key;
    }

    private Node mininum(Node node) {
        if (node.left == null)
            return node;
        return mininum(node.left);
    }

    public K maxnum() {
        if (root == null) return null;
        return maxnum(root).key;
    }
    private Node maxnum(Node node) {
        if (node.right == null)
            return node;
        return maxnum(node.right);
    }

    public K removeMin() {
        K ret = mininum();
        root = removeMin(root);
        return ret;
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

    public K removeMax() {
        K ret = maxnum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) return null;
        Node retNode;
        if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {

            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size --;
                retNode = rightNode;
            }

            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size --;
                retNode = leftNode;
            }
            else {
                Node successor = mininum(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        if(retNode == null)
            return null;

        // 更新Height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        int balanceFactor = getBalanceFactor(retNode);

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0)
            retNode = rightRotate(retNode);
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0)
            retNode = leftRotate(retNode);
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }
        return retNode;
    }


    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key){
        if(node == null)
            return null;

        if(key.equals(node.key))
            return node;
        else if(key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }


    public void levelOrder() {
        if (root == null) return;

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        Node last = root;
        Node nLast = null;

        while (!q.isEmpty()) {
            Node node = q.poll();
            System.out.print(node.key);

            if (node.left != null) {
                q.add(node.left);
                nLast = node.left;
            }

            if (node.right != null) {
                q.add(node.right);
                nLast = node.right;
            }

            if (node.equals(last)) {
                System.out.println();
                last = nLast;
            }
        }
    }

    public void preOrderNR() {
        if (root == null) return;
        Stack<Node> stack = new Stack<Node>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            System.out.println(node.key);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) return;
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }


    public void postOrder() {
        postOrder(root);
    }
    private void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }


    public static void main(String[] args) {
//        AVLTree<Integer> avlTree = new AVLTree<Integer>();
//        int[] nums = new int[] { 5, 3, 2, 8, 6, 9, 4 };
//
//        for (int num : nums)
//            avlTree.add(num);

//        System.out.println("avlTree.inOrder(): ");
//        avlTree.preOrder();
//
//        avlTree.remove(5);
//        System.out.println("avlTree.remove(5): ");
//        avlTree.preOrderNR();

//        System.out.println("avlTree.contains(5): " + avlTree.contains(5));
//        avlTree.remove(5);
//        System.out.println("avlTree.contains(5): " + avlTree.contains(5));

        ArrayList<String> words = new ArrayList<>();
        AVLTree<String, Integer> avlTree = new AVLTree<String, Integer>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            for (String word : words) {
                if (avlTree.contains(word))
                    avlTree.add(word, avlTree.get(word) + 1);
                else
                    avlTree.add(word, 1);
            }
            System.out.println("pride-and-prejudice: ");
            System.out.println("Total words: " + words.size());
            System.out.println("Total different words: " + avlTree.size());
            System.out.println("Frequency of PRIDE: " + avlTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avlTree.get("prejudice"));
            System.out.println("The Tree is BST or not: " + avlTree.isBST());
            System.out.println("The Tree is Balance or not: " + avlTree.isBalance());

            avlTree.remove("pride");
            System.out.println("The Tree is BST or not: " + avlTree.isBST());
            System.out.println("The Tree is Balance or not: " + avlTree.isBalance());
        }
    }


}
