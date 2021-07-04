package usedcar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCarCommand {
    @NotBlank
    private String brand;
    @NotBlank
    private String carType;
    @Positive
    private int age;
    private CarState state;
    //private List<KmState> kmState;
}
