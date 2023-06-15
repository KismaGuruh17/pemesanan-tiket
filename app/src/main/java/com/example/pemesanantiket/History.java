package com.example.pemesanantiket;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.example.pemesanantiket.adapter.UserAdapter;
import com.example.pemesanantiket.database.AppDatabase;
import com.example.pemesanantiket.database.userEntity.User;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AppDatabase database;
    private UserAdapter userAdapter;
    private List<User> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";

    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;

    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recycler_view);
        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.userDao().getAll());
        userAdapter = new UserAdapter(getApplicationContext(), list);
        userAdapter.setDialog(new UserAdapter.Dialog() {

            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(History.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(History.this, EditOrder.class);
                                intent.putExtra("uid", list.get(position).uid);
                                startActivity(intent);
                                break;
                            case 1:
                                User user = list.get(position);
                                database.userDao().delete(user);
                                onStart();
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);

        Toolbar toolbar = findViewById(R.id.toolbarhistory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.userDao().getAll());
        userAdapter.notifyDataSetChanged();
    }
}