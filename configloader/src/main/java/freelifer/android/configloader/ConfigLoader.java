package freelifer.android.configloader;

import static freelifer.android.configloader.Utils.checkNotNull;

/**
 * 加载配置
 * 如果没有本地cache配置，那就异步执行高优先级的拉取配置
 * 如果有本地cache配置，那就按照优先级一个一个拉取配置
 *
 * @author zhukun on 2019/4/16.
 */
public class ConfigLoader {

    private ConfigLoader(Builder builder) {

    }

    public void load() {
        // to go fetch
    }

    public static class Builder {

        private String name;
        private String url;
        private long delay;
        private long interval;

        public Builder setName(String name) {
            this.name = checkNotNull(name, "ConfigLoader.Builder name == null");
            return this;
        }

        public Builder setUrl(String url) {
            this.url = checkNotNull(url, "ConfigLoader.Builder url == null");
            return this;
        }

        public Builder setDelay(long delay) {
            this.delay = delay;
            return this;
        }

        public Builder setInterval(long interval) {
            this.interval = interval;
            return this;
        }

        public ConfigLoader build() {
            return new ConfigLoader(this);
        }
    }

}
