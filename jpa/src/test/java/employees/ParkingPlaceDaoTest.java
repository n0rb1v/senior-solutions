package employees;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ParkingPlaceDaoTest {
    private ParkingPlaceDao parkingPlaceDao;
    private EmployeeDao employeeDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        parkingPlaceDao = new ParkingPlaceDao(factory);
        employeeDao = new EmployeeDao(factory);
    }
    @Test
    void saveParkingPlace() {
        parkingPlaceDao.saveParkingPlace(new ParkingPlace(100));
        ParkingPlace parkingPlace = parkingPlaceDao.findParkingPlace(100);
        assertEquals(100,parkingPlace.getNumber());
    }

    @Test
    void saveEmployeeParkingPlace() {
        ParkingPlace parkingPlace = new ParkingPlace(100);
        parkingPlaceDao.saveParkingPlace(parkingPlace);

        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000, 1, 11));
        employee.setParkingPlace(parkingPlace);
        employeeDao.save(employee);

        Employee another = employeeDao.findById(employee.getId());
        assertEquals(100,another.getParkingPlace().getNumber());
    }
}