package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

public class BarangAdapterUserpass extends RecyclerView.Adapter<BarangAdapterUserpass.BarangViewHolder> {

    List<BarangUserpass> barangs;
    public BarangAdapterUserpass(List<BarangUserpass> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_userpass, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {

        DecimalFormat formatter = new DecimalFormat("#,###,###");

        barangViewHolder.barangUser.setText(barangs.get(i).getUser());
        barangViewHolder.barangPass.setText(barangs.get(i).getPass());

   //     barangViewHolder.barangTgl.setText(barangs.get(i).getTgl_rec()+"  ("+barangs.get(i).getTagtran()+")");
       // barangViewHolder.barangKeterangan.setText("Grade:"+barangs.get(i).getSgd()+" / P :"+barangs.get(i).getPanjang()+" / L :"+barangs.get(i).getLebar()+" / T :"+barangs.get(i).getTebal()+" /SW : "+barangs.get(i).getGroup_sw());
      //  barangViewHolder.barangKeterangan.setText("Nomor Papan:"+String.valueOf(barangs.get(i).getNourut()));
       // barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getId()));

    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangUserpass getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        TextView barangUser;

        TextView barangPass;


        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            barangUser = (TextView) itemView.findViewById(R.id.textUser);
            barangPass = (TextView) itemView.findViewById(R.id.textPass);

        }

    }

}
