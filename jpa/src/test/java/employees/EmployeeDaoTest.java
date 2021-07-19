package employees;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    private EmployeeDao employeeDao;
    @BeforeEach
    void init() throws SQLException {
//        MariaDbDataSource dataSource = new MariaDbDataSource();
//        dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
//        dataSource.setUser("employees");
//        dataSource.setPassword("employees");
//
//        Flyway flyway = Flyway.configure().dataSource(dataSource).locations("db/migration").load();
//        flyway.clean();
//        flyway.migrate();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        employeeDao = new EmployeeDao(entityManagerFactory);
    }

    @Test
    void testSave() {
        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));
        employeeDao.save(employee);

        Long id = employee.getId();

//        Employee another = employeeDao.findById(id);
//        assertEquals("John Doe",another.getName());
    }
    @Test
    void test10save() {
        for (int i = 0; i < 10; i++) {
            employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11)));
        }
        Employee employee = employeeDao.listAll().get(0);
        assertEquals("John Doe",employee.getName());

    }

    @Test
    void testListAll() {
        employeeDao.save(new Employee("John Doe", Employee.EmployeeType.FULL_TIME,LocalDate.now()));
        employeeDao.save(new Employee("Jack Doe", Employee.EmployeeType.HALF_TIME,LocalDate.now()));

        List<Employee> employees = employeeDao.listAll();
        assertEquals(List.of("Jack Doe", "John Doe"),employees.stream().map(Employee::getName).collect(Collectors.toList()));
    }

    @Test
    void testChange() {
        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.changeName(id,"Jack Doe");

        Employee another = employeeDao.findById(id);
        assertEquals("Jack Doe",another.getName());
    }
    @Test
    void testDelete() {
        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));
        employeeDao.save(employee);

        long id = employee.getId();

        employeeDao.delete(id);

        List<Employee> employees = employeeDao.listAll();

        assertEquals(0,employees.size());
    }
    @Test
    void testAttributes() {
        employeeDao.save(new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11)));
        Employee employee = employeeDao.listAll().get(0);

        assertEquals(LocalDate.of(2000,1,11),employee.getBirth());
    }
    @Test
    void testChangeState() {
        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));
        employeeDao.save(employee);
        employee.setName("Jack Doe");
        Employee modified = employeeDao.findById(employee.getId());
        assertEquals("John Doe",modified.getName());
        assertEquals(false,employee == modified);
    }

    @Test
    void testMerge() {
        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));
        employeeDao.save(employee);
        employee.setName("Jack Doe");
        employeeDao.update(employee);

        Employee modified = employeeDao.findById(employee.getId());
        assertEquals("Jack Doe", modified.getName());
    }
}