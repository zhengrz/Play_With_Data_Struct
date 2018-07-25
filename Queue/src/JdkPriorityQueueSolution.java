import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @author zhengrz
 * @date 2018/7/25 16:09
 */
public class JdkPriorityQueueSolution {


    private class Freq implements Comparable<Freq> {

        private int e;
        private int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) return -1;
            else if (this.freq > another.freq) return 1;
            else return 0;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }
        PriorityQueue<Freq> pb = new PriorityQueue<>();
        for (int key : map.keySet()) {
            if (pb.size() < k) pb.add(new Freq(key, map.get(key)));
            else if (map.get(key) > pb.peek().freq) {
                pb.remove();
                pb.add(new Freq(key, map.get(key)));
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pb.isEmpty())res.add(pb.remove().e);
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 1, 1, 2, 2, 3 };
        System.out.println(new JdkPriorityQueueSolution().topKFrequent(nums, 2));
    }


}
