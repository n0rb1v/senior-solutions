package meetingrooms;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class ExtMeetingRoom extends MeetingRoom{

    private int dailyRate;

    public ExtMeetingRoom(String name, int width, int length, int dailyRate) {
        super(name, width, length);
        this.dailyRate = dailyRate;
    }

}
