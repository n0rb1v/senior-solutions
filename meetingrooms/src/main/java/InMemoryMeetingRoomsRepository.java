import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{

    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(String name, int width, int lenght) {
        meetingRooms.add(new MeetingRoom(0L, name, width,lenght));
    }

    @Override
    public List<MeetingRoom> findAll() {
        return meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAll() {
        meetingRooms.clear();
    }
}
