package SegmentTree;

/**
 * @author zhengrz
 * @date 2018/7/26 10:59
 */
public interface Merger<E> {

    E merge(E a, E b);

}
