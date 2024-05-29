package com.example.barcodsawmillnew_scanzebra;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Utillity extends AppCompatActivity {

    private ActionBar toolbar;

    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter,dateFormatter2,dateFormatterdoc;
    private TextView tvDateResult1,tvDateResult2,hasil1,hasil2;
    private ImageButton btDatePicker1,btDatePicker2;
    private ProgressDialog pDialog;
    private WebView myWebView;
    private Button btsubmit;
    private RadioGroup radiogrp;
    private static  final int REQUEST_CODE_ADD =1;
    private static  final int REQUEST_CODE_EDIT =2;
    public static String webhasil1,webhasil2,kodecabang,groupho,tgl1,tgl2,sw_group;
    private static String nodoc;
    Spinner spnSwgroup;
    TextView textTempSwgroup,textTempSwgroup2,textHasilswgroup;
    List<String> users=new ArrayList<>();
    ArrayAdapter<String> adapter;
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;
    SQLiteDatabase mDatabase;
    private static final String DATABASE_NAME = "master.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_utillty);
        MainActivity.module_buffer="utillity";
        // toolbar.setTitle("Accounting Report");
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);
        pDialog = new ProgressDialog(Utillity.this);


        adb_master = new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        dateFormatter2 = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        tvDateResult1 = (TextView) findViewById(R.id.tv_dateresult1);
        tvDateResult2 = (TextView) findViewById(R.id.tv_dateresult2);
        hasil1 = (TextView) findViewById(R.id.textHasil1);
        hasil2 = (TextView) findViewById(R.id.textHasil2);
     //   btDatePicker1 = (ImageButton) findViewById(R.id.bt_datepicker1);
    //    btDatePicker2 = (ImageButton) findViewById(R.id.bt_datepicker2);
        btsubmit = (Button) findViewById(R.id.bt_submit);
        radiogrp = (RadioGroup) findViewById(R.id.acc_rpt);
     //   spnSwgroup=findViewById(R.id.spnSwgroup);
        textTempSwgroup = (TextView) findViewById(R.id.textTempSwgroup);
        textTempSwgroup2 = (TextView) findViewById(R.id.textTempSwgroup2);
        textHasilswgroup=findViewById(R.id.textHasilswgroup);



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




        Calendar newDate = Calendar.getInstance();

        dateFormatterdoc = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.US);
        Date currentTime = Calendar.getInstance().getTime();
        nodoc=MainActivity.user_idbuffer+dateFormatterdoc.format(newDate.getTime());


        //  newDate.set(year, monthOfYear, dayOfMonth);


     //   tvDateResult1.setText(dateFormatter.format(newDate.getTime()));
       // hasil1.setText(dateFormatter2.format(newDate.getTime()));



        //   Calendar newDate = Calendar.getInstance();
        // newDate.set(year, monthOfYear, dayOfMonth);


        //tvDateResult2.setText(dateFormatter.format(newDate.getTime()));
        //hasil2.setText(dateFormatter2.format(newDate.getTime()));





        btsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final int a1=hasil1.length();
                final int a2=hasil2.length();
                tgl1=hasil1.getText().toString();
                tgl2=hasil2.getText().toString();

                //Toast.makeText(getBaseContext(),"Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show();
              //  Intent aa= new Intent(getBaseContext(),printSawmillGroup.class);
               // startActivity(aa);

                int Id = radiogrp.getCheckedRadioButtonId();
                switch(Id){

                    case R.id.butImportVendor:
                        importdatavendor();
                        break;
                    case R.id.butImportuser:
                     importdatauser();
                        break;
                    case R.id.butImportWrh:
                        importdata_wrh();
                        break;
                    case R.id.butImportOven:
                        importdata_oven();
                        break;
                    case R.id.butImportSawmill:
                        importdata_sawmill();
                        break;
                    case R.id.butImportFactory:
                        importdata_factory();
                        break;
                    case R.id.butImportTebalKayu:
                        importdata_tebal();
                        break;
                    case R.id.butImportTipeKayu:
                        importdata_tipe();
                        break;
                    case R.id.butClearimportlog:
                        //importdata_tipe();
                        cleardatalogtrans();
                        break;
                    case R.id.butClearalltrans:

                         cleardataAlltrans();
                        break;
                    case R.id.butUsername:
                        Intent a=new Intent(getBaseContext(),userpass_edit.class);
                        startActivity(a);
                        break;
                    case R.id.butListPutri:
                        Intent ax=new Intent(getBaseContext(),LihatBarcodeTia.class);
                        startActivity(ax);
                        break;
                    case R.id.butListdelgroup:
                        Intent ax2=new Intent(getBaseContext(),LihatBarcodeTia_deletegroup.class);
                        startActivity(ax2);
                        break;
                    case R.id.butAbout:
                        Intent aa=new Intent(getBaseContext(),About.class);
                        startActivity(aa);
                        break;
                   /* case R.id.historybarcode:
                        if (a1==0||a2==0)
                        {
                            Toast.makeText(getBaseContext(),"Tanggal tidak boleh kosong", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent a3= new Intent(getBaseContext(),HistoryBarcode.class);
                        startActivity(a3);
                        break;*/

                }

            }
        });



    }




    private void importdatavendor() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del2 = "delete  FROM " + ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel;
        createdel.execSQL(del2);


        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_vendor.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_tipevd(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_tipevd(String response){

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

                ContentValues usersubsp = new ContentValues();
                usersubsp.put(ADB_Master.MyColumns_sawmill_basesupplier.supplier,obj.getString("supplier_name"));
                create.insert(ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel, null, usersubsp);

                // final String notransaksi= notrans.getText().toString();

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Supplier");
        startActivity(a);
    }


    private void cleardataAlltrans() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        //sList No Group
        String del1x = "delete  FROM " + ADB_Master.MyColumns_tokoprofile.NamaTabel;
        createdel.execSQL(del1x);

        //sawmil papan
        String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
        createdel.execSQL(del1);

        String del2 = "delete  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel;
        createdel.execSQL(del2);
        String url ="";

        //saw check ini
        String delcheckin = "delete  FROM " +  ADB_Master.MyColumns_newbarcode_checkin.NamaTabel;
        createdel.execSQL(delcheckin);

        //sawmill factory
        String delcheckfact = "delete  FROM " +  ADB_Master.MyColumns_newbarcode_factory.NamaTabel;
        createdel.execSQL(delcheckfact);

        //sawmill LOG
        String delchecklog = "delete  FROM " +   ADB_Master.MyColumns_newbarcode_log.NamaTabel;
        createdel.execSQL(delchecklog);


        //sawmill MJ Papan
        String delcheckmjpapan = "delete  FROM " +  ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel;
        createdel.execSQL(delcheckmjpapan);


        //sawmill Oven
        String delcheckoven = "delete  FROM " +  ADB_Master.MyColumns_newbarcode_oven.NamaTabel;
        createdel.execSQL(delcheckoven);

        //sawmill swout
        String delcheckswout = "delete  FROM " +    ADB_Master.MyColumns_newbarcode_swout.NamaTabel;
        createdel.execSQL(delcheckswout);


        //sawmill swout
        String delcheckwrh = "delete  FROM " +     ADB_Master.MyColumns_newbarcode_wrh.NamaTabel;
        createdel.execSQL(delcheckwrh);






        pDialog.setMessage("Deleting Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_sawmill(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Clear Data All Trans Completed");
        startActivity(a);
    }



    private void importdata_tipe() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_shortlong.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_tipe.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_tipe(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_tipe(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_shortlong.kode_sl,obj.getString("kode_sl"));
                create.insert(ADB_Master.MyColumns_sawmill_shortlong.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }



    private void importdata_tebal() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmilltebal.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_tebal.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_tebal(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_tebal(String response){

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
                values.put(ADB_Master.MyColumns_sawmilltebal.tebal,obj.getString("tebal"));
                create.insert(ADB_Master.MyColumns_sawmilltebal.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }



    private void importdata_factory() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_factory.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_factory.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_factory(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_factory(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_factory.nama_lokasi,obj.getString("nama_lokasi"));
                values.put(ADB_Master.MyColumns_sawmill_factory.alamat_lokasi,obj.getString("alamat_lokasi"));
                create.insert(ADB_Master.MyColumns_sawmill_factory.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }



    private void cleardatalogtrans() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
        createdel.execSQL(del1);

        String del2 = "delete  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel;
        createdel.execSQL(del2);
        String url ="";


        pDialog.setMessage("Deleting Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_sawmill(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Clear Data LOG Trans Completed");
        startActivity(a);
    }



    private void importdata_sawmill() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_swgroup.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_sawmill.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_sawmill(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_sawmill(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_swgroup.kode_lokasi,obj.getString("kode_lokasi"));
                values.put(ADB_Master.MyColumns_sawmill_swgroup.nama_lokasi,obj.getString("nama_lokasi"));
                create.insert(ADB_Master.MyColumns_sawmill_swgroup.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }





    private void importdata_oven() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_oven.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_oven.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_oven(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_oven(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_oven.nama_lokasi,obj.getString("nama_lokasi"));
                values.put(ADB_Master.MyColumns_sawmill_oven.alamat_lokasi,obj.getString("alamat_lokasi"));
                create.insert(ADB_Master.MyColumns_sawmill_oven.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }



    private void importdata_wrh() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_wrh.NamaTabel;
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdata_wrh.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
//        pDialog = new ProgressDialog(Transaksi_POS.this);
        //      pDialog.setMessage("Retieve Data Barang...");
        // Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        //    pDialog.show();

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_wrh(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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


    private void processResponsePOS_wrh(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_wrh.nama_lokasi,obj.getString("nama_lokasi"));
                values.put(ADB_Master.MyColumns_sawmill_wrh.alamat_lokasi,obj.getString("alamat_lokasi"));
                create.insert(ADB_Master.MyColumns_sawmill_wrh.NamaTabel, null, values);

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
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

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS_sub(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data User Selesai");
        startActivity(a);
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

        pDialog.setMessage("Retrieve Data...");
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                // Log.d("MainActivity bedul kubu","response:"+response);

                //   hideDialog();



                // barangListPOS.clear();
                processResponsePOS(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();

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




    private void printWebViewtran() {
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();


        // WebView myWebView = (WebView) findViewById(R.id.webView);


        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view,
                                                    WebResourceRequest request)
            {
                pDialog.setMessage("Loading Data ...");
                showDialog();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                hideDialog();
                createWebPrintJob(view);
                myWebView = null;
            }
        });

        String htmlDocument =
                "<html><body><h1>Android Print Test</h1><p>"
                        + "This is some sample content.</p></body></html>";

        // webView.loadDataWithBaseURL("detik.com", htmlDocument,

        //  "text/HTML", "UTF-8", null);
       /* webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.codepolitan.com");*/
        //pDialog = new ProgressDialog(Transaksi_POS.this);
        //pDialog.setMessage("Loading Data ...");
        //pDialog.show();
        //Toast.makeText(getBaseContext(),"No Transaksi: "+notrans2, Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('content', 'initial-scale=1.0,maximum-scale=10.0');";
        webView.loadUrl(AppConfig.IP_SERVER+"reportomzetbytran.php?date1="+webhasil1+"&date2="+webhasil2+"&kodecab="+kodecabang1);
        myWebView = webView;

    }


    private void printWebViewomzet() {
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();

        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view,
                                                    WebResourceRequest request)
            {
                pDialog.setMessage("Loading Data ...");
                showDialog();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                hideDialog();
                createWebPrintJob(view);
                myWebView = null;
            }
        });

        String htmlDocument =
                "<html><body><h1>Android Print Test</h1><p>"
                        + "This is some sample content.</p></body></html>";

        // webView.loadDataWithBaseURL("detik.com", htmlDocument,

        //  "text/HTML", "UTF-8", null);
       /* webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.codepolitan.com");*/
        //pDialog = new ProgressDialog(Transaksi_POS.this);
        //pDialog.setMessage("Loading Data ...");
        //pDialog.show();
        //Toast.makeText(getBaseContext(),"No Transaksi: "+notrans2, Toast.LENGTH_SHORT).show();
        Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('content', 'initial-scale=1.0,maximum-scale=10.0');";
        webView.loadUrl(AppConfig.IP_SERVER+"reportomzetbydate.php?date1="+webhasil1+"&date2="+webhasil2+"&kodecab="+kodecabang1);
        myWebView = webView;

    }


    private void printWebViewStocksaja() {
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();

        Intent intent2=getIntent();
        final String kodecabang11=intent2.getStringExtra("kodecabang");
        final String namacabang11=intent2.getStringExtra("namacabang");
        final String groupho11=intent2.getStringExtra("groupho");
        final String username11=intent2.getStringExtra("username");

        WebView webView = new WebView(this);
        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view,
                                                    WebResourceRequest request)
            {
                pDialog.setMessage("Loading Data ...");
                showDialog();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {
                hideDialog();
                createWebPrintJob(view);
                myWebView = null;
            }
        });

        String htmlDocument =
                "<html><body><h1>Android Print Test</h1><p>"
                        + "This is some sample content.</p></body></html>";

        // webView.loadDataWithBaseURL("detik.com", htmlDocument,

        //  "text/HTML", "UTF-8", null);
       /* webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.codepolitan.com");*/
        //pDialog = new ProgressDialog(Transaksi_POS.this);
        //pDialog.setMessage("Loading Data ...");
        //pDialog.show();
        //Toast.makeText(getBaseContext(),"No Transaksi: "+notrans2, Toast.LENGTH_SHORT).show();
        //  Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
        Toast.makeText(getBaseContext(),"GroupHO: "+groupho11, Toast.LENGTH_LONG).show();

        if (groupho11.equals("1")) {
            String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('content', 'initial-scale=1.0,maximum-scale=10.0');";
            webView.loadUrl(AppConfig.IP_SERVER+"laststockho.php?kodecab=" + kodecabang11);

        }else
        {
            String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('content', 'initial-scale=1.0,maximum-scale=10.0');";
            webView.loadUrl(AppConfig.IP_SERVER+"laststockcabang.php?kodecab="+kodecabang11);
        }
        myWebView = webView;
    }



    private void printWebViewMutasisaja2() {
        webhasil1=hasil1.getText().toString();
        webhasil2=hasil2.getText().toString();

        Intent intent2=getIntent();
        kodecabang=intent2.getStringExtra("kodecabang");
        final String namacabang11=intent2.getStringExtra("namacabang");
        groupho=intent2.getStringExtra("groupho");
        final String username11=intent2.getStringExtra("username");



        // webView.loadDataWithBaseURL("detik.com", htmlDocument,

        //  "text/HTML", "UTF-8", null);
       /* webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.codepolitan.com");*/
        //pDialog = new ProgressDialog(Transaksi_POS.this);
        //pDialog.setMessage("Loading Data ...");
        //pDialog.show();
        //Toast.makeText(getBaseContext(),"No Transaksi: "+notrans2, Toast.LENGTH_SHORT).show();
        //  Intent intent=getIntent();
        //final String kodecabang1=intent.getStringExtra("kodecabang");


        //web_view.loadUrl("https://www.tutorialspoint.com/");

     //   Intent a= new Intent(getBaseContext(),printMutasi.class);
       // startActivity(a);

    }






    private void printWebViewMutasisaja() {
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();

        Intent intent2=getIntent();
        final String kodecabang11=intent2.getStringExtra("kodecabang");
        final String namacabang11=intent2.getStringExtra("namacabang");
        final String groupho11=intent2.getStringExtra("groupho");
        final String username11=intent2.getStringExtra("username");

        WebView webView = new WebView(this);

        //  WebSettings webSettings = myWebView.getSettings();
        // webSettings.setJavaScriptEnabled(true);

        // Settings so page loads zoomed-out all the way.
        // webSettings.setLoadWithOverviewMode(true);
        // webSettings.setUseWideViewPort(true);
        // webSettings.setBuiltInZoomControls(true);

        webView.setWebViewClient(new WebViewClient() {

            public boolean shouldOverrideUrlLoading(WebView view,
                                                    WebResourceRequest request)
            {
                pDialog.setMessage("Loading Data ...");
                showDialog();
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url)
            {



                hideDialog();
                createWebPrintJob(view);
                myWebView = null;
            }
        });

        String htmlDocument =
                "<html><body><h1>Android Print Test</h1><p>"
                        + "This is some sample content.</p></body></html>";

        // webView.loadDataWithBaseURL("detik.com", htmlDocument,

        //  "text/HTML", "UTF-8", null);
       /* webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        // Baris di bawah untuk menambahkan scrollbar di dalam WebView-nya
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.codepolitan.com");*/
        //pDialog = new ProgressDialog(Transaksi_POS.this);
        //pDialog.setMessage("Loading Data ...");
        //pDialog.show();
        //Toast.makeText(getBaseContext(),"No Transaksi: "+notrans2, Toast.LENGTH_SHORT).show();
        //  Intent intent=getIntent();
        //final String kodecabang1=intent.getStringExtra("kodecabang");
        if (groupho11.equals("1")) {
            // String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('AppConfig.IP_SERVER+\"/stockmutasiHO.php?date1=\" + webhasil1 + \"&date2=\" + webhasil2 + \"&kodecab=\" + kodecabang11', 'initial-scale=1.0,maximum-scale=10.0');";
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.loadUrl(AppConfig.IP_SERVER+"stockmutasiHO.php?date1=" + webhasil1 + "&date2=" + webhasil2 + "&kodecab=" + kodecabang11);
            // webView.loadUrl(javascript);
        }else {
            //String javascript="javascript:document.getElementsByName('viewport')[0].setAttribute('AppConfig.IP_SERVER+\"/stockmutasicabang.php?date1=\" + webhasil1 + \"&date2=\" + webhasil2 + \"&kodecab=\" + kodecabang11', 'initial-scale=1.0,maximum-scale=10.0');";
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setDisplayZoomControls(false);
            webView.loadUrl(AppConfig.IP_SERVER+"stockmutasicabang.php?date1=" + webhasil1 + "&date2=" + webhasil2 + "&kodecab=" + kodecabang11);
            //webView.loadUrl(javascript);
        }
        myWebView = webView;

    }








    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }


    private void createWebPrintJob(WebView webView) {

        PrintManager printManager = (PrintManager) this
                .getSystemService(Context.PRINT_SERVICE);

        PrintDocumentAdapter printAdapter =
                webView.createPrintDocumentAdapter("MyDocument");

        String jobName = getString(R.string.app_name) + " Print Test";

        printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        // printJobs.add(printJob);
/*
        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) getActivity()
                .getSystemService(Context.PRINT_SERVICE);
        PrintManager printManager = (PrintManager) Transaksi_POS.getSystemService(Context.PRINT_SERVICE);

        String jobName = getString(R.string.app_name) + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        // Save the job object for later status checking
        printJobs.add(printJob);
        */

    }


    private void showDateDialog1(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                tvDateResult1.setText(dateFormatter.format(newDate.getTime()));
                hasil1.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }


    private void showDateDialog2(){

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);


                tvDateResult2.setText(dateFormatter.format(newDate.getTime()));
                hasil2.setText(dateFormatter2.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        datePickerDialog.show();
    }






    private void DownloadDataTrans2(){
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();
        Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        //   final String namacari=cari1.getText().toString();
        // String url = AppConfig.IP_SERVER+"/listdata.php";
        String url ="http://golden-care.co.id/android/reportomzetbytrandown.php";
        //   pDialog.setMessage("Download Data Barang...");
        // Toast.makeText(this,"test masuk load server",Toast.LENGTH_LONG).show();
        //showDialog();



        WritableWorkbook workbook;

        final String date1=hasil1.getText().toString();
        final String date2=hasil2.getText().toString();


        String Fnamexls = "itemtrans5" + ".xls";

        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        directory.mkdir();

        File file = new File(directory, Fnamexls);


        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        //   Toast.makeText(getBaseContext(), "no 1", Toast.LENGTH_LONG).show();
        int a = 1;









        //batas atas--------------------------------------------------
        //  WritableWorkbook workbook;
        try {




            workbook = Workbook.createWorkbook(file, wbSettings);
            //     Toast.makeText(getBaseContext(), "no 1a", Toast.LENGTH_LONG).show();
            WritableSheet sheet = workbook.createSheet("First Sheet", 0);


            //Label I1 = new Label(0,i,String.valueOf(a));
            //Label I1 = new Label(0,i+1,String.valueOf(obj.getString("kode")));
            //Label I2 = new Label(1, i+1, String.valueOf(obj.getString("nama")));
            //Label I3 = new Label(2,i+1,String.valueOf(obj.getString("harga")));
            //Label I4 = new Label(3,i+1,String.valueOf(obj.getString("harga_beli")));


            try {

                Toast.makeText(getBaseContext(), "NO 2 " , Toast.LENGTH_LONG).show();
                Date currentTime = Calendar.getInstance().getTime();

                Label H0 = new Label(0, 0, "Download Date : " + date1 + " s/d " + date2);
                Label H1 = new Label(0, 1, "jjjjjjj");
                Label H2 = new Label(1, 1, "xxxx222");
                Label H3 = new Label(2, 1, "Item Code");
                Label H4 = new Label(3, 1, "Item Desc");
                Label H5 = new Label(4, 1, "Qty");
                Label H6 = new Label(5, 1, "Sell Price");
                Label H7 = new Label(6, 1, "Purchase Price");
                Label H8 = new Label(7, 1, "Discount");
                Label H9 = new Label(8, 1, "Pay Type");
                Label H10 = new Label(9, 1, "Pay No");
                Label H11 = new Label(10, 1, "User Name");
                Label H12 = new Label(11, 1, "Kurs");


                sheet.addCell(H0);
                sheet.addCell(H1);
                sheet.addCell(H2);
                sheet.addCell(H3);
                sheet.addCell(H4);
                sheet.addCell(H5);
                sheet.addCell(H6);
                sheet.addCell(H7);
                sheet.addCell(H8);
                sheet.addCell(H9);
                sheet.addCell(H10);
                sheet.addCell(H11);
                sheet.addCell(H12);






          /*      SQLiteDatabase db2 = adb_trans.getReadableDatabase();

                String selectQuery2 = "SELECT  tgl_rec,notrans_link,item_code,item_desc,qty,harga_jual,harga_beli,disc_val,x1,x2,user_name,x3  FROM trans1sub where tgl_rec >= '"+date1+"' and tgl_rec <= '"+date2+" 23:59:59'";
                //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
                //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
                Cursor c2 = db2.rawQuery(selectQuery2, null);
                int idno4 = c2.getCount();
                //  StringBuffer ok = new StringBuffer();
                // ok.append(obj.getString("kode"));
                //Toast.makeText(getBaseContext(), "no 1a "+String.valueOf(idno4)+date1+"-"+date2, Toast.LENGTH_LONG).show();



                if ( c2.moveToFirst()) {

                    for (int i = 0; i < idno4; i++) {

                        Label H51 = new Label(0, i + 2, c2.getString(0));
                        Label H61 = new Label(1, i + 2, c2.getString(1));
                        Label H71 = new Label(2, i + 2, c2.getString(2));
                        Label H81 = new Label(3, i + 2, c2.getString(3));
                        Label H101 = new Label(4, i + 2,String.valueOf(c2.getDouble(4)));
                        Label H111 = new Label(5, i + 2, String.valueOf(c2.getDouble(5)));
                        Label H121 = new Label(6, i + 2, String.valueOf(c2.getDouble(6)));
                        Label H131 = new Label(7, i + 2, String.valueOf(c2.getDouble(7)));
                        Label H141 = new Label(8, i + 2, c2.getString(8));
                        Label H151 = new Label(9, i + 2, c2.getString(9));
                        Label H161 = new Label(10, i + 2, c2.getString(10));
                        Label H162 = new Label(11, i + 2, c2.getString(11));


                        sheet.addCell(H51);
                        sheet.addCell(H61);
                        sheet.addCell(H71);
                        sheet.addCell(H81);
                        sheet.addCell(H101);
                        sheet.addCell(H111);
                        sheet.addCell(H121);
                        sheet.addCell(H131);
                        sheet.addCell(H141);
                        sheet.addCell(H151);
                        sheet.addCell(H161);
                        sheet.addCell(H162);


                        c2.moveToNext();

                    }

                }*/


            } catch (RowsExceededException e) {
                e.printStackTrace();
            } catch (WriteException e) {
                e.printStackTrace();
            }

            workbook.write();

            try {
                workbook.close();
            } catch (WriteException e) {

                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();

            Log.d("Excel Detect", "error Excel");

        }
        openDataTrans();
    }









    private void DownloadDataTransx(){
        final String webhasil1=hasil1.getText().toString();
        final String webhasil2=hasil2.getText().toString();
        Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        //   final String namacari=cari1.getText().toString();
        // String url = AppConfig.IP_SERVER+"/listdata.php";
        String url =AppConfig.IP_SERVER+"gcnew/reportsalesgroupexcel.php";
       // pDialog.setMessage("Download Data Barang...");
        Toast.makeText(this,"test masuk load server",Toast.LENGTH_LONG).show();
       // showDialog();

        StringRequest postRequesttrans = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response1) {

                Log.d("MainActivity bedul kubu","response:"+response1);

                hideDialog();



                processResponse1(response1);

                // gambarDatakeRecyclerView();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        // pDialog.setMessage("Connection failed");


                        if (error instanceof NetworkError) {
                            Toast.makeText(Utillity.this, "Oops. Network Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(Utillity.this, "Oops. Server Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(Utillity.this, "Oops. No Connection", Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(Utillity.this, "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                        }


                        hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams()

            {

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("tgl1",webhasil1);
                params.put("tgl2",webhasil2);
                params.put("kodecab","x");

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequesttrans);

    }




    private void processResponse1(String response1){

        WritableWorkbook workbook;

        final String date1=hasil1.getText().toString();
        final String date2=hasil2.getText().toString();

        String Fnamexls = nodoc+ ".xls";
       // String Fnamexls = "itemtrans6" + ".xls";

        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        directory.mkdir();

        File file = new File(directory, Fnamexls);


        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        Toast.makeText(getBaseContext(), "no 1", Toast.LENGTH_LONG).show();
        int a = 1;







     //   try {

          //  Log.d("TAG", "data response bedul kubu: " + response1);

           // JSONObject jsonObj = new JSONObject(response1);

         //   JSONArray jsonArray = jsonObj.getJSONArray("data");







      //      Log.d("TAG", "data length: " + jsonArray.length());

            //  Barang objectbarang = null;

            //barangList.clear();


            //          objectbarang= new Barang();

            //   Locale localeID = new Locale("in", "ID");
            // NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            // detailHarga.setText(formatRupiah.format((double)hargarumah));

/*
                objectbarang.setId(obj.getString("id"));
                objectbarang.setNama(obj.getString("nama"));
                objectbarang.setKode(obj.getString("kode"));
                objectbarang.setHarga(obj.getString("harga"));
                objectbarang.setHarga_beli(obj.getString("harga_beli"));
                barangList.add(objectbarang);*/


            //batas atas--------------------------------------------------
            //  WritableWorkbook workbook;



           try {




                 workbook = Workbook.createWorkbook(file, wbSettings);
                Toast.makeText(getBaseContext(), "no 1a", Toast.LENGTH_LONG).show();
                WritableSheet sheet = workbook.createSheet("First Sheet", 0);




                //Label I1 = new Label(0,i,String.valueOf(a));
                //Label I1 = new Label(0,i+1,String.valueOf(obj.getString("kode")));
                //Label I2 = new Label(1, i+1, String.valueOf(obj.getString("nama")));
                //Label I3 = new Label(2,i+1,String.valueOf(obj.getString("harga")));
                //Label I4 = new Label(3,i+1,String.valueOf(obj.getString("harga_beli")));


             try {


                    Date currentTime = Calendar.getInstance().getTime();

                    Label H0 = new Label(0, 0, "Sales Golden Care : "+date1+" s/d "+date2);
                    Label H1 = new Label(0, 1, "Item Code");
                    Label H2 = new Label(1, 1, "Item Desc.");
                    Label H3 = new Label(2, 1, "Qty");
                    Label H4 = new Label(3, 1, "Date");
                    Label H5 = new Label(4, 1, "Tujuan");
                    Label H6 = new Label(4, 1, "No. Inv");
                    //Label H6 = new Label(5, 1, "Harga Jual");
                    //Label H7 = new Label(6, 1, "Harga Beli");
                    //Label H8 = new Label(7, 1, "Discount");


                    sheet.addCell(H0);
                    sheet.addCell(H1);
                    sheet.addCell(H2);
                    sheet.addCell(H3);
                    sheet.addCell(H4);
                    sheet.addCell(H5);
                    sheet.addCell(H6);
                    //sheet.addCell(H6);
                    //sheet.addCell(H7);
                    //sheet.addCell(H8);


                    try{

                        Toast.makeText(getBaseContext(), "NO2 " , Toast.LENGTH_LONG).show();
                        JSONObject jsonObj = new JSONObject(response1);

                        JSONArray jsonArray = jsonObj.getJSONArray("data");


                  for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject obj = jsonArray.getJSONObject(i);

                        //  StringBuffer ok = new StringBuffer();
                        // ok.append(obj.getString("kode"));


                        Label H51 = new Label(0, i + 2, obj.getString("item_code"));
                        Label H61 = new Label(1, i + 2, obj.getString("item_desc"));
                        Label H71 = new Label(2, i + 2, obj.getString("Qty"));
                        Label H81 = new Label(3, i + 2, obj.getString("tgl"));
                        Label H91 = new Label(4, i + 2, obj.getString("tujuan"));
                        Label H92 = new Label(5, i + 2, obj.getString("noinv"));
                        //Label H101 = new Label(5, i + 2, obj.getString("harga_jual"));
                        //Label H111 = new Label(6, i + 2, obj.getString("harga_beli"));
                        //Label H121 = new Label(7, i + 2, obj.getString("disc_val"));


                        sheet.addCell(H51);
                        sheet.addCell(H61);
                        sheet.addCell(H71);
                        sheet.addCell(H81);
                        sheet.addCell(H91);
                        sheet.addCell(H92);
                        //sheet.addCell(H101);
                        //sheet.addCell(H111);
                        //sheet.addCell(H121);


                    }
                    } catch (JSONException e) {

                        Log.d("MainActivity", "errorJSON");

                        // openDataTrans();

                    }


             } catch (RowsExceededException e) {
                     e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }

                workbook.write();

                try {
                    workbook.close();
                } catch (WriteException e) {

                    e.printStackTrace();

                    Log.d("Excel Detect", "error Excel");
                }

           } catch(IOException e){
                e.printStackTrace();

                Log.d("Excel Detect", "error Excel");
            }


         openDataTrans2();


//------------------------------------------------batas volley-------------------------------------------//


   /*     } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");

           // openDataTrans();

        } */
       //openDataTrans();
    }



    private void openDataTrans(){
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        // directory.mkdir();
        String Fnamexls = "itemtrans6" + ".xls";
        File file = new File(directory, Fnamexls);
        //File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata/itemmaster.xls");
        if (file.exists())
        {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
           // Toast.makeText(Report.this, "view xls", Toast.LENGTH_LONG).show();
            Intent ambil=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            ambil.setDataAndType(uri, "application/vnd.ms-excel");
            ambil.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            ambil.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


            try
            {
                startActivity(Intent.createChooser(ambil,"Pilih Aplikasi :"));
            }
            catch(ActivityNotFoundException e)
            {
                //Toast.makeText(MainActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }


    }




    private void openDataTrans2(){
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        // directory.mkdir();
        final String date1=tvDateResult1.getText().toString();
        final String date2=tvDateResult2.getText().toString();
     //   String Fnamexls = "Lapsales"+date1+"to"+date2 + ".xls";
       //
        // String Fnamexls = "itemtrans.xls";
      // String Fnamexls = "Lapsales"+date1+"to"+date2 + ".xls";
                String Fnamexls = nodoc+ ".xls";
        File file = new File(directory, Fnamexls);
        //File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata/itemmaster.xls");
        if (file.exists())
        {
              StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
             StrictMode.setVmPolicy(builder.build());

/*            Toast.makeText(Acc_Report.this, "view xls", Toast.LENGTH_LONG).show();
            Intent ambil=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            ambil.setDataAndType(uri, "application/vnd.ms-excel");
            ambil.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            ambil.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
*/


            Intent ambil = new Intent(Intent.ACTION_SEND);
            ambil.setType("application/vnd.ms-excel");
            // ambil.setDataAndType(uri, "application/vnd.ms-excel");
            //  target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            //target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //  target.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            // target.setPackage("com.adobe.reader");
            //   Intent intent = Intent.createChooser(target, "Share File To: ");
            ambil.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            ambil.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
            ambil.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));


            try
            {
                startActivity(Intent.createChooser(ambil,"Pilih Aplikasi :"));
            }
            catch(ActivityNotFoundException e)
            {
                //Toast.makeText(MainActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }else
        {
            Toast.makeText(Utillity.this, "File tidak ada", Toast.LENGTH_LONG).show();
        }


    }


    private void openDataMaster(){
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        // directory.mkdir();
        String Fnamexls = "itemmaster5" + ".xls";
        File file = new File(directory, Fnamexls);
        //File file = new File( Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata/itemmaster.xls");
        if (file.exists())
        {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
            Toast.makeText(Utillity.this, "view xls", Toast.LENGTH_LONG).show();
            Intent ambil=new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(file);
            ambil.setDataAndType(uri, "application/vnd.ms-excel");
            ambil.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            ambil.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);


            try
            {
                startActivity(Intent.createChooser(ambil,"Pilih Aplikasi :"));
            }
            catch(ActivityNotFoundException e)
            {
                //Toast.makeText(MainActivity.this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
            }
        }


    }

    private void DownloadDataMaster(){
        //   final String namacari=cari1.getText().toString();
        // String url = AppConfig.IP_SERVER+"/listdata.php";
        Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        String url ="http://anugrah.store/pos/listdata.php";
        pDialog.setMessage("Download Data Barang...");
        Toast.makeText(this,"test masuk load server",Toast.LENGTH_LONG).show();
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                Log.d("MainActivity bedul kubu","response:"+response);

                hideDialog();



                processResponse(response);

                // gambarDatakeRecyclerView();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        // pDialog.setMessage("Connection failed");

                        if (error instanceof NetworkError) {
                            Toast.makeText(Utillity.this, "Oops. Network Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(Utillity.this, "Oops. Server Error", Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                            Toast.makeText(Utillity.this, "Oops. No Connection", Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(Utillity.this, "Oops. Timeout error!", Toast.LENGTH_LONG).show();
                        }




                        hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams()

            {

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("kodecab","C");

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);

    }

/*
    public void onErrorResponse(VolleyError error) {
        if (error instanceof NetworkError) {
        } else if (error instanceof ServerError) {
        } else if (error instanceof AuthFailureError) {
        } else if (error instanceof ParseError) {
        } else if (error instanceof NoConnectionError) {
        } else if (error instanceof TimeoutError) {
            Toast.makeText(this,
                    "Oops. Timeout error!",
                    Toast.LENGTH_LONG).show();
        }}*/


    private void processResponse(String response){

        WritableWorkbook workbook;


      //  String Fnamexls = "Lapcheckinout"+date1+"to"+date2 + ".xls";
        String Fnamexls = "itemmaster2" + ".xls";

       // String Fnamexls = "Lapsales"+date1+"to"+date2 + ".xls";

        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/exceldata");
        directory.mkdir();

        File file = new File(directory, Fnamexls);


        WorkbookSettings wbSettings = new WorkbookSettings();
        wbSettings.setLocale(new Locale("en", "EN"));
        Toast.makeText(getBaseContext(), "no 1", Toast.LENGTH_LONG).show();
        int a = 1;







        try {

            Log.d("TAG", "data response bedul kubu: " + response);

            JSONObject jsonObj = new JSONObject(response);

            JSONArray jsonArray = jsonObj.getJSONArray("data");

            Log.d("TAG", "data length: " + jsonArray.length());

            //  Barang objectbarang = null;

            //barangList.clear();


            //          objectbarang= new Barang();

            //   Locale localeID = new Locale("in", "ID");
            // NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            // detailHarga.setText(formatRupiah.format((double)hargarumah));

/*
                objectbarang.setId(obj.getString("id"));
                objectbarang.setNama(obj.getString("nama"));
                objectbarang.setKode(obj.getString("kode"));
                objectbarang.setHarga(obj.getString("harga"));
                objectbarang.setHarga_beli(obj.getString("harga_beli"));
                barangList.add(objectbarang);*/


            //batas atas--------------------------------------------------
            //  WritableWorkbook workbook;
            try {
                workbook = Workbook.createWorkbook(file, wbSettings);
                Toast.makeText(getBaseContext(), "no 1a", Toast.LENGTH_LONG).show();
                WritableSheet sheet = workbook.createSheet("First Sheet", 0);




                //Label I1 = new Label(0,i,String.valueOf(a));
                //Label I1 = new Label(0,i+1,String.valueOf(obj.getString("kode")));
                //Label I2 = new Label(1, i+1, String.valueOf(obj.getString("nama")));
                //Label I3 = new Label(2,i+1,String.valueOf(obj.getString("harga")));
                //Label I4 = new Label(3,i+1,String.valueOf(obj.getString("harga_beli")));


                try {

                    //  Toast.makeText(getBaseContext(), "NO I : " + i, Toast.LENGTH_LONG).show();
                    Date currentTime = Calendar.getInstance().getTime();

                    Label H0 = new Label(0, 0, "Tanggal Download : "+currentTime);
                    Label H1 = new Label(0, 1, "KODE");
                    Label H2 = new Label(1, 1, "Nama Product");
                    Label H3 = new Label(2, 1, "Harga Jual");
                    Label H4 = new Label(3, 1, "Harga Beli");

                    sheet.addCell(H0);
                    sheet.addCell(H1);
                    sheet.addCell(H2);
                    sheet.addCell(H3);
                    sheet.addCell(H4);


                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject obj = jsonArray.getJSONObject(i);

                        //  StringBuffer ok = new StringBuffer();
                        // ok.append(obj.getString("kode"));


                        Label H5 = new Label(0, i + 2, obj.getString("kode"));
                        Label H6 = new Label(1, i + 2, obj.getString("nama"));
                        Label H7 = new Label(2, i + 2, obj.getString("harga"));
                        Label H8 = new Label(3, i + 2, obj.getString("harga_beli"));


                        sheet.addCell(H5);
                        sheet.addCell(H6);
                        sheet.addCell(H7);
                        sheet.addCell(H8);
                    }

                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }

                workbook.write();

                try {
                    workbook.close();
                } catch (WriteException e) {

                    e.printStackTrace();
                }

            } catch(IOException e){
                e.printStackTrace();
            }





//------------------------------------------------batas volley-------------------------------------------//


        } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");

        }
        openDataMaster();
    }



    @Override
    public void onBackPressed() {
        //Toast.makeText(this,"BackButton",Toast.LENGTH_SHORT).show();

        Intent intent2=getIntent();
        final String kodecabang11=intent2.getStringExtra("kodecabang");
        final String namacabang11=intent2.getStringExtra("namacabang");
        final String groupho11=intent2.getStringExtra("groupho");
        final String username11=intent2.getStringExtra("username");

        Intent balik4=new Intent();
        balik4.putExtra("kodecab",kodecabang11);
        balik4.putExtra("namacab",namacabang11);
        balik4.putExtra("user1",username11);
        balik4.putExtra("groupho",groupho11);
        setResult(RESULT_OK,balik4);
        finish();


    }

}

