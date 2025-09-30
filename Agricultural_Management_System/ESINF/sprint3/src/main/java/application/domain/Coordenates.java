package application.domain;

public class Coordenates {
     private double lat;

     private double lng;

     public Coordenates(double lat, double lng){
         this.lat = lat;
         this.lng = lng;
     }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    @Override
    public String toString() {
        return String.format("(Latitude: %.2f ; Longitude: %.2f)", lat, lng);
    }


    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
