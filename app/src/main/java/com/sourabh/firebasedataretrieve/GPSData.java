package com.sourabh.firebasedataretrieve;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
public class GPSData {
    public String latitude;
    public String longitude;

    public GPSData() {
    }

    public GPSData(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
