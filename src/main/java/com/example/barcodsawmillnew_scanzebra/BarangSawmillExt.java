package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangSawmillExt implements Serializable{

    private int id=0;
    private double volume=0.0;
    private int idno_link1=0;
    private String sawmill="";
    private String supplier_name="";
    private String tipe_kayu="";
    private int no_log=0;
    private double panjang=0;
    private double lebar=0;
    private double tinggi=0;
    private double diameter=0;
    private String tgl_beli="";
    private int jumbar=0;
    private int tagtran=0;
    private String barcodeheader="";

    public BarangSawmillExt(int id, double volume, int idno_link1, String sawmill, String supplier_name, String tipe_kayu, int no_log, double panjang, double lebar, double tinggi, double diameter, String tgl_beli, int jumbar, int tagtran, String barcodeheader) {
        this.id = id;
        this.volume = volume;
        this.idno_link1 = idno_link1;
        this.sawmill = sawmill;
        this.supplier_name = supplier_name;
        this.tipe_kayu = tipe_kayu;
        this.no_log = no_log;
        this.panjang = panjang;
        this.lebar = lebar;
        this.tinggi = tinggi;
        this.diameter = diameter;
        this.tgl_beli = tgl_beli;
        this.jumbar = jumbar;
        this.tagtran = tagtran;
        this.barcodeheader = barcodeheader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getIdno_link1() {
        return idno_link1;
    }

    public void setIdno_link1(int idno_link1) {
        this.idno_link1 = idno_link1;
    }

    public String getSawmill() {
        return sawmill;
    }

    public void setSawmill(String sawmill) {
        this.sawmill = sawmill;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getTipe_kayu() {
        return tipe_kayu;
    }

    public void setTipe_kayu(String tipe_kayu) {
        this.tipe_kayu = tipe_kayu;
    }

    public int getNo_log() {
        return no_log;
    }

    public void setNo_log(int no_log) {
        this.no_log = no_log;
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

    public double getTinggi() {
        return tinggi;
    }

    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }

    public double getDiameter() {
        return diameter;
    }

    public void setDiameter(double diameter) {
        this.diameter = diameter;
    }

    public String getTgl_beli() {
        return tgl_beli;
    }

    public void setTgl_beli(String tgl_beli) {
        this.tgl_beli = tgl_beli;
    }

    public int getJumbar() {
        return jumbar;
    }

    public void setJumbar(int jumbar) {
        this.jumbar = jumbar;
    }

    public int getTagtran() {
        return tagtran;
    }

    public void setTagtran(int tagtran) {
        this.tagtran = tagtran;
    }

    public String getBarcodeheader() {
        return barcodeheader;
    }

    public void setBarcodeheader(String barcodeheader) {
        this.barcodeheader = barcodeheader;
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
