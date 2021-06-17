public class LocationParser {

    public Location parse(String text) {
        String[] result = text.split(",");
        return new Location(result[0],Double.parseDouble(result[1]),Double.parseDouble(result[2]));
    }

}
