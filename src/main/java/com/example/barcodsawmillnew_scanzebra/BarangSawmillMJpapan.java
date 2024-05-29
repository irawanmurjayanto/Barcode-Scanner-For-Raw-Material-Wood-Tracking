package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangSawmillMJpapan implements Serializable{

    private int id=0;
    private String supplier="";
    private String noref="";
    private String tgl_rec="";
    private String tipetrans="";
    private int tagtran=0;
    private String skau="";

    public BarangSawmillMJpapan(int id, String supplier, String noref, String tgl_rec, String tipetrans, int tagtran, String skau) {
        this.id = id;
        this.supplier = supplier;
        this.noref = noref;
        this.tgl_rec = tgl_rec;
        this.tipetrans = tipetrans;
        this.tagtran = tagtran;
        this.skau = skau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getNoref() {
        return noref;
    }

    public void setNoref(String noref) {
        this.noref = noref;
    }

    public String getTgl_rec() {
        return tgl_rec;
    }

    public void setTgl_rec(String tgl_rec) {
        this.tgl_rec = tgl_rec;
    }

    public String getTipetrans() {
        return tipetrans;
    }

    public void setTipetrans(String tipetrans) {
        this.tipetrans = tipetrans;
    }

    public int getTagtran() {
        return tagtran;
    }

    public void setTagtran(int tagtran) {
        this.tagtran = tagtran;
    }

    public String getSkau() {
        return skau;
    }

    public void setSkau(String skau) {
        this.skau = skau;
    }

    /*
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
