package freelifer.jsbridge;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.NonNull;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static freelifer.jsbridge.Constants.LOCAL_JAVASCRIPT_BRIDGE;

/**
 * @author zhukun on 2019/2/27.
 */
public class JsBridgeWebViewClient extends WebViewClient {

    @NonNull
    private JsBridgeWebView webView;

    public JsBridgeWebViewClient(@NonNull JsBridgeWebView webView) {
        this.webView = webView;
    }

    @Override
    public final boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
            webView.handlerReturnData(url);
            return true;
        } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
            webView.flushMessageQueue();
            return true;
        } else {
            return this.onCustomShouldOverrideUrlLoading(url) || super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public final boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            String url = request.getUrl().toString();
            try {
                url = URLDecoder.decode(url, "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
            }
            if (url.startsWith(BridgeUtil.YY_RETURN_DATA)) { // 如果是返回数据
                webView.handlerReturnData(url);
                return true;
            } else if (url.startsWith(BridgeUtil.YY_OVERRIDE_SCHEMA)) { //
                webView.flushMessageQueue();
                return true;
            } else {
                return this.onCustomShouldOverrideUrlLoading(url) || super.shouldOverrideUrlLoading(view, request);
            }
        } else {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);

        Helper.loadLocalJsByWebView(view, LOCAL_JAVASCRIPT_BRIDGE);

        //
//        if (webView.getStartupMessage() != null) {
//            for (Message m : webView.getStartupMessage()) {
//                webView.dispatchMessage(m);
//            }
//            webView.setStartupMessage(null);
//        }

        //
        onCustomPageFinishd(view,url);

    }


    protected boolean onCustomShouldOverrideUrlLoading(String url) {
        return false;
    }


    protected void onCustomPageFinishd(WebView view, String url){

    }

}
