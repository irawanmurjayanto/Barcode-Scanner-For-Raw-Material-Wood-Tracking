package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class NURUploadBarcodeLokal_sub1_backup extends AppCompatActivity {
    LayoutInflater inflater;
    View dialogView;
    private ProgressDialog pDialog;
    private EditText cari1, editoven;
    private static int hitbarc,tot;
    AlertDialog.Builder dialog;
    private BarangSawmill_barc barang;
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;
    TextView textLokasi;
    public static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;

    Spinner spnTebal,spnSGD,spnShort,spnSwgroup;
    List<String> users=new ArrayList<>();
    ArrayAdapter<String> adapter;
    TextView textTempTebal, textTempTebal2,textTempSGD, textTempSGD2,textIdno_link,textNolog,textHbarcode;
    TextView textTempShort,textTempShort2,textTempSwgroup,textTempSwgroup2;
    private SimpleDateFormat dateFormatter;
    Button btnBarcode,btnSave;
    EditText editLebar,editPanjang,editBarcode,editNolog;
    public static String lastscan;
    private static int typesave,nol_link,no_log,getidno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_n_u_r_upload_barcode_lokal_sub1);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Data Papan");
        setSupportActionBar(toolbar);

      //  textLokasi=findViewById(R.id.textlokasi);
        spnTebal=findViewById(R.id.spnTebal);
        spnSGD=findViewById(R.id.spnSGD);
        spnShort=findViewById(R.id.spnShort);
        spnSwgroup=findViewById(R.id.spnSwgroup);

        textTempTebal=(TextView)findViewById(R.id.textTempTebal);
        textTempTebal2=(TextView)findViewById(R.id.textTempTebal2);
        textTempSGD=(TextView)findViewById(R.id.textTempSGD);
        textTempSGD2=(TextView)findViewById(R.id.textTempSGD2);
        textIdno_link=(TextView)findViewById(R.id.textIdno_link);
        textNolog=(TextView) findViewById(R.id.textNolog);
        textTempShort=(TextView)findViewById(R.id.textTempShort);
        textTempShort2=(TextView)findViewById(R.id.textTempShort2);
        textTempSwgroup=(TextView)findViewById(R.id.textTempSwgroup);
        textTempSwgroup2=(TextView)findViewById(R.id.textTempSwgroup2);
        textHbarcode=(TextView)findViewById(R.id.textHbarcode);
        editBarcode=findViewById(R.id.editBarcode);

        btnBarcode=(Button) findViewById(R.id.btnBarcode);
        btnSave=(Button) findViewById(R.id.btnSave);

        editLebar=findViewById(R.id.editLebar);
        editPanjang=findViewById(R.id.editPanjang);

        adb_master=new ADB_Master(getBaseContext());
        adb_trans=new ADB_Trans(getBaseContext());


        Intent a=getIntent();
        String getidno_link=a.getStringExtra("idno_link");
        textNolog.setText(a.getStringExtra("no_log"));
        textHbarcode.setText(a.getStringExtra("barcodeheader"));
        textIdno_link.setText(getidno_link);
        Toast.makeText(getBaseContext(),"Barcode H :"+textHbarcode.getText().toString(),Toast.LENGTH_LONG).show();


        //set null utk spinner new data
        pDialog = new ProgressDialog(this);

       SQLiteDatabase db2 = adb_master.getReadableDatabase();
        //  String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel+ " WHERE "
        //      + ADB_Master.MyColumns_item.kode + " = '"+item_code+"'";
        //  String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_sawmilltemp.NamaTabel;
        Cursor c2 = db2.rawQuery(selectQuery2, null);
        int idno4 = c2.getCount();
        c2.moveToFirst();
     //   textLokasi.setText(c2.getString(2));


        spnSGD.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                textTempSGD.setText( spnSGD.getSelectedItem().toString());
                //  editTextHak.setText( allusers.getSelectedItem().toString());

/*adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //attach adapter to spinner
                allusers.setAdapter(adapter);
                String compareValue = editTextHak.getText().toString();
                Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
                if (compareValue != null) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    allusers.setSelection(spinnerPosition);
              */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spnTebal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                textTempTebal.setText( spnTebal.getSelectedItem().toString());
                //  editTextHak.setText( allusers.getSelectedItem().toString());

/*adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                //attach adapter to spinner
                allusers.setAdapter(adapter);
                String compareValue = editTextHak.getText().toString();
                Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
                if (compareValue != null) {
                    int spinnerPosition = adapter.getPosition(compareValue);
                    allusers.setSelection(spinnerPosition);
              */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });



        spnShort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                textTempShort.setText( spnShort.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

        spnSwgroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                textTempSwgroup.setText( spnSwgroup.getSelectedItem().toString());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });


        //noreflokal = textnoreff.getText().toString();

//Button Barcode and Save



btnBarcode.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {



            //ambilremarks = editremarks.getText().toString();
            //Toast.makeText(getBaseContext(), "Load barcode", Toast.LENGTH_LONG).show();
            IntentIntegrator integrator = new IntentIntegrator(NURUploadBarcodeLokal_sub1_backup.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
            integrator.setCaptureActivity(TorchOnCaptureActivityLokal.class);
            integrator.setOrientationLocked(false);
            integrator.setPrompt("Scan a barcode PT. Paradise");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(true);
            integrator.initiateScan();



    }
});



        if (a.hasExtra("barang")) {


            barang = (BarangSawmill_barc) a.getSerializableExtra("barang");

typesave=2;
            setData(barang);

        //  //  noreflokal = barang.getNoref();
          //  ambilnoref = textnoreff.getText().toString();
        } else {
            typesave=1;
            Calendar newDate = Calendar.getInstance();
//no ref generate
            dateFormatter = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.US);
            Date currentTime = Calendar.getInstance().getTime();
textTempSwgroup2.setText(a.getStringExtra("sw_group"));
            //     Date currentTime = Calendar.getInstance().getTime();
           // textnoreff.setText(MainActivity.user_idbuffer + dateFormatter.format(newDate.getTime()));
           // noreflokal = textnoreff.getText().toString();

            //norefall=MainActivity.user_idbuffer+dateFormatter.format(newDate.getTime());
        }

spnSwgroup.setEnabled(false);
//load spinner
        getSwgroup();
        getShort();
        getTebal();
        getSGD();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (typesave==1) {
                Toast.makeText(getBaseContext(), "Tujuan :" + textTempSGD.getText().toString().length()+"--"+textTempTebal.getText().toString().length(), Toast.LENGTH_LONG).show();
                  if (textTempSwgroup.getText().toString().isEmpty()||textTempShort.getText().toString().isEmpty()||textTempTebal.getText().toString().length()==5||textTempSGD.getText().toString().length()==5||editLebar.getText().toString().isEmpty()||editPanjang.getText().toString().isEmpty()||editBarcode.getText().toString().isEmpty())
                  {

                      Intent a = new Intent(getBaseContext(), warningumum.class);
                      a.putExtra("warning", "Nilai tidak boleh kosong");
                      startActivity(a);

                  }else {
                      insertdata();
                  }
                }else {
                    updatedata();
                }
            }
        });
    }





    private void setData(BarangSawmill_barc barang) {

        // Locale localeID = new Locale("in", "ID");
        //NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //detailHarga.setText(formatRupiah.format((double)hargarumah));

        DecimalFormat formatter = new DecimalFormat("#");

        SQLiteDatabase db2 = adb_master.getReadableDatabase();
        String selectQuery2 = "SELECT  barcode,tebal,lebar,panjang,sgd,group_sw,group_panjang,tgl_rec,idno_link1  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel+ " where idno= "+barang.getId();
        Cursor c2 = db2.rawQuery(selectQuery2, null);
        int idno4 = c2.getCount();
        c2.moveToFirst();
        editBarcode.setText(c2.getString(0));
        //textNoref.setText(c2.getString(2));
       // textLokasi.setText(c2.getString(4));
        textTempTebal2.setText(c2.getString(1));
        textTempSGD2.setText(c2.getString(4));
        textTempShort2.setText(c2.getString(6));
        textTempSwgroup2.setText(c2.getString(5));
        editPanjang.setText(c2.getString(3));
        editLebar.setText(c2.getString(2));
        getidno=barang.getId();
        Toast.makeText(getBaseContext(), "IDNO :" + getidno, Toast.LENGTH_LONG).show();
      //  textIdno_link.setText(c2.getString(8));
        //textNolog.setText(barang.getNo_log());
       // textNolog.setText(c2.getString(11));

        // totalrp.setText(formatter.format(obj.getDouble("total")));

      //   textTempTebal2.setText();
      //   textTempS.setText();
        //  textTampung3.setText(String.valueOf(barang.getNamaoven()));
        //  editremarks.setText(barang.getNamaoven2());
     //   textnoreff.setText(barang.getNoref());
        //   editoven.setText(barang.getNamaoven2());
        //   Toast.makeText(getBaseContext(), "Tujuan :" + barang.getLokoven(), Toast.LENGTH_LONG).show();

      /*   if (Integer.valueOf(t1.getText().toString())>0)
         {
           // spnOven.setEnabled(false);
          //  butovenname.setEnabled(false);
             Toast.makeText(getBaseContext(),"t",Toast.LENGTH_LONG).show();
         }*/

       // loadDataLokal();
     //   gambarDatakeRecyclerView();

    }




    private void insertto_test(final String norefx) {
        //del transaction


        //  pDialog.setMessage("Uploading Data...");
        //  pDialog.show();


        SQLiteDatabase db = adb_master.getWritableDatabase();
        String sql1 = "select barcode,noref,lokasi,tebal,sgd,lebar,panjang,temp5,idno from tbl_barctran where noref='" + norefx + "' ";
        Cursor cursor = db.rawQuery(sql1, null);
        hitbarc=cursor.getCount();


        SQLiteDatabase dbtrans = adb_trans.getWritableDatabase();

        //delete awal data di tbl barang
        String del1="delete from trans1sub";
        dbtrans.execSQL(del1);

        // cursor.moveToFirst();

        SQLiteDatabase createtrans = adb_trans.getWritableDatabase();
        // String selectQuery = "SELECT hak FROM " + ADB_User.MyColumnsHak.NamaTabel;
        //Cursor cursor=create.rawQuery(selectQuery,null);

        ContentValues values = new ContentValues();
        values.put(ADB_Trans.MyColumns_trans1sub.item_code, "x");
        values.put(ADB_Trans.MyColumns_trans1sub.item_desc, "x");
        createtrans.insert(ADB_Trans.MyColumns_trans1sub.NamaTabel, null, values);

       /* String sql12 = "select kode from tbl_barang ";
        Cursor cursor2 = db.rawQuery(sql12, null);

        int jum1=cursor2.getCount();

        Toast.makeText(getBaseContext(),"Last Barcode:  "+jum1,Toast.LENGTH_LONG).show();
*/
////////////              batas del


        //   int hut = 0;

        if (cursor.moveToFirst()) {
            do {
                //    BarangDialog userin = new BarangDialog(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                //  userList2.add(userin);

                String sql12 = "select item_code from trans1sub where item_desc='x'";
                Cursor cursor2 = dbtrans.rawQuery(sql12, null);
                cursor2.moveToFirst();

                // SQLiteDatabase db=adb_master.getWritableDatabase();

                String upd1="update trans1sub set item_code='"+cursor2.getString(0)+";"+cursor.getString(0)+"'";
                db.execSQL(upd1);

                int jum=cursor2.getCount();

                //  Toast.makeText(getBaseContext(),"Last Barcode:  "+cursor.getString(0),Toast.LENGTH_LONG).show();


            } while (cursor.moveToNext());

        }


         String sql12 = "select item_code from trans1sub where item_desc='x'";
        Cursor cursor2 = db.rawQuery(sql12, null);
        cursor2.moveToFirst();
      //  cursor.moveToLast();
       // String upd1="update tbl_barang set kode='"+cursor2.getString(0)+";"+cursor.getString(0)+"'";
       // db.execSQL(upd1);

        // editremarks.setText(cursor2.getString(0));

        // Toast.makeText(getBaseContext(),"Last Barcode:  "+cursor2.getString(0),Toast.LENGTH_LONG).show();




        //sqlserverinsert(cursor2.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        sqlserverinsert(cursor2.getString(0), "x", "x","x", "x", "x");

//hideDialog();
     /*   int jum= cursor.getCount();
cursor.moveToFirst();

        for (int i = 0; i < jum; i++)
        {
            tagdata=1;
            sqlserverinsert(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));


                cursor.moveToNext();

        }
*/


        //  Toast.makeText(getBaseContext(),"Last Barcode:  "+cursor.getString(0),Toast.LENGTH_SHORT).show();
   /*     cursor.moveToFirst();
        Toast.makeText(getBaseContext(),"Completed Transfer Jumlah:  "+i,Toast.LENGTH_SHORT).show();
        updatebarctran(norefx);


    */
        //  return userList2;


        //    loadDataServerVolley();

        Toast.makeText(getBaseContext(),"Upload Completed",Toast.LENGTH_SHORT).show();
    }





    private void sqlserverinsert(final String t0, final String t1, final String t2, final String t3, final String t4, final String t5) {


        //hit = 0;
    /*    final String macadd1 = textgetmax.getText().toString();
        final String location1 = textlocation.getText().toString();
        final String absen = textabsen.getText().toString();


        if (absen.equals("Masuk."))
        {
            statusabsen="masuk";
        }else
        {
            statusabsen="keluar";
        }*/

        //  final String tglmasuk = textDate.getText().toString();
        // final String jammasuk = textTime.getText().toString();


        String url = AppConfig.IP_SERVER+"sawmill/lokal_insertdatasawmill.php";


        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {


                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();
                //   Toast.makeText(getBaseContext(),"Hasil awal: "+macadd1+location1+absen,Toast.LENGTH_SHORT).show();

                processResponse(response);
                // deldatatrans();
                //loadDataServerVolley();

                //    hideDialog();


                //   finish();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();


                        if (error instanceof NetworkError) {
                            //   Toast.makeText(getBaseContext(),
                            //   "Oops. Network Error",
                            //  Toast.LENGTH_LONG).show();
                            DialogErrorNetwork();

                        } else if (error instanceof ServerError) {
                            DialogErrorNetwork();
                            //   Toast.makeText(getBaseContext(),
                            //     "Oops. Server Time out",
                            //   Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                            DialogErrorNetwork();
                        } else if (error instanceof ParseError) {
                            DialogErrorNetwork();
                        } else if (error instanceof NoConnectionError) {
                            DialogErrorNetwork();
                            // Toast.makeText(getBaseContext(),
                            // "Oops. No Connection!",
                            //  Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            DialogErrorNetwork();
                            // Toast.makeText(getBaseContext(),
                            //  "Oops. Timeout error!",
                            // Toast.LENGTH_LONG).show();
                        }


                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                // the POST parameters:

                params.put("barcode", t0);
                params.put("noref", t1);
                params.put("typelokasi", t2);
                params.put("tgl_barcode", t3);
                params.put("vdname", t4);
                params.put("user_name", t5);
                params.put("hitbarc", String.valueOf(hitbarc));

                //params.put("remarks", "tre");


                // params.put("tglmasuk",tglmasuk);
                // params.put("jammasuk",jammasuk);

                // params.put("nama",nama1);



/*
                if (action_flag.equals("add")){

                    params.put("id","0");

                }else{
                    //for add setup saving bro...
                    params.put("id","99");

                }
*/
                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        // Volley.newRequestQueue(this).getCache().clear();



        // simpleprogress.setProgress(Integer.valueOf(valprogress));
    }

    private void processResponse(String response) {

        try {


            Log.d("TAG", "data response DATA POS: " + response);

            JSONObject jsonObj = new JSONObject(response);


         //   final String check1 = jsonObj.getString("check1");
          //  final String check2 = jsonObj.getString("check2");
         //   final String check3 = jsonObj.getString("check3");







          /*  final Handler handler = new Handler();
            handler.post( new Runnable(){
                private int k = 0;
*/



            //  public void run() {





        /*    if (check1.equals(String.valueOf(hitbarc))) {

                Toast.makeText(getBaseContext(),"Jumlah Akhir "+check1+"--"+check2+"--"+hitbarc,Toast.LENGTH_SHORT).show();

                if (Integer.valueOf(check3)>0) {
                    //  updatebarctran(ambilnoref);
                    Intent a = new Intent(getBaseContext(), Emp_RegErrorLokal.class);
                    startActivity(a);
                    return;
                }




                if (check2.equals("0")||check2.equals(null)) {
                    updatebarctran(ambilnoref);

                    Intent a1 = new Intent(getBaseContext(), warningselesai.class);
                    a1.putExtra("warning", "Upload Selesai ...Error: " + check2);
                    //  a1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(a1);



                } else {


                    Intent a = new Intent(getBaseContext(), Emp_RegErrorLokal.class);
                    startActivity(a);
                }
            }
*/

            //  k++;
            // if( k <= hitbarc)
            //{
            // Here `this` refers to the anonymous `Runnable`

            //   handler.postDelayed(this, 1000);
            //}
            //}
            //});














        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }
        //  Toast.makeText(getBaseContext(),"Jumlah Akhir ",Toast.LENGTH_SHORT).show();

        // hideDialog();
       // tagdata = 2;


    }




    private void DialogErrorNetwork() {
        dialog = new AlertDialog.Builder(NURUploadBarcodeLokal_sub1_backup.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.error_network, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Network Error,Pilih Yes untuk mengulangi upload");

        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();
              //  sqldelnoref(textnoreff.getText().toString());
                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                //  finish();
            }
        });

        dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
        return;
    }


    private void DialogErrorServer() {
        dialog = new AlertDialog.Builder(NURUploadBarcodeLokal_sub1_backup.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.error_server, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Server Error");

        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("Close", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();

                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                finish();
            }
        });
/*
        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }




    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }


    private void updatebarctran(final String norefx)
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String upd1="update tbl_barctran set tagtran=1 where noref='"+norefx+"'";
        db.execSQL(upd1);
    }




    private void getSwgroup() {

        users=adb_master.getSwgroup();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnSwgroup.setAdapter(adapter);
        String compareValue = textTempSwgroup2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnSwgroup.setSelection(spinnerPosition);
        }


    }


    private void getShort() {

        users=adb_master.getShort();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnShort.setAdapter(adapter);
        String compareValue = textTempShort2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnShort.setSelection(spinnerPosition);
        }


    }


    private void getTebal() {

        users=adb_master.getTebal();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnTebal.setAdapter(adapter);
        String compareValue = textTempTebal2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnTebal.setSelection(spinnerPosition);
        }


    }


    private void getSGD() {

        users=adb_master.getSGD();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnSGD.setAdapter(adapter);
        String compareValue = textTempSGD2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnSGD.setSelection(spinnerPosition);
        }


    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


        if (scanningResult != null) {

            // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));
            //insertdata();

            if (scanningResult.getContents() != null) {
                //   Toast.makeText(this, "hASIL cANNING"+scanningResult.getContents(), Toast.LENGTH_SHORT).show();
                editBarcode.setText("");
                editBarcode.setText(scanningResult.getContents());
                lastscan = scanningResult.getContents();

                //  loadDataLokal();
                //   gambarDatakeRecyclerView();
                //we have a result
                //cari1.setText(scanningResult.getContents());
                // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));
                if (lastscan.length() == 9 || lastscan.length() == 10) {

                    //String noref = textNoref.getText().toString();
                    String barcodex = editBarcode.getText().toString();
                    SQLiteDatabase db2 = adb_master.getReadableDatabase();

                    String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel + " WHERE "
                            + ADB_Master.MyColumns_newbarcode.idnn_link1 + " = '" + textIdno_link.getText().toString() + "' and " + ADB_Master.MyColumns_newbarcode.barcode + " = '" + barcodex + "'";
                    //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
                    //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
                    Cursor c2 = db2.rawQuery(selectQuery2, null);
                    int hit1 = c2.getCount();
                    //  Toast.makeText(this, "hit" + hit1, Toast.LENGTH_SHORT).show();
                  //  Toast.makeText(this, "tbl:" + tebal+"sgd:" + sgd+"pnj:" + panjang+"lbr:" + lebar+"lok:" + lokasi+"noref:" + noref+"barcode:" +barcodex, Toast.LENGTH_SHORT).show();


                    if (hit1 > 0) {

                        //Toast.makeText(this, "Barcode sudah pernah ada", Toast.LENGTH_SHORT).show();
                        // DialogExistitem();
                        Intent a = new Intent(getBaseContext(), warningumum.class);
                        a.putExtra("warning", "Barcode Sudah pernah di Scan");
                        startActivity(a);
                    }

                   // btnSave.performClick();
                 //   insertdata();
                  //  loadDataLokal();
                 //   gambarDatakeRecyclerView();

                } else {
                    Intent a = new Intent(getBaseContext(), warningumum.class);
                    a.putExtra("warning", "Barcode salah format");
                    startActivity(a);
                    return;
                }
            }
        }
    }

    //ambil data sever volley
    private void insertdata() {



        String tebal = textTempTebal.getText().toString();
        String sgd = textTempSGD.getText().toString();
        String panjang = editPanjang.getText().toString();
        String lebar = editLebar.getText().toString();

        String barcodex = editBarcode.getText().toString();





        int tagin = 1;

        SQLiteDatabase db2 = adb_master.getReadableDatabase();

        String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel + " WHERE "
                + ADB_Master.MyColumns_newbarcode.idnn_link1 + " = '" + textIdno_link.getText().toString() + "' and " + ADB_Master.MyColumns_newbarcode.barcode + " = '" + barcodex + "'";
        //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
        Cursor c2 = db2.rawQuery(selectQuery2, null);

        int hit1 = c2.getCount();
         Toast.makeText(this, "hit : " + hit1+textIdno_link.getText().toString() +"---"+barcodex, Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "tbl:" + tebal+"sgd:" + sgd+"pnj:" + panjang+"lbr:" + lebar+"lok:" + lokasi+"noref:" + noref+"barcode:" +barcodex, Toast.LENGTH_SHORT).show();


        if (hit1 > 0) {

            //Toast.makeText(this, "Barcode sudah pernah ada", Toast.LENGTH_SHORT).show();
            // DialogExistitem();
            Intent a = new Intent(getBaseContext(), warningumum.class);
            a.putExtra("warning", "Barcode Sudah pernah di Scan ya");
            startActivity(a);

            return;
        } else {

         /*   String insertSQL = "INSERT INTO tbl_barctran \n" +
                    "(barcode,noref,lokoven,namaoven,typetran,tebal,sgd,panjang,lebar,temp5,tagtran,namaoven2 )\n" +
                    "VALUES \n" +
                    "(?, ?, ? ,?,?,?,?,?,?,?,?,?);";
            mDatabase.execSQL(insertSQL, new String[]{barcodex, noref, lokasi, "x","master", tebal, sgd, panjang, lebar, "t5", "1", "x"});
*/
            SQLiteDatabase create = adb_master.getWritableDatabase();

            //isi data master
            ContentValues values2 = new ContentValues();
            values2.put(ADB_Master.MyColumns_newbarcode.barcode,barcodex );
            values2.put(ADB_Master.MyColumns_newbarcode.idnn_link1,Integer.valueOf(textIdno_link.getText().toString() ));
            values2.put(ADB_Master.MyColumns_newbarcode.group_sw,textTempSwgroup.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode.group_panjang,textTempShort.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode.tebal,Double.valueOf(tebal) );
            values2.put(ADB_Master.MyColumns_newbarcode.sgd,sgd );
            values2.put(ADB_Master.MyColumns_newbarcode.panjang,Double.valueOf(panjang) );
            values2.put(ADB_Master.MyColumns_newbarcode.lebar,Double.valueOf(lebar) );
            values2.put(ADB_Master.MyColumns_newbarcode.headerbarcode,textHbarcode.getText().toString());
            values2.put(ADB_Master.MyColumns_newbarcode.tagtran,2 );//2 masuk 1 update


           create.insert(ADB_Master.MyColumns_newbarcode.NamaTabel, null, values2);

            MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.bell);
            mediaPlayer.start();
            //fab.performClick();
            Toast.makeText(this, "Add Barcode Succesfully", Toast.LENGTH_SHORT).show();
           // Toast.makeText(this, "hit : " + hit1+"xxx"+textIdno_link.getText().toString() +"---"+barcodex, Toast.LENGTH_SHORT).show();
            finish();
        }


    }




    private void updatedata() {



        String tebal = textTempTebal.getText().toString();
        String sgd = textTempSGD.getText().toString();
        String panjang = editPanjang.getText().toString();
        String lebar = editLebar.getText().toString();
       // String lokasi = textLokasi.getText().toString();
        String idno_link = textIdno_link.getText().toString();
        String barcodex = editBarcode.getText().toString();
      //  String nolog = editNolog.getText().toString();



        int tagin = 1;

        SQLiteDatabase db2 = adb_master.getReadableDatabase();

        String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel + " WHERE "
                + ADB_Master.MyColumns_newbarcode.idnn_link1 + " = '" + textIdno_link.getText().toString() + "' and " + ADB_Master.MyColumns_newbarcode.barcode + " = '" + barcodex + "'";
        //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
        Cursor c2 = db2.rawQuery(selectQuery2, null);
        int hit1 = c2.getCount();
          Toast.makeText(this, "hit : " + hit1+idno_link+barcodex, Toast.LENGTH_SHORT).show();
        // Toast.makeText(this, "tbl:" + tebal+"sgd:" + sgd+"pnj:" + panjang+"lbr:" + lebar+"lok:" + lokasi+"noref:" + noref+"barcode:" +barcodex, Toast.LENGTH_SHORT).show();


        if (hit1 > 0) {

            //Toast.makeText(this, "Barcode sudah pernah ada", Toast.LENGTH_SHORT).show();
            // DialogExistitem();
            Intent a = new Intent(getBaseContext(), warningumum.class);
            a.putExtra("warning", "Barcode Sudah pernah di Scan ya");
            startActivity(a);

            return;
        } else {
            //  final String namacari = cari1.getText().toString();

         /*   String insertSQL = "INSERT INTO tbl_barctran \n" +
                    "(barcode,noref,lokoven,namaoven,typetran,tebal,sgd,panjang,lebar,temp5,tagtran,namaoven2 )\n" +
                    "VALUES \n" +
                    "(?, ?, ? ,?,?,?,?,?,?,?,?,?);";
            mDatabase.execSQL(insertSQL, new String[]{barcodex, noref, lokasi, "x","master", tebal, sgd, panjang, lebar, "t5", "1", "x"});
*/
            SQLiteDatabase createdel = adb_master.getWritableDatabase();

            String del1 = "delete  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel+ " WHERE "
                    + ADB_Master.MyColumns_newbarcode.idno + " = " + getidno;
            createdel.execSQL(del1);

            insertdata();
        }


    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_del, menu);

        return true;

    }


    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_del) {


DialogDelBarcTran();

           // finish();

            return true;



        }



        return super.onOptionsItemSelected(item);

    }


    private void DialogDelBarcTran() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warningdelbarctran, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);

        String d1 = "Data " + editBarcode.getText().toString();
        dialog.setTitle(d1);

        //  txt_nama    = (EditText) dialogView.findViewById(R.id.txt_nama);
        // txt_usia    = (EditText) dialogView.findViewById(R.id.txt_usia);
        //txt_alamat  = (EditText) dialogView.findViewById(R.id.txt_alamat);
        //txt_status = (EditText) dialogView.findViewById(R.id.txt_status);

        // kosong();

        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                //    nama    = txt_nama.getText().toString();
                //  usia    = txt_usia.getText().toString();
                //alamat  = txt_alamat.getText().toString();
                //status = txt_status.getText().toString();
                // deltrans();
                //  sqldelnoref(textnoreff.getText().toString());
                //  inserttoserver(textnoreff.getText().toString());
                //  Intent bukadialog = new Intent(NURUploadBarcode.this,NURpono.class);
                //   final String notrans3=notrans.getText().toString();
                //   Toast.makeText(getBaseContext(),"No Transaksi: "+notrans3, Toast.LENGTH_SHORT).show();
                //    bukadialog.putExtra("posno","posno");
                // startActivityForResult(bukadialog, REQUEST_CODE_EDIT);
                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                // sqldelnoref(textnoreff.getText().toString());
                //  inserttoserver(textnoreff.getText().toString());
                delbarctran();




                    /*  sqldelnoref(textnoreff.getText().toString());
                      if (hitbarc>0) {
                          inserttoserver(textnoreff.getText().toString());
                      }
*/
                dialog.dismiss();
                 finish();
                return;
            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void delbarctran()
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String del1 = "delete  FROM " + ADB_Master.MyColumns_newbarcode.NamaTabel + " WHERE "
                + ADB_Master.MyColumns_newbarcode.idnn_link1 + " = '" + textIdno_link.getText().toString() + "' and " + ADB_Master.MyColumns_newbarcode.barcode + " = '" + editBarcode.getText().toString() + "'";
        db.execSQL(del1);
    }

}
