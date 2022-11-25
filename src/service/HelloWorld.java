/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.teamdev.jxmaps.GeocoderCallback;
import com.teamdev.jxmaps.GeocoderRequest;
import com.teamdev.jxmaps.GeocoderResult;
import com.teamdev.jxmaps.GeocoderStatus;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapViewOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.swing.MapView;
import entities.Position;
import java.util.List;

/**
 *
 * @author zidah
 */
  public class HelloWorld extends MapView {
    public HelloWorld(MapViewOptions options,List<Position> pos ) {
        super(options);
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                    final Map map = getMap();
                    map.setZoom(5.0);
                    GeocoderRequest request = new GeocoderRequest(map);
                    //request.setLocation(new LatLng(1478523.0,156464.0));
                    
                    
                   request.setAddress("youssoufia");

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                        @Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                Marker marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());
                                
                               
                                
                                final InfoWindow window = new InfoWindow(map);
                                for(Position p1 : pos){
                                    
                                Marker marker2 = new Marker(map);
                                marker2.setPosition(new LatLng(p1.getLatitude(),p1.getLongitude()) );
                                
                                window.open(map, marker2);
                                }
                                window.setContent("Hello, World!");
                                
                                window.open(map, marker);
                                 
                            }
                        }
                    });
                }
            }
        });
    }

   

   }
