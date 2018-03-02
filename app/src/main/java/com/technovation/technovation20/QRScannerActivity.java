package com.technovation.technovation20;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRScannerActivity extends AppCompatActivity {

    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        qrScan=new IntentIntegrator(this);
        qrScan.initiateScan();
        setContentView(R.layout.activity_qrscanner);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null){
            if(result.getContents()==null){
                //Nothing found
            }else{
                Log.e("qrcode",result.getContents());
            }

        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }
}
