package freelifer.android.configloader;

import android.support.annotation.Nullable;

/**
 * @author zhukun on 2019/4/16.
 */
public class Utils {
    static <T> T checkNotNull(@Nullable T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
