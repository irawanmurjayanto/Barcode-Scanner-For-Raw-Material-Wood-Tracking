package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangListReviewBarcode implements Serializable{

    private String barcode="";
    private String nourut1="";
    private String nourut2="";
    private String nolog="";
    private String noskau="";
    private String tgl="";
    private String hit="";

    public String getHit() {
        return hit;
    }

    public void setHit(String hit) {
        this.hit = hit;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getNourut1() {
        return nourut1;
    }

    public void setNourut1(String nourut1) {
        this.nourut1 = nourut1;
    }

    public String getNourut2() {
        return nourut2;
    }

    public void setNourut2(String nourut2) {
        this.nourut2 = nourut2;
    }

    public String getNolog() {
        return nolog;
    }

    public void setNolog(String nolog) {
        this.nolog = nolog;
    }

    public String getNoskau() {
        return noskau;
    }

    public void setNoskau(String noskau) {
        this.noskau = noskau;
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
