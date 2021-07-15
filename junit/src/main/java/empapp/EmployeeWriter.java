package empapp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EmployeeWriter {

    public void write(Path file, List<Employee> employees) {
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (Employee item : employees) {
                bw.write(item.getName()+", "+item.getYearOfBirth()+"\n");
            }
        } catch (IOException e) {
            throw new IllegalStateException("Can't write file", e);
        }
    }
}
