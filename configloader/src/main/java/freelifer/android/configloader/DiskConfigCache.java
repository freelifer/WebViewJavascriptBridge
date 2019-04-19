package freelifer.android.configloader;

/**
 * @author zhukun on 2019/4/19.
 */
public interface DiskConfigCache<T> {

    boolean contains(String name);

    T get(String name);

    void set(T t);
}
