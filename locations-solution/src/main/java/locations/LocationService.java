package locations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LocationService {

    public void writeLocations(Path file, List<Location> locations) {
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (Location item : locations) {
                bw.write(item.getName()+","+item.getLat()+","+item.getLon()+"\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't write file", e);
        }
    }

    public List<Location> readLocation(Path file) {
        try (BufferedReader br = Files.newBufferedReader(file)) {
            String line;
            List<Location> locations = new ArrayList<>();
            while ((line = br.readLine()) != null) {
                String[] result = line.split(",");
                locations.add(new Location(result[0],Double.parseDouble(result[1]),Double.parseDouble(result[2])));
            }
            return locations;

        } catch (IOException e) {
            throw new IllegalStateException("file error");
        }
    }
}
