package wpxiao.austar.lottery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


class DialogHelper{
    private final Context mContext;

    public DialogHelper(Context context) {
        mContext = context;
    }

    @JavascriptInterface
    public void showDialog(String message,String imageName) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setPositiveButton("确定",null);
        builder.setTitle(message);
        builder.show();
    }
}

public class MainActivity extends AppCompatActivity {

    private WebView webView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("抽奖");
        webView = (WebView) findViewById(R.id.webview);

        // 配置 WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript 支持
        webSettings.setDomStorageEnabled(true);  // 启用 DOM Storage 支持
        //禁止缩放
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        // 加载网页
        webView.loadUrl("file:///android_asset/lottery.html");
        webView.addJavascriptInterface(new DialogHelper(this),"DialogHelper");

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 销毁 WebView
        if (webView != null) {
            webView.stopLoading();
            webView.destroy();
        }
    }
}