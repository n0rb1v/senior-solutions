package movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    private long id;
    private String title;
    private int length;
    private List<Integer> rates;
    private double rate;

}
