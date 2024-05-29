package com.example.barcodsawmillnew_scanzebra;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class BarangActivityTranPOS extends AppCompatActivity {

    private EditText editTextIDNO, editTextProduct, editTextQty,editTextDiscount;

    private BarangSawmillAllLOG barang;

    private String action_flag="add";

    private String refreshFlag="0";

    private static final String TAG="test test";

    private ProgressDialog pDialog;
    private ADB_Master adb_master;
    private static final String DATABASE_NAME = "master.db";
    SQLiteDatabase mDatabase;
    android.app.AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_barang_tran_pos);

        adb_master = new ADB_Master(getBaseContext());
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);
      //  FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

   //   fab.setOnClickListener(new View.OnClickListener() {

     //      @Override

       //   public void onClick(View view) {

       //        saveDataVolley();

        //   }

     //  });

     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);

                              // barang = new BarangTranPos();

      initUI();

        //initEvent();

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Edit No urut");
        setSupportActionBar(toolbar);


        Intent intent = getIntent();



        if (intent.hasExtra("barang")) {

           Log.d(TAG, "Barang : ");

       barang = (BarangSawmillAllLOG) intent.getSerializableExtra("barang");

    //        Log.d(TAG, "Barang : " + barang.toString());

//     setData(barang);


//            Toast.makeText(getBaseContext(),"data "+barang.getNourut()+"--"+barang.getId(),Toast.LENGTH_LONG).show();

            action_flag = "edit";

          editTextIDNO.setEnabled(false);
          editTextProduct.setEnabled(true);
            editTextIDNO.setText(String.valueOf(barang.getId()));
            editTextProduct.setText(String.valueOf(barang.getNourut()));

        }else{
            Log.d(TAG, "Barang : ");
          //  barang = new BarangTranPos();
        //    Log.d(TAG, "Barang : " + barang.toString());

        }

    }


    private void setData(BarangSawmillAllLOG barang) {

        // Locale localeID = new Locale("in", "ID");
        //NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        //detailHarga.setText(formatRupiah.format((double)hargarumah));

        DecimalFormat formatter = new DecimalFormat("#");
       // totalrp.setText(formatter.format(obj.getDouble("total")));
        Toast.makeText(getBaseContext(),"data "+barang.getNourut()+"--"+barang.getId(),Toast.LENGTH_LONG).show();
////        editTextIDNO.setText(String.valueOf(barang.getId()));
//       editTextProduct.setText(barang.getNourut());
        editTextIDNO.setText(barang.getId());
        editTextProduct.setText("Nourut");
//        editTextQty.setText(String.valueOf(formatter.format(barang.getQty())));
//        editTextDiscount.setText(String.valueOf(formatter.format(barang.getDiscount())));

    }

    private void initUI() {

        pDialog = new ProgressDialog(com.example.barcodsawmillnew_scanzebra.BarangActivityTranPOS.this);



        editTextIDNO = (EditText) findViewById(R.id.editTextIDNO);

        editTextProduct = (EditText) findViewById(R.id.editTextProduct);

//        editTextQty = (EditText) findViewById(R.id.editTextQty);
//
//        editTextDiscount = (EditText) findViewById(R.id.editTextDiscount);

    }

    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_barang, menu);

        return true;

    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will

        // automatically handle clicks on the Home/Up button, so long

        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_save) {

            saveDataSQL();

            return true;


        }else  if (id == R.id.action_close) {

            finish();

            return true;



        }else  if (id == R.id.action_delete) {

            hapusData();

            return true;

        }

        return super.onOptionsItemSelected(item);

    }



    private void hapusData() {

        new AlertDialog.Builder(this)

                .setTitle("Data Barang")

                .setMessage("Hapus Data " + barang.getBarcode() + " ?")

                .setIcon(android.R.drawable.ic_dialog_alert)

                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {

                        final String idno = editTextIDNO.getText().toString();



                        SQLiteDatabase dbtrans = adb_master.getWritableDatabase();

                        String upd1="delete from  tbl_newbarcode_log  where idno='"+idno+"'";
                        dbtrans.execSQL(upd1);
                        finish();


                        refreshFlag = "1";

                        //finish();

                    }

                })

                .setNegativeButton(android.R.string.no, null).show();

    }

    @Override

    public void finish() {

        System.gc();

        Intent data = new Intent();

        data.putExtra("refreshflag", refreshFlag);

        //  data.putExtra("barang", barang);

        setResult(RESULT_OK, data);

        super.finish();

    }


    private void saveDataSQL(){
        final String idno = editTextIDNO.getText().toString();
        final String nourut = editTextProduct.getText().toString();

//        SQLiteDatabase db = adb_master.getReadableDatabase();
//        String sql = "select supplier,typetran,skau from "+ADB_Master.MyColumns_newbarcode_log.NamaTabel+" where noref= '" + noref + "'";
//        Cursor c = db.rawQuery(sql, null);

        SQLiteDatabase dbtrans = adb_master.getWritableDatabase();

        String upd1="update tbl_newbarcode_log set nourut='"+nourut+"' where idno='"+idno+"'";
        dbtrans.execSQL(upd1);
        finish();

    }


        private void saveDataVolley(){

        refreshFlag="1";

        final String product = editTextProduct.getText().toString();

        final String idno = editTextIDNO.getText().toString();

        final String qty = editTextQty.getText().toString();

        final String discount = editTextDiscount.getText().toString();

        String url = AppConfig.IP_SERVER+"/savedataposedit.php";

        pDialog.setMessage("Save Data Barang...");

        showDialog();

        StringRequest postRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {

                hideDialog();

                Log.d("BarangActivity", "response :" + response);

                // Toast.makeText(getBaseContext(),"response: "+response, Toast.LENGTH_SHORT).show();

                processResponse("Save Data",response);

                finish();

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

            protected Map<String, String> getParams()

            {

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                params.put("product",product);

                params.put("idno",idno);

                params.put("qty",qty);

                params.put("discount",discount);


                if (action_flag.equals("add")){

                    params.put("idno","0");

                }else{

                    params.put("idno",String.valueOf(barang.getId()));

                }

                return params;

            }

        };

        Volley.newRequestQueue(this).add(postRequest);


    }

    private void processResponse(String paction, String response){

        try {

            JSONObject jsonObj = new JSONObject(response);

            String errormsg = jsonObj.getString("errormsg");

            Toast.makeText(getBaseContext(),paction+" "+errormsg,Toast.LENGTH_SHORT).show();

        } catch (JSONException e) {

            Log.d("BarangActivity", "errorJSON");

        }

    }





    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }



}

