package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Toast;


import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.MenuActivityContract;
import com.svalero.pisosalquiler.domain.Dto.HouseDto;
import com.svalero.pisosalquiler.domain.User;
import com.svalero.pisosalquiler.presenter.MenuActivityPresenter;

import java.util.List;

public class MapsActivityView extends AppCompatActivity implements MenuActivityContract.View {

    private MapView mapView;
    private Bundle bundle;
    private User user;
    private PointAnnotationManager pointAnnotationManager;
    private MenuActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_view);

        Intent intent = getIntent();
        bundle = getIntent().getExtras();
        user = (User)bundle.getSerializable("user");


        mapView = findViewById(R.id.MapView);
        initializePointManager();

        System.out.println(user);

        presenter = new MenuActivityPresenter(this);

        presenter.loadAllHouses(user);

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager
                (annotationPlugin,annotationConfig);
    }

    private void addHousesToMap(List<HouseDto> housesDto) {
        for (HouseDto houseDto : housesDto) {
            Point point = Point.fromLngLat(houseDto.getLongitudeHouse(),
                    houseDto.getLatitudeHouse());
            addMarker(point, houseDto.getAddressHouse());
        }
        HouseDto lastHouse = housesDto.get(housesDto.size() - 1);
        setCameraPosition(Point.fromLngLat(lastHouse.getLongitudeHouse(),
                lastHouse.getLatitudeHouse()));
    }

    private void addMarker(Point point, String address) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(address)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.house));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

    @Override
    public void showHouses(List<HouseDto> housesDto) {
        addHousesToMap(housesDto);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}