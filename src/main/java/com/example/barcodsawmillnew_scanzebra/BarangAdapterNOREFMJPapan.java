package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class BarangAdapterNOREFMJPapan extends RecyclerView.Adapter<BarangAdapterNOREFMJPapan.BarangViewHolder> {

    List<BarangSawmillMJpapan> barangs;
    public BarangAdapterNOREFMJPapan(List<BarangSawmillMJpapan> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_barangbarcode_logl_mjpapan, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {
        Calendar newDate = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy_HHmmss", Locale.US);
        android.text.format.DateFormat df = new android.text.format.DateFormat();
       // SimpleDateFormat formattgl = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formattgl = new SimpleDateFormat("dd/MM/yyyy");
        DecimalFormat formatter = new DecimalFormat("#,###.####");
       // DecimalFormat formatter = new DecimalFormat("#,##0.0#");

    /*    barangViewHolder.barangNoref.setText(barangs.get(i).getNoref());
        barangViewHolder.barangTgl.setText(barangs.get(i).getTgl_rec());

         //Double hasil = Math.round(barangs.get(i).getJumlahkubik() ) / 1000000;

    //    barangViewHolder.barangJumlah.setText(String.valueOf(barangs.get(i).getJumbarc()+" / "+));
        barangViewHolder.barangTujuan.setText("Jumlah Kubikasi : "+formatter.format(barangs.get(i).getJumlahkubik())+ " m3 / Jumlah Barcode :"+barangs.get(i).getJumbarc());

       // barangViewHolder.barangTagTran.setText(barangs.get(i).getTagtran());

         if (Integer.valueOf(barangs.get(i).getTagtran())==1) {
            barangViewHolder.barangTagTran.setText("NO");
        }else
        {
            barangViewHolder.barangTagTran.setText("Uploaded");
        }
      //  barangViewHolder.barangTgl.setText(barangs.get(i).getTglrec());

        //barangViewHolder.barangIDNO.setText(String.valueOf(barangs.get(i).getId()));
*/
        barangViewHolder.barangSkau.setText(String.valueOf(barangs.get(i).getSkau()));
        barangViewHolder.barangIdno.setText(String.valueOf(barangs.get(i).getId()));
        barangViewHolder.barangNoref.setText(String.valueOf(barangs.get(i).getNoref()));
       // String tgl=formattgl.format(Date.parse(barangs.get(i).getTgl_rec().toString()));
        //String tgl=dateFormatter.format(Date.valueOf(barangs.get(i).getTgl_rec()));
        barangViewHolder.barangTgl.setText(barangs.get(i).getTgl_rec());
        barangViewHolder.barangTypeTrans.setText(String.valueOf(barangs.get(i).getTipetrans()));


            barangViewHolder.barangVendor.setText(String.valueOf(barangs.get(i).getSupplier()));
            // barangViewHolder.barangLokasi.setText(String.valueOf(barangs.get(i).getLokasi()));

        if (barangs.get(i).getTagtran()==2)
        {
            barangViewHolder.barangStatus.setText("Process");
        }else {
            barangViewHolder.barangStatus.setText("Completed");
        }
    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangSawmillMJpapan getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        TextView barangSkau;
        TextView barangIdno;
        TextView barangNoref;
        TextView barangTgl;
        TextView barangTypeTrans;
        TextView barangVendor;
        TextView barangStatus;



        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);
            barangSkau = (TextView) itemView.findViewById(R.id.textSkau);
            barangIdno = (TextView) itemView.findViewById(R.id.textIdno);
            barangNoref = (TextView) itemView.findViewById(R.id.textNoref);
            barangTgl = (TextView) itemView.findViewById(R.id.textTgl);
            barangTypeTrans = (TextView) itemView.findViewById(R.id.textTypetrans);
            barangVendor = (TextView) itemView.findViewById(R.id.textVendor);
            barangStatus = (TextView) itemView.findViewById(R.id.textStatus);
       //     barangLokasi = (TextView) itemView.findViewById(R.id.textLokasi);

        }

    }

}
