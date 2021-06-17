package locations;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
}
