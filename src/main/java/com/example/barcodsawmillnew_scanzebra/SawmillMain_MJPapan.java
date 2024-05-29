package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class SawmillMain_MJPapan extends AppCompatActivity {

    public static final String TAG_JSON_ARRAY="result";
    private RecyclerView mRecyclerView;

    private BarangAdapterNOREFMJPapan rvAdapter;

    private ArrayList<BarangMasterUser> barangCabangArrayList;
    private ArrayList<String> names = new ArrayList<String>();

    private RecyclerView.LayoutManager mLayoutManager;

    private Context context = SawmillMain_MJPapan.this;

    private static  final int REQUEST_CODE_ADD =1;

    private static  final int REQUEST_CODE_EDIT =2;

    private List<BarangSawmillMJpapan> barangList = new ArrayList<BarangSawmillMJpapan>();
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;

    private ProgressDialog pDialog;
    private EditText cari1,editoven;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    ImageButton butbarcodeher;
    private static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;
    TextView t1,textTampung,textTampung2,textTampung3,textnoreff;
    Button butpos1,butovenname;
    private SimpleDateFormat dateFormatter;
    Spinner spnOven;
private String wrhtype_get;

    Button butprocwrh;

  //  public static String typeall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maingrader);

        MainActivity.module_buffer="mjpapan";


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent wrhsatu=getIntent();
        final String judul=wrhsatu.getStringExtra("judul");
          wrhtype_get=wrhsatu.getStringExtra("wrhtype");

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MJ Papan");
        setSupportActionBar(toolbar);


        adb_master=new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


        SQLiteDatabase db=adb_master.getReadableDatabase();


        String sqluser = "select * FROM " + ADB_Master.MyColumns_sawmilluser_sub.NamaTabel+" where user_name='"+MainActivity.user_idbuffer+"' and module='"+MainActivity.module_buffer+"'";
        //  String sqluser = "select * FROM " + ADB_Master.MyColumns_sawmilluser_sub.NamaTabel;
        Cursor cuser=db.rawQuery(sqluser,null);
        cuser.moveToFirst();
        int jumuser=cuser.getCount();

        //Toast.makeText(getBaseContext(),"Nilai : "+MainActivity.user_idbuffer+"--"+MainActivity.module_buffer,Toast.LENGTH_LONG).show();

        if (jumuser==0)
        {
            Intent a = new Intent(getBaseContext(), warningumum.class);
            a.putExtra("warning", "Anda tidak berhak untuk buka modul ini");
            startActivity(a);
            finish();
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);


       butprocwrh=findViewById(R.id.butprocwrh);

        butprocwrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getBaseContext(),SawmillSub_MJPapan.class);
                a.putExtra("wrhtype",wrhtype_get);
                startActivityForResult(a,111);
            }
        });

      //  WrhParisOut.typeall="out";
        loadDataLokal();
    }


    private void loadDataLokal(){

     /*   Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        final String namacari=cari1.getText().toString();*/


       /* String item1=cari1.getText().toString();
        String lokoven=textTampung2.getText().toString();
        String namaoven=textTampung3.getText().toString();
        String noref=textnoreff.getText().toString();*/

     //   t1=(TextView) findViewById(R.id.jumbarc2);



        SQLiteDatabase db=adb_master.getReadableDatabase();
       // String sql="select * from tbl_barctran where typetran='"+wrhtype_get+"'";
        //Cursor c=db.rawQuery(sql,null);
       // int jum=c.getCount();
       // t1.setText("Jumlah Barcode : "+String.valueOf(jum));
        //  barangListPOS = adb_user.getSemuaUser();
        barangList = adb_master.getSemuaMJPapan();
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();



    }



    private void gambarDatakeRecyclerView(){

        rvAdapter = new BarangAdapterNOREFMJPapan(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                        Intent intent2=getIntent();
                        final String kodecabang1=intent2.getStringExtra("kodecabang");
                        BarangSawmillMJpapan barang = rvAdapter.getItem(position);
                        Intent intent = new Intent(SawmillMain_MJPapan.this, SawmillSub_MJPapan.class);
                        intent.putExtra("noref",barang.getNoref());
                     //    intent.putExtra("judul",ju);
                        intent.putExtra("barang", barang);
                        //  intent.putExtra("kodecabang", kodecabang1);


                        startActivityForResult(intent, 333);

                    }

                })

        );

    }




    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);




            if (resultCode == RESULT_OK && requestCode == 111) {

                //editoven.setText(data.getStringExtra("section"));
               // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));

                loadDataLokal();


            }else
            {
                loadDataLokal();
            }



    }


}