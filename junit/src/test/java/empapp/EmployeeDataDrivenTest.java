package empapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDataDrivenTest {
    private int[][] values = {{1970,0},{1980,10},{1990,20}};

    @RepeatedTest(value = 3, name = "Get age {currentRepetition} / {totalRepetitions}")
    void test(RepetitionInfo repInfo){
        Employee employee = new Employee("John Doe", 1970);

        assertEquals(values[repInfo.getCurrentRepetition()-1][1],
                employee.getAge(values[repInfo.getCurrentRepetition()-1][0]));
    }
}
