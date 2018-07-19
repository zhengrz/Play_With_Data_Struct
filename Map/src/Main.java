import java.util.ArrayList;

public class Main {

    private static double testMap(Map<String, Integer> map, String fileName) {
        System.out.println("Pride and Prejudice");
        long startTime = System.nanoTime();
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(fileName, words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }
            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        return ( System.nanoTime() - startTime ) / 1000000000.0;
    }


    public static void main(String[] args) {
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        String fileName = "pride-and-prejudice.txt";
        System.out.println("LinkedListMap cost time: " + testMap(linkedListMap, fileName) + " s");;

        System.out.println();

        BSTMap<String, Integer> bstMap = new BSTMap<>();
        System.out.println("BSTMap cost time: " + testMap(bstMap, fileName) + " s");;












//        for (int i = 0; i < 5; i ++)
//            map.add(String.valueOf(i), i);
//        System.out.println("current map size: " + map.getSize());
//        System.out.println("current map remove element: key(1) : value(" + map.remove("1") + ")");

    }
}
