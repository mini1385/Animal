package com.je1224.animal;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.ArrayList;

public class Fragment_hospital extends Fragment {

    Context context;

    MapView mapView;
    TextView tvTitle,tvAdd,tvTel,tvSite;

    LocationManager locationManager;
    ImageView ivBtn;

    ArrayList<String> hospitalList = new ArrayList<>(); // 동물병원
    ArrayList<String> hospital24List = new ArrayList<>(); // 24시 동물병원
    ArrayList<String> pharmacyList = new ArrayList<>(); // 동물약국

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = container.getContext();

        View view = inflater.inflate(R.layout.frag_hospital, container, false);

        tvTitle = view.findViewById(R.id.title);
        tvAdd = view.findViewById(R.id.address);
        tvTel = view.findViewById(R.id.telephone);
        tvSite = view.findViewById(R.id.site);
        ivBtn = view.findViewById(R.id.my_location);

        mapView=new MapView(getActivity());
        RelativeLayout mapContainer=view.findViewById(R.id.map_view);
        mapContainer.addView(mapView);

        locationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult=ActivityCompat.checkSelfPermission(context,Manifest.permission.ACCESS_FINE_LOCATION);
            if(permissionResult != PackageManager.PERMISSION_GRANTED){
                String[] permissions=new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions,10);
            }
        }

        myLocation();
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        ivBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMyLocation();
            }
        });



        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 10:
                if(grantResults[0]==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(context, "위치정보 제공에 동의하지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void myLocation(){

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled("gps")) {
           locationManager.requestLocationUpdates("gps",5000,2,locationListener);
        }else if(locationManager.isProviderEnabled("network")){
            locationManager.requestLocationUpdates("network", 5000, 2, locationListener);
        }

    }

    LocationListener locationListener=new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double latitude= location.getLatitude();
            double longitude= location.getLongitude();

            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude,longitude),5,true);
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
    };

    Location location=null;

    public void changeMyLocation(){

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (locationManager.isProviderEnabled("gps")) {
            location = locationManager.getLastKnownLocation("gps");
        }else if(locationManager.isProviderEnabled("network")){
            location = locationManager.getLastKnownLocation("network");
        }

        if(location==null){
            Toast.makeText(context, "위치조회에 실패하였습니다.", Toast.LENGTH_SHORT).show();
        }else{
            double latitude= location.getLatitude();
            double longitude= location.getLongitude();
            mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(latitude,longitude),5,true);
        }

    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        locationManager.removeUpdates(locationListener);
        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOff);
        mapView.setShowCurrentLocationMarker(false);
    }
}
