package com.sourabh.firebasedataretrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView mEditText;
    Button mButton;
    DatabaseReference mReference;
    LinkedList<GPSData> mGPSDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=findViewById(R.id.mTextView);
        mButton=findViewById(R.id.button);
        mReference= FirebaseDatabase.getInstance().getReference("object");
        mGPSDataList=new LinkedList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               // String lat=dataSnapshot.child("latitude").getValue().toString();
               // String lon=dataSnapshot.child("longitude").getValue().toString();
                GPSData gpsData=new GPSData();
                for (DataSnapshot gpsSnapShot: dataSnapshot.getChildren())
                    gpsData =gpsSnapShot.getValue(GPSData.class);
                mGPSDataList.add(gpsData);
                mEditText.setText("Done "+mGPSDataList.getLast().latitude);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
