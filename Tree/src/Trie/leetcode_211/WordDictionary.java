package Trie.leetcode_211;

import java.util.TreeMap;

/**
 * 211. 添加与搜索单词 - 数据结构设计(https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/description/)
 *
 * 设计一个支持以下两种操作的数据结构：

 * void addWord(word)
 * bool search(word)
 * search(word) 可以搜索文字或正则表达式字符串，字符串只包含字母 . 或 a-z 。 . 可以表示任何一个字母。
 *
 *
 * 示例:
 *    addWord("bad")
 *    addWord("dad")
 *    addWord("mad")
 *    search("pad") -> false
 *    search("bad") -> true
 *    search(".ad") -> true
 *    search("b..") -> true
 *
 * @author zhengrz
 * @date 2018/7/28 14:59
 */
public class WordDictionary {

    private class Node {
        private boolean isWord;
        private TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            this.next = new TreeMap<>();
        }
        public Node() {
            this(false);
        }
    }

    private Node root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new Node();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i ++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length())
            return node.isWord;
        char c = word.charAt(index);
        if (c != '.') {
            if (node.next.get(c) == null)
                return false;
            return match(node.next.get(c), word, index + 1);
        } else {
            for (char ck : node.next.keySet())
                if (match(node.next.get(ck), word, index + 1))
                    return true;
            return false;
        }
    }

    /**
     * Your WordDictionary object will be instantiated and called as such:
     * WordDictionary obj = new WordDictionary();
     * obj.addWord(word);
     * boolean param_2 = obj.search(word);
     */

    public static void main(String[] args) {
        WordDictionary d = new WordDictionary();
        d.addWord("bad");
        d.addWord("dad");
        d.addWord("mad");
        System.out.println("d.search(\"pad\") -> " + d.search("pad"));
        System.out.println("d.search(\"bad\") -> " + d.search("bad"));;
        System.out.println("d.search(\".ad\") -> " + d.search(".ad"));;
        System.out.println("d.search(\"b..\") -> " + d.search("b.."));;
    }


}
