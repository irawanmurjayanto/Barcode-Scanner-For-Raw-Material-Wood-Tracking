package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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

import org.json.JSONArray;
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

public class SawmillSub_MJPapan extends AppCompatActivity  {
    public static final String TAG_JSON_ARRAY = "result";
    private RecyclerView mRecyclerView;
    private BarangSawmillLOG_MJ barang;
    private BarangAdapterSawmillSub_MJ rvAdapter;
    GPSTracker gps;
    List<String> users=new ArrayList<>();
    ArrayAdapter<String> adapter;
    public static double lattemp;
    public static double longtemp;

    private ArrayList<BarangMasterUser> barangCabangArrayList;
    private ArrayList<String> names = new ArrayList<String>();

    private RecyclerView.LayoutManager mLayoutManager;

    private Context context = SawmillSub_MJPapan.this;

    private static final int REQUEST_CODE_ADD = 1;

    private static final int REQUEST_CODE_EDIT = 2;

    private List<BarangSawmillLOG_MJ> barangList = new ArrayList<BarangSawmillLOG_MJ>();
    private List<BarangBarcodeNOREFLokal> barangListNoref = new ArrayList<BarangBarcodeNOREFLokal>();
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;

    private ProgressDialog pDialog;
    private EditText cari1, editoven;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    ImageButton butbarcodeher;
    private static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;
    TextView t0, t1, textTampung, textTampung2, textTampung3, textnoreff;
    Button butpos1, butovenname, butmanualbarc, butmanualbarcdel, butdelbarctran, butdelnoref, buterror,butposttest;
    private SimpleDateFormat dateFormatter;
    Spinner spnSupplier,spnTipetrans,spnTujuan;
    private String a1;
    public static int hitbarc,tot;
    String[] pilihan = {"Sales", "Putra", "Bad Stock"};
    public static String lastscan, jumjumbar, noreflokal, valprogress,typebarcode;
    FloatingActionButton fab;
    private static int hit;
    public static int tagdata;
    EditText editremarks,editSkau;
    public static String ambilremarks, senddata,ambilnoref;
    ProgressBar simpleprogress;
    private LinearLayout my_view2;
    ViewGroup.LayoutParams params;
    View my_view;
    boolean isUp;
    TextView nilaiof,textTempSupplier,textTempSupplier2,textTempTipetrans,textTempTipetrans2,textLokasi,textTemptujuan1,textTemptujuan2;
    TextView textLokasihasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sawmill_mjpapan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        spnSupplier = (Spinner) findViewById(R.id.spnSupplier);
        spnTipetrans = (Spinner) findViewById(R.id.spnTipetrans);
        spnTujuan = (Spinner) findViewById(R.id.spnTujuan);

        textTampung = (TextView) findViewById(R.id.textTampung);
        textTampung2 = (TextView) findViewById(R.id.textTampung2);
        textTampung3 = (TextView) findViewById(R.id.textTampung3);
        textTempSupplier = (TextView) findViewById(R.id.textTempSupplier);
        textTempSupplier2 = (TextView) findViewById(R.id.textTempSupplier2);
        textTempTipetrans = (TextView) findViewById(R.id.textTempTipetrans);
        textTempTipetrans2 = (TextView) findViewById(R.id.textTempTipetrans2);
        textTemptujuan1 = (TextView) findViewById(R.id.textTampungtujuan1);
        textTemptujuan2 = (TextView) findViewById(R.id.textTampungtujuan2);

        textLokasi = (TextView) findViewById(R.id.textLokasi);
        textLokasihasil = (TextView) findViewById(R.id.textLokasihasil);

        textnoreff = (TextView) findViewById(R.id.textnoref);
        editremarks = (EditText) findViewById(R.id.editremarks);
        editSkau = (EditText) findViewById(R.id.editSkau);
       // simpleprogress = findViewById(R.id.simpleProgressBar);
        //  nilaiof=findViewById(R.id.nilaiof);
        //my_view = findViewById(R.id.layprogress);
       // params = my_view.getLayoutParams();
        //  params.height=200;


       // simpleprogress.setMax(hitbarc);
       // simpleprogress.setProgress(0);

        //    toolbar.setTitle("Barcode Upload");
        //setSupportActionBar(toolbar);
        cari1 = (EditText) findViewById(R.id.cariText);
        editoven = (EditText) findViewById(R.id.editOven);
        //  butbarcodeher=(ImageButton) findViewById(R.id.butBarcodeher);
        butpos1 = (Button) findViewById(R.id.butpost2);
        butovenname = (Button) findViewById(R.id.butovenname);
        butmanualbarc = (Button) findViewById(R.id.butmanualbarc);
        butmanualbarcdel = (Button) findViewById(R.id.butmanualbarcdel);
        butdelnoref = (Button) findViewById(R.id.butdelnoref);
        butposttest = (Button) findViewById(R.id.butposttest);



        t0 = (TextView) findViewById(R.id.jumbarc1);
        t1 = (TextView) findViewById(R.id.jumbarc2);
        Calendar newDate = Calendar.getInstance();
        buterror = findViewById(R.id.buterror);
        //  newDate.set(year, monthOfYear, dayOfMonth);


        adb_master = new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);


        //my_view.setVisibility(View.INVISIBLE);
        //  butpos1.setText("Slide up");
        isUp = false;

        Intent a = getIntent();










        cari1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    butmanualbarc.performClick();

                }
                return false;
            }
        });




        a1 = a.getStringExtra("wrhtype");
        //Toast.makeText(getBaseContext(),a1,Toast.LENGTH_LONG).show();



        butdelnoref.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(getBaseContext(), Emp_RegErrorLokal.class);
                a.putExtra("warning", "Barcode Salah Format");
                startActivity(a);
            }
        });

        butmanualbarcdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cari1.setText("");
                cari1.setGravity(0);
            }
        });

        butmanualbarc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (textTempSupplier.getText().toString().equals("Group") || (textTempTipetrans.getText().toString().equals("Tipe"))) {
                    Intent a = new Intent(getBaseContext(), warningumum.class);
                    a.putExtra("warning", "Supplier atau Tipe Kayu atau Lokasi, harus dipilih");
                    startActivity(a);
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
                    mediaPlayer.start();
                    return;
                }

                if (cari1.getText().toString().length() == 11) {
                    insertdata();
                    loadDataLokal();
                    gambarDatakeRecyclerView();
                } else {
                    Intent a = new Intent(getBaseContext(), warningumum.class);
                    a.putExtra("warning", "Barcode Salah Format");
                    startActivity(a);
                    MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
                    mediaPlayer.start();
                    return;

                }

            }
        });


        spnTipetrans.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                textTempTipetrans.setText(spnTipetrans.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


        spnSupplier.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                textTempSupplier.setText(spnSupplier.getSelectedItem().toString());

                SQLiteDatabase db = adb_master.getReadableDatabase();
                String sql = "select nama_lokasi from "+ADB_Master.MyColumns_sawmill_swgroup.NamaTabel+" where kode_lokasi= '" + spnSupplier.getSelectedItem().toString() + "'";
                Cursor c = db.rawQuery(sql, null);
                int jum = c.getCount();
                c.moveToFirst();
                textLokasihasil.setText(c.getString(0));
                //hitbarc = jum;
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });



        spnTujuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                textTemptujuan1.setText(spnTujuan.getSelectedItem().toString());

                //hitbarc = jum;
            }




            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });


   /*     ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,pilihan);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spnOven.setAdapter(aa);
*/




    /*    butbarcodeher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Load barcode", Toast.LENGTH_LONG).show();
                IntentIntegrator integrator = new IntentIntegrator(NURUploadBarcode.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
                integrator.setCaptureActivity(TorchOnCaptureActivity.class);
                integrator.setOrientationLocked(false);
                integrator.setPrompt("Scan a barcode");
                integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(true);
                integrator.initiateScan();
            }
        });*/


    /*    cari1.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == EditorInfo.IME_ACTION_SEARCH ||
                                keyCode == EditorInfo.IME_ACTION_DONE ||
                                event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    Toast.makeText(NURUploadBarcode.this, "cari kata", Toast.LENGTH_SHORT).show();
                    loadDataServerVolley();
                    gambarDatakeRecyclerView();
                    return true;
                }
                return false;
            }
        });*/

    /*    fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                if (textTempSupplier.getText().toString().equals("Group") || (textTempTipetrans.getText().toString().equals("Tipe"))) {
                    Intent a = new Intent(getBaseContext(), warningumum.class);
                    a.putExtra("warning", "Supplier atau Tipe Kayu atau Lokasi, harus dipilih");
                    startActivity(a);
                    return;
                } else {
                   // SawmillSub_Checkin.typebarcode="mastercheckin";
                    SawmillSub_LOG.typebarcode="mjpapanmanis";
                    //ambilremarks = editremarks.getText().toString();
                    //Toast.makeText(getBaseContext(), "Load barcode", Toast.LENGTH_LONG).show();
                    IntentIntegrator integrator = new IntentIntegrator(SawmillSub_MJPapan.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setCaptureActivity(AnyOrientationCaptureActivity.class);
                    integrator.setCaptureActivity(TorchOnCaptureActivityLokal.class);
                    integrator.setOrientationLocked(false);
                    integrator.setPrompt("Scan a barcode PT. Paradise");
                    integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setBeepEnabled(true);
                    integrator.initiateScan();
                }

            }

        });
*/


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);

        //loadDataServerVolley();
        if (a.hasExtra("barang")) {

            String noref =a.getStringExtra("noref");
         //   barang = (BarangSawmillLOG_sub) a.getSerializableExtra("barang");


         //   noreflokal = barang.getNoref();
           // ambilnoref = textnoreff.getText().toString();
            textnoreff.setText(noref);
            setData(noref);

            spnTujuan.setEnabled(false);

        } else {
            dateFormatter = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.US);
            Date currentTime = Calendar.getInstance().getTime();
            //textnoreff.setText("MainActivity.user_idbuffer" + dateFormatter.format(newDate.getTime()));

            SQLiteDatabase db = adb_master.getReadableDatabase();
            String sql = "select nama_user_short from "+ADB_Master.MyColumns_sawmilltemp.NamaTabel;
            Cursor c = db.rawQuery(sql, null);
            c.moveToFirst();

            textnoreff.setText(c.getString(0) +"_"+ dateFormatter.format(newDate.getTime()));
        //    textnoreff.setText("Grader" + dateFormatter.format(newDate.getTime()));
            noreflokal = textnoreff.getText().toString();

            //norefall=MainActivity.user_idbuffer+dateFormatter.format(newDate.getTime());
        }
        // retrieveOven();
        //retrieveSpin1();


        butposttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPOST();
            }
        });

        buterror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent a = new Intent(getBaseContext(), Emp_RegErrorLokal.class);
                    startActivity(a);
              // updatebarctranbalik(textnoreff.getText().toString());
               // Toast.makeText(getBaseContext(),"Baliktran "+noreflokal,Toast.LENGTH_LONG).show();
            }
        });

   /*     if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network

            // uploadFile(s2);
            connected = true;
        } else {
            connected = false;
            DialogErrorNetwork();
        }
    */
        loadDataLokal();
        gambarDatakeRecyclerView();
       // textTempTipetrans2.setText("OUT");
getSupplier();//Get Lokasi
getTipetrans();
//getLocation2();
getTujuan();
spnTujuan.setVisibility(View.INVISIBLE);
//spnTipetrans.setEnabled(false);
    }







    public void getLocation2(){


        gps = new GPSTracker(getBaseContext());

        // Check if GPS enabled
        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();
            lattemp = latitude;
            longtemp = longitude;



            loc();

            hideDialog();


        }

    }



    public void loc() {
        try {

            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(lattemp,longtemp, 1);
            textLokasi.setText("Lat:" + lattemp + "," + "Long:" + longtemp + "\n" + addresses.get(0).getAddressLine(0) + ", " +
                    addresses.get(0).getAddressLine(1) + ", " + addresses.get(0).getAddressLine(2));
            //  editJudul.setText(addresses.get(0).getAddressLine(0));
            //klokasi=textlocation.getText().toString();
        } catch (Exception e) {

        }

    }



    private void getTujuan() {

        users=adb_master.getSpnoven();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnTujuan.setAdapter(adapter);
        String compareValue = textTemptujuan2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnTujuan.setSelection(spinnerPosition);
        }


    }


    private void getSupplier() {

        users=adb_master.getSwgroup();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnSupplier.setAdapter(adapter);
        String compareValue = textTempSupplier2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnSupplier.setSelection(spinnerPosition);
        }


    }


    private void getTipetrans() {

        users=adb_master.getTipetrans();


        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,android.R.id.text1,users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //attach adapter to spinner
        spnTipetrans.setAdapter(adapter);
        String compareValue = textTempTipetrans2.getText().toString();
        //  Toast.makeText(getApplicationContext(), "masuk:"+compareValue, Toast.LENGTH_SHORT).show();
        if (compareValue != null) {
            int spinnerPosition = adapter.getPosition(compareValue);
            spnTipetrans.setSelection(spinnerPosition);
        }


    }


    public void slideUp(View view) {
      //  params.height = 200;
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                view.getHeight(),  // fromYDelta
                0);                // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);
        DialogPOST();
    }
  //  params
    // slide the view from its current position to below itself
    public void slideDown(View view) {
       // .height = 0;
        view.setVisibility(View.VISIBLE);
        TranslateAnimation animate = new TranslateAnimation(
                0,                 // fromXDelta
                0,                 // toXDelta
                0,                 // fromYDelta
                view.getHeight()); // toYDelta
        animate.setDuration(500);
        animate.setFillAfter(true);
        view.startAnimation(animate);

    }

    public void onSlideViewButtonClick(View view) {
        if (isUp) {
            // slideDown(my_view);
            butpos1.setText("Post Data+");
        } else {
            slideUp(my_view);
            butpos1.setText("Past Data");
        }
        isUp = !isUp;
    }


    private void setData(String noref) {

        // Locale localeID = new Locale("in", "ID");
        //NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //detailHarga.setText(formatRupiah.format((double)hargarumah));

        DecimalFormat formatter = new DecimalFormat("#");
        // totalrp.setText(formatter.format(obj.getDouble("total")));

        SQLiteDatabase db = adb_master.getReadableDatabase();
        String sql = "select supplier,typetran,tujuan,skau from "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" where noref= '" + noref + "'";
        Cursor c = db.rawQuery(sql, null);
        int jum = c.getCount();
        hitbarc = jum;
        jumjumbar = String.valueOf(hitbarc);

        c.moveToFirst();

        textTempSupplier2.setText(c.getString(0));
        textTempTipetrans2.setText(c.getString(1));
        editSkau.setText(c.getString(3));
        editSkau.setEnabled(false);


        Toast.makeText(getBaseContext(), "cari kata"+c.getString(0)+" "+c.getString(1), Toast.LENGTH_SHORT).show();

      //  textTemptujuan2.setText(c.getString(2));

      //  textTempTipetrans2.setText(barang.get);

      /*  textTampung.setText(String.valueOf(barang.getLokoven()));
        textTampung3.setText(String.valueOf(barang.getNamaoven()));
        editremarks.setText(barang.getNamaoven2());
        textnoreff.setText(barang.getNoref());
        editoven.setText(barang.getNamaoven2());
        Toast.makeText(getBaseContext(), "Tujuan :" + barang.getLokoven(), Toast.LENGTH_LONG).show();
*/
      /*   if (Integer.valueOf(t1.getText().toString())>0)
         {
           // spnOven.setEnabled(false);
          //  butovenname.setEnabled(false);
             Toast.makeText(getBaseContext(),"t",Toast.LENGTH_LONG).show();
         }*/



    }






    private void DialogKoneksi() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.koneksi2_warning, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("Connection Info");

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

                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                //   Intent balikretry=new Intent();
                //  balikretry.putExtra("balikretry","ulang");
                ///    setResult(RESULT_OK,balikretry);

                finish();
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


    private void gambarDatakeRecyclerView() {


      rvAdapter = new BarangAdapterSawmillSub_MJ(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                        Intent intent2 = getIntent();
                        // final String kodecabang1=intent2.getStringExtra("kodecabang");

                       BarangSawmillLOG_MJ barang = rvAdapter.getItem(position);


                        // Toast.makeText(getBaseContext(), "Hasil Del "+barang.getBarcode()+"--"+barang.getId(), Toast.LENGTH_SHORT).show();

                        //Intent intent = new Intent(NURUploadBarcode.this, BarangActivity.class);

                        // intent.putExtra("barang", barang);


                        //  startActivityForResult(intent, REQUEST_CODE_EDIT);
                      DialogDelBarcTran(barang.getBarcode(), barang.getId());


                    }

                })

        );

    }

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


      /*  if (scanningResult != null) {

            // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));
            //insertdata();

            if (scanningResult.getContents() != null) {
                //   Toast.makeText(this, "hASIL cANNING"+scanningResult.getContents(), Toast.LENGTH_SHORT).show();
                cari1.setText("");
                cari1.setText(scanningResult.getContents());

                lastscan = scanningResult.getContents();

                //  loadDataLokal();
                //   gambarDatakeRecyclerView();
                //we have a result
                //cari1.setText(scanningResult.getContents());
                // textTampung3.setText(String.valueOf(data.getStringExtra("idno")));
                if (lastscan.length() == 10) {
                    insertdata();
                    loadDataLokal();
                    gambarDatakeRecyclerView();

                } else {
                    Intent a = new Intent(getBaseContext(), warningumum.class);
                    a.putExtra("warning", "Barcode salah format");
                    startActivity(a);
                    return;
                }
            }
        }

*/

        if (resultCode == RESULT_OK && requestCode == 222) {

            editoven.setText(data.getStringExtra("section"));
            textTampung3.setText(String.valueOf(data.getStringExtra("idno")));

            loadDataLokal();

            nilaiof.setText("0 of " + hitbarc);

        }


    }

    //ambil data sever volley
    private void insertdata() {

        String item1 = cari1.getText().toString();


        int tagin = 1;

        SQLiteDatabase db2 = adb_master.getReadableDatabase();

        String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel + " WHERE "
                + ADB_Master.MyColumns_newbarcode_mjpapan.noref + " = '" + textnoreff.getText().toString() + "' and " + ADB_Master.MyColumns_newbarcode_mjpapan.barcode + " = '" + item1 + "'";
        //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
        Cursor c2 = db2.rawQuery(selectQuery2, null);
        int hit1 = c2.getCount();

//hitung no urut
        String selectQuery3 = "SELECT  *  FROM " + ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel + " WHERE "
                + ADB_Master.MyColumns_newbarcode_mjpapan.noref + " = '" + textnoreff.getText().toString() + "'";
        //   String selectQuery2 = "SELECT  *  FROM " + ADB_Master.MyColumns_item.NamaTabel;
        //String selectQuery2 = "SELECT  *  FROM " + ADB_Trans.MyColumns_trans1sub.NamaTabel;
        Cursor c3 = db2.rawQuery(selectQuery3, null);
        int hiturut = c3.getCount()+1;


        Toast.makeText(this, "hit" + hit1, Toast.LENGTH_SHORT).show();


        if (hit1 > 0) {

            //Toast.makeText(this, "Barcode sudah pernah ada", Toast.LENGTH_SHORT).show();
            // DialogExistitem();
            Intent a = new Intent(getBaseContext(), warningumum.class);
            a.putExtra("warning", "Barcode Sudah pernah di Scan");
            startActivity(a);
            MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.error);
            mediaPlayer.start();
            return;
        } else {
        //    final String namacari = cari1.getText().toString();

            SQLiteDatabase create = adb_master.getWritableDatabase();

            //isi data master
            ContentValues values2 = new ContentValues();
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.barcode,item1 );
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.noref,textnoreff.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.typetran,textTempTipetrans.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.supplier,textTempSupplier.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.tujuan,textTemptujuan1.getText().toString() );
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.tagtran,2 );//2 masuk 1 update
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.nourut,hiturut );//2 masuk 1 update
            values2.put(ADB_Master.MyColumns_newbarcode_mjpapan.skau,editSkau.getText().toString() );//2 masuk 1 update
            create.insert(ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel, null, values2);

            new CountDownTimer(100, 1000) {
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.bell);
                    mediaPlayer.start();
                }

                public void onTick(long millisUntilFinished) {
                    // millisUntilFinished    The amount of time until finished.
                }
            }.start();
         //   fab.performClick();
            Toast.makeText(this, "Add Barcode Succesfully", Toast.LENGTH_SHORT).show();
            typebarcode="x";
        }


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
                cari1.setText("");
                cari1.requestFocus();
            }
        }
        return super.dispatchKeyEvent(event);
    }



    private void loadDataLokal() {

     /*   Intent intent=getIntent();
        final String kodecabang1=intent.getStringExtra("kodecabang");
        final String namacari=cari1.getText().toString();*/


    //    String item1 = cari1.getText().toString();
    //    String lokoven = textTampung2.getText().toString();
    //    String namaoven = textTampung3.getText().toString();
        String noref = textnoreff.getText().toString();

        t1 = (TextView) findViewById(R.id.jumbarc2);

        SQLiteDatabase db = adb_master.getReadableDatabase();
        String sql = "select * from "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" where noref= '" + noref + "'";
        Cursor c = db.rawQuery(sql, null);
        int jum = c.getCount();
        hitbarc = jum;
        jumjumbar = String.valueOf(hitbarc);


        if (jum > 0) {

            spnSupplier.setEnabled(false);
            spnTipetrans.setEnabled(false);

        }

        t1.setText(String.valueOf(jum));
        //  barangListPOS = adb_user.getSemuaUser();
         barangList = adb_master.getSemuaMJPapansub(noref);
        //  barangList = adb_trans.getSemuaItemtest();
        gambarDatakeRecyclerView();


    }


    private void retrieveOven2() {

        //  Intent intent=getIntent();
        //final String kodecabang1=intent.getStringExtra("kodecabang");


        final String namacari = cari1.getText().toString();
        String url = AppConfig.IP_SERVER+"sawmill/barcsw_ovenloc2.php";
        //String url ="http://golden-care.co.id/android/listdata.php";
        pDialog.setMessage("Retieve Data Barang...");
        //    Toast.makeText(this,"test masuk load server "+kodecabang1,Toast.LENGTH_LONG).show();
        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                Log.d("MainActivity bedul kubu", "response:" + response);

                hideDialog();


                processResponse4(response);

                gambarDatakeRecyclerView();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        // pDialog.setMessage("Connection failed");

                        hideDialog();

                        if (error instanceof NetworkError) {
                            DialogKoneksi();
                        } else if (error instanceof ServerError) {

                            DialogKoneksi();
                        } else if (error instanceof AuthFailureError) {
                            DialogKoneksi();
                        } else if (error instanceof ParseError) {
                            DialogKoneksi();
                        } else if (error instanceof NoConnectionError) {

                            DialogKoneksi();
                        } else if (error instanceof TimeoutError) {

                            DialogKoneksi();
                        }


                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                // the POST parameters:

                params.put("idoven", textTampung.getText().toString());
                //    params.put("kodecab",kodecabang1);

                return params;

            }

        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(100 * 1000, 1, 1.0f));
        Volley.newRequestQueue(this).add(postRequest);

    }

    private void processResponse4(String response) {

        try {

            JSONObject obj = new JSONObject(response);
            // if(obj.optString("status").equals("true")){

            barangCabangArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {

                BarangMasterUser playerModel = new BarangMasterUser();
                JSONObject dataobj = dataArray.getJSONObject(i);

                textTampung2.setText(String.valueOf(dataobj.getString("idno")));

                // playerModel.setIdoven(Integer.valueOf(dataobj.getString("idno")));
                // playerModel.setOvenname(dataobj.getString("section"));
                //barangCabangArrayList.add(playerModel);

            }


            try {

                //   mPicture = getPictureCallback();
                //   mCamera.takePicture(null, null, mPicture);

            } catch (Exception e1) {
                e1.printStackTrace();
                //texterror.setText("nosupport");
            }


            SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
            String date = dateFormat.format(new Date());


            hideDialog();
            // finish();


        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }

    }


    private void DialogExistitem() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warningitem, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        String w1 = "Item : " + cari1.getText().toString();
        dialog.setTitle(w1);

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
                // deltrans();
                // inserttoserver();
                //Intent bukadialog = new Intent(NURUploadBarcode.this,NURpono.class);
                //   final String notrans3=notrans.getText().toString();
                //   Toast.makeText(getBaseContext(),"No Transaksi: "+notrans3, Toast.LENGTH_SHORT).show();
                // bukadialog.putExtra("posno","posno");
                // startActivityForResult(bukadialog, REQUEST_CODE_EDIT);
                //txt_hasil.setText("Nama : " + nama + "\n" + "Usia : " + usia + "\n" + "Alamat : " + alamat + "\n" + "Status : " + status);
                dialog.dismiss();
                //  finish();
                return;
            }
        });

      /*  dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }


    private void DialogDelNoref(final String norefxj) {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warningdelbarctran, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);

        String d1 = "No Ref " + norefxj;
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
                delbarctran_noref(norefxj);
                sqldelnoref(norefxj);

                finish();
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


    private void DialogDelBarcTran(String barc, final Integer gidno) {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warningdelbarctran, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);

        String d1 = "Data " + barc;
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
                delbarctran(gidno);

                loadDataLokal();


                    /*  sqldelnoref(textnoreff.getText().toString());
                      if (hitbarc>0) {
                          inserttoserver(textnoreff.getText().toString());
                      }
*/
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


    private void DialogPOST() {
        dialog = new AlertDialog.Builder(this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.post_warning, null);
        dialog.setView(dialogView);
        //  dialog.setCancelable(true);
        dialog.setIcon(R.mipmap.ic_launcher);
        dialog.setTitle("POST Warning");

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

             //   sqldelnoref(textnoreff.getText().toString());
              //  updatebarctran(ambilnoref);
               insertto_test(textnoreff.getText().toString());

                //  inserttoserver(textnoreff.getText().toString());
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


    private void sqldelnoref(final String norefs) {



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


        String url = AppConfig.IP_SERVER+"gcnew/lokal_delnoref.php";


        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {


                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();
                //   Toast.makeText(getBaseContext(),"Hasil awal: "+macadd1+location1+absen,Toast.LENGTH_SHORT).show();

                processResponsedel(response);
                // deldatatrans();
                //loadDataServerVolley();

                hideDialog();


                //   finish();

            }

        },

                new Response.ErrorListener() {

                    @Override

                    public void onErrorResponse(VolleyError error) {

                        hideDialog();


                        if (error instanceof NetworkError) {
                            Toast.makeText(getBaseContext(),
                                    "Oops. Network Error",
                                    Toast.LENGTH_LONG).show();
                            DialogErrorNetwork();

                        } else if (error instanceof ServerError) {
                            DialogErrorServer();
                            Toast.makeText(getBaseContext(),
                                    "Oops. Server Time out",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof AuthFailureError) {
                        } else if (error instanceof ParseError) {
                        } else if (error instanceof NoConnectionError) {
                            DialogErrorNetwork();
                            Toast.makeText(getBaseContext(),
                                    "Oops. No Connection!",
                                    Toast.LENGTH_LONG).show();
                        } else if (error instanceof TimeoutError) {
                            DialogErrorNetwork();
                            Toast.makeText(getBaseContext(),
                                    "Oops. Timeout error!",
                                    Toast.LENGTH_LONG).show();
                        }


                        error.printStackTrace();

                    }

                }

        ) {

            @Override

            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                // the POST parameters:

                params.put("noref", norefs);


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


    }

    private void processResponsedel(String response) {

        try {


            JSONObject jsonObj = new JSONObject(response);
/*
            String errormsg = jsonObj.getString("errormsg");
            String nama = jsonObj.getString("nama");
            String section = jsonObj.getString("section");
            //  String waktu = jsonObj.getString("waktu");
            Toast.makeText(getBaseContext(),"Att : "+nama+' '+section+" Data Save Succesfully",Toast.LENGTH_LONG).show();

*/


        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }


        inserttoserver(textnoreff.getText().toString());

    }


    private void sqlserverinsert(final String t0, final String t1, final String t2, final String t3, final String t4, final String t5, final String rems) {


        hit = 0;
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


        String url = AppConfig.IP_SERVER+"gcnew/lokal_insertbarctran_test.php";


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

                params.put("barcode2", t0);
                params.put("noref", t1);
                params.put("typetran", t2);
                params.put("lokoven", t3);
                params.put("tgl_rec", t4);
                params.put("user_name", t5);
                params.put("hitbarc", String.valueOf(hitbarc));
                params.put("remarks", editremarks.getText().toString());


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


            final String check1 = jsonObj.getString("check1");
            final String check2 = jsonObj.getString("check2");
            final String check3 = jsonObj.getString("check3");







          /*  final Handler handler = new Handler();
            handler.post( new Runnable(){
                private int k = 0;
*/



              //  public void run() {





                    if (check1.equals(String.valueOf(hitbarc))) {

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
        tagdata = 2;


    }


    public void delay2(final int c,final int s) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EditText tv= (EditText) findViewById(R.id.editremarks);
                tv.setText("Text = "+s);
                DialogErrorServer();
            }
        }, c);

    }



    public void delay(final int c) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(c);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               // DialogErrorServer();         //send
            }
        }, c);

    }


    private void DialogErrorNetwork() {
        dialog = new AlertDialog.Builder(SawmillSub_MJPapan.this);
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
                sqldelnoref(textnoreff.getText().toString());
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
        dialog = new AlertDialog.Builder(SawmillSub_MJPapan.this);
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


    private void inserttoserver(final String norefx) {
        //del transaction


        //  pDialog.setMessage("Uploading Data...");
        //  pDialog.show();


        SQLiteDatabase db = adb_master.getWritableDatabase();
        String sql1 = "select barcode,noref,typetran,lokoven,tgl_rec,temp1,idno,temp2 from tbl_barctran where noref='" + norefx + "' ";
        Cursor cursor = db.rawQuery(sql1, null);

        cursor.moveToFirst();


////////////              batas del


          int hut = 0;

        if (cursor.moveToFirst()) {
            do {
                //    BarangDialog userin = new BarangDialog(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                //  userList2.add(userin);


                sqlserverinsert(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
                //Toast.makeText(getBaseContext(),"Att : "+cursor.getString(1),Toast.LENGTH_SHORT).show();


                hut = hut + 1;


      // delay2(1000,hut);



                //  nilaiof.setText(String.valueOf(hit)+" Of "+hitbarc);
                //editremarks.setText(String.valueOf(hit));


            } while (cursor.moveToNext());
        }

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

        //Toast.makeText(getBaseContext(),"Upload Completed",Toast.LENGTH_SHORT).show();
    }






    private void updateTextView(final String s) {
        SawmillSub_MJPapan.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                EditText tv= (EditText) findViewById(R.id.editremarks);
                tv.setText("Text = "+s);
                tv.invalidate();









            }
        });

    }

private void deldatatrans()
{
    SQLiteDatabase db=adb_master.getWritableDatabase();
    String del1="delete from tbl_barang";
    db.execSQL(del1);
}

    private void delbarctran_noref(String norefx)
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String del1="delete from " +ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" where noref='"+norefx+"'";
        db.execSQL(del1);
    }

    private void delbarctran(Integer idno)
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String del1="delete from "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel +" where idno="+idno;
        db.execSQL(del1);

        String upd1="update "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" set tagtran='2'  where noref='"+textnoreff.getText().toString()+"'";
        db.execSQL(upd1);
    }


    private void updatebarctranbalik(final String norefx)
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String upd1="update tbl_barctran set tagtran='1' where noref='"+norefx+"'";
        db.execSQL(upd1);
    }

    private void insertto_test(final String norefx) {
        //del transaction


        SQLiteDatabase db = adb_master.getWritableDatabase();
        String sql1 = "select barcode,tgl_rec,noref,lokasi,nourut from "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel +" where noref='" + textnoreff.getText().toString()+ "' ";
        Cursor cursor = db.rawQuery(sql1, null);
        hitbarc=cursor.getCount();


        SQLiteDatabase dbtrans = adb_trans.getWritableDatabase();

        //delete awal data di tbl barang
        String del1="delete from transtempsawmill";
        dbtrans.execSQL(del1);

        // cursor.moveToFirst();

        SQLiteDatabase createtrans = adb_trans.getWritableDatabase();
        // String selectQuery = "SELECT hak FROM " + ADB_User.MyColumnsHak.NamaTabel;
        //Cursor cursor=create.rawQuery(selectQuery,null);

        ContentValues values = new ContentValues();
        values.put(ADB_Trans.MyColumns_transtempsawmill.barcode, "x");
        values.put(ADB_Trans.MyColumns_transtempsawmill.lokasi, "x");
        values.put(ADB_Trans.MyColumns_transtempsawmill.noref, "x");//nourut mj papan
        values.put(ADB_Trans.MyColumns_transtempsawmill.remarks, "x");
        values.put(ADB_Trans.MyColumns_transtempsawmill.tgl_doc, "01-01-1970 01:01:01");   //nolog/sw_group
        createtrans.insert(ADB_Trans.MyColumns_transtempsawmill.NamaTabel, null, values);

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

                String sql12 = "select barcode,tgl_doc,noref from " +ADB_Trans.MyColumns_transtempsawmill.NamaTabel+ " where remarks='x'";
                Cursor cursor2 = dbtrans.rawQuery(sql12, null);
                cursor2.moveToFirst();

                // SQLiteDatabase db=adb_master.getWritableDatabase();

                String upd1="update transtempsawmill set barcode='"+cursor2.getString(0)+";"+cursor.getString(0)+"',tgl_doc='"+cursor2.getString(1)+";"+cursor.getString(1)+"',noref='"+cursor2.getString(2)+";"+cursor.getString(4)+"'";
                dbtrans.execSQL(upd1);

                int jum=cursor2.getCount();

                //  Toast.makeText(getBaseContext(),"Last Barcode:  "+cursor.getString(0),Toast.LENGTH_LONG).show();


            } while (cursor.moveToNext());

        }


        String sql12 = "select barcode,tgl_doc,noref from transtempsawmill"; //noref as nourut
        Cursor cursor2 = dbtrans.rawQuery(sql12, null);
        cursor2.moveToFirst();
        //  cursor.moveToLast();
        // String upd1="update tbl_barang set kode='"+cursor2.getString(0)+";"+cursor.getString(0)+"'";
        // db.execSQL(upd1);

        // editremarks.setText(cursor2.getString(0));

        // Toast.makeText(getBaseContext(),"Last Barcode:  "+cursor2.getString(0),Toast.LENGTH_LONG).show();




        //sqlserverinsert(cursor2.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        cursor.moveToFirst();
        sqlserverinsert(cursor2.getString(0), cursor2.getString(1),textnoreff.getText().toString(),textTempSupplier.getText().toString(),cursor2.getString(2) );


/*
        params.put("barcode", t0);
        params.put("idno_link", t1);
        params.put("sw_panjang", t2);
        params.put("lebar", t3);
        params.put("panjang", t4);
        params.put("tebal", t5);
        params.put("grade", t6);
        params.put("sw_panjang", t7);
        params.put("tgl_doc", t8);
        params.put("hitbarc", String.valueOf(hitbarc));
   */

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
        updatebarctran();


    */
        //  return userList2;


        //    loadDataServerVolley();
        // updatebarctran(textnoreff.getText().toString());
        //Toast.makeText(getBaseContext(),"Upload Completed Grader LOG",Toast.LENGTH_SHORT).show();

    }




    private void sqlserverinsert(final String t0, final String t1, final String t2, final String t3, final String t4) {


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


        String url = AppConfig.IP_SERVER+"sawmill/lokal_insertdatasawmill_mjpapan.php";


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
                params.put("nourut", t4);
                params.put("skau", editSkau.getText().toString());
                params.put("tujuan", textTemptujuan1.getText().toString());
                params.put("barcode", t0);
                params.put("tgl_doc", t1);
                params.put("noref", t2);
                params.put("lokasi", t3);
                params.put("typetran",textTempTipetrans.getText().toString());
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


        if (isConnected()) {
            // simpleprogress.setProgress(Integer.valueOf(valprogress
            updatebarctran(textnoreff.getText().toString());


            Intent a = new Intent(getBaseContext(), warningumum.class);
            a.putExtra("warning", "Upload MJ Papan Selesai");
            startActivity(a);
        }
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

    private void updatebarctran(final String norefx)
    {
        SQLiteDatabase db=adb_master.getWritableDatabase();
        String upd1="update "+ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" set tagtran=1 where noref='"+norefx+"'";
        db.execSQL(upd1);
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

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

       getMenuInflater().inflate(R.menu.menu_inventory_master, menu);

       return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will

        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_close) {
            Intent intent=getIntent();
            final String kodecabang1=intent.getStringExtra("kodecabang");
            final String namacabang1=intent.getStringExtra("namacabang");
            final String groupho1=intent.getStringExtra("groupho");
            final String username1=intent.getStringExtra("username");

            Intent balik4=new Intent();
            balik4.putExtra("kodecab",kodecabang1);
            balik4.putExtra("namacab",namacabang1);
            balik4.putExtra("user1",username1);
            balik4.putExtra("groupho",groupho1);
            setResult(RESULT_OK,balik4);
             finish();
            return true;

        }

        return super.onOptionsItemSelected(item);

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
