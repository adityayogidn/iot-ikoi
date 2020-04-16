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

        switchPompa1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa1.setValue(true);
                    switchPompa1.setTextOn("Nyala");
                }else{
                    statusPompa1.setValue(false);
                    switchPompa1.setTextOff("Mati");
                }
            }
        });

        switchPompa2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa2.setValue(true);
                    switchPompa2.setTextOn("Nyala");
                }else{
                    statusPompa2.setValue(false);
                    switchPompa2.setTextOff("Mati");
                }
            }
        });

        switchPompa3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusPompa3.setValue(true);
                    switchPompa3.setTextOn("Nyala");
                }else{
                    statusPompa3.setValue(false);
                    switchPompa3.setTextOff("Mati");
                }
            }
        });

        switchHeater.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusHeater.setValue(true);
                    switchHeater.setTextOn("Nyala");
                }else{
                    statusHeater.setValue(false);
                    switchHeater.setTextOff("Mati");
                }
            }
        });

        switchCooler.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    statusCooler.setValue(true);
                    switchCooler.setTextOn("Nyala");
                }else{
                    statusCooler.setValue(false);
                    switchCooler.setTextOff("Mati");
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
