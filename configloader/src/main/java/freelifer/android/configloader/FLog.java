package freelifer.android.configloader;

import android.util.Log;

/**
 * @author zhukun on 2019/4/19.
 */
public class FLog {
    private static final String TAG = "f_log";

    public static void i(String format, Object... args) {
        Log.i(TAG, String.format(format, args));
    }

    public static void i(String tag, String format, Object... args) {
        Log.i(tag, String.format(format, args));
    }
    public static void w(String format, Object... args) {
        Log.w(TAG, String.format(format, args));
    }

    public static void e(String format, Object... args) {
        Log.e(TAG, String.format(format, args));
    }
}
