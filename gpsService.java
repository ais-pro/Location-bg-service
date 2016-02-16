package com.example.root.servicedata;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;


/**
 * Created by Al Imran Suvro on 2/16/16.
 */
public class gpsService extends Service {

    GpsLocationListener gpsLocationListener;
    LocationManager locationManager;
    String provider;
    Criteria criteria;

    public gpsService() {
        super();
    }


    @Override
    public void onCreate() {
        super.onCreate();

        Toast.makeText(this,"Location Service created",Toast.LENGTH_SHORT).show();
    }





    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service destroyed.",Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //location start
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        gpsLocationListener=new GpsLocationListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return 0;
        }
        locationManager.requestLocationUpdates(provider, 10000, 1, gpsLocationListener);

        //location end





        Toast.makeText(this,"Location Service is running.",Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
       // return START_STICKY;
    }



    public class GpsLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location location) {
            Log.d("ServiceTag", String.valueOf(location.getLatitude()));
            Toast.makeText(gpsService.this,"LocationX:"+String.valueOf(location.getLatitude()),Toast.LENGTH_SHORT).show();
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
    }








}
