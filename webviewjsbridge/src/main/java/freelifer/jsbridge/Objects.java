package freelifer.jsbridge;

import android.support.annotation.Nullable;

/**
 * @author zhukun on 2019/2/27.
 */
public class Objects {

    public static String requireNonEmpty(String str, String message) {
        if (str == null || str.length() == 0) {
            throw new NullPointerException(message);
        }
        return str;
    }

    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null)
            throw new NullPointerException(message);
        return obj;
    }
}
