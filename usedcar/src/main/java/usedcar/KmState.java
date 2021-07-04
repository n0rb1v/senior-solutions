package usedcar;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class KmState {
    private LocalDate date;
    private int kilometer;

    public KmState(String date, int kilometer) {
        this.date = LocalDate.parse(date);
        this.kilometer = kilometer;
    }

    public KmState(LocalDate date, int kilometer) {
        this.date = date;
        this.kilometer = kilometer;
    }
}
