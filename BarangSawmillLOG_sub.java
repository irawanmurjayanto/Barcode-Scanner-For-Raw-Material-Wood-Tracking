package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangSawmillLOG_sub implements Serializable{

    private int id=0;

    private String noref="";

    private String barcode="";

    private String supplier="";

    private String tipetrans="";

    private String tglrec="";

    private int tagtran=0;

    public BarangSawmillLOG_sub(int id, String noref, String barcode, String supplier, String tipetrans, String tglrec, int tagtran) {
        this.id = id;
        this.noref = noref;
        this.barcode = barcode;
        this.supplier = supplier;
        this.tipetrans = tipetrans;
        this.tglrec = tglrec;
        this.tagtran = tagtran;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoref() {
        return noref;
    }

    public void setNoref(String noref) {
        this.noref = noref;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTipetrans() {
        return tipetrans;
    }

    public void setTipetrans(String tipetrans) {
        this.tipetrans = tipetrans;
    }

    public String getTglrec() {
        return tglrec;
    }

    public void setTglrec(String tglrec) {
        this.tglrec = tglrec;
    }

    public int getTagtran() {
        return tagtran;
    }

    public void setTagtran(int tagtran) {
        this.tagtran = tagtran;
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
