package supfitness.luciole.com.supfitness.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.text.DecimalFormat;

import supfitness.luciole.com.supfitness.R;

public class TabFragment3 extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener{

    private MapView mapView;
    private GoogleMap map;
    private Marker marker;
    private TextView positionTextview;

    private static double SUPINFO_LONGITUDE = 3.52169;
    private static double SUPINFO_LATITUDE = 50.36246;
    private String position_string_value;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_3, container, false);

        position_string_value = getResources().getString(R.string.map_position);
        positionTextview = (TextView)view.findViewById(R.id.marker_position_textview);
        positionTextview.setText(position_string_value + "\r\n" + SUPINFO_LATITUDE + "\r\nLONG : " + SUPINFO_LONGITUDE);

        mapView = (MapView)view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onResume(){
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause(){
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.map = googleMap;
        try{
            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.setMyLocationEnabled(true);
            try{
                MapsInitializer.initialize(this.getActivity());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(SUPINFO_LATITUDE, SUPINFO_LONGITUDE), 15);
            marker = map.addMarker(new MarkerOptions().position(new LatLng(SUPINFO_LATITUDE, SUPINFO_LONGITUDE)).title("Supinfo").draggable(true));
            map.setOnMarkerDragListener(this);
            map.animateCamera(cameraUpdate);
        }
        catch (SecurityException ex){
            ex.printStackTrace();
        }


    }

    @Override
    public void onMarkerDragStart(Marker marker) {
        setlabelMarkerPosition();
    }

    @Override
    public void onMarkerDrag(Marker marker) {
        setlabelMarkerPosition();
    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        setlabelMarkerPosition();
    }

    private void setlabelMarkerPosition(){
        DecimalFormat df = new DecimalFormat("#.#####");
        LatLng position = marker.getPosition();
        positionTextview.setText(position_string_value + "\r\n" + df.format(position.latitude) + "\r\nLONG : " + df.format(position.longitude));
    }
}