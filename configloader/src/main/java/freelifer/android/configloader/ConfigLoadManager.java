package freelifer.android.configloader;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhukun on 2019/4/16.
 */
public class ConfigLoadManager {

    private List<ConfigLoader> list = new ArrayList<>();

    private final static class Holder {
        private final static ConfigLoadManager instance = new ConfigLoadManager();
    }

    public static ConfigLoadManager get() {
        return Holder.instance;
    }

    public void register(ConfigLoader configLoader) {
        list.add(configLoader);
    }



}
