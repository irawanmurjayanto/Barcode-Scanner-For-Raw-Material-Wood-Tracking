package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class BarangAdapterSawmill extends RecyclerView.Adapter<BarangAdapterSawmill.BarangViewHolder> {

    List<BarangSawmill> barangs;
    public BarangAdapterSawmill(List<BarangSawmill> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_barangbarcodenoreflokal, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        DecimalFormat formatter = new DecimalFormat("#,###.####");
       // DecimalFormat formatter = new DecimalFormat("#,##0.0#");

        barangViewHolder.barangIdno.setText(String.valueOf(barangs.get(i).getId()));
        barangViewHolder.barangIdno_link1.setText(String.valueOf(barangs.get(i).getIdno_link1()));

         //Double hasil = Math.round(barangs.get(i).getJumlahkubik() ) / 1000000;
           String statusnew="";
    //    barangViewHolder.barangJumlah.setText(String.valueOf(barangs.get(i).getJumbarc()+" / "+));
        if (barangs.get(i).getTagtran()==2)
        {
           statusnew = "Process";
        }else  if (barangs.get(i).getTagtran()==1)
        {
            statusnew = "Completed";
        }else if (barangs.get(i).getTagtran()==0)
        {
            statusnew = "None";
        }

        barangViewHolder.barangVolume.setText(String.valueOf(barangs.get(i).getVolume())+" m3");
        barangViewHolder.barangSpname.setText(String.valueOf(barangs.get(i).getSupplier_name()));
        barangViewHolder.barangTipekayu.setText(String.valueOf(barangs.get(i).getTipe_kayu()));
        barangViewHolder.barangNolog.setText(" No. LOG: "+String.valueOf(barangs.get(i).getNo_log()));
        barangViewHolder.barangSawmill.setText("SW : "+String.valueOf(barangs.get(i).getSawmill()+" / Tgl. Beli: "+barangs.get(i).getTgl_beli()));
        barangViewHolder.barangStatus.setText(" Status: "+String.valueOf(statusnew));
        barangViewHolder.barangQtypapan.setText("Qty Papan : "+barangs.get(i).getJumbar());

       // barangViewHolder.barangTagTran.setText(barangs.get(i).getTagtran());

    /*     if (Integer.valueOf(barangs.get(i).getTagtran())==1) {
            barangViewHolder.barangTagTran.setText("NO");
        }else
        {
            barangViewHolder.barangTagTran.setText("Uploaded");
        }
     */

      //  barangViewHolder.barangTgl.setText(barangs.get(i).getTglrec());

        //barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getId()));

    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangSawmill getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;

        TextView barangIdno;
        TextView barangIdno_link1;
        TextView barangVolume;
        TextView barangSawmill;
        TextView barangSpname;
        TextView barangTipekayu;
        TextView barangNolog;
        TextView barangStatus;
        TextView barangQtypapan;



        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);

            barangIdno = (TextView) itemView.findViewById(R.id.textIdno);
            barangIdno_link1 = (TextView) itemView.findViewById(R.id.textIdno_link1);
            barangVolume = (TextView) itemView.findViewById(R.id.textVolume);
            barangSpname = (TextView) itemView.findViewById(R.id.textSpname);
            barangSawmill = (TextView) itemView.findViewById(R.id.textSawmill);
            barangTipekayu = (TextView) itemView.findViewById(R.id.textTipekayu);
            barangNolog = (TextView) itemView.findViewById(R.id.textNolog);
            barangStatus = (TextView) itemView.findViewById(R.id.textStatus);
            barangQtypapan = (TextView) itemView.findViewById(R.id.textQtypapan);

        }

    }

}
