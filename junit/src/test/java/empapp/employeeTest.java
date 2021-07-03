package empapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class employeeTest {
    @Test
    void name() {
        Employee employee = new Employee("John Doe", 1970);

        int age = employee.getAge(2000);

        assertEquals(30,age);
    }
}