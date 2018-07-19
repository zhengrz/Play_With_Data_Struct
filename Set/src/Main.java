import java.util.ArrayList;

public class Main {

    public static double testSet(Set set, String fileName) {
        long startTime = System.nanoTime();
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total different words: " + set.getSize());
        }

        return ( System.nanoTime() - startTime ) / 1000000000.0;
    }

    public static void main(String[] args) {
        String fileName = "pride-and-prejudice.txt";
        BSTSet<String> bstSet = new BSTSet<>();
        System.out.println("BSTSet cost time: " + testSet(bstSet, fileName) + " s");

        System.out.println();

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        System.out.println("LinkedListSet cost time: " + testSet(linkedListSet, fileName) + " s");

    }
}
