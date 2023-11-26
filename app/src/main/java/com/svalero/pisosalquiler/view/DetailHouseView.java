package com.svalero.pisosalquiler.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.svalero.pisosalquiler.R;
import com.svalero.pisosalquiler.contract.DetailHouseContract;
import com.svalero.pisosalquiler.domain.House;
import com.svalero.pisosalquiler.presenter.DetailHousePresenter;
import com.mapbox.maps.MapView;

public class DetailHouseView extends AppCompatActivity implements DetailHouseContract.View {

    private MapView superMap;

    private Point point;

    private PointAnnotationManager pointAnnotationManager;
    private DetailHousePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_house_view);

        presenter = new DetailHousePresenter(this);

        superMap = findViewById(R.id.SuperMap);

        initializePointManager();

        Intent intent = getIntent();
        String idHouse = intent.getStringExtra("idHouse");
        if (idHouse == null)
            return;

        presenter.loadHouse(idHouse);
    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(superMap);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin,annotationConfig);
    }

    private void addHouseToMap(House house) {
        Point point = Point.fromLngLat(house.getLongitudeHouse(), house.getLatitudeHouse());
        addMarker(point, house.getAddressHouse());
        setCameraPosition(Point.fromLngLat(house.getLongitudeHouse(),house.getLatitudeHouse()));
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
                .zoom(14.0)
                .bearing(-17.6)
                .build();
        superMap.getMapboxMap().setCamera(cameraPosition);
    }


    @Override
    public void showHouse (House house) {
        TextView etIdHouse = findViewById(R.id.etIdHouseDetalle);
        TextView etAddressHouse = findViewById(R.id.etAddressHouse);
        TextView etPostalCode = findViewById(R.id.etPostalCode);
        TextView etCityHouse = findViewById(R.id.etCityHouse);

        String id = String.valueOf(house.getIdHouse());
        etIdHouse.setText(id);
        etAddressHouse.setText(house.getAddressHouse());
        etPostalCode.setText(Integer.toString(house.getPostalCodeHouse()));
        etCityHouse.setText(house.getCityHouse());

        addHouseToMap(house);
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((TextView) findViewById(R.id.etIdHouseDetalle)),
                errorMessage, BaseTransientBottomBar.LENGTH_LONG).show();
    }


}