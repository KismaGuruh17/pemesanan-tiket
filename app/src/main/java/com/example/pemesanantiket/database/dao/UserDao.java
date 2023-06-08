package com.example.pemesanantiket.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.pemesanantiket.database.userEntity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("INSERT INTO user (nama,nope, keberangkatan, tujuan) VALUES(:nama, :nope, :keberangkatan, :tujuan)")
    void insertAll(String nama, String nope, String keberangkatan, String tujuan);

    @Query("UPDATE user SET nama=:nama, nope=:nope, keberangkatan=:keberangkatan, tujuan=:tujuan WHERE uid=:uid")
    void update(int uid, String nama, String nope, String keberangkatan, String tujuan);

    @Query("SELECT * FROM user WHERE uid=:uid")
    User get(int uid);

    @Delete
    void delete(User user);
}
