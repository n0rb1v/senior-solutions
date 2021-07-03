package empapp;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class EmployeeServiceTest {

    @Test
    void listEmployees() {
        assertEquals(List.of("John Doe", "Jane Doe"), new EmployeeService().listNames());
    }

    @Test
    void listNames() {
        EmployeeService employeeService = null;//new EmployeeService();
        assumeTrue(employeeService != null);
        assertEquals(List.of("John Doe", "Jane Doe"), employeeService.listEmployees().stream()
                .map(Employee::getName)
                .collect(Collectors.toList())
        );
    }
}