package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangBarcode implements Serializable{

    private int id=0;

    private String barcode="";

    private String tglrec="";

    private String tagtran="";

    private String SGD="";

    private String Nolog="";

    private double panjang=0.00;
    private double lebar=0.00;
    private double tebal=0.00;

    public BarangBarcode(int id, String barcode, String tglrec, String tagtran, String SGD, double panjang, double lebar, double tebal,String Nolog) {
        this.id = id;
        this.barcode = barcode;
        this.tglrec = tglrec;
        this.tagtran = tagtran;
        this.SGD = SGD;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tebal = tebal;
        this.Nolog = Nolog;
    }


    public String getNolog() {
        return Nolog;
    }

    public void setNolog(String nolog) {
        Nolog = nolog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTglrec() {
        return tglrec;
    }

    public void setTglrec(String tglrec) {
        this.tglrec = tglrec;
    }

    public String getTagtran() {
        return tagtran;
    }

    public void setTagtran(String tagtran) {
        this.tagtran = tagtran;
    }

    public String getSGD() {
        return SGD;
    }

    public void setSGD(String SGD) {
        this.SGD = SGD;
    }

    public double getPanjang() {
        return panjang;
    }

    public void setPanjang(double panjang) {
        this.panjang = panjang;
    }

    public double getLebar() {
        return lebar;
    }

    public void setLebar(double lebar) {
        this.lebar = lebar;
    }

    public double getTebal() {
        return tebal;
    }

    public void setTebal(double tebal) {
        this.tebal = tebal;
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
