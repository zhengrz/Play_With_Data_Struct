import java.util.ArrayList;

/**
 * @author zhengrz
 * @date 2018/7/18 16:08
 */
public class BSTSet< E extends Comparable<E> > implements Set<E>{


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

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();  // 记录字数的计数器
        BSTSet<String> counter = new BSTSet<>();     // 记录不同字的计数器
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words)
                counter.add(word);
        }
        System.out.println("Total different words: " + counter.getSize());

        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        BSTSet<String> counter2 = new BSTSet<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());
            for(String word: words2)
                counter2.add(word);
            System.out.println("Total different words: " + counter2.getSize());
        }
    }

}
