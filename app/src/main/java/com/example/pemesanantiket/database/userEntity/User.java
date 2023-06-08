package com.example.pemesanantiket.database.userEntity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "nama")
    public String nama;

    public String keberangkatan;

    public String tujuan;

    public String tanggal;

    public String harga;

    public String anak;

    public String dewasa;

    public String kelas;

    public String status;

    public String nope;
}