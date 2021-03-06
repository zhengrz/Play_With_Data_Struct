import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author zhengrz
 * @date 2018/7/19 10:45
 */
public class BSTSetSolution {

   private interface Set<E> {
       void add(E e);

       void remove(E e);

       boolean contains(E e);

       int getSize();

       boolean isEmpty();
   }

   private class BST< E extends Comparable > {

        private class Node {

            private E v;
            private Node left;
            private Node right;

            public Node(E v) {
                this.v = v;
                this.left = null;
                this.right = null;
            }
        }

        private int size;
        private Node root;

        public int getSize() {
            return size;
        }

        public boolean contains(E e) {
            return contains(root, e);
        }

        private boolean contains(Node node, E e) {
            if (node == null) return false;
            if (e.compareTo(node.v) == 0)
                return true;
            else if (e.compareTo(node.v) < 0)
                return contains(node.left, e);
            else
                return contains(node.right, e);
        }

        /**
         * 添加二叉树节点
         * 复杂度: O(logN)
         * @param node
         * @param e
         * @return
         */
        private Node add (Node node, E e) {
            if (node == null) {
                size++;
                return new Node(e);
            }
            if (e.compareTo(node.v) < 0) {
                node.left = add(node.left, e);
            } else if (e.compareTo(node.v) > 0) {
                node.right = add(node.right, e);
            }
            return node;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void add (E e) {
            root = add(root, e);
        }

        /**
         * 递归查找二叉树最小值
         * @return
         */
        public E mininum() {
            if (root == null) return null;
            return mininum(root).v;
        }

        private Node mininum(Node node) {
            if (node.left == null) return node;
            return mininum(node.left);
        }

        /**
         * 递归查找二叉树最大值
         */
        public E maxnum() {
            if (root == null) return null;
            return maxnum(root).v;
        }

        private Node maxnum(Node node) {
            if (node.right == null) return node;
            return maxnum(node.right);
        }

        /**
         * 删除二叉树最小值
         * @return
         */
        public E removeMin() {
            E min = mininum();
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
        public E removeMax() {
            E max = maxnum();
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


        public void remove(E e) {
            root = remove(root, e);
        }

        private Node remove(Node node, E e) {
            if (node == null) return node;

            // 移除元素e 比 当前遍历子节点元素小
            if (e.compareTo(node.v) < 0) {
                node.left = remove(node.left, e);
                return node;
            }

            // 移除元素e 比  当前遍历子节点元素大
            if (e.compareTo(node.v) > 0) {
                node.right = remove(node.right, e);
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
                System.out.print(n.v);

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
                System.out.println(n.v);
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
            System.out.println(node.v);
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
            System.out.println(node.v);
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
            System.out.println(node.v);
        }


    }

   private class BSTSet< E extends Comparable<E> > implements Set<E>{


       private BST<E> bst;

       public BSTSet() {
           bst = new BST<>();
       }

       @Override
       public void add(E e) {
           bst.add(e);
       }

       @Override
       public void remove(E e) {
           bst.remove(e);
       }

       @Override
       public boolean contains(E e) {
           return bst.contains(e);
       }

       @Override
       public int getSize() {
           return bst.getSize();
       }

       @Override
       public boolean isEmpty() {
           return bst.isEmpty();
       }
   }


    public int uniqueMorseRepresentations(String[] words) {

       String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};

       BSTSet<String> bst = new BSTSet<>();
       for (String word : words) {
           StringBuilder sb = new StringBuilder();
           for (int i = 0; i < word.length(); i ++)
               sb.append(codes[word.charAt(i) - 'a']);
           bst.add(sb.toString());
       }
        return bst.getSize();
    }


    public static void main(String[] args) {
        String[] words = { "gin", "zen", "gig", "msg" };
        System.out.println("difference explain num: " + new BSTSetSolution().uniqueMorseRepresentations(words));
    }

}
