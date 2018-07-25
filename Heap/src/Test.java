import java.util.Random;

public class Test {

    public static void main(String[] args) {

        int n = 1000000;

        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) maxHeap.add(random.nextInt(Integer.MAX_VALUE));


        Integer[] numbers = new Integer[n];
        for (int j = 0; j < n; j++) numbers[j] = maxHeap.extractMax();

        for (int k = 1; k < n; k++) {
            if (numbers[k - 1] < numbers[k]) throw new IllegalArgumentException("Error");
        }

        System.out.println("Test MaxHeap Complete!");

    }
}
