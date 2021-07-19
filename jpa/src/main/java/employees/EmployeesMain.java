package employees;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class EmployeesMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();

        Employee employee = new Employee("John Doe", Employee.EmployeeType.HALF_TIME, LocalDate.of(2000,1,11));

        em.persist(employee);

        em.getTransaction().commit();

//        long id = employee.getId();
//        employee = em.find(Employee.class, id);
//        System.out.println(employee);
//
//        em.getTransaction().begin();
//        employee = em.find(Employee.class,id);
        employee.setName("John Jack Doe");
        em.getTransaction().commit();

        List<Employee> employees = em.createQuery("select e from Employee e", Employee.class)
                .getResultList();
        System.out.println(employees);

        em.close();
        factory.close();
    }
}
