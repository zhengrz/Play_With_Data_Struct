import java.util.Random;

/**
 * @author zhengrz
 * @date 2018/7/25 11:55
 */
public class Test2 {

    private static double testMaxHeap(Integer[] testData, Boolean isHeapify) {
        long startTime = System.nanoTime();
        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num : testData) maxHeap.add(num);
        }
        int[] numbers = new int[testData.length];
        for (int j = 0; j < testData.length; j++) numbers[j] = maxHeap.extractMax();
        for (int k = 1; k < testData.length; k++) {
            if (numbers[k - 1] < numbers[k]) throw new IllegalArgumentException("Error");
        }
        return (System.nanoTime() - startTime) / 1000000000.0;
    }

    public static void main(String args[]) {
        int n = 1000000;
        Integer testData[] = new Integer[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) testData[i] = random.nextInt(Integer.MAX_VALUE);
        System.out.println("without heapify: " + testMaxHeap(testData, false) + " s");  // 复杂度: O(nlogN) => n个add => n个siftUp
        System.out.println("    has heapify: " + testMaxHeap(testData, true) + " s");   // 复杂度: O(n)
    }

}
