package com.example.ikoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class ControlingActivity extends AppCompatActivity {
    private static final String TAG = "relay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controling);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.app_name));

        final Switch switchPompa1 = (Switch) findViewById(R.id.switch_pompa1);
        final Switch switchPompa2 = (Switch) findViewById(R.id.switch_pompa2);
        final Switch switchPompa3 = (Switch) findViewById(R.id.switch_pompa3);
        final Switch switchHeater = (Switch) findViewById(R.id.switch_heater);
        final Switch switchCooler = (Switch) findViewById(R.id.switch_cooler);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference getRelay = database.getReference("relay");
        final DatabaseReference statusPompa1 = getRelay.child("p_fltrasi/status");
        final DatabaseReference statusPompa2 = getRelay.child("p_kolam/status");
        final DatabaseReference statusPompa3 = getRelay.child("p_sumber/status");
        final DatabaseReference statusHeater = getRelay.child("heater/status");
        final DatabaseReference statusCooler = getRelay.child("cooler/status");

        //mengambil value data dari firebase
        statusPompa1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if(value==true){
                    switchPompa1.setChecked(true);
                } else {
                    switchPompa1.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        statusPompa2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if(value==true){
                    switchPompa2.setChecked(true);
                } else {
                    switchPompa2.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        statusPompa3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if(value==true){
                    switchPompa3.setChecked(true);
                } else {
                    switchPompa3.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        statusCooler.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if(value==true){
                    switchCooler.setChecked(true);
                } else {
                    switchCooler.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        statusHeater.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean value = dataSnapshot.getValue(Boolean.class);
                if(value==true){
                    switchHeater.setChecked(true);
                } else {
                    switchHeater.setChecked(false);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        //melakukan set data jika di-klik on/off dan mengirimkan value datanya ke firebase
        switchPompa1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa1.setValue(true);
                    switchPompa1.setText("Nyala");
                }else{
                    statusPompa1.setValue(false);
                    switchPompa1.setText("Mati");
                }
            }
        });

        switchPompa2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa2.setValue(true);
                    switchPompa2.setText("Nyala");
                }else{
                    statusPompa2.setValue(false);
                    switchPompa2.setText("Mati");
                }
            }
        });

        switchPompa3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa3.setValue(true);
                    switchPompa3.setText("Nyala");
                }else{
                    statusPompa3.setValue(false);
                    switchPompa3.setText("Mati");
                }
            }
        });

        switchHeater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusHeater.setValue(true);
                    switchHeater.setText("Nyala");
                }else{
                    statusHeater.setValue(false);
                    switchHeater.setText("Mati");
                }
            }
        });

        switchCooler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusCooler.setValue(true);
                    switchCooler.setText("Nyala");
                }else{
                    statusCooler.setValue(false);
                    switchCooler.setText("Mati");
                }
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
