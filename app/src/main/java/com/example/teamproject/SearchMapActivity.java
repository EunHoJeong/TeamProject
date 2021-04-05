package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.media.CamcorderProfile.get;

public class SearchMapActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, GoogleMap.OnMarkerClickListener {
    private GoogleApiClient mGoogleApiClient = null;
    private Marker currentMarker = null;

    private static final String TAG = "googleMap_example";
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 2002;
    private static final int UPDATE_INTERVAL_MS = 1000;
    private static final int FASTEST_UPDATE_INTERVAL_MS = 500;

    private AppCompatActivity mActivity;
    boolean askPermissionOnceAgain = false;
    boolean mRequestingLocationUpdates = false;
    Location mCurrentLocatiion;
    boolean mMoveMapByUser = true;
    boolean mMoveMapByAPI = true;
    LatLng currentPosition;
    LatLng currentPosition1;
    ArrayList<MarkerItem> markerList = new ArrayList();
    MarkerItem markerItem;
    Marker selectedMarker;
    Geocoder geocoder;
    //Location location1 = null;



    LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setInterval(UPDATE_INTERVAL_MS).setFastestInterval(FASTEST_UPDATE_INTERVAL_MS);

    private GoogleMap mGoogleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_search_map);
        setTitle("주변 숙소 찾기");



        mActivity = this;

        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_google1);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (mGoogleApiClient.isConnected()) {

            Log.d(TAG, "onResume : call startLocationUpdates");
            if (!mRequestingLocationUpdates) startLocationUpdates();
        }


        //앱 정보에서 퍼미션을 허가했는지를 다시 검사해봐야 한다.
        if (askPermissionOnceAgain) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                askPermissionOnceAgain = false;

                checkPermissions();
            }
        }
    }

    private void startLocationUpdates() {

        if (!checkLocationServicesStatus()) {

            Log.d(TAG, "startLocationUpdates : call showDialogForLocationServiceSetting");
            showDialogForLocationServiceSetting();
        } else {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                Log.d(TAG, "startLocationUpdates : 퍼미션 안가지고 있음");
                return;
            }


            Log.d(TAG, "startLocationUpdates : call FusedLocationApi.requestLocationUpdates");
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
            mRequestingLocationUpdates = true;

            mGoogleMap.setMyLocationEnabled(true);

        }

    }


    private void stopLocationUpdates() {

        Log.d(TAG, "stopLocationUpdates : LocationServices.FusedLocationApi.removeLocationUpdates");
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        mRequestingLocationUpdates = false;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MarkerOptions markerOptions = new MarkerOptions();
        geocoder=new Geocoder(this);

        Log.d(TAG, "onMapReady :");

        mGoogleMap = googleMap;
        String locationAddress = getIntent().getStringExtra("location");
        if(locationAddress == null){
//            setDefaultLocation();
//            MarkerOptions markerOptions2=new MarkerOptions();
//            Location location1 = null;
//            markerOptions2.title("현재위치");
//            LatLng currentLatLng1 = new LatLng(location1.getLatitude(), location1.getLongitude());
//            mGoogleMap.addMarker(markerOptions2);
//            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng1,15));



            LocationCallback locationCallback = new LocationCallback() {
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);

                    List<Location> locationList = locationResult.getLocations();

                    if (locationList.size() > 0) {
                        Location location = locationList.get(locationList.size() - 1);
                        //location = locationList.get(0);

                        currentPosition1
                                = new LatLng(location.getLatitude(), location.getLongitude());


                        String markerTitle = getCurrentAddress(currentPosition1);
                        String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                                + " 경도:" + String.valueOf(location.getLongitude());

                        Log.d(TAG, "onLocationResult : " + markerSnippet);


                        //현재 위치에 마커 생성하고 이동
                        setCurrentLocation(location, markerTitle, markerSnippet);

                        mCurrentLocatiion = location;
                    }


                }

            };


        }else{
            List<Address> addressList=null;
            try {
                addressList=geocoder.getFromLocationName(locationAddress,20);
            }catch (Exception e){
                e.printStackTrace();
            }
            String[] splitStr=addressList.get(0).toString().split(",");
            String address=splitStr[0].substring(splitStr[0].indexOf("/")+1,splitStr[0].length()-2);
            String latitude=splitStr[10].substring(splitStr[10].indexOf("=")+1);
            String longitude=splitStr[12].substring(splitStr[12].indexOf("=")+1);
            LatLng point=new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude));
            MarkerOptions markerOptions1=new MarkerOptions();
            markerOptions1.title(locationAddress);
            markerOptions1.snippet(address);
            markerOptions1.position(point);
            mGoogleMap.addMarker(markerOptions1);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point,15));


        }


        //런타임 퍼미션 요청 대화상자나 GPS 활성 요청 대화상자 보이기전에
        //지도의 초기위치를 서울로 이동

        //mGoogleMap.getUiSettings().setZoomControlsEnabled(false);
        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        getSampleMarkerItems();
        mGoogleMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {

            @Override
            public boolean onMyLocationButtonClick() {

                Log.d(TAG, "onMyLocationButtonClick : 위치에 따른 카메라 이동 활성화");
                mMoveMapByAPI = true;
                return true;
            }
        });
        mGoogleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {

                Log.d(TAG, "onMapClick :");
            }
        });

        mGoogleMap.setOnCameraMoveStartedListener(new GoogleMap.OnCameraMoveStartedListener() {

            @Override
            public void onCameraMoveStarted(int i) {

                if (mMoveMapByUser == true && mRequestingLocationUpdates) {

                    Log.d(TAG, "onCameraMove : 위치에 따른 카메라 이동 비활성화");
                    mMoveMapByAPI = false;
                }

                mMoveMapByUser = true;

            }
        });


        mGoogleMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {

            @Override
            public void onCameraMove() {


            }
        });

    }



    private void getSampleMarkerItems() {


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

    private Marker addMarker(MarkerItem markerItem, boolean isSelectedMarker) {
        LatLng position = new LatLng(markerItem.getLat(), markerItem.getLon());
        String motelName = markerItem.getMotelName();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.title(String.valueOf(motelName));
        markerOptions.position(position);


        return mGoogleMap.addMarker(markerOptions);
    }


    @Override
    public void onLocationChanged(Location location) {

        currentPosition
                = new LatLng(location.getLatitude(), location.getLongitude());


        Log.d(TAG, "onLocationChanged : ");

        String markerTitle = getCurrentAddress(currentPosition);
        String markerSnippet = "위도:" + String.valueOf(location.getLatitude())
                + " 경도:" + String.valueOf(location.getLongitude());

        //현재 위치에 마커 생성하고 이동
        //setCurrentLocation(location, markerTitle, markerSnippet);

        mCurrentLocatiion = location;
    }



    @Override
    protected void onStart() {

        if (mGoogleApiClient != null && mGoogleApiClient.isConnected() == false) {

            Log.d(TAG, "onStart: mGoogleApiClient connect");
            mGoogleApiClient.connect();
        }

        super.onStart();
    }

    @Override
    protected void onStop() {

        if (mRequestingLocationUpdates) {

            Log.d(TAG, "onStop : call stopLocationUpdates");
            stopLocationUpdates();
        }

        if (mGoogleApiClient.isConnected()) {

            Log.d(TAG, "onStop : mGoogleApiClient disconnect");
            mGoogleApiClient.disconnect();
        }

        super.onStop();
    }


    @Override
    public void onConnected(Bundle connectionHint) {


        if (mRequestingLocationUpdates == false) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);

                if (hasFineLocationPermission == PackageManager.PERMISSION_DENIED) {

                    ActivityCompat.requestPermissions(mActivity,
                            new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                            PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                } else {

                    Log.d(TAG, "onConnected : 퍼미션 가지고 있음");
                    Log.d(TAG, "onConnected : call startLocationUpdates");
                    startLocationUpdates();
                    mGoogleMap.setMyLocationEnabled(true);
                }

            } else {

                Log.d(TAG, "onConnected : call startLocationUpdates");
                startLocationUpdates();
                mGoogleMap.setMyLocationEnabled(true);
            }
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Log.d(TAG, "onConnectionFailed");
        setDefaultLocation();
    }


    @Override
    public void onConnectionSuspended(int cause) {

        Log.d(TAG, "onConnectionSuspended");
        if (cause == CAUSE_NETWORK_LOST)
            Log.e(TAG, "onConnectionSuspended(): Google Play services " +
                    "connection lost.  Cause: network lost.");
        else if (cause == CAUSE_SERVICE_DISCONNECTED)
            Log.e(TAG, "onConnectionSuspended():  Google Play services " +
                    "connection lost.  Cause: service disconnected");
    }


    public String getCurrentAddress(LatLng latlng) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latlng.latitude,
                    latlng.longitude,
                    1);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }


        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "주소 미발견";

        } else {
            Address address = addresses.get(0);
            return address.getAddressLine(0).toString();
        }

    }


    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }


    public void setCurrentLocation(Location location, String markerTitle, String markerSnippet) {

        mMoveMapByUser = false;


        if (currentMarker != null) currentMarker.remove();


        LatLng currentLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLatLng);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(false);


        currentMarker = mGoogleMap.addMarker(markerOptions);


        if (mMoveMapByAPI) {

            Log.d(TAG, "setCurrentLocation :  mGoogleMap moveCamera "
                    + location.getLatitude() + " " + location.getLongitude());
            //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLatLng, 15);
            //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(currentLatLng);
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,15));
        }
    }



    public void setDefaultLocation() {

        mMoveMapByUser = false;


        //디폴트 위치, Seoul
        LatLng DEFAULT_LOCATION = new LatLng(37.56, 126.97);
        String markerTitle = "위치정보 가져올 수 없음";
        String markerSnippet = "위치 퍼미션과 GPS 활성 요부 확인하세요";


        if (currentMarker != null) currentMarker.remove();

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(DEFAULT_LOCATION);
        markerOptions.title(markerTitle);
        markerOptions.snippet(markerSnippet);
        markerOptions.draggable(true);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
        currentMarker = mGoogleMap.addMarker(markerOptions);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(DEFAULT_LOCATION, 15);
        mGoogleMap.moveCamera(cameraUpdate);

    }


    //여기부터는 런타임 퍼미션 처리을 위한 메소드들
    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        boolean fineLocationRationale = ActivityCompat
                .shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (hasFineLocationPermission == PackageManager
                .PERMISSION_DENIED && fineLocationRationale)
            showDialogForPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");

        else if (hasFineLocationPermission
                == PackageManager.PERMISSION_DENIED && !fineLocationRationale) {
            showDialogForPermissionSetting("퍼미션 거부 + Don't ask again(다시 묻지 않음) " +
                    "체크 박스를 설정한 경우로 설정에서 퍼미션 허가해야합니다.");
        } else if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED) {


            Log.d(TAG, "checkPermissions : 퍼미션 가지고 있음");

            if (mGoogleApiClient.isConnected() == false) {

                Log.d(TAG, "checkPermissions : 퍼미션 가지고 있음");
                mGoogleApiClient.connect();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        if (permsRequestCode
                == PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION && grantResults.length > 0) {

            boolean permissionAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

            if (permissionAccepted) {


                if (mGoogleApiClient.isConnected() == false) {

                    Log.d(TAG, "onRequestPermissionsResult : mGoogleApiClient connect");
                    mGoogleApiClient.connect();
                }


            } else {

                checkPermissions();
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.M)
    private void showDialogForPermission(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchMapActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(false);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                ActivityCompat.requestPermissions(mActivity,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        });

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }

    private void showDialogForPermissionSetting(String msg) {

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchMapActivity.this);
        builder.setTitle("알림");
        builder.setMessage(msg);
        builder.setCancelable(true);
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                askPermissionOnceAgain = true;

                Intent myAppSettings = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.parse("package:" + mActivity.getPackageName()));
                myAppSettings.addCategory(Intent.CATEGORY_DEFAULT);
                myAppSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mActivity.startActivity(myAppSettings);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        builder.create().show();
    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SearchMapActivity.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d(TAG, "onActivityResult : 퍼미션 가지고 있음");


                        if (mGoogleApiClient.isConnected() == false) {

                            Log.d(TAG, "onActivityResult : mGoogleApiClient connect ");
                            mGoogleApiClient.connect();
                        }
                        return;
                    }
                }

                break;
        }
    }

    private Marker addMarker(Marker marker, boolean isSelectedMarker) {
        double lat = marker.getPosition().latitude;
        double lon = marker.getPosition().longitude;
        String motelName = String.valueOf(marker.getTitle());
        MarkerItem temp = new MarkerItem(lat, lon, motelName);
        return addMarker(temp, isSelectedMarker);
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(marker.getPosition());
        mGoogleMap.animateCamera(center);

        changeSelectedMarker(marker);

        return true;


    }

    private void changeSelectedMarker(Marker marker) {
        if (selectedMarker != null) {
            addMarker(selectedMarker, false);
            selectedMarker.remove();
        }
        if (marker != null) {
            selectedMarker = addMarker(marker, true);
            marker.remove();
        }
    }

    public void onMapClick(LatLng latLng) {
        changeSelectedMarker(null);
    }
}
