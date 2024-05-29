package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangListBarcodeTia_delgroup implements Serializable{


    private String nogroup="";
    private int linknourut=0;
    private String tgl="";


    public BarangListBarcodeTia_delgroup(String nogroup, int linknourut, String tgl ) {
        this.nogroup = nogroup;
        this.linknourut = linknourut;
        this.tgl = tgl;

    }

    public String getNogroup() {
        return nogroup;
    }

    public void setNogroup(String nogroup) {
        this.nogroup = nogroup;
    }

    public int getLinknourut() {
        return linknourut;
    }

    public void setLinknourut(int linknourut) {
        this.linknourut = linknourut;
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
