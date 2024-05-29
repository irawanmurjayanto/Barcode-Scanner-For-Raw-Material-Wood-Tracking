package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangSawmill_barc implements Serializable{

    private int id=0;
    private int idno_link1=0;
    private String barcode="";
    private double tebal=0;
    private double panjang=0;
    private double lebar=0;
    private String sgd="";
    private String group_sw="";
    private String group_panjang="";
    private int tagtran=0;
    private String tgl_rec="";
    private int nourut=0;

    public BarangSawmill_barc(int id, int idno_link1, String barcode, double tebal, double panjang, double lebar, String sgd, String group_sw, String group_panjang, int tagtran, String tgl_rec, int nourut) {
        this.id = id;
        this.idno_link1 = idno_link1;
        this.barcode = barcode;
        this.tebal = tebal;
        this.panjang = panjang;
        this.lebar = lebar;
        this.sgd = sgd;
        this.group_sw = group_sw;
        this.group_panjang = group_panjang;
        this.tagtran = tagtran;
        this.tgl_rec = tgl_rec;
        this.nourut = nourut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdno_link1() {
        return idno_link1;
    }

    public void setIdno_link1(int idno_link1) {
        this.idno_link1 = idno_link1;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public double getTebal() {
        return tebal;
    }

    public void setTebal(double tebal) {
        this.tebal = tebal;
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

    public String getSgd() {
        return sgd;
    }

    public void setSgd(String sgd) {
        this.sgd = sgd;
    }

    public String getGroup_sw() {
        return group_sw;
    }

    public void setGroup_sw(String group_sw) {
        this.group_sw = group_sw;
    }

    public String getGroup_panjang() {
        return group_panjang;
    }

    public void setGroup_panjang(String group_panjang) {
        this.group_panjang = group_panjang;
    }

    public int getTagtran() {
        return tagtran;
    }

    public void setTagtran(int tagtran) {
        this.tagtran = tagtran;
    }

    public String getTgl_rec() {
        return tgl_rec;
    }

    public void setTgl_rec(String tgl_rec) {
        this.tgl_rec = tgl_rec;
    }

    public int getNourut() {
        return nourut;
    }

    public void setNourut(int nourut) {
        this.nourut = nourut;
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
