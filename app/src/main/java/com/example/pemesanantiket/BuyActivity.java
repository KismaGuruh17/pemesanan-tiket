package com.example.pemesanantiket;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pemesanantiket.database.AppDatabase;
import com.example.pemesanantiket.database.userEntity.User;

public class BuyActivity extends AppCompatActivity {

    private EditText editNama, editNope, editBrkt, editTjn;
    private Button btnSave;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        editNama = findViewById(R.id.inputNama);
        editNope = findViewById(R.id.inputTelepon);
        editBrkt = findViewById(R.id.spBerangkat);
        editTjn = findViewById(R.id.spTujuan);


        btnSave = findViewById(R.id.btnCheckout);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        uid = intent.getIntExtra("uid", 0);
        if (uid > 0) {
            isEdit = true;
            User user = database.userDao().get(uid);
            editNama.setText(user.nama);
            editNope.setText(user.nope);
            editBrkt.setText(user.keberangkatan);
            editTjn.setText(user.tujuan);


        } else {
            isEdit = false;
        }
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    database.userDao().update(uid, editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                } else {
                    database.userDao().insertAll(editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                }
                finish();
            }
        });
    }
}