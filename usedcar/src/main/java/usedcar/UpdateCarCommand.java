package usedcar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCarCommand {
    @NotBlank
    private String brand;
    @NotBlank
    private String carType;
    @Positive
    private int age;
    private CarState state;
}
