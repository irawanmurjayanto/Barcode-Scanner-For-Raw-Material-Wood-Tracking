package com.example.barcodsawmillnew_scanzebra;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView textuser_id;
    public static  String user_idbuffer,module_buffer;
    Button butlogout;
    ImageButton butGrader,butSawcheckin,butMJPapan;
    ImageButton butimport,butin,butout,butretin,butretout,butactive,butbad,butadj,butinPapan,butReport,butOven,butWrh,butFactory,butOutpapan,butUtillity;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private ADB_Master adb_master;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
     /*    android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);*/

        Intent user=new Intent (getBaseContext(),userpass.class);
        startActivityForResult(user,1234);

        textuser_id=findViewById(R.id.user_id);
        butinPapan=findViewById(R.id.inPapan);
        butOutpapan=findViewById(R.id.outPapan);
        butlogout=findViewById(R.id.butlogout);
//        butReport=findViewById(R.id.butReport);
        butGrader=findViewById(R.id.butGrader);
        butOven=findViewById(R.id.butOven);
        butWrh=findViewById(R.id.butWrh);
        butFactory=findViewById(R.id.butFactory);
        butSawcheckin=findViewById(R.id.sawCheckin);
//        butMJPapan=findViewById(R.id.butMJPapan);
        butUtillity=findViewById(R.id.butUtillity);

//        butMJPapan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent user=new Intent (getBaseContext(),SawmillMain_MJPapan.class);
//                startActivityForResult(user,4599);
//            }
//        });



        butUtillity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user=new Intent (getBaseContext(),Utillity.class);
                startActivityForResult(user,4568);
            }
        });


//        butReport.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent user=new Intent (getBaseContext(),Report.class);
//                startActivityForResult(user,4567);
//            }
//        });



        butlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent user=new Intent (getBaseContext(),userpass.class);
                startActivityForResult(user,1234);
            }
        });



     //   butbad=findViewById(R.id.m12);

        butSawcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saya=new Intent(getBaseContext(),SawmillMain_Checkin.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,117);
            }
        });

        butFactory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saya=new Intent(getBaseContext(),SawmillMain_Fact.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,121);
            }
        });

        butWrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saya=new Intent(getBaseContext(),SawmillMain_Wrh.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,120);
            }
        });

        butOven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saya=new Intent(getBaseContext(),SawmillMain_Oven.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,118);
            }
        });

        butGrader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent saya=new Intent(getBaseContext(),SawmillMain_LOG.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,119);
            }
        });

        butinPapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent saya=new Intent(getBaseContext(),WrhParisLokal.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,117);

            }
        });

        butOutpapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent saya=new Intent(getBaseContext(),SawmillMain_SWOut.class);
                saya.putExtra("wrhtype","OUT");
                saya.putExtra("judul","OUT LOKAL");
                startActivityForResult(saya,117);

            }
        });

    /*    butadj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent saya=new Intent(getBaseContext(),WrhParisOutAdj.class);
                saya.putExtra("wrhtype","ADJ");
                saya.putExtra("judul","Adjustment");
                startActivityForResult(saya,112);

            }
        });
*/

     /*   butretin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent saya=new Intent(getBaseContext(),WrhParis.class);
                saya.putExtra("wrhtype","RIN");
                saya.putExtra("judul","Golden Care Return IN");
                startActivityForResult(saya,111);

            }
        });

*/





    /*    butbad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent saya=new Intent(getBaseContext(),MainStockGC.class);
                saya.putExtra("wrhtype","stockgc");
                saya.putExtra("tipestock","Bad Stock");
                saya.putExtra("judul","Bad Stock Golden Care ");
                startActivityForResult(saya,111);

            }
        });
*/

        verifyStoragePermissions(MainActivity.this);

        adb_master=new ADB_Master(getBaseContext());
isiSawmill();
//        if (isConnected()) {
//           importdatauser();
//        }

    }


    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;
        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }


    private void isiSawmill(){

        SQLiteDatabase create = adb_master.getWritableDatabase();

        SQLiteDatabase db=adb_master.getReadableDatabase();

        String sql = "select * FROM " + ADB_Master.MyColumns_sawmilluser.NamaTabel;
        Cursor c=db.rawQuery(sql,null);
        //c.moveToFirst();
        int jumuser=c.getCount();

         if (jumuser==0) {
            String deluser = "delete  FROM " + ADB_Master.MyColumns_sawmilluser.NamaTabel;
            create.execSQL(deluser);
            //tiep_kayu
            ContentValues valuesuser = new ContentValues();
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "irm");
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1");
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "admin");
            create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "charlie");
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "6789");
            valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "admin");
            create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "mj1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "mj2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "4567");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "bj1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "bj2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "4567");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "oven1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "oven2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "oven3");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "oven4");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "wrh1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "wrh2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "wrh3");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);


             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "grader1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "grader1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "fact1");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "fact2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "1234");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);

             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_user, "grader2");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_pass, "grader4567");
             valuesuser.put(ADB_Master.MyColumns_sawmilluser.nama_ket, "user");
             create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, valuesuser);
        }




        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String sqlsubuser = "select * FROM " + ADB_Master.MyColumns_sawmilluser_sub.NamaTabel;
        Cursor csubuser=db.rawQuery(sqlsubuser,null);
        //c.moveToFirst();
        int jumsubuser=csubuser.getCount();

        if (jumsubuser==0) {
            String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmilluser_sub.NamaTabel;
            createdel.execSQL(del1);

            //modif acs
            ContentValues usersub = new ContentValues();
            usersub.put(ADB_Master.MyColumns_sawmilluser_sub.user_name, "irm");
            usersub.put(ADB_Master.MyColumns_sawmilluser_sub.module, "utillity");
            create.insert(ADB_Master.MyColumns_sawmilluser_sub.NamaTabel, null, usersub);

        }


     /*   String delstipekayu = "delete  FROM " + ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel;
        create.execSQL(delstipekayu);
        //tiep_kayu
        ContentValues valuestpkayu = new ContentValues();
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_tipekayu.tipe_kayu, "Tipe Kayu");
        create.insert(ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel, null, valuestpkayu);
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_tipekayu.tipe_kayu, "BULAT");
        create.insert(ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel, null, valuestpkayu);
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_tipekayu.tipe_kayu, "KOTAK");
        create.insert(ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel, null, valuestpkayu);
*/


        String sqlsw = "select * FROM " + ADB_Master.MyColumns_sawmill_swgroup.NamaTabel;
        Cursor csw=db.rawQuery(sqlsw,null);
        //c.moveToFirst();
        int hitsw=csw.getCount();

        if (hitsw==0) {

            String delsw = "delete  FROM " + ADB_Master.MyColumns_sawmill_swgroup.NamaTabel;
            create.execSQL(delsw);
            //Cursor del2 = create.rawQuery(del1, null);
            //isisawmill group lokasi utama
            ContentValues valueslok = new ContentValues();
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "Group");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "'");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "A");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "Sawmill Bangunjiwo Atas");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "B");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "Sawmill Bangunjiwo Barat");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "C");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "Sawmill Bangunjiwo Timur");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "M");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "Sawmill Murah Jati");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi, "P");
            valueslok.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi, "Sawmill Murah Jati");
            create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, valueslok);
        }




        String sqlsl = "select * FROM " + ADB_Master.MyColumns_sawmill_shortlong.NamaTabel;
        Cursor csl=db.rawQuery(sqlsl,null);
        //c.moveToFirst();
        int hitsl=csl.getCount();

        if (hitsl==0) {

            String delsl = "delete  FROM " + ADB_Master.MyColumns_sawmill_shortlong.NamaTabel;
            create.execSQL(delsl);
            //isisawmill S/L
            ContentValues valuessl = new ContentValues();
            valuessl.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl, "Tipe");
            create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, valuessl);
            valuessl.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl, "CH");
            create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, valuessl);
            valuessl.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl, "SL");
            create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, valuessl);
            valuessl.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl, "Short");
            create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, valuessl);
            valuessl.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl, "Long");
            create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, valuessl);
        }

    /*    String dellok = "delete  FROM " + ADB_Master.MyColumns_sawmilllokasi.NamaTabel;
        create.execSQL(dellok);
        //isisawmill lokasi
        ContentValues values = new ContentValues();
        values.put(ADB_Master.MyColumns_sawmilllokasi.nama_lokasi, "Murah_Jati");
        values.put(ADB_Master.MyColumns_sawmilllokasi.alamat_lokasi, "Imogiri Barat");
        create.insert(ADB_Master.MyColumns_sawmilllokasi.NamaTabel, null, values);
        values.put(ADB_Master.MyColumns_sawmilllokasi.nama_lokasi, "RA_Jati");
        values.put(ADB_Master.MyColumns_sawmilllokasi.alamat_lokasi, "Sewon Bantul");
        create.insert(ADB_Master.MyColumns_sawmilllokasi.NamaTabel, null, values);
        //create.close();
*/
//oven

        String sqloven = "select * FROM " + ADB_Master.MyColumns_sawmill_oven.NamaTabel;
        Cursor coven=db.rawQuery(sqloven,null);
        //c.moveToFirst();
        int hitoven=coven.getCount();

        if (hitoven==0) {


            String deloven = "delete  FROM " + ADB_Master.MyColumns_sawmill_oven.NamaTabel;
            create.execSQL(deloven);
            //isisawmill lokasi
            ContentValues values_oven = new ContentValues();
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.nama_lokasi, "Lokasi");
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.alamat_lokasi, "lokasi");
            create.insert(ADB_Master.MyColumns_sawmill_oven.NamaTabel, null, values_oven);
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.nama_lokasi, "Murah_Jati");
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.alamat_lokasi, "Imogiri Barat");
            create.insert(ADB_Master.MyColumns_sawmill_oven.NamaTabel, null, values_oven);
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.nama_lokasi, "Bangunjiwo");
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.alamat_lokasi, "Sewon Bantul");
            create.insert(ADB_Master.MyColumns_sawmill_oven.NamaTabel, null, values_oven);
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.nama_lokasi, "Paradise");
            values_oven.put(ADB_Master.MyColumns_sawmill_oven.alamat_lokasi, "Sedayu Bantul");
            create.insert(ADB_Master.MyColumns_sawmill_oven.NamaTabel, null, values_oven);
        }


        String sqlwrh = "select * FROM " + ADB_Master.MyColumns_sawmill_wrh.NamaTabel;
        Cursor cwrh=db.rawQuery(sqlwrh,null);
        //c.moveToFirst();
        int hitwrh=cwrh.getCount();

        if (hitwrh==0) {
//warehouse
            String delwrh = "delete  FROM " + ADB_Master.MyColumns_sawmill_wrh.NamaTabel;
            create.execSQL(delwrh);
            //isisawmill lokasi
            ContentValues values_wrh = new ContentValues();
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.nama_lokasi, "Lokasi");
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.alamat_lokasi, "lokasi");
            create.insert(ADB_Master.MyColumns_sawmill_wrh.NamaTabel, null, values_wrh);
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.nama_lokasi, "Parangtritis");
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.alamat_lokasi, "parangtritis");
            create.insert(ADB_Master.MyColumns_sawmill_wrh.NamaTabel, null, values_wrh);
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.nama_lokasi, "Gudang_Baru");
            values_wrh.put(ADB_Master.MyColumns_sawmill_wrh.alamat_lokasi, "Sedayu Bantul");
            create.insert(ADB_Master.MyColumns_sawmill_wrh.NamaTabel, null, values_wrh);
        }


        String sqlfac = "select * FROM " + ADB_Master.MyColumns_sawmill_factory.NamaTabel;
        Cursor cfac=db.rawQuery(sqlfac,null);
        //c.moveToFirst();
        int hitfac=cfac.getCount();

        if (hitfac==0) {


//warehouse
            String delfact = "delete  FROM " + ADB_Master.MyColumns_sawmill_factory.NamaTabel;
            create.execSQL(delfact);
            //isisawmill lokasi
            ContentValues values_factory = new ContentValues();
            values_factory.put(ADB_Master.MyColumns_sawmill_factory.nama_lokasi, "Lokasi");
            values_factory.put(ADB_Master.MyColumns_sawmill_factory.alamat_lokasi, "lokasi");
            create.insert(ADB_Master.MyColumns_sawmill_factory.NamaTabel, null, values_factory);
            values_factory.put(ADB_Master.MyColumns_sawmill_factory.nama_lokasi, "Factory");
            values_factory.put(ADB_Master.MyColumns_sawmill_factory.alamat_lokasi, "Sedayu");
            create.insert(ADB_Master.MyColumns_sawmill_factory.NamaTabel, null, values_factory);

        }


        String sqltebal = "select * FROM " + ADB_Master.MyColumns_sawmilltebal.NamaTabel;
        Cursor ctebal=db.rawQuery(sqltebal,null);
        //c.moveToFirst();
        int hittebal=ctebal.getCount();

        if (hittebal==0) {


            //isisawmill Tebal Kayu

            String deltebal = "delete  FROM " + ADB_Master.MyColumns_sawmilltebal.NamaTabel;
            create.execSQL(deltebal);

            ContentValues values2 = new ContentValues();
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, "Tebal");
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 1.8);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 2.5);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 2.9);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 3.3);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 4.2);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            values2.put(ADB_Master.MyColumns_sawmilltebal.tebal, 4.6);
            create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values2);
            //isisawmill SGD

        }


        String sqlsgd = "select * FROM " + ADB_Master.MyColumns_sawmillsgd.NamaTabel;
        Cursor csgd=db.rawQuery(sqlsgd,null);
        //c.moveToFirst();
        int hitsgd=csgd.getCount();

        if (hitsgd==0) {


            String delsgd = "delete  FROM " + ADB_Master.MyColumns_sawmillsgd.NamaTabel;
            create.execSQL(delsgd);

            ContentValues values3 = new ContentValues();
            values3.put(ADB_Master.MyColumns_sawmillsgd.nama_sgd, "Grade");
            create.insert(ADB_Master.MyColumns_sawmillsgd.NamaTabel, null, values3);
            values3.put(ADB_Master.MyColumns_sawmillsgd.nama_sgd, "S");
            create.insert(ADB_Master.MyColumns_sawmillsgd.NamaTabel, null, values3);
            values3.put(ADB_Master.MyColumns_sawmillsgd.nama_sgd, "G");
            create.insert(ADB_Master.MyColumns_sawmillsgd.NamaTabel, null, values3);
            values3.put(ADB_Master.MyColumns_sawmillsgd.nama_sgd, "D");
            create.insert(ADB_Master.MyColumns_sawmillsgd.NamaTabel, null, values3);
        }


        //isisawmill temp
      String delstemp = "delete  FROM " + ADB_Master.MyColumns_sawmilltemp.NamaTabel;
       create.execSQL(delstemp);
        ContentValues values4 = new ContentValues();
        values4.put(ADB_Master.MyColumns_sawmilltemp.nama_user,"irawan");
        values4.put(ADB_Master.MyColumns_sawmilltemp.nama_user_short,"irm");
        values4.put(ADB_Master.MyColumns_sawmilltemp.nama_lokasi,"Ra_Jati");
        values4.put(ADB_Master.MyColumns_sawmilltemp.nama_lainnya,"lainnya");
        create.insert(ADB_Master.MyColumns_sawmilltemp.NamaTabel, null, values4);




        create.close();



        SQLiteDatabase db2 = adb_master.getReadableDatabase();

        //  String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel+ " WHERE "
        //      + ADB_Master.MyColumns_item.kode + " = '"+item_code+"'";
        //  String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_sawmilluser.NamaTabel;
        Cursor c2 = db2.rawQuery(selectQuery2, null);
        int idno4 = c2.getCount();
        c2.moveToFirst();
     //   Toast.makeText(this, "Data : "+c2.getString(1)+"--"+c2.getString(2)+" jum"+idno4, Toast.LENGTH_SHORT).show();
      //  Toast.makeText(this, "Jumlah User "+idno4+"--"+c2.getString(1), Toast.LENGTH_SHORT).show();

    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE




            );
        }
    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_close) {

            finish();

            return true;



        }else  if (id == R.id.action_tentang) {
            // Toast.makeText(getBaseContext(),"buka",Toast.LENGTH_SHORT).show();
             Intent saya=new Intent(this,tentang.class);
            startActivity(saya);
            return true;

        }else  if (id == R.id.stockgc) {
            Intent saya=new Intent(this,MainStockGC.class);
            saya.putExtra("wrhtype","stockgc");
            saya.putExtra("tipestock","Normal");
            saya.putExtra("judul","Golden Care Inventory ");
            startActivityForResult(saya,111);
            return true;

        }else  if (id == R.id.stockgcbad) {
            Intent saya=new Intent(this,MainStockGC.class);
            saya.putExtra("wrhtype","stockgc");
            saya.putExtra("tipestock","Bad Stock");
            saya.putExtra("judul","Bad Stock Golden Care ");
            startActivityForResult(saya,111);
            return true;

        }else  if (id == R.id.gc_import) {
            //Toast.makeText(getBaseContext(),"buka",Toast.LENGTH_SHORT).show();
            Intent saya=new Intent(this,WrhParis.class);
           saya.putExtra("wrhtype","import");
           saya.putExtra("judul","Golden Care Import");
           startActivityForResult(saya,111);
            return true;
        }else  if (id == R.id.gc_IN) {
            Intent saya=new Intent(this,WrhParis.class);
            saya.putExtra("wrhtype","IN");
            saya.putExtra("judul","Golden Care IN ");
            startActivityForResult(saya,111);
            return true;

        }else  if (id == R.id.gc_out) {
            Intent saya=new Intent(this,WrhParisOut.class);
            saya.putExtra("wrhtype","OUT");
            saya.putExtra("judul","Golden Care OUT New");
            startActivityForResult(saya,111);
            return true;

        }else  if (id == R.id.rt_in) {
            Intent saya=new Intent(this,WrhParis.class);
            saya.putExtra("wrhtype","RIN");
            saya.putExtra("judul","Golden Care Return IN");
            startActivityForResult(saya,111);
            return true;

        }else  if (id == R.id.rt_out) {
            Intent saya=new Intent(this,WrhParis.class);
            saya.putExtra("wrhtype","ROUT");
            saya.putExtra("judul","Golden Care Return OUT");
            startActivityForResult(saya,111);
            return true;

        }

        return super.onOptionsItemSelected(item);

    }

    @Override

    protected void onActivityResult ( int requestCode, int resultCode, Intent data){

  //      IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);




        if (resultCode == RESULT_OK && requestCode == 1234) {

//editoven.setText(data.getStringExtra("section"));
            //   textTampung3.setText(String.valueOf(data.getStringExtra("idno")));

            //   loadDataLokal();

 textuser_id.setText("( "+data.getStringExtra("user_id")+" )");
 user_idbuffer=data.getStringExtra("user_id");
//user_idbuffer=data.getStringExtra("user_id");
        }


    }



    @Override
    public void onBackPressed() {
      /*  DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }*/
     /*   DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
    }




    private void importdatauser_sub() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmilluser_sub.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_user_sub.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

//        pDialog.setMessage("Retrieve Data...");
//        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_sub(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
//                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

//                        hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                // params.put("nopos",notransaksi);
                //   params.put("typetran",statustran.getText().toString());

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        //  Toast.makeText(this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();
    }


    private void processResponsePOS_sub(String response){

        try {

            Log.d("TAG", "data response DATA POS: "+response);

            JSONObject jsonObj = new JSONObject(response);

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            Log.d("TAG", "data length: " + jsonArray.length());

            BarangCabang objectbarang = null;

            //  barangListPOS.clear();

            //NumberFormat numberFormat  = new DecimalFormat("##");

            // DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            //  totalrp.setText(formatter.format(obj.getDouble("total")));

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                /*
                objectbarang= new BarangCabang();
                objectbarang.setBarcode(obj.getString("barcode"));
                objectbarang.setItem_code(obj.getString("item_code"));
                objectbarang.setItem_desc(obj.getString("item_desc"));
                objectbarang.setKondisi(obj.getString("kondisi"));
                objectbarang.setRemarks(obj.getString("remarks"));
                objectbarang.setNoref(obj.getString("noref"));
                objectbarang.setTypetran(obj.getString("typetran"));
                objectbarang.setQtygc(obj.getDouble("qty"));
                objectbarang.setUser_name(obj.getString("user_name"));
                objectbarang.setIdno(obj.getString("idno"));
                */

                SQLiteDatabase create = adb_master.getWritableDatabase();

                // String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
                //   create.execSQL(del1);
                //Cursor del2 = create.rawQuery(del1, null);
                //isisawmill lokasi
                ContentValues values = new ContentValues();
                values.put(ADB_Master.MyColumns_sawmilluser_sub.user_name,obj.getString("user_name"));
                values.put(ADB_Master.MyColumns_sawmilluser_sub.module,obj.getString("module"));
                create.insert(ADB_Master.MyColumns_sawmilluser_sub.NamaTabel, null, values);

                //Toast.makeText(this,"Import Completed ",Toast.LENGTH_LONG).show();



               /* objectbarang.setIDNO(obj.getInt("idno"));
                objectbarang.setProduct(obj.getString("item_desc"));
                objectbarang.setHarga_jual(obj.getDouble("harga_jual"));
                objectbarang.setQty(obj.getDouble("qty"));
                objectbarang.setDiscount(obj.getDouble("disc_val"));
                objectbarang.setSubtot(obj.getDouble("subtot"));
                objectbarang.setQtyret(obj.getInt("qtyreturn"));*/
                //       barangListPOS.add(objectbarang);


            }
//pDialog.hide();
        } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");

        }
        //   loadDataLokal("x");
//        Intent a = new Intent(getBaseContext(), warningumum.class);
//        a.putExtra("warning", "Import Data User Selesai");
//        startActivity(a);
        Toast.makeText(getBaseContext(),"Initializing Completed,Anda Bisa Login",Toast.LENGTH_LONG).show();
    }



    public void importdatauser() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmilluser.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_user.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

//        pDialog.setMessage("Retrieve Data...");
//        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
//                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

//                        hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                // params.put("nopos",notransaksi);
                //   params.put("typetran",statustran.getText().toString());

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        //  Toast.makeText(this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();
    }


    private void processResponsePOS(String response){

        try {

            Log.d("TAG", "data response DATA POS: "+response);

            JSONObject jsonObj = new JSONObject(response);

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            Log.d("TAG", "data length: " + jsonArray.length());

            BarangCabang objectbarang = null;

            //  barangListPOS.clear();

            //NumberFormat numberFormat  = new DecimalFormat("##");

            // DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            //  totalrp.setText(formatter.format(obj.getDouble("total")));

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                /*
                objectbarang= new BarangCabang();
                objectbarang.setBarcode(obj.getString("barcode"));
                objectbarang.setItem_code(obj.getString("item_code"));
                objectbarang.setItem_desc(obj.getString("item_desc"));
                objectbarang.setKondisi(obj.getString("kondisi"));
                objectbarang.setRemarks(obj.getString("remarks"));
                objectbarang.setNoref(obj.getString("noref"));
                objectbarang.setTypetran(obj.getString("typetran"));
                objectbarang.setQtygc(obj.getDouble("qty"));
                objectbarang.setUser_name(obj.getString("user_name"));
                objectbarang.setIdno(obj.getString("idno"));
                */

                SQLiteDatabase create = adb_master.getWritableDatabase();

                // String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
                //   create.execSQL(del1);
                //Cursor del2 = create.rawQuery(del1, null);
                //isisawmill lokasi
                ContentValues values = new ContentValues();
                values.put(ADB_Master.MyColumns_sawmilluser.nama_user,obj.getString("user_id"));
                values.put(ADB_Master.MyColumns_sawmilluser.nama_pass,obj.getString("pass_id"));
                values.put(ADB_Master.MyColumns_sawmilluser.nama_ket,obj.getString("grade"));
                create.insert(ADB_Master.MyColumns_sawmilluser.NamaTabel, null, values);

                //Toast.makeText(this,"Import Completed ",Toast.LENGTH_LONG).show();



               /* objectbarang.setIDNO(obj.getInt("idno"));
                objectbarang.setProduct(obj.getString("item_desc"));
                objectbarang.setHarga_jual(obj.getDouble("harga_jual"));
                objectbarang.setQty(obj.getDouble("qty"));
                objectbarang.setDiscount(obj.getDouble("disc_val"));
                objectbarang.setSubtot(obj.getDouble("subtot"));
                objectbarang.setQtyret(obj.getInt("qtyreturn"));*/
                //       barangListPOS.add(objectbarang);


            }
//pDialog.hide();
        } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");

        }
        importdatauser_sub();
        //   loadDataLokal("x");

    }


//    private void showDialog() {
//
//        if (!pDialog.isShowing())
//
//            pDialog.show();
//
//    }
//
//    private void hideDialog() {
//
//        if (pDialog.isShowing())
//
//            pDialog.dismiss();
//
//    }

}