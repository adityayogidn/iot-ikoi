package com.example.ikoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MonitoringActivity extends AppCompatActivity {
    private static final String TAG = "data_sensor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitoring);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        final TextView nilaiOksigen = (TextView) findViewById(R.id.getOksigen);
        final TextView nilaiPh = (TextView) findViewById(R.id.getPh);
        final TextView nilaiSuhu = (TextView) findViewById(R.id.getSuhu);
        final TextView nilaiTurbidity = (TextView) findViewById(R.id.getTurbidity);
        final TextView nilaiTds = (TextView) findViewById(R.id.getTds);


        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference dataSensor = database.getReference("data_sensor");
        DatabaseReference dataOksigen = dataSensor.child("oksigen");
        DatabaseReference dataPh = dataSensor.child("ph");
        DatabaseReference dataSuhu = dataSensor.child("temperatur");
        DatabaseReference dataTurbidity = dataSensor.child("turbidity");
        DatabaseReference dataTds = dataSensor.child("tds");

         dataOksigen.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 Float value = dataSnapshot.getValue(Float.class);
                 nilaiOksigen.setText(value.toString());
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 Log.w(TAG, "Failed to read value", databaseError.toException());
             }
         });

        dataPh.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                nilaiPh.setText(value.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        dataSuhu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                nilaiSuhu.setText(value.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        dataTurbidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                nilaiTurbidity.setText(value.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        dataTds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Float value = dataSnapshot.getValue(Float.class);
                nilaiTds.setText(value.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

        final TextView nilaiKualitasAir = findViewById(R.id.tv_kualitasAir);
        final DatabaseReference kualitasAir = database.getReference("kualitas_air");
        kualitasAir.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                if (value == 1){
                    nilaiKualitasAir.setText("Normal");
                    nilaiKualitasAir.setTextColor(Color.parseColor("#03A9F4"));
                    notification_normal();
                } else if (value == 2) {
                    nilaiKualitasAir.setText("Warning");
                    nilaiKualitasAir.setTextColor(Color.parseColor("#FFC107"));
                    notification_warning();
                } else if(value == 3) {
                    nilaiKualitasAir.setText("Danger");
                    nilaiKualitasAir.setTextColor(Color.parseColor("#FF1807"));
                    notification_danger();
                } else {
                    nilaiKualitasAir.setText("Error");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "Failed to read value", databaseError.toException());
            }
        });

    }

    private void notification_normal(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(true);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this, MonitoringActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "NotifApps")
                .setContentTitle("Kualitas Air")
                .setContentText("Status Kolam NORMAL")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_fish)
                .setAutoCancel(true) // menghapus notif ketika user melakukan tap pada notif
                .setLights(200,200,200)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOnlyAlertOnce(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent); // action notif ketika di tap
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }

    private void notification_warning(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(true);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, MonitoringActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentTitle("Kualitas Air")
                .setContentText("Status Kolam WARNING")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_fish)
                .setAutoCancel(true) // menghapus notif ketika user melakukan tap pada notif
                .setLights(200,200,200)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOnlyAlertOnce(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent); // action notif ketika di tap
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }

    private void notification_danger(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel =
                    new NotificationChannel("n", "n", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setShowBadge(true);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Intent intent = new Intent(this, MonitoringActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "n")
                .setContentTitle("Kualitas Air")
                .setContentText("Status Kolam DANGER")
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_fish)
                .setAutoCancel(true) // menghapus notif ketika user melakukan tap pada notif
                .setLights(200,200,200)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setOnlyAlertOnce(true)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent); // action notif ketika di tap
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(1,builder.build());
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
