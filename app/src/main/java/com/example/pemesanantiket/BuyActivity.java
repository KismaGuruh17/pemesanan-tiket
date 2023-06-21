package com.example.pemesanantiket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pemesanantiket.database.AppDatabase;
import com.example.pemesanantiket.database.userEntity.User;

import java.util.Objects;

public class BuyActivity extends AppCompatActivity {

    private EditText editNama, editNope, editBrkt, editTjn;
    private AppDatabase database;
    Toast toastOrder;
    String orderToast;
    int duration;
    Context context;
    private int uid = 0;
    private boolean isEdit = false;

    public static final String PRIMARY_CHANNEL_ID_1 = "Order, Update";
    public static final String PRIMARY_CHANNEL_ID_2 = "Hapus";
    public NotificationManager mNotifyManager;

    public static final int NOTIFICATION_ID = 0;

    public static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notify.ACTION_UPDATE_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        editNama = findViewById(R.id.inputNama);
        editNope = findViewById(R.id.inputTelepon);
        editBrkt = findViewById(R.id.spBerangkat);
        editTjn = findViewById(R.id.spTujuan);

        createNotificationChannel();

        Button btnSave = findViewById(R.id.btnCheckout);

        duration = Toast.LENGTH_SHORT;
        orderToast = "Order Berhasil";
        context = getApplicationContext();
        toastOrder = Toast.makeText(context,orderToast,duration);

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
            public void onClick(View view) { sendNotification();
                toastOrder.show();
                if (isEdit) {
                    database.userDao().update(uid, editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                } else {
                    database.userDao().insertAll(editNama.getText().toString(), editNope.getText().toString(), editBrkt.getText().toString(), editTjn.getText().toString());
                }
                finish();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbarbuy);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

    }
    public void sendNotification() {

        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
// for update notif
//        notifyBuilder.addAction(R.drawable.ic_update, "Edit Data", updatePendingIntent);

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

    }
    public void createNotificationChannel() {
        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {

            // Create a NotificationChannel
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID_1,
                    "Order Notif", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Notification from Order");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }

    private NotificationCompat.Builder getNotificationBuilder() {
        Intent notificationIntent = new Intent(this, History.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(this,
                NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID_1)
                .setContentTitle("Infoo!")
                .setContentText("Pemesanan Selesai!")
                .setContentIntent(notificationPendingIntent)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setSmallIcon(R.drawable.ic_android);
        return notifyBuilder;
    }

    public void updateNotification() {
        Intent notificationIntent = new Intent(this, EditOrder.class);


    }

    public class NotificationReceiver extends BroadcastReceiver {

        public NotificationReceiver() {
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            // Update the notification
            updateNotification();
        }
    }
}