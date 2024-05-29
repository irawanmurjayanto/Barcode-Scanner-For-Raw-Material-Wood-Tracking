package com.example.barcodsawmillnew_scanzebra;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public class ADB_Master extends SQLiteOpenHelper{

    //InnerClass, untuk mengatur artibut seperti Nama Tabel, nama-nama kolom dan Query
    static abstract class MyColumns_item implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_barang";
        static final String idno = "idno";
        static final String kode = "kode";
        static final String nama = "nama";
        static final String harga = "harga";
        static final String harga_beli = "harga_beli";
        static final String tgl_rec = "tgl_rec";

    }



    static abstract class MyColumns_tokoprofile implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "toko_profile";
        static final String idno = "idno";
        static final String nama_toko = "nama_toko";
        static final String alamat_toko = "alamat_toko";
        static final String no_hp_toko = "no_hp_toko";
        static final String tgl_rec = "tgl_rec";

    }

    static abstract class MyColumns_sawmilllokasi implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmilllokasi";
        static final String idno = "idno";
        static final String nama_lokasi = "nama_lokasi";
        static final String alamat_lokasi = "alamat_lokai";
    }


    static abstract class MyColumns_sawmill_oven implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmill_oven";
        static final String idno = "idno";
        static final String nama_lokasi = "nama_lokasi";
        static final String alamat_lokasi = "alamat_lokai";
    }

    static abstract class MyColumns_sawmill_wrh implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmill_wrh";
        static final String idno = "idno";
        static final String nama_lokasi = "nama_lokasi";
        static final String alamat_lokasi = "alamat_lokai";
    }

    static abstract class MyColumns_sawmill_factory implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmill_factory";
        static final String idno = "idno";
        static final String nama_lokasi = "nama_lokasi";
        static final String alamat_lokasi = "alamat_lokai";
    }

    static abstract class MyColumns_sawmilltebal implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmilltebal";
        static final String idno = "idno";
        static final String tebal = "tebal";
    }

    static abstract class MyColumns_sawmillsgd implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmillsgd";
        static final String idno = "idno";
        static final String nama_sgd = "namasgd";
    }

    static abstract class MyColumns_sawmilltemp implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmilltemp";
        static final String idno = "idno";
        static final String nama_user = "user";
        static final String nama_user_short = "nama_user_short";
        static final String nama_lokasi = "lokasi";
        static final String nama_lainnya = "lainnya";
    }

    static abstract class MyColumns_sawmilluser implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmilluser";
        static final String idno = "idno";
        static final String nama_user = "user";
        static final String nama_pass = "pass";
        static final String nama_ket = "ket";
    }

    static abstract class MyColumns_sawmilluser_sub implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sawmilluser_sub";
        static final String idno = "idno";
        static final String user_name = "user_name";
        static final String module = "module";
        static final String ket = "ket";
    }

    static abstract class MyColumns_sawmill_swgroup implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sw_group";
        static final String idno = "idno";
        static final String kode_lokasi = "kode_lokasi";
        static final String nama_lokasi = "nama_lokasi";
    }


    static abstract class MyColumns_sawmill_shortlong implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sw_shortlong";
        static final String idno = "idno";
        static final String kode_sl = "kode_sl";
        static final String keterangan = "keterangan";
    }








    static abstract class MyColumns_barcode implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_barctran";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String noref = "noref";
        static final String typetran = "typetran";
        static final String lokoven = "lokoven";
        static final String namaoven = "namaoven";
        static final String namaoven2 = "namaoven2";
        static final String tebal = "tebal";
        static final String sgd = "sgd";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String temp5 = "temp5";
        static final String tagtran = "tagtran";
        static final String tgl_rec = "tgl_rec";

    }


    static abstract class MyColumns_newbarcode implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String idnn_link2 = "idno_link2";
        static final String nourut = "nourut";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String sgd = "sgd";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String group_sw = "group_sw";
        static final String group_panjang = "group_panjang";
        static final String headerbarcode = "headerbarcode";
        static final String tgl_rec = "tgl_rec";

    }

    static abstract class MyColumns_newbarcode_log implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_log";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String skau = "skau";
        static final String nourut = "nourut";
        static final String tgl_rec = "tgl_rec";

    }


    static abstract class MyColumns_newbarcode_checkin implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_checkin";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tgl_rec = "tgl_rec";

    }

    static abstract class MyColumns_newbarcode_wrh implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_wrh";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tujuan = "tujuan";
        static final String tgl_rec = "tgl_rec";

    }

    static abstract class MyColumns_newbarcode_swout implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_swout";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tujuan = "tujuan";
        static final String tgl_rec = "tgl_rec";

    }


    static abstract class MyColumns_newbarcode_mjpapan implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_mjpapan";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tujuan = "tujuan";
        static final String no_papan = "no_papan";
        static final String skau = "skau";
        static final String nourut = "nourut";
        static final String tgl_rec = "tgl_rec";

    }


    static abstract class MyColumns_newbarcode_oven implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_oven";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tujuan = "tujuan";
        static final String tgl_rec = "tgl_rec";

    }



    static abstract class MyColumns_newbarcode_factory implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_newbarcode_factory";
        static final String idno = "idno";
        static final String barcode = "barcode";
        static final String idnn_link1 = "idno_link1";
        static final String noref = "noref";
        static final String tebal = "tebal";
        static final String lokasi = "lokasi";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tagtran = "tagtran";
        static final String supplier = "supplier";
        static final String typetran = "typetran";
        static final String tujuan = "tujuan";
        static final String tgl_rec = "tgl_rec";

    }


    static abstract class MyColumns_headerbarcode implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_headerbarcode";
        static final String idno = "idno";
        static final String idno_link1 = "idno_link1";
        static final String idno_link2 = "idno_link2";
        static final String kode_spp = "kode_spp";
        static final String nama_spp = "nama_spp";
        static final String tgl_beli = "tgl_beli";
        static final String volume = "volume";
        static final String sku = "sku";
        static final String jum_glond = "jum_glond";
        static final String tgl_oven = "tgl_oven";
        static final String tipe_kayu = "tipe_kayu";
        static final String sawmill = "sawmill";
        static final String no_log = "no_log";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tinggi = "tinggi";
        static final String diameter = "diameter";
        static final String barcodeheader = "barcodeheader";
        static final String tgl_rec = "tgl_rec";


    }


    static abstract class MyColumns_headertempbarcode implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "tbl_headertempbarcode";
        static final String idno = "idno";
        static final String idno_link1 = "idno_link1";
        static final String idno_link2 = "idno_link2";
        static final String kode_spp = "kode_spp";
        static final String nama_spp = "nama_spp";
        static final String tgl_beli = "tgl_beli";
        static final String volume = "volume";
        static final String sku = "sku";
        static final String jum_glond = "jum_glond";
        static final String tgl_oven = "tgl_oven";
        static final String tipe_kayu = "tipe_kayu";
        static final String sawmill = "sawmill";
        static final String no_log = "no_log";
        static final String panjang = "panjang";
        static final String lebar = "lebar";
        static final String tinggi = "tinggi";
        static final String diameter = "diameter";
        static final String tgl_rec = "tgl_rec";


    }


    static abstract class MyColumns_sawmill_basesupplier implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sw_basesupplier";
        static final String idno = "idno";
        static final String supplier = "supplier";
        static final String keterangan = "keterangan";
    }

    static abstract class MyColumns_sawmill_tipekayu implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sw_tipekayu";
        static final String idno = "idno";
        static final String tipe_kayu = "tipe_kayu";
        static final String keterangan = "keterangan";
    }

    static abstract class MyColumns_sawmill_datafilter implements BaseColumns{
        //Menentukan Nama Table dan Kolom
        static final String NamaTabel = "sw_datafilter";
        static final String idno = "idno";
        static final String tipe_kayu = "tipe_kayu";
        static final String supplier = "supplier";
        static final String m3 = "m3";
        static final String no_log = "no_log";
        static final String tgl_rec = "tgl_rec";
    }

//temp1=tebal,temp2=sgd,temp3=panjang,temp4=lebar

    private static final String NamaDatabase = "master.db";
    private static final int VersiDatabase = 41;

    //Query yang digunakan untuk membuat Tabel
    //batas sawmill



    //sw_baselokasi
    private static final String SQL_CREATE_ENTRIES_sawmill_basesupplier = "CREATE TABLE "+ MyColumns_sawmill_basesupplier.NamaTabel+
            "("+  MyColumns_sawmill_basesupplier.idno+" INTEGER PRIMARY KEY, "+  MyColumns_sawmill_basesupplier.supplier+" TEXT , "+  MyColumns_sawmill_basesupplier.keterangan+" TEXT )";
     //tipe_kayu
    private static final String SQL_CREATE_ENTRIES_sawmill_tipekayu = "CREATE TABLE "+ MyColumns_sawmill_tipekayu.NamaTabel+
            "("+ MyColumns_sawmill_tipekayu.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_tipekayu.tipe_kayu+" TEXT , "+ MyColumns_sawmill_tipekayu.keterangan+" TEXT )";

    //data_filter
    private static final String SQL_CREATE_ENTRIES_datafilter = "CREATE TABLE "+ MyColumns_sawmill_datafilter.NamaTabel+
            "("+ MyColumns_sawmill_datafilter.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_datafilter.supplier+" TEXT , "+ MyColumns_sawmill_datafilter.tipe_kayu+
            " TEXT, "+ MyColumns_sawmill_datafilter.m3+" DOUBLE,"+ MyColumns_sawmill_datafilter.no_log+" INTEGER ,"
            + MyColumns_sawmill_datafilter.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";


//lokasisawmill

    private static final String SQL_CREATE_ENTRIES_newbarcode_factory = "CREATE TABLE "+ MyColumns_newbarcode_factory.NamaTabel+
            "("+ MyColumns_newbarcode_factory.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_factory.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_factory.idnn_link1+
            " INTEGER, "+ MyColumns_newbarcode_factory.tebal+" DOUBLE,"+ MyColumns_newbarcode_factory.lokasi+" TEXT ,"
            + MyColumns_newbarcode_factory.panjang+" DOUBLE,"+ MyColumns_newbarcode_factory.lebar+" DOUBLE,"
            + MyColumns_newbarcode_factory.tagtran+" INTEGER ,"+ MyColumns_newbarcode_factory.supplier+" TEXT,"
            + MyColumns_newbarcode_factory.typetran+" TEXT ,"+ MyColumns_newbarcode_factory.noref+" TEXT,"
            + MyColumns_newbarcode_factory.tujuan+" TEXT,"
            + MyColumns_newbarcode_factory.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

private static final String SQL_CREATE_ENTRIES_newbarcode_wrh = "CREATE TABLE "+ MyColumns_newbarcode_wrh.NamaTabel+
        "("+ MyColumns_newbarcode_wrh.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_wrh.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_wrh.idnn_link1+
        " INTEGER, "+ MyColumns_newbarcode_wrh.tebal+" DOUBLE,"+ MyColumns_newbarcode_wrh.lokasi+" TEXT ,"
        + MyColumns_newbarcode_wrh.panjang+" DOUBLE,"+ MyColumns_newbarcode_wrh.lebar+" DOUBLE,"
        + MyColumns_newbarcode_wrh.tagtran+" INTEGER ,"+ MyColumns_newbarcode_wrh.supplier+" TEXT,"
        + MyColumns_newbarcode_wrh.typetran+" TEXT ,"+ MyColumns_newbarcode_wrh.noref+" TEXT,"
        + MyColumns_newbarcode_wrh.tujuan+" TEXT,"
        + MyColumns_newbarcode_wrh.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";


    private static final String SQL_CREATE_ENTRIES_newbarcode_swout = "CREATE TABLE "+ MyColumns_newbarcode_swout.NamaTabel+
            "("+ MyColumns_newbarcode_swout.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_swout.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_swout.idnn_link1+
            " INTEGER, "+ MyColumns_newbarcode_swout.tebal+" DOUBLE,"+ MyColumns_newbarcode_swout.lokasi+" TEXT ,"
            + MyColumns_newbarcode_swout.panjang+" DOUBLE,"+ MyColumns_newbarcode_swout.lebar+" DOUBLE,"
            + MyColumns_newbarcode_swout.tagtran+" INTEGER ,"+ MyColumns_newbarcode_swout.supplier+" TEXT,"
            + MyColumns_newbarcode_swout.typetran+" TEXT ,"+ MyColumns_newbarcode_swout.noref+" TEXT,"
            + MyColumns_newbarcode_swout.tujuan+" TEXT,"
            + MyColumns_newbarcode_swout.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";


    private static final String SQL_CREATE_ENTRIES_newbarcode_mjpapan = "CREATE TABLE "+ MyColumns_newbarcode_mjpapan.NamaTabel+
            "("+ MyColumns_newbarcode_mjpapan.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_mjpapan.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_mjpapan.idnn_link1+
            " INTEGER, "+ MyColumns_newbarcode_mjpapan.tebal+" DOUBLE,"+ MyColumns_newbarcode_mjpapan.lokasi+" TEXT ,"
            + MyColumns_newbarcode_mjpapan.panjang+" DOUBLE,"+ MyColumns_newbarcode_mjpapan.lebar+" DOUBLE,"
            + MyColumns_newbarcode_mjpapan.tagtran+" INTEGER ,"+ MyColumns_newbarcode_mjpapan.supplier+" TEXT,"
            + MyColumns_newbarcode_mjpapan.typetran+" TEXT ,"+ MyColumns_newbarcode_mjpapan.noref+" TEXT,"
            + MyColumns_newbarcode_mjpapan.tujuan+" TEXT,"+ MyColumns_newbarcode_mjpapan.no_papan+" INTEGER,"
            + MyColumns_newbarcode_mjpapan.skau+" TEXT,"+ MyColumns_newbarcode_mjpapan.nourut+" INTEGER,"
            + MyColumns_newbarcode_mjpapan.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_newbarcode_oven = "CREATE TABLE "+ MyColumns_newbarcode_oven.NamaTabel+
        "("+ MyColumns_newbarcode_oven.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_oven.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_oven.idnn_link1+
        " INTEGER, "+ MyColumns_newbarcode_oven.tebal+" DOUBLE,"+ MyColumns_newbarcode_oven.lokasi+" TEXT ,"
        + MyColumns_newbarcode_oven.panjang+" DOUBLE,"+ MyColumns_newbarcode_oven.lebar+" DOUBLE,"
        + MyColumns_newbarcode_oven.tagtran+" INTEGER ,"+ MyColumns_newbarcode_oven.supplier+" TEXT,"
        + MyColumns_newbarcode_oven.typetran+" TEXT ,"+ MyColumns_newbarcode_oven.noref+" TEXT,"
        + MyColumns_newbarcode_oven.tujuan+" TEXT,"
        + MyColumns_newbarcode_oven.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_newbarcode_log = "CREATE TABLE "+ MyColumns_newbarcode_log.NamaTabel+
            "("+ MyColumns_newbarcode_log.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_log.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_log.idnn_link1+
            " INTEGER, "+ MyColumns_newbarcode_log.tebal+" DOUBLE,"+ MyColumns_newbarcode_log.lokasi+" TEXT ,"
            + MyColumns_newbarcode_log.panjang+" DOUBLE,"+ MyColumns_newbarcode_log.lebar+" DOUBLE,"
            + MyColumns_newbarcode_log.tagtran+" INTEGER ,"+ MyColumns_newbarcode_log.supplier + " TEXT,"
            + MyColumns_newbarcode_log.typetran+" TEXT ,"+ MyColumns_newbarcode_log.noref+" TEXT,"
            + MyColumns_newbarcode_log.nourut +" INTEGER ,"+ MyColumns_newbarcode_log.skau+" TEXT,"
            + MyColumns_newbarcode_log.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_newbarcode_checkin = "CREATE TABLE "+ MyColumns_newbarcode_checkin.NamaTabel+
            "("+ MyColumns_newbarcode_checkin.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode_checkin.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode_checkin.idnn_link1+
            " INTEGER, "+ MyColumns_newbarcode_checkin.tebal+" DOUBLE,"+ MyColumns_newbarcode_checkin.lokasi+" TEXT ,"
            + MyColumns_newbarcode_checkin.panjang+" DOUBLE,"+ MyColumns_newbarcode_checkin.lebar+" DOUBLE,"
            + MyColumns_newbarcode_checkin.tagtran+" INTEGER ,"+ MyColumns_newbarcode_log.supplier + " TEXT,"
            + MyColumns_newbarcode_checkin.typetran+" TEXT ,"+ MyColumns_newbarcode_checkin.noref+" TEXT,"
            + MyColumns_newbarcode_checkin.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_newbarcode = "CREATE TABLE "+ MyColumns_newbarcode.NamaTabel+
            "("+ MyColumns_newbarcode.idno+" INTEGER PRIMARY KEY, "+ MyColumns_newbarcode.barcode+" TEXT NOT NULL, "+ MyColumns_newbarcode.idnn_link1+" TEXT , "+ MyColumns_newbarcode.idnn_link2+" INTEGER , "+ MyColumns_newbarcode.nourut+
            " INTEGER, "+ MyColumns_newbarcode.tebal+" DOUBLE,"+ MyColumns_newbarcode.sgd+" TEXT ,"
            + MyColumns_newbarcode.panjang+" DOUBLE,"+ MyColumns_newbarcode.lebar+" DOUBLE,"
            + MyColumns_newbarcode.tagtran+" INTEGER ,"+ MyColumns_newbarcode.group_sw+" TEXT,"
            + MyColumns_newbarcode.group_panjang+" TEXT ,"+ MyColumns_newbarcode.noref+" TEXT,"
            + MyColumns_newbarcode.headerbarcode+" TEXT,"
            + MyColumns_newbarcode.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_headerbarcode = "CREATE TABLE "+ MyColumns_headerbarcode.NamaTabel+
            "("+ MyColumns_headerbarcode.idno+" INTEGER PRIMARY KEY, "+ MyColumns_headerbarcode.idno_link1+" INTEGER, "+ MyColumns_headerbarcode.idno_link2+
            " INTEGER, "+ MyColumns_headerbarcode.kode_spp+" TEXT,"+ MyColumns_headerbarcode.nama_spp+" TEXT ,"
            + MyColumns_headerbarcode.tgl_beli+" TEXT,"+ MyColumns_headerbarcode.volume+" DOUBLE,"
            + MyColumns_headerbarcode.sku+" TEXT,"+ MyColumns_headerbarcode.jum_glond+" DOUBLE,"
            + MyColumns_headerbarcode.tgl_oven+" TEXT,"+ MyColumns_headerbarcode.tipe_kayu+" TEXT,"
            + MyColumns_headerbarcode.sawmill+" TEXT,"+ MyColumns_headerbarcode.no_log+" INTEGER,"
            + MyColumns_headerbarcode.panjang+" DOUBLE,"+ MyColumns_headerbarcode.lebar+" DOUBLE,"
            + MyColumns_headerbarcode.tinggi+" DOUBLE,"+ MyColumns_headerbarcode.diameter+" DOUBLE,"
            + MyColumns_headerbarcode.barcodeheader+" TEXT,"
            + MyColumns_headerbarcode.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";

    private static final String SQL_CREATE_ENTRIES_headertempbarcode = "CREATE TABLE "+ MyColumns_headertempbarcode.NamaTabel+
            "("+ MyColumns_headertempbarcode.idno+" INTEGER PRIMARY KEY, "+ MyColumns_headertempbarcode.idno_link1+" INTEGER, "+ MyColumns_headertempbarcode.idno_link2+
            " INTEGER, "+ MyColumns_headertempbarcode.kode_spp+" TEXT,"+ MyColumns_headertempbarcode.nama_spp+" TEXT ,"
            + MyColumns_headertempbarcode.tgl_beli+" TEXT,"+ MyColumns_headertempbarcode.volume+" DOUBLE,"
            + MyColumns_headertempbarcode.sku+" TEXT,"+ MyColumns_headertempbarcode.jum_glond+" DOUBLE,"
            + MyColumns_headertempbarcode.tgl_oven+" TEXT,"+ MyColumns_headertempbarcode.tipe_kayu+" TEXT,"
            + MyColumns_headertempbarcode.sawmill+" TEXT,"+ MyColumns_headertempbarcode.no_log+" INTEGER,"
            + MyColumns_headertempbarcode.panjang+" DOUBLE,"+ MyColumns_headertempbarcode.lebar+" DOUBLE,"
            + MyColumns_headertempbarcode.tinggi+" DOUBLE,"+ MyColumns_headertempbarcode.diameter+" DOUBLE,"
            + MyColumns_headertempbarcode.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";


//shortlong
    private static final String SQL_CREATE_ENTRIES_sawmillshortlong = "CREATE TABLE "+ MyColumns_sawmill_shortlong.NamaTabel+
            "("+ MyColumns_sawmill_shortlong.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_shortlong.kode_sl+" TEXT , "+ MyColumns_sawmill_shortlong.keterangan+" TEXT )";
//sw_group
private static final String SQL_CREATE_ENTRIES_sawmillswgroup = "CREATE TABLE "+ MyColumns_sawmill_swgroup.NamaTabel+
        "("+ MyColumns_sawmill_swgroup.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_swgroup.kode_lokasi+" TEXT , "+ MyColumns_sawmill_swgroup.nama_lokasi+" TEXT )";


    private static final String SQL_CREATE_ENTRIES_sawmilllokasi = "CREATE TABLE "+ MyColumns_sawmilllokasi.NamaTabel+
            "("+ MyColumns_sawmilllokasi.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmilllokasi.nama_lokasi+" TEXT NOT NULL, "+ MyColumns_sawmilllokasi.alamat_lokasi+" TEXT NOT NULL )";

    private static final String SQL_CREATE_ENTRIES_sawmill_oven = "CREATE TABLE "+ MyColumns_sawmill_oven.NamaTabel+
            "("+ MyColumns_sawmill_oven.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_oven.nama_lokasi+" TEXT NOT NULL, "+ MyColumns_sawmill_oven.alamat_lokasi+" TEXT NOT NULL )";

    private static final String SQL_CREATE_ENTRIES_sawmill_wrh = "CREATE TABLE "+ MyColumns_sawmill_wrh.NamaTabel+
            "("+ MyColumns_sawmill_wrh.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_wrh.nama_lokasi+" TEXT NOT NULL, "+ MyColumns_sawmill_wrh.alamat_lokasi+" TEXT NOT NULL )";

    private static final String SQL_CREATE_ENTRIES_sawmill_factory = "CREATE TABLE "+ MyColumns_sawmill_factory.NamaTabel+
            "("+ MyColumns_sawmill_factory.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmill_factory.nama_lokasi+" TEXT NOT NULL, "+ MyColumns_sawmill_factory.alamat_lokasi+" TEXT NOT NULL )";


    //sawmuilltebal
    private static final String SQL_CREATE_ENTRIES_sawmilltebal = "CREATE TABLE "+ MyColumns_sawmilltebal.NamaTabel+
            "("+ MyColumns_sawmilltebal.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmilltebal.tebal+" DOUBLE NOT NULL  )";
    //sawmuillSGD
    private static final String SQL_CREATE_ENTRIES_sawmillsgd = "CREATE TABLE "+ MyColumns_sawmillsgd.NamaTabel+
            "("+ MyColumns_sawmillsgd.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmillsgd.nama_sgd+" TEXT NOT NULL  )";
    //sawmuilltemp
    private static final String SQL_CREATE_ENTRIES_sawmilltemp= "CREATE TABLE "+ MyColumns_sawmilltemp.NamaTabel+
            "("+ MyColumns_sawmilltemp.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmilltemp.nama_user+" TEXT NOT NULL,"+ MyColumns_sawmilltemp.nama_lokasi+" TEXT NOT NULL, "+ MyColumns_sawmilltemp.nama_user_short+" TEXT NOT NULL, "+ MyColumns_sawmilltemp.nama_lainnya+" TEXT NOT NULL   )";

    //sawmuillmasteruser
    private static final String SQL_CREATE_ENTRIES_sawmilluser= "CREATE TABLE "+ MyColumns_sawmilluser.NamaTabel+
            "("+ MyColumns_sawmilluser.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmilluser.nama_user+" TEXT NOT NULL,"+ MyColumns_sawmilluser.nama_pass+" TEXT NOT NULL, "+ MyColumns_sawmilluser.nama_ket+" TEXT NOT NULL   )";

    //sawmilluser_sub
    private static final String SQL_CREATE_ENTRIES_sawmilluser_sub= "CREATE TABLE "+ MyColumns_sawmilluser_sub.NamaTabel+
            "("+ MyColumns_sawmilluser_sub.idno+" INTEGER PRIMARY KEY, "+ MyColumns_sawmilluser_sub.user_name+" TEXT , "+ MyColumns_sawmilluser_sub.module+" TEXT  )";




    //batas sawmill
    private static final String SQL_CREATE_ENTRIES_item = "CREATE TABLE "+ MyColumns_item.NamaTabel+
            "("+ MyColumns_item.idno+" INTEGER PRIMARY KEY, "+ MyColumns_item.kode+" TEXT NOT NULL, "+ MyColumns_item.nama+
            " TEXT NOT NULL, "+ MyColumns_item.harga+" DOUBLE NOT NULL,"+ MyColumns_item.harga_beli+" DOUBLE NOT NULL,"+ MyColumns_item.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M', 'NOW','localtime')))";


    private static final String SQL_CREATE_ENTRIES_barcode = "CREATE TABLE "+ MyColumns_barcode.NamaTabel+
            "("+ MyColumns_barcode.idno+" INTEGER PRIMARY KEY, "+ MyColumns_barcode.barcode+" TEXT NOT NULL, "+ MyColumns_barcode.noref+
            " TEXT NOT NULL, "+ MyColumns_barcode.typetran+" TEXT NOT NULL,"+ MyColumns_barcode.lokoven+" TEXT  NOT NULL,"
            + MyColumns_barcode.namaoven+" TEXT  NOT NULL,"+ MyColumns_barcode.namaoven2+" TEXT  NOT NULL," + MyColumns_barcode.tebal+" DOUBLE  NOT NULL,"
            + MyColumns_barcode.sgd+" TEXT  NOT NULL,"+ MyColumns_barcode.panjang+" DOUBLE  NOT NULL,"
            + MyColumns_barcode.lebar+" DOUBLE  NOT NULL,"+ MyColumns_barcode.temp5+" TEXT  NOT NULL,"
            + MyColumns_barcode.tagtran+" INTEGER  NOT NULL,"
            + MyColumns_item.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M:%S', 'NOW','localtime')))";



    private static final String SQL_CREATE_ENTRIES_tokoprofile = "CREATE TABLE "+ MyColumns_tokoprofile.NamaTabel+
            "("+ MyColumns_tokoprofile.idno+" INTEGER PRIMARY KEY, "+ MyColumns_tokoprofile.nama_toko+" TEXT NOT NULL, "+ MyColumns_tokoprofile.alamat_toko+
            " TEXT NOT NULL, "+ MyColumns_tokoprofile.no_hp_toko+" TEXT NOT NULL,"+ MyColumns_tokoprofile.tgl_rec+" DATETIME DEFAULT (STRFTIME('%d-%m-%Y   %H:%M', 'NOW','localtime')))";



    private static final String SQL_DELETE_ENTRIES_basesupplier = "DROP TABLE IF EXISTS "+  MyColumns_sawmill_basesupplier.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_tipekayu = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_tipekayu.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_datafilter = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_datafilter.NamaTabel;

    private static final String SQL_DELETE_ENTRIES_newbarcode_factory= "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_factory.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_wrh = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_wrh.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_swout = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_swout.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_mjpapan = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_mjpapan.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_oven = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_oven.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_checkin = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_checkin.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode_log = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode_log.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_newbarcode = "DROP TABLE IF EXISTS "+ MyColumns_newbarcode.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_headerbarcode = "DROP TABLE IF EXISTS "+ MyColumns_headerbarcode.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_headertempbarcode = "DROP TABLE IF EXISTS "+ MyColumns_headertempbarcode.NamaTabel;

    //Query upgrade sawmill
    private static final String SQL_DELETE_ENTRIES_sawmilllokasi = "DROP TABLE IF EXISTS "+ MyColumns_sawmilllokasi.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmill_oven = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_oven.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmill_wrh = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_wrh.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmill_factory = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_factory.NamaTabel;

    private static final String SQL_DELETE_ENTRIES_sawmilltebal = "DROP TABLE IF EXISTS "+ MyColumns_sawmilltebal.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmillsgd = "DROP TABLE IF EXISTS "+ MyColumns_sawmillsgd.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmilltemp = "DROP TABLE IF EXISTS "+ MyColumns_sawmilltemp.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmilluser = "DROP TABLE IF EXISTS "+ MyColumns_sawmilluser.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmilluser_sub = "DROP TABLE IF EXISTS "+ MyColumns_sawmilluser_sub.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmillshortlong = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_shortlong.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_sawmillswgroup = "DROP TABLE IF EXISTS "+ MyColumns_sawmill_swgroup.NamaTabel;

    //Query yang digunakan untuk mengupgrade Tabel
    private static final String SQL_DELETE_ENTRIES_item = "DROP TABLE IF EXISTS "+ MyColumns_item.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_barcode = "DROP TABLE IF EXISTS "+ MyColumns_barcode.NamaTabel;
    private static final String SQL_DELETE_ENTRIES_tokoprofile = "DROP TABLE IF EXISTS "+ MyColumns_tokoprofile.NamaTabel;




    public List<String> getShort(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT kode_sl FROM " + ADB_Master.MyColumns_sawmill_shortlong.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<String> getSpnfactory(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT nama_lokasi FROM " + ADB_Master.MyColumns_sawmill_factory.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public List<String> getSpnwrh(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT nama_lokasi FROM " + ADB_Master.MyColumns_sawmill_wrh.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public List<String> getSpnoven(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT nama_lokasi FROM " + ADB_Master.MyColumns_sawmill_oven.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<String> getSwgroup(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT kode_lokasi FROM " + ADB_Master.MyColumns_sawmill_swgroup.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public List<String> getTebal(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT tebal FROM " + ADB_Master.MyColumns_sawmilltebal.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public List<String> getSGD(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT namasgd FROM " + ADB_Master.MyColumns_sawmillsgd.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<String> getLokOven(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT supplier FROM " + ADB_Master.MyColumns_sawmilllokasi.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }

    public List<String> getSpsupplier(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT supplier FROM " + ADB_Master.MyColumns_sawmill_basesupplier.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }


    public List<String> getTipetransoven() {
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT tipe_kayu FROM " + ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        userList.add("Tipe");
        userList.add("IN");
        userList.add("OUT");
        userList.add("ARV");
        return userList;
    }



    public List<String> getTipetrans() {
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT tipe_kayu FROM " + ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        userList.add("Tipe");
        userList.add("IN");
        userList.add("OUT");
        return userList;
    }



    public List<String> getTipekayu(){
        List<String> userList = new ArrayList<>();
        String selectQuery = "SELECT tipe_kayu FROM " + ADB_Master.MyColumns_sawmill_tipekayu.NamaTabel;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                String userin = new String(cursor.getString(0));
                // userList.add(cursor.getInt(0),cursor.getString(1));
                userList.add(userin);
            } while (cursor.moveToNext());
        }
        return userList;
    }



    public List<BarangSawmillExt> getHeader(String barcode){
        List<BarangSawmillExt> userList2 = new ArrayList<>();
        String selectQuery = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where a.barcodeheader='"+barcode+"' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by b.tagtran desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillExt userin = new BarangSawmillExt(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getDouble(9),cursor.getDouble(10),cursor.getString(11),cursor.getInt(12),cursor.getInt(13),cursor.getString(14));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillExt> getHeaderTag(){
        List<BarangSawmillExt> userList2 = new ArrayList<>();
      //  String selectQuery = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran='2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by b.tagtran desc";
        String selectQuery = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader,max(b.nourut) as bedulno FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran='2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by bedulno desc";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillExt userin = new BarangSawmillExt(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getDouble(9),cursor.getDouble(10),cursor.getString(11),cursor.getInt(12),cursor.getInt(13),cursor.getString(14));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillExt> getHeaderTag_complete(){
        List<BarangSawmillExt> userList2 = new ArrayList<>();
        String selectQuery = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader,max(b.nourut) as bedulurut FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran<>'2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by bedulurut desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillExt userin = new BarangSawmillExt(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getDouble(9),cursor.getDouble(10),cursor.getString(11),cursor.getInt(12),cursor.getInt(13),cursor.getString(14));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillExt> getHeaderTag_post(){
        List<BarangSawmillExt> userList2 = new ArrayList<>();
        String selectQuery = "SELECT a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,max(a.panjang) as panjang,max(a.lebar) as lebar,max(a.tinggi) as tinggi,max(a.diameter) as diameter,max(a.tgl_beli) as tgl_beli,count(b.barcode) as jumbarc,max(b.tagtran) as btagtran,a.barcodeheader,max(b.nourut) as bedulurut FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+" a left join "+ MyColumns_newbarcode.NamaTabel+" b on a.idno_link1=b.idno_link1 where b.tagtran='2' group by a.idno,a.volume,a.idno_link1,a.sawmill,a.nama_spp,a.tipe_kayu,a.no_log,a.barcodeheader order by bedulurut asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillExt userin = new BarangSawmillExt(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getDouble(9),cursor.getDouble(10),cursor.getString(11),cursor.getInt(12),cursor.getInt(13),cursor.getString(14));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

/*backup

    public List<BarangSawmill> getHeader(){
        List<BarangSawmill> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,volume,idno_link1,sawmill,nama_spp,tipe_kayu,no_log,panjang,lebar,tinggi,diameter,tgl_beli FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+"  order by no_log asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmill userin = new BarangSawmill(cursor.getInt(0),cursor.getDouble(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getDouble(9),cursor.getDouble(10),cursor.getString(11));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

   */

   /* public List<BarangSawmill_barc> getHeaderSub(String idno_link1){
        List<BarangSawmill_barc> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,idno_link1,barcode,tebal,panjang,lebar,sgd,tgl_rec,group_sw,group_panjang,noref,tagtragn FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel+"  order by no_log asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmill_barc userin = new BarangSawmill_barc(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getDouble(3),cursor.getDouble(4),cursor.getDouble(5),cursor.getString(6),cursor.getString(7),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getInt(11));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }
*/


    public List<BarangSawmillLOG_sub> getSemuaFactsub(String noref){
        List<BarangSawmillLOG_sub> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran FROM " +ADB_Master.MyColumns_newbarcode_factory.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_sub userin = new BarangSawmillLOG_sub(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillLOG> getSemuaFact(){
        List<BarangSawmillLOG> userList2 = new ArrayList<>();
        String selectQuery = "SELECT max(idno) as idno,supplier,noref,max(tgl_rec) as tgl_rec,typetran,max(tagtran) as tagtran FROM " +ADB_Master.MyColumns_newbarcode_factory.NamaTabel+" group by supplier,noref,typetran  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG userin = new BarangSawmillLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangSawmillLOG_sub> getSemuaSwOutsub(String noref){
        List<BarangSawmillLOG_sub> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran FROM " +ADB_Master.MyColumns_newbarcode_swout.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_sub userin = new BarangSawmillLOG_sub(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillLOG_MJ> getSemuaMJPapansub(String noref){
        List<BarangSawmillLOG_MJ> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran,skau,nourut FROM " +ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" where noref = '"+noref+"'  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_MJ userin = new BarangSawmillLOG_MJ(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7),cursor.getInt(8));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillLOG> getSemuaSWOut(){
        List<BarangSawmillLOG> userList2 = new ArrayList<>();
        String selectQuery ="SELECT max(a.idno) as idno,b.nama_lokasi,a.noref,max(a.tgl_rec) as tgl_rec,a.typetran,max(a.tagtran) as tagtran FROM " +ADB_Master.MyColumns_newbarcode_swout.NamaTabel+" a inner join "+MyColumns_sawmill_swgroup.NamaTabel+" b on a.supplier=b.kode_lokasi group by a.supplier,a.noref,a.typetran  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG userin = new BarangSawmillLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangSawmillMJpapan> getSemuaMJPapan(){
        List<BarangSawmillMJpapan> userList2 = new ArrayList<>();
        String selectQuery ="SELECT max(a.idno) as idno,b.nama_lokasi,a.noref,max(a.tgl_rec) as tgl_rec,a.typetran,max(a.tagtran) as tagtran,skau FROM " +ADB_Master.MyColumns_newbarcode_mjpapan.NamaTabel+" a inner join "+MyColumns_sawmill_swgroup.NamaTabel+" b on a.supplier=b.kode_lokasi group by a.supplier,a.noref,a.typetran ,a.skau order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillMJpapan userin = new BarangSawmillMJpapan(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillLOG_sub> getSemuaWrhsub(String noref){
        List<BarangSawmillLOG_sub> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran FROM " +ADB_Master.MyColumns_newbarcode_wrh.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_sub userin = new BarangSawmillLOG_sub(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillLOG> getSemuaWrh(){
        List<BarangSawmillLOG> userList2 = new ArrayList<>();
        String selectQuery = "SELECT max(idno) as idno,supplier,noref,max(tgl_rec) as tgl_rec,typetran,max(tagtran) as tagtran FROM " +ADB_Master.MyColumns_newbarcode_wrh.NamaTabel+" group by supplier,noref,typetran  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG userin = new BarangSawmillLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangSawmillLOG_sub> getSemuaOvensub(String noref){
        List<BarangSawmillLOG_sub> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran FROM " +ADB_Master.MyColumns_newbarcode_oven.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_sub userin = new BarangSawmillLOG_sub(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillLOG> getSemuaOven(){
        List<BarangSawmillLOG> userList2 = new ArrayList<>();
        String selectQuery = "SELECT max(idno) as idno,supplier,noref,max(tgl_rec) as tgl_rec,typetran,max(tagtran) as tagtran FROM " +ADB_Master.MyColumns_newbarcode_oven.NamaTabel+" group by supplier,noref,typetran  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG userin = new BarangSawmillLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }




    public List<BarangListBarcode> getListBarcode(String noskau){
        List<BarangListBarcode> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,barcodeheader,no_log FROM " +ADB_Master.MyColumns_headerbarcode.NamaTabel +" where sawmill like '"+noskau+"' order by no_log asc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangListBarcode userin = new BarangListBarcode(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangSawmillLOG_sub> getSemuaCheckinSub(String noref){
        List<BarangSawmillLOG_sub> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran FROM " +ADB_Master.MyColumns_newbarcode_checkin.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG_sub userin = new BarangSawmillLOG_sub(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmillLOG> getSemuaCheckin(){
        List<BarangSawmillLOG> userList2 = new ArrayList<>();
        String selectQuery = "SELECT max(a.idno) as idno,b.nama_lokasi,a.noref,max(a.tgl_rec) as tgl_rec,a.typetran,max(a.tagtran) as tagtran FROM " +ADB_Master.MyColumns_newbarcode_checkin.NamaTabel+" a inner join "+MyColumns_sawmill_swgroup.NamaTabel+" b on a.supplier=b.kode_lokasi group by a.supplier,a.noref,a.typetran  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOG userin = new BarangSawmillLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillAllLOG> getSemuaGraderSub(String noref){
        List<BarangSawmillAllLOG> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,noref,barcode,supplier,typetran,tgl_rec,tagtran,nourut FROM " +ADB_Master.MyColumns_newbarcode_log.NamaTabel+" where noref = '"+noref+"' group by idno,barcode,supplier,noref,tgl_rec,typetran,tagtran  order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillAllLOG userin = new BarangSawmillAllLOG(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getInt(7));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangListBarcodeTia_delgroup> getDelgroup(){
        List<BarangListBarcodeTia_delgroup> userList2 = new ArrayList<>();
        String selectQuery = "SELECT b.sawmill as group_hd,a.idno_link2 as idno_link2,max(a.tgl_rec) as tgl_rec FROM  tbl_newbarcode a inner join  tbl_headerbarcode b on a.idno_link2=b.idno_link2 group by b.sawmill,a.idno_link2 order by tgl_rec desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangListBarcodeTia_delgroup userin = new BarangListBarcodeTia_delgroup(cursor.getString(0),cursor.getInt(1),cursor.getString(2));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangListNoGroup> getListNoGroup(){
        List<BarangListNoGroup> userList2 = new ArrayList<>();
        String selectQuery = "SELECT  sawmill as nogroup FROM " + MyColumns_headerbarcode.NamaTabel+" group by sawmill order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangListNoGroup userin = new BarangListNoGroup(cursor.getString(0));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    public List<BarangSawmillLOGMain> getSemuaGrader(){
        List<BarangSawmillLOGMain> userList2 = new ArrayList<>();
        String selectQuery = "SELECT max(idno) as idno,supplier,noref,max(tgl_rec) as tgl_rec,typetran,max(tagtran) as tagtran,skau FROM " +ADB_Master.MyColumns_newbarcode_log.NamaTabel+" group by supplier,noref,typetran,skau  order by tagtran desc,idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmillLOGMain userin = new BarangSawmillLOGMain(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }



    public List<BarangBarcodeNOREFLokal> getSemuaINPRS(String typtran){
        List<BarangBarcodeNOREFLokal> userList2 = new ArrayList<>();
        String selectQuery = "SELECT noref,lokoven,namaoven,'temp5',typetran,min(tagtran) as tagtran,max(tgl_rec) as tgl_rec,count(barcode) as jumbarc,sum((tebal*panjang*lebar)/1000000) as jumkubik FROM " +ADB_Master.MyColumns_barcode.NamaTabel+" where typetran= '"+typtran+"'"+" group by noref,lokoven,namaoven,typetran order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangBarcodeNOREFLokal userin = new BarangBarcodeNOREFLokal(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getInt(7),cursor.getDouble(8));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangSawmill_barc> getSemuaItem(String idno_link){
        List<BarangSawmill_barc> userList2 = new ArrayList<>();
        String selectQuery = "SELECT idno,idno_link1,barcode,tebal,panjang,lebar,sgd,group_sw,group_panjang,tagtran,tgl_rec,nourut FROM " +ADB_Master.MyColumns_newbarcode.NamaTabel+" where idno_link1= '"+idno_link+"'"+" order by idno desc";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangSawmill_barc userin = new BarangSawmill_barc(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4), cursor.getDouble(5), cursor.getString(6), cursor.getString(7), cursor.getString(8), cursor.getInt(9), cursor.getString(10), cursor.getInt(11));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }

    public List<BarangDialog> getItemDialog(String itemcode){
        List<BarangDialog> userList2 = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + ADB_Master.MyColumns_item.NamaTabel+" where "
                + MyColumns_item.kode+" LIKE '%"+itemcode+"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                BarangDialog userin = new BarangDialog(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3));
                userList2.add(userin);
            } while (cursor.moveToNext());
        }
        return userList2;
    }


    //updateData
    public void updateData(String nama_toko, String alamat_toko, String no_hp_toko, int idno){
        SQLiteDatabase database = getWritableDatabase();
        //query to update record
        String sql = "UPDATE toko_profile SET nama_toko=?, alamat_toko=?, no_hp_toko=? WHERE idno=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1, nama_toko);
        statement.bindString(2, alamat_toko);
        statement.bindString(3, no_hp_toko);
        statement.bindDouble(5, (double)idno);
        statement.execute();
        database.close();
    }

    //deleteData
    public void deleteData(int idno){
        SQLiteDatabase database = getWritableDatabase();
        //query to delete record using id
        String sql = "DELETE FROM toko_profile WHERE idno=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, (double)idno);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    ADB_Master(Context context) {
        super(context, NamaDatabase, null, VersiDatabase);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_item);
        db.execSQL(SQL_CREATE_ENTRIES_barcode);
        db.execSQL(SQL_CREATE_ENTRIES_tokoprofile);

    db.execSQL(SQL_CREATE_ENTRIES_sawmill_oven);
         db.execSQL(SQL_CREATE_ENTRIES_sawmill_wrh);
      db.execSQL(SQL_CREATE_ENTRIES_sawmill_factory);

        db.execSQL(SQL_CREATE_ENTRIES_sawmill_basesupplier);
       db.execSQL(SQL_CREATE_ENTRIES_sawmill_tipekayu);
        db.execSQL(SQL_CREATE_ENTRIES_datafilter);
         db.execSQL(SQL_CREATE_ENTRIES_newbarcode_factory);
        db.execSQL(SQL_CREATE_ENTRIES_newbarcode_checkin);
       db.execSQL(SQL_CREATE_ENTRIES_newbarcode_log);
          db.execSQL(SQL_CREATE_ENTRIES_newbarcode_wrh);
         db.execSQL(SQL_CREATE_ENTRIES_newbarcode_swout);
        db.execSQL(SQL_CREATE_ENTRIES_newbarcode_oven);
      db.execSQL(SQL_CREATE_ENTRIES_newbarcode_mjpapan);

        db.execSQL(SQL_CREATE_ENTRIES_newbarcode);
         db.execSQL(SQL_CREATE_ENTRIES_headerbarcode);
       db.execSQL(SQL_CREATE_ENTRIES_headertempbarcode);

        //batas sawmill
        db.execSQL(SQL_CREATE_ENTRIES_sawmilllokasi);
        db.execSQL(SQL_CREATE_ENTRIES_sawmilltebal);
        db.execSQL(SQL_CREATE_ENTRIES_sawmillsgd);
       db.execSQL(SQL_CREATE_ENTRIES_sawmilltemp);
        db.execSQL(SQL_CREATE_ENTRIES_sawmilluser);
        db.execSQL(SQL_CREATE_ENTRIES_sawmilluser_sub);
       db.execSQL(SQL_CREATE_ENTRIES_sawmillshortlong);
        db.execSQL(SQL_CREATE_ENTRIES_sawmillswgroup);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES_item);
        db.execSQL(SQL_DELETE_ENTRIES_barcode);
        db.execSQL(SQL_DELETE_ENTRIES_tokoprofile);

        db.execSQL(SQL_DELETE_ENTRIES_basesupplier);
        db.execSQL(SQL_DELETE_ENTRIES_datafilter);
       db.execSQL(SQL_DELETE_ENTRIES_tipekayu);
        db.execSQL(SQL_DELETE_ENTRIES_newbarcode_checkin);
      db.execSQL(SQL_DELETE_ENTRIES_newbarcode_factory);
        db.execSQL(SQL_DELETE_ENTRIES_newbarcode_log);
          db.execSQL(SQL_DELETE_ENTRIES_newbarcode_oven);
        db.execSQL(SQL_DELETE_ENTRIES_newbarcode_wrh);
        db.execSQL(SQL_DELETE_ENTRIES_newbarcode_swout);
          db.execSQL(SQL_DELETE_ENTRIES_newbarcode_mjpapan);
        db.execSQL(SQL_DELETE_ENTRIES_newbarcode);
       db.execSQL(SQL_DELETE_ENTRIES_headerbarcode);
        db.execSQL(SQL_DELETE_ENTRIES_headertempbarcode);

         //batas sawmill
         db.execSQL(SQL_DELETE_ENTRIES_sawmill_oven);
        db.execSQL(SQL_DELETE_ENTRIES_sawmill_wrh);
        db.execSQL(SQL_DELETE_ENTRIES_sawmill_factory);

      db.execSQL(SQL_DELETE_ENTRIES_sawmilllokasi);
        db.execSQL(SQL_DELETE_ENTRIES_sawmillsgd);
       db.execSQL(SQL_DELETE_ENTRIES_sawmilltebal);
       db.execSQL(SQL_DELETE_ENTRIES_sawmilltemp);
       db.execSQL(SQL_DELETE_ENTRIES_sawmilluser);
        db.execSQL(SQL_DELETE_ENTRIES_sawmilluser_sub);
       db.execSQL(SQL_DELETE_ENTRIES_sawmillswgroup);
       db.execSQL(SQL_DELETE_ENTRIES_sawmillshortlong);


        onCreate(db);
        //  dataAwaluser(db);
        // dataAwaluserHak(db);
    }


}
