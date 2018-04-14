package com.technovation.technovation20;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScannerActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;
    Toolbar toolbar;
    WebView webView;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrScan=new IntentIntegrator(this);
        qrScan.initiateScan();
        setContentView(R.layout.activity_qrscanner);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        setSupportActionBar(toolbar);

        webView=(WebView)findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(getApplicationContext(),"Error unable to load",Toast.LENGTH_SHORT).show();
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if(newProgress == 100){
                    // Hide the progressbar
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.canGoBack();
        webView.loadUrl("http://www.technovation2k18.com/");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                //Nothing found
            }else{
                Log.e("qrcode",result.getContents());
                webView.loadUrl(result.getContents());
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.qrscannermenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.scanQrMenu){
            qrScan=new IntentIntegrator(this);
            qrScan.initiateScan();
        }
        return super.onOptionsItemSelected(item);
    }
}
