package meetingrooms;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "meetingrooms")
public class MeetingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "mr_name")
    private String name;
    private int width;
    private int length;

    public MeetingRoom(String name, int width, int length) {
        this.name = name;
        this.width = width;
        this.length = length;
    }
}
