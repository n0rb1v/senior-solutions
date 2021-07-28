package meetingrooms;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Data
public class CompMeetingRoom extends MeetingRoom{

    private int vacation;

    public CompMeetingRoom(String name, int width, int length, int vacation) {
        super(name, width, length);
        this.vacation = vacation;
    }

}
