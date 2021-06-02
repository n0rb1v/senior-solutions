import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoomsService {
    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int lenght) {
        meetingRoomsRepository.save(name, width, lenght);
    }

    public List<MeetingRoom> listMeetingRooms() {
        return meetingRoomsRepository.findAll();
    }

    public List<MeetingRoom> revListMeetingRooms() {
        return meetingRoomsRepository.findAll().stream()
                .sorted(Comparator.comparing(MeetingRoom::getName).reversed())
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> evenListMeetingRooms() {
        List<MeetingRoom> even = meetingRoomsRepository.findAll();
        return IntStream.range(0, even.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(even::get)
                .collect(Collectors.toList());
    }

    public List<MeetingRoom> areaListMeetingRooms() {
        List<MeetingRoom> area = meetingRoomsRepository.findAll();
        return area.stream()
                .sorted(Comparator.comparing(MeetingRoom::getArea).reversed())
                .collect(Collectors.toList());
    }

    public Optional<MeetingRoom> findByName(String s) {
        List<MeetingRoom> name = meetingRoomsRepository.findAll();
        return name.stream()
                .filter(meetingRoom -> meetingRoom.getName().equals(s))
                .findAny();
    }

    public Optional<MeetingRoom> findByPartname(String s) {
        List<MeetingRoom> name = meetingRoomsRepository.findAll();
        return name.stream()
                .filter(meetingRoom -> meetingRoom.getName().contains(s.toLowerCase()))
                .findAny();
    }

    public List<MeetingRoom> findByArea(int n) {
        List<MeetingRoom> area = meetingRoomsRepository.findAll();
        return area.stream()
                .filter(meetingRoom -> meetingRoom.getArea() > n)
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
