package freelifer.jsbridge.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import freelifer.jsbridge.Callback;
import freelifer.jsbridge.JsBridgeHandler;
import freelifer.jsbridge.JsBridgeWebView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    JsBridgeWebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);

        webView.loadUrl("file:///android_asset/demo.html");

        webView.registerHandler("submitFromWeb", new JsBridgeHandler() {

            @Override
            public void handler(String data, Callback callback) {
                Log.i(TAG, "handler = submitFromWeb, data from web = " + data);
                callback.onCallback("submitFromWeb exe, response data 中文 from Java");
            }

        });
    }


    public void callJs(View view) {
        webView.callHandler("functionInJs", "data from Java", new Callback() {

            @Override
            public void onCallback(String data) {
                Log.i(TAG, "reponse data from js " + data);
            }

        });
    }
}
