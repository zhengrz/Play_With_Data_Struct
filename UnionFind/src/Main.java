import java.util.ArrayList;
import java.util.Random;

public class Main {


    private static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(m);
            int b = random.nextInt(m);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i ++) {
            int a = random.nextInt(m);
            int b = random.nextInt(m);
            uf.isConnected(a, b);
        }

        return (System.nanoTime() - startTime) / 1000000000.0;
    }


    public static void main(String[] args) {

        int size = 10000000;
        int m = 10000000;
        ArrayList<UF> ufList = new ArrayList<>();
//        UnionFind1 uf1 = new UnionFind1(size);      // 最慢
//        UnionFind2 uf2 = new UnionFind2(size);      // 快于uf1, 但是当元素在一定情况下, 会退化成uf1, 有点类似链表, 由于jvm底层优化了数组， 所以比链表快
        UnionFind3 uf3 = new UnionFind3(size);      // 最快
        UnionFind4 uf4 = new UnionFind4(size);
        UnionFind5 uf5 = new UnionFind5(size);

//        ufList.add(uf1);
//        ufList.add(uf2);
        ufList.add(uf3);
        ufList.add(uf4);
        ufList.add(uf5);

        ufList.parallelStream().forEach(uf -> System.out.println(uf.getClass().getName() + ": " + testUF(uf, m) + " s"));

    }
}
