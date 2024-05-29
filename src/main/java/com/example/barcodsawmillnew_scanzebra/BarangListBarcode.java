package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangListBarcode implements Serializable{

    private int id=0;



    private String barcode="";

    private String nolog="";


    public BarangListBarcode(int id, String barcode,String nolog) {
        this.id = id;
        this.barcode = barcode;
        this.nolog = nolog;
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

    public void setBarcode(String nolog) {
        this.nolog = nolog;
    }

    public String getNoLog() {
        return nolog;
    }

    public void setNolog(String nolog) {
        this.nolog = nolog;
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
