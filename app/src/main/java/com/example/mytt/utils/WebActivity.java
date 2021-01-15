package com.example.mytt.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mytt.R;

public class WebActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView tv_web_title;
    private WebView web_view;
    private String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*//去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams. FLAG_FULLSCREEN , WindowManager.LayoutParams. FLAG_FULLSCREEN);
*/
        setContentView(R.layout.activity_web);
        init();
        initData();
    }
    private void init(){
        back=(ImageView)findViewById(R.id.back);
        tv_web_title=(TextView)findViewById(R.id.tv_web_title);
        web_view=(WebView)findViewById(R.id.web_view);
        back.setOnClickListener(this);
    }
    private void initData(){
        Intent intent=getIntent();
        url=intent.getStringExtra("key_url");

        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.setWebViewClient(new WebViewClient());
        web_view.loadUrl(url);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }
}
