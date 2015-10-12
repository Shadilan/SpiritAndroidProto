package com.coe.spiritandroidproto.utility;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

/**
 * Created by Administrator on 09.10.15.
 */
public class GPSInfo {
    private int lat=-1;
    private int lng=-1;
    private int request=0;
    LocationManager locationManager;

    public GPSInfo(Context mContext){
        locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        RequestUpdate();
    }
    public void RequestUpdate(){
        if (request==1) return;
        request=1;
        for (String prov : locationManager.getAllProviders())
        {
            locationManager.requestLocationUpdates(prov, 300000, 1, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    lat=(int)(location.getLatitude()*1000000);
                    lng=(int)(location.getLongitude()*1000000);
                    request=0;
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        };
    };
    public int GetLat(){
        return lat;
    }
    public int GetLng(){
        return lng;
    }
}
