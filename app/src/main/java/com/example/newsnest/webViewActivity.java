package com.example.newsnest;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class webViewActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private WebView mWebview;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        Intent i = getIntent();
        mWebview = findViewById(R.id.webview);
        String url = i.getStringExtra("url");
        loadNews(i, url);
    }

    private void loadNews(Intent i, String url) {
        mWebview.setWebViewClient(new WebViewClient());
        mWebview.setWebChromeClient(new WebChromeClient() {
            private ProgressDialog progressDialog;

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (progressDialog == null) {
                    progressDialog = new ProgressDialog(webViewActivity.this);
                    progressDialog.show();
                }
                progressDialog.setMessage("Loading news " + String.valueOf(newProgress));

                if (newProgress == 100) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        });

        mWebview.loadUrl(url);

    }
}