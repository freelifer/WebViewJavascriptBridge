package freelifer.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.AttributeSet;
import android.webkit.WebView;

import java.util.HashMap;

/**
 * @author zhukun on 2019/2/27.
 */
public class JsBridgeWebView extends WebView {

    private static final boolean DBG = true;
    private static final String LOG = "JsBridgeWebView";

    private long uniqueId = 0;

    private HashMap<String, Callback> responseCallbacks = new HashMap<>();
    private HashMap<String, JsBridgeHandler> messageHandlers = new HashMap<>();

    public JsBridgeWebView(Context context) {
        super(context);
        init();
    }

    public JsBridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public JsBridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void registerHandler(String handlerName, JsBridgeHandler handler) {
        handlerName = Objects.requireNonEmpty(handlerName, "registerHandler handler name is empty");
        handler = Objects.requireNonNull(handler, "registerHandler JsBridgeHandler is null");
        messageHandlers.put(handlerName, handler);
    }

    public void unregisterHandler(String handlerName) {
        handlerName = Objects.requireNonEmpty(handlerName, "unregisterHandler handler name is empty");
        messageHandlers.remove(handlerName);
    }

    public void callHandler(String handlerName, String data, @Nullable Callback callBack) {
        handlerName = Objects.requireNonEmpty(handlerName, "callHandler handler name is empty");
        doSend(handlerName, data, callBack);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void init() {
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
        this.getSettings().setJavaScriptEnabled(true);
        if (DBG && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        this.setWebViewClient(new JsBridgeWebViewClient(this));
    }

    private void doSend(String handlerName, String data, @Nullable Callback responseCallback) {
        Message m = new Message();
        if (!TextUtils.isEmpty(data)) {
            m.setData(data);
        }
        if (responseCallback != null) {
            String callbackStr = String.format(BridgeUtil.CALLBACK_ID_FORMAT, ++uniqueId + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
            responseCallbacks.put(callbackStr, responseCallback);
            m.setCallbackId(callbackStr);
        }
        if (!TextUtils.isEmpty(handlerName)) {
            m.setHandlerName(handlerName);
        }
        queueMessage(m);
    }

    private void queueMessage(Message m) {
        if (startupMessage != null) {
            startupMessage.add(m);
        } else {
            dispatchMessage(m);
        }
    }
}