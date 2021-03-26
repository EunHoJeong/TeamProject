package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.teamproject.R;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.NaverMapOptions;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;

public class SearchMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    private MapView mapView;
    private static final LatLng COORD_1 = new LatLng(35.1798159, 129.0750222);
    private static final LatLng COORD_2 = new LatLng(37.5666102, 126.9783881);

    private static final int LOCATION_PERMISSION_REQUEST_CODE=1000;
    private FusedLocationSource locationSource;
    private  NaverMap naverMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_map);

        mapView=findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);


        locationSource=new FusedLocationSource(this,LOCATION_PERMISSION_REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap=naverMap;

        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);


        UiSettings uiSettings=naverMap.getUiSettings();
        uiSettings.setCompassEnabled(true); //나침반
        uiSettings.setScaleBarEnabled(true);//거리
        uiSettings.setZoomControlEnabled(true);//줌
        uiSettings.setLocationButtonEnabled(true);//내가 있는곳

//        마커 찍는 기능
//        Marker marker1=new Marker();
//        marker1.setPosition(COORD_1);
//        marker1.setMap(naverMap);
//
//        Marker marker2=new Marker();
//        marker2.setPosition(COORD_2);
//        marker2.setMap(naverMap);
    }
}