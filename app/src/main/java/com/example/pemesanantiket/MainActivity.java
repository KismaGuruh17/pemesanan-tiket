package com.example.pemesanantiket;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final ImageView IMAGE_STATE = null;
    private final CustomReceiver mReceiver = new CustomReceiver();

    private Switch mode_switch;

    private Button btnPesawat, btnKapal, btnKereta, btnHistory, btnBanner;

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;

    ImageView Banner1, Banner2, Banner3;

    private static final String IMAGE_BITMAP_KEY = "imageBitmap";

    private Bitmap imageBitmap;

    String imageUrl1, imageUrl2, imageUrl3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mode_switch = findViewById(R.id.mode_switch);

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);

        this.registerReceiver(mReceiver, filter);

        btnPesawat = findViewById(R.id.btnPesawat);
        btnKapal = findViewById(R.id.btnKapal);
        btnKereta = findViewById(R.id.btnKereta);

        btnHistory = findViewById(R.id.historyTiket);

        btnBanner = findViewById(R.id.bannerBtn);
        btnBanner.setOnClickListener((View.OnClickListener) this);

        Banner1 = findViewById(R.id.bnr1);
        Banner2 = findViewById(R.id.bnr2);
        Banner3 = findViewById(R.id.bnr3);


        imageUrl1 = "https://skbbindonesia.com/img-berita/default.png";
        imageUrl2 = "https://skbbindonesia.com/img-berita/default.png";
        imageUrl3 = "https://skbbindonesia.com/img-berita/default.png";



        btnPesawat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        btnKapal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);

            }
        });

        btnKereta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BuyActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, History.class);
                startActivity(intent);
            }
        });

        mode_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }

            }
        });
    }

    @Override
    public void onClick(View view) {
        new ImageShow().execute("https://static.designboom.com/wp-content/uploads/2018/08/art-safiental-2018-land-art-exhibition-horizontal-vertical-designboom08.jpg");
        Glide.with(this)
                .load(imageUrl1)
                .into(Banner1);
        new ImageShow2().execute("https://w0.peakpx.com/wallpaper/644/902/HD-wallpaper-swiss-nature-rocks-hills-house-view-grass-beautiful-switzerland-lake-mountain-serenity-cliffs-swiss-summer-reflection.jpg");
        Glide.with(this)
                .load(imageUrl2)
                .into(Banner2);
        new ImageShow3().execute("https://ik.imagekit.io/tvlk/blog/2021/11/Destinasi-Wisata-Swiss-Matterhorn-Shutterstock.jpg?tr=dpr-2,w-675");
        Glide.with(this)
                .load(imageUrl3)
                .into(Banner3);
    }

    private class ImageShow extends AsyncTask<String, Void, Bitmap> {
        HttpURLConnection httpURLConnection;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                Banner1.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Load 1 Success!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Load Error!", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private class ImageShow2 extends AsyncTask<String, Void, Bitmap> {
        HttpURLConnection httpURLConnection;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                Banner2.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Load 2 Success!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Load Error!", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    private class ImageShow3 extends AsyncTask<String, Void, Bitmap> {
        HttpURLConnection httpURLConnection;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                Bitmap temp = BitmapFactory.decodeStream(inputStream);
                return temp;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                httpURLConnection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if(bitmap != null) {
                Banner3.setImageBitmap(bitmap);
                Toast.makeText(getApplicationContext(), "Load 3 Success!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Load Error!", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    @Override
    protected void onDestroy() {
        // Unregister the receiver.
        this.unregisterReceiver(mReceiver);
        super.onDestroy();

        /////////////////////////////////////
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(mReceiver);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setIcon(R.drawable.ic_baseline_exit_to_app_24);
        alertDialog.setTitle("Keluar Aplikasi?");
        alertDialog.setMessage("Apakah kamu ingin keluar dari Aplikasi??");
        alertDialog.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });
        alertDialog.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }

}
