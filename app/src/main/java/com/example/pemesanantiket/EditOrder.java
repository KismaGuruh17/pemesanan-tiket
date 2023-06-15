package com.example.pemesanantiket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pemesanantiket.database.AppDatabase;
import com.example.pemesanantiket.database.userEntity.User;


public class EditOrder extends AppCompatActivity {

    private EditText editNama, editNope, editBrkt, editTjn;
    private Button btnUpdateOrd;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;

    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notify.ACTION_UPDATE_NOTIFICATION";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_order);

        editNama = findViewById(R.id.inputNama);
        editNope = findViewById(R.id.inputTelepon);
        editBrkt = findViewById(R.id.spBerangkat);
        editTjn = findViewById(R.id.spTujuan);



        btnUpdateOrd = findViewById(R.id.btnUpdateOrder);

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
        btnUpdateOrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEdit) {
                    database.userDao().update(uid, editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                } else {
                    database.userDao().insertAll(editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                }
                finish();
                updateNotification();
            }

            public void updateNotification() {
//                Bitmap androidImage = BitmapFactory
//                        .decodeResource(getResources(),R.drawable.mascot_1);
//
//                NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
//                notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
//                        .bigPicture(androidImage)
//                        .setBigContentTitle("Notifikasi Diupdate!"));
//
//                mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
            }


        });
        Toolbar toolbar = findViewById(R.id.toolbaredit);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}