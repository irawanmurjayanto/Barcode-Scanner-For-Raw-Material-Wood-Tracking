package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.BarangViewHolder> {

    List<BarangSawmill_barc> barangs;
    public BarangAdapter(List<BarangSawmill_barc> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_barangbarcode, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");

        barangViewHolder.barangBarcode.setText(barangs.get(i).getBarcode());
        barangViewHolder.barangNourut.setText(String.valueOf(barangs.get(i).getNourut()));

        barangViewHolder.barangTgl.setText(barangs.get(i).getTgl_rec()+"  ("+barangs.get(i).getTagtran()+")");
       // barangViewHolder.barangKeterangan.setText("Grade:"+barangs.get(i).getSgd()+" / P :"+barangs.get(i).getPanjang()+" / L :"+barangs.get(i).getLebar()+" / T :"+barangs.get(i).getTebal()+" /SW : "+barangs.get(i).getGroup_sw());
        barangViewHolder.barangKeterangan.setText("Nomor Papan:"+String.valueOf(barangs.get(i).getNourut()));
        barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getId()));

    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangSawmill_barc getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        TextView barangNourut;

        TextView barangBarcode;

        TextView barangKeterangan;

        TextView barangTgl;

        TextView barangIDNO;

        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            barangNourut = (TextView) itemView.findViewById(R.id.textNourut);
            barangKeterangan = (TextView) itemView.findViewById(R.id.textKeterangan);
            barangBarcode = (TextView) itemView.findViewById(R.id.textViewRowbarcode);
            barangTgl = (TextView) itemView.findViewById(R.id.textViewRowTgl);
            barangIDNO = (TextView) itemView.findViewById(R.id.textViewRowIDNO);

        }

    }

}
