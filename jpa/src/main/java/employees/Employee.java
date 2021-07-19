package employees;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
//@IdClass(EmployeeId.class)
public class Employee {
    public enum EmployeeType {FULL_TIME, HALF_TIME}

//    @GeneratedValue(strategy = GenerationType.TABLE)
//    @GeneratedValue(generator = "Emp_name")
//    @TableGenerator(name = "Emp_name", table = "emp_id_gen", pkColumnName = "gen_name", valueColumnName = "gen_val")

    //    @Id
//    private String depName;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @EmbeddedId
//    private EmployeeId id;
    @Column(name = "emp_name", length = 200, nullable = false)
    private String name;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType = EmployeeType.FULL_TIME;

    private LocalDate birth;

    public Employee() {
    }

//    public Employee(Long id, String name, EmployeeType employeeType, LocalDate birth) {
//        this.id = id;
//        this.name = name;
//        this.employeeType = employeeType;
//        this.birth = birth;
//    }

    public Employee(String name, EmployeeType employeeType, LocalDate birth) {
        this.name = name;
        this.employeeType = employeeType;
        this.birth = birth;
    }

    @PostPersist
    public void debugPersist() {
        System.out.println(name + id);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeType getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
