package com.example.bluest.maps;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.example.bluest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bluest.databinding.ActivityWisataMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class WisataMaps extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Polyline polyline;
    private ActivityWisataMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWisataMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        double latitude = getIntent().getDoubleExtra("latitude", 0.0);
        double longitude = getIntent().getDoubleExtra("longitude", 0.0);
        // Add a marker in Sydney and move the camera
        LatLng origin = new LatLng(-6.2088, 106.8456);
        LatLng location = new LatLng(latitude,longitude);
        mMap.addMarker(new MarkerOptions().position(location).title("Selected Place"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location,5));

        drawPolyline(origin, location);


    }
    private void drawPolyline(LatLng origin, LatLng destination) {
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(origin, destination)
                .width(5f)
                .color(R.color.coklatCerah); // Anda dapat mengganti warna sesuai keinginan

        polyline = mMap.addPolyline(polylineOptions);
    }

}