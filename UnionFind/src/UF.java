/**
 * @author zhengrz
 * @date 2018/7/28 17:37
 */
public interface UF {

    int getSize();
    boolean isConnected(int p, int q);
    void unionElements(int p, int q);

}
