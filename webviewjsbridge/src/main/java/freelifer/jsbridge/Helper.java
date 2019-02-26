package freelifer.jsbridge;

import android.content.Context;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author zhukun on 2019/2/27.
 */
public class Helper {

    public static void loadLocalJsByWebView(WebView view, String path) {
        String jsContent = assetFile2Str(view.getContext(), path);
        view.loadUrl("javascript:" + jsContent);
    }

    public static String assetFile2Str(Context context, String urlStr) {
        InputStream in = null;
        BufferedReader bufferedReader = null;
        try {
            in = context.getAssets().open(urlStr);
            bufferedReader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder sb = new StringBuilder();
            do {
                line = bufferedReader.readLine();
                if (line != null && !line.matches("^\\s*\\/\\/.*")) { // 去除注释
                    sb.append(line);
                }
            } while (line != null);

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(bufferedReader);
            close(in);
        }
        return null;
    }

    public static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }

        try {
            closeable.close();
        } catch (IOException e) {
            // ignore
        }
    }
}
