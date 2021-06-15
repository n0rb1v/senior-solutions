import java.util.Objects;

public class Location {
    private String name;
    private double lat;
    private double lon;

    public Location(String name, double lat, double lon) {
        if (lat > 90 || lat < -90) {
            throw new IllegalArgumentException("hibas parameter2");
        }
        if (lon > 180 || lon < -180) {
            throw new IllegalArgumentException("hibas parameter3");
        }
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public boolean isOnEquator() {
        if (lat == 0) {
            return true;
        }
        return false;
    }

    public boolean isOnPrimeMeridian() {
        if (lon == 0) {
            return true;
        }
        return false;
    }

    public double distanceFrom(Location loc) {

        final int R = 6371; // Radius of the earth
        double latDistance = Math.toRadians(loc.getLat() - this.lat);
        double lonDistance = Math.toRadians(loc.getLon() - this.lon);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.lat)) * Math.cos(Math.toRadians(loc.getLat()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 && Double.compare(location.lon, lon) == 0 && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lat, lon);
    }

    @Override
    public String toString() {
        return "name=" + name;
    }
}
