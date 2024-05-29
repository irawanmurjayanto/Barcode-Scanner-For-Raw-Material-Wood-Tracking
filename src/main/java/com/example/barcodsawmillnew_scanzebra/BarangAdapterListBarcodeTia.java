package com.example.barcodsawmillnew_scanzebra;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class BarangAdapterListBarcodeTia extends RecyclerView.Adapter<BarangAdapterListBarcodeTia.BarangViewHolder> {



    List<BarangListBarcodeTia> barangs;
    public BarangAdapterListBarcodeTia(List<BarangListBarcodeTia> barangs) {

        this.barangs = barangs;}

    @Override
    public BarangViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_barang_adapter_listbarcodetia, viewGroup,false);

        BarangViewHolder barangViewHolder = new BarangViewHolder(v);

        return barangViewHolder;

    }

    @Override

    public void onBindViewHolder(BarangViewHolder barangViewHolder, int i) {

        //SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
       // Date date = format.parse(bundle.getString("starttime"));

      //  SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

     //   dateFormatter = new SimpleDateFormat("dd-MM-yyyyHH-mm-ss", Locale.US);
       // Date currentTime = Calendar.getInstance().getTime();
       // textnoreff.setText(a1 + dateFormatter.format(newDate.getTime()))

        //format.format(Date.parse("Your date string"));

    /*    try {
            Date date = df.parse(barangs.get(i).getTgl_rec());
            barangViewHolder.barangTgl.setText(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }*/

     //   String dateTime = df.format(String.valueOf(barangs.get(i).getTgl_rec()));

        barangViewHolder.barangBarcodeLog.setText(String.valueOf(barangs.get(i).getBarcodelog()));

        barangViewHolder.barangBarcodePotong.setText(barangs.get(i).getBarcodelpotong());

        barangViewHolder.barangCurskau.setText(barangs.get(i).getCurskau());

        barangViewHolder.barangTglLog.setText(String.valueOf(barangs.get(i).getTgl()));



     //   barangViewHolder.barangGroup.setText(barangs.get(i).getGroupho());

      //  barangViewHolder.barangSwitch.setChecked(barangs.get(i).getSwitch());

    }

    @Override

    public int getItemCount() {

        return barangs.size();

    }

    public BarangListBarcodeTia getItem(int position) {

        return barangs.get(position);

    }

    @Override

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

    }

    public static class BarangViewHolder extends RecyclerView.ViewHolder {

        CardView cv;


        TextView barangBarcodeLog;
        TextView barangBarcodePotong;
        TextView barangCurskau;
        TextView barangTglLog;





      //  TextView barangGroup;

     //   Switch barangSwitch;



        BarangViewHolder(View itemView) {

            super(itemView);

            cv = (CardView) itemView.findViewById(R.id.cv);



            barangBarcodeLog= (TextView) itemView.findViewById(R.id.textBarcodeLog);
            barangBarcodePotong= (TextView) itemView.findViewById(R.id.textBarcodePot);
            barangCurskau= (TextView) itemView.findViewById(R.id.textCurskau);
            barangTglLog = (TextView) itemView.findViewById(R.id.textTglLog);





        //    barangGroup = (TextView) itemView.findViewById(R.id.textGroupho);

         //   barangSwitch=(Switch)  itemView.findViewById(R.id.switch1);

        }

    }


}