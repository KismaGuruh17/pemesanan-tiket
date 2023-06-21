package com.example.pemesanantiket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();

        String toastMessage = null;

        if (intentAction != null) {
            toastMessage = "unknown intent action";
            switch (intentAction) {
                case Intent.ACTION_POWER_CONNECTED:
                    toastMessage = "CHARGER TERHUBUNG!";
                    break;
                case Intent.ACTION_POWER_DISCONNECTED:
                    toastMessage = "CHARGER DICABRUT!";
                    break;
            }

            // Display the toast.

            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
        }
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
    }
}
