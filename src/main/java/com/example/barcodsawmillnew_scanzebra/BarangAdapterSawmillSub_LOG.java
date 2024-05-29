package com.example.barcodsawmillnew_scanzebra;

import android.app.AlertDialog;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

public class BarangAdapterSawmillSub_LOG extends RecyclerView.Adapter<BarangAdapterSawmillSub_LOG.BarangViewHolder> {

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    List<BarangSawmillLOG_sub> barangs;
    Context ok;
    ADB_Master adb_master;
    public static int idno;
   View v;

    public BarangAdapterSawmillSub_LOG(List<BarangSawmillLOG_sub> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_barangbarcode, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, final int i) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");

        barangViewHolder.barangBarcode.setText(barangs.get(i).getBarcode());

        barangViewHolder.barangTgl.setText(barangs.get(i).getTglrec()+"  ("+barangs.get(i).getTagtran()+")");

        barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getId()));
        idno=barangs.get(i).getId();

        barangViewHolder.barangDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // DialogPOST();
                Toast.makeText(ok,"Test", Toast.LENGTH_SHORT).show();

            }
        });






    }











    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangSawmillLOG_sub getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        TextView barangBarcode;

        TextView barangTgl;

        TextView barangIDNO;
        Button barangDel;

        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);

            barangBarcode = (TextView) itemView.findViewById(R.id.textViewRowbarcode);
            barangTgl = (TextView) itemView.findViewById(R.id.textViewRowTgl);
            barangIDNO = (TextView) itemView.findViewById(R.id.textViewRowIDNO);
            barangDel = (Button) itemView.findViewById(R.id.butDeldata);

        }



    }



}
