package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
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

public class userpass_edit extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    private BarangUserpass barang;
    private BarangAdapterUserpass rvAdapter;

    private ArrayList<BarangUserpass> barangCabangArrayList;
    private ArrayList<String> names = new ArrayList<String>();

    private RecyclerView.LayoutManager mLayoutManager;

    private Context context = userpass_edit.this;

    private static  final int REQUEST_CODE_ADD =1;

    private static  final int REQUEST_CODE_EDIT =2;

    private List<BarangUserpass> barangList = new ArrayList<BarangUserpass>();

    private ProgressDialog pDialog;
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    Button butNew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpass_edit);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        pDialog = new ProgressDialog(this);

butNew=findViewById(R.id.butNew);
butNew.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getBaseContext(), userpass_modify.class);
        startActivity(intent);
       // startActivityForResult(intent, REQUEST_CODE_EDIT);
    }
});



        loaddatavolley();

    }





    @Override

    protected void onActivityResult ( int requestCode, int resultCode, Intent data){

     //   IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);


      loaddatavolley();






    }





    private void gambarDatakeRecyclerView(){

        rvAdapter = new BarangAdapterUserpass(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        //  final String test1=ambilsw1.getText().toString();


        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                        // Toast.makeText(getBaseContext(),"Posisi no: "+position, Toast.LENGTH_SHORT).show();

                        BarangUserpass barang = rvAdapter.getItem(position);

                        Intent intent = new Intent(getBaseContext(), userpass_modify.class);
                        //startActivity(intent);

                        intent.putExtra("barang", barang);

                        startActivityForResult(intent, REQUEST_CODE_EDIT);

                    }

                })

        );

    }




    public void loaddatavolley(){

        // final String notransaksi= notrans.getText().toString();
        // Intent intent=getIntent();
        // final String kodecabang1=intent.getStringExtra("kodecabang");
//        Toast.makeText(this,"Load Barcode : "+notransaksi,Toast.LENGTH_LONG).show();
        String url = AppConfig.IP_SERVER+"sawmill/editdata_user.php";
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
                gambarDatakeRecyclerView();
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

            protected Map<String, String> getParams()

            {

                // Toast.makeText(Transaksi_POS.this,"HAS MAP : "+notransaksi,Toast.LENGTH_LONG).show();

                Map<String, String>  params = new HashMap<>();

                // the POST parameters:

                // params.put("nopos",notransaksi);
               // params.put("typetran",statustran.getText().toString());

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

            BarangUserpass objectbarang = null;

            barangList.clear();

            //NumberFormat numberFormat  = new DecimalFormat("##");

            // DecimalFormat formatter = new DecimalFormat("#,###,###,###");
            //  totalrp.setText(formatter.format(obj.getDouble("total")));

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject obj = jsonArray.getJSONObject(i);

                objectbarang= new BarangUserpass();

                objectbarang.setUser(obj.getString("user_id"));
                objectbarang.setPass(obj.getString("pass_id"));


                barangList.add(objectbarang);


            }
//pDialog.hide();
        } catch (JSONException e) {

            Log.d("MainActivity", "errorJSON");

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

}