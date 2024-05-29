package com.example.barcodsawmillnew_scanzebra;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;


public class TorchOnCaptureActivityLokal extends CaptureActivity {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;
    private boolean cameraFlashOn = false;
    Button turnflashOn,turnflashOff;
    TextView lastscan;
    private static Integer jumtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        barcodeScannerView = initializeContent();
        TorchEventListener torchEventListener = new TorchEventListener(this);
        barcodeScannerView.setTorchListener(torchEventListener);

        turnflashOn = findViewById(R.id.switch_flashlight_on);
        turnflashOff = findViewById(R.id.switch_flashlight_off);

        // turn the flash on if set via intent
        Intent scanIntent = getIntent();
        if(scanIntent.hasExtra(appConstants.CAMERA_FLASH_ON)){
            if(scanIntent.getBooleanExtra(appConstants.CAMERA_FLASH_ON,false)){
                barcodeScannerView.setTorchOn();
                updateView();
            }
        }
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();

        turnflashOn.performClick();
    }

    class TorchEventListener implements DecoratedBarcodeView.TorchListener{
        private TorchOnCaptureActivityLokal activity;

        TorchEventListener(TorchOnCaptureActivityLokal activity){
            this.activity = activity;
        }

        @Override
        public void onTorchOn() {
            this.activity.cameraFlashOn = true;
            this.activity.updateView();
        }

        @Override
        public void onTorchOff() {
            this.activity.cameraFlashOn = false;
            this.activity.updateView();
        }
    }
    public void updateView(){
        if(cameraFlashOn){
            turnflashOn.setVisibility(View.GONE);
            turnflashOff.setVisibility(View.VISIBLE);
        }else{
            turnflashOn.setVisibility(View.VISIBLE);
            turnflashOff.setVisibility(View.GONE);
        }
    }


    public void toggleFlash(View view){
        if(cameraFlashOn){
            barcodeScannerView.setTorchOff();
        }else{
            barcodeScannerView.setTorchOn();
        }
    }

    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.capture_flash);
        //return setContentView(R.layout.test2zing);
        // return (DecoratedBarcodeView)findViewById(R.layout.test2zing);

        lastscan = findViewById(R.id.textLastscan);

 //jumtemp=Integer.valueOf(NURUploadBarcodeBatch.jumjumbar)+1;
        /*
        if (SawmillSub_Checkin.typebarcode.equals("mastercheckin")) {
           // lastscan.setText(SawmillSub_Checkin.lastscan + " Hit : " + SawmillSub_Checkin.jumjumbar);
            lastscan.setText("New Scan");
        }else
        {
            lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("masterlog")) {
            //lastscan.setText(SawmillSub_LOG.lastscan+ " Hit : "+SawmillSub_LOG.jumjumbar);
            lastscan.setText("New Scan");
        }else
        {
            lastscan.setText("New Scan");
        }

        if (WrhParisLokal.typebarcode.equals("master"))
        {
            lastscan.setText("Master Scan");

        }else {

            lastscan.setText(NURUploadBarcodeBatch.lastscan + " Hit: " + NURUploadBarcodeBatch.jumjumbar);

        }
*/

        if (SawmillSub_LOG.typebarcode.equals("masterpapan")) {

                lastscan.setText(SawmillSub_LOG.lastscan+ " Hit : "+NURUploadBarcodeLokal.jumjumbar);
                //lastscan.setText("New Scan");

            //lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("mjpapanmanis")) {
            lastscan.setText(SawmillSub_MJPapan.lastscan+ " Hit : "+SawmillSub_MJPapan.jumjumbar);
            //lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("checklog")) {
            lastscan.setText("Barcode LOG" );
            //lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("masterswout")) {
            lastscan.setText(SawmillSub_SWOut.lastscan+ " Hit : "+SawmillSub_SWOut.jumjumbar);
            //lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("masterfact")) {
            lastscan.setText(SawmillSub_Fact.lastscan+ " Hit : "+SawmillSub_Fact.jumjumbar);
            //lastscan.setText("New Scan");
        }


        if (SawmillSub_LOG.typebarcode.equals("masterwrh")) {
            lastscan.setText(SawmillSub_Wrh.lastscan+ " Hit : "+SawmillSub_Wrh.jumjumbar);
            //lastscan.setText("New Scan");
        }

        if (SawmillSub_LOG.typebarcode.equals("masteroven")) {
            lastscan.setText(SawmillSub_Oven.lastscan+ " Hit : "+SawmillSub_Oven.jumjumbar);
            //lastscan.setText("New Scan");
        }

       if (SawmillSub_LOG.typebarcode.equals("masterlog")) {
            lastscan.setText(SawmillSub_LOG.lastscan+ " Hit : "+SawmillSub_LOG.jumjumbar);
            //lastscan.setText("New Scan");
        }

       // lastscan.setText(SawmillSub_Checkin.typebarcode+SawmillSub_LOG.typebarcode);

       if (SawmillSub_LOG.typebarcode.equals("mastercheckin")) {
           lastscan.setText(SawmillSub_Checkin.lastscan + " Hit : " + SawmillSub_Checkin.jumjumbar);
         // lastscan.setText("New Scan");
       }

            if (lastscan.getText().toString().equals("Last Scan :"))
            {
                lastscan.setText("New Scan");
            }

        return (DecoratedBarcodeView)findViewById(com.google.zxing.client.android.R.id.zxing_barcode_scanner);
    }

    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       capture.onDestroy();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        capture.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }


}
