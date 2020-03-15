package com.example.ikoi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
        Switch switchPompa2 = (Switch) findViewById(R.id.switch_pompa2);
        Switch switchPompa3 = (Switch) findViewById(R.id.switch_pompa3);
        Switch switchHeater = (Switch) findViewById(R.id.switch_heater);
        Switch switchCooler = (Switch) findViewById(R.id.switch_cooler);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference getRelay = database.getReference("relay");
        final DatabaseReference statusPompa1 = getRelay.child("p_fltrasi/status");
        DatabaseReference statusPompa2 = getRelay.child("p_kolam/status");
        DatabaseReference statusPompa3 = getRelay.child("p_sumber/status");
        DatabaseReference statusHeater = getRelay.child("heater/status");
        DatabaseReference statusCooler = getRelay.child("cooler/status");

        statusPompa1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Integer value = dataSnapshot.getValue(Integer.class);
                if(value==1) {
                    switchPompa1.isChecked();
                }

                switchPompa1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(switchPompa1.isChecked()){
                            statusPompa1.setValue(1);
                        }else{
                            statusPompa1.setValue(0);
                        }
                    }
                });
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
