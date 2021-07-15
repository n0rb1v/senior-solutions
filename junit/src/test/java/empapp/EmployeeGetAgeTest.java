package empapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class EmployeeGetAgeTest {
    Employee employee;

    @Nested
    class WithYear1980 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1980);
        }

        @Test
        void test1() {
        }

        @Test
        void test2() {
        }

        @Test
        void test3() {
        }
    }

    @Nested
    class WithYear1990 {
        @BeforeEach
        void init() {
            employee = new Employee("John Doe", 1980);
        }

        @Test
        void test1() {
        }

        @Test
        void test2() {
        }

        @Test
        void test3() {
        }
    }
}

