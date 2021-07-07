package empapp;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeParametrizedTest {

    @ParameterizedTest(name ="Get age: {0},age:{1}")
    //@ValueSource(ints = {1970,1980,1990})
    //@MethodSource("createAges")
//    @CsvSource({
//            "1970,30",
//            "1980,20",
//            "1990,10"
//    })
    @CsvFileSource(resources = "/ages.csv")
    void test(int yearOfBirth, int age){
        Employee employee = new Employee("John Doe", yearOfBirth);

        assertEquals(age,employee.getAge(2000));
    }
    static Stream<Arguments> createAges() {
        return Stream.of(
                Arguments.arguments(1970,30),
                Arguments.arguments(1980,20),
                Arguments.arguments(1990,10)
        );
    }
}
