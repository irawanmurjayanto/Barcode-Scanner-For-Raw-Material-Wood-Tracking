package com.example.barcodsawmillnew_scanzebra;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class listbarcode extends AppCompatActivity {
    private static final String DATABASE_NAME = "master.db";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog pDialog;
    private ADB_Master adb_master;
    SQLiteDatabase mDatabase;
    private List<BarangListBarcode> barangList = new ArrayList<BarangListBarcode>();
    private BarangAdapterListBarcode rvAdapter;
    public static String hasilbarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbarcode);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);

        adb_master = new ADB_Master(getBaseContext());


        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
loadDataLokal();

    }

    private void loadDataLokal() {


     /*   Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        final String namacari=cari1.getText().toString();*/


        //    String item1 = cari1.getText().toString();
        //    String lokoven = textTampung2.getText().toString();
        //    String namaoven = textTampung3.getText().toString();
//        String noref = textnoreff.getText().toString();
//
//        t1 = (TextView) findViewById(R.id.jumbarc2);

//        SQLiteDatabase db = adb_master.getReadableDatabase();
//        String sql = "select max(nourut) as nourut from "+ADB_Master.MyColumns_newbarcode_log.NamaTabel+" where noref= '" + noref + "'";
//        Cursor c = db.rawQuery(sql, null);
//        c.moveToFirst();
////        int jum = c.getCount();
//        int jum = c.getInt(0);
//        hitbarc = jum;
//        jumjumbar = String.valueOf(hitbarc);





        //  barangListPOS = adb_user.getSemuaUser();
        barangList = adb_master.getListBarcode(WrhParisLokal.ValBarcode);
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();

        adb_master.close();

    }

    private void gambarDatakeRecyclerView() {


        rvAdapter = new BarangAdapterListBarcode(barangList);

        mRecyclerView.setAdapter(rvAdapter);






        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(getBaseContext(), new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {
                        BarangListBarcode barang = rvAdapter.getItem(position);
                        hasilbarcode=barang.getBarcode();
                        Intent hasilbalik=new Intent();
                        hasilbalik.putExtra("hasilbarcode",barang.getBarcode());
                        setResult(6767,hasilbalik);
                        pDialog.setMessage("Ambil Data...");
                        showDialog();
                       // Toast.makeText(getBaseContext(),"Barcode"+barang.getBarcode(),Toast.LENGTH_LONG).show();
finish();

//                        Intent intent2 = getIntent();
//                        // final String kodecabang1=intent2.getStringExtra("kodecabang");
//
//                        BarangListBarcode barang = rvAdapter.getItem(position);
//
//
//
//
//                        Intent intent = new Intent(getBaseContext(), BarangActivityTranPOS.class);
//
//
//                        intent.putExtra("barang", barang);
//                        startActivityForResult(intent, 1700);
//


                    }

                })

        );

    }

    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }


    @Override
    public void onBackPressed() {

        Intent balik=new Intent();
        balik.putExtra("ok","ok");
        setResult(8888,balik);

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