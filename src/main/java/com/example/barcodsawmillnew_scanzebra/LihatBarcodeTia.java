package com.example.barcodsawmillnew_scanzebra;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.NetworkError;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LihatBarcodeTia extends AppCompatActivity {

    private ProgressDialog pDialog;
    private BarangAdapterListBarcodeTia rvAdapter;
    private RecyclerView  mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BarangListBarcodeTia> barangList=new ArrayList<BarangListBarcodeTia>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barcode_tia);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_barcode);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        pDialog = new ProgressDialog(this);
        loaddatavolley();
    }


    private void showDialog() {

        if (!pDialog.isShowing())

            pDialog.show();

    }

    private void hideDialog() {

        if (pDialog.isShowing())

            pDialog.dismiss();

    }


    private void gambarDatakeRecyclerView() {


          rvAdapter = new BarangAdapterListBarcodeTia(barangList);

          mRecyclerView.setAdapter(rvAdapter);


    }

    private void loaddatavolley() {


//
//        SQLiteDatabase createdel = adb_master.getWritableDatabase();
//
//        String del1 = "delete  FROM " + ADB_Master.MyColumns_sawmill_wrh.NamaTabel;
//        createdel.execSQL(del1);

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
      //  String url = AppConfig.IP_SERVER+"sawmill/listbarcputri.php";
        String url = AppConfig.IP_SERVER+"sawmill/listbarcputri.php";


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
                gambarDatakeRecyclerView();
//                pDialog.hide();
              //  hideDialog();
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

            BarangListBarcodeTia objectbarang = null;

            barangList.clear();

            //  barangListPOS.clear();

            //NumberFormat numberFormat  = new DecimalFormat("##");

            // DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            //  totalrp.setText(formatter.format(obj.getDouble("total")));

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                 objectbarang= new BarangListBarcodeTia();
                 objectbarang.setBarcodelog(obj.getString("LOG"));
                 objectbarang.setBarcodelpotong(obj.getString("HASILPOTONG"));
                 objectbarang.setCurskau(obj.getString("curskau2"));
                 if(obj.getString("tgl_sawmill").equals("null")) {
                     objectbarang.setTgl(String.valueOf(obj.getString("tglscan") + "/ -"));
                 }else
                 {
                     objectbarang.setTgl(String.valueOf(obj.getString("tglscan") + " / "+obj.getString("tgl_sawmill")));

                 }
                barangList.add(objectbarang);


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
//
//                SQLiteDatabase create = adb_master.getWritableDatabase();
//
//                // String del1 = "delete  FROM " + ADB_Master.MyColumns_headerbarcode.NamaTabel;
//                //   create.execSQL(del1);
//                //Cursor del2 = create.rawQuery(del1, null);
//                //isisawmill lokasi
//                ContentValues values = new ContentValues();
//                values.put(ADB_Master.MyColumns_sawmill_wrh.nama_lokasi,obj.getString("nama_lokasi"));
//                values.put(ADB_Master.MyColumns_sawmill_wrh.alamat_lokasi,obj.getString("alamat_lokasi"));
//                create.insert(ADB_Master.MyColumns_sawmill_wrh.NamaTabel, null, values);

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
hideDialog();
    }

}