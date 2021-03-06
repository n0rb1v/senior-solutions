package empapp;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class EmployeeTest {
    Employee employee;

    public EmployeeTest() {
        System.out.println("Constructor");
    }
    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all");
    }
    @BeforeEach
    void init(){
        employee = new Employee("John Doe", 1970);
    }

    @Test
    @DisplayName("Test_age_calculation")
    void test1() {
        System.out.println("TC1");
        assertAll(
                () -> assertEquals(30,employee.getAge(2000)),
                () -> assertEquals("John Doe",employee.getName())
        );
    }

    @Test
    @Tag("unit")
    @Tag("Feature-329")
    void age_With_Zero() {
        System.out.println("TC2");
        assertTrue(30 == employee.getAge(2000));
        assertEquals(0,employee.getAge(1970));
        assertNotNull(employee);

        Employee expected = new Employee("John Doe",1970);
        assertEquals(expected,employee);
        assertNotSame(expected,employee);
    }

    @UnitTest
    void testYear1700() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Employee("John Doe",1700));
        assertEquals("year: 1700", ex.getMessage());
    }

    @TempDir
    Path tempDir;

    @Test
    void testWriteEmployee() throws IOException {
        Path file = tempDir.resolve("john-doe.txt");
        new EmployeeWriter()
                .write(file, List.of(new Employee("John Doe", 1970)));
        assertEquals("John Doe, 1970\n", Files.readString(file));
    }
}