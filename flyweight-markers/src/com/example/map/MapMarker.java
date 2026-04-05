package com.example.map;

// extrinsic state only (lat, lng, label) - style is shared via flyweight
public class MapMarker {

    private final double lat;
    private final double lng;
    private final String label;
    private final MarkerStyle style; // shared, comes from factory

    public MapMarker(double lat, double lng, String label, MarkerStyle style) {
        this.lat = lat;
        this.lng = lng;
        this.label = label;
        this.style = style;
    }

    public double getLat() { return lat; }
    public double getLng() { return lng; }
    public String getLabel() { return label; }
    public MarkerStyle getStyle() { return style; }
}
