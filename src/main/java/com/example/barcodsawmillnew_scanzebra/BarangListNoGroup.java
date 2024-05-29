package com.example.barcodsawmillnew_scanzebra;

import java.io.Serializable;

public class BarangListNoGroup implements Serializable{





    private String nogroup="";

    public BarangListNoGroup(String nogroup) {
        this.nogroup = nogroup;
    }

    public String getNogroup() {
        return nogroup;
    }

    public void setNogroup(String nogroup) {
        this.nogroup = nogroup;
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
