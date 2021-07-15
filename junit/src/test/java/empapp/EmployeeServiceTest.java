package empapp;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;
import static org.assertj.core.groups.Tuple.tuple;
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

    @Test
    void testCalcYearly() {
        assertTimeout(Duration.ofSeconds(6), () -> new EmployeeService().calculateYearlyReport());
        assertTimeoutPreemptively(Duration.ofSeconds(3), () -> new EmployeeService().calculateYearlyReport());
    }

    @Test
    void testList() {
        List<Employee> employees = new EmployeeService().listEmployees();

        Employee employee = employees.get(0);

        assertThat(employee.getName())
                .isEqualTo("John Doe")
                .startsWith("John")
                .endsWith("Doe");

        assertThat(employees)
                .hasSize(2)
                .extracting(Employee::getName,Employee::getYearOfBirth)
                .contains(tuple("John Doe",1970));
    }

    @Test
    void softtest() {
        List<Employee> employees = new EmployeeService().listEmployees();

        Employee employee = employees.get(0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(employee.getName()).startsWith("xxx");
        softly.assertThat(employee.getName()).startsWith("yyy");
        softly.assertAll();

        assumeThat(employee.getName()).isEqualTo("xxx");
    }

    @Test
    void assumetest() {
        List<Employee> employees = new EmployeeService().listEmployees();

        Employee employee = employees.get(0);

        assumeThat(employee.getName()).isEqualTo("xxx");
    }
}