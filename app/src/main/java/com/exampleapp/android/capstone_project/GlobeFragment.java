package com.exampleapp.android.capstone_project;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mousebird.maply.Atmosphere;
import com.mousebird.maply.GlobeMapFragment;
import com.mousebird.maply.MaplyBaseController;
import com.mousebird.maply.MaplyStarModel;
import com.mousebird.maply.Point3d;
import com.mousebird.maply.QuadImageTileLayer;
import com.mousebird.maply.RemoteTileInfo;
import com.mousebird.maply.RemoteTileSource;
import com.mousebird.maply.SphericalMercatorCoordSystem;

import java.io.File;
import java.io.IOException;

/**
 * Created by Aiman Nabeel on 20/10/2018.
 */
/**
 * A simple {@link Fragment} subclass.
 */
public class GlobeFragment extends GlobeMapFragment {


    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle inState) {
        super.onCreateView(inflater, container, inState);

        // Do app specific setup logic.

        return baseControl.getContentView();
    }

    @Override
    protected MapDisplayType chooseDisplayType() {
        return MapDisplayType.Globe;
    }

    @Override
    protected void controlHasStarted() {
        // setup base layer tiles
        String cacheDirName = "stamen_watercolor";
        File cacheDir = new File(getActivity().getCacheDir(), cacheDirName);
        cacheDir.mkdir();
        //RemoteTileSource remoteTileSource = new RemoteTileSource(new RemoteTileInfo("http://tile.stamen.com/watercolor/", "png", 0, 18));
        //Ref: http://examples.webglearth.com/#satellite
        RemoteTileSource remoteTileSource = new RemoteTileSource(new RemoteTileInfo("http://tileserver.maptiler.com/nasa/{z}/{x}/{y}", "jpg", 0, 16));
        remoteTileSource.setCacheDir(cacheDir);
        SphericalMercatorCoordSystem coordSystem = new SphericalMercatorCoordSystem();

        // globeControl is the controller when using MapDisplayType.Globe
        // mapControl is the controller when using MapDisplayType.Map
        QuadImageTileLayer baseLayer = new QuadImageTileLayer(globeControl, coordSystem, remoteTileSource);
        baseLayer.setImageDepth(1);
        baseLayer.setSingleLevelLoading(false);
        baseLayer.setUseTargetZoomLevel(false);
        baseLayer.setCoverPoles(true);
        baseLayer.setHandleEdges(true);
        //baseControl.setClearColor(Color.rgb(255,255,255));
        //baseControl.setClearColor(Color.alpha((int) 0.01));
        //baseControl.setClearColor(Color.TRANSPARENT);
        //globeSettings.clearColor = Color.TRANSPARENT;
        //globeSettings.clearColor = Color.RED;

        // add layer and position
        globeControl.addLayer(baseLayer);
        //globeControl.animatePositionGeo(-3.6704803, 40.5023056, 5, 1.0);
        globeControl.animatePositionGeo(-3.6704803, 40.5023056, 1.5, -10.0);
        globeControl.setAutoRotate(0,60);
        addStars();
        addAtmosphere();
    }

    //WhirlyGlobe Component Tester class Ref: https://github.com/mousebird/WhirlyGlobe/tree/master/ios/apps/WhirlyGlobeComponentTester
    //Ref: https://github.com/imobdevdevice/Ibobber/blob/master/app/src/main/java/com/reelsonar/ibobber/WhirlyGlobeFragment.java
    private void addStars() {

        try {
            MaplyStarModel starModel = new MaplyStarModel( "starcatalog_orig.txt", "star_background.png", getActivity() );
            starModel.addToViewc( globeControl, MaplyBaseController.ThreadMode.ThreadAny);
        } catch ( IOException exc) {
            Log.e("WhirlyGlobeFragment", "Got error adding stars");
        }
    }

    //Ref: https://github.com/mousebird/WhirlyGlobe/blob/master/android/library/Android/src/com/mousebird/maply/Atmosphere.java
    //Ref: https://github.com/mousebird/WhirlyGlobe/blob/master/android/apps/AutoTesterAndroid/app/src/main/java/com/mousebirdconsulting/autotester/TestCases/AtmosphereTestCase.java
    private void addAtmosphere() {
        Atmosphere atmosphere = new Atmosphere(globeControl,  MaplyBaseController.ThreadMode.ThreadAny);
        atmosphere.setSunPosition(new Point3d(1.0,0.0,0.0));
        atmosphere.setWaveLength(new float[]{0.650f, 0.570f, 0.475f});
        atmosphere.getOuterRadius();
    }

}
