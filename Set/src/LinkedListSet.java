import java.util.ArrayList;

/**
 * @author zhengrz
 * @date 2018/7/18 17:04
 */
public class LinkedListSet<E> implements Set<E> {

    private LinkedList<E> list;

    public LinkedListSet() {
        list = new LinkedList<>();
    }

    @Override
    public void add(E e) {
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeLast();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");
        ArrayList<String> words = new ArrayList<>();  // 记录字数的计数器
        LinkedListSet<String> counter = new LinkedListSet<>();     // 记录不同字的计数器
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words)
                counter.add(word);
        }
        System.out.println("Total different words: " + counter.getSize());

        System.out.println();

        System.out.println("A Tale of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        LinkedListSet<String> counter2 = new LinkedListSet<>();
        if(FileOperation.readFile("a-tale-of-two-cities.txt", words2)){
            System.out.println("Total words: " + words2.size());
            for(String word: words2)
                counter2.add(word);
            System.out.println("Total different words: " + counter2.getSize());
        }
    }
}
