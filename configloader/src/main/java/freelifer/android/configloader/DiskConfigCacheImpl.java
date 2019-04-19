package freelifer.android.configloader;

/**
 * @author zhukun on 2019/4/19.
 */
public class DiskConfigCacheImpl implements DiskConfigCache {
    @Override
    public boolean contains(String name) {
        return false;
    }

    @Override
    public Object get(String name) {
        return null;
    }

    @Override
    public void set(Object o) {

    }
}
