package com.example.barcodsawmillnew_scanzebra;

import android.app.Instrumentation;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
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

public class LihatBarcodeTia_deletegroup extends AppCompatActivity {

    private ProgressDialog pDialog;
    private BarangAdapterListBarcodeTia_delgroup rvAdapter;
    private RecyclerView  mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<BarangListBarcodeTia_delgroup> barangList=new ArrayList<BarangListBarcodeTia_delgroup>();
    private ADB_Master adb_master;
    private ADB_Trans adb_trans;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_barcode_tia_deletegroup);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view_barcode);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        pDialog = new ProgressDialog(this);

        adb_master=new ADB_Master(getBaseContext());
        adb_trans = new ADB_Trans(getBaseContext());

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


        rvAdapter = new BarangAdapterListBarcodeTia_delgroup(barangList);

        mRecyclerView.setAdapter(rvAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemListener(context, new RecyclerItemListener.OnItemClickListener() {

                    @Override

                    public void onItemClick(View view, int position) {

                        //Intent intent2 = getIntent();
                        // final String kodecabang1=intent2.getStringExtra("kodecabang");

                        BarangListBarcodeTia_delgroup barangdetail = rvAdapter.getItem(position);


                        // Toast.makeText(getBaseContext(), "Hasil Del "+barang.getBarcode()+"--"+barang.getId(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getBaseContext(), LihatBarcodeTia_DelGroup_Sub.class);



                        intent.putExtra("group_del", barangdetail.getNogroup());
                        intent.putExtra("idnolink_del", String.valueOf(barangdetail.getLinknourut()));
                        startActivityForResult(intent, 2020);
//
//
//
//                        startActivityForResult(intent, 202020);
//                        // DialogDelBarcTran(barang.getBarcode(), barang.getId());

                    }

                })

        );



    }


    @Override

    protected void onActivityResult ( int requestCode, int resultCode, Intent data) {

        //      IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (requestCode==2020)
        {
            loaddatavolley();
        }

    }

        private void loaddatavolley() {

        barangList = adb_master.getDelgroup();
        gambarDatakeRecyclerView();
hideDialog();
    }

}