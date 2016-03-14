package com.example.user.first;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by USER on 2016-03-07.
 */
public class MyLocationListener implements LocationListener {
    public double latitude,longtitude,altitude;//위도,경도,고도

    @Override
    public void onLocationChanged(Location location) {//위치정보 변경시 호출되는 함수
        latitude =location.getLatitude();
        longtitude=location.getLongitude();
        altitude=location.getAltitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}

