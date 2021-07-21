package activitytracker;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Trackpoint {
    private Long id;
    private LocalDate time;
    private double lat;
    private double lon;
}
