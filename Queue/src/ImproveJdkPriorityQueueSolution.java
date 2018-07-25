import java.util.*;
import java.util.PriorityQueue;

/**
 * 优化代码
 * @author zhengrz
 * @date 2018/7/25 16:30
 */
public class ImproveJdkPriorityQueueSolution {



    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) map.put(num, map.get(num) + 1);
            else map.put(num, 1);
        }

        PriorityQueue<Integer> pb = new PriorityQueue<>(
//                (a, b) ->  map.get(a) - map.get(b)
                Comparator.comparingInt(map::get)
        );
        for (int num : map.keySet()) {
            if (pb.size() < k) pb.add(num);
            else if (map.get(num) > map.get(pb.peek())) {
                pb.remove();
                pb.add(num);
            }
        }
        LinkedList<Integer> res = new LinkedList<>();
        while (!pb.isEmpty()) res.add(pb.remove());
        return res;
    }


}
