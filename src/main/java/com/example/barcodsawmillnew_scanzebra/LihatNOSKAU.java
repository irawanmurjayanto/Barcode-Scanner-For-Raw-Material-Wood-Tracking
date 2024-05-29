package com.example.barcodsawmillnew_scanzebra;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LihatNOSKAU extends Activity {
    private RecyclerView  mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog pDialog;
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;
    private List<BarangListNoGroup> barangList=new ArrayList<BarangListNoGroup>();
    private BarangAdapterListNoGroup rvAdapter;
    public static String nogroupbalik;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_n_o_s_k_a_u);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_barcode);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        pDialog = new ProgressDialog(this);


        //set us to non-modal, so that others can receive the outside touch events.
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL, WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        //and watch for outside touch events too
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH, WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH);


        adb_master = new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());
        loadDataLokal();
    }




    private void gambarDatakeRecyclerView() {


        rvAdapter = new BarangAdapterListNoGroup(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getBaseContext(), new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                        Intent intent2 = getIntent();
                        // final String kodecabang1=intent2.getStringExtra("kodecabang");

                        BarangListNoGroup barang = rvAdapter.getItem(position);
                        nogroupbalik=barang.getNogroup();

                        Intent hasilskau=new Intent();
                        hasilskau.putExtra("hasilbalikskau",barang.getNogroup());
                        setResult(9898,hasilskau);

                        finish();


                        // Toast.makeText(getBaseContext(), "Hasil Del "+barang.getBarcode()+"--"+barang.getId(), Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(NURUploadBarcode.this, BarangActivity.class);

                        // intent.putExtra("barang", barang);


                        //  startActivityForResult(intent, REQUEST_CODE_EDIT);
                     //   DialogDelBarcTran(barang.getBarcode(), barang.getId());


                    }

                })

        );

    }

    private void loadDataLokal() {


        //  barangListPOS = adb_user.getSemuaUser();
        barangList = adb_master.getListNoGroup();
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();


    }


    @Override
    public void onBackPressed() {
        Intent balik=new Intent();
        balik.putExtra("balikdulu","balikdulu");
        setResult(9999,balik);

       finish();
//        if (doubleBackToExitPressedOnce) {
//            super.onBackPressed();
//            return;
//        }
//
//        this.doubleBackToExitPressedOnce = true;
//        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
//
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                doubleBackToExitPressedOnce=false;
//            }
//        }, 2000);
    }


}