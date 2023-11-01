package wpxiao.austar.lottery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        setTitle("机器人协会抽奖平台");
        WebView webView = (WebView) findViewById(R.id.webview);
        Button button = findViewById(R.id.chou);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PreviewActivity.this,MainActivity.class));
            }
        });

        // 配置 WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);  // 启用 JavaScript 支持
        webSettings.setDomStorageEnabled(true);  // 启用 DOM Storage 支持
        //禁止缩放
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setSupportZoom(false);
        // 加载网页
        webView.loadUrl("file:///android_asset/index.html");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, R.id.menu_1, Menu.NONE, "说明");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.menu_1) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                    .setTitle("说明")
                    .setMessage("可根据实际情况修改权重，可联系 AI-彭文晓-计科 修改")
                    .setPositiveButton("确定",null);
            dialog.show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }
}