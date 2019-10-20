package com.sourabh.firebasedataretrieve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.LinkedList;


public class MainActivity extends FragmentActivity implements OnMapReadyCallback  {
    TextView mEditText;
    DatabaseReference mReference;
    LinkedList<GPSData> mGPSDataList;
    GoogleMap mMap;
    double lat;
    double lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText=findViewById(R.id.mTextView);
        mReference= FirebaseDatabase.getInstance().getReference("object");
        mGPSDataList=new LinkedList<>();
        SupportMapFragment mapFragment=(SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_activity);
        mapFragment.getMapAsync( this);

    }


    @Override
    protected void onStart() {
        super.onStart();

        mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GPSData gpsData=new GPSData();
                for (DataSnapshot gpsSnapShot: dataSnapshot.getChildren())
                    gpsData =gpsSnapShot.getValue(GPSData.class);
                mGPSDataList.add(gpsData);
                lat= Double.parseDouble(mGPSDataList.getLast().latitude);
                lon= Double.parseDouble(mGPSDataList.getLast().longitude);
                mEditText.setText("latitude "+lat+"\n longitude:"+lon);

                LatLng x = new LatLng(lat,lon);
               // mEditText.append("\nShit:  lat"+lat);
                mMap.addMarker(new MarkerOptions().position(x).title("Marker in Sydney"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera (16.852398,74.581474);
        //LatLng sydney = new LatLng(lat,lon);
        //mEditText.append("\nShit:  lat"+lat);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

    }
}
