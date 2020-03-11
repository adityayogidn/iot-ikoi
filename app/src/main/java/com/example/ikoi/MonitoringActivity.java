package com.example.ikoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                 String value = dataSnapshot.getValue(String.class);
                 nilaiOksigen.setText(value);
             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
                 Log.w(TAG, "onCancelled", databaseError.toException());
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
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        dataSuhu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                nilaiSuhu.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        dataTurbidity.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                nilaiTurbidity.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

        dataTds.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                nilaiTds.setText(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.w(TAG, "onCancelled", databaseError.toException());
            }
        });

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
