package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AutoCompleteTextView;

import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WrhParisLokal extends AppCompatActivity {
    List<String> users=new ArrayList<>();
    List<BarangListReviewBarcode> BarangListReview=new ArrayList<BarangListReviewBarcode>();
    ArrayAdapter<String> adapter;
    public static final String TAG_JSON_ARRAY="result";
    private RecyclerView mRecyclerView;
    public static String lastscan,ValBarcode,NoGroupSimpan;
    private BarangAdapterSawmillExt rvAdapter;
    private BarangAdapterListBarcodeReveiw rvAdapterReview;
    FloatingActionButton fab;
    private ArrayList<BarangMasterUser> barangCabangArrayList;
    private ArrayList<String> names = new ArrayList<String>();

    private RecyclerView.LayoutManager mLayoutManager;

    private Context context = WrhParisLokal.this;

    private static  final int REQUEST_CODE_ADD =1;

    private static  final int REQUEST_CODE_EDIT =2;

    private List<BarangSawmillExt> barangList = new ArrayList<BarangSawmillExt>();
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;

    private ProgressDialog pDialog;
    private EditText cari1,editoven,editnogroup;


    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    ImageButton butbarcodeher;
    private static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;
    TextView t1,textTampung,textTampung2,textTampung3,textnoreff,texttempSpfilter,texttempSpfilter2,texttempTipekayu,texttempTipekayu2;
    Button butProcessList,butProcessGroup,butListRev,butClose,butListBarcode,butpos1,butovenname,butImportsupplier,butImportdata,butProcfilter,butProcclear,butManualbarc,butProcesstab,butProcesstab_completed,butProcesstab_post;
    private SimpleDateFormat dateFormatter;
    Spinner spnOven,spnSpfilter,spnTipekayu;
    EditText editFilterm3,editFilternolog,editBarcode;
private String wrhtype_get;
public static String typebarcode,typepost,barcodeambil;

    Button butprocwrh;

  //  public static String typeall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrh_parislokal);

        MainActivity.module_buffer="saw_in";




        typebarcode="master";

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Intent wrhsatu=getIntent();
        final String judul=wrhsatu.getStringExtra("judul");
          wrhtype_get=wrhsatu.getStringExtra("wrhtype");
         // spnSpfilter=findViewById(R.id.spnSpfilter);
        //  spnTipekayu=findViewById(R.id.spnTipekayu);
       /*   texttempSpfilter=findViewById(R.id.textTempSpfilter);
          texttempSpfilter2=findViewById(R.id.textTempSpfilter2);
          texttempTipekayu=findViewById(R.id.textTempTipekayu);
          texttempTipekayu2=findViewById(R.id.textTempTipekayu2);*/

       //   editFilterm3=findViewById(R.id.editFilterm3);
       //   editFilternolog=findViewById(R.id.editNolog);

    //    butProcfilter=findViewById(R.id.butProcessfilter);
    //    butProcclear=findViewById(R.id.butProcessclear);



      /*  android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sawmill");
        setSupportActionBar(toolbar);
*/




    /*    fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {


                    // SawmillSub_Checkin.typebarcode="mastercheckin";
                    SawmillSub_LOG.typebarcode="checklog";
                    //ambilremarks = editremarks.getText().toString();
                    //Toast.makeText(getBaseContext(), "Load barcode", Toast.LENGTH_LONG).show();
                    IntentIntegrator integrator = new IntentIntegrator(WrhParisLokal.this);
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

        Note :
        Transakasi process : 2
        Transaksi Completed : 1

*/


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

        editBarcode=findViewById(R.id.editBarcode);
        butprocwrh=findViewById(R.id.butprocwrh);
        butImportdata=findViewById(R.id.butImportdata);
        butImportsupplier=findViewById(R.id.butImportsupplier);
        butManualbarc=findViewById(R.id.butManualbarc);
        butProcesstab=findViewById(R.id.butProcesstab);
        butProcesstab_completed=findViewById(R.id.butProcesstab_completed);
        butProcesstab_post=findViewById(R.id.butProcesstab_post);
        butProcessGroup=findViewById(R.id.butProcesstab_group);
        butListBarcode=findViewById(R.id.butListBarcode);
        butListRev=findViewById(R.id.butProcesstab_rev);
        butProcessList=findViewById(R.id.butProcesstab_list);
        butClose=findViewById(R.id.butCloseLokal);




        editnogroup=findViewById(R.id.NoGroup);

        editnogroup.setOnClickListener(new DoubleClickListener() {

         				@Override
 				public void onDoubleClick() {
 					// double-click code that is executed if the user double-taps
 					// within a span of 200ms (default).
                          //  Toast.makeText(getBaseContext(),"Test",Toast.LENGTH_LONG).show();
                            Intent a =new Intent(getBaseContext(),LihatNOSKAU.class);
                            startActivityForResult(a,9898);
 				}
 			});


      //  editnogroup.setOnClickListener(new DoubleTapListener(this));


//        editnogroup.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//               // loaddatanik();
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                // TODO Auto-generated method stub
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//                // TODO Auto-generated method stub
//            }
//        });
//
//        if (editnogroup.getText().toString().isEmpty())
//        {
//            editnogroup.setText(ValBarcode);
//        }

//     Button btn = new Button(this);
//       editnogroup.setOnClickListener( new DoubleClick(new DoubleClickListener() {
//            @Override
//            public void onSingleClick(View view) {
//
//                // Single tap here.
//            }
//
//            @Override
//            public void onDoubleClick(View view) {
//
//                Toast.makeText(getBaseContext(),"Test",Toast.LENGTH_LONG).show();
//            }
//        });


        butProcessList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(getBaseContext(),"Test",Toast.LENGTH_LONG).show();
                Intent a =new Intent(getBaseContext(),LihatNOSKAU.class);
                startActivityForResult(a,9898);
            }
        });


        butProcessGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValBarcode=editnogroup.getText().toString();

             //     tambahroup();
                if (editnogroup.getText().toString().isEmpty())
                {
                 Toast.makeText(getBaseContext(),"No Group Harus diisi dahulu",Toast.LENGTH_LONG).show();
                }else {
                    importdataserver2(editnogroup.getText().toString());
                }
            }
        });


        butListRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValBarcode=editnogroup.getText().toString();
                Intent a=new Intent(getBaseContext(),LihatBarcodeReview.class);
                startActivity(a);
            }
        });

        butClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        butListBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // NoGroupSimpan=editnogroup.getText().toString();
                ValBarcode=editnogroup.getText().toString(); //Nomor Group
             //   Toast.makeText(getBaseContext(),"noskau"+ValBarcode,Toast.LENGTH_LONG).show();
                Intent barc =new Intent(getBaseContext(),listbarcode.class);
                startActivityForResult(barc,213);
                typepost="";
            }
        });

        butProcesstab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Retrieving Data...");
                showDialog();
                loadDataLokal("1");
                gambarDatakeRecyclerView();
                typepost="";
            }
        });


        butProcesstab_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Retrieving Data...");
                showDialog();
                loadDataLokal("2");
                gambarDatakeRecyclerView();
                typepost="";

            }
        });

        butProcesstab_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog.setMessage("Retrieving Data...");
                showDialog();
                loadDataLokal("3");
                gambarDatakeRecyclerView();
                typepost="POST";

            }
        });

        editBarcode.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    butManualbarc.performClick();

                }
                return false;
            }
        });



        butManualbarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              loadDataLokal("x");
              gambarDatakeRecyclerView();

                new CountDownTimer(250, 1000) {
                    public void onFinish() {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.bell);
                        mediaPlayer.start();
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }
                }.start();
            }
        });



        butImportdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               Dialogimport();

            }
        });

        butImportsupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                importdsupplier();

            }
        });


        butprocwrh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getBaseContext(),NURUploadBarcodeLokal.class);
                a.putExtra("wrhtype","master");
                a.putExtra("noref","master");
                startActivityForResult(a,111);
            }
        });

      //  WrhParisOut.typeall="out";
     //   loadDatafilter();
     //   getSupplier();
     //   spnTipekayu.setEnabled(false);
        loadDataLokal("x");
     /*   getSupplier();
        getTipekayu();
        spnTipekayu.setEnabled(false);

        loadDataLokal();*/
    }




    private void tambahroup(){

        SQLiteDatabase dbtrans = adb_master.getWritableDatabase();

        //delete awal data di tbl barang
        String del1 = "delete from toko_profile where nama_toko='"+editnogroup.getText().toString()+"'";
        dbtrans.execSQL(del1);

         String sql1="insert into toko_profile ('nama_toko','alamat_toko','no_hp_toko') values ('"+editnogroup.getText().toString()+"','1','1')" ;
        dbtrans.execSQL(sql1);

//        SQLiteDatabase create2 = adb_trans.getWritableDatabase();
//         //String sqldel="delete from trans1 where notrans='"+editnogroup.getText().toString()+"'" ;
//         String sqldel="delete from "+ADB_Trans.MyColumns_trans1.NamaTabel ;
//         create2.execSQL(sqldel);

//        SQLiteDatabase createdel = adb_master.getWritableDatabase();
//
//        //sawmil papan
//        String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
//        createdel.execSQL(del1);

//        SQLiteDatabase create = adb_master.getWritableDatabase();
//        String delstipekayu = "delete  FROM " + ADB_Master.MyColumns_sawmill_datafilter.NamaTabel;
//        create.execSQL(delstipekayu);
//
//         String sql1="insert into trans1 values ('"+editnogroup.getText().toString()+"')" ;
//        create2.execSQL(sql1);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyaction = event.getAction();
        if(keyaction == KeyEvent.ACTION_DOWN )
        {
            String keycode = event.getCharacters();
            // Toast toast = Toast.makeText(this,String.valueOf(event.getKeyCode()), Toast.LENGTH_LONG);
            //toast.show();

                if (event.getKeyCode()==103)
            {
                //  Toast toast = Toast.makeText(this,"12285", Toast.LENGTH_LONG);
                // toast.show();
                //  butmanualbarc.performClick();
                editBarcode.setText("");
                editBarcode.requestFocus();
            }
        }
        return super.dispatchKeyEvent(event);
    }



    private void Dialogimport() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warning, null);


        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setMessage("Anda ingin import data baru ? ");
        dialog.setTitle("Import Warning");

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

                importdataserver();
                // sqldelnoref(textnoreff.getText().toString());
                //  updatebarctran(ambilnoref);


                //baru baru ini
                //   Intent a2 = new Intent(getBaseContext(), warningloading.class);
                //  startActivity(a2);

                //    insertto_test(textIdno_link.getText().toString());
                // inserttoserver(textnoreff.getText().toString());
                //  Intent bukadialog = new Intent(NURUploadBarcode.this,NURpono.class);
                //   final String notrans3=notrans.getText().toString();
                //   Toast.makeText(getBaseContext(),"No Transaksi: "+notrans3, Toast.LENGTH_SHORT).show();
                //    bukadialog.putExtra("posno","posno");
                // startActivityForResult(bukadialog, REQUEST_CODE_EDIT);
                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                //  finish();
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


    private void loadDatafilter() {

        //users=adb_master.getSpsupplier();

       // SQLiteDatabase create = adb_master.getWritableDatabase();

        SQLiteDatabase db=adb_master.getReadableDatabase();

        String sql = "select supplier,m3,no_log FROM " + ADB_Master.MyColumns_sawmill_datafilter.NamaTabel;
        Cursor c=db.rawQuery(sql,null);
        c.moveToFirst();
        int jum=c.getCount();
      //  Toast.makeText(getBaseContext(),"Nilai : "+c.getString(0),Toast.LENGTH_LONG).show();
        //tiep_kayu
       texttempSpfilter.setText(c.getString(0));
        texttempSpfilter2.setText(c.getString(0));
       editFilternolog.setText(c.getString(2));
       editFilterm3.setText(c.getString(1));

    }


    private void resetDatafilter() {

        //users=adb_master.getSpsupplier();

        SQLiteDatabase create = adb_master.getWritableDatabase();


        String delstipekayu = "delete  FROM " + ADB_Master.MyColumns_sawmill_datafilter.NamaTabel;
        create.execSQL(delstipekayu);
        //tiep_kayu
        ContentValues valuestpkayu = new ContentValues();
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.supplier,"");
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.m3, "");
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.no_log, "");
        create.insert(ADB_Master.MyColumns_sawmill_datafilter.NamaTabel, null, valuestpkayu);



    }

    private void simpanDatafilter() {

        //users=adb_master.getSpsupplier();

        SQLiteDatabase create = adb_master.getWritableDatabase();


        String delstipekayu = "delete  FROM " + ADB_Master.MyColumns_sawmill_datafilter.NamaTabel;
        create.execSQL(delstipekayu);
        //tiep_kayu
        ContentValues valuestpkayu = new ContentValues();
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.supplier, texttempSpfilter.getText().toString());
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.m3, editFilterm3.getText().toString());
        valuestpkayu.put(ADB_Master.MyColumns_sawmill_datafilter.no_log, editFilternolog.getText().toString());
        create.insert(ADB_Master.MyColumns_sawmill_datafilter.NamaTabel, null, valuestpkayu);



    }


    private void getSupplier() {

        users=adb_master.getSpsupplier();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnSpfilter.setAdapter(adapter);
        String compareValue = texttempSpfilter2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnSpfilter.setSelection(spinnerPosition);
        }


    }


    private void getTipekayu() {

        users=adb_master.getTipekayu();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnTipekayu.setAdapter(adapter);
        String compareValue = texttempTipekayu2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnTipekayu.setSelection(spinnerPosition);
        }



    }



    private void loadDataLokal2(){

     /*   Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        final String namacari=cari1.getText().toString();*/


       /* String item1=cari1.getText().toString();
        String lokoven=textTampung2.getText().toString();
        String namaoven=textTampung3.getText().toString();
        String noref=textnoreff.getText().toString();*/

        //   t1=(TextView) findViewById(R.id.jumbarc2);


        //TextView jdljum_log=findViewById(R.id.jdl_jumlog);

      //  Toast.makeText(getBaseContext(),"nilai : "+texttempSpfilter.getText().toString()+"-"+editFilterm3.getText().toString()+"-"+editFilternolog.getText().toString(),Toast.LENGTH_LONG).show();

     /*   SQLiteDatabase db=adb_master.getReadableDatabase();
        String sql="select * from tbl_headerbarcode where nama_spp like '%"+texttempSpfilter.getText().toString()+"%' and volume like '%"+editFilterm3.getText().toString()+"%' and no_log<9999 and panjang>0 and no_log like '%"+editFilternolog.getText().toString()+"%'";
        Cursor c=db.rawQuery(sql,null);
        int jum=c.getCount();*/
      //  Toast.makeText(getBaseContext(),"Jum :"+jum,Toast.LENGTH_LONG).show();
       // jdljum_log.setText("Sawmill List of LOG (Jumlah : "+jum +") LOGS");

   /*     if (texttempSpfilter.getText().toString().equals("Supplier"))
        {
            texttempSpfilter.setText(null);
        }
*/
        // t1.setText("Jumlah Barcode : "+String.valueOf(jum));
        //  barangListPOS = adb_user.getSemuaUser();
      //  barangList = adb_master.getHeader(editBarcode.getText().toString());
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();



    }




    private void loadDataLokal(String pil) {

     /*   Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        final String namacari=cari1.getText().toString();*/

        pDialog.setMessage("Ambil Data...");
        showDialog();
       /* String item1=cari1.getText().toString();
        String lokoven=textTampung2.getText().toString();
        String namaoven=textTampung3.getText().toString();
        String noref=textnoreff.getText().toString();*/

        //   t1=(TextView) findViewById(R.id.jumbarc2);


        //TextView jdljum_log=findViewById(R.id.jdl_jumlog);


        // Toast.makeText(getBaseContext(),"nilai : "+texttempSpfilter.getText().toString()+"-"+editFilterm3.getText().toString()+"-"+editFilternolog.getText().toString(),Toast.LENGTH_LONG).show();


         SQLiteDatabase db=adb_master.getReadableDatabase();
      /*  String sql="select * from tbl_headerbarcode where nama_spp like '%"+texttempSpfilter.getText().toString()+"%' and volume like '%"+editFilterm3.getText().toString()+"%' and no_log<9999 and panjang>0 and no_log like '%"+editFilternolog.getText().toString()+"%'";
        Cursor c=db.rawQuery(sql,null);
        int jum=c.getCount();*/
        //   Toast.makeText(getBaseContext(),"Jum :"+jum,Toast.LENGTH_LONG).show();
        //  jdljum_log.setText("Sawmill List of LOG (Jumlah : "+jum +") LOGS");


        // t1.setText("Jumlah Barcode : "+String.valueOf(jum));
        //  barangListPOS = adb_user.getSemuaUser();

        String sql = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ ADB_Master.MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran='2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by b.tagtran desc";
        Cursor c=db.rawQuery(sql,null);
        int jum =c.getCount();


        String sql_comp = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ ADB_Master.MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran!='2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by b.tagtran desc";
        Cursor c_comp=db.rawQuery(sql_comp,null);
        int jum_comp =c_comp.getCount();

//Completed Data
        //kasih data NOGRoup


        if (pil.equals("1")) {
            barangList = adb_master.getHeaderTag();
            if (jum==0)
            {
                Intent a = new Intent(getBaseContext(), warningumum.class);
                a.putExtra("warning", "Data tidak ada");
                startActivity(a);

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
                mediaPlayer.start();
            }
        } else if ((pil.equals("2"))) {
            barangList = adb_master.getHeaderTag_complete();
            Toast.makeText(getBaseContext(),"completed :"+jum_comp,Toast.LENGTH_LONG).show();
            if (jum_comp==0)
            {
                Intent a = new Intent(getBaseContext(), warningumum.class);
                a.putExtra("warning", "Data tidak ada");
                startActivity(a);

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
                mediaPlayer.start();
            }
        } else if ((pil.equals("3"))) {
            barangList = adb_master.getHeaderTag_post();
            Toast.makeText(getBaseContext(),"Sisa Post :"+jum,Toast.LENGTH_LONG).show();
            if (jum==0)
            {
                Intent a = new Intent(getBaseContext(), warningumum.class);
                a.putExtra("warning", "Data tidak ada");
                startActivity(a);

                MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
                mediaPlayer.start();
            }
        }else



            {

           if (editBarcode.getText().toString().isEmpty()){
          // Toast.makeText(getBaseContext(),"Data Barcode belum ada",Toast.LENGTH_LONG).show();
           }else {



                   String selectBarcode = "SELECT sawmill FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel + " where barcodeheader ='" + editBarcode.getText().toString() + "'";
                   Cursor barcode = db.rawQuery(selectBarcode, null);

                   if (barcode.getCount()>0)
                   {
                       barcode.moveToFirst();
                       editnogroup.setText(barcode.getString(0));
                       ValBarcode=barcode.getString(0);
                   }



                   String sql2 = "select *  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel + " where barcodeheader='" + editBarcode.getText().toString() + "'";
                   Cursor c2 = db.rawQuery(sql2, null);
                   int jumbarcode = c2.getCount();

                   if (jumbarcode == 0) {
                       importcheckbarcode(editBarcode.getText().toString());
                   }







//               if (jumbarcode > 1) {
//                   butManualbarc.performClick();
//               }
//               if (jumbarcode == 0) {
//                   Intent a = new Intent(getBaseContext(), warningumum.class);
//                   a.putExtra("warning", "Data LOG belum ada,Mohon hubungi fera");
//                   startActivity(a);
//                   MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
//                   mediaPlayer.start();
//               }
           }

            barangList = adb_master.getHeader(editBarcode.getText().toString());
        }
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();

        ///reload again if record more 1 record
//        String sql2 = "select *  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel + " where barcodeheader='" + editBarcode.getText().toString() + "'";
//        Cursor c2 = db.rawQuery(sql2, null);
//        int jumbarcode2 = c2.getCount();
//
//        if (jumbarcode2 > 1) {
//            butManualbarc.performClick();
//        }


//
//adb_master.close();
//adb_trans.close();
     //   hideDialog();
//db.close();

        new CountDownTimer(250, 1000) {
            public void onFinish() {
                hideDialog();
            }

            public void onTick(long millisUntilFinished) {
                // millisUntilFinished    The amount of time until finished.
            }


        }.start();


    }






    private void importcheckbarcode(final String barcodecheck) {



//        SQLiteDatabase createdel = adb_master.getWritableDatabase();
//
//        String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
//        createdel.execSQL(del1);

       //final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/listbarckoreksi.php";
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
                processResponsePOS_3(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                      //  hideDialog();

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

               params.put("barcode",barcodecheck);
                //   params.put("typetran",statustran.getText().toString());

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        //  Toast.makeText(this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();
    }


    private void processResponsePOS_3(String response){

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

                barcodeambil=obj.getString("skau");
                Toast.makeText(this,"barcode ambil "+barcodeambil,Toast.LENGTH_LONG).show();
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

//                SQLiteDatabase create = adb_master.getWritableDatabase();
//
//                // String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
//                //   create.execSQL(del1);
//                //Cursor del2 = create.rawQuery(del1, null);
//                //isisawmill lokasi
//                ContentValues values = new ContentValues();
//                values.put(ADB_Master.MyColumns_headerbarcode.volume,obj.getString("volume"));
//                values.put(ADB_Master.MyColumns_headerbarcode.idno_link1, obj.getString("line_no")); //linno
//                values.put(ADB_Master.MyColumns_headerbarcode.idno_link2, obj.getString("IDNO")); //bsasenourut
//                values.put(ADB_Master.MyColumns_headerbarcode.sawmill, obj.getString("NO_SKAU"));
//                values.put(ADB_Master.MyColumns_headerbarcode.nama_spp, obj.getString("supplier_name"));
//                values.put(ADB_Master.MyColumns_headerbarcode.tipe_kayu, obj.getString("tipe_kayu"));
//                values.put(ADB_Master.MyColumns_headerbarcode.no_log, obj.getString("no_log"));
//                values.put(ADB_Master.MyColumns_headerbarcode.panjang, obj.getString("panjang"));
//                values.put(ADB_Master.MyColumns_headerbarcode.lebar, obj.getString("lebar"));
//                values.put(ADB_Master.MyColumns_headerbarcode.tinggi, obj.getString("tinggi"));
//                values.put(ADB_Master.MyColumns_headerbarcode.diameter, obj.getString("diameter"));
//                values.put(ADB_Master.MyColumns_headerbarcode.tgl_beli, obj.getString("doc_date"));
//                values.put(ADB_Master.MyColumns_headerbarcode.barcodeheader, obj.getString("barcodeheader"));
//                create.insert(ADB_Master.MyColumns_headerbarcode.NamaTabel, null, values);

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
//        loadDataLokal("x");
//        Intent a = new Intent(getBaseContext(), warningumum.class);
//        a.putExtra("warning", "Import Data Selesai");
//        startActivity(a);
        importdataserver2(barcodeambil);
    }




    private void importdataserver2(final String noskaulast) {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel+" where sawmill='"+editnogroup.getText().toString()+"'";
        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdataferakoreksi.php";
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
                processResponsePOS_2(response);
                //  gambarDatakeRecyclerView();
//                pDialog.hide();
                hideDialog();
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
                            // DialogErrorNetwork();
                            Toast.makeText(getBaseContext(),
                                    "Oops. Network error",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. Server error",
                                    Toast.LENGTH_LONG).show();
                        }else if (error instanceof TimeoutError) {

                            Toast.makeText(getBaseContext(),
                                    "Oops. Time out error",
                                    Toast.LENGTH_LONG).show();
                        }

                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("noskau",noskaulast);
                //   params.put("typetran",statustran.getText().toString());

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);
        //  Toast.makeText(this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();
    }


    private void processResponsePOS_2(String response){

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
                values.put(ADB_Master.MyColumns_headerbarcode.volume,obj.getString("volume"));
                values.put(ADB_Master.MyColumns_headerbarcode.idno_link1, obj.getString("line_no")); //linno
                values.put(ADB_Master.MyColumns_headerbarcode.idno_link2, obj.getString("IDNO")); //bsasenourut
                values.put(ADB_Master.MyColumns_headerbarcode.sawmill, obj.getString("NO_SKAU"));
                values.put(ADB_Master.MyColumns_headerbarcode.nama_spp, obj.getString("supplier_name"));
                values.put(ADB_Master.MyColumns_headerbarcode.tipe_kayu, obj.getString("tipe_kayu"));
                values.put(ADB_Master.MyColumns_headerbarcode.no_log, obj.getString("no_log"));
                values.put(ADB_Master.MyColumns_headerbarcode.panjang, obj.getString("panjang"));
                values.put(ADB_Master.MyColumns_headerbarcode.lebar, obj.getString("lebar"));
                values.put(ADB_Master.MyColumns_headerbarcode.tinggi, obj.getString("tinggi"));
                values.put(ADB_Master.MyColumns_headerbarcode.diameter, obj.getString("diameter"));
                values.put(ADB_Master.MyColumns_headerbarcode.tgl_beli, obj.getString("doc_date"));
                values.put(ADB_Master.MyColumns_headerbarcode.barcodeheader, obj.getString("barcodeheader"));
                create.insert(ADB_Master.MyColumns_headerbarcode.NamaTabel, null, values);

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
//        loadDataLokal("x");
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
      // butManualbarc.performClick();
    }




    private void importdataserver() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

         String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
           createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importdatafera.php";
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
                values.put(ADB_Master.MyColumns_headerbarcode.volume,obj.getString("volume"));
                values.put(ADB_Master.MyColumns_headerbarcode.idno_link1, obj.getString("line_no")); //linno
                values.put(ADB_Master.MyColumns_headerbarcode.idno_link2, obj.getString("IDNO")); //bsasenourut
                values.put(ADB_Master.MyColumns_headerbarcode.sawmill, obj.getString("NO_SKAU"));
                values.put(ADB_Master.MyColumns_headerbarcode.nama_spp, obj.getString("supplier_name"));
                values.put(ADB_Master.MyColumns_headerbarcode.tipe_kayu, obj.getString("tipe_kayu"));
                values.put(ADB_Master.MyColumns_headerbarcode.no_log, obj.getString("no_log"));
                values.put(ADB_Master.MyColumns_headerbarcode.panjang, obj.getString("panjang"));
                values.put(ADB_Master.MyColumns_headerbarcode.lebar, obj.getString("lebar"));
                values.put(ADB_Master.MyColumns_headerbarcode.tinggi, obj.getString("tinggi"));
                values.put(ADB_Master.MyColumns_headerbarcode.diameter, obj.getString("diameter"));
                values.put(ADB_Master.MyColumns_headerbarcode.tgl_beli, obj.getString("doc_date"));
                values.put(ADB_Master.MyColumns_headerbarcode.barcodeheader, obj.getString("barcodeheader"));
                create.insert(ADB_Master.MyColumns_headerbarcode.NamaTabel, null, values);

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
        loadDataLokal("x");
        Intent a = new Intent(getBaseContext(), warningumum.class);
        a.putExtra("warning", "Import Data Selesai");
        startActivity(a);
    }




    private void importdsupplier() {



        SQLiteDatabase createdel = adb_master.getWritableDatabase();

        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel;
        createdel.execSQL(del1);
        SQLiteDatabase create = adb_master.getWritableDatabase();

        // String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
        //   create.execSQL(del1);
        //Cursor del2 = create.rawQuery(del1, null);
        //isisawmill lokasi
        ContentValues values = new ContentValues();
        values.put(ADB_Master.MyColumns_sawmill_basesupplier.supplier,"Supplier");
        create.insert(ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel, null, values);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/importsupplierkayu.php";
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
                processResponsex1(response);
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


    private void processResponsex1(String response){

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
                values.put(ADB_Master.MyColumns_sawmill_basesupplier.supplier,obj.getString("supplier_name"));
                create.insert(ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel, null, values);

                Toast.makeText(this,"Import Completed ",Toast.LENGTH_LONG).show();

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
       // loadDataLokal();
        getSupplier();
    }





    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }



        private void gambarDatakeRecyclerView(){

        rvAdapter = new BarangAdapterSawmillExt(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                     //   Intent intent2=getIntent();
                      //  final String kodecabang1=intent2.getStringExtra("kodecabang");

                        pDialog.setMessage("Retrieve Data...");
                        showDialog();

                        BarangSawmillExt barang = rvAdapter.getItem(position);

                        Intent intent = new Intent(WrhParisLokal.this, NURUploadBarcodeLokal.class);
                        intent.putExtra("wrhtype",wrhtype_get);
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

//      if(data.hasExtra(null))
//      {
//          Toast.makeText(getBaseContext(),"Data Null",Toast.LENGTH_LONG).show();
//      }


     //   if (requestCode==213)

        if (resultCode==6767)
        {
            try {
                editBarcode.setText("");
                editBarcode.setText(data.getStringExtra("hasilbarcode"));
                Toast.makeText(getBaseContext(),data.getStringExtra("hasilbarcode"),Toast.LENGTH_LONG).show();

                new CountDownTimer(250, 1000) {
                    public void onFinish() {
                        hideDialog();
                      butManualbarc.performClick();
                    }

                    public void onTick(long millisUntilFinished) {
                        // millisUntilFinished    The amount of time until finished.
                    }


                }.start();

            } catch(NullPointerException e)
            {
                System.err.println("Null Exception");
            }
        }


        if (typepost=="POST")
        {
            loadDataLokal("3");
        }else
        {
            loadDataLokal("x");
        }
      /*  loadDatafilter();
        loadDataLokal();
        getSupplier();
        getTipekayu();

        spnTipekayu.setEnabled(false);

*/
     /*   if (scanningResult != null) {

            // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));
            //insertdata();

            if (scanningResult.getContents() != null) {
                //   Toast.makeText(this, "hASIL cANNING"+scanningResult.getContents(), Toast.LENGTH_SHORT).show();

                editBarcode.setText(scanningResult.getContents());

                lastscan = scanningResult.getContents();

                //  loadDataLokal();
                //   gambarDatakeRecyclerView();
                //we have a result
                //cari1.setText(scanningResult.getContents());
                // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));

                    loadDataLokal("x");
                    gambarDatakeRecyclerView();

            }
        }

*/

        /*    if (resultCode == RESULT_OK && requestCode == 111) {

                //editoven.setText(data.getStringExtra("section"));
               // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));

                loadDataLokal();


            }else
            {
                loadDataLokal();
            }
*/


        if (requestCode==9898)
        {
            editnogroup.requestFocus();

            ValBarcode=data.getStringExtra("hasilbalikskau");





                    //hideDialog();
                    editnogroup.setText(ValBarcode);
                    Toast.makeText(getBaseContext(),editnogroup.getText().toString(),Toast.LENGTH_LONG).show();

 return;
        }

    }

 /*   @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_data, menu);

        // MenuItem item = menu.findItem(R.id.action_new_print);
        // shareActionProvider = (ShareActionProvider) item.getActionProvider();
        return true;
        // Return true to display menu
        // return super.onCreateOptionsMenu(menu);
    }



    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=getIntent();
        //   final String kodecabang1=intent.getStringExtra("kodecabang");

        int id = item.getItemId();



        switch (item.getItemId()){





            case (R.id.action_import ):


                importdataserver();


                break;





        }




        return super.onOptionsItemSelected(item);

    }

*/
 boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        return;
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