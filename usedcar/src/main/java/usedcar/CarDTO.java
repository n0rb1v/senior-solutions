package usedcar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {
    private String brand;
    private String carType;
    private int age;
    private CarState state;
    //private List<KmState> kmState;

}
