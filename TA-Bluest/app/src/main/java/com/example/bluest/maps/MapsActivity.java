package com.example.bluest.maps;


import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bluest.R;
import com.example.bluest.databinding.ActivityMapsBinding;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GetAddressTask.OnTaskCompleted {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    FusedLocationProviderClient fusedLocationProviderClient;
    static final int REQUEST_LOCATION_PERMISSION=1;
    private TextView locationTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        locationTextView=findViewById(R.id.titik);
        fusedLocationProviderClient= LocationServices
                .getFusedLocationProviderClient(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                String location=searchView.getQuery().toString();
//                List<Address> addressList=null;
//                if (location != null){
//                    Geocoder geocoder=new Geocoder(MapsActivity.this);
//                    try {
//                        addressList=geocoder.getFromLocationName(location,1);
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }
//                    Address address=addressList.get(0);
//                    LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
////                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
////                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
//                }
//
//
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                return false;
//            }
//        });

        mapFragment.getMapAsync(this);
//        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.autoCompleteTextView);
//        Places.initialize(getApplicationContext(), getString(R.string.google_maps_key));
//
//        AutocompleteSessionToken token = AutocompleteSessionToken.newInstance();
//        AutocompletePredictionsRequest request = AutocompletePredictionsRequest.builder()
//                .setTypeFilter(TypeFilter.ADDRESS)
//                .setSessionToken(token)
//                .setQuery(query) // query dari AutoCompleteTextView
//                .build();
//
//        PlacesClient placesClient = Places.createClient(this);
//        placesClient.findAutocompletePredictions(request).addOnSuccessListener((response) -> {
//            // Handle hasil pencarian di sini
//        }).addOnFailureListener((exception) -> {
//            if (exception instanceof ApiException) {
//                ApiException apiException = (ApiException) exception;
//                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
//            }
//        });

// Menangani pemilihan lokasi
//        autoCompleteTextView.setOnItemClickListener((adapterView, view, position, id) -> {
//            AutocompletePrediction item = adapter.getItem(position);
//            if (item != null) {
//                String placeId = item.getPlaceId();
//                List<Place.Field> placeFields = Arrays.asList(Place.Field.LAT_LNG, Place.Field.NAME);
//
//                FetchPlaceRequest request = FetchPlaceRequest.builder(placeId, placeFields).build();
//                placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
//                    Place place = response.getPlace();
//                    LatLng latLng = place.getLatLng();
//
//                    // Tambahkan marker atau geser kamera ke lokasi terpilih di peta
//                }).addOnFailureListener((exception) -> {
//                    if (exception instanceof ApiException) {
//                        ApiException apiException = (ApiException) exception;
//                        Log.e(TAG, "Place not found: " + apiException.getStatusCode());
//                    }
//                });
//            }
//        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        getLocation();
    }
    //awal
    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }else {
            /*fusedLocationProviderClient.getLastLocation().addOnSuccessListener(
                    new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location!=null){

                    }
                }
            });*/

            fusedLocationProviderClient.requestLocationUpdates(getLocationRequest(),
                    new LocationCallback(){
                        @Override
                        public void onLocationResult(@NonNull LocationResult locationResult) {
                            Location location=locationResult.getLastLocation();
                            if(location!=null){
                                String lat=location.getLatitude()+"";
                                String lng=location.getLongitude()+"";
                                LatLng loc=new LatLng(location.getLatitude(),location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(loc)
                                        .title("LAT:"+lat+" - LNG:"+lng));
                                float zoom=15;
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc,zoom));
                                new GetAddressTask(MapsActivity.this,
                                        MapsActivity.this).execute(location);
                            }
                        }
                    }, null);
        }
    }

    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest=new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_LOCATION_PERMISSION:
                if(grantResults.length>0 && grantResults[0]
                        ==PackageManager.PERMISSION_GRANTED){
                    getLocation();
                }
        }
    }
    @Override
    public void onTaskCompleted(String result) {
        locationTextView.setText("Alamat : "+result);
    }

    //rute!

}