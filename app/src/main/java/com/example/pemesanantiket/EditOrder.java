package com.example.pemesanantiket;

import static com.example.pemesanantiket.BuyActivity.PRIMARY_CHANNEL_ID_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.pemesanantiket.database.AppDatabase;
import com.example.pemesanantiket.database.userEntity.User;

import java.util.Objects;


public class EditOrder extends AppCompatActivity {

    private EditText editNama, editNope, editBrkt, editTjn;
    private AppDatabase database;
    private int uid = 0;
    private boolean isEdit = false;

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

        createNotificationChannel();

        Button btnUpdateOrd = findViewById(R.id.btnUpdateOrder);

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
        });
        Toolbar toolbar = findViewById(R.id.toolbaredit);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {
            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID_1,
                    "Edit Order", NotificationManager
                    .IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Edit");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    public void updateNotification() {
        Bitmap androidImage = BitmapFactory
                .decodeResource(getResources(),R.drawable.update);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        notifyBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                .bigPicture(androidImage)
                .setBigContentTitle("Update!"));

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, History.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID_1)
                .setContentTitle("Update!!!")
                .setContentText("Pesanan Diupdate!")
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_android);

        return notifyBuilder;
    }
}