package Test;

import AVLTree.AVLTree;
import BST.BSTTree;
import Trie.FileOperation;

import java.util.ArrayList;

/**
 * @author zhengrz
 * @date 2018/7/31 10:53
 */
public class TestAvlTree_BSTTree {

    public static void testAVLTree(AVLTree<String, Integer> tree, ArrayList<String> words) {
        long startTime = System.nanoTime();
        for (String word : words) {
            if (tree.contains(word)) {
                tree.add(word, tree.get(word) + 1);
            } else
                tree.add(word, 1);
        }

        for (String word : words)
            tree.contains(word);

        System.out.println("AVLTree: " + (System.nanoTime() - startTime) / 1000000000.0 + " s");
    }

    public static void testBSTTree(BSTTree<String, Integer> tree, ArrayList<String> words) {
        long startTime = System.nanoTime();
        for (String word : words) {
            if (tree.contains(word)) {
                tree.add(word, tree.get(word) + 1);
            } else
                tree.add(word, 1);
        }

        for (String word : words)
            tree.contains(word);

        System.out.println("BSTTree: " + (System.nanoTime() - startTime) / 1000000000.0 + " s");
    }

    public static void main(String[] args) {

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            testBSTTree(new BSTTree<String, Integer>(), words);
            testAVLTree(new AVLTree<String, Integer>(), words);
        }


    }


}
