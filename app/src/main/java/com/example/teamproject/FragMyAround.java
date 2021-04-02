package com.example.teamproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.teamproject.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.ContentValues.TAG;
import static androidx.core.content.ContextCompat.getSystemService;

public class FragMyAround extends Fragment implements OnMapReadyCallback {
    private GoogleMap mGoogleMap;
    private MapView mapView;


    private GoogleApiClient mGoogleApiClient ;
    private Marker currentMarker = null;

    private static final String TAG = "googleMap_example";
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;




    LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS).setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.frag_my_around, container, false);
        mapView=(MapView)view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(this.getActivity());

        mGoogleMap = googleMap;
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        getSampleMarkerItems();

    }

    public void getSampleMarkerItems() {

        ArrayList<MarkerItem> markerList = new ArrayList();

        markerList.add(new MarkerItem(37.562603, 126.990499, "New stay inn"));
        markerList.add(new MarkerItem(37.589148, 127.018659, "RG 모텔"));
        markerList.add(new MarkerItem(37.601480, 127.020532, "W Mini hotel"));
        markerList.add(new MarkerItem(37.495893, 126.845681, "갤러리아 모텔"));
        markerList.add(new MarkerItem(37.540524, 127.063458, "건대 HOTEL K WORLD"));
        markerList.add(new MarkerItem(37.558614, 127.076551, "건대 더 디자이너스"));
        markerList.add(new MarkerItem(37.551446, 127.068994, "건대 쁠랑 "));
        markerList.add(new MarkerItem(37.588943, 127.054971, "경희대 수\t"));
        markerList.add(new MarkerItem(37.540975, 127.142258, "길동 샘"));
        markerList.add(new MarkerItem(37.654705, 127.058582, "노원 호텔 리버"));
        markerList.add(new MarkerItem(37.544186, 126.973494, "뉴월드 호텔"));
        markerList.add(new MarkerItem(37.566715, 127.053543, "답십리 SM 부티크 호텔"));
        markerList.add(new MarkerItem(37.572356, 127.047366, "답십리 밀\t"));
        markerList.add(new MarkerItem(37.573277, 127.023885, "동대문 다락"));
        markerList.add(new MarkerItem(37.617784, 126.920399, "디스테이션"));
        markerList.add(new MarkerItem(37.572334, 126.991096, "레몬트리 모텔"));
        markerList.add(new MarkerItem(37.566875, 126.987948, "롯데호텔앤리조트\t"));
        markerList.add(new MarkerItem(37.571902, 126.989559, "메이 호텔\t"));
        markerList.add(new MarkerItem(37.499109, 126.918886, "모텔봄 남구로점 모텔"));
        markerList.add(new MarkerItem(37.599834, 126.921207, "브릭스 관광 호텔"));
        markerList.add(new MarkerItem(37.474380, 126.980955, "사당 넘버 25"));
        markerList.add(new MarkerItem(37.506944, 127.054003, "삼성 디에이스"));
        markerList.add(new MarkerItem(37.595709, 127.093531, "상봉 호텔 그레이튼\t"));
        markerList.add(new MarkerItem(37.598135, 127.093704, "상봉 호텔 코안도르\t"));
        markerList.add(new MarkerItem(37.597906, 127.093721, "상봉 호텔스타\t"));
        markerList.add(new MarkerItem(37.486454, 127.013594, "서초 LAVA 교대남부터미널\t"));
        markerList.add(new MarkerItem(37.634712, 127.021318, "수유 메리엘"));
        markerList.add(new MarkerItem(37.635796, 127.024615, "수유 파티오"));
        markerList.add(new MarkerItem(37.635887, 127.024577, "수유 포시즌 호텔\t"));
        markerList.add(new MarkerItem(37.572556, 126.981136, "신라스테이 광화문\t"));
        markerList.add(new MarkerItem(37.562378, 127.006777, "써미트 호텔"));
        markerList.add(new MarkerItem(37.531876, 126.971148, "아모르 모텔"));
        markerList.add(new MarkerItem(37.557429, 126.942554, "에버에잇 레지던스"));
        markerList.add(new MarkerItem(37.517898, 126.910180, "영등포 페트라"));
        markerList.add(new MarkerItem(37.563343, 127.035042, "왕십리 FULLMOON\t"));
        markerList.add(new MarkerItem(37.562794, 127.034515, "왕십리 리젠트호텔"));
        markerList.add(new MarkerItem(37.562554, 127.034765, "왕십리 컬리넌"));
        markerList.add(new MarkerItem(37.602088, 127.062265, "외대 Life hotel Raha"));
        markerList.add(new MarkerItem(37.510933, 127.081628, "잠실 FORESTAR"));
        markerList.add(new MarkerItem(37.562703, 127.036914, "장안 새턴\t"));
        markerList.add(new MarkerItem(37.648337, 127.043814, "창동 HOTEL 99 프레스티지"));
        markerList.add(new MarkerItem(37.607723, 127.078534, "태릉 호텔 드씨엘"));
        markerList.add(new MarkerItem(37.565618, 126.979433, "프레지던트 호텔"));
        markerList.add(new MarkerItem(37.534762, 126.993572, "해밀톤 호텔"));
        markerList.add(new MarkerItem(37.560577, 126.997191, "호텔아띠"));
        markerList.add(new MarkerItem(37.551964, 126.917619, "홍대 더 디자이너스"));
        markerList.add(new MarkerItem(126.917619, 126.846286, "화곡 거기\t"));
        markerList.add(new MarkerItem(37.496191,127.029881,"강남 BNN "));
        markerList.add(new MarkerItem(37.536080,127.136833,"길동 아르고"));
        markerList.add(new MarkerItem(37.525013,126.876242,"목동 잠"));
        markerList.add(new MarkerItem(37.494226,126.985963,"방배 STYLE"));
        markerList.add(new MarkerItem(37.475626,126.980688,"사당 KOTEL"));
        markerList.add(new MarkerItem(37.483051,126.954581,"서울대입구 폭스"));
        markerList.add(new MarkerItem(37.515606,127.016519,"신사 하이웨이"));
        markerList.add(new MarkerItem(37.510746,127.080299,"신천 아이\t"));
        markerList.add(new MarkerItem(37.501308,127.042083,"역삼 컬리넌"));
        markerList.add(new MarkerItem(37.520597,126.903476,"영등포 코코"));
        markerList.add(new MarkerItem(37.518534,126.908169,"영등포 프로방스"));
        markerList.add(new MarkerItem(37.515765,127.109191,"잠실 트라움"));
        markerList.add(new MarkerItem(37.534189,127.133519,"천호 월"));
        markerList.add(new MarkerItem(37.529219,126.847286,"화곡 여우잠"));

        for (MarkerItem markerItem : markerList) {
            addMarker(markerItem, false);
        }
    }


    private Marker addMarker(MarkerItem markerItem, boolean b) {
        LatLng position = new LatLng(markerItem.getLat(), markerItem.getLon());
        String motelName = markerItem.getMotelName();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(String.valueOf(motelName));
        markerOptions.position(position);


        return mGoogleMap.addMarker(markerOptions);
    }
}
