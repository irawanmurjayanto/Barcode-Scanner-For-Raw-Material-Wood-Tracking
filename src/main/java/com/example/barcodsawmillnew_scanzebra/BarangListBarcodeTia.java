package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangListBarcodeTia implements Serializable{


    private String barcodelog="";
    private String barcodelpotong="";
    private String tgl="";
    private String curskau="";

    public String getCurskau() {
        return curskau;
    }

    public void setCurskau(String curskau) {
        this.curskau = curskau;
    }

    public String getBarcodelog() {
        return barcodelog;
    }

    public void setBarcodelog(String barcodelog) {
        this.barcodelog = barcodelog;
    }

    public String getBarcodelpotong() {
        return barcodelpotong;
    }

    public void setBarcodelpotong(String barcodelpotong) {
        this.barcodelpotong = barcodelpotong;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

/* @Override

    public String toString() {

        return "Barang{" +
        "id='" + id + '\'' +
        ", kode='" + kode + '\'' +
        ", nama='" + nama + '\'' +
        ", harga='" + harga + '\'' +
                ", harga_beli='" + harga_beli + '\'' +
        '}';

    }*/

}
