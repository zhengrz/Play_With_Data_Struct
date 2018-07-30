package Trie;

import BST.extend.BSTSet;

import java.util.ArrayList;

/**
 * @author zhengrz
 * @date 2018/7/28 10:29
 */
public class Test {


    public static void main(String[] args) {
        System.out.println("pride-and-prejudice: ");

        ArrayList<String> words = new ArrayList<>();

        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();
            BSTSet<String> bstSet = new BSTSet<>();
            for (String word : words)
                bstSet.add(word);
            for (String word : words)
                bstSet.contains(word);
            System.out.println("BSTSet: " + (System.nanoTime() - startTime) / 1000000000.0 + " s");

            startTime = System.nanoTime();
            Trie trie = new Trie();
            for (String word : words)
                trie.insert(word);
            for (String word : words)
                trie.search(word);
            System.out.println("Trie: " + (System.nanoTime() - startTime) / 1000000000.0 + " s");


        }


    }

}
