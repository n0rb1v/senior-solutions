package employees;

import javax.persistence.*;

@Entity
@Table(name = "phone_numbers")
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String number;
    @ManyToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;
    private int pos;

    public PhoneNumber() {
    }

    public PhoneNumber(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }
}
