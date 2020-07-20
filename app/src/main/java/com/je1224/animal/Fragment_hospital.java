package com.je1224.animal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.List;

public class Fragment_hospital extends Fragment {

    Context context;

    MapView mapView;
    TextView tvTitle,tvAdd,tvTime,tvTel;
    LocationManager locationManager;
    ImageView ivBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        context = container.getContext();

        View view = inflater.inflate(R.layout.frag_hospital, container, false);

        tvTitle = view.findViewById(R.id.title);
        tvAdd = view.findViewById(R.id.address);
        tvTime = view.findViewById(R.id.time);
        tvTel = view.findViewById(R.id.telephone);
        ivBtn = view.findViewById(R.id.my_location);

        mapView=new MapView(getActivity());
        RelativeLayout mapContainer=view.findViewById(R.id.map_view);
        mapContainer.addView(mapView);

        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.5,126.97),true);
        mapView.setZoomLevel(5,true);
        mapView.zoomIn(true);
        mapView.zoomOut(true);

        return view;
    }
}
