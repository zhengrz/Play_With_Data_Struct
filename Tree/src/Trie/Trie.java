package Trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * 应用于： 通讯录
 *
 * 缺点: 费空间, 一个字符一个节点, 可以使用压缩Trie解决
 * 优点: 不像二叉树，根据复杂度根据树的深度决定，而是根据搜索的字符串单词长度解决，由于大部分英文单词都是比较短小的
 *
 * @author zhengrz
 * @date 2018/7/28 9:55
 */
public class Trie {

    class Node {
        private boolean isWord;
        private Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<Character, Node>();
        }

        public Node() {
            this.isWord = false;
            this.next = new TreeMap<Character, Node>();
        }
    }

    private int size;
    private Node root;

    public Trie() {
        this.root = new Node();
        this.size = 0;
    }

    /**
     * Initialize your data structure here.
     * @param word
     */
    public void insert(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            Character c = word.charAt(i);
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) {
            cur.isWord = true;
            this.size ++;
        }
    }

    /**
     * Returns if the word is in the trie.
     * @param word
     * @return
     */
    public boolean search(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            Character c = word.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return cur.isWord;
    }


    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     * @param prefix
     * @return
     */
    public boolean startsWith(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i ++) {
            Character c = prefix.charAt(i);
            if (cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        return true;
    }

}
